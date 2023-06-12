package soundbeats.soundbeatsproject.soundbeatsartifact.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;


import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;

public class RabbitMQUtil {
    private static final String EXCHANGE_CARRERA = "categorias";
    ConnectionFactory factory;
    volatile boolean fin = false;
    Consulta cons;
    Gson gson;

    public RabbitMQUtil() {
        gson = new Gson();
    }

    public Consulta conexion(String nuss) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException {

        char[] keyPassphrase = "sf4463sf".toCharArray();
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(new FileInputStream("certs/client_my-rabbit.p12"), keyPassphrase);
  
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, keyPassphrase);
  
        char[] trustPassphrase = "sf4463sf".toCharArray();
        KeyStore tks = KeyStore.getInstance("JKS");
        tks.load(new FileInputStream("certs/truststoreplus.jks"), trustPassphrase);
  
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(tks);
  
        SSLContext c = SSLContext.getInstance("TLSv1.2");
        c.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
  
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5671);
        factory.useSslProtocol(c);


        factory = new ConnectionFactory();
        factory.setHost("soundbeatsnodered.duckdns.org");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();

            // Creación de Exchanges
            channel.exchangeDeclare(EXCHANGE_CARRERA, "topic");

            String queueName = channel.queueDeclare().getQueue();
            String topic = "#."+nuss;
            channel.queueBind(queueName, EXCHANGE_CARRERA, topic);

            final CountDownLatch latch = new CountDownLatch(1);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String response = new String(delivery.getBody(), "UTF-8");
                cons = gson.fromJson(response, Consulta.class);
                latch.countDown(); // Signal that the response has been received
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});

            try {
                latch.await(); // Wait until the response is received
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            channel.close();
        } catch (IOException | TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cons;
    }
}
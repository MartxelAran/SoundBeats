package soundbeats.soundbeatsproject.soundbeatsartifact.utils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;

public class RabbitMQUtil {
    private static final String EXCHANGE_CARRERA = "categorias";
    ConnectionFactory factory;
    volatile boolean fin = false;
    Consulta cons;
    Gson gson;

    public RabbitMQUtil() {
        gson = new Gson();
    }

    public Consulta conexion() {
        factory = new ConnectionFactory();
        factory.setHost("soundbeatsnodered.duckdns.org");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();

            // Creación de Exchanges
            channel.exchangeDeclare(EXCHANGE_CARRERA, "topic");

            String queueName = channel.queueDeclare().getQueue();
            String topic = "#."+LoggedPaciente.getPaciente().getNumss();
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
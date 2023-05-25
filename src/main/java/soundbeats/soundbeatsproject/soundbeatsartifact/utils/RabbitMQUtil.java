package soundbeats.soundbeatsproject.soundbeatsartifact.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import soundbeats.soundbeatsproject.soundbeatsartifact.domain.consulta.Consulta;
import soundbeats.soundbeatsproject.soundbeatsartifact.domain.paciente.LoggedPaciente;

public class RabbitMQUtil {
    private static final String EXCHANGE_CARRERA = "categorias";
    ConnectionFactory factory;
    volatile boolean fin = false;
    Consulta cons;

    public RabbitMQUtil() {
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
            MiConsumer consumer = new MiConsumer(channel, this);
            channel.basicConsume(queueName, true, consumer);
            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            channel.close();
            System.out.println("Channel .close");
        } catch (IOException | TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cons;
    }

    public class MiConsumer extends DefaultConsumer {

        RabbitMQUtil r;

        public MiConsumer(Channel channel, RabbitMQUtil rabbitMQUtil) {
            super(channel);
            this.r=rabbitMQUtil;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                throws IOException {
            System.out.println("Handle");
            Gson gson = new Gson();
            String message = new String(body, StandardCharsets.UTF_8);
            Consulta consulta = gson.fromJson(message, Consulta.class);
            System.out.println("Recibido en handler: " + consulta);
        }
    }
}
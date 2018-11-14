package kafka.client;

import kafka.client.consumer.ConsumerProperties;
import kafka.client.consumer.ConsumerThread;
import kafka.client.metrics.impl.CSVKafkaReporter;
import kafka.client.metrics.impl.ConsoleKafkaReporter;
import kafka.client.metrics.impl.GraphiteKafkaReporter;
import kafka.client.metrics.impl.Sl4jKafkaReporter;
import kafka.client.producer.ProducerProperties;
import kafka.client.producer.ProducerThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@Configuration
public class Application {

    private static ProducerProperties producerProperties;
    private static ConsumerProperties consumerProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new ProducerThread(producerProperties));
        executorService.submit(new ConsumerThread(consumerProperties));
        executorService.submit(new Sl4jKafkaReporter());
        executorService.submit(new CSVKafkaReporter());
        executorService.submit(new ConsoleKafkaReporter());
        executorService.submit(new GraphiteKafkaReporter());
        System.out.println("Hello World!");
    }

    @Autowired
    public void setProducerProperties(ProducerProperties producerProperties) {
        Application.producerProperties = producerProperties;
    }

    @Autowired
    public void setConsumerProperties(ConsumerProperties consumerProperties) {
        Application.consumerProperties = consumerProperties;
    }
}

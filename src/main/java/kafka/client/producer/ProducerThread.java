package kafka.client.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class ProducerThread implements Runnable {
    private ProducerProperties producerConfig;

    public ProducerThread(ProducerProperties producerConfig) {
        this.producerConfig = producerConfig;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", producerConfig.getServer());
            props.put("acks", producerConfig.getAcks());
            props.put("retries", producerConfig.getRetries());
            props.put("batch.size", producerConfig.getBatchSize());
            props.put("linger.ms", producerConfig.getLinger());
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("metric.reporters", producerConfig.getReporters());
            Producer<String, String> producer = new KafkaProducer(props);
            while (true) {
                try {
                    RecordMetadata result = (RecordMetadata) producer.send(new ProducerRecord(producerConfig.getTopic(), "test", "test")).get();
                    System.out.printf("Send message,  offset = %d, partition= %s%n", result.offset(), result.partition());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

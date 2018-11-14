package kafka.client.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerThread implements Runnable {
    private ConsumerProperties consumerProperties;

    public ConsumerThread(ConsumerProperties consumerProperties) {
        this.consumerProperties = consumerProperties;
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
            props.put("bootstrap.servers", consumerProperties.getServer());
            props.put("group.id", consumerProperties.getGroupId());
            props.put("enable.auto.commit", consumerProperties.isAutoCommit());
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("metric.reporters", consumerProperties.getReporters());
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(consumerProperties.getTopic()));
            while (true) {
                try {
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                        System.out.printf("Consume message count = %d%n", records.count());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

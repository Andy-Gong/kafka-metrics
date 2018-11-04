package kafka.client.metrics.impl;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Slf4jReporter;
import kafka.client.metrics.KafkaReporter;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Sl4jKafkaReporter extends KafkaReporter {
    private final static MetricRegistry metricRegistry = new MetricRegistry();

    @Override
    public ScheduledReporter scheduledReporter() {
        return Slf4jReporter.forRegistry(metricRegistry)
                .outputTo(LoggerFactory.getLogger(Sl4jKafkaReporter.class.getName()))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public MetricRegistry metricRegistry() {
        return metricRegistry;
    }
}

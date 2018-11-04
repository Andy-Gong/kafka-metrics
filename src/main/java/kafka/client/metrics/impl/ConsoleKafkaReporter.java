package kafka.client.metrics.impl;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import kafka.client.metrics.KafkaReporter;

import java.util.concurrent.TimeUnit;

public class ConsoleKafkaReporter extends KafkaReporter {
    private final static MetricRegistry metricRegistry = new MetricRegistry();

    @Override
    public ScheduledReporter scheduledReporter() {
        return ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
    }

    @Override
    public MetricRegistry metricRegistry() {
        return metricRegistry;
    }
}

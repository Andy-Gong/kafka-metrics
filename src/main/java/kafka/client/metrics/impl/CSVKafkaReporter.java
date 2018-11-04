package kafka.client.metrics.impl;

import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import kafka.client.metrics.KafkaReporter;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class CSVKafkaReporter extends KafkaReporter {
    private final static MetricRegistry metricRegistry = new MetricRegistry();

    @Override
    public ScheduledReporter scheduledReporter() {
        return CsvReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new File("~/data/"));
    }

    @Override
    public MetricRegistry metricRegistry() {
        return metricRegistry;
    }
}

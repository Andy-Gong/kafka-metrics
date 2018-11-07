package kafka.client.metrics.impl;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.PickledGraphite;
import kafka.client.metrics.KafkaReporter;

import java.util.concurrent.TimeUnit;

public class GraphiteKafkaReporter extends KafkaReporter {
    private final static MetricRegistry metricRegistry = new MetricRegistry();
    private static final String host = "localhost";
    private static final int port = 2004;

    @Override
    public ScheduledReporter scheduledReporter() {
        return GraphiteReporter.forRegistry(metricRegistry)
                .prefixedWith("test")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(new PickledGraphite(this.host, this.port));
    }

    @Override
    public MetricRegistry metricRegistry() {
        return metricRegistry;
    }
}

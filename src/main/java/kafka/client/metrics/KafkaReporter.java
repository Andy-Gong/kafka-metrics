package kafka.client.metrics;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import org.apache.kafka.common.metrics.KafkaMetric;
import org.apache.kafka.common.metrics.MetricsReporter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class KafkaReporter implements MetricsReporter, Runnable {
    private final ScheduledReporter scheduledReporter;
    private final Set<KafkaMetric> kafkaMetrics = new HashSet<>();

    public KafkaReporter() {
        scheduledReporter = scheduledReporter();
    }

    public abstract ScheduledReporter scheduledReporter();

    public abstract MetricRegistry metricRegistry();

    /**
     * This is called when the reporter is first registered to initially register all existing metrics
     *
     * @param metrics All currently existing metrics
     */
    @Override
    public void init(List<KafkaMetric> metrics) {
        metrics.forEach(this::metricChange);

    }

    /**
     * This is called whenever a metric is updated or added
     *
     * @param metric
     */
    @Override
    public void metricChange(KafkaMetric metric) {
        synchronized (metricRegistry()) {
            if (metricRegistry().getGauges().get(metricName(metric)) != null) {
                metricRegistry().remove(metricName(metric));
            }
            metricRegistry().register(metricName(metric), new Gauge() {
                @Override
                public Object getValue() {
                    return metric.value();
                }
            });
        }
        kafkaMetrics.add(metric);
    }

    private String metricName(KafkaMetric metric) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(metric.metricName().group())
                .append(metric.metricName().name());
        return stringBuilder.toString();
    }

    /**
     * This is called whenever a metric is removed
     *
     * @param metric
     */
    @Override
    public void metricRemoval(KafkaMetric metric) {
        metricRegistry().remove(metricName(metric));
        kafkaMetrics.remove(metric);
    }

    /**
     * Called when the metrics repository is closed.
     */
    @Override
    public void close() {
        kafkaMetrics.forEach(kafkaMetric -> {
            metricRegistry().remove(metricName(kafkaMetric));
        });
        kafkaMetrics.clear();
    }

    /**
     * Configure this class with the given key-value pairs
     *
     * @param configs
     */
    @Override
    public void configure(Map<String, ?> configs) {

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
        scheduledReporter.start(30, TimeUnit.SECONDS);
    }
}

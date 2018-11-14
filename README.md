# Build Kafka Client Metrics in Graphite

This project describes how to gather kafka client metrics, and reports them to graphite server. You also can see the change of kafka metrics, e.g request-rate, request size when you change your producer configurations, like batch.size, linger.ms.

It also gives other reporter methods, like:
- ConsoleKafkaReporter, which will output kafka client metrics in the console
- CSVKafkaReporter, which will output kafka client metrics into csv file, default path is {project dir}/csv/ 
- Sl4jKafkaReporter, which output kafka client metrics into logs, default path is {project dir}/logs/

# Percondition
- Maven 3++
- java 8


# Run application

1. Download source code

```
git clone https://github.com/Andy-Gong/kafka-metrics.git
```

2. Run with source code

   Find Application class and run it.

3. Run with jar

```
mvn clean install
java -jar target/kafka-client-metrics-1.0-SNAPSHOT.jar
```

# Run graphite

Start graphite with docker image, 
```
docker run -d\
 --name graphite\
 --restart=always\
 -p 80:80\
 -p 2003-2004:2003-2004\
 -p 2023-2024:2023-2024\
 -p 8125:8125/udp\
 -p 8126:8126\
 hopsoft/graphite-statsd
```
Open graphite in brower: http://localhost/dashboard

Detail information about hopsoft/graphite-statsd, you can find in [this repository](https://github.com/hopsoft/docker-graphite-statsd).

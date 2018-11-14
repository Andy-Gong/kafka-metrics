# Build Kafka Client Metrics in Graphite
This project describes how to gather kafka client metrics, and it provides 4 reporters:
- ConsoleKafkaReporter, which will output kafka client metrics in the console
- CSVKafkaReporter, which will output kafka client metrics into csv file, default path is {project dir}/csv/ 
- GraphiteKafkaReporter, which will send kafka client metrics to Graphite server, the default host is 'localhost', port is '2004', [this repository](https://github.com/hopsoft/docker-graphite-statsd) introduces how to start a graphite service with docker image.
- Sl4jKafkaReporter, which output kafka client metrics into logs, default path is {project dir}/logs/

# Percondition
- Maven 3 or greater
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

Start graphite with image, 
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
Open graphite in brower
  http://localhost/dashboard

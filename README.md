# kafka-client-metrics
This project describes how to gather kafka client metrics, and it provides 4 reporters which include ConsoleKafkaReporter, CSVKafkaReporter, GraphiteKafkaReporter, Sl4jKafkaReporter.

# how to start application in local envirment
```
git clone https://github.com/Andy-Gong/kafka-metrics.git
```

1. Run with source code
Find Application class and run it.

2. Run with jar
```
mvn clean install
java -jar target/kafka-client-metrics-1.0-SNAPSHOT.jar
```

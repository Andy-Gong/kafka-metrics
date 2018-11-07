# kafka-client-metrics
This project describes how to gather kafka client metrics, and it provides 4 reporters:
- ConsoleKafkaReporter 
- CSVKafkaReporter 
- GraphiteKafkaReporter
- Sl4jKafkaReporter

# Percondition
- Maven 3 or greater
- java 8


# Run application

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

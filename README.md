# kafka-client-metrics
This project describes how to gather kafka client metrics, and it provides 4 reporters which include ConsoleKafkaReporter, CSVKafkaReporter, GraphiteKafkaReporter, Sl4jKafkaReporter.

# how to start in local envirment
git clone https://github.com/Andy-Gong/kafka-metrics.git

run with source code


build jar
mvn clean install

run application
java -jar target/kafka-client-metrics-1.0-SNAPSHOT.jar

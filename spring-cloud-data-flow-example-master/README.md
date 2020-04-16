# spring-cloud-data-flow
Spring Cloud Data Flow Example with Kafka-binder 1

I went through this video https://www.youtube.com/watch?v=TTsOoQ6_QB0 and it solved my issue.

### Apache Zookeeper :

Create `zookeeper_data` (You can choose any name) inside `C:\kafka_2.11-2.3.1` where my Kafka distribution has kept.

Then go to `C:\kafka_2.11-2.3.1\config` and edit `zookeeper.properties` file and use `dataDir=C:\kafka_2.11-2.3.1\zookeeper_data`

# Step to Start Zookeeper:

`zookeeper-server-start.bat C:\kafka_2.11-2.3.1\config\zookeeper.properties`

##### Apache Kafka: 

Create kafka-logs folder under `C:\kafka_2.11-2.3.1` and then Go to `C:\kafka_2.11-2.3.1\config` and edit `server.properties` file and use below

enter image description here

# A comma separated list of directories under which to store log files
log.dirs=C:\kafka_2.11-2.3.1\kafka-logs

############################# Internal Topic Settings  #############################
# The replication factor for the group metadata internal topics "__consumer_offsets" and "__transaction_state"
# For anything other than development testing, a value greater than 1 is recommended for to ensure availability such as 3.
offsets.topic.num.partitions=1
offsets.topic.replication.factor=1
transaction.state.log.replication.factor=1
transaction.state.log.min.isr=1
min.insync.replicas=1
default.replication.factor=1
Step To start Apache Kafka


# Step to Start Apache Kafka:

`kafka-server-start.bat C:\kafka_2.11-2.3.1\config\server.properties`



================================================================================================
###### Follow Below Steps

###### 1) Apache-Kafka Binary Distribution [Download](http://apachemirror.wuchna.com/kafka/2.3.1/kafka_2.11-2.3.1.tgz).

###### 2) Strat Zookeeper server
> zookeeper-server-start.bat D:\software\kafka_2.11-2.3.1\config\zookeeper.properties

###### 3) Strat Kafka server 
> kafka-server-start.bat D:\software\kafka_2.11-2.3.1\config\server.properties

###### 4) Download Spring Cloud Data Flow Server jar [Download](https://repo.spring.io/milestone/org/springframework/cloud/spring-cloud-dataflow-server-local/1.7.4.RELEASE/spring-cloud-dataflow-server-local-1.7.4.RELEASE.jar).

###### 5) Strat Spring Cloud Data Flow Server 
> java -jar spring-cloud-dataflow-server-local-1.7.4.RELEASE.jar

Then access the  `http://localhost:9393/dashboard`

###### 6) Download Spring Cloud Data Flow Shell jar [Download](http://repo.spring.io/milestone/org/springframework/cloud/spring-cloud-dataflow-shell/1.3.0.M1/spring-cloud-dataflow-shell-1.3.0.M1.jar).

###### 7) Strat Spring Cloud Data Flow shell 
> java -jar spring-cloud-dataflow-shell-1.3.0.M1.jar

###### 8) Register all 3 microservices to Spring Cloud Data Flow Server using below commands
> app register --name product-service --type source --uri maven://com.javatechie:product-service:jar:0.0.1-SNAPSHOT

> app register --name discount-service --type processor --uri maven://com.javatechie:discount-service:jar:0.0.1-SNAPSHOT

> app register --name courier-service --type sink --uri maven://com.javatechie:courier-service:jar:0.0.1-SNAPSHOT

###### 9) Create Cloud Stream to connect between all microservices registered in spring cloud data flow server
> create --name log-data --definition 'product-service | discount-service | courier-service'

###### 10) Strat & Deploy Stream 
> stream deploy --name log-data

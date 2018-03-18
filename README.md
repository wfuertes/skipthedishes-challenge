# skipthedishes-challenge

The project challenge is not complete, but I had cover a good part. Let's understand my approach for the problem. I had choose a microservices architecture, so as API Gateways I choose the Nginx (for hour), because we don't have to much time for a approach using Zuul and Spring Cloud.

1. Nginx as API Gateway;
2. SpringBoot for each microservice (auth-service, product-service and order-service);
3. At order-service I also used a ActiveMQ to hava queue for incoming orders;
4. At order-service as consumer to process the messages  (orders);
5. As dada base I am using a in-memory H2 to be more easy handle the approach;
6. For the queue I am using docker;
```
$ docker run --name='activemq' -it --rm -p 8161:8161 -p 61616:61616 -e 'ACTIVEMQ_CONFIG_MINMEMORY=128' -e 'ACTIVEMQ_CONFIG_MAXMEMORY=256' -P webcenter/activemq:latest
```

Follow a brief image of my archtequiture.

![pseudo architecture](/pseudo-arch.jpeg)
# skipthedishes-challenge

The project challenge is not complete, but I was able to cover a good part. Let's understand my approach for the problem. I had choose a microservices architecture, so as API Gateway I am using the Nginx (for hour). Here we also can use Zuul and Spring Cloud.

1. Nginx as API Gateway;
2. SpringBoot for each microservice (auth-service, product-service and order-service);
3. At order-service I was used a ActiveMQ in order to have a queue for incoming orders;
4. At order-service I put consumer responsible for process the messages (orders);
5. As database I am using a in-memory H2 to represent one database per microservice;
6. For the queue I am using docker with ActiveMQ;
```
$ docker run --name='activemq' -it --rm -p 8161:8161 -p 61616:61616 -e 'ACTIVEMQ_CONFIG_MINMEMORY=128' -e 'ACTIVEMQ_CONFIG_MAXMEMORY=256' -P webcenter/activemq:latest
```

Follow a brief image of my archtequiture.

![pseudo architecture](/pseudo-arch.jpeg)

## Auth Servie

### Missing, but I am going to use JWT.

## Product Api

### GET products
```
curl -X GET \
  http://localhost:8082/v1/products \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: b08d9082-d5f3-452e-80f2-5a4e6b3f7f36'
```

### GET search products by name
```
curl -X GET \
  http://localhost:8082/v1/products/search/{searchText} \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 793c2574-c01f-4589-8abe-3516c489c376'
```


## Order API

### GET Order
```
curl -X GET \
  http://localhost:8082/orders/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 56d80667-a57d-40e9-bc12-ffa39a11766e'
```

### Create Order
```
curl -X POST \
  http://localhost:8082/orders \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 550d3355-e7f3-4a1b-b4bb-280b584122ec' \
  -d '{
  "date": "2018-03-18T17:57:48.469Z",
  "customerId": 100,
  "deliveryAddress": "880 13 May",
  "contact": "wfuertes@gmail.com",
  "storeId": 200,
  "orderItems": [
    {
      "productId": 1,
      "price": 10.90,
      "quantity": 2,
      "total": 21.80
    }
  ],
  "total": 21.80,
  "status": "INCOMING",
  "lastUpdate": "2018-03-18T17:57:48.469Z"
}'
```

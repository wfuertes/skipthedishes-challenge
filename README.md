# skipthedishes-challenge

The project challenge is not complete, but I was able to cover a good part. Let's understand my approach for the problem. I had choose a microservices architecture, so as API Gateway I am using the Nginx (for hour). Here we also can use Zuul and Spring Cloud.

1. Nginx as API Gateway;
2. SpringBoot for each microservice (auth-service, product-service and order-service);
3. At order-service I was used a ActiveMQ in order to have a queue for incoming orders;
4. At order-service I put consumer responsible for process the messages (orders);
5. As database I am using a in-memory H2 to represent one database per microservice;
6. For the queue I am using docker with ActiveMQ: [http://localhost:8161](http://localhost:8161) [user: admin / pass: admin]


Follow a brief image of my archtequiture.

![pseudo architecture](/challenge-arch.png)

## How to run the project
It needs `docker` and `docker-compose` installed.

### Running
```
$ docker-compose up
```

### Nginx will expose order APIs

My nginx is exposed at port 8888: [http://localhost:8888]

* `auth` => `http://localhost:8888/auth`
* `costumers` => `http://localhost:8888/api/v1/costumers` (not completed)
* `products` => `http://localhost:8888/api/v1/products`
* `orders` => `http://localhost:8888/api/v1/orders`


## Auth Servie

### POST authenticating

```
curl -X POST \
  http://localhost:8888/auth \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a800f17d-bef3-4ee2-800f-6d648cffd3f5' \
  -d '{
	"username": "admin",
	"password": "admin"
   }'
```

### GET User info
```
curl -X GET \
  http://localhost:8888/auth/user \
  -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZGllbmNlIjoid2ViIiwiY3JlYXRlZCI6MTUyMTgxNDg1MzQ2MiwiZXhwIjoxNTIyNDE5NjUzfQ.iwRlMtICAMSsajVgvBWnmVSyJhF6xWO12r6QtfGqMLVA7yKZq0qaQqP050bWmjd0Otn6qcHNMbeNudUaXrrltA' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 24b05353-4f90-4faa-adda-347f671e431c'
```

## Costumers API

### (Not implemented yet).

## Product API

### GET products
```
curl -X GET \
  http://localhost:8888/api/v1/products \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: b08d9082-d5f3-452e-80f2-5a4e6b3f7f36'
```

### GET search products by name
```
curl -X GET \
  http://localhost:8888/api/v1/products/search/{searchText} \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 793c2574-c01f-4589-8abe-3516c489c376'
```


## Order API

### GET Order
```
curl -X GET \
  http://localhost:8888/api/v1/orders/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 56d80667-a57d-40e9-bc12-ffa39a11766e'
```

### Create Order
```
curl -X POST \
  http://localhost:8888/api/v1/orders \
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

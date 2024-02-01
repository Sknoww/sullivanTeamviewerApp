<div align="center">
<h1>
Hunter Sullivan's Programming Challenge for Teamviewer
</h1>

</div>

## Description
This is a REST API that allows for the management of products, orders and order-items created for Hunter Sullivan's programming challenge for Teamviewer.
Since this was a programming challenge, the Dockerfiles and docker-compose.yml files were kept fairly simple but could be expanded upon by adding different envs, build options, etc.
The application was created using Java, Spring Boot, and Maven. The database used is PostreSQL, which is an in-memory database hosted using Docker.

## Prerequisites
- Docker installed and running
- (Optional) Maven and Java installed and running (Only necessary if you want to run specific Maven commands. i.e. mvn clean install, mvn clean package, etc.)

### OpenAPI Documentation
OpenAPI generated documentation can be accessed at the below url after starting the app using the process explained below.
```sh
localhost:8080/v3/api-docs
```

## Installation

### Download
```sh
curl -L -O https://github.com/Sknoww/sullivanTeamviewerApp/archive/master.zip
```
### or
Manual download: 
[Click Here](https://github.com/Sknoww/sullivanTeamviewerApp/archive/master.zip)

Once the download is complete, be sure to extract the zip.
#### Windows (CMD)
```sh
tar -xf master.zip
```
#### Mac
```sh
unzip master.zip
```
#### Linux
```sh
sudo apt-get install unzip
unzip master.zip
```

## Usage
### Starting REST API
```sh
cd sullivanTeamviewerApp-master
docker compose --profile dev up -d --build
```
This will download all of the dependencies, run the unit and integration tests and finally start the api. The api will be hosted at localhost:8080.

*If there is an issue running the docker compose, be sure Docker Desktop is running.
If the issue persists, add the @Disabled annotation to the integration testing classes. 
Java TestContainers can have issues with Docker containers due to trying to establish a Docker container in a Docker container.
These can be run manually if Maven and Java are installed.

### Running tests
```sh
cd sullivanTeamviewerApp-master
docker compose --profile test up --build
```
This will run the command 'mvn clean test' which will run all unit and integration tests.

*This will show all of the logs so that you can see if the tests pass successfully. However, to exit you need to press Ctrl+C.

### Shutting down the application
```sh
docker compose --profile dev down
```
### or
```sh
docker compose --profile test down
```
This will shut down the application and Docker containers.

### Manual testing
The app can be manually tested after starting the app and hitting the url: localhost:8080

There are sample requests below that can be used to test and use the application.

## Sample requests

### ProductAPI:

#### GET - getAllProducts
Request:
```sh
GET localhost:8080/api/products
```
Respose:
```js
[
    {
        "id": 1,
        "name": "ProductA",
        "price": 10.99
    },
    {
        "id": 2,
        "name": "ProductB",
        "price": 10.99
    }
]
```

#### GET - getProductById
Request:
```sh
GET localhost:8080/api/products/1
```
Response:
```js
{
    "id": 1,
    "name": "ProductA",
    "price": 10.99
}
```

#### POST - createAProduct
Request:
```sh
POST localhost:8080/api/products
```
Request Body:
```js
{
    "name": "ProductA",
    "price": 10.99
}
```
Response:
```js
{
    "id": 1,
    "name": "ProductA",
    "price": 10.99
}
```

#### PUT - updateAProduct
Request:
```sh
PUT localhost:8080/api/products/1
```
Request Body:
```js
{
    "name": "ProductC",
    "price": 20.99
}
```
Response:
```js
{
    "id": 1,
    "name": "ProductC",
    "price": 20.99
}
```

#### DELETE - deleteAProduct
Request:
```sh
DELETE localhost:8080/api/products/1
```

### OrdersAPI:

#### GET - getAllOrders
Request:
```sh
GET localhost:8080/api/orders
```
Response:
```js
[
    {
        "id": 1,
        "customerName": "John Doe",
        "orderItems": []
    },
    {
        "id": 2,
        "customerName": "Jane Doe",
        "orderItems": []
    }
    
]
```
Response (with order-items):
```js
[
    {
        "id": 1,
        "customerName": "John Doe",
        "orderItems": [
            {
                "id": 1,
                "product": {
                    "id": 1,
                    "name": "ProductA",
                    "price": 10.99
                },
                "quantity": 2
            }
        ]
    },
    {
        "id": 2,
        "customerName": "Jane Doe",
        "orderItems": [
            {
                "id": 2,
                "product": {
                    "id": 1,
                    "name": "ProductA",
                    "price": 10.99
                },
                "quantity": 2
            }
        ]
    }
]
```

#### GET - getOrderById
Request:
```sh
GET localhost:8080/api/orders/1
```
Response:
```js
{
    "id": 1,
    "customerName": "John Doe",
    "orderItems": [
        {
            "id": 1,
            "product": {
                "id": 1,
                "name": "ProductA",
                "price": 10.99
            },
            "quantity": 2
        }
    ]
}
```

#### POST - createAnOrder
Request:
```sh
POST localhost:8080/api/orders
```
Respose:
```js
{
    "id": 1,
    "customerName": "John Doe",
    "orderItems": null
}
```

#### PUT - updateAnOrder
Request:
```sh
PUT localhost:8080/api/orders/1
```
Request Body:
```js
{
    "customerName": "John Smith"
}
```
Response:
```js
{
    "id": 1,
    "customerName": "John Smith",
    "orderItems": [
        {
            "id": 1,
            "product": {
                "id": 1,
                "name": "ProductA",
                "price": 10.99
            },
            "quantity": 2
        }
    ]
}
```

#### DELETE - deleteAnOrder
Request:
```sh
DELETE localhost:8080/api/orders/1
```

### OrderItemsAPI:

#### GET - getAllOrderItems
Request:
```sh
GET localhost:8080/api/order-items
```
Respose:
```js
[
    {
        "id": 1,
        "product": {
            "id": 1,
            "name": "ProductA",
            "price": 10.99
        },
        "order": {
            "id": 1,
            "customerName": "John Smith"
        },
        "quantity": 2
    },
    {
        "id": 2,
        "product": {
            "id": 1,
            "name": "ProductA",
            "price": 10.99
        },
        "order": {
            "id": 2,
            "customerName": "Jane Doe"
        },
        "quantity": 2
    }
]
```

#### GET - getOrderItemById
Request:
```sh
GET localhost:8080/api/order-items/1
```
Response:
```js
{
    "id": 1,
    "product": {
        "id": 1,
        "name": "ProductA",
        "price": 10.99
    },
    "order": {
        "id": 1,
        "customerName": "John Smith"
    },
    "quantity": 2
}
```

#### POST - createAnOrderItem
Request:
```sh
POST localhost:8080/api/order-items
```
Request Body:
```js
{
    "productId": 1,
    "orderId": 1,
    "quantity": 2
}
```
Response:
```js
{
    "id": 1,
    "product": {
        "id": 1,
        "name": "ProductA",
        "price": 10.99
    },
    "order": {
        "id": 1,
        "customerName": "John Smith"
    },
    "quantity": 2
}
```

#### PUT - updateAnOrderItem
Request:
```sh
PUT localhost:8080/api/order-items/1
```
Request Body:
```js
{
    "quantity": 5
}
```
Response:
```js
{
    "id": 1,
    "product": {
        "id": 1,
        "name": "ProductA",
        "price": 10.99
    },
    "order": {
        "id": 1,
        "customerName": "John Smith"
    },
    "quantity": 5
}
```

#### DELETE - deleteAnOrderItem
Request:
```sh
DELETE localhost:8080/api/order-items/1
```

### Metadata
H. Sullivan - sknow.codes@gmail.com

### License
MIT License, reference `LICENSE` for details.

<div align="center">
<h1>
Hunter Sullivan's Programming Challenge for Teamviewer
</h1>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

</div>

## Prerequisites
- Docker installed and running
- (Optional) Maven and Java installed and running (Only necessary if you want to run specific Maven commands. i.e. mvn clean install, mvn clean package, etc.)

### OpenAPI Documentation
OpenAPI generated documentation can be accessed at the below url after starting the app using the process explained below.
```sh
localhost:8080/v3/api-docs
```

## Installation

```sh
wget https://github.com/Sknoww/sullivanTeamviewerApp/archive/master.zip
```
### or
```sh
curl -L -O https://github.com/Sknoww/sullivanTeamviewerApp/archive/master.zip
```
### or
Manual download: 
[Click Here](https://github.com/Sknoww/sullivanTeamviewerApp/archive/master.zip)

Once the download is complete, be sure to extract the zip.

## Usage
### Starting RESTapi
```sh
cd ~/Downloads/sullivanTeamviewerApp
docker-compose --profile dev up --build
```
This will download all of the dependencies, run the unit and integration tests and finally start the api. The api will be hosted at localhost:8080.

*If there is an issue running the docker-compose, be sure Docker Desktop is running.

### Running tests
```sh
cd ~/Downloads/sullivanTeamviewerApp
docker-compose --profile test up --build
```
This will run the command 'mvn clean test' which will run all unit and integration tests.

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

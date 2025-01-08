

# E-Commerce Microservices Platform

This is an open-source **E-Commerce Platform** built using a **Microservices Architecture** with **Spring Boot**, **Docker**, and **Kubernetes**. It supports multiple vendors, inventory management, dynamic pricing, and payment integration. This project is maintained by **Aditya Pratap Bhuyan** and is licensed under the **GNU General Public License v3.0**.

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Classes and Components](#classes-and-components)
4. [Configuration](#configuration)
5. [Building the Application](#building-the-application)
6. [Running the Application](#running-the-application)
7. [Skipping Tests](#skipping-tests)
8. [Deploying to Docker](#deploying-to-docker)
9. [Deploying to Kubernetes](#deploying-to-kubernetes)
10. [License](#license)

---

## Project Overview

The **E-Commerce Microservices Platform** is designed to provide a robust, scalable solution for managing products, inventory, pricing, orders, and payments in an e-commerce ecosystem. The platform consists of the following key components:

- **Product Service**
- **Inventory Service**
- **Pricing Service**
- **Order Service**
- **Payment Service**
- **User Service**
- **Notification Service**

Each service is independent, and all services communicate over REST APIs. The project is built with **Spring Boot** and orchestrated using **Docker** and **Kubernetes** for containerization and deployment.

---

## Features

- **Multi-vendor support** for managing products from different vendors.
- **Inventory management** to track stock availability.
- **Dynamic pricing** to adjust product prices based on business rules.
- **Payment gateway integration** (PayPal/Stripe).
- **Microservices-based architecture** using Spring Boot and Spring Cloud.
- **Dockerized services** for easy deployment.
- **Kubernetes** orchestration for scalability and high availability.

---

## Classes and Components

### 1. **EcommerceApplication** (`EcommerceApplication.java`)
This is the main entry point of the Spring Boot application. It initializes all the Spring beans and runs the application.

### 2. **Controllers**
Each microservice (e.g., `ProductController`, `OrderController`) contains REST APIs that allow communication between services and clients. These controllers handle HTTP requests and return appropriate responses.

### 3. **Services**
Services like `ProductService`, `InventoryService`, and `OrderService` define the business logic. They process requests from controllers, interact with repositories to fetch or store data, and handle the flow of the application.

### 4. **Repositories**
These are interfaces that manage data persistence using **Spring Data JPA**. Each service has its own repository (e.g., `ProductRepository`, `OrderRepository`) that interacts with the database.

### 5. **Models**
Model classes such as `Product`, `Order`, `Payment`, `User`, etc., define the entities used across the services. They are used to map data between Java objects and database tables.

### 6. **Configuration Files**
Configuration files like `application.properties` and `bootstrap.yml` define the environment settings, database connections, and other system configurations.

---

## Configuration

### Database Configuration (`application.properties`)

- **MySQL Database**: Each service will connect to its own MySQL instance. Configure the connection details (URL, username, password) in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<db_host>:3306/<db_name>
spring.datasource.username=<db_username>
spring.datasource.password=<db_password>
```

Replace `<db_host>`, `<db_name>`, `<db_username>`, and `<db_password>` with your actual MySQL configuration.

### Docker Configuration
Make sure to replace any hardcoded values like database credentials or service URLs with environment variables or values from your container orchestration environment when deploying to Docker or Kubernetes.

### External Services
You may need to configure third-party services such as **Payment Gateways (Stripe/PayPal)** in the respective services (e.g., `PaymentService`).

---

## Building the Application

To build the application using Maven:

1. Clone the repository:

```bash
git clone https://github.com/orgs/cloudnativeplayground/ecom-microservices.git
cd ecom-microservices
```

2. Build the application:

```bash
./mvnw clean install
```

This will compile the source code, run tests, and package the application into a JAR file located in the `target/` directory.

---

## Running the Application

To run the application locally:

1. Start the application using Maven:

```bash
./mvnw spring-boot:run
```

By default, the application will be available at `http://localhost:8080`.

2. To run it on a specific port or environment, use the following command:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

This will run the application on port `8081`.

---

## Skipping Tests

To build and run the application while skipping tests (useful for faster builds):

```bash
./mvnw clean install -DskipTests
```

This command will skip running the tests and just package the application.

---

## Deploying to Docker

### Build Docker Image

1. Navigate to the root directory where the `Dockerfile` is located.
2. Build the Docker image:

```bash
docker build -t ecom-microservices .
```

### Run Docker Container

Run the application in a Docker container:

```bash
docker run -d -p 8080:8080 --name ecom-microservices ecom-microservices
```

This will start the application in detached mode and expose it on port `8080` of your host machine.

---

## Deploying to Kubernetes

### Prerequisites

1. Ensure you have a Kubernetes cluster running (e.g., on **Minikube**, **Google Kubernetes Engine**, or **Amazon EKS**).
2. Ensure **kubectl** is configured and able to communicate with your Kubernetes cluster.

### Deploying to Kubernetes

1. Create a Kubernetes Deployment YAML file (e.g., `deployment.yml`):

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ecom-microservices
spec:
  replicas: 3
  selector:
    matchLabels:
      app: ecom-microservices
  template:
    metadata:
      labels:
        app: ecom-microservices
    spec:
      containers:
        - name: ecom-microservices
          image: ecom-microservices:latest
          ports:
            - containerPort: 8080
```

2. Apply the deployment file to your Kubernetes cluster:

```bash
kubectl apply -f deployment.yml
```

3. Create a Service YAML file to expose the application (e.g., `service.yml`):

```yaml
apiVersion: v1
kind: Service
metadata:
  name: ecom-microservices-service
spec:
  selector:
    app: ecom-microservices
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```

4. Apply the service file to expose your application:

```bash
kubectl apply -f service.yml
```

Your application should now be running in the Kubernetes cluster and accessible via the exposed LoadBalancer IP or service URL.

---

## License

This open-source project is licensed under the **GNU General Public License v3.0**.

- **Maintainer**: [Aditya Pratap Bhuyan](https://linkedin.com/in/adityabhuyan)

---
Feel free to contribute to the project by submitting a pull request or opening an issue on GitHub. Happy coding!

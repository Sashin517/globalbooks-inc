# 📚 GlobalBooks Inc. | Hybrid SOA & Microservices Architecture

## 🌐 Project Overview

This project is an enterprise-grade **Hybrid Integration System** designed for the GlobalBooks e-commerce platform. It demonstrates the strategic migration of a legacy monolithic application into a distributed architecture, successfully bridging older Service-Oriented Architecture (SOA) components with modern Microservices.

By integrating **Apache ODE (BPEL)** for orchestration and **RabbitMQ** for event-driven choreography, the system coordinates synchronous REST APIs, legacy SOAP contracts, and asynchronous downstream fulfillment without tight coupling.

---

## 🛠️ Built With

* **Java (JAX-WS)** – Core language for the legacy SOAP `CatalogService`.
* **Spring Boot** – Framework for the modern `Orders`, `Payments`, and `Shipping` microservices.
* **Apache ODE (BPEL)** – Workflow engine for central process orchestration.
* **RabbitMQ (AMQP)** – Message broker for asynchronous choreography and event routing.
* **Apache Tomcat** – Web server hosting the legacy service and BPEL engine.
* **Spring Security (OAuth2 / JWT)** – Securing modern REST perimeters.
* **WS-Security (WSS4J)** – Encrypting and securing legacy SOAP envelopes.
* **SoapUI & Postman** – API testing and validation.

---

## 🚀 Key Features

* **✅ Hybrid Orchestration**: A central BPEL workflow (`PlaceOrderProcess`) manages cross-protocol transactions between SOAP and REST boundaries.
* **📈 Event-Driven Choreography**: `OrdersService` publishes events to a RabbitMQ Topic Exchange, decoupling the `Payments` and `Shipping` consumer services.
* **🔒 Dual Security Perimeter**: Implements both legacy WS-Security (UsernameToken) for internal catalog data and stateless OAuth2/JWT for external API clients.
* **⚙️ Resilient Error Handling**: Utilizes RabbitMQ manual acknowledgments, TTL backoff retries, and isolated Dead-Letter Queues (DLQ) to prevent data loss during fulfillment crashes.

## 🛠️ Installation & Setup

### 1️⃣ Prerequisites
Ensure the following are installed and running on your local machine:
* Java JDK 8 or higher (JDK 17 recommended for Spring Boot)
* Apache Tomcat (v9.0+) with Apache ODE 1.3.8 installed in the `webapps` directory
* RabbitMQ Server (running on default port 5672)
* Maven (for building the Java projects)

### 2️⃣ Clone the Repository

```bash
# Clone the repository
git clone [https://github.com/](https://github.com/)<YOUR_USERNAME>/globalbooks-integration.git
cd globalbooks-integration
```

---

## 🔄 Running the Pipeline

### 🟢 1. Start the Messaging Broker
Ensure the RabbitMQ service is active. Access the management dashboard at `http://localhost:15672` to verify the `x-order-events` exchange is ready.

### 🟠 2. Deploy the Legacy Catalog Service
```bash
cd catalog-service
mvn clean package
# Copy the generated .war file to your Tomcat /webapps directory
cp target/CatalogService.war <TOMCAT_HOME>/webapps/
```

### 🔵 3. Deploy the BPEL Orchestration Engine
Package the contents of the `PlaceOrder-BPEL` directory into a `.zip` file.
Place the `.zip` archive directly into `<TOMCAT_HOME>/webapps/ode/WEB-INF/processes/`.
Verify deployment at `http://localhost:8080/ode`.

### 🟣 4. Launch the Spring Boot Microservices
Open separate terminal windows for each modern service:
```bash
# Terminal 1: Start Orders Producer
cd orders-service
mvn spring-boot:run

# Terminal 2: Start Payments Consumer
cd payment-service
mvn spring-boot:run

# Terminal 3: Start Shipping Consumer
cd shipping-service
mvn spring-boot:run
```

---

## 🚢 Testing & Validation

### 💻 API Testing
Testing artifacts are included in the `/tests` directory.
* **SOAP/BPEL:** Import `GlobalBooks-SoapUI-Tests.xml` into SoapUI to execute the WS-Security and BPEL workflow tests.
* **REST:** Import `GlobalBooks-Postman-Tests.json` into Postman to test OAuth2 token generation and the `POST /api/v1/orders` endpoint.

---

## ✅ Project Tasks Status

1. **Legacy SOA Implementation** :heavy_check_mark:
* Java JAX-WS `CatalogService` :heavy_check_mark:
* WS-Security Interceptor Configuration :heavy_check_mark:

2. **Modern Microservices Development** :heavy_check_mark:
* Spring Boot `Orders`, `Payments`, `Shipping` :heavy_check_mark:
* OAuth2 / JWT Authorization :heavy_check_mark:

3. **Integration & Orchestration** :heavy_check_mark:
* Apache ODE BPEL Process Deployment :heavy_check_mark:
* RabbitMQ Async Topic Routing & DLQ :heavy_check_mark:

4. **Governance & Documentation** :heavy_check_mark:
* WSDL, UDDI Metadata, and SLA Policies :heavy_check_mark:

---

## 👥 Author

* 🧑‍💻 **Sashin517** – Integration Architect & Developer

---

## 🤝 Get Involved

Have feedback or ideas for optimizing the BPEL to Spring Boot integration?
* Feel free to open an **Issue** or submit a **Pull Request**.

---

## 📢 Disclaimer

This project was developed as academic coursework for the **CCS3341 SOA & Microservices** module. It is intended for educational demonstration of enterprise integration patterns and should not be deployed directly into a production environment without further security hardening.
```

# 🛒 N-Cart: Java-Based eCommerce Web Application

Welcome to **N-Cart**, a lightweight yet scalable eCommerce web application built using **Java EE**, **JSP**, **Servlets**, and **MySQL**. This platform enables users to browse, purchase, and manage products seamlessly, complete with authentication and a session-managed shopping cart system.

---

## 🔍 Project Overview

This application demonstrates a complete eCommerce workflow:

- Product listing
- User authentication
- Shopping cart functionality
- Session handling
- Database interaction via DAO pattern
- Responsive UI using Bootstrap

---

## 🚀 Technologies Used

| Layer            | Technology                     |
|------------------|--------------------------------|
| Frontend         | HTML, CSS, Bootstrap, JSP      |
| Backend          | Java (Servlets, JSP), JDBC     |
| Database         | MySQL                          |
| Server           | Apache Tomcat (v9+)            |
| Tools            | Eclipse/IntelliJ, Maven        |

---

## 🧱 Project Structure

```bash
N-Cart/
│
├── src/
│   ├── com.ecommerce.connection/DbConnection.java
│   ├── com.ecommerce.dao/ProductDao.java
│   ├── com.ecommerce.model/Products.java
│   ├── com.ecommerce.model/Cart.java
│   └── com.ecommerce.model/Users.java
│
├── WebContent/
│   ├── includes/
│   │   ├── head.jsp
│   │   ├── navbar.jsp
│   │   └── foot.jsp
│   ├── images/          # Product images
│   ├── index.jsp        # Home - displays all products
│   └── WEB-INF/web.xml  # Servlet configuration
│
├── README.md
└── pom.xml (if using Maven)
````

---

## 🧠 Core Functionalities

### 🛍 Product Listing

* All available products are dynamically retrieved from the database using the `ProductDao` class.
* Products are displayed using Bootstrap cards in a responsive grid layout.

### 🧾 Cart Management

* Shopping cart is session-based and stored as `ArrayList<Cart>`.
* Users can add products to the cart and proceed to purchase via `buy-now`.

### 👤 User Authentication

* Authentication object is retrieved from session using:

  ```java
  Users auth = (Users) request.getSession().getAttribute("auth");
  ```
* Authenticated sessions can add to cart, buy products, etc.

### 📦 DAO Pattern

* `ProductDao` handles all product-related DB operations, abstracting SQL logic from JSP.

---

## 🔧 Setup Instructions

### 🛠 Prerequisites

* JDK 8+
* Apache Tomcat 9+
* MySQL Server
* Eclipse/IntelliJ IDEA
* MySQL Workbench (optional)

### ⚙️ Database Setup

1. Create a database:

   ```sql
   CREATE DATABASE ecommerce;
   ```

2. Use the following schema to create `products` and `users` tables:

   ```sql
   CREATE TABLE products (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255),
       category VARCHAR(100),
       price DOUBLE,
       image VARCHAR(255)
   );

   CREATE TABLE users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100),
       password VARCHAR(100)
   );
   ```

3. Insert sample data into `products`.

---

## 🧪 Testing

* Add products to the `products` table.
* Start the Tomcat server.
* Navigate to `http://localhost:8080/N-Cart/`.
* Add items to the cart, try "Buy Now", and ensure session handling.

---

## 🛡 Security Considerations

* Input validation (server-side) should be strengthened.
* Prepared statements should be used to prevent SQL injection (ensure this in DAO layer).
* Session management should be tightened for production environments.

---

## 🌟 Future Enhancements

* 🧾 Order History and Tracking
* 🔐 Spring Security for Authentication
* 🗃 Admin Panel for Product Management
* 📱 Responsive Progressive Web App (PWA) features
* 🧾 Invoice PDF generation
* 💳 Payment Gateway Integration

---

## 🙏 Acknowledgements

Special thanks to open-source libraries, Bootstrap, and the Java EE community for providing a robust ecosystem.

```

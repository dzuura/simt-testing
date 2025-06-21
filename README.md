# 🧪 SIM-TOKO Testing

This project is an automated test system for **SIM-TOKO**, a web-based application used to manage sales, inventory, transaction, customer, supplier, and user data. Testing is conducted using **Cucumber** (BDD) and **Selenium WebDriver** (browser automation), following the **Page Object Model (POM)** design pattern to separate test logic from UI interaction.

---

## 🎯 Project Goals

- Ensure all functional flows of the system behave as expected.
- Detect bugs and input validation errors early.
- Increase system reliability before production deployment.

---

## 📁 Project Directory Structure

```
simt-testing/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       ├── org/
    │       │   └── example/
    │       │       └── Main.java
    │       └── pages/
    │           ├── BasePage.java
    │           ├── CustomerPage.java
    │           ├── IncomingStockPage.java
    │           ├── KatalogPage.java
    │           ├── LoginPage.java
    │           ├── PelangganPage.java
    │           ├── ProfilePage.java
    │           ├── SupplierPage.java
    │           └── UserManagementPage.java
    ├── test/
    │   ├── java/
    │   │   ├── runner/
    │   │   │   └── Runner.java
    │   │   └── stepDef/
    │   │       ├── AddCustomerStepDef.java
    │   │       ├── AddStockStepDef.java
    │   │       ├── AddSupplierStepDef.java
    │   │       ├── AddUserStepDef.java
    │   │       ├── CreateOrderStepDef.java
    │   │       ├── DeleteUserStepDef.java
    │   │       ├── LoginStepDef.java
    │   │       ├── ProfileStepDef.java
    │   │       └── TestContext.java
    │   └── resources/
    │       └── features/
    │           ├── add_customer.feature
    │           ├── add_stock.feature
    │           ├── add_supplier.feature
    │           ├── add_user.feature
    │           ├── create_order.feature
    │           ├── delete_user.feature
    │           ├── edit_profile.feature
    │           └── login.feature
    └── test-suite/
        ├── login.pdf
        ├── add-user.pdf
        ├── add-customer.pdf
        ├── add-supplier.pdf
        ├── add-incoming-stock.pdf
        ├── delete-supplier.pdf
        ├── edit-profile.pdf
        └── add-order.pdf


```

---

## ✅ Test Suites

| Test Suite             | Description                                         |
| ---------------------- | --------------------------------------------------- |
| `login.feature`        | Login tests with valid and invalid credentials      |
| `add_user.feature`     | Add user, validate required fields and email format |
| `add_stock.feature`    | Add incoming stock and check quantity validation    |
| `add_customer.feature` | Add customer and validate required field inputs     |
| `add_supplier.feature` | Add supplier and validate required fields           |
| `create_order.feature` | Add items to cart and complete order                |
| `delete_user.feature`  | Delete customer data with confirmation popup        |
| `edit_profile.feature` | Edit user profile and validate form inputs          |

---

## 📘 Report

HTML reports will be generated automatically at:

```
target/cucumber-reports.html
```

---

## 👥 Team Contributions

| Team Member | Role               | Responsibilities                                                                |
| ----------- | ------------------ | ------------------------------------------------------------------------------- |
| Daffa       | Back-end Engineer  | Framework setup (with BasePage dan TestContext), login, add user, and reporting |
| Tegar       | PM                 | Qase setup (with default test-suite) and add supplier                           |
| Assifa      | Front-end Engineer | Add incoming stock and add customer                                             |
| Fayyadh     | UI/UX              | Create order, Delete user customer, and edit profile                            |

---

## 🔧 Tools & Frameworks

- Java 22
- Maven
- Selenium WebDriver
- Cucumber (Gherkin + JUnit)
- Cucumber HTML Reporting Plugin

---

## 📌 Notes

- Ensure the server is running at `https://sim-toko.madanateknologi.web.id` before test execution.
- Tests should be run on an up-to-date Chrome or another Web browser.

# Library API Automation Framework

A robust, scalable API automation framework built using **Java**, **RestAssured**, and **TestNG**. This project demonstrates a layered architecture designed for maintainability and environment flexibility.



## 🏗 Architecture Features

- **Centralized Configuration**: All environment-specific variables (Base URI, Endpoints) are managed via a `config.properties` file and a `ConfigReader` utility.
- **Service Object Model (SOM)**: API logic is encapsulated within the `endpoints` package, separating the "How to call the API" from the "What to test."
- **Generic Abstraction Layer**: Utilizes a custom `ApiResponse<T>` wrapper to handle status codes and body parsing consistently across all tests.
- **Type Safety**: Fully POJO-based request and response mapping using **Jackson** and **Lombok**.

## 📂 Project Structure

* **`aquashoalstudio.endpoints`**: Service Layer containing `LibraryAPI`.
* **`aquashoalstudio.models`**: Data models for JSON serialization.
* **`aquashoalstudio.utils`**: Framework utilities (`ConfigReader`, `ApiResponse`).
* **`tests`**: TestNG suites inheriting from `BaseTest`.

## 🚀 Execution

To run the full test suite, navigate to the project root in your terminal and execute:

```bash
mvn clean test
# Library API Automation Framework

A robust, scalable API automation framework built using **Java**, **RestAssured**, and **TestNG**.

## 🏗 Architecture Features
- **Abstraction Layer**: Uses an `ApiResponse<T>` wrapper to decouple the test layer from the HTTP client logic.
- **Resilience Strategy**: Implemented `@JsonAlias` in models to handle casing inconsistencies (e.g., `Isbn` vs `isbn`) found in the backend responses.
- **Type Safety**: Fully POJO-based (Plain Old Java Objects) request and response mapping using **Jackson** and **Lombok**.
- **Efficiency**: Utilizes `RequestSpecification` to centralize configurations like Base URI and Headers.

## 🚀 How to Run
1. Ensure you have **Maven** and **JDK 21** installed.
2. Clone the repository.
3. Run the following command:
   ```bash
   mvn clean test
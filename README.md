# Library API Automation Framework

A robust, scalable API automation framework built using **Java**, **Rest-Assured**, and **TestNG**. This project demonstrates a layered architecture designed for maintainability and resilience against real-world API inconsistencies.

## 🏗 Architecture Features

- **Centralized Configuration**: All environment-specific variables (Base URI, Endpoints) are managed via `config.properties` and a `ConfigReader` utility.
- **Service Object Model (SOM)**: API logic is encapsulated within the `endpoints` package, separating transport logic from test assertions.
- **Generic Abstraction Layer**: Utilizes a custom `ApiResponse<T>` wrapper to handle HTTP status codes and body parsing consistently.
- **Defensive Deserialization**: Implements custom safety checks to handle API "type-switching" (e.g., when the API returns a JSON Object instead of a List during error states).



## 📂 Project Structure

* **`aquashoalstudio.endpoints`**: Service Layer containing the `LibraryAPI` client.
* **`aquashoalstudio.models`**: POJO models using **Lombok** for clean data handling and **Jackson** for JSON mapping.
* **`aquashoalstudio.utils`**: Framework utilities including `ConfigReader` and the `ApiResponse` wrapper.
* **`tests`**: TestNG suites focusing on functional validation and edge-case handling.

## 💡 Key Design Decisions

### 1. Inconsistency Mapping
The Library API presents inconsistent casing for success messages (e.g., `"Msg"` in POST vs `"msg"` in DELETE). I utilized Jackson's `@JsonAlias({"Msg", "msg"})` to ensure the response body is always mapped correctly, preventing `NullPointerExceptions`.

### 2. Unified Data Models
To follow **DRY (Don't Repeat Yourself)** principles, I implemented a single `BookResponse` model for all search endpoints. This model handles multiple JSON key variations (e.g., `Name` vs `book_name`) using `@JsonAlias`.

### 3. Build Reproducibility
The project includes the **Maven Wrapper (`mvnw`)**. This allows the suite to be executed in any environment without requiring a pre-installed global version of Maven.

## 🚀 Execution

To run the full test suite on macOS or Linux, execute the following commands in your terminal:

```bash
# 1. Grant execution permissions to the wrapper
chmod +x mvnw

# 2. Run the tests
./mvnw clean test
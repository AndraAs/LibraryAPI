# Test Plan: Bexio Invoices API

This document outlines the high-level test approach, scenarios, and automation strategy for the Bexio Invoices API.

---

## 1. High-Level Test Approach
I will prioritize core business logic, such as invoice creation, and data integrity regarding total and currency rules.

* **Automated Testing (80%)**: Focused on "Happy Path", Negative validation, and Regression using the Java/Rest-Assured framework.
* **Manual Testing (20%)**: Focused on exploratory testing, verifying error message clarity, and initial contract validation. This includes checking for `total_rounding_difference` during currency conversions.
* **Prioritization**:
    * **Level 1 (P0)**: Functional Success (CRUD operations).
    * **Level 2 (P1)**: Business Rule Validation (Totals > 0, Unique contact_id, and Tax/Currency calculation logic).
    * **Level 3 (P2)**: Edge Cases (Invalid search criteria, pagination boundaries, and polymorphic position types).

---

## 2. Key Test Scenarios

| Area | Scenario Type | Input Data | Expected Result |
| :--- | :--- | :--- | :--- |
| **Create** | Default Logic | `contact_id` omitted | 201 Created; Verify `contact_id` defaults to 12345. |
| **Create** | **Constraint** | **Existing `contact_id`** | **422 Unprocessable Entity or 409 Conflict; Verify uniqueness is enforced.** |
| **Create** | Negative | `total = 0` or `-1` | 422 Unprocessable Entity. |
| **Create** | Data Integrity | Invalid email format | 422/Error: "Invalid email format". |
| **Create** | Tax Logic | `mwst_type: 0`, `mwst_is_net: true` | Verify taxes are added on top of the net total. |
| **Retrieve** | Functional | Valid `invoice_id` | 200 OK; All fields match original input precisely. |
| **Retrieve** | Negative | `invoice_id` does not exist | 404 Not Found error response returned. |
| **Update** | Business Rule | Change currency only | 200 OK; numeric total remains constant. |
| **Update** | Business Rule | Change total only | 200 OK; `currency_id` remains constant. |
| **Update** | Partial Update | Send only `title` | 200 OK; Only title changes; other fields persist. |
| **Update** | Method Check | POST to `/kb_invoice/{id}` | 200 OK; Verify API handles POST for edits. |
| **Search** | Filter Logic | `field: "total"`, `criteria: ">"`, `value: "100"` | 200 OK; Filtered list returned. |
| **Fetch List** | Pagination | `limit=10`, `offset=10` | 200 OK; Returns second page of results. |
| **Fetch List** | Boundary Test | `limit=2000` | GET /invoices?limit=2000 (The max allowed). |
| **Fetch List** | Edge Case | `limit=0` | Does it return an error or an empty list?. |
| **Delete** | Sequence | Create Invoice -> Delete ID | 200/204 OK; Success body returned. |
| **Delete** | Integrity | GET deleted ID | 404 Not Found; Verify deletion is permanent. |

---

## 3. Tooling & Automation
I will utilize the existing Java/Rest-Assured/TestNG framework.

* **Service Object Model**: An `InvoicesAPI` class to encapsulate endpoint logic.
* **Data Models**: POJOs for `InvoiceRequest` and `InvoiceResponse` using Jackson for serialization.
* **Polymorphic Type Handling**: A strategy to support Article, Text, and Discount position objects within the same array.
* **State Verification**: Automation will chain requests (e.g., CREATE -> DELETE -> GET) to ensure system state.

---

## 4. Questions & Assumptions

* **The contact_id**: The requirement says `contact_id` must be unique but defaults to 12345. Question: If two invoices are created without an ID, will the second fail?
* **Total vs. Currency**: Requirements state changing currency shouldn't affect the total and vice versa. Question: Does the API handle internal value differences, or is the numeric value locked?
* **Email Validation**: Does validation check strictly for @ or other validations?
* **Search Logical Operator**: Are the multiple search criteria linked by an AND relationship.
* **Tax/Total Dependency**: Documentation mentions `mwst_is_net` affects total if `mwst_type` is 0. Does the API perform these calculations server-side.
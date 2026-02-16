package tests;

import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void globalSetup() {
        // Check if a System property exists (for CI/CD)
        // usage: mvn test -Denv=staging
        String env = System.getProperty("env", "local");
        System.out.println("--- Running tests in " + env + " environment ---");
    }
}
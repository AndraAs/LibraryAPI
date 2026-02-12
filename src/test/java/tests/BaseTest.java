package tests;

import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void globalSetup() {
        // High-level design: You could load environment variables or
        // properties files here in the future.
        System.out.println("--- Starting Library API Test Suite ---");
    }
}
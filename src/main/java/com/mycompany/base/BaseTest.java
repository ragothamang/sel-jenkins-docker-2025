package com.mycompany.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setUp() {
        // Use ExtentSparkReporter instead of ExtentHtmlReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Setup WebDriver using WebDriverManager
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
        
        FirefoxOptions options = new FirefoxOptions();
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to connect to Selenium Grid", e);
        }
        
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        // Quit WebDriver and flush the report
        if (driver != null) {
//            driver.quit();
        }
        extent.flush();
    }
}


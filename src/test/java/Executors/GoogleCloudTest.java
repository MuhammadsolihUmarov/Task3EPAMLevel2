package Executors;

import Pages.GoogleCloudCalculatorPage;
import Pages.GoogleCloudMainPage;
import Pages.yopmailPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;

public class GoogleCloudTest {
    private WebDriver driver;
    private GoogleCloudMainPage mainPage;
    private GoogleCloudCalculatorPage calculatorPage;
    private yopmailPage emailPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        this.mainPage = new GoogleCloudMainPage(driver);
        this.calculatorPage = new GoogleCloudCalculatorPage(driver);
        this.emailPage = new yopmailPage(driver);
    }

    @Test
    public void testGoogleCloud() throws InterruptedException {
        mainPage.openPage();
        mainPage.searchText("Google Cloud Platform Pricing Calculator");
        mainPage.chooseSearchResult();
        mainPage.addToEstimate();
        mainPage.addComputeEngine();
        calculatorPage.chooseNumberOfInstances();
        calculatorPage.chooseMachineType();
        calculatorPage.addGpus();
        calculatorPage.addLocalSsd();
        calculatorPage.chooseLocation();
        calculatorPage.chooseDiscount();
        calculatorPage.shareCalc();

        // Handle new tab and perform email operations
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        driver.get("https://yopmail.com/ru/");
        emailPage.newEmail();
        emailPage.newMessage();
        emailPage.copyMessage();

    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}

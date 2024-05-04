package Tests;

import Pages.GoogleCloudCalculatorPage;
import Pages.GoogleCloudMainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import Utils.ComputeInstanceConfig;
import Utils.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class GoogleCloudTest {
    private WebDriver driver;
    private GoogleCloudMainPage mainPage;
    private GoogleCloudCalculatorPage calculatorPage;
    private ComputeInstanceConfig config = new ComputeInstanceConfig("4", "n1-standard-8, vCPUs: 8, RAM: 30 GB",
            "1", "2x375 GB", "1 year");

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("124").setup();
        ChromeOptions options = new ChromeOptions();
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        this.driver.get(ConfigManager.getProperty("url"));
        this.mainPage = new GoogleCloudMainPage(driver);
        this.calculatorPage = new GoogleCloudCalculatorPage(driver);
    }

    @Test
    public void testGoogleCloud() {
        mainPage.openPage();
        mainPage.searchText("Google Cloud Platform Pricing Calculator");
        mainPage.chooseSearchResult();
        mainPage.addToEstimate();
        mainPage.addComputeEngine();
        calculatorPage.configureGoogleCloudCalculator(config);
        calculatorPage.shareCalc();
        calculatorPage.openEstimatedSummary();

        // Working with the new window
        Set<String> allWindows = driver.getWindowHandles();
        String originalWindow = driver.getWindowHandle();
        String newWindow = null;
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                newWindow = windowHandle;
                break;
            }
        }
        // Switch to the new window
        driver.switchTo().window(newWindow);
        calculatorPage.assertAllEnteredInfo();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            if (driver != null) {
                takeScreenshot(description.getMethodName());
            } else {
                System.out.println("Driver is null, cannot take screenshot");
            }
        }

        private void takeScreenshot(String methodName) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                String currentPath = System.getProperty("user.dir");
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                Files.copy(scrFile.toPath(), Paths.get(currentPath + "/screenshots/" + methodName + timestamp + ".png"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    };
}

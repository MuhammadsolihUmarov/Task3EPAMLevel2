package Executors;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GoogleCloudPricingTest {

    public static void main(String[] args) {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        PricingCalculatorPage pricingCalculatorPage = new PricingCalculatorPage(driver);
        EmailServicePage emailServicePage = new EmailServicePage(driver);

        try {
            homePage.navigateToHomePage();
            homePage.openSearch();
            homePage.enterSearchTerm("Google Cloud Platform Pricing Calculator");
            homePage.performSearch();
            searchResultsPage.selectResult("Google Cloud Platform Pricing Calculator");
            pricingCalculatorPage.selectComputeEngine();
            pricingCalculatorPage.fillForm();
            pricingCalculatorPage.addToEstimate();
           // pricingCalculatorPage.emailEstimate();
            emailServicePage.navigateToEmailService();
            String email = emailServicePage.generateAndGetEmail();
           /* pricingCalculatorPage.enterEmail(email);
            pricingCalculatorPage.sendEmail();*/
        } finally {
            //driver.quit();
        }
    }

    static class BasePage {
        protected WebDriver driver;
        protected WebDriverWait wait;

        public BasePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
    }

    static class HomePage extends BasePage {
        public HomePage(WebDriver driver) {
            super(driver);
        }

        public void navigateToHomePage() {
            driver.get("https://cloud.google.com/");
        }

        public void openSearch() {
            driver.findElement(By.xpath("//div[@jsname='Ohx1pb']")).click();
        }

        public void enterSearchTerm(String term) {
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@jsname='YPqjbf']")));
            searchBox.sendKeys(term);
        }

        public void performSearch() {
            driver.findElement(By.xpath("//i[@jsname=\"Z5wyCf\" and text()=\"send_spark\"]")).click();
        }
    }

    static class SearchResultsPage extends BasePage {
        public SearchResultsPage(WebDriver driver) {
            super(driver);
        }

        public void selectResult(String resultTitle) {
            WebElement resultLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href=\"https://cloud.google.com/products/calculator\"]")));
            resultLink.click();
        }
    }

    static class PricingCalculatorPage extends BasePage {
        public PricingCalculatorPage(WebDriver driver) {
            super(driver);
        }

        public void selectComputeEngine() {
            driver.findElement(By.xpath("//button[@jscontroller='O626Fe' and @class='AeBiU-LgbsSe AeBiU-LgbsSe-OWXEXe-Bz112c-M1Soyc AeBiU-LgbsSe-OWXEXe-dgl2Hf VVEJ3d']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[text()='Compute Engine']"))).click();
        }

        public void fillForm() {
            driver.findElement(By.xpath("//input[@min='0' and @max = '50000']")).click();

            driver.findElement(By.id("c22")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-value=\"free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license\"]"))).click();

            driver.findElement(By.id("c24")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"General Purpose\" and @jsname='K4r5Ff']"))).click();

            driver.findElement(By.id("c28")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"N1\" and @jsname='K4r5Ff']"))).click();

            driver.findElement(By.id("c32")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"n1-standard-8\" and @jsname='K4r5Ff']"))).click();


            driver.findElement(By.xpath("//button[@aria-label='Add GPUs']")).click();
            driver.findElement(By.id("#c11093")).click();
            driver.findElement(By.xpath("//span[@jsname=\"K4r5Ff\" and text()='NVIDIA Tesla V100']")).click();

            driver.findElement(By.id("c11097")).click();
            driver.findElement(By.xpath("//span[@jsname='K4r5Ff' and text()='1']"));

            driver.findElement(By.id("c40")).click();
            driver.findElement(By.xpath("//span[@jsname='K4r5Ff' and text()='2x375 GB']")).click();

            driver.findElement(By.xpath("//span[text()='Region']")).click();
            driver.findElement(By.xpath("//span[text()='Netherlands (europe-west4)' and @jsname='K4r5Ff']")).click();

            driver.findElement(By.id("1-year")).click();
        }

        public void addToEstimate() {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add to estimate']"))).click();
            //With assertion
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='$8,935.06']"))).getText();
        }

        /*public void emailEstimate() {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Email Estimate')]"))).click();
        }

        public void enterEmail(String email) {
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
            emailInput.sendKeys(email);
        }

        public void sendEmail() {
            driver.findElement(By.xpath("//button[contains(.,'Send Email')]")).click();
        }*/
    }

    static class EmailServicePage extends BasePage {
        public EmailServicePage(WebDriver driver) {
            super(driver);
        }

        public void navigateToEmailService() {
            driver.get("https://yopmail.com/");
        }

        public String generateAndGetEmail() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()=\"Random Email generator\"]"))).click();

            String email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("geny"))).getText();

            return email;
        }
    }
}

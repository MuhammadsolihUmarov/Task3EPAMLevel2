package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudCalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By numberOfInstances = By.xpath("//input[@id='c11']");
    private final By machineTypeDropdown = By.xpath("//span[@id='c33']/parent::*/parent::*");
    private final By machineTypeChooseOption = By.xpath("//li[@data-value='n1-standard-8']");
    private final By gpusAddButton = By.xpath("//button[@aria-label='Add GPUs']//span[@class='eBlXUe-hywKDc']");
    private final By gpusTypeDropDown = By.xpath("//div[@data-field-type='158']");
    private final By gpusOption = By.xpath("//li[@data-value='nvidia-tesla-v100']");
    private final By localSsdDropDown = By.xpath("//div[@data-field-type='180']");
    private final By localSsdOption = By.xpath("//span[text()='2x375 GB']/../..");
    private final By locationDropDown = By.xpath("//div[@data-field-type='115']");
    private final By locationOption = By.xpath("//li[@data-value='europe-west4']");
    private final By discountButton = By.xpath("//label[text()='1 year']");
    private final By totalPrice = By.xpath("//span[text()='$5,628.90']");
    private final By shareButton = By.xpath("//span[text()='Share']/..");
    private final By copyLinkButton = By.xpath("//button[@track-name='copy link']");

    public GoogleCloudCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void chooseNumberOfInstances() {
        driver.findElement(numberOfInstances).clear();
        driver.findElement(numberOfInstances).sendKeys("4");
    }

    public void chooseMachineType() {
        wait.until(ExpectedConditions.elementToBeClickable(machineTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(machineTypeChooseOption)).click();
    }

    public void addGpus() {
        wait.until(ExpectedConditions.elementToBeClickable(gpusAddButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(gpusTypeDropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(gpusOption)).click();
    }

    public void addLocalSsd() {
        wait.until(ExpectedConditions.elementToBeClickable(localSsdDropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(localSsdOption)).click();
    }

    public void chooseLocation() {
        wait.until(ExpectedConditions.elementToBeClickable(locationDropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(locationOption)).click();
    }

    public void chooseDiscount() {
        wait.until(ExpectedConditions.elementToBeClickable(discountButton)).click();
    }

    public void shareCalc() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(totalPrice));
        driver.findElement(shareButton).click();
        driver.findElement(copyLinkButton).click();
    }
}

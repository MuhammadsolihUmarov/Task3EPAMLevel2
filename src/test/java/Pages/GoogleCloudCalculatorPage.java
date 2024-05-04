package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ComputeInstanceConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class GoogleCloudCalculatorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public GoogleCloudCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

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
    private final By shareButton = By.xpath("//span[text()='Share']");
    private final By estimatedSummaryButton = By.xpath("//a[text()='Open estimate summary']");
    private final By instanceNumber =  By.xpath("//span[text() = 'Number of Instances']/following-sibling::span[1]");
    private final By costElement = By.xpath("//h6[text() = 'Instances (Compute Engine)']/following-sibling::p[1]");
    private final By operatingSystemsElement = By.xpath("//span[text()='Operating System / Software']/following-sibling::span[1]");
    private final By provisioningModel = By.xpath("//span[text()='Provisioning Model']/following-sibling::span[1]");
    private final By machineTypeElement = By.xpath("//span[text() = 'Machine type']/following-sibling::span[1]");
    private final By gpuExistElement = By.xpath("//span[text() = 'Add GPUs']/following-sibling::span[1]");
    private final By gpuModelElement = By.xpath("//span[text() = 'GPU Model']/following-sibling::span[1]");
    private final By numberOfGPUs = By.xpath("//span[text()='Number of GPUs']/following-sibling::span[1]");
    private final By localSsdElement = By.xpath("//span[text()='Local SSD']/following-sibling::span[1]");
    private final By committedUsageElement = By.xpath("//span[text()='Committed use discount options']/following-sibling::span[1]");
    public void configureGoogleCloudCalculator(ComputeInstanceConfig config) {
        chooseNumberOfInstances(config.getNumberOfInstances());
        chooseMachineType(config.getMachineType());
        addGpus(Integer.parseInt(config.getNumberOfGPUs()));
        addLocalSsd();
        chooseLocation();
        chooseDiscount();
    }

    public void chooseNumberOfInstances(String numberOfInstances) {
        wait.until(ExpectedConditions.elementToBeClickable(this.numberOfInstances)).clear();
        driver.findElement(this.numberOfInstances).sendKeys(numberOfInstances);
    }

    public void chooseMachineType(String machineType) {
        wait.until(ExpectedConditions.elementToBeClickable(machineTypeDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(machineTypeChooseOption)).click();
    }

    public void addGpus(int numberOfGPUS) {
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
        wait.until(ExpectedConditions.elementToBeClickable(shareButton)).click();
    }

    public void openEstimatedSummary() {
        wait.until(ExpectedConditions.elementToBeClickable(estimatedSummaryButton)).click();
    }

    public void assertAllEnteredInfo()
    {
        //Total cost
        String cost = wait.until(ExpectedConditions.elementToBeClickable(costElement)).getText();
        assertEquals("$5,628.90", cost);

        // Number of Instances
        String instanceNumberText = driver.findElement(instanceNumber).getText();
        Assert.assertEquals("4", instanceNumberText);

        // Operating System / Software
        String operatingSystemText = driver.findElement(operatingSystemsElement).getText();
        Assert.assertEquals("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)", operatingSystemText);

        // Provisioning Model
        String provisioningModelText = driver.findElement(provisioningModel).getText();
        Assert.assertEquals("Regular", provisioningModelText);

        // Machine Type
        String machineTypeText = driver.findElement(machineTypeElement).getText();
        Assert.assertEquals("n1-standard-8, vCPUs: 8, RAM: 30 GB", machineTypeText);

        // Add GPUs
        String gpuExistText = driver.findElement(gpuExistElement).getText();
        Assert.assertEquals("true", gpuExistText);

        // GPU Model
        String gpuModelText = driver.findElement(gpuModelElement).getText();
        Assert.assertEquals("NVIDIA Tesla V100", gpuModelText);

        // Number of GPUs
        String numberOfGPUsText = driver.findElement(numberOfGPUs).getText();
        Assert.assertEquals("1", numberOfGPUsText);

        // Local SSD
        String localSsdText = driver.findElement(localSsdElement).getText();
        Assert.assertEquals("2x375 GB", localSsdText);

        // Committed Use Discount Options
        String committedUsageText = driver.findElement(committedUsageElement).getText();
        Assert.assertEquals("1 year", committedUsageText);
    }
}

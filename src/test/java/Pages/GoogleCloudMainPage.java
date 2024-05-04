package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class GoogleCloudMainPage {
    private WebDriver driver;
    private final By searchButton = By.xpath("//div[@class='ND91id LLv0lb']");
    private final By searchField = By.xpath("//input[@id='i4']");
    private final By sendSearchTextButton = By.xpath("//i[@class='google-material-icons PETVs PETVs-OWXEXe-UbuQg']");
    private final By searchResultLink = By.xpath("//a[@href='https://cloud.google.com/products/calculator']");
    private final By pricingButton = By.xpath("//span[text() = 'Add to estimate']");
    private final By computeEngineButton = By.xpath("//h2[text()='Compute Engine']");



    public GoogleCloudMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://cloud.google.com/");
    }

    public void searchText(String text) {
        driver.findElement(searchButton).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(sendSearchTextButton).click();
    }

    public void chooseSearchResult() {
        driver.findElement(searchResultLink).click();
    }

    public void addToEstimate() {
        driver.findElement(pricingButton).click();
    }

    public void addComputeEngine() {
        driver.findElement(computeEngineButton).click();
    }


}

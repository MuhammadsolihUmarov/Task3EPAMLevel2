package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class yopmailPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String email;
    private final By newEmailButton = By.xpath("//h3[text()='Случайный адрес электронной почты']");
    private final By checkEmail = By.xpath("//span[text()='Проверить почту']");
    private final By newMessageButton = By.id("newmail");
    private final By messageTextField = By.id("msgbody");
    private final By emailText = By.cssSelector("#autoaltcpt em");

    public yopmailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void newEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(newEmailButton)).click();
    }

    public void newMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(checkEmail)).click();
        wait.until(ExpectedConditions.elementToBeClickable(newMessageButton)).click();
        driver.switchTo().frame("ifmail");
        driver.findElement(messageTextField).click();
        driver.findElement(messageTextField).sendKeys("Hello, I do not know what can I do. The requirements are expired!");
        driver.switchTo().defaultContent();
    }
    public void copyMessage() {
        email = driver.findElement(emailText).getText();
    }
    //We can do something with the copied email here
}

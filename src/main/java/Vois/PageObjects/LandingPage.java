package Vois.PageObjects;

import Vois.AbstractComponants.AbstractComponants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponants {

    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")
    WebElement UserEmail;

    @FindBy(id = "userPassword")
    WebElement Password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "div[aria-label='Incorrect email or password.']")
    WebElement ErrorMessage;

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage()
    {
        WaitForWebElementToApear(ErrorMessage);
        return ErrorMessage.getText();
    }
    public ProductCatalogue LoginApplication(String email, String password)
    {
        UserEmail.sendKeys(email);
        Password.sendKeys(password);
        submit.click();
        return new ProductCatalogue(driver);
    }
}

package Vois.PageObjects;

import Vois.AbstractComponants.AbstractComponants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.By.cssSelector;

public class CheckoutPage extends AbstractComponants {

    WebDriver driver;
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement SelectCounteryBox;

    @FindBy(xpath = "//span[text()=' India']")
    WebElement Countery;

    @FindBy(css =".btnn")
    WebElement PlaceOrderBottunn;

    By CounteryIndex = By.cssSelector(".ta-results");

    public void SelectCountry(String CounterName)
    {
        SelectCounteryBox.sendKeys(CounterName);
        WaitForElementToApear(CounteryIndex);
        Countery.click();

    }

    public ConfirmationPage goToPlaceOrder()
    {
        PlaceOrderBottunn.click();
        return new ConfirmationPage(driver);
    }

}

package Vois.AbstractComponants;

import Vois.PageObjects.CartPage;
import Vois.PageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//this is for CICD
public class AbstractComponants
{
     WebDriver driver;

    public AbstractComponants(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//button[@routerlink='/dashboard/cart'])[1]")
    WebElement CartHeader;

    @FindBy(css ="button[routerlink='/dashboard/myorders']" )
    WebElement OrderHeader;


    public void WaitForElementToApear(By findby)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void WaitForWebElementToApear(WebElement findby)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findby));
    }

    public void WaitForElementToDisapear(By findby)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findby));
    }

    public CartPage goToCartPage()
    {
        CartHeader.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrderPage()
    {
        OrderHeader.click();
        return new OrderPage(driver);
    }
}

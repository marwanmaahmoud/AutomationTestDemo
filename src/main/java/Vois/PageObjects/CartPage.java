package Vois.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> CartProducts;

    @FindBy(xpath = "//button[normalize-space()='Checkout']")
    WebElement CheckoutBottun;


    public boolean isProductDisplayed(String ProductName)
    {
        boolean match = CartProducts.stream().anyMatch(CartProduct-> CartProduct.getText().equalsIgnoreCase(ProductName));
        return match;
    }

    public CheckoutPage goToCheckout()
    {
        CheckoutBottun.click();
        return new CheckoutPage(driver);
    }



}

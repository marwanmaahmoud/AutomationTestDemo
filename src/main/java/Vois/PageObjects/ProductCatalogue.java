package Vois.PageObjects;


import Vois.AbstractComponants.AbstractComponants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponants {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> Products;

    By productby =(By.cssSelector(".mb-3"));
    By AddToCart = By.xpath(".//div[@class='card-body']/button[2]");
    By toastmessage = By.id("toast-container");
    By Disappearby = By.cssSelector(".ngx-spinner-overlay ng-tns-c31-3 ng-trigger ng-trigger-fadeIn ng-star-inserted");


    public List<WebElement> getProductList() {
        WaitForElementToApear(productby);
        return Products;
    }

    public WebElement GetProductName(String ProductName)
    {
        WebElement product = getProductList().stream().filter(Product->
                Product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
        return product;

    }

    public void AddProductToCart(String ProductName)
    {
       WebElement product = GetProductName(ProductName);
       product.findElement(AddToCart).click();
       WaitForElementToApear(toastmessage);
       WaitForElementToDisapear(Disappearby);
    }





}


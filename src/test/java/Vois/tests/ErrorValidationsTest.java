package Vois.tests;

import Vois.PageObjects.CartPage;
import Vois.PageObjects.ProductCatalogue;
import Vois.TestComponants.BaseTest;
import Vois.TestComponants.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidationsTest extends BaseTest {

        @Test(retryAnalyzer = Retry.class)
        public void LoginErrorValidation() {

                LandingPage.LoginApplication("marwan@gmail.com", "Mmh1+2=33");
                Assert.assertEquals(LandingPage.getErrorMessage(), "Incorrect email or password.");
        }

        @Test
        public void ProductErrorValidation() {

                String ProductName = "ZARA COAT 3";
                ProductCatalogue productCatalogue =LandingPage.LoginApplication("marwan@gmail.com", "Mmh1+2=3");
                List<WebElement> products = productCatalogue.getProductList();
                productCatalogue.AddProductToCart(ProductName);
                CartPage CartPage = productCatalogue.goToCartPage();
                boolean match = CartPage.isProductDisplayed("ZARA COAT 33");
                Assert.assertFalse(match);
        }
}

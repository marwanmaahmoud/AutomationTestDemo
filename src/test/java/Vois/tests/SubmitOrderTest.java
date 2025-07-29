package Vois.tests;

import Vois.PageObjects.*;
import Vois.TestComponants.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {


    String CounteryName = "india";
    String OriginalText = "Thankyou for the order.";

    @Test(dataProvider ="getData",groups = ("Purchase"))
    public void SubmitOrder(HashMap<String,String> input) throws IOException {

        ProductCatalogue productCatalogue =LandingPage.LoginApplication(input.get("email"),input.get("password"));
        productCatalogue.getProductList();
        productCatalogue.AddProductToCart(input.get("ProductName"));

        CartPage CartPage = productCatalogue.goToCartPage();
        boolean match = CartPage.isProductDisplayed(input.get("ProductName"));
        Assert.assertTrue(match);

        CheckoutPage CheckoutPage = CartPage.goToCheckout();
        CheckoutPage.SelectCountry(CounteryName);

        ConfirmationPage ConfirmationPage = CheckoutPage.goToPlaceOrder();
        String ConfirmationText = ConfirmationPage.getConfirmationText();
        Assert.assertTrue(ConfirmationText.equalsIgnoreCase(OriginalText));
    }

    @Test(dependsOnMethods = "SubmitOrder" )
    public void OrderHistoryTest()
    {
        String ProductName = "ZARA COAT 3";
        ProductCatalogue productCatalogue =LandingPage.LoginApplication("marwan@gmail.com", "Mmh1+2=3");
        OrderPage OrderNames = productCatalogue.goToOrderPage();
        Assert.assertTrue(OrderNames.VerifyProductDisplayed(ProductName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = GetJasonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\Vois\\Resources\\SubmitOrderData.json");
       return new Object[][] {{data.get(0)},{data.get(1)}};
    }


}
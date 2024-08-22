package qatest.Tests;

import java.io.IOException;import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import qatest.TestComponents.BaseTest;
import qatest.pageobjects.CartPage;
import qatest.pageobjects.CheckoutPage;
import qatest.pageobjects.ConfirmationPage;
import qatest.pageobjects.LandingPage;
import qatest.pageobjects.OrderPage;
import qatest.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest { 
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public  void submitOrder(HashMap<String,String> input) throws IOException
	{
	
	ProductCatalogue productcatalogue=landingpage.LoginApplication(input.get("email"),input.get("password"));
     //ProductCatalogue productcatalogue=new ProductCatalogue(driver);
     List<WebElement> products=productcatalogue.getProductList();
     productcatalogue.addProductToCart(input.get("product"));
     CartPage cartpage= productcatalogue.goToCartPage();
     Boolean match=cartpage.VerifyProductDisplay(productName);
    Assert.assertTrue(match);
    CheckoutPage checkoutPage = cartpage.goToCheckout();
    checkoutPage.selectCountry("india");
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	String confirmMessage = confirmationPage.getConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
    
	
	
	}
	
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
	ProductCatalogue productcatalogue=landingpage.LoginApplication("hinaya33@gmail.com","Shivu2020");
		OrderPage orderspage=productcatalogue.goToOrderPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
	}
	

 @DataProvider
public Object[][] getData() throws IOException
{
	 List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//qatest//data//PurchaseOrder.json,StandardCharsets.UTF_8");
	 return new Object[][] {{data.get(0)},{data.get(1)}};
	/* HashMap<String,String> map = new HashMap<String,String>();
	 map.put("email", "anshika@gmail.com");
	 map.put("password", "Iamking@000");
	 map.put("product", "ZARA COAT 3");
	
	 HashMap<String,String> map1 = new HashMap<String,String>();
	 map1.put("email", "shetty@gmail.com");
	 map1.put("password", "Iamking@000");
	 map1.put("product", "ADIDAS ORIGINAL");
	// return new Object[][] {{map},{map1}};	
   // return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
	 */  
}


	
}

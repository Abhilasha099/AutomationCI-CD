package qatest.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import qatest.TestComponents.BaseTest;
import qatest.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {
	
	@Test
	public  void submitOrder() throws IOException
	{
	String productName="ZARA COAT 3";
	landingpage.LoginApplication("hinaya33@gmail.com","Shivu2020");
	Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

}
	
}

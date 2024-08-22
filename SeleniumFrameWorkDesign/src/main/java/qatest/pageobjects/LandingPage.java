package qatest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qatest.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver; 
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		//intialization
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	//Page Favtory
	@FindBy(id="userEmail")
	WebElement userEmail;
	//driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement userPassword;
	//login
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue LoginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
return new ProductCatalogue(driver);

	}
	
	public String getErrorMessage()
	{
		waitforWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	

}

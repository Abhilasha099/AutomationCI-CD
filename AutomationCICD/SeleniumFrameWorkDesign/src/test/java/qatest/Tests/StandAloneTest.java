package qatest.Tests;

import java.time.Duration;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import qatest.pageobjects.LandingPage;

public class StandAloneTest { 
	
	public static void main(String[] args)
	{
	String productName="ZARA COAT 3";
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	//driver.manage().window().setSize(new Dimension(1920, 1080));
	driver.get("https://rahulshettyacademy.com/client");
	LandingPage landingpage=new LandingPage(driver);
	driver.findElement(By.id("userEmail")).sendKeys("hinaya33@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Shivu2020");
	
	driver.findElement(By.id("login")).click();
	List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod= products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    //.cartSection h3
    List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
    //cartProducts.stream().filter(cart->cart.findElement(By.cssSelector("h3")).getText().equals(productName)).findFirst().orElse(null);
    Boolean match=cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
    Assert.assertTrue(match);
    
    
    /*WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button"))); 
    ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);*/
  driver.findElement(By.cssSelector(".totalRow button")).click();
   // JavascriptExecutor js = (JavascriptExecutor) driver;
   // js.executeScript("window.scrollBy(0,250)", "");
    
    Actions a = new Actions(driver);//advanced selenium for interaction with dynamiic webelemnts
    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    driver.findElement(By.cssSelector(".action__submit")).click();
    String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    driver.close();
    
	
	
	}
}

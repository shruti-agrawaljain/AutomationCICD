package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("shruti16@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shruti@123");
		driver.findElement(By.id("login")).click();
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement name = products.stream().filter(product -> product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		//Here stream() is used for iterating each and every product available in list. 
		//filter() is used to apply filter on partuicular product to find unique one. 
		//Here inside filter() we used fildElement() on that particular product which is iterated through stream() to getText of that particular product.
		//and findFirst() is used to choose first product only if more products are identified by applying filter().
		
		name.findElement(By.cssSelector("div[class='card-body'] button:last-of-type")).click();
		//Here in css selector we have used parent child relationship but for that we are getting two buttons for that particular product 
		//so after button we have used last-of-type attribute which is used to select last tag.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ngx-spinner[class*='ng-star-inserted']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ngx-spinner[class*='ng-star-inserted']")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		
		//Now my requirement is to verify any one item from the page that we have added in the cart or not
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean result = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		//After stream we have used anyMatch() instead of filter because filter() will return the WebElement here we have to not do any operation 
		//on WebElement only we have to verify whether that product is present in cart or not so we have used anyMatch() it will match with each and 
		//every product and if that product is present in cart then it will return true and result will store in Boolean variable and after that we 
		//can do Assertion from that result.
		Assert.assertTrue(result);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		WebElement dropdown = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(dropdown, "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-backdrop")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Boolean result2 = message.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(result2);
	}

}

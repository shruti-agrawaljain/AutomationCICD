package rahulshettyacademy.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException 
	{
		// TODO Auto-generated method stub
		//String productName = "ZARA COAT 3";
		landingpage.loginApplication("shruti16@gmail.com", "shruti123");
		String msg = landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email password.", msg);
		
	}
	
	@Test
	public void productErrorValidation()
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue pc = landingpage.loginApplication("shruti16@gmail.com", "Shruti@123");
		List<WebElement> productsList = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCartPage();
		boolean match = cp.verifyCartProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}

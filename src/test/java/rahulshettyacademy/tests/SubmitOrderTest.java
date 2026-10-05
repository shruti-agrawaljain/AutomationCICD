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
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	@Test
	public void submitOrder() throws IOException 
	{
		// TODO Auto-generated method stub
		ProductCatalogue pc = landingpage.loginApplication("shruti16@gmail.com", "Shruti@123");
		List<WebElement> productsList = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCartPage();
		boolean match = cp.verifyCartProduct(productName);
		Assert.assertTrue(match);
		CheckoutPage cop =cp.goToCheckout();
		cop.selectCountry("india");
		ConfirmationPage confirmPge = cop.submitBtn();
		String msg = confirmPge.getConfirmMessage();
		//Here we have used the concept of refractor means instead of creating objects of different Page Object Files in Test file we can 
		//create object of only first class and other class objects we can create in different Page Object Files.
		Boolean result2 = msg.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(result2);
		
		//Here we have done Driver object creation within Page object classes encapsulating it from Test.
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryPage()
	{
		ProductCatalogue pc = landingpage.loginApplication("shruti16@gmail.com", "Shruti@123");
		OrderPage orderPage = pc.goToOrderPage();
		Boolean res = orderPage.orderVerifyPage(productName);
		Assert.assertTrue(res);
	}

}

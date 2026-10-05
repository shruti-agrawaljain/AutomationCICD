package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class HashMapForWebsite extends BaseTest{

	@Test(dataProvider="getData", groups= {"PurchaseHash"})
	public void submitOrder(HashMap<String, String> input) throws IOException 
	{
		// TODO Auto-generated method stub
		ProductCatalogue pc = landingpage.loginApplication(input.get("mail"), input.get("pass"));
		List<WebElement> productsList = pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		CartPage cp = pc.goToCartPage();
		boolean match = cp.verifyCartProduct(input.get("productName"));
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
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData", groups= {"PurchaseHash"})
	public void orderHistoryPage(HashMap<String, String> input)
	{
		ProductCatalogue pc = landingpage.loginApplication(input.get("mail"), input.get("pass"));
		OrderPage orderPage = pc.goToOrderPage();
		Boolean res = orderPage.orderVerifyPage(input.get("productName"));
		Assert.assertTrue(res);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] 
		{
			{data.get(0)},{data.get(1)}
		};
	}

}

//In DataProvider we are using annotation as @DataProvider with one method in that method we are giving multiple data sets that how much time we 
//have to provide data and for that data sets we are using two dimensional array as Object[][].

//Then after creating any method with DataProvider annotation we can use that DataProvider with multiple test cases as 
//@Test(dataProvider="methodname") then with that method where we are using that @DataProvider annotation in that we have to use variables for datas.

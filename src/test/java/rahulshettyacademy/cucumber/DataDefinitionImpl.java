package rahulshettyacademy.cucumber;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class DataDefinitionImpl extends BaseTest{
	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmPge;
	//Given I landed on the Website
	@Given("I landed on the Website")
	public void i_landed_on_the_website() throws IOException
	{
		landingpage = launchApplication();
	}
	
	//Given I logged in with <username> and <password>
	@Given("^I logged in with (.+) and (.+)$")
	public void i_loggedin_with_username_and_password(String username, String password)
	{
		productcatalogue = landingpage.loginApplication(username, password);
	}
	
	//When I want to add product <productName> to Cart
	@When("^I want to add product (.+) to Cart$")
	public void i_want_to_add_product_to_cart(String productName)
	{
//		List<WebElement> productsList = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
	}
	
	//And Checkout <productName> and submit the order
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		cartpage = productcatalogue.goToCartPage();
		boolean match = cartpage.verifyCartProduct(productName);
		Assert.assertTrue(match);
		checkoutpage =cartpage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmPge = checkoutpage.submitBtn();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
	@Then("{string} message is displayed on Confirmation Page")
	public void message_is_pdisplayed_on_confirm_page(String string)
	{
		String msg = confirmPge.getConfirmMessage();
		Boolean result2 = msg.equalsIgnoreCase(string);
		Assert.assertTrue(result2);
		driver.close();
	}
	
	//Then "Incorrect email password." message should be displayed
	@Then("{string} message should be displayed")
	public void message_should_be_displayed(String string)
	{
		String msg = landingpage.getErrorMessage();
		Assert.assertEquals(string, msg);
		driver.close();
	}
	

}

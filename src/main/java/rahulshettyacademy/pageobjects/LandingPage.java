// These are Page Object Classes
package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id= "userEmail")
	WebElement usernameEle;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement loginEle;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;


	
	public ProductCatalogue loginApplication(String username, String password)
	{
		usernameEle.sendKeys(username);
		passwordEle.sendKeys(password);
		loginEle.click();
		return new ProductCatalogue(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
	public String getErrorMessage()
	{
		return errorMsg.getText();
	}	
	
}

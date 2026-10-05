package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	
	By productsWait = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("div[class='card-body'] button:last-of-type");
	By spinnerWait = By.cssSelector("ngx-spinner[class*='ng-star-inserted']");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsWait);
		System.out.println("Products found:" + products);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		List<WebElement> products = getProductList();
		for(WebElement product : products) {
			String actualProductName = product.findElement(By.cssSelector("b")).getText().trim();
			System.out.println("Expected"+ productName);
			System.out.println("Actual"+ actualProductName);
			
			if(actualProductName.equalsIgnoreCase(productName.trim())){
				return product;
			}
		}
		return null;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement name = getProductByName(productName);
		name.findElement(addToCart).click();
		waitForElementToAppear(spinnerWait);
		waitForElementToDisappear(spinnerWait);
	}
	
}

package demoweb.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import demoweb.genericutils.ObjectIdentificationUtils;

public class DemoWebBooksPage {
	
	WebDriver driver;
	
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();

	Logger logObj;
	
	
	
	////div[@class="product-item"]//child::div[@class='details']//child::a[text()='Fiction']
	////div[@class="product-item"]//child::div[@class='details']//child::a[text()='Fiction']//parent::h2//parent::div//following-sibling::div[@class='add-info']//child::input[@value='Add to cart']
	
	public DemoWebBooksPage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//p[text()='The product has been added to your ']")
	WebElement msg_AddedToCart_Successful;
	
	@FindBy(how=How.XPATH, using="//p[text()='The product has been added to your ']//child::a[text()='shopping cart']")
	WebElement lnk_ShoppingCart;
	
	@FindBy(how=How.XPATH, using="//h1[text()='Books']")
	WebElement tile_Books_Page;
	
	
	public boolean verifyBooksPage() throws Exception {
		
		if(objIdentification.waitForWebElement(tile_Books_Page, 10)){
			logObj.info("Navigated to Books page succesfully");
		}else {
			logObj.error("Unable to navigate books page");
			return false;
		}
		return true;
	}
	
	
	
	public boolean checkForDesiredBookInCatalogs(String bookName) {
		
		WebElement obj_BookItem = driver.findElement(By.xpath("//div[@class=\"product-item\"]//child::div[@class='details']//child::a[text()='" + bookName + "']"));
		try {
			
			if(obj_BookItem.isDisplayed()) {
				
				logObj.info(bookName + " is available in the catalog");
				
			}
		}catch(Exception ex) {
			
			logObj.error("'" + bookName + "' is not available in catalog. Please check!");
			return false;
			
		}
		
		return true;
	}
	
	
	public boolean addBookToCart(String bookName) {
		
		WebElement btn_AddToCart = driver.findElement(By.xpath("//div[@class=\"product-item\"]//child::div[@class='details']//child::a[text()='"+ bookName +"']//parent::h2//parent::div//following-sibling::div[@class='add-info']//child::input[@value='Add to cart']"));
		
		try {
			
			if (btn_AddToCart.isDisplayed()) {
				btn_AddToCart.click();
				
				if(objIdentification.waitForWebElement(msg_AddedToCart_Successful, 10) && lnk_ShoppingCart.isDisplayed() ) {
				
					logObj.info(bookName + " iis added to cart");
				}else {
					logObj.error("Not able to add Book Item '" + bookName + "' to cart. Please check!");
					return false;
				}
			}
		}catch(Exception ex) {
			logObj.error("Add to Cart button is not available. Please check!");
			return false;
		}
		return true;
	}
	
	
	public String getPriceOfBookItem(String bookName) {
		
		WebElement lbl_ItemPrice = driver.findElement(By.xpath("//div[@class=\"product-item\"]//child::div[@class='details']//child::a[text()='" + bookName + "']//parent::h2//parent::div//following-sibling::div[@class='prices']//child::span[@class='price actual-price']"));
		
		try {
			
			if (lbl_ItemPrice.isDisplayed()) {
				return lbl_ItemPrice.getText();	
			}else {
				return "-1";
			}
		}catch(Exception ex) {
			logObj.error("Add to Cart button is not available. Please check!");
			return "-1";
		}
	}
	
	public boolean clickOnShoppingCart() {
		try {
			
			if (lnk_ShoppingCart.isDisplayed()) {
				
				lnk_ShoppingCart.click();
				DemoWebShoppingCart shopingCartobj = new DemoWebShoppingCart(driver, logObj);
				if(!shopingCartobj.verifyShoppingCartPage()) {
					return false;
				}
			}
			
		}catch(Exception ex) {
			
			logObj.error("Unabe to identify Shopping Cart kink on successful message. Please check!");
			return false;
		}
		return true;
	}

}

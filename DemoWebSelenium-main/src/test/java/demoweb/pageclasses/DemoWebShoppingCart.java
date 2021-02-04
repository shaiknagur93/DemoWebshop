package demoweb.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import demoweb.genericutils.ObjectIdentificationUtils;

public class DemoWebShoppingCart {
	
	WebDriver driver;
	
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();

	Logger logObj;
	
	
	
	////div[@class="product-item"]//child::div[@class='details']//child::a[text()='Fiction']
	////div[@class="product-item"]//child::div[@class='details']//child::a[text()='Fiction']//parent::h2//parent::div//following-sibling::div[@class='add-info']//child::input[@value='Add to cart']
	
	////table[@class='cart']//tr//child::td[@class='product']//child::a[text()='Computing and Internet']//parent::td//following-sibling::td[@class='qty nobr']//child::input[contains(@name, 'itemquantity')]
	public DemoWebShoppingCart(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//h1[text() = 'Shopping cart']")
	WebElement title_ShoppingCart_Label;
	
	@FindBy(how=How.XPATH, using="//p[text()='The product has been added to your ']//child::a[text()='shopping cart']")
	WebElement lnk_ShoppingCart;
	
	@FindBy(how=How.NAME, using="updatecart")
	WebElement btn_UpdateShopping_Cart;
	
	@FindBy(how=How.XPATH, using="//table[@class='cart-total']//child::tr//td//child::span[text() = 'Sub-Total:']//parent::td//following-sibling::td//span[@class='product-price']")
	WebElement lbl_SubTotal_Price;
	
	@FindBy(how=How.XPATH, using="//div[@class='terms-of-service']//child::input[@id='termsofservice' and @type='checkbox']")
	WebElement chkbx_TermsOfService;
	
	
	@FindBy(how=How.ID, using="checkout")
	WebElement btn_Checkout;
	
	
	public boolean verifyShoppingCartPage() {
		
		try {
			
			if(objIdentification.waitForWebElement(title_ShoppingCart_Label, 10)) {
				
				logObj.info("Successfully navoigated to Shopping Cart");
				
			}
		}catch(Exception ex) {
			
			logObj.error("Unable to navigate to Shopping Cart");
			return false;
			
		}
		
		return true;
	}
	
	public boolean updateQuantityOfItemInCart(String itemName, String quantity) {
		try {
			WebElement fld_ItemQuantity = driver.findElement(By.xpath("//table[@class='cart']//tr//child::td[@class='product']//child::a[text()='" + itemName + "']//parent::td//following-sibling::td[@class='qty nobr']//child::input[contains(@name, 'itemquantity')]"));
			if(fld_ItemQuantity.isDisplayed()) {
				fld_ItemQuantity.clear();
				fld_ItemQuantity.sendKeys(quantity);
				
				logObj.info("Successfully updated the quantity of items");
				
			}
			
		}catch(Exception ex) {
			logObj.error("Unable to identify quantity field in shopping cart page");
			return false;
		}
		
		return true;
		
	}
	
	
	public boolean clickOnUpdateShoppingCart() {
		try {
			
			if (btn_UpdateShopping_Cart.isDisplayed()) {
				
				btn_UpdateShopping_Cart.click();
				Thread.sleep(3000);
			}
			
		}catch(Exception ex) {
			
			logObj.error("Unable to identofy  Update Shopping Cart button. Please check!");
			return false;
		}
		return true;
	}
	
	public float calculateTotalPriceForItem(String quantity, String itemPrice) {
		int quant = Integer.parseInt(quantity);
		float price = Float.parseFloat(itemPrice);
		
		float total = quant * price;
		
		return total;
	}
	
	
	public boolean verifySubtotalAmountForItem(String quantity, String itemPrice) {
		float total = calculateTotalPriceForItem(quantity, itemPrice);
		
		try {
			if(lbl_SubTotal_Price.isDisplayed()) {
				float subTotalPrice = Float.parseFloat(lbl_SubTotal_Price.getText());
				if (subTotalPrice == total) {
					logObj.info("Cart Total price is verified successfully");
				}else {
					logObj.error("Cart Total price is not matched with total  price. Please check!");
					return false;
				}
			}
		}catch(Exception ex) {
			
			logObj.error("Unable to identify sub total price. Please check!");
			return false;
		}
		return true;
	}
	
	
	public boolean selectTerfmsOfSDerviceCheckBox() {
		try {
			if(chkbx_TermsOfService.isDisplayed()) {
				chkbx_TermsOfService.click();
				logObj.info("sel;ected terfoirm of service checkbox");
			}
			
		}catch(Exception ex) {
			
			logObj.error("Unable to identify Terfms of checkbox. Please check!");
			return false;
		}
		return true;
	}
	
	public boolean clickOnCheckout() {
		try {
			if(btn_Checkout.isDisplayed()) {
				btn_Checkout.click();
				logObj.info("Clicked on Checkout");
				DemoWebCheckoutPage checkoutPage = new DemoWebCheckoutPage(driver, logObj);
				if(!checkoutPage.verifyCheckoutTitleOfThePage());
			}
			
		}catch(Exception ex) {
			
			logObj.error("Unable to identify Checkout button. Please check!");
			return false;
		}
		return true;
	}
	
	
	

}

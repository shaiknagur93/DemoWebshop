package demoweb.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import demoweb.genericutils.ObjectIdentificationUtils;

public class DemoWebOrderConfirmationPage {
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils identifyObjects = new ObjectIdentificationUtils();
	public DemoWebOrderConfirmationPage(WebDriver driver, Logger logObj) {
		this.driver = driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//div[@class='section order-completed']//child::div[@class='title']//child::strong")
	WebElement msg_OrderConfirmation;
	
	@FindBy(how=How.XPATH, using="//div[@class='section order-completed']//child::ul[@class='details']//child::li[1]")
	WebElement lbl_OrderNumber;
	
	@FindBy(how=How.XPATH, using="//div[@class='section order-completed']//child::div[@class='buttons']//child::input[@value='Continue']")
	WebElement btn_Continue;
	
	@FindBy(how=How.XPATH, using="//h1[text() = 'Thank you']")
	WebElement title_OrderConfirmation_Page;
	
	public boolean verifyOrderConfirmationPage() throws Exception {
		
		if(identifyObjects.waitForWebElement(title_OrderConfirmation_Page, 20)) {
			logObj.info("Order Confirmation page has been displayed successfully");
		}else {
			
			logObj.error("Order Confirmation page has not been disp;layed");
			return false;
			
		}
		
		return true;
		
	}
	
	
	public boolean verifyConderConfirmationMessage() {
		try {
			if(msg_OrderConfirmation.getText().trim().equals("Your order has been successfully processed!")) {
				logObj.info("Order hasbeen placed successfully");
			}
			
		}catch(Exception ex) {
			logObj.error("Unable to identify conformation message object. Please check!");
			return false;
		}
		
		return true;
	}
	
	
	public boolean printOrderNumber() {
		
		try {
			String orderNumber = lbl_OrderNumber.getText();
			logObj.info("Yay!! Order successfull and here is your ORDER NUMBER '" + orderNumber + "'" );
		}catch(Exception ex) {
			logObj.error("Unable to identify order Number lable. Please check!");
			return false;
		}
		
		return true;
	}
	
	
	public boolean clickOnContinueOnOrderConformationPage() {
		try {
			btn_Continue.click();
			
		}catch(Exception ex) {
			logObj.error("Unable to identify Continue button on Order confirmation Page. Please check!");
			return false;
		}
		
		return true;
	}
	
	
	
	

}

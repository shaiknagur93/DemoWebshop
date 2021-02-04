package demoweb.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import demoweb.genericutils.ObjectIdentificationUtils;

public class DemoWebCheckoutPage {
	
	WebDriver driver;
	
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();

	Logger logObj;
	
	
	
	////div[@class="product-item"]//child::div[@class='details']//child::a[text()='Fiction']
	////div[@class="product-item"]//child::div[@class='details']//child::a[text()='Fiction']//parent::h2//parent::div//following-sibling::div[@class='add-info']//child::input[@value='Add to cart']
	
	////table[@class='cart']//tr//child::td[@class='product']//child::a[text()='Computing and Internet']//parent::td//following-sibling::td[@class='qty nobr']//child::input[contains(@name, 'itemquantity')]
	public DemoWebCheckoutPage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//h1[text() = 'Checkout']")
	WebElement title_Checkout_Label;
	
	@FindBy(how=How.ID, using="billing-address-select")
	WebElement dropDown_BillingAddress;
	
	@FindBy(how=How.ID, using="BillingNewAddress_FirstName")
	WebElement fld_FirstName;
	
	@FindBy(how=How.ID, using="BillingNewAddress_LastName")
	WebElement fld_LastName;
	
	@FindBy(how=How.ID, using="BillingNewAddress_Email")
	WebElement fld_Email;
	
	@FindBy(how=How.ID, using="BillingNewAddress_CountryId")
	WebElement dropDown_Country;
	
	@FindBy(how=How.ID, using="BillingNewAddress_City")
	WebElement fld_City;
	
	@FindBy(how=How.ID, using="BillingNewAddress_Address1")
	WebElement fld_Address;
	
	@FindBy(how=How.ID, using="BillingNewAddress_ZipPostalCode")
	WebElement fld_ZipCode;
	
	@FindBy(how=How.ID, using="BillingNewAddress_PhoneNumber")
	WebElement fld_PhoneNumber;
	
	@FindBy(how=How.NAME, using="shipping_address_id")
	WebElement dropdown_ShippingAddress;
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-billing']//child::div[@id='billing-buttons-container']//child::input[@title='Continue']")
	WebElement btn_BillingContinue;
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-shipping']//child::div[@id='shipping-buttons-container']//child::input[@title='Continue']")
	WebElement btn_ShippingContinue;
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-shipping-method']//child::div[@id='shipping-method-buttons-container']//child::input[@value='Continue']")
	WebElement btn_ShipMethodContinue;
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-payment-method']//child::div[@id='payment-method-buttons-container']//child::input[@value='Continue']")
	WebElement btn_PaymetnMethodContinue;
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-payment-info']//child::div[@id='payment-info-buttons-container']//child::input[@value='Continue']")
	WebElement btn_PaymetnInfoContinue;
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-confirm-order']//child::div[@id='confirm-order-buttons-container']//child::input[@value='Confirm']")
	WebElement btn_ConfirmOrder;
	
	
	@FindBy(how=How.XPATH, using="//div[@id='checkout-step-payment-info']//child::div[@class='info']//p")
	WebElement label_paymentInformation;
	
	//NextDayAir
	
	//div[@id='checkout-step-shipping-method']//child::ul[@class='method-list']//li//child::label[contains(text(), 'Next Day Air')]//preceding-sibling::input
	
	//payment method
	
	//div[@id='checkout-step-payment-method']//div[@class='method-name']//child::label[contains(text(), 'Cash On Delivery (COD)')]//preceding-sibling::input
	
	//message
	//You will pay by COD
	
	public boolean verifyCheckoutTitleOfThePage() {
		
		try {
			
			if(objIdentification.waitForWebElement(title_Checkout_Label, 10)) {
				
				logObj.info("Successfully navoigated to Checkout Page");
				
			}
		}catch(Exception ex) {
			
			logObj.error("Unable to navigate to Checkout page");
			return false;
			
		}
		
		return true;
	}
	
	
	public boolean selectNewAddressFromBillingAddress() {
		
		try {
			if (dropDown_BillingAddress.isDisplayed()) {
				
				Select dd_BillingAddress = new Select(dropDown_BillingAddress);
				dd_BillingAddress.selectByVisibleText("New Address");
			}
		}catch(Exception ex) {
			
			logObj.error("Unable to identify Billing Address drop donw");
			return false;
		}
		return true;
	}
	
	
	public boolean waitUntilEnterAddressBlockDisplayed() {
		
		try {
			if(objIdentification.waitForWebElement(fld_FirstName, 5)) {
				
				logObj.info("Enter New address block displayed");
			}
		}catch(Exception ex) {
			logObj.error("New ddress block fields are not displayed to nter addres, Please check!");
			return false;
		}
		
		return true;
	}
	
	public boolean EnterNewAddress(String fname, String lname, String email, String country, String city, String address, String zip, String phoneNumber) {
		
//		try {
//			if(fld_FirstName.isDisplayed()) {
//				fld_FirstName.sendKeys(fname);
//				logObj.info("Entered First Name");
//			}
//			
//		}catch(Exception ex) {
//			logObj.error("FirstName field is not available, Please check!");
//			return false;
//		}
//		
//		try {
//			if(fld_LastName.isDisplayed()) {
//				fld_LastName.sendKeys(lname);
//				logObj.info("Entered Last Name");
//			}
//			
//		}catch(Exception ex) {
//			logObj.error("Last name field is not available, Please check!");
//			return false;
//		}
//		
//		try {
//			if(fld_Email.isDisplayed()) {
//				fld_Email.sendKeys(email);
//				logObj.info("EnteredEmail");
//			}
//			
//		}catch(Exception ex) {
//			logObj.error("Email field is not available, Please check!");
//			return false;
//		}
		
		try {
			if(dropDown_Country.isDisplayed()) {
				Select dd_Country = new Select(dropDown_Country);
				dd_Country.selectByVisibleText(country);
				logObj.info("Slected Email");
			}
			
		}catch(Exception ex) {
			logObj.error("Country drop down field is not available, Please check!");
			return false;
		}
		
		try {
			if(fld_City.isDisplayed()) {
				fld_City.sendKeys(city);
				logObj.info("Entered City");
			}
			
		}catch(Exception ex) {
			logObj.error("City field is not available, Please check!");
			return false;
		}
		
		try {
			if(fld_Address.isDisplayed()) {
				fld_Address.sendKeys(address);
				logObj.info("Entered Address1");
			}
			
		}catch(Exception ex) {
			logObj.error("Address1 field is not available, Please check!");
			return false;
		}
		
		
		try {
			if(fld_ZipCode.isDisplayed()) {
				fld_ZipCode.sendKeys(zip);
				logObj.info("Entered ZIP");
			}
			
		}catch(Exception ex) {
			logObj.error("Zip Code field is not available, Please check!");
			return false;
		}
		
		try {
			if(fld_PhoneNumber.isDisplayed()) {
				fld_PhoneNumber.sendKeys(phoneNumber);
				logObj.info("Entered Phone Number");
			}
			
		}catch(Exception ex) {
			logObj.error("Phone Number field is not available, Please check!");
			return false;
		}
		return true;
	}

	public boolean clickOnBillingContinue() {
		
		try {
			if(btn_BillingContinue.isDisplayed()) {
				btn_BillingContinue.click();
				if(objIdentification.waitForWebElement(dropdown_ShippingAddress, 20)) {
					logObj.info("Successfully navigated to Shipping Address Section");
				}else {
					logObj.error("BUnable to navigate to Shiping Address, Please check!");
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("Phone Number field is not available, Please check!");
			return false;
		}
		return true;
	}

	public boolean selectShippingAddressSameAsBilling(String fname, String lname, String email, String country, String city, String address, String zip, String phoneNumber) {
		
		try {
			if(objIdentification.waitForWebElement(dropdown_ShippingAddress, 20)) {
				try {
					Select dd_Shippingaddress = new Select(dropdown_ShippingAddress);
					String addressToSelect = fname + " " + lname + ", " + address + ", " + city + " " + zip + ", " + country;
					System.out.println(addressToSelect);
					dd_Shippingaddress.selectByVisibleText(addressToSelect);
				}catch(Exception shiipingSelectObj) {
					logObj.error("Unable to selct Shipping addres as billing address, Please check!");
					return false;
				}
				
			}else {
				logObj.error("Waited for 20 seconds Shipping address dropdown is not yet loaded, Please check!");
				return false;
			}
				
			
		}catch(Exception ex) {
			logObj.error("Unable to identify Shiping address, Please check!");
			return false;
		}
		
		
		return true;
		
	}
	
	public boolean clickOnShippingContinue(String shippingmethod) {
		
		try {
			if(btn_ShippingContinue.isDisplayed()) {
				btn_ShippingContinue.click();
				objIdentification.waitForEelement();
				if(objIdentification.waitForWebElement(btn_ShipMethodContinue, 20)) {
					logObj.info("Successfully navigated to Shipping Method Section");
				}else {
					logObj.error("Unable to navigate to Shipoing section, Please check!");
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("continue button in Shipping address section is not available is not available, Please check!");
			return false;
		}
		return true;
	}
	
	public boolean selectShippingMethod(String shippingmethod) {
		
		try {
			WebElement shippinMethod  = driver.findElement(By.xpath("//div[@id='checkout-step-shipping-method']//child::ul[@class='method-list']//li//child::label[contains(text(), '" + shippingmethod + "')]//preceding-sibling::input"));
			if(shippinMethod.isDisplayed()) {
				shippinMethod.click();
			}
		}catch(Exception ex) {
			logObj.error("Unable to identify Shippingmethod '" + shippingmethod + "', Please check!");
			return false;
		}
		
		return true;
		
	}
	
	public boolean clickOnShippingMethodContinue() {
		
		try {
			if(btn_ShipMethodContinue.isDisplayed()) {
				btn_ShipMethodContinue.click();
				objIdentification.waitForEelement();
				if(objIdentification.waitForWebElement(btn_PaymetnMethodContinue, 20)) {
					logObj.info("Successfully navigated to Payment Method Section");
				}else {
					logObj.error("Unable to navigate to Payment Method section, Please check!");
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("Continue button in Shipping Method section is not available, Please check!");
			return false;
		}
		return true;
	}
	
	public boolean selectPaymentMethod(String method) {
		String paymentMethod = "";
		
		if(method.toUpperCase().equals("COD")) {
			paymentMethod =  "Cash On Delivery (COD)";
		}
		
		
		try {
			WebElement payMethod  = driver.findElement(By.xpath("//div[@id='checkout-step-payment-method']//div[@class='method-name']//child::label[contains(text(), '" + paymentMethod + "')]//preceding-sibling::input"));
			if(payMethod.isDisplayed()) {
				payMethod.click();
				logObj.info("Successfully selected to Payment Method '" + paymentMethod + "'");
			}
		}catch(Exception ex) {
			logObj.error("Unable to identify Payment Method '" + paymentMethod + "', Please check!");
			return false;
		}
		
		return true;
		
	}
	
	public boolean clickOnPaymentMethodContinue() {
		
		try {
			if(btn_PaymetnMethodContinue.isDisplayed()) {
				btn_PaymetnMethodContinue.click();
				objIdentification.waitForEelement();
				if(objIdentification.waitForWebElement(btn_PaymetnInfoContinue, 20)) {
					logObj.info("Successfully navigated to Payment Information Section Section");
				}else {
					logObj.error("Unable to navigate to Payment Information Section, Please check!");
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("Continue button in Pament Method section is not available, Please check!");
			return false;
		}
		return true;
	}
	
	public boolean verifyPaymentInformation(String payMethod) {
		
		String message = "";
		
		if (payMethod.toUpperCase().equals("COD")) {
			message = "You will pay by COD";
		}
		
		try {
			if(message.equals(label_paymentInformation.getText().trim())){
				logObj.info("Payment method information displayed successfully as COD");
			}else {
				logObj.error("Unable to verify Payment information as COD, Please check!");
				return false;
			}
			
		}catch(Exception ex) {
			logObj.error("Payment information label is not displayed, Please check!");
			return false;
		}
		
		
		return true;
	}
	
	public boolean clickOnPaymentinformationContinue() {
		
		try {
			if(btn_PaymetnInfoContinue.isDisplayed()) {
				btn_PaymetnInfoContinue.click();
				objIdentification.waitForEelement();
				if(objIdentification.waitForWebElement(btn_ConfirmOrder, 20)) {
					logObj.info("Successfully navigated to Order conformation Section");
				}else {
					logObj.error("Unable to navigate to Order Confirmation section, Please check!");
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("Continue button in Pament information section is not available, Please check!");
			return false;
		}
		return true;
	}
	
	public boolean clickOnConfirmOrder() {
		
		try {
			if(btn_ConfirmOrder.isDisplayed()) {
				btn_ConfirmOrder.click();
				DemoWebOrderConfirmationPage confrimPage = new DemoWebOrderConfirmationPage(driver, logObj);
				if (confrimPage.verifyOrderConfirmationPage()) {
					logObj.info("Successfully navigated to order conformation page ");
				}else {
					logObj.error("Unable to navigate to Order Confirmation page  after click on Conform button, Please check");
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("Confirma button in order confirmation section is not available, Please check!");
			return false;
		}
		return true;
	}
	


	
	

}

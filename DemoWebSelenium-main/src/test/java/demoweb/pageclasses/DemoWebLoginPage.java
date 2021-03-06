package demoweb.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import demoweb.genericutils.ObjectIdentificationUtils;

public class DemoWebLoginPage {
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	
	public DemoWebLoginPage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.LINK_TEXT, using="Log in")
	WebElement lnk_Login;
	
	@FindBy(how=How.XPATH, using="//h1[text() = 'Welcome, Please Sign In!']")
	WebElement label_Welcome_SignIn;
	
	@FindBy(how=How.ID, using="Email")
	public WebElement fld_Email;
	
	@FindBy(how=How.ID, using="Password")
	WebElement fld_Password;
	
	@FindBy(how=How.XPATH, using="//input[@value='Log in']")
	WebElement btn_Login;
	
	
	//Method to click on Login button
	public boolean clickOnLogin() {
		
		try {
			if(lnk_Login.isDisplayed()) {
				try {
					lnk_Login.click();
					logObj.info("Click on login button successful");
					try {
						if(label_Welcome_SignIn.isDisplayed()) {
							logObj.info("Welcome, Please Sign In! message is displayed as Title after click on Login link");
						}
					}catch(Exception welcomeSignIn) {
						logObj.error("Welcome, Please Sign In! message is not displayed");
						return false;
					}
				}catch(Exception loginClick) {
					logObj.error("Unable to click on Login button");
					return false;
				}
			}
		}catch(Exception objDisplayed) {
			logObj.error("Login Button is not available on home screen");
			return false;
		}
		
		return true;
	}
	
	public boolean signIn(String userName, String password) {
		
		
		try {
			
			if (fld_Email.isDisplayed() && fld_Password.isDisplayed() && btn_Login.isDisplayed()) {
//				fld_Email.clear();
				fld_Email.sendKeys(userName);
				
//				fld_Password.clear();
				fld_Password.sendKeys(password);
				
				btn_Login.click();
				
				
			}else {
				
			}
		}catch(Exception signFields) {
			logObj.error("Unable to identify Login Credentials fields to login");
			return false;
		}
		
		return true;
	}
	
	
	public boolean verifyUserIDDisplayedAfterLogin(String userName) throws Exception {
		DemoWebHomePage homePage = new DemoWebHomePage(driver, logObj);
	
		
		if(homePage.verifyLoggedInUserID(userName)) {
			return true;
		}else {
			return false;
		}
		
	}
}

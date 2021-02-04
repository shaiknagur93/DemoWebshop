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

public class DemoWebHomePage {
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils identifyObjects = new ObjectIdentificationUtils();
	public DemoWebHomePage(WebDriver driver, Logger logObj) {
		this.driver = driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.LINK_TEXT, using="Books")
	WebElement lnk_BooksCategory;
	
	@FindBy(how=How.LINK_TEXT, using="Log out")
	WebElement lnk_Logout;
	
	
	public boolean verifyLoggedInUserID(String userName) throws Exception {
		

			WebElement lnk_userID = driver.findElement(By.linkText(userName));
		
			
			if(identifyObjects.waitForWebElement(lnk_userID, 15)) {
				logObj.info("Login is successfull and able to verify logged in user id");
			}else {
				
				logObj.error("Unable to see logged in User ID '" + userName + "'");
				return false;
				
			}
			return true;
	}
	
	
	public boolean selectCategory(String category) {
		
		try {
			
			WebElement lnk_Category = driver.findElement(By.linkText(category));
			if(lnk_Category.isDisplayed()) {
				lnk_Category.click();
				DemoWebBooksPage booksPage =  new DemoWebBooksPage(driver, logObj);
				if(booksPage.verifyBooksPage()) {
					
				}else {
					return false;
				}
			}
			
		}catch(Exception ex) {
			logObj.error("'" + category + "' link is not available on Home page");
			return false;
		}
		
		return true;
		
	}
	
	public boolean clickOnLogut() {
		try {
			lnk_Logout.click();
			DemoWebLoginPage loginPage = new DemoWebLoginPage(driver, logObj);
			if(identifyObjects.waitForWebElement(loginPage.lnk_Login, 15)) {
				logObj.info("Logout successfull!!!!");
			}else {
				
				logObj.error("Un pageLogout failed! Unable to see log in butotn on Logi");
				return false;
				
			}
			
		}catch(Exception ex) {
			logObj.error("Unable to identify Log Out button on Home page");
			return false;
		}
		
		return true;
	}
}

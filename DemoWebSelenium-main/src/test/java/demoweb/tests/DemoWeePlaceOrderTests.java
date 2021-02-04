package demoweb.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.io.Files;

import demoweb.excelutils.ExcelDataExtraction;
import demoweb.genericutils.TestDataProps;
import demoweb.pageclasses.DemoWebBooksPage;
import demoweb.pageclasses.DemoWebCheckoutPage;
import demoweb.pageclasses.DemoWebHomePage;
import demoweb.pageclasses.DemoWebLoginPage;
import demoweb.pageclasses.DemoWebOrderConfirmationPage;
import demoweb.pageclasses.DemoWebShoppingCart;

public class DemoWeePlaceOrderTests extends Driver {
	
	String workingDir = System.getProperty("user.dir");
	WebDriver driver;
	ExcelDataExtraction excelObj = new ExcelDataExtraction();
	Multimap<String, String> multiMap = ArrayListMultimap.create();
	TestDataProps testProps = new TestDataProps();
	Properties prop; 
	Logger logObj = Logger.getLogger("PLace Order Tests");
	
	@BeforeClass
	public void beforeClassMethod() throws Exception {
		prop = testProps.getProps();
		multiMap = excelObj.Exceldatextraction(workingDir + "/testdata/PlaceOrderTests_TestData.xlsx");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		PropertyConfigurator.configure(workingDir + "/configs/log4j.properties");
		System.setProperty("webdriver.chrome.driver", workingDir + "\\browserdrivers\\chromedriver.exe");
		driver = new ChromeDriver();  
		driver.get(prop.getProperty("APP_URL"));		
		driver.manage().window().maximize();
		
	}
	
	
	@Test(dataProvider="TestData")
	public void VerifyBookItemPlaceOrder(String category, String bookName, String fName, String lName, String email, String address, String city, String country, 
			String zipCode, String phoneNumber, String shippingMethod, String paymentMethod) throws Exception {
		
		String userName = prop.getProperty("EMAIL");
		String pwd = prop.getProperty("PASSWORD");
		String bookPrice = "";
		System.out.println(userName);
		System.out.println(pwd);
		DemoWebLoginPage loginPage = new DemoWebLoginPage(driver, logObj);
		
		//Click on Login button in login page
		AssertJUnit.assertTrue(loginPage.clickOnLogin());
		
		//Enter Login Credentials and Click on LOgin button
		loginPage.signIn(userName, pwd);
		
		//Create HOME PAGE object
		DemoWebHomePage homePage = new DemoWebHomePage(driver, logObj);
		
		//Verifying logged in user id after login
		AssertJUnit.assertTrue(homePage.verifyLoggedInUserID(userName));
		
		//Select Books Category from home page
		AssertJUnit.assertTrue(homePage.selectCategory(category));
		
		//Create Books page object
		DemoWebBooksPage booksPage = new DemoWebBooksPage(driver, logObj);
		
		//verify whether book is available in the cart
		AssertJUnit.assertTrue(booksPage.checkForDesiredBookInCatalogs(bookName));
		
		//add desired book to card
		AssertJUnit.assertTrue(booksPage.addBookToCart(bookName));
		
		//Get the price of the book selected
		bookPrice = booksPage.getPriceOfBookItem(bookName);
		if (bookPrice.equals("-1")) {
			logObj.error("Unable to get Book Price for '" + bookName + "'. Please check");
			AssertJUnit.assertTrue(false);
		}
		
		//Go to shopping cart
		AssertJUnit.assertTrue(booksPage.clickOnShoppingCart());
		
		//Create page object of shopping cart
		DemoWebShoppingCart shopingCartobj = new DemoWebShoppingCart(driver, logObj);
		
		//update qualtity of the otems
		AssertJUnit.assertTrue(shopingCartobj.updateQuantityOfItemInCart(bookName, "2"));
		AssertJUnit.assertTrue(shopingCartobj.clickOnUpdateShoppingCart());
		
		//verify subtotal amount on cart
		AssertJUnit.assertTrue(shopingCartobj.verifySubtotalAmountForItem("2", bookPrice));
		
		//select terms and conditions and click checkout
		AssertJUnit.assertTrue(shopingCartobj.selectTerfmsOfSDerviceCheckBox());
		AssertJUnit.assertTrue(shopingCartobj.clickOnCheckout());
		
		//Create object of the Checkout Page
		DemoWebCheckoutPage checkoutPage = new DemoWebCheckoutPage(driver, logObj);
		
		//select new Billing address from billing drop down
		AssertJUnit.assertTrue(checkoutPage.selectNewAddressFromBillingAddress());
		
		//Wait until new address block is displayed		
		AssertJUnit.assertTrue(checkoutPage.waitUntilEnterAddressBlockDisplayed());
		
//		String fname = "atest";
//		String lname = "dummy";
//		String email = "atest@gmail.com";
//		String country = "India";
//		String city = "Hyderabad";
//		String address = "KukatpallyTU";
//		String zip = "500087";
//		String phoneNumber = "5556789023";
//		String shippingMethod = "Next Day Air";
//		String paymentMethod = "COD";
		
		//enter address details
		AssertJUnit.assertTrue(checkoutPage.EnterNewAddress(fName, lName, email, country, city, address, zipCode, phoneNumber));
		
		//Click on Continue button on  Biling Section
		AssertJUnit.assertTrue(checkoutPage.clickOnBillingContinue());
		
		//Select the Billing address entered above as Shipping address
		AssertJUnit.assertTrue(checkoutPage.selectShippingAddressSameAsBilling(fName, lName, email, country, city, address, zipCode, phoneNumber));
		
		//Click on Continue button in Shipping
		AssertJUnit.assertTrue(checkoutPage.clickOnShippingContinue(shippingMethod));
		
		//Select Shipping Method
		AssertJUnit.assertTrue(checkoutPage.selectShippingMethod(shippingMethod));
		
		//Click on Continue button in Shipping method selection section
		AssertJUnit.assertTrue(checkoutPage.clickOnShippingMethodContinue());
		
		//Select Payment  Method
		AssertJUnit.assertTrue(checkoutPage.selectPaymentMethod(paymentMethod));
		
		//Click on Continue button in Payment method selection section
		AssertJUnit.assertTrue(checkoutPage.clickOnPaymentMethodContinue());
		
		//Verify selected payment method displayed or not
		AssertJUnit.assertTrue(checkoutPage.verifyPaymentInformation("COD"));
		
		//Click on Continue  button in Payment information
		AssertJUnit.assertTrue(checkoutPage.clickOnPaymentinformationContinue());
		
		//Click on Confirm Order Button
		AssertJUnit.assertTrue(checkoutPage.clickOnConfirmOrder());
		
		//Create confirmation page object
		DemoWebOrderConfirmationPage confrimPage = new DemoWebOrderConfirmationPage(driver, logObj);
		
		//Verify Confirmation message
		AssertJUnit.assertTrue(confrimPage.verifyConderConfirmationMessage());
		
		//Print the order Number
		AssertJUnit.assertTrue(confrimPage.printOrderNumber());
		
		//Click on COntinue button
		AssertJUnit.assertTrue(confrimPage.clickOnContinueOnOrderConformationPage());
		
		AssertJUnit.assertTrue(homePage.clickOnLogut());
	
		
	}
	
	@AfterMethod	
	public void afterMethod(ITestResult result) throws Exception {
			TakesScreenshot screenObj = (TakesScreenshot)driver;
			File snapFile = screenObj.getScreenshotAs(OutputType.FILE);
			Date dateObj = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMYYYYhhmmss");
			String date = dateFormat.format(dateObj);
			File destNationFolder = new File(workingDir + "/screenshots/" + result.getName() + "_" + date + ".png");
			Files.copy(snapFile, destNationFolder);
		
		
			driver.quit();
		
	}
	
	@DataProvider(name="TestData")
	public String[][] dataProviderMethos(Method mtd){
		System.out.println(mtd.getName());
		String testcaseName = mtd.getName();
		
		String[][] arrayData = null;
		try {
			arrayData = excelObj.GetTestCaseDataArray(testcaseName, multiMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return arrayData;
	}
	
	
	

}

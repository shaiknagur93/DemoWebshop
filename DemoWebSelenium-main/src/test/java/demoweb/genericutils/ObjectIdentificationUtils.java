package demoweb.genericutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ObjectIdentificationUtils {
	
	GenericMethods genericMethods = new GenericMethods();
	Logger logObj = Logger.getLogger("ObjectIdentificationUtils");
	
	/*
	   * Author: Sharaf
	   * Method name: waitForWebElement
	   * Descriptio0n: This is a customized function for Dynamic wait. it waits for specified amount of time and 
	   * 				polls for every second to check whether object is displayed or not
	   * Parameter: 
	   */
	public boolean waitForWebElement(WebElement ele, int seconds) throws Exception {
		
		int cnt = 0;
		boolean isExists= false;
		while(cnt <= seconds) {
			try {
				isExists = ele.isDisplayed();
				break;
			}catch(Exception ex) {
				Thread.sleep(1000);
				cnt = cnt + 1;
			}
		}		
		if(isExists) {
			return true;
		}else {
			logObj.info("Waitedf for '" + seconds + "' and Unable to find element on the Page" );
			return false;
		}
	}
	
	public void waitForEelement() throws Exception {
		Thread.sleep(2000);
	}
	
	

}

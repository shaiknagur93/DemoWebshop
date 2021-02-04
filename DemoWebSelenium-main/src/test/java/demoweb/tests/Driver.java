package demoweb.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.google.common.io.Files;

import demoweb.genericutils.GenericMethods;

public class Driver {
	String workingDir = System.getProperty("user.dir");
	GenericMethods genericMethods = new GenericMethods();
	@BeforeSuite
	public void beforeSuite() {
		if(genericMethods.deleteFilesInDirectory(workingDir + "/log")) {
			
			Assert.assertTrue(true);
		}else {
			System.out.println("Before Suite cOnfiguration failed at deleting files in 'log' folder");
			Assert.assertTrue(false);
		}
		
		if(genericMethods.deleteFilesInDirectory(workingDir + "/screenshots")) {			
			Assert.assertTrue(true);
		}else {
			System.out.println("Before Suite cOnfiguration failed at deleting files in 'screenshots' folder");
			Assert.assertTrue(false);
		}		
	}
	
	

}

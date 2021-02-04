package demoweb.genericutils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class GenericMethods {
	
	/*
	   * Author: Sharaf
	   * Method name: takeSnapshot
	   * Descriptio0n: This method tasks the snapshot
	   * Parameter: 
	   */
	public void takeSnapshot(WebDriver driver, String testName) throws Exception {
		try{
			String workingDir = System.getProperty("user.dir");
			TakesScreenshot ts = (TakesScreenshot)driver;
			Date curDate = new Date();                                            
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMYYYYhhmmss"); 
			String actdate = dateFormat.format(curDate); 
			String fileName = workingDir + "/screenshots/" + actdate + "_" + testName + ".png";
			File snap = ts.getScreenshotAs(OutputType.FILE);
			File actscreenshot = new File(fileName);
			Files.copy(snap, actscreenshot);
		}catch(Exception ex) {
			System.out.println("Unable to take snpshot for test script '" + testName + "', please check");
		}
	}
	
	/*
	   * Author: Sharaf
	   * Method name: deleteFilesInDirectory
	   * Descriptio0n: Delete the files i the folder recursively
	   * Parameter: 
	   */
	
	public boolean deleteFilesInDirectory(String path) {
	    try{
	    	File file = new File(path);	 
	    	if(file.exists()) {
	    		boolean flag = false;
			    File[] files = file.listFiles(); 
			    if(files.length == 0) {
			    	flag = true;
			    	System.out.println("No FIles exists in the folder '" + path);
			    }
			    for (File f:files) 
			    {
			    	if (f.isFile() && f.exists()) 
			        { 
			    		f.delete();
			    		System.out.println("successfully deleted");
			    		flag = true;
			        }else{
			        	System.out.println("cant delete a file due to open or error");
			        } 
			    }
						
				if(flag) {
					System.out.println("Files deleted successfully from folder " + path);
					return true;
				}else {
					System.out.println("Files deleted successfully from folder " + path);
					return false;
				}
	    	}
	    	else {
	    		return true;
	    	}
		    
	    }catch(Exception ex) {
	    	System.out.println("Unable to deleted files fom Folder '" + path + "'" );
	    	return false;
	    }
			
	}
	

}

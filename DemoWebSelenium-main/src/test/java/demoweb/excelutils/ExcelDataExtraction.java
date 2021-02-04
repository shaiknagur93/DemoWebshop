package demoweb.excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class ExcelDataExtraction {	

	/*
	   * Author: Sharaf
	   * Method name: Exceldatextraction
	   * Descriptio0n: This Method will scan the Excel file given and reads teh data for each test case and do the below
	   * 	1. reads the data for each test case and stores the data in multimap (test case name is the key and column values are Value)
	   * 	2. If we have multiple set of data for each test, this stores the each set in the multimap
	   * Parameter: excelPath
	   */
	
  public Multimap<String, String> Exceldatextraction(String excelPath) throws Exception{
	  
	  Multimap<String, String> multiMap = ArrayListMultimap.create();
      
		File file = new File(excelPath);
		FileInputStream fis = new FileInputStream(file);		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int noOfRows = sheet.getLastRowNum();
		String sTestCaseName;
		int iROW = 0;
		while(iROW <=noOfRows){
			Cell oCell1 = sheet.getRow(iROW).getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			Cell oCell2 = sheet.getRow(iROW).getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if(oCell1 != null && oCell2 != null){
				int iTestDataRowForCurrentTC = 0;
				int iColHeaderRow = iROW;
				int iCOL = 1;
				sTestCaseName = sheet.getRow(iROW).getCell(0).getStringCellValue();
				System.out.println(sTestCaseName);
				while(1<2){
					Cell oCell = sheet.getRow(iROW).getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
					if(oCell == null){
						break;
					}else{
						iTestDataRowForCurrentTC = iTestDataRowForCurrentTC + 1;
						iROW++;
					}
				}
				String sTCParName;
				while(sheet.getRow(iColHeaderRow).getCell(iCOL, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null){
					sTCParName = sheet.getRow(iColHeaderRow).getCell(iCOL).getStringCellValue();
					String append = sTCParName + ":-:";// usename:-:naga||rau<>ee<>simns, password:-:data<>d:>ata2<>data3, phoneNumber:-:data<>data<>date
					for(int i = 1; i<iTestDataRowForCurrentTC; i++){
						if(i!=iTestDataRowForCurrentTC-1){
							append = append + sheet.getRow(iColHeaderRow+i).getCell(iCOL).getStringCellValue() + "<>";
						}else{
							append = append + sheet.getRow(iColHeaderRow+i).getCell(iCOL).getStringCellValue();
						}
					}
					multiMap.put(sTestCaseName, append);
					iCOL++;
				}
			}else{
				iROW++;
			}
		}
		return multiMap;
  }
  
  
  /*
   * Author: Sharaf
   * Method name: GetTestCaseDataArray
   * Descriptio0n: This method read the data from Multiamp object with the help of TestZcasename given and converts data into Array
   * 				It will read and stores the data a multiple rows into Dataprovider Array
   * Parameter: testCaseName, testCaseName object
   */
  
  public String[][] GetTestCaseDataArray(String testCaseName, Multimap<String, String> multiMap) throws Exception{
		
		int iLength = 0;
		System.out.println("=============================================");
	
		
      Collection<String> valueForKey = multiMap.get(testCaseName);
      String[] araVals = null;
      for (String val : valueForKey){
      	String[] arraparmValues = val.split(":-:");
      	String s = arraparmValues[1];
      	araVals= s.split("<>");
      	break;
     }
      String[][] arrayExcelData = null;
      arrayExcelData = new String[araVals.length][valueForKey.size()];
      int colIndx = 0;
      
      for (String val : valueForKey){ //UserName:-:Hello<>erer
      	String[] arraparmValues = val.split(":-:");
      	String s = arraparmValues[1];
      	araVals= s.split("<>");
      	for (int rowIndx = 0; rowIndx < araVals.length; rowIndx++){
      		arrayExcelData[rowIndx][colIndx] = araVals[rowIndx];
      	}
      	colIndx++;
      	if(colIndx == valueForKey.size()){
      		break;
      	}
      }
      
      
      return arrayExcelData;
      

	}
  
  
}

package myfinance;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/* Here in this scenario we are generating a variable Invoice by using VariableInvoiceGeneration() method and paying 
 * amount for the generated invoice by using PayVariableInvoice() method in that only we are getting voucher no. also.*/

public class FinancialTestingIMA_TC_003 {
	WebDriver driver = new FirefoxDriver();
	String splitmessage;
	logindetails ldr = new logindetails();
	InvoiceGenerationHelper help1 = new InvoiceGenerationHelpDEMO10();
	FinancialVouchersHelp help2 = new FinancialVouchersDEMO10();
	
	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String URL,String username, String password) throws InterruptedException {
		ldr.adminlogin(driver,URL, username, password);
		Thread.sleep(2000);
	}
	@Test (priority = 2,dataProvider = "VariableInvoice")
	public void VariableInvoiceGeneration(String URL,String Invoicedateid,String Duedateid,
			String apartment,String Block,String amount,String narration,String url2) throws BiffException, InterruptedException, IOException{
	 splitmessage =	 help1.GenerateVariableInvoice(driver,URL, Invoicedateid, Duedateid,
				 apartment, Block, amount, narration, url2);
		
	}
	
	@Test(priority =3,dataProvider = "Payment")
	public void PayVariableInvoice(String url,String dateid, String Block,  String Flatno, String narration) throws InterruptedException, BiffException, IOException{
			help2.Payment(driver,url, dateid,  Block,  Flatno, splitmessage,  narration);
		
	}
	
	@DataProvider    (name = "logintestdata")
	public  Object [][] readexcel()  throws  IOException, BiffException  {
	   File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
	    Workbook ws= Workbook.getWorkbook(fs);
	    Sheet s = ws.getSheet("Logindata");
	    int rows = s.getRows();
	    int columns = s.getColumns();
	    String inputdata [][]= new String [rows-1][columns]; 
	    for (int i=1; i<rows; i++){
	        for (int j=0; j<columns; j++){
	            Cell cl = s.getCell(j,i);
	            inputdata [i-1][j] = cl.getContents();
	                  
	        }
	    }
	            return inputdata;
	}
	
	@DataProvider    (name = "VariableInvoice")
	public  Object [][] readexcel1()  throws  IOException, BiffException  {
	   File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FinanceTestingIMA_TC_003.xls");
	    Workbook ws= Workbook.getWorkbook(fs);
	    Sheet s = ws.getSheet("VariableInvoiceGeneration");
	    int rows = s.getRows();
	    int columns = s.getColumns();
	    String inputdata [][]= new String [rows-1][columns]; 
	    for (int i=1; i<rows; i++){
	        for (int j=0; j<columns; j++){
	            Cell cl = s.getCell(j,i);
	            inputdata [i-1][j] = cl.getContents();
	                  
	        }
	    }
	            return inputdata;
	}
	
	@DataProvider    (name = "Payment")
	public  Object [][] readexcel2()  throws  IOException, BiffException  {
	   File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FinanceTestingIMA_TC_003.xls");
	    Workbook ws= Workbook.getWorkbook(fs);
	    Sheet s = ws.getSheet("Payment");
	    int rows = s.getRows();
	    int columns = s.getColumns();
	    String inputdata [][]= new String [rows-1][columns]; 
	    for (int i=1; i<rows; i++){
	        for (int j=0; j<columns; j++){
	            Cell cl = s.getCell(j,i);
	            inputdata [i-1][j] = cl.getContents();
	                  
	        }
	    }
	            return inputdata;
	}
}

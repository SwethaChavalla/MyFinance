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
	InvoiceGenerationHelper help1 = new InvoiceGenerationhelpdemo12();
	FinancialVouchersHelp help2 = new FinancialVouchersDEMO12();
	
	@Test(priority = 1, dataProvider = "logintestdata")
	public void Login(String username, String password, String URL) throws InterruptedException {
		ldr.adminlogin(driver, username, password);
		driver.navigate().to(URL);
		Thread.sleep(3000);
	}
	@DataProvider(name = "VariableInvoice")
	public Object[][] VariableInvoiceGeneration() throws BiffException, InterruptedException, IOException{
		return help1.GenerateVariableInvoice(driver);
		
	}
	
	@Test(priority =2,dataProvider = "VariableInvoice")
	public void PayVariableInvoice(String splitmessage) throws InterruptedException{
		help1.VoucherNo(driver, splitmessage);
		help2.Payment(driver, splitmessage);
		
	}
	
	@DataProvider(name = "logintestdata")
	   public Object[][] readexcel() throws IOException, BiffException {
	   	File fs = new File(
	   			"C:/Users/Swetha/Desktop/Financial scenarios data xls files/Copy of Vaariable data for scenario1 in Finance scenarios.xls");
	   	Workbook ws = Workbook.getWorkbook(fs);
	   	Sheet s = ws.getSheet(0);
	   	int rows = s.getRows();
	   	int columns = s.getColumns();
	   	String inputdata[][] = new String[rows][columns];
	   	for (int i = 0; i < rows; i++) {
	   		for (int j = 0; j < columns; j++) {
	   			Cell cl = s.getCell(j, i);
	   			inputdata[i][j] = cl.getContents();
	   		}
	   	}
	   	return inputdata;
	   }
}

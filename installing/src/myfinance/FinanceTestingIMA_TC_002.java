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

/* Here in this scenario we are generating a Fixed Invoice by using FixedInvoiceGenerationWithDates() method 
 * and reversing the same invoice by using ReverseFixedInvoice() method.  */



public class FinanceTestingIMA_TC_002 {
WebDriver driver = new FirefoxDriver();
String splitmessage;
logindetails ldr = new logindetails();
InvoiceGenerationHelper help1 = new InvoiceGenerationHelper();

@Test(priority = 1, dataProvider = "logintestdata")
public void Login(String URL,String username, String password) throws InterruptedException {
	ldr.adminlogin(driver,URL, username, password);
	Thread.sleep(2000);
}
@DataProvider(name="FixedInvoiceno")
public Object[][] FixedInvoiceGenerationWithDates() throws BiffException, InterruptedException, IOException{
	 return help1.GenerateFixedInvoiceno(driver);
	
}
@Test(priority=2,dataProvider="FixedInvoiceno")
public void ReverseFixedInvoice(String splitmessage) throws InterruptedException, BiffException, IOException{
	help1.ReverseInvoice(driver, splitmessage);
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
	}



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
 * and reversing the same invoice by using ReverseFixedInvoice() method. Before running this script set date
 * in GenerateFixedInvoiceno and GenerateFixedInvoicenoForTenant.  */



public class FinanceTestingIMA_TC_002 {
WebDriver driver = new FirefoxDriver();
String splitmessage;
logindetails ldr = new logindetails();
InvoiceGenerationHelper help1 = new InvoiceGenerationHelpDEMO10();

@Test(priority = 1, dataProvider = "logintestdata")
public void Login(String URL,String username, String password) throws InterruptedException {
	ldr.adminlogin(driver,URL, username, password);
	Thread.sleep(2000);
}
@Test(priority=2,dataProvider="FixedInvoice")
public void FixedInvoiceGenerationWithDates(String URL,String Invoicedateid,String Duedateid,String apartment
		,String Block,String amount,String narration,String URL2)  throws BiffException, InterruptedException, IOException{
	splitmessage = help1.GenerateFixedInvoiceno(driver, URL, Invoicedateid, Duedateid, apartment
				, Block, amount, narration, URL2) ;
	
}
@Test (priority=4,dataProvider="FixedInvoiceForTenant")
public void GenerateFixedInvoiceTenant(String URL,String Invoicedateid,String Duedateid,String apartment
		,String Block,String amount,String narration,String URL2) throws BiffException, InterruptedException, IOException{
	help1.GenerateFixedInvoicenoForTenant(driver, URL, Invoicedateid, Duedateid, apartment
			, Block, amount, narration, URL2);
}

@Test(priority=3,dataProvider="ReverseInvoice")
public void ReverseFixedInvoice(String url,String dateid,String url2) throws InterruptedException, BiffException, IOException{
	help1.ReverseInvoice(driver,url, splitmessage,dateid,url2);
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

@DataProvider    (name = "FixedInvoice")
public  Object [][] readexcel1()  throws  IOException, BiffException  {
   File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FinanceTestingIMA_TC_002.xls");
    Workbook ws= Workbook.getWorkbook(fs);
    Sheet s = ws.getSheet("FixedInvoiceGeneration");
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

@DataProvider    (name = "ReverseInvoice")
public  Object [][] readexcel2()  throws  IOException, BiffException  {
   File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FinanceTestingIMA_TC_002.xls");
    Workbook ws= Workbook.getWorkbook(fs);
    Sheet s = ws.getSheet("ReverseInvoice");
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

@DataProvider    (name = "FixedInvoiceForTenant")
public  Object [][] readexcel3()  throws  IOException, BiffException  {
   File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/FinanceTestingIMA_TC_002.xls");
    Workbook ws= Workbook.getWorkbook(fs);
    Sheet s = ws.getSheet("FixedInvoiceGenerationTenant");
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



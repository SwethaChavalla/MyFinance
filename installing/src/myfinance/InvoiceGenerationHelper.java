package myfinance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class InvoiceGenerationHelper {
	// WebDriver driver = new FirefoxDriver();
	String URL = "https://test-itsmyaccount.azurewebsites.net/Login";
	String username = "DEMO_12";
	String password = "Welcome";
	logindetails ldr = new logindetails();
	String splitmessge;	
	

public String GenerateFixedInvoiceno(WebDriver driver,String URL,String Invoicedateid,String Inyear,String Inmonth,
		String Indate,String Duedateid,String Dueyear,String Duemonth, String Duedate,String apartment
		,String Block,String amount,String narration,String URL2) throws InterruptedException, BiffException, IOException {
	int year = Integer.parseInt(Inyear);
	int month = Integer.parseInt(Inmonth);
	int date = Integer.parseInt(Indate);
	int Dyear = Integer.parseInt(Dueyear);
	int Dmonth = Integer.parseInt(Duemonth);
	int Ddate = Integer.parseInt(Duedate);
	            driver.navigate().to(URL);
				Thread.sleep(2000);
				driver.findElement(By.id("Fixed")).click();
				Thread.sleep(2000);
				setDate(driver,Invoicedateid,year,month,date);
				Thread.sleep(2000);
				setDate(driver,Duedateid,Dyear,Dmonth,Ddate);
				Thread.sleep(2000);
				driver.findElement(
						By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
						.click();
				Thread.sleep(4000);
				driver.findElement(By.linkText(apartment)).click();
				Thread.sleep(2000);
				driver.findElement(
						By.id("auto_ApartmentID")).sendKeys(Block);
				Thread.sleep(2000);
				driver.findElement(By.id("BA1")).clear();
				Thread.sleep(2000);
				driver.findElement(By.id("BA1")).sendKeys(amount);// basic
																	// amount
				Thread.sleep(2000);
				driver.findElement(By.id("x1")).sendKeys(narration);// narration
				Thread.sleep(2000);
				driver.findElement(By.id("Gen")).click(); // Generate button
				Thread.sleep(5000);
				driver.findElement(By.id("Generate")).click();// yes button
				Thread.sleep(2000);
				driver.findElement(By.id("GenInv")).click(); // generate invoice
																// button

				 WebDriverWait wait = new WebDriverWait(driver,50);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				String splitmessage = message.split(" ")[2];
				System.out.println("Maintanance fixed invoice no." + splitmessage);
				Thread.sleep(2000);
				String fixedvoucherno = splitmessage;
				Thread.sleep(2000);
				ldr.SearchVoucherno(driver,URL2,splitmessage);
				return fixedvoucherno;
}

      public void SelectTaxInProfile(WebDriver driver,String url,String choose,String Taxpercentage) throws InterruptedException, BiffException, IOException{
    	  driver.navigate().to(url);
    	  WebElement checkbox = driver.findElement(By.id("ServiceTax"));
    	  String selectcheckbox = "yes";
    	  if(selectcheckbox.equals(choose)){
    	  if(checkbox.isSelected() == true){
    		  System.out.println("Tax Percentage is:  "  + Taxpercentage);
    		      		  
    	  }
    	  else{
    		  checkbox.click();
    			Thread.sleep(2000);
    			driver.findElement(By.id("ServiceTaxPercent")).sendKeys(Taxpercentage);
    			Thread.sleep(2000);
    			driver.findElement(By.id("btnSave")).click();
    			ldr.logout(driver);
    	  }
    	  }
    	  else{
    		 if(checkbox.isSelected() == true){
    			 checkbox.click();
    			 Thread.sleep(2000);
     			driver.findElement(By.id("btnSave")).click();
     			ldr.logout(driver);
    		  }
    		 else{
    			 System.out.println("Tax not selected");
    		 }
    	  }
    	  ldr.logout(driver);
    	  }
      
      public void setDate(WebDriver driver,String dateid,int year,int month,int day) throws InterruptedException{
    	  System.out.println("Taken Current Date");
      }
      
      public String GenerateFixedInvoicenoForTenant(WebDriver driver,String URL,String Invoicedateid,String Duedateid,String apartment
    			,String Block,String amount,String narration,String URL2) throws InterruptedException, BiffException, IOException {
  			driver.navigate().to(URL);
  				Thread.sleep(2000);
  				driver.findElement(By.id("Fixed")).click();
  				Thread.sleep(2000);
  				setDate(driver,Invoicedateid,2016,10,1);
  				Thread.sleep(2000);
  				setDate(driver,Duedateid,2016,10,11);
  				Thread.sleep(2000);
  				driver.findElement(
  						By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
  						.click();
  				Thread.sleep(4000);
  				driver.findElement(By.linkText(apartment)).click();
  				Thread.sleep(2000);
  				driver.findElement(
  						By.id("auto_ApartmentID")).sendKeys(Block);
  				Thread.sleep(2000);
  				driver.findElement(By.id("BAT1")).clear();
  					Thread.sleep(2000);
  					driver.findElement(By.id("BAT1")).sendKeys(amount);// basic
  																		// amount
  					Thread.sleep(2000);
  				driver.findElement(By.id("x1")).sendKeys(narration);// narration
  				Thread.sleep(2000);
  				driver.findElement(By.id("Gen")).click(); // Generate button
  				Thread.sleep(5000);
  				driver.findElement(By.id("Generate")).click();// yes button
  				Thread.sleep(2000);
  				driver.findElement(By.id("GenInv")).click(); // generate invoice
  																// button

  				 WebDriverWait wait = new WebDriverWait(driver,50);
  				wait.until(ExpectedConditions.alertIsPresent());
  				Alert alert = driver.switchTo().alert();
  				String message = alert.getText();
  				System.out.println(message);
  				alert.accept();
  				String splitmessage = message.split(" ")[2];
  				System.out.println("Maintanance fixed invoice no." + splitmessage);
  				Thread.sleep(2000);
  				String fixedvoucherno = splitmessage;
  				Thread.sleep(2000);
  				ldr.SearchVoucherno(driver,URL2,splitmessage);
  		  		return fixedvoucherno;
  }
      
     
	public void ReverseInvoice(WebDriver driver,String URL, String splitmessage,String dateid,String URL2) throws InterruptedException, BiffException, IOException {
    	driver.navigate().to(URL);
		Thread.sleep(4000);
		driver.findElement(By.id("Reversal")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("InvoiceNumber")).sendKeys(splitmessage);
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();// submit button
		Thread.sleep(4000);
		WebElement reversaltable = driver.findElement(By.id("Grid"));
		List<WebElement> rows1 = reversaltable.findElements(By.tagName("tr"));
		int rowscount = rows1.size();
		if (rowscount == 1) {
			System.out.println("Amount already paid for this voucher so reversal is not possible");
		} else {
			String migvoucher = driver.findElement(By.xpath(".//*[@id='1']/td[3]")).getText();
			if (migvoucher.equals(splitmessage)) {
				driver.findElement(By.id("jqg_Grid_1")).click();// click
																// checkbox
				Thread.sleep(4000);
				driver.findElement(By.xpath(".//*[@id='subform']/form/div[1]/div[3]/button")).click();// reverse
																										// button
				Thread.sleep(4000);
				setDate(driver,dateid,2016,10,6);
				driver.findElement(By.id("Reason")).sendKeys("reversing amount for Invoice" + splitmessage);
				Thread.sleep(4000);
				driver.findElement(By.id("Generate")).click(); // reverse button
																// after giving
																// reason
				Thread.sleep(4000);
				Alert alert2 = driver.switchTo().alert();
				Thread.sleep(4000);
				String message2 = alert2.getText();
				Thread.sleep(4000);
				message2 = message2.split(" ")[3];
				Thread.sleep(4000);
				System.out.println("Reversal maintanance invoice no. " + message2);
				Thread.sleep(2000);
				alert2.accept();
				Thread.sleep(2000);
				ldr.SearchVoucherno(driver,URL2, message2);
			
				}
		}
		}
		
	

	
	public String GenerateVariableInvoice(WebDriver driver,String URL,String Invoicedateid,String Duedateid,
			String apartment,String Block,String amount,String narration,String url2) throws InterruptedException, BiffException, IOException{
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Variable")).click();
		Thread.sleep(2000);
		setDate(driver,Invoicedateid,2016,10,2);
		Thread.sleep(2000);
		setDate(driver,Duedateid,2016,10,16);
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='INV']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
				.click(); // BlockID dropdown
		Thread.sleep(2000);
		driver.findElement(By.linkText(apartment)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='INV']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[3]/div/a/i"))
				.click(); // Apartment
		Thread.sleep(2000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).sendKeys(amount);// Basic amount
		Thread.sleep(2000);
		driver.findElement(By.id("Narration1")).sendKeys(narration);
		Thread.sleep(2000);
		driver.findElement(By.id("Gen")).click(); // generate button
		Thread.sleep(2000);
		driver.findElement(By.id("Generate")).click(); // Yes button
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
		driver.findElement(By.id("GenInv")).click();// generate button
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert1 = driver.switchTo().alert();
		Thread.sleep(2000);
		String message1 = alert1.getText();
		Thread.sleep(2000);
		System.out.println(message1);
		Thread.sleep(2000);
		alert1.accept();
		Thread.sleep(2000);
		String splitmessage = message1.split(" ")[2];
		Thread.sleep(2000);
		System.out.println("Generated Adhoc Invoice no: "   +  splitmessage);
		Thread.sleep(2000);
		String voucherno = splitmessage;
		ldr.SearchVoucherno(driver, url2, splitmessage);
		return voucherno;
	}

	public String GenerateFixedInvoicenoforscenario4(WebDriver driver,String URL,String Block,String Flat, String amount, String narration)
			throws InterruptedException, BiffException, IOException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Fixed")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[3]/div/a/i"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).sendKeys(amount);// basic amount
		Thread.sleep(2000);
		driver.findElement(By.id("x1")).sendKeys(narration);// narration
		Thread.sleep(2000);
		driver.findElement(By.id("Gen")).click(); // Generate button
		Thread.sleep(5000);
		driver.findElement(By.id("Generate")).click();// yes button
		driver.findElement(By.id("GenInv")).click(); // generate invoice button
		Thread.sleep(5000);
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		System.out.println(message);
		alert.accept();
		String splitmessage = message.split(" ")[2];
		System.out.println("For maintanance Invoice generated successfully");
		System.out.println("Maintanance fixed invoice no." + splitmessage);
		String fixedvoucherno = splitmessage;
		return fixedvoucherno;
	}

	public String GenerateFixedInvoiceforscenario4(WebDriver driver, String URL,String Block,String Flat, String amount, String narration)
			throws InterruptedException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Fixed")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
				.click();
		Thread.sleep(4000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[3]/div/a/i"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("BA1")).sendKeys(amount);// basic amount
		Thread.sleep(2000);
		driver.findElement(By.id("x1")).sendKeys(narration);// narration
		Thread.sleep(2000);
		driver.findElement(By.id("Gen")).click(); // Generate button
		Thread.sleep(5000);
		driver.findElement(By.id("Generate")).click();// yes button
		driver.findElement(By.id("GenInv")).click(); // generate invoice button
		Alert alert = driver.switchTo().alert();
		String message = alert.getText();
		System.out.println(message);
		alert.accept();
		String splitmessage = message.split(" ")[2];
		System.out.println("For maintanance Invoice generated successfully");
		System.out.println("Maintanance fixed invoice no." + splitmessage);
		String fixedvoucherno = splitmessage;
		return fixedvoucherno;
	}
	
	
	/*public void VoucherNo(WebDriver driver,String splitmessage) throws InterruptedException{
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(2000);
		driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/div[2]/div[1]/div/div/div/table/tbody/tr/td[3]/div/a/i")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Variable / Adhoc Invoice")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("searchgrid")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//option[contains(.,'Header Reference')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg2")).sendKeys(splitmessage);
		Thread.sleep(2000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(2000);
		System.out.println("Voucher no: " + driver.findElement(By.xpath(".//*[@id='1']/td[3]")).getText());
	}*/

	// data for fixed invoice generation in sheet 3
	@DataProvider(name = "testdata5")
	public Object[][] readexcel5() throws IOException, BiffException {
		File fs = new File("C:/Users/Swetha/Desktop/variabledata.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(3);
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

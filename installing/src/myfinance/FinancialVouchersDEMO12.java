package myfinance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class FinancialVouchersDEMO12 extends FinancialVouchersHelp{
	WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	
	
	
	int year = 2016;
	int month = 10;
	int day = 5;
	
	public void setDate(WebDriver driver) throws InterruptedException{
		//System.out.println("setDate");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date datespecified = c.getTime();
		driver.findElement(By.id("date")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("date")).sendKeys(df.format(datespecified));
		Thread.sleep(2000);
	}
   
	public void SelectTaxInProfile() throws InterruptedException, BiffException, IOException{
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("ServiceTaxPercentage");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		for (int x = 0; x < rows - 1; x++) {
			String url = inputdata[x][0];
			String percentage = inputdata[x][1];
		driver.navigate().to(url);
		driver.findElement(By.id("ServiceTax")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ServiceTaxPercent")).sendKeys(percentage);
		Thread.sleep(2000);
		driver.findElement(By.id("btnSave")).click();
		ldr.logout(driver);
		
		}
	}
	
	
}

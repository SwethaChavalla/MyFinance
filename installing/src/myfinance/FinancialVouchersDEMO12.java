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
   
	public void SelectTaxInProfile() throws InterruptedException{
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/SocietySetup/Step2/?Type=edit");
		driver.findElement(By.id("ServiceTax")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ServiceTaxPercent")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.id("btnSave")).click();
		ldr.logout(driver);
		
		
	}
	
	
}

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
	
	
	
	
	public void setDate(WebDriver driver,String id,int year,int month,int day) throws InterruptedException{
		//System.out.println("setDate");
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date datespecified = c.getTime();
		driver.findElement(By.id(id)).clear();
		Thread.sleep(2000);
		driver.findElement(By.id(id)).sendKeys(df.format(datespecified));
		Thread.sleep(2000);
	}
	
	}
	
	


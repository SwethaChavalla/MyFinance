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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BookingFacilityhelpdemo12 extends BookingFacilityHelper{
	//WebDriver driver = new FirefoxDriver();
		
	
	public void DueDate(WebDriver driver, String days){
		Select duedate =new Select(driver.findElement(By.id("TypeOfDueDate")));
		duedate.selectByIndex(2);
		driver.findElement(By.id("DueDate")).sendKeys(days);
	
	}
	
	
	public String ToVerifyGeneratedVoucherno(WebDriver driver) throws InterruptedException {
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(4000);
		driver.findElement(
				By.xpath("html/body/div[1]/div/div/div[1]/div[2]/div[1]/div/div/div/table/tbody/tr/td[3]/div/a/i"))
				.click();// voucher type
		Thread.sleep(4000);
		driver.findElement(By.linkText("Facility Booking")).click();
		Thread.sleep(4000);
		String FIGvoucherno = driver.findElement(By.xpath(".//*[@id='1']/td[6]")).getText();
		System.out.println("Generated Facility Booked Voucherno : " +FIGvoucherno);
		String duedate = driver.findElement(By.xpath(".//*[@id='1']/td[11]")).getText();
		System.out.println("Duedate to pay amount  " + duedate);
		String Voucherno = driver.findElement(By.xpath(".//*[@id='1']/td[3]")).getText();
		System.out.println("Voucher No: " + Voucherno );
		String clearedreference = driver.findElement(By.xpath(".//*[@id='1']/td[12]")).getText();
		System.out.println("By Clearing Voucher no.s:  " + clearedreference);
		driver.navigate().refresh();
		return FIGvoucherno;
			}
	
	
}

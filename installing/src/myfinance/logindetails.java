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
import org.testng.Assert;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class logindetails {
	String URL;
	String username;
	String password;
	

	public void userlogin(WebDriver driver, String username, String password) throws InterruptedException {
		driver.get("https://www.itsmyaccount.com/login/");
		Thread.sleep(2000);
		driver.findElement(By.id("UserName")).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.id("Password")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.id("TnC")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logsave")).click();
		Thread.sleep(2000);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://www.itsmyaccount.com/Home/MemberHomepage");
		System.out.println("Login Successfull");

	}

	public void adminlogin(WebDriver driver,String URL, String username, String password) throws InterruptedException {
		driver.get(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("UserName")).sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.id("Password")).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.id("TnC")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logsave")).click();
		Thread.sleep(2000);
		WebElement verification = driver.findElement(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li[2]/span/a"));
		String value = verification.getText();
		Assert.assertEquals(value, "Application");
		System.out.println("Login Successfull");
	}

	public void logout(WebDriver driver) {
		driver.findElement(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li[5]/span/a")).click();
	}
	
	public void Date(WebDriver driver) throws InterruptedException, BiffException, IOException{
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("Date");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows-1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i-1][j] = cl.getContents();
			}
		}
		
		for (int i = 0; i <rows-1; i++) {
			int year = Integer.parseInt(inputdata[i][0]);
			int month = Integer.parseInt(inputdata[i][1]);
			int day = Integer.parseInt(inputdata[i][2]);
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
	}

	public void forgotuserid(WebDriver driver) {
		driver.get("https://www.itsmyaccount.com/login/");
		driver.findElement(By.xpath(".//*[@id='body']/div/div/div/form/div[2]/div/div[4]/div/div[1]")).click();
		driver.findElement(By.id("email")).sendKeys("swethachavalla@gmail.com");
		driver.findElement(By.xpath(".//*[@id='body']/div/div/div/form/div[2]/div/div[3]/button")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public void forgotpassword(WebDriver driver) {
		driver.get("https://www.itsmyaccount.com/login/");
		driver.findElement(By.xpath(".//*[@id='body']/div/div/div/form/div[2]/div/div[4]/div/div[2]/a")).click();
		driver.findElement(By.id("UID")).sendKeys("SWETHA");
		driver.findElement(By.id("email")).sendKeys("swethachavalla@gmail.com");
		driver.findElement(By.xpath(".//*[@id='body']/div/div/div/form/div[2]/div/div[3]/button")).click();
	}
}

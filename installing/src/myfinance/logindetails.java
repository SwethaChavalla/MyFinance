package myfinance;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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

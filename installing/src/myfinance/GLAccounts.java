package myfinance;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class GLAccounts {
	public static void main(String[] args) throws InterruptedException{
	WebDriver driver = new FirefoxDriver();
	Thread.sleep(2000);
	 driver.get("http://test-itsmyaccount.azurewebsites.net/Login");
	 Thread.sleep(2000);
		driver.findElement(By.id("UserName")).sendKeys("demo_4");
		Thread.sleep(2000);
		driver.findElement(By.id("Password")).sendKeys("Welcome");
		Thread.sleep(2000);
		driver.findElement(By.id("TnC")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("logsave")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Application")).click();
		Thread.sleep(2000);
		driver.navigate().to("http://test-itsmyaccount.azurewebsites.net/Ledger");
		Thread.sleep(3000);
		driver.findElement(By.id("Add")).click();
		Thread.sleep(3000);
	driver.findElement(By.id("AccountName")).sendKeys("Service Tax25");//Account Name
	driver.findElement(By.id("Description")).sendKeys("Tax for the service used");//Narration
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[2]/div[2]/div[4]/input")).sendKeys("Income");
	WebElement button = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[3]/button[1]"));//save button.
	System.out.println(button);
	button.click();
	button.click();	
	WebElement element = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[3]/div[3]"));
	List <WebElement> collection= element.findElements(By.xpath("html/body/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[3]/div[3]"));//xpath of the table
	System.out.println(collection.size());
	}
	}

	
	
	
	

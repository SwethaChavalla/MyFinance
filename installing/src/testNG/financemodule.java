package testNG;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class financemodule {
	WebDriver driver = new FirefoxDriver();
	//String input = "Assets";
	@Test (priority=1)
	public void login() throws InterruptedException{
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
	}
	/*@Test (priority=2)
	public void dropdown() throws InterruptedException{
		WebElement select = driver.findElement(By.id("rows"));
		Thread.sleep(2000);
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options){
		if ("30".equals(option.getText())){
			option.click();
			Thread.sleep(3000);
				}
		}			
	}*/
	/*@Test (priority=2)
	public void pageno(){
		driver.findElement(By.xpath(".//*[@id='pagination']/li[4]/a")).click();
	}*/
	/*@Test (priority=3)
	public void Add() throws InterruptedException{
		driver.findElement(By.id("Add")).click();
		Thread.sleep(3000);
	driver.findElement(By.id("AccountName")).sendKeys("Service30");//Account Name
	Thread.sleep(3000);
	driver.findElement(By.id("Description")).sendKeys("Tax for the service used");//Narration
	Thread.sleep(3000);
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[2]/div[2]/div[4]/input")).sendKeys(input);;//ledger group
	WebElement button = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[3]/button[1]"));//save button.
	button.click();
	button.click();	
	         }*/
 /*@Test   (priority=4)
public void Edit() throws InterruptedException{
	WebElement checkbox = driver.findElement(By.id("jqg_Grid_26"));//checkbox
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("arguments[0].scrollIntoView()", checkbox);
	checkbox.click();
	Actions action = new Actions(driver);
	action.moveToElement(checkbox).click().perform();
	WebElement transaction = driver.findElement(By.xpath(".//*[@id='26']/td[8]"));//transaction
    String text = transaction.getText();
    Assert.assertEquals("No", text);
	driver.findElement(By.id("Edit")).click();
	Thread.sleep(3000);
 }
@Test (dependsOnMethods = {"Edit"})
public void  cleardata() throws InterruptedException{
	driver.findElement(By.id("AccountName")).clear();//Account Name
	Thread.sleep(3000);
	driver.findElement(By.id("AccountName")).sendKeys("Service25");//Account Name
	Thread.sleep(3000);
	driver.findElement(By.id("Description")).clear();//Narration
	Thread.sleep(3000);
	driver.findElement(By.id("Description")).sendKeys("Tax for the service used");//Narration
	Thread.sleep(3000);
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[2]/div[2]/div[4]/input")).clear();//ledger group
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[2]/div[2]/div[4]/input")).sendKeys(input);;//ledger group
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[3]/button[1]")).click();
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[3]/div/div/form/div/div[3]/button[1]")).click();
	
}*/

/*@Test (priority=5)
public void deletedata1() throws InterruptedException{ //Created from Account module 
	Thread.sleep(3000);
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[3]/div[3]/div/table/tbody/tr[7]/td[1]")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("Delete")).click();
	Thread.sleep(2000);
	Alert alert = driver.switchTo().alert();
	Thread.sleep(2000);
	alert.accept();
	Thread.sleep(2000);
	Alert alert1 = driver.switchTo().alert();
	Thread.sleep(2000);
	alert1.accept();
	Thread.sleep(2000);
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[3]/div[3]/div/table/tbody/tr[7]/td[1]")).click();
		}*/
/*@Test (priority=3)
public void deletedata2() throws InterruptedException{// not from Account module
	Thread.sleep(3000);
	WebElement checkbox1 = driver.findElement(By.id("jqg_Grid_27"));
	Thread.sleep(3000);
	Actions action = new Actions(driver);
	Thread.sleep(3000);
	action.moveToElement(checkbox1).click().build().perform();
	Thread.sleep(3000);
	driver.findElement(By.id("Delete")).click();
	Thread.sleep(3000);
	Alert alert1 = driver.switchTo().alert();
	Thread.sleep(3000);
	alert1.accept();
	Thread.sleep(3000);
	}*/
@Test (priority=2)
public void Export() throws InterruptedException{
	WebElement exportbutton= driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div/div/div/table/tbody/tr/td[5]/div/button"));//export button
	exportbutton.click();
	Thread.sleep(3000);
	//driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div[1]/div/div/div/table/tbody/tr/td[5]/div/ul/li[1]/a")).click();
	List <WebElement> select = exportbutton.findElements(By.tagName("option"));
	for (WebElement value : select){
		System.out.println("swetha");
			if("PDF".equals(value.getText())){
			value.click();
			
			
		}
	}
}
}


 
	


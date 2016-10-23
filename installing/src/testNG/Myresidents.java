package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import myfinance.logindetails;

public class Myresidents {
	WebDriver driver = new FirefoxDriver();
	  logindetails ldr = new logindetails();
    @Test(priority = 1, dataProvider = "logintestdata")
	  public void Login(String URL,String username, String password) throws InterruptedException {
		ldr.adminlogin(driver,URL, username, password);
		Thread.sleep(2000);
	}
  //ldr.forgotuserid(driver);
   	/*driver.findElement(By.id("ContactPopup")).click();
   	Thread.sleep(2000);
   WebElement em = driver.findElement(By.id("Email"));
   Thread.sleep(2000);
   String mil = em.getText();
   System.out.println(mil);
   Thread.sleep(2000);
   em.sendKeys("swethachavalla@gmail.com");
   Thread.sleep(2000);
*/

	/*@Test (priority=2)
	public void sendSMS() throws InterruptedException{
		driver.findElement(By.id("searchgrid")).click();
		WebElement search = driver.findElement(By.xpath(".//*[@id='fbox_Grid']/table/tbody/tr[3]/td[2]/select"));
		List <WebElement> select = search.findElements(By.tagName("option"));
		for (WebElement value: select){
			if ("Block Name".equals(value.getText())){
				value.click();
			}
		}
		driver.findElement(By.id("jqg1")).sendKeys("Block 3");// enter blockname
		driver.findElement(By.id("fbox_Grid_search")).click();// click on find button
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click();// click on close button
		driver.findElement(By.id("jqg_Grid_1")).click();// click the checkbox
		driver.findElement(By.id("SMS")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("Message")).sendKeys("Test messaage");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='SMSSnd']/div/div[3]/button")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
		System.out.println("SMS sent successfully");
		Scanner sms1 = new Scanner(System.in);
		System.out.println("Enter your choice");
		String choice = sms1.next();
		if (choice.equals("yes")){
			System.out.println("continue to send Email");
		}
		}
		
		Thread.sleep(2000);
		driver.findElement(By.id("EMAIL")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("Subject")).sendKeys("itsmyaccount");
		Thread.sleep(2000);
		driver.findElement(By.id("Message")).sendKeys("test message");
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='EmailSnd']/div/div[3]/button")).click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);*/
		
	
}


	
	


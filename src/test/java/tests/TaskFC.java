package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TaskFC {
 
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, 10);
		
		driver.get("https://trello.com/en");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		
		driver.findElement(By.xpath("//input[@id='user']")).sendKeys("rajeshmsty30@gmail.com");
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.urlContains("application"));
		driver.findElement(By.id("password")).sendKeys("Redpm34#pc");
		driver.findElement(By.id("login-submit")).click();
		
		driver.findElement(By.xpath("//span[text()='Create new board']")).click();
		
		driver.findElement(By.xpath("//div[text()='Board title']//following-sibling::input")).sendKeys("Trello Task");
		
		driver.findElement(By.xpath("//button[text()='Create']")).click();
		
		
		driver.findElement(By.name("name")).sendKeys("List A");
		driver.findElement(By.xpath("//input[@value='Add list']")).click();
		
		driver.findElement(By.name("name")).sendKeys("List B");
		driver.findElement(By.xpath("//input[@value='Add list']")).click();
		
		driver.findElement(By.xpath("//span[text()='Add a card']")).click();
		
		WebElement typefield =driver.findElement(By.xpath("//div[contains(@class,'list-card-details')]/textarea"));
		
		typefield.sendKeys("Card sample");
		typefield.sendKeys(Keys.ENTER);
		
		Actions a = new Actions(driver);
		
		WebElement cardInA = driver.findElement(By.xpath("//h2[text()='List A']//parent::div//following-sibling::div[contains(@class,'list-cards')]/a"));
		WebElement destinationB = driver.findElement(By.xpath("//h2[text()='List B']//parent::div//following-sibling::div[contains(@class,'card-composer-container')]"));
		
		a.dragAndDrop(cardInA, destinationB).build().perform();
		
		Point loc = driver.findElement(By.xpath("//h2[text()='List B']//parent::div//following-sibling::div[contains(@class,'list-cards')]//*[text()='Card sample']")).getLocation();
		System.out.println("Card is located at "+loc);
		
	}

}

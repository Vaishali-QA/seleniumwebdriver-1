package day1;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Flipkart {
	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		WebElement searchbox=driver.findElement(By.name("q"));
		searchbox.sendKeys("iphone 15 pro max");
		searchbox.submit();
		
		String mainPage= driver.getWindowHandle();
		System.out.println("Main Page="+mainPage);
		
		driver.findElement(By.xpath("//div[normalize-space()=\"Apple iPhone 15 Pro Max (Black Titanium, 512 GB)\"]")).click();
		
		Set<String> allpages=driver.getWindowHandles();
		for(String page: allpages) {
			if(!page.equals(mainPage))
			{
				driver.switchTo().window(page);
				break;
				
			}
		}
		
		System.out.println(driver.getCurrentUrl());
		
		
	}

}

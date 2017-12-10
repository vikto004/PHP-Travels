package com.PHPTravels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginAndLogout {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("http://phptravels.com/");
		Thread.sleep(2000);
		// get the current window handle
		String parentHandle = driver.getWindowHandle(); 

		//Click on login link which will open new window
		driver.findElement(By.className("login")).click();
				
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's the newly opened window)
		}
		Thread.sleep(4000);
		//Verify new window title
		String actualTitle = driver.getTitle();
		String expectedTitle= "Client Area - PHPTRAVELS";
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			System.out.println("Page Title Matched");
		else
			System.out.println("Title doesn't match");
		//Input login criteria
		driver.findElement(By.id("inputEmail")).sendKeys("eli4063@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("Php3303!");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(2000);
		//Log out
		driver.findElement(By.linkText("Hello, Ilya!")).click();
		driver.findElement(By.linkText("Logout")).click();
		
		// close newly opened window
		driver.close(); 
		 //switch back to the original window
		driver.switchTo().window(parentHandle);
		driver.close();
	}
}

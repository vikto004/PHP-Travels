package com.PHPTravels;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegisterandCheckout {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get("http://phptravels.com/");
		Thread.sleep(2000);
		driver.findElement(By.linkText("Order")).click();
		//Scroll Page down
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1200)", "");
		
		//Select "Desktop application"
		Thread.sleep(3000);
		
		// get the current window handle
		String parentHandle = driver.getWindowHandle(); 
		//Click on link that will open new window
		driver.findElement(By.cssSelector("div.col-md-4:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > form:nth-child(1) > button:nth-child(1)")).click();

		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
		Thread.sleep(4000);
		//Verify new window title
		String actualTitle = driver.getTitle();
		String expectedTitle= "Shopping Cart - PHPTRAVELS";
		if(actualTitle.equalsIgnoreCase(expectedTitle))
			System.out.println("Home page Title Matched");
		else
			System.out.println("Title doesn't match");
		
		//Confirm that the delete button is present and also indirectly confirms there is at least one item in cart
		if(driver.findElement(By.linkText("Remove"))!= null){
			System.out.println("Delete button is present and at leaste one item in the cart");
		}else{
			System.out.println("NO ITEMS IN THE CART");
		}		
		// close newly opened window
		driver.close(); 
		// switch back to the original window
		driver.switchTo().window(parentHandle);
		driver.close();
	}
}

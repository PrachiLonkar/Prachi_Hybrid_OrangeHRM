package com.technocredits.orghrm.base;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.technocredits.orghrm.constant.ConstantPath;

public class PredefinedActions {
	static protected WebDriver driver;
	static private WebDriverWait wait;
	
	static public void start() {
		 start("https://pvlonkar-trials71.orangehrmlive.com/");
	}
	
	static public void start(String url) {
		System.setProperty(ConstantPath.CHROMEDRIVER, ConstantPath.CHROMEDRIVEREXE);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		wait = new WebDriverWait(driver, ConstantPath.AVGWAIT);
	}
	
	protected WebElement getElement(String locatorType, String locator, boolean isWaitRequired) {
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		switch(locatorType.toUpperCase()) {
			case "XPATH" :
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				else
					element = driver.findElement(By.xpath(locator));
				break;
			
			case "ID" :
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
				else
					element = driver.findElement(By.id(locator));
				break;
			
			default :
				element = null;
				System.out.println("Invalid LocatorType");
		}
		return element;
	}
	
	
	
	
	protected List<WebElement> getAllElements(String locatorType, String locator, boolean isWaitRequired) {
		List<WebElement> element;
		switch(locatorType.toUpperCase()) {
			case "XPATH" :
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
				else
					element = driver.findElements(By.xpath(locator));
				break;
			
			case "ID" :
				if(isWaitRequired)
					element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(locator)));
				else
					element = driver.findElements(By.id(locator));
				break;
			
			default :
				element = null;
				System.out.println("Invalid LocatorType");
		}
		return element;
	}
	
	
	
	
	protected void scrollToElement(WebElement element) {
		if(!element.isDisplayed()) {
			JavascriptExecutor je = (JavascriptExecutor)driver;
			je.executeScript("arguments[0].scrollIntoView(true)", element);
		}
	}
	
	
	protected boolean isElementDisplayed(WebElement element) {
		if(element.isDisplayed()) {
			return true;
		}else {
			scrollToElement(element);
			return element.isDisplayed();
		}
	}
	
	
	protected boolean isElementDisplayed(String locatorType, String locator, boolean isWaitRequired) {
		try {
			WebElement element = getElement(locatorType, locator, isWaitRequired);
			return isElementDisplayed(element);
		}catch(Exception e) {
			return false;
		}
	}
	
	
	protected String getElementText(WebElement element) {
		scrollToElement(element);
		return element.getText();
	}
	
	protected String getElementText(String locatorType, String locator, boolean isWaitRequired) {
		return getElement(locatorType, locator, isWaitRequired).getText();
	}
	
	protected void enterText(String locatorType, String locator, boolean isWaitRequired, String text) throws Exception {
		WebElement element = getElement(locatorType, locator, isWaitRequired);
		if(element.isEnabled())
			element.sendKeys(text);
		else
			throw new Exception("Element was not enabled for LocatorType - " + locatorType + " and Locator " + locator);
	}
	
	protected void clickOnElement(String locatorType, String locator, boolean isWaitRequired) {
		WebElement element = getElement(locatorType, locator, isWaitRequired);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click(); 
	}
	
	protected String getElementAttribute (String locatorType, String locator, boolean isWaitRequired, String attribute) {
		WebElement element = getElement(locatorType, locator, isWaitRequired);
		return element.getAttribute(attribute);
	}
	
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	static public void tearDown() {
		driver.close();
	}
}

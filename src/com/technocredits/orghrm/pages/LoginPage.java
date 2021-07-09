package com.technocredits.orghrm.pages;

//import org.openqa.selenium.By;

import com.technocredits.orghrm.base.PredefinedActions;

public class LoginPage extends PredefinedActions{

	public boolean isLogoDisplayed() {
		return isElementDisplayed("id", "divLogo", true);
	}
	
	public boolean isLoginPanelDisplayed() throws InterruptedException {
		Thread.sleep(1000);
		return isElementDisplayed("id", "logInPanelHeading", true);
		// System.out.println(isElementDisplayed("xpath", "//div[text()='LOGIN Panel']", true));
	}
	
	public DashboardPage doLogin(String username, String password) throws Throwable {
		enterUsername(username);
		enterPassword(password);
		return clickOnLoginButton();
	}
	
	public LoginPage enterUsername(String username) throws Throwable{
		enterText("xpath", "//input[@id='txtUsername']", true, username);
		return this;
	}
	
	public LoginPage enterPassword(String password) throws Throwable{
		enterText("xpath","//input[@name='txtPassword']",true, password);
		return this;
	}
	
	public DashboardPage clickOnLoginButton() {
		clickOnElement("xpath", "//input[@type='submit']", true);
		return new DashboardPage();
	}
}

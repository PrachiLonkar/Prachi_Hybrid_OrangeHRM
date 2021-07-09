package com.technocredits.orghrm.testscripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.Test;

import com.technocredits.orghrm.base.PredefinedActions;
import com.technocredits.orghrm.pages.DashboardPage;
import com.technocredits.orghrm.pages.LoginPage;

public class LoginTest {

	@Test
	public void loginTest() throws Throwable {
		System.out.println("STEP- Launch browser and Load URL");
		PredefinedActions.start();
		LoginPage loginPage = new LoginPage();
		System.out.println("VERIFY - Logo is dispalyed on login page");
		Assert.assertFalse(loginPage.isLogoDisplayed());
				
		System.out.println("VERIFY - LoginPanel is displayed");
		Assert.assertTrue(loginPage.isLoginPanelDisplayed(), "LoginPanel was not displayed on login page.");
		//loginPage.isLoginPanelDisplayed();
		
		System.out.println("STEP - Login with valid credentials");
		DashboardPage dashboardPage = loginPage.doLogin("Admin", "Igx4@R5TJj");
		System.out.println("VERIFY- Dashboard Heading is Displayed ");
		Assert.assertTrue(dashboardPage.isDashboardDisplayed(),"Dashboard heading not displayed after login");

		boolean isDashboardSelecedByDefault = dashboardPage.isDashboardSelectedAsDefaultMenu();
		Assert.assertTrue(isDashboardSelecedByDefault, "Dashboard was not selected as default menu");
		
		List<String> actualWidgetTextList = dashboardPage.getAllWidgetTitle();
		Assert.assertEquals(7, actualWidgetTextList.size(),"Some menu is missing");
		
		List<String> expectedWidgetTextList = new ArrayList<String>();
		expectedWidgetTextList.add("Quick Access");
		expectedWidgetTextList.add("My Actions");
		expectedWidgetTextList.add("Employees on Leave Today");
		expectedWidgetTextList.add("Time At Work");
		expectedWidgetTextList.add("Latest News");
		expectedWidgetTextList.add("Latest Documents");
		expectedWidgetTextList.add("Performance Quick Feedback");
		
		Collections.sort(actualWidgetTextList);
		Collections.sort(expectedWidgetTextList);
		
		/*for(String widgetText : expectedWidgetTextList) {
			Assert.assertTrue(actualWidgetTextList.contains(widgetText));
		}*/
		
		Assert.assertEquals(actualWidgetTextList, expectedWidgetTextList, "non matching widget text - "+expectedWidgetTextList.removeAll(actualWidgetTextList));
	}
}

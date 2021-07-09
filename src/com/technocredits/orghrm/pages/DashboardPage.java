package com.technocredits.orghrm.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.technocredits.orghrm.base.PredefinedActions;

public class DashboardPage extends PredefinedActions{
	
	public boolean isDashboardDisplayed() {
		return isElementDisplayed("xpath", "//li[@class='page-title']", true);
	}

	public boolean isDashboardSelectedAsDefaultMenu() {
		return getCurrentUrl().endsWith("dashboard");
	}
	
		
	public List<String> getAllWidgetTitle(){
		List<WebElement> list = getAllElements("xpath", "//div[@class='widget-header']/span[2]", true);
		List<String> menuTextList = new ArrayList<String>();
		for(WebElement element : list) {
			menuTextList.add(element.getText());
		}
		return menuTextList;
	}
	
	
}

package com.technocredits.orghrm.pages;

import com.technocredits.orghrm.base.PredefinedActions;

public class MyInfo extends PredefinedActions{
	public void navigateToPersonalDetails() {
		clickOnElement("xpath", "//a[text()='Persona Details']", true);
		//return new PersonalDetailsPage();
	}

	public void navigateToJob() {
		//clickOm
	}
}

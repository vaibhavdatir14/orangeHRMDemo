/**
 * 
 */
package com.orengeHRM.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

/**
 * @author Vaibhav
 *
 */
public class Dashboard extends BaseClass {

	@FindBy(linkText = "PIM")
	WebElement PIMLink;

	@FindBy(xpath = "//p[contains(.,'Quick Launch')]")
	WebElement QuickLaunch;

	@FindBy(xpath = "//*[@class='oxd-grid-3 orangehrm-quick-launch']")
	// WebElement countOptions;

	// to count the no. of options
	List<WebElement> countOptions;

	public Dashboard() {

		PageFactory.initElements(driver, this);

	}

	public boolean quickLaunchAvailable() {

		return Action.isDisplayed(driver, QuickLaunch);
	}

	public int quickLaunchCount() {

		return countOptions.size();
	}
	
	public void quickLaunchList() {
		
		for(WebElement option : countOptions) {
		
			System.out.println("List of Options are" + option.getText());
		}
	}

	public PIM_EmpList navigateToPIM() {
		
		Action.click(driver, PIMLink);
		return new PIM_EmpList();

	}

	public String getCurrentURL() {
		String dashboardURL = Action.getCurrentURL(driver);
		return dashboardURL;
	}

}

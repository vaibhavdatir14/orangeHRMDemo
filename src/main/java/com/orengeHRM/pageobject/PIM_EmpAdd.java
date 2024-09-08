/**
 * 
 */
package com.orengeHRM.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

/**
 * @author Vaibhav
 *
 */
public class PIM_EmpAdd extends BaseClass	{
	
	PIM_EmpAdd empadd;
	
	@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title'][contains(.,'Personal Details')]")
	WebElement personalDetailsEmployeePageMessage;
	
	@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
	WebElement addEmployeeMessage;
	
	public PIM_EmpAdd() {

		PageFactory.initElements(driver, this);

	}
	
	public String addPagePersonalDetailsTitle() {
		
		Action.isDisplayed(driver, personalDetailsEmployeePageMessage);
		
		String pageTitle = personalDetailsEmployeePageMessage.getText();
		
		return pageTitle;
	}
	
	public String addEmployeePageTitle() {
		
		Action.isDisplayed(driver, addEmployeeMessage);
		
		String pageTitle = addEmployeeMessage.getText();
		
		return pageTitle;
	}
 

}

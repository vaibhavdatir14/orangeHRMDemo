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
public class ResetPassword extends BaseClass {
	
	@FindBy(xpath = "//button[@type='button']")
	WebElement cancelButton;
	
	@FindBy(xpath = "//h6[contains(.,'Reset Password')]")
	WebElement ResetPasswordTitle;
	

public ResetPassword() {
	
	PageFactory.initElements(driver, this);
	
}

public Login cancelForgotPassword() {
	
	Action.click(driver, cancelButton);
	
	return new Login();
	
}

public boolean verifyResetPassPage() {
	
	return Action.isDisplayed(driver, ResetPasswordTitle);
}

}

/**
 * @author Vaibhav Datir
 *
 */

package com.orengeHRM.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

public class Login extends BaseClass {
	
	
	// Created a constructor to initialize all page object, this keyword use to initialize all object.

		public Login() {
			
			PageFactory.initElements(driver, this); // Initialize WebElements
			
		}
		

	@FindBy(name="username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement Password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;

	@FindBy(xpath = "//p[contains(.,'Forgot your password?')]")
	WebElement forgotPassword;

	@FindBy(xpath = "//p[contains (.,'Invalid credentials')]")
	WebElement invalidMsg;
	
	@FindBy(xpath = "//h5[contains(.,'Login')]")
	WebElement loginMessage;


	public Dashboard validLogin(String uname, String pwd) {
		
		Action.sendKeys(userName, uname);
		Action.sendKeys(Password, pwd);
		Action.click(driver, loginButton);
		
		return new Dashboard();

	}

	public boolean invalidLogin(String InvName, String InvPwd) {

		Action.sendKeys(userName, InvName);
		Action.sendKeys(Password, InvPwd);
		Action.click(driver, loginButton);
		return Action.isDisplayed(driver, invalidMsg);
	//	return invalidMsg.isDisplayed();

	}
	
		public boolean forgotPassAvailable() {
		return Action.isDisplayed(driver, forgotPassword);

	}

		public ResetPassword resetPassword() {
		Action.click(driver, forgotPassword);
		return new ResetPassword();
	}
		
		public boolean loginMessage() {
			
			return Action.isDisplayed(driver, loginMessage);
		}

	
}


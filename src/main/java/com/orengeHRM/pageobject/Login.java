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

	// Created a constructor to initialize all page object, this keyword use to
	// initialize all object.

	public Login() {

		PageFactory.initElements(driver, this); // Initialize WebElements

	}

	@FindBy(name = "username")
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

	@FindBy(xpath = "//*[contains(.,'Required')]")
	WebElement credentialBlank;

	public Dashboard verifyLogin(String uname, String pwd) {

		Action.sendKeys(userName, uname);
		Action.sendKeys(Password, pwd);
		Action.click(driver, loginButton);

		return new Dashboard();

	}

	public String invalidLogin() {

		Action.isDisplayed(driver, invalidMsg);
		String invalidmsg = invalidMsg.getText();
		return invalidmsg;
	}

	public String blankCredentials() {

		Action.isDisplayed(driver, credentialBlank);
		String requiredmsg = credentialBlank.getText();
		return requiredmsg;

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

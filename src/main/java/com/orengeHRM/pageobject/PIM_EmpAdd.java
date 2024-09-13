package com.orengeHRM.pageobject;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

public class PIM_EmpAdd extends BaseClass {

	private static final String String = null;

	PIM_EmpAdd empadd;

	@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
	WebElement addEmployeeMessage;

	@FindBy(name = "firstName")
	WebElement firstName;

	@FindBy(name = "middleName")
	WebElement middleName;

	@FindBy(name = "lastName")
	WebElement lastName;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;

	@FindBy(xpath = "//button[@type='button'][text()=' Cancel ']")
	WebElement cancelButton;

	@FindBy(xpath = "oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message")
	WebElement empIDAleradyexistMessage;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
	WebElement employeeIDField;

	@FindBy(xpath = "//*[@class='oxd-icon bi-plus']")
	WebElement addPhoto;

//	@FindBy(id = "oxd-toaster_1")
	@FindBy(xpath = "//*[@class='oxd-toast-content oxd-toast-content--success']")
	WebElement toaster;

	@FindBy(xpath = "//*[@class='oxd-switch-wrapper']")
	WebElement loginDetailsSwitchButton;

	@FindBy(xpath = "(//input[@autocomplete='off'])[1]")
	WebElement loginDetailsUserName;

	@FindBy(xpath = "(//input[@autocomplete='off'])[2]")
	WebElement loginDetailsPassword;

	@FindBy(xpath = "(//input[@autocomplete='off'])[3]")
	WebElement loginDetailsConfirmPassword;

	@FindBy(xpath = "//*[text()='Username already exists']")
	WebElement loginEmpExist;

	@FindBy(xpath = "//*[text()='Attachment Size Exceeded']")
	WebElement PhotoSizeExceed;

	@FindBy(xpath = "//*[text()='File type not allowed']")
	WebElement invalidFileType;

	public PIM_EmpAdd() {

		PageFactory.initElements(driver, this);

	}

	public String addEmployeePageTitle() {

		Action.isDisplayed(driver, addEmployeeMessage);

		String pageTitle = addEmployeeMessage.getText();

		return pageTitle;
	}

	public String enterEmpFullName(String FirstName, String MiddleName, String LastName) {

		Action.sendKeys(firstName, FirstName);
		Action.sendKeys(middleName, MiddleName);
		Action.sendKeys(lastName, LastName);

		return String;
	}

	public boolean validateEmpID() {

		return Action.isDisplayed(driver, empIDAleradyexistMessage);
	}

	public String enterEmpID(String EmpID) {

		Action.sendKeys(employeeIDField, EmpID);

		return EmpID;
	}

	public void addPhoto(String exeLink) throws IOException, InterruptedException {

		Action.click(driver, addPhoto);
		Thread.sleep(2000);
		Runtime.getRuntime().exec(exeLink);
	}

	public PIM_EmpList cancelEmployee() {

		Action.click(driver, cancelButton);
		return new PIM_EmpList();
	}

	public PIM_EmpList saveEmployee() {

		Action.click(driver, saveButton);

		return new PIM_EmpList();
	}

	public String toasterSuccessMessage(boolean toast) {

		WebElement toasterElement = toaster;

		if (toasterElement.isDisplayed()) {
			// Capture and return the text from the toaster message
			String toasterText = toaster.getText().trim();
//			System.out.println("Toaster message: " + toasterText);

			return toasterText;
		}

		// Dont want to return anything if toaster not display
		return "";

	}

	public void loginDetails(String uname, String password, String confirmpassword) {

		Action.click(driver, loginDetailsSwitchButton);
		Action.sendKeys(loginDetailsUserName, uname);
		Action.sendKeys(loginDetailsPassword, password);
		Action.sendKeys(loginDetailsConfirmPassword, confirmpassword);

	}

	public boolean empAlreadyExistMsg() {

		return Action.isDisplayed(driver, loginEmpExist);
	}

	public boolean validatePhotoSize() {

		return Action.isDisplayed(driver, PhotoSizeExceed);
	}

	public boolean invalidFileType() {

		return Action.isDisplayed(driver, invalidFileType);
	}
}
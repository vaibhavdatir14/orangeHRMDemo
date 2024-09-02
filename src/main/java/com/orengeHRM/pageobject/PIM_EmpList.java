
/**
 * @author Vaibhav
 *
 */

package com.orengeHRM.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

public class PIM_EmpList extends BaseClass {

	@FindBy(xpath = "//button[@type='reset']")
	WebElement ResetButton;

//	@FindBy(xpath = "//span[contains(.,'No Records Found')]")
	@FindBy(xpath = "//*[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span")
	WebElement recordTableMessage;

	@FindBy(id = "oxd-toaster_1")
	WebElement NoRecordToasterMessage;

	@FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
	WebElement enterEmpNameInSerach;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement SearchButton;

	@FindBy(xpath = "//*[@class='oxd-table-row oxd-table-row--with-border']")
	WebElement TableRowCount;

	@FindBy(xpath = "//*[@class='oxd-table-row oxd-table-row--with-border']/div")
	WebElement TablecolCount;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	WebElement EnterIDinSearch;

	@FindBy(xpath = "//*[@class='oxd-select-text oxd-select-text--active'][@xpath='1']")
	WebElement EmpStatusDropDown;

	@FindBy(xpath = "//*[@class='oxd-icon-button oxd-table-cell-action-space'][@type='button'][1]")
	WebElement EditEmpButton;

	@FindBy(xpath = "//*[@class='oxd-icon-button oxd-table-cell-action-space'][@type='button'][2]")
	WebElement DeleteEmpButton;

	@FindBy(xpath = "//button[contains(.,'No, Cancel')]")
	WebElement DeleteEmpCancelButton;

	@FindBy(xpath = "//button[contains(.,' Yes, Delete')]")
	WebElement DeleteEmpConfirmButton;

	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
	WebElement AddEmpButton;

	//Page factory method initialization
	
	public PIM_EmpList() {

		PageFactory.initElements(driver, this);

	}

	// Entering some data in the Name field
	
	public String enterDataInName(String EnterEmpName) {

		Action.sendKeys(enterEmpNameInSerach, EnterEmpName);
		return EnterEmpName;

	}

	// Get data from the Name field
	
	public String getDataFromName() {

		try {
			String data = Action.getTextbyAttribute(driver, enterEmpNameInSerach);
			return (data != null && !data.isEmpty()) ? data : null; // Return null if data is empty
		} catch (Exception e) {
			System.err.println("Error retrieving data from the name field: " + e.getMessage());
			return null;
		}
	}
	
	// Entering some data in the Name field
	
	public String getDataFromID() {

		try {
			String data = Action.getTextbyAttribute(driver, EnterIDinSearch);
			return (data != null && !data.isEmpty()) ? data : null; // Return null if data is empty
		} catch (Exception e) {
			System.err.println("Error retrieving data from the ID field: " + e.getMessage());
			return null;
		}
	}
	
	// Entering some data in the Name field
	public String getDataFromStatus(int integer) {

		Action.selectByIndex(EmpStatusDropDown, integer );
		try {
			String data = Action.getText(driver, EmpStatusDropDown);
			return (data != null && !data.isEmpty()) ? data : null; // Return null if data is empty
		} catch (Exception e) {
			System.err.println("Error retrieving data from the ID field: " + e.getMessage());
			return null;
		}
	}

	// This method is Reset search
	public PIM_EmpList resetSearch() {

		try {
			Action.click(driver, ResetButton);
		} catch (Exception e) {
			System.err.println("Error performing reset search: " + e.getMessage());
		}

		return this;
	}

	public void searchButton() {

		Action.click(driver, SearchButton);
	}

	// This method show search result found message
	public String toasterSearchRecordMessage(boolean toaster) {
		
		WebElement toasterElement = NoRecordToasterMessage;
		
       		if (toasterElement.isDisplayed()) {
	            // Capture and return the text from the toaster message
	            String toasterText = toasterElement.getText().trim();
	            System.out.println("Toaster message: " + toasterText);
	            return toasterText;
	        } else {
	            System.out.println("Toaster message is not displayed.");
	            return null; // Return null or an empty string if the toaster is not displayed
	        }
	}

	public String tableSearchRecordMessage() {

		Action.scrollByVisibility(driver, recordTableMessage);

		String table = recordTableMessage.getText();
/*		if (table.contains("Record Found")) {
			System.out.println("Record Found");
		} else if (table.contains("No Records Found")) {
			System.out.println("No record found");
		}
*/
		return table;

	}

	public void empSearchByName(String EnterEmpName) {

		Action.sendKeys(enterEmpNameInSerach, EnterEmpName);
		Action.click(driver, SearchButton);
		Action.getRowCount(TableRowCount);
		Action.getColumncount(TablecolCount);

	}

	public void empSearchByID(String EnterID) {

		Action.sendKeys(EnterIDinSearch, EnterID);
		Action.click(driver, SearchButton);
		Action.getRowCount(TableRowCount);
		Action.getColumncount(TablecolCount);

	}

	public void empSearchbyStatus(int index) {

		Action.selectByIndex(EmpStatusDropDown, index);
		Action.click(driver, SearchButton);
		Action.isDisplayed(driver, recordTableMessage);
		Action.getRowCount(TableRowCount);
		Action.getColumncount(TablecolCount);
	}

	public String empUrl() {
		String EmployeePageURL = Action.getCurrentURL(driver);
		return EmployeePageURL;
	}

}

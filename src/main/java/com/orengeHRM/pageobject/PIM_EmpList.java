
/**
 * @author Vaibhav
 *
 */

package com.orengeHRM.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

public class PIM_EmpList extends BaseClass {

	PIM_EmpList emplist;

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

	@FindBy(xpath = "//*[@class='oxd-table-card']/div")
	List<WebElement> TableRowCount;

	@FindBy(xpath = "//*[@class='oxd-table-card'][1]/div/div")
	List<WebElement> TableColCount;

	@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
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

	// Page factory method initialization

	public PIM_EmpList() {

		PageFactory.initElements(driver, this);

	}

	// Entering some data in the Name field

	public String enterDataInName(String EnterEmpName) {

		Action.sendKeys(enterEmpNameInSerach, EnterEmpName);
		return EnterEmpName;

	}

	// Entering some data in the ID field

	public String enterDataInID(String EnterEmpID) {

		Action.sendKeys(EnterIDinSearch, EnterEmpID);
		return EnterEmpID;

	}

	// Select option from dropdown & get selected option

	public int selectFromStatus(int integer) {

		Action.selectByIndex(EmpStatusDropDown, integer);
		return integer;

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

	// Get data from the ID field

	public String getDataFromID() {

		try {
			// Alternative: Using JavaScript to get the value
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String ID = (String) js.executeScript("return arguments[0].value;", EnterIDinSearch);
			System.out.println("Employee ID using JavaScript: " + ID);
			// String ID = Action.getTextbyAttribute(driver, EnterIDinSearch);
			return (ID != null && !ID.isEmpty()) ? ID : null; // Return null if data is empty
		} catch (Exception e) {
			System.err.println("Error retrieving data from the ID field: " + e.getMessage());
			return null;
		}
	}

	// Get data from the Status field

	public String getSelectedStatus(String data) {

		try {
			data = Action.getText(driver, EmpStatusDropDown);
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

	// This method is use to get record available or not message on table.
	public String tableSearchRecordMessage() {

		Action.scrollByVisibility(driver, recordTableMessage);

		String tablemessage = recordTableMessage.getText();

		return tablemessage;

	}

/*	// This method is use to get no of record shows in table.
	public int noOfTableSearchRecord() {

		Action.scrollByVisibility(driver, recordTableMessage);

		String noOfRecords = recordTableMessage.getText();

		int record = Integer.parseInt(noOfRecords);

		System.out.println(record);

		return record;

	}
*/

	public String countTableData() {

		List<WebElement> rows = TableRowCount;
		List<WebElement> columns = TableColCount;
		System.out.println("No. of Rows : " + rows.size());
		System.out.println("No. of Columns : " + columns.size());

		for (int r = 1; r <= rows.size(); r++) {
			for (int c = 1; c <= columns.size() - 1; c++) {

				String rowdata = driver.findElement(By.xpath("//*[@class='oxd-table-card'][" + r + "]/div/div[" + c + "]")).getText()+ "      ";
				System.out.print(rowdata);
			}
			
			System.out.println();

		}

		// I want a return type rowcount to compare thats why i convert integer row
		// count to String
		String rowscount = Integer.toString(rows.size());
		return rowscount;

	}
	
	public String getNameFromTable(String empName) {

		List<WebElement> rows = TableRowCount;
		List<WebElement> columns = TableColCount;
		System.out.println("No. of Rows : " + rows.size());
		System.out.println("No. of Columns : " + columns.size());

		
		boolean flag = false;
		
		for (int r = 1; r <= rows.size(); r++) {
		//	for (int c = 1; c <= columns.size() - 1; c++) {

				String rowdata = driver.findElement(By.xpath("//*[@class='oxd-table-card'][" + r + "]/div/div[3]")).getText()+ "      ";
				System.out.print(rowdata);
				
				if(rowdata.equals(empName)) {
					flag = true;
					System.out.println(rowdata);
					break;
				}
			}
			
			System.out.println();

	//	}

		return empName;

	}

	public String empSearchByName(String EnterEmpName) {

		emplist = new PIM_EmpList();

		Action.sendKeys(enterEmpNameInSerach, EnterEmpName);
		Action.click(driver, SearchButton);
		emplist.countTableData();
		return EnterEmpName;

	}

	public String empSearchByID(String EnterID) {

		emplist = new PIM_EmpList();
		Action.sendKeys(EnterIDinSearch, EnterID);
		Action.click(driver, SearchButton);
		emplist.countTableData();
		return EnterID;

	}

	public int empSearchbyStatus(int index) {

		emplist = new PIM_EmpList();
		Action.selectByIndex(EmpStatusDropDown, index);
		Action.click(driver, SearchButton);
		emplist.countTableData();
		return index;
	}

	public String empUrl() {
		String EmployeePageURL = Action.getCurrentURL(driver);
		return EmployeePageURL;
	}


	public WebElement deleteRecord() throws InterruptedException {
		
		Action.click(driver, DeleteEmpButton);
		Action.click(driver, DeleteEmpConfirmButton);
		Thread.sleep(1500);
		PIM_EmpList emplist = new PIM_EmpList();
		emplist.toasterSearchRecordMessage(true);
		
		return NoRecordToasterMessage;
		
	}

	public PIM_EmpAdd editEmployeePage() {
		
		Action.click(driver, EditEmpButton);
		
		return new PIM_EmpAdd();
	}
	
	public PIM_EmpAdd employeeAdd() {
		
		Action.click(driver, AddEmpButton);
		
		return new PIM_EmpAdd();
	}
}

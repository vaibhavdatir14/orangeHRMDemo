package com.orengeHRM.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.PIM_EmpAdd;
import com.orengeHRM.pageobject.PIM_EmpList;
import com.orengeHRM.utility.Log;

public class AddEmployeeTest extends BaseClass{
	
	Login login;
	Dashboard dashboard;
	PIM_EmpList emplist;
	PIM_EmpAdd empadd;
	
	
	@Test
	public void addEmpWithoutLoginDetails() throws IOException, InterruptedException {
		
		Log.startTestCase("addEmpWithoutLoginDetails");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();
		
		Log.info("User entering credentials to login");
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("User Navigating on PIM page");
		emplist = dashboard.navigateToPIM();
		
		Log.info("Clicking on Add button to add new employee");
		empadd = emplist.employeeAddButton();
		
		Log.info("Entering employee full name");
		empadd.enterEmpFullName("Sam", "Joseph", "Landwood");
		
		Log.info("Uploading employee profile photo");
		empadd.addPhoto("/OrengeHRM/TestData/OrangeHRM_fileUpload.exe");
		empadd.saveEmployee();		
		Log.info("Verify success message to confirm employee added");
		String successmessage = empadd.toasterSuccessMessage(true);
		
		if(empadd.validateEmpID()) {
						
			System.out.println("Employee ID is already exist, please try another Employee ID");
			
		}
		else {
			
			System.out.println(successmessage);
		}
		Log.endTestCase("addEmpWithoutLoginDetails");
	}
	
	
	@Test
	public void addEmpWithLoginDetails() throws IOException, InterruptedException {
		
		Log.startTestCase("addEmpWithLoginDetails");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();
		
		Log.info("User entering credentials to login");
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("User Navigating on PIM page");
		emplist = dashboard.navigateToPIM();
		Log.info("Clicking on Add button to add new employee");
		empadd = emplist.employeeAddButton();
		
		Log.info("Entering employee full name");
		empadd.enterEmpFullName("Sam", "Joseph", "Landwood");
		
		Log.info("Uploading employee profile photo");
		empadd.addPhoto("C:/Users/Admin/selenium_practice/OrengeHRM/TestData/OrangeHRM_fileUpload.exe");
		
		Log.info("Switching Login details and entering details");
		empadd.loginDetails("pranu1", "abcd123", "abcd123");
		
		emplist=empadd.saveEmployee();		
		
		Log.info("Verify success message to confirm employee added");
		String successmessage = empadd.toasterSuccessMessage(true);
		
		Log.info("Validate employee ID already exist or not");
		if(empadd.validateEmpID()) {
//			empadd.enterEmpID("500124");
//			empadd.saveEmployee();	
			
			System.out.println("Employee ID is already exist, please try another Employee ID");
		}
		
		else if(empadd.empAlreadyExistMsg()) {
			Log.info("Validate employee name already exist or not");
			System.out.println("Employee name is already exist, please try another Employee Name");
		}
		else {
			
			System.out.println(successmessage);
			
		}
		Log.endTestCase("addEmpWithLoginDetails");
	}
	
	@Test
	public void cancelAddEmployee() throws InterruptedException {
		
		Log.startTestCase("cancelAddEmployee");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();
		
		Log.info("User entering credentials to login");
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("User Navigating on PIM page");
		emplist = dashboard.navigateToPIM();
		
		Log.info("Clicking Add button to add employee");
		empadd = emplist.employeeAddButton();
		Thread.sleep(2000);
		Log.info("Clicking Cancel button to cancel add employee operation");
		emplist = empadd.cancelEmployee();
		
		String acturl = dashboard.getCurrentURL();
		String ExpUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
		Log.info("Validating after cancel opration it redirected on Employee List page");
		Assert.assertEquals(acturl, ExpUrl);
			
		Log.info("cancelAddEmployee");
	}
	
	
	@Test
	public void validateLargeFileValidation() throws IOException, InterruptedException {
		
		Log.info("validateLargeFileValidation");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();
		
		Log.info("User entering credentials to login");
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("User Navigating on PIM page");
		emplist = dashboard.navigateToPIM();
		
		empadd = emplist.employeeAddButton();
		Thread.sleep(2000);
		
		Log.info("Uploading employee profile photo");
		empadd.addPhoto("C://Users/Admin/selenium_practice/OrengeHRM/TestData/Invalid_file.exe/LargFile_Validation_Test.exe");
		
		Log.info("Validating uploaded photo size");
		boolean flag = empadd.validatePhotoSize();	
		Assert.assertTrue(flag);
		
		Log.endTestCase("validateLargeFileValidation");
	}
	
	@Test
	public void validateInvalidFileType() throws IOException, InterruptedException {
		
		Log.startTestCase("validateInvalidFileType");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();
		
		Log.info("User entering credentials to login");
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("User Navigating on PIM page");
		emplist = dashboard.navigateToPIM();
		
		empadd = emplist.employeeAddButton();
		Thread.sleep(2000);
		
		Log.info("Uploading employee profile photo");
		empadd.addPhoto("C://Users/Admin/selenium_practice/OrengeHRM/TestData/Invalid_file.exe");
		
		Log.info("Validating photo type");
		boolean flag = empadd.validatePhotoSize();	
		Assert.assertTrue(flag);
		
		Log.endTestCase("validateInvalidFileType");
	}
}

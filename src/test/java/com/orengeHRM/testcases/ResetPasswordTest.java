package com.orengeHRM.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.ResetPassword;


public class ResetPasswordTest extends BaseClass{
	
	Login login;
	ResetPassword resetpass;
	
	
	@Test(groups = {"All"})
	public void cancelResetPass() {
		
		login = new Login();
		resetpass= new ResetPassword();
		
		login.resetPassword();
		login = resetpass.cancelForgotPassword();
		boolean loginTitle = login.loginMessage();
		Assert.assertTrue(loginTitle);
		if(loginTitle) {
			System.out.println("Login Title is Present:");
		}
		
	}
	
	@Test(groups = "All")
	public void verifyResetPassPage() {
		login = new Login();
		resetpass= new ResetPassword();
		
		login.resetPassword();
		boolean verifyreset = resetpass.verifyResetPassPage();
		Assert.assertTrue(verifyreset);
		if(verifyreset) {
			
			System.out.println("Reset Password Page open :-");
		}
		
	}

}

package com.orengeHRM.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.orengeHRM.actiondriver.Action;
import com.orengeHRM.base.BaseClass;

public class ExtentListner extends ExtentManager implements ITestListener {

	Action action = new Action();

	public void onTestStart(ITestResult result) {

		setExtent();
		test = extent.createTest(result.getName());
		System.out.println("On Start method invoked....");
	}

	public void onTestSuccess(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Pass Test case is: " + result.getName());
		}
	}

	public void onTestFailure(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			// String imgPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";

			String imgPath = Action.screenShot(BaseClass.driver, result.getName() + ".png");

			test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext result) {
		System.out.println("On Finished method invoked....");
		extent.flush();// it is mandatory to call flush method to ensure information is written to the
						// started reporter.

	}
}

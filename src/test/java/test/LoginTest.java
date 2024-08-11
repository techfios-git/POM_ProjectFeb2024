package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;

public class LoginTest {
	
	WebDriver driver;
	LoginPage loginPage; 
	
//	String userName = "demo@codefios.com";
//	String password = "abc123";
	String userAlertMsg = "Please enter your user name";
	String passwordAlertMsg = "Please enter your password";
	
	@Test(priority=2)
	public void validUserShouldBeAbleToLogin() {
		
		driver = BrowserFactory.init();

		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(BrowserFactory.getUserName());
		loginPage.insertPassword(BrowserFactory.getPassword());
		loginPage.clickSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		Assert.assertEquals(dashboardPage.validateDashboardPage(), "Dashboard", "Dashboard page not found!!");
		BrowserFactory.tearDown();
		
	}
	
	@Test(priority=1)
	public void validateAlertMessages() {
		
		driver = BrowserFactory.init();
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickSigninButton();
		Assert.assertEquals(loginPage.getAlertMsg(), userAlertMsg, "Alert msg dosn't match!!");
		loginPage.accetpAlert();
		
		loginPage.insertUserName(BrowserFactory.getUserName());
		loginPage.clickSigninButton();
		Assert.assertEquals(loginPage.getAlertMsg(), passwordAlertMsg, "Alert msg dosn't match!!");
		loginPage.accetpAlert();
		BrowserFactory.tearDown();
	}

}

package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

import io.appium.java_client.AppiumDriver;

@Listeners(ExtentITestListenerClassAdapter.class)
public class sequentially {

	BaseTest bT;

	public sequentially() {
		bT = new BaseTest();
	}
	public static AppiumDriver driver;

	@BeforeClass
	public void setUp() {
		driver = bT.androidDriverUp("12", "Android");
		bT.setDriver(driver);
	}

	@AfterClass(description = "Closing test")
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test(description = "Edit TrueCaller User Profile")
	public void Step_1_editProfile() {
		bT.editProfile("TD");
	}

	@Test(description = "Search User for In Contacts and from TrueCaller")
	public void Step_2_searchUser() {
		bT.searchUser("9369191367", "IN YOUR CONTACTS");
		bT.searchUser("9911931845", "IDENTIFIED BY TRUECALLER");
	}

}

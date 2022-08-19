package tests;

import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners(ExtentITestListenerClassAdapter.class)
public class SampleTest {
	BaseTest bT;

	public SampleTest() {
		bT = new BaseTest();
	}
	public static AppiumDriver driver;


	@BeforeClass
//	@Parameters({ "platformVersion", "deviceName"})
	public void setUp() {
		driver = bT.androidDriverUp("11", "Android");
		bT.setDriver(driver);
	}

	@AfterClass(description = "Closing test")
	void tearDown() {
		driver.quit();
	}

	@Test(description = "Edit TrueCaller User Profile")
	public void Step_1_editProfile() {
		bT.editProfile("TD");
		System.out.println("Step_1_editProfile");
	}

	@Test(description = "Search User for In Contacts and from TrueCaller")
	public void Step_2_searchUser() {
		bT.searchUser("9369191367", "IN YOUR CONTACTS");
		bT.searchUser("9911931845", "IDENTIFIED BY TRUECALLER");
		System.out.println("Step_2_searchUser");
	}

	@Test(description = "Search User for In Contacts and from TrueCaller")
	public void Step_3_searchDetailsFromContacts() {
		bT.searchFromContacts("Account info");
	}
}

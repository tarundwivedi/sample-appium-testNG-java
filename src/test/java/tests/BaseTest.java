package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;

public class BaseTest {

	public static String platform;
	public static String deviceName;
	public static String build;
	public static String reposPath;
	private AppiumDriver driver;

	public AppiumDriver androidDriverUp(String platformVersion, String deviceName) {
		reposPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator
				+ "resources" + File.separator;
		System.out.println("Initilizing the Android Driver");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		//		capabilities.setCapability("udid", udid);
		capabilities.setCapability("app", reposPath + "com.truecaller.apk");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		//		capabilities.setCapability("unicodeKeyboard", true);
		//		capabilities.setCapability("resetkeyboard", true);
		capabilities.setCapability("newCommandTimeout", 4000);
		capabilities.setCapability("adbExecTimeout", 4000);
		capabilities.setCapability("appWaitDuration", 40000);
		capabilities.setCapability("skipDeviceInitialization", true);
//		capabilities.setCapability("skipLogcatCapture", true);
//		capabilities.setCapability("skipLogCapture", true);
		try {
			AppiumDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//			try{System.out.println("initial memory: " + getMemoryInfo(driver).get("totalPss"));}catch (Exception e) {	}
			return driver;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Step("User Caller Decline Call")
	public void userCallerDeclineCall(AppiumDriver driver1) {
		caller();
		cutPhoneFromNotification(driver1);
	}
	@Step("cut Phone From Notification")
	public void cutPhoneFromNotification(AppiumDriver driver1) {
		wait(5000);
		((AndroidDriver) driver1).openNotifications();
		//		driver1.findElement(AppiumBy.accessibilityId("Decline")).click();
		driver1.findElement(AppiumBy.id("com.truecaller:id/button_decline")).click();
		wait(500);
		driver1.navigate().back();
	}
	@Step("Calling Nunber")
	public void caller() {
		//		((AndroidDriver) driver).launchApp();
		driver.findElement(AppiumBy.id("com.truecaller:id/fab_icon")).click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[9]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[9]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[1]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[1]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[9]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[3]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[1]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[8]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[4]"))
		.click();
		driver.findElement(
				By.xpath("//android.view.ViewGroup[@resource-id='com.truecaller:id/dialpad']/android.view.View[5]"))
		.click();
		driver.findElement(AppiumBy.id("com.truecaller:id/tcx_fab_call")).click();
		driver.findElement(AppiumBy.accessibilityId( "Off, Mute, Button")).isDisplayed();
		driver.findElement(AppiumBy.accessibilityId("Keypad, Button")).isDisplayed();
		driver.findElement(AppiumBy.accessibilityId("Off, Speaker, Button")).isDisplayed();
	}
	@Step("send Message From Dialer List")
	public void sendMessageFromDialerList() {
	
		((AndroidDriver) driver).launchApp();
		wait(1000);
		//		boolean isKeyboardShown = ((AndroidDriver) driver).isKeyboardShown();
		//		System.out.println(isKeyboardShown);
		//		((AndroidDriver) driver).hideKeyboard();

		//		if (isKeyboardShown) {
		//			((AndroidDriver) driver).hideKeyboard();
		//			wait(1000);
		//		}
		driver.findElement(
				By.xpath("(//android.widget.ImageView[@resource-id='com.truecaller:id/action_secondary'])[1]")).click();
		Assert.assertEquals(driver.findElement(AppiumBy.id("com.truecaller:id/source")).getText().toLowerCase(),
				"IN YOUR CONTACTS".toLowerCase());
		driver.findElement(AppiumBy.id("com.truecaller:id/message_button")).click();
		driver.findElement(AppiumBy.id("com.truecaller:id/emoji_toggle_button")).isDisplayed();
		driver.findElement(AppiumBy.id("com.truecaller:id/attach_button")).isDisplayed();
		driver.findElement(AppiumBy.id("com.truecaller:id/message_text")).sendKeys("I Love truecaller");
		driver.findElement(AppiumBy.id("com.truecaller:id/sendButton")).click();
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
		System.out.println("SMS Sended");
		((AndroidDriver) driver).openNotifications();
	}
	
	@Step("recived Message From User and mark as Read")
	public void recivedMessageFromDialerList(AppiumDriver driver1) {
		((AndroidDriver) driver1).openNotifications();
		driver1.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc='Mark as read']")).click();
	}
	
	@Step("recived Message From User and mark as Read")
	public void userSendMessageAndMarkedAsRead(AppiumDriver driver1) {
		sendMessageFromDialerList();
		recivedMessageFromDialerList(driver1);
	}
	@Step("Edit Profile")
	public void editProfile(String EditName) {
		driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
		String UserName = driver.findElement(AppiumBy.id("com.truecaller:id/name")).getText();
		driver.findElement(AppiumBy.accessibilityId("Edit profile")).click();
		WebElement ele = driver.findElement(AppiumBy.id("com.truecaller:id/firstNameEditText"));
		ele.clear();
		ele.sendKeys(EditName);
		driver.findElement(AppiumBy.id("com.truecaller:id/saveButton")).click();
		String updatedUserName = driver.findElement(AppiumBy.id("com.truecaller:id/name")).getText();
		Assert.assertNotEquals(UserName, updatedUserName,"Profile Name is same after edit ");
		System.out.println("Edit profile test completed");
	}
	@Step("search User from search bar ")
	public void searchUser(String searchUser, String DetailsScreeenTitle) {
		((AndroidDriver) driver).launchApp();
		driver.findElement(AppiumBy.id("com.truecaller:id/searchBarLabel")).click();
		driver.findElement(AppiumBy.id("com.truecaller:id/search_field")).sendKeys(searchUser);
		wait(1000);
		//		driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'" + searchUser + "')]")).isDisplayed();
		driver.findElements(AppiumBy.id("com.truecaller:id/title")).get(0).click();
		wait(500);
		Assert.assertEquals(driver.findElement(AppiumBy.id("com.truecaller:id/source")).getText().toLowerCase(),
				DetailsScreeenTitle.toLowerCase(),"Expected Page not opened");
		scroll("THE DISPLAYED CONTENT IS UNMODERATED");
		driver.navigate().back();
	}
	
	public void scroll(String scrollString) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+scrollString+"\").instance(0))")).isDisplayed();
	}

	public void searchFromContacts(String scrollString) {
		((AndroidDriver) driver).launchApp();
		driver.findElement(AppiumBy.id("com.truecaller:id/bottombar2_contacts")).click();
		scroll(scrollString);
		}
	
	public void wait(int waiting) {
		try {
			Thread.sleep(waiting);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDriver(AppiumDriver driver) {
		this.driver = driver;
	}

	private HashMap<String, Integer> getMemoryInfo(AppiumDriver driver) {
		try {
//			List<List<Object>> cpuInfo = ((AndroidDriver) driver).getPerformanceData("com.truecaller", "cpuinfo", 2);
//			cpuInfo.forEach(System.out::println);
//			List<List<Object>> batteryInfo = ((AndroidDriver) driver).getPerformanceData("com.truecaller", "batteryinfo", 2);
//			batteryInfo.forEach(System.out::println);
//			List<List<Object>> networkInfo = ((AndroidDriver) driver).getPerformanceData("com.truecaller", "networkinfo", 5);
//			networkInfo.forEach(System.out::println);
			List<List<Object>> data = ((AndroidDriver) driver).getPerformanceData("com.truecaller", "memoryinfo", 2);
			data.forEach(System.out::println);
			HashMap<String, Integer> readableData = new HashMap<>();
			System.out.println(data);
			for (int i = 0; i < data.get(0).size(); i++) {
				int val;
				if (data.get(1).get(i) == null) {
					val = 0;
				} else {
					val = Integer.parseInt((String) data.get(1).get(i));
				}
				readableData.put((String) data.get(0).get(i), val);
			}
			return readableData;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
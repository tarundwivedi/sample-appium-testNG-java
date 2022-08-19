package tests;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;
public class combinationTest {
		BaseTest bT;
		
		public combinationTest() {
			bT	= new BaseTest();
			System.out.println("Starting test for User call and messages");
		}

		public static AppiumDriver driver;
		public static AppiumDriver driver1;
		
		@BeforeMethod
		void init() {

		}
		
		@BeforeClass
		@Parameters({ "platformVersion", "deviceName"})
		void setUp(String platformVersion, String deviceName) {
			System.out.print("platformVersion : "+platformVersion);
			driver	=		bT.androidDriverUp(platformVersion, deviceName);
			bT.setDriver(driver);
		}
		
		@AfterClass
		void tearDown() throws Exception {
			driver.quit();
		}

		@Test
		public void Step_1_call() {
			driver1	=		bT.androidDriverUp("11","Android");
			bT.userCallerDeclineCall(driver1);
		}

		@Test
		public void Step_2_sendMessageFromDialerList() {
//			driver1	=		bT.androidDriverUp("11","Android");
			bT.userSendMessageAndMarkedAsRead(driver1);
			driver1.quit();
		}
}

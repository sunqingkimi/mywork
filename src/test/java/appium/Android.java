package appium;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
public class Android {
    AndroidDriver driver;
    @BeforeTest
    public void setUp() throws MalformedURLException{
        DesiredCapabilities des = new DesiredCapabilities();
//      des.setCapability("app", "c:\\");
        des.setCapability("platformName", "Android");
        des.setCapability("platformVersion", "4.4.2");
//        des.setCapability("udid", "127.0.0.1:62001");
        des.setCapability("deviceName", "127.0.0.1:62001");
        des.setCapability("appPackage", "com.envisioniot.mobile.enos");//com.android.contacts
        des.setCapability("appActivity", "com.envisioniot.mobile.enos.SplashActivity");//.activities.PeopleActivity
        des.setCapability("unicodeKeyboard", "True");
        des.setCapability("resetKeyboard", "True");
        des.setCapability("newCommandTimeout", "15");
        des.setCapability("nosign", "True");
        driver = new AndroidDriver(new URL("http://127.0.0.1:62001/wd/hub"),des);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test(testName = "app")
public void test(){
        driver.findElementById("com.envisioniot.mobile.enos:id/et_username").sendKeys("guest");
    }
}

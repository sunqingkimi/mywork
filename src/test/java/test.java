import base.CSVData2;
import base.isElementExist;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
public class test {
    //初始化一个chrome浏览器实例，实例名称叫driver
    WebDriver driver = new ChromeDriver();

    @DataProvider(name = "num")
    public static Object[][] words() throws Exception {
        return CSVData2.getData("D:\\FirstAPITest\\src\\test\\java\\testdata\\test.csv");
    }

    @Test(testName = "百度搜索参数化测试", dataProvider = "num")
    public void testcase(String caseID, String user, String password,String isneedrun) throws InterruptedException, IOException {
        if(!Boolean.valueOf(isneedrun))
            return;

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + caseID + ">>>>>>>>>>>>>>>>>>>>>>>");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        //最大化窗口
        driver.manage().window().maximize();
        //设置隐性等待时间
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        // get()打开一个站点
        driver.get("http://10.27.21.146:8082/fchmi/index.html#/login");
        //判断页面元素是否存在,等待10秒
        isElementExist ElementExist = new isElementExist();
        ElementExist.ElementExist(driver, By.className("user"), 10);
        //在输入框输入参数化值
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[1]/input")).sendKeys(user);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/div[2]/input")).sendKeys(password);
        //点击百度一下按钮
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/div/button")).click();
//        ((ChromeDriver) driver).findElementById("su").click();
        TimeUnit.SECONDS.sleep(5);
        //查询页面有没有出现关键字
        assert driver.findElement(By.tagName("body")).getText().contains("工作窗口");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/ul/li[4]/div")).click();
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.xpath("//*[@id=\"jobperiod$Menu\"]/li[1]")).click();
        TimeUnit.SECONDS.sleep(5);
        assert driver.findElement(By.tagName("body")).getText().contains("风机总数");
        TimeUnit.SECONDS.sleep(10);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[3]/span/input")).sendKeys("01");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[3]/span/span/button")).click();
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div/div[3]/div/span[1]")).click();
        assert driver.findElement(By.tagName("body")).getText().contains("八仙角海上");
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.xpath("//*[@id=\"jobperiod$Menu\"]/li[2]")).click();
        TimeUnit.SECONDS.sleep(5);
        assert driver.findElement(By.tagName("body")).getText().contains("窗口期预览");
        // 调用截图方法
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(".\\Screenshots\\screen.png"));
        System.out.printf("title of current page is %s\n", driver.getTitle());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

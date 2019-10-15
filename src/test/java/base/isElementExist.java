package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class isElementExist {
    /**
     * 判断元素是否存在
     *
     * @param driver
     * @param locator
     * @param timeoutSeconds
     *            最多等待的时间,单位 ： 30秒
     * @return
     */

    public boolean ElementExist(WebDriver driver, By locator, int timeoutSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
        }
    }
}

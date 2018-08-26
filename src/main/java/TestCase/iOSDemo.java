package TestCase;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class iOSDemo {

    private AppiumDriver driver;
    private String udid = "86616cbaa40e52d3f9236ec982dd6f1e933a44bd";
    private static String rootPath = System.getProperty("user.dir");
    private String htmlPath = rootPath + "/test.html";

    /**
     * 测试真机App
     * @throws InterruptedException
     * @throws MalformedURLException
     */
    @Test
    public void test_real() throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability("platformName", "ios");
        caps.setCapability("automationName", "xcuitest");
        caps.setCapability("deviceName", "iPhone");
        caps.setCapability("udid", "auto");
        caps.setCapability("app", "com.iOS.Demo.xinxi");
        //核心是这两个配置
        caps.setCapability("startIWDP", true);
        //不涉及编译不需要配置这个
        caps.setCapability("xcodeOrgId", "4QWXQ8WHH9");
        caps.setCapability("xcodeSigningId", "iPhone Developer");
        AppiumDriver driver=new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(driver.getPageSource());
        driver.findElementByAccessibilityId("Buttons").click();
    }


    /**
     * 测试Safari浏览器
     * @throws InterruptedException
     * @throws MalformedURLException
     */
    @Test
    public void test_brower() throws InterruptedException, MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "xcuitest");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone sim");
        desiredCapabilities.setCapability("startIWDP", true);
        desiredCapabilities.setCapability("usePrebuiltWDA", true); //不再次安装WDA

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AppiumDriver driver = new AppiumDriver(url, desiredCapabilities);
        // 浏览网页和定位页面元素取得id。
        driver.get("http://www.baidu.com");
        Thread.sleep(2000);
        driver.findElement(By.id("index-kw")).sendKeys("tester");
        driver.findElement(By.id("index-bn")).click();
        Thread.sleep(2000);
    }

    /**
     * 测试app中的webView页面
     * @throws InterruptedException
     * @throws MalformedURLException
     */
    @Test
    public void test_appWebView() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "xcuitest");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone sim");
        desiredCapabilities.setCapability("app", "com.iOS.Demo.xinxi");
        desiredCapabilities.setCapability("startIWDP", true);
        desiredCapabilities.setCapability("usePrebuiltWDA", true); //不再次安装WDA

        AppiumDriver driver=new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap();
        scrollObject.put("direction", "down");
        scrollObject.put("name", "Web View");
        js.executeScript("mobile: scroll", scrollObject);
        //点击Web View
        driver.findElementByAccessibilityId("Web View").click();
        Thread.sleep(10000);
        //打印原生page source
        System.out.println(driver.getPageSource());
        //切换到最后一个context，一般是webview
        driver.context(driver.getContextHandles().toArray()[1].toString());
        //打印webview的page source
        System.out.println(driver.getPageSource());
        //用css定位找元素点击
        //driver.findElementByCssSelector(".cta").click();
        driver.findElement(By.xpath("//*[@id=\"section-1-hero-position-1\"]/div/div[2]/a[1]")).click();
    }




}


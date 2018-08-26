package WeApp;

import Base.Base;
import Base.Driver;
import Model.HomePage;
import Model.LoginPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Test_WeApp {


    private AndroidDriver driver;

    /**
     * 获取配置appium的capabilities
     * @return driver实例
     */
    public AndroidDriver basedriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("deviceName","FA69P0314479"); // 真机的设备号
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "6.0"); //系统版本号必须写对
        capabilities.setCapability("appPackage", "com.tencent.mm");
        capabilities.setCapability("appActivity", ".ui.LauncherUI");
        capabilities.setCapability("fastReset", "false");
        capabilities.setCapability("fullReset", "false");
        capabilities.setCapability("noReset", "true");
        capabilities.setCapability("unicodeKeyboard",true); // 设置支持中文
        capabilities.setCapability("newCommandTimeout",60);
        //capabilities.setCapability("app",BaseDriver.getAppPath()); // 不设置可以不重新安装app

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("androidProcess", "com.tencent.mm:tools");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // 设置全局隐示等待
        return driver;
    }


    /**
     * 在setup完成初始化drvier操作
     */
    @BeforeClass
    public void setup() throws IOException, InterruptedException {
        basedriver();
        System.out.println("启动app");

    }

    @Test
    public void miniProgramTest() throws Exception {
        System.out.println("测试case");

    }

}

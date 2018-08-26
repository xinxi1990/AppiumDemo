package Model;

import Base.Base;
import Base.Driver;
import java.io.IOException;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;



/**
 * @author xinxi
 * 页面基类
 * BasePage负责初始化Drvier
 */

public class BasePage {

    public AndroidDriver baseDriver;
    public TouchAction touchAction;
    public Base base;
    public HomePage homePage;
    public LoginPage loginPage;

    /**
     * 在setup完成初始化drvier操作
     * BasePage不负责初始化其他Page
     * 子类setup中的方法负责初始化PO
     */
    @BeforeClass
    public void setup() throws IOException, InterruptedException {
        baseDriver = Driver.getInstance();
        touchAction = new TouchAction(baseDriver);
        base = new Base(baseDriver);
        Thread.sleep(3000);
        initPage();
        homePage.homeAgree();
    }


    @AfterClass
    public  void teardown(){
        baseDriver.quit();

    }


    /**
     * 初始化所有PO
     */
    private void initPage(){
        homePage = new HomePage(baseDriver);
        loginPage = new LoginPage(baseDriver);
    }


}

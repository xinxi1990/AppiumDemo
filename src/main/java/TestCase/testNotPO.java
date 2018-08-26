package TestCase;

import Base.Driver;
import Base.Base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Set;


/**
 * @author xinxi
 * 没有用PO模式
 */


public class testNotPO extends Driver {



    private String PakName = "com.xueqiu.android";
    public static Logger logger;
    private AndroidElement androidElement;
    public AndroidDriver baseDriver;
    private TouchAction touchAction;
    private Base base;
    private int TimeOut = 5;
    private String HomeAgree = "agree";
    private String HomeUser = "user_profile_icon";
    private String LoginButton = "tv_login";
    private String MobileLogin = ".//*[@text='手机及其他登录']";
    private String MobileInput = "register_phone_number";
    private String MobileCode = "register_code";
    private String MobileButton = "button_next";
    private String UserName = "18513571170";
    private String Code = "1234";
    private String HomeSelect = ".//*[@text='自选']";
    private String CloseAD = "ib_close";
    private String AddButon = ".//*[contains(@resource-id, 'portfolio_and_cube_empty')]/android.widget.ImageView[@resource-id=\"com.xueqiu.android:id/image\"]";
    private String SelectTab= ".//*[@text='沪深']";
    private String InputText = "search_input_text";
    private String InputVule = "拼多多";
    private String AddButton = "add_attention";
    private String AlreadAdd = ".//*[@text='已添加']";
    private String HK = ".//*[@resource-id='com.xueqiu.android:id/button_text'][@text='港股']";

    /**
     * 在setup完成初始化drvier操作
     */
    @BeforeClass
    public void setup() throws Exception {
        baseDriver = Driver.getInstance();
        logger =  new Driver().initlogger();
        touchAction = new TouchAction(baseDriver);
        base = new Base(baseDriver);
        Thread.sleep(3000);
    }


    /**
     * 测试点：
     * 启动雪球->点击主界面的登陆按钮->点击登陆->输入用户名和密码
     */
    @Test
    public void test_login() throws Exception {

        base.getMemoryPss();

        base.FindElementSkip(By.id(HomeAgree));

        base.displayWait(TimeOut,By.id(HomeUser)).click();
        base.displayWait(TimeOut,By.id(LoginButton)).click();
        base.displayWait(TimeOut,By.xpath(MobileLogin)).click();
        androidElement = (AndroidElement) base.displayWait(TimeOut,By.id(MobileInput));
        androidElement.clear();
        androidElement.sendKeys(UserName);
        androidElement = (AndroidElement) base.displayWait(TimeOut,By.id(MobileCode));
        androidElement.clear();
        androidElement.sendKeys(Code);
        base.displayWait(TimeOut,By.id(MobileButton)).click();

        base.getMemoryPss();

    }

    /**
     * 测试点：
     * 点击自选->点击+号 添加一只你喜欢的股票
     */
    @Test
    public void test_addLike(){

        while (true){
            if (baseDriver.findElements(By.id(HomeUser)).size() !=0){
                break;
            }else {
                baseDriver.navigate().back();
            }
        }

        base.displayWait(TimeOut,By.xpath(HomeSelect));
        base.displayWait(TimeOut,By.xpath(HomeSelect));
        base.displayWait(TimeOut,By.xpath(HomeSelect)).click();
        base.displayWait(TimeOut,By.xpath(SelectTab)).click();

        if (baseDriver.findElements(By.id(CloseAD)).size() !=0){
            base.displayWait(TimeOut,By.id(CloseAD)).click();
        }

        base.displayWait(TimeOut,By.xpath(AddButon)).click();
        base.displayWait(TimeOut,By.id(InputText)).sendKeys(InputVule);
        base.displayWait(TimeOut,By.id(AddButton)).click();

        if (baseDriver.findElements(By.xpath(AlreadAdd)).size() !=0) {
            System.out.print("股票添加成功!");
        }else {
            System.out.print("股票添加失败!");
        }
    }


    /**
     * 测试点：测试滑动
     */
    @Test
    public  void  test_scroll() throws InterruptedException {
        base.scroll_up(1);
        base.scroll_down(1);
    }


    /**
     * 测试点：获取性能数据
     */
    @Test
    public  void test_getPerdata() throws Exception {

        base.getPerData(PakName,"batteryinfo");
        base.getPerData(PakName,"cpuinfo");

    }







}

package TestCase;

import Base.Driver;
import Base.Base;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class testWebView {


    private AndroidDriver baseDriver;
    private Base base;
    private int TimeOut = 5;
    private String HomeAgree = "agree";
    private String HK = ".//*[@resource-id='com.xueqiu.android:id/button_text'][@text='港股']";
    private String NowBtn = "/html/body/div/section[1]/div[3]/a";
    private String HuShenBtn = "//android.widget.TextView[@text='沪深' and @resource-id='com.xueqiu.android:id/button_text']";
    private String openBtn = "立即开户";
    private String Now = "开始";
    private String Menu = "body > div > section.one.hk > div.header > div > div > div";
    private String Help = "/html/body/div/section[1]/div[1]/div/ul/li[6]/a";
    private String openAccount = "/html/body/div[1]/div[2]/div/div[1]/ul/li[1]/a/p";



    /**
     * 在setup完成初始化drvier操作
     */
    @BeforeClass
    public void setup() throws Exception {
        baseDriver = Driver.getInstance();
        base = new Base(baseDriver);
        Thread.sleep(3000);
        base.FindElementSkip(By.id(HomeAgree));
    }


    /**
     * 测试点:点击弹窗中的同意-点击港深跳转到webview-切换webview-菜单-帮助-开户指南
     * 帮助和开户指南在两个web页面上
     */
    @Test
    public void test_webview() throws Exception {
        base.displayWait(TimeOut, By.xpath(HK)).click();
        base.switchToWebView();
        base.displayWait(TimeOut, By.cssSelector(Menu)).click();
        base.displayWait(TimeOut, By.xpath(Help)).click();
        base.displayWait(TimeOut, By.xpath(openAccount)).click();
    }

    /**
     * 测试点:点击弹窗中的同意-截图-点击港深跳转到webview-点击立即开户
     */
    @Test
    public void test_webview2() throws Exception {
        base.FindElementClick(3,By.xpath(HuShenBtn));
        baseDriver.findElementByAccessibilityId(openBtn).click();
        baseDriver.findElementByAccessibilityId(Now).click();

    }

}
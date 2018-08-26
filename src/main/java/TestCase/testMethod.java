package TestCase;

import Base.Base;
import Model.BasePage;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;


/**
 * @author xinxi
 * 测试常用api方法
 */

public class testMethod extends BasePage {

    AndroidElement androidElement;
    String findElement = ".//*[@text='记账本  152评']";



    /**
     * 测试常用api方法
     */
    @Test(enabled = false)
    public void test_method() throws IOException, InterruptedException {

        /**
        baseDriver.lockDevice();
        //锁定屏幕

        baseDriver.unlockDevice();
        //解锁屏幕

        Duration duration = Duration.ofSeconds(5);
        baseDriver.runAppInBackground(duration);
        //把app置于后台5s

        baseDriver.openNotifications();
        //打开通知栏


        baseDriver.hideKeyboard();
        //隐藏键盘

        baseDriver.getKeyboard();
        //展示键盘

        Activity activity = new Activity("com.xxx","xxxx");
        baseDriver.startActivity(activity);
        // 启动Activity

        Boolean isInstall = baseDriver.isAppInstalled("com.example.android.apis");
        System.out.println("app是否安装:" + isInstall);
        //查询某个app是否安装

        baseDriver.installApp("path/to/my.apk");
        // 安装app

        baseDriver.removeApp("com.example.android.apis");
        // 卸载app

        baseDriver.closeApp();
        // 关闭app

        baseDriver.launchApp();
        // 启动app

        baseDriver.resetApp();
        // 重置app


        System.out.println(baseDriver.getContextHandles());
        // 获取当前Context

        System.out.println(baseDriver.getContext());
        // 获取当前Context

        baseDriver.context("NATIVE_APP");
        // 切换到某个context

        //System.out.println(baseDriver.getAppStringMap().entrySet());
        // 获取app的名字
         */
        //androidElement = (AndroidElement) baseDriver.findElementByXPath(findElement);
        //new Base(baseDriver).ScrollToElement(androidElement);


        Thread.sleep(3000);


        baseDriver.pressKeyCode(AndroidKeyCode.ENTER);
        // 模拟设备事件

        System.out.println(baseDriver.getCurrentPackage());
        // 获取当前的包

        //baseDriver.pushFile("", new File(""));
        //  向设备发送文件

        //baseDriver.pullFile("");
        // 获取设备上的文件

        System.out.println(baseDriver.getSettings());
        // 获取设置信息



        String act = baseDriver.currentActivity();
        System.out.println("当前Activity:" + act);
        //获取当前Activity名字

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        };
        baseDriver.addLogcatConnectionListener(runnable);
        Boolean isListening =  baseDriver.getLogcatClient().isListening();
        System.out.println(isListening);
        //logcat监听
    }

    /**
     * 测试toast方法
     */
    @Test(enabled = true)
    public void  test_toast(){

        baseDriver.findElementByName("Accessibility").click();

//        baseDriver.findElementByAndroidUIAutomator("new UiSelector().text(\"App\")").click();
//
//        new Base(baseDriver).scrollToElment("Views");
//        new Base(baseDriver).scrollToElment("Popup Menu");
//        //baseDriver.findElementByClassName("android.widget.Button").click();
//        //baseDriver.findElementByAndroidUIAutomator("new UiSelector().text(\"MAKE A POPUP!\")");
//        baseDriver.findElementByXPath("//android.widget.TextView[contains(@text,'Search')]").click();
//        String toast = baseDriver.findElementByXPath("//*[@class='android.widget.Toast']").getText();
//        System.out.println(toast);




    }


}

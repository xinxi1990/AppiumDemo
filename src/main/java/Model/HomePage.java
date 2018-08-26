package Model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import Base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;




public class HomePage {
    /**
     * WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
     * /java8换了时间库chronoUnit 新版本写法
     * WithTimeout(time = 5000,unit = TimeUnit.MILLISECONDS)
     * 老版本写法
     * /
     */
    public AndroidDriver androidDriver;

    private static final int timeout= 5;
    // 显示最大超时时间

    private By Homeagree = By.id("agree");

    @AndroidFindBy(id = "agree")
    @WithTimeout(time = timeout,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement HomeAgree;

    @AndroidFindBy(id = "user_profile_icon")
    @WithTimeout(time = timeout,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement HomeUser;

    @AndroidFindBy(id = "tv_search")
    @WithTimeout(time = timeout,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement HomeSearch;

    @AndroidFindBy(id = "tv_login")
    @WithTimeout(time = timeout,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement HomeLogin;

    @AndroidFindBy(id = "tv_login_by_phone_or_others")
    @WithTimeout(time = timeout,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement LoginOthers;


    /**
     * 初始化页面
     */
    public HomePage(AndroidDriver androidDriver) {
        Duration duration = Duration.ofSeconds(10); //设置10s隐藏等待
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
        this.androidDriver = androidDriver;
    }


    /**
     * 点击弹窗同意
     */
    public void homeAgree(){
        if (androidDriver.findElements(Homeagree).size() > 0 ){
            androidDriver.findElement(Homeagree).click();
        }
    }

    /**
     * 点击首页头像
     */
    public void homeUser(){
        HomeUser.click();
    }
}

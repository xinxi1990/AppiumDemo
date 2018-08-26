package Model;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import java.time.temporal.ChronoUnit;


public class LoginPage {

    public AndroidDriver androidDriver;

    @AndroidFindBy( id = "tv_login")
    @WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement LoginButton;

    @AndroidFindBy( xpath = ".//*[@text='手机及其他登录']")
    @WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement MobileLogin;

    @AndroidFindBy( xpath = ".//*[@text='邮箱手机号密码登录']")
    @WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement UserLogin;


    @AndroidFindBy( id = "register_phone_number")
    @WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement MobileInput;

    @AndroidFindBy( id = "register_code")
    @WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement MobileCode;

    @AndroidFindBy( id = "button_next")
    @WithTimeout(time = 5,chronoUnit = ChronoUnit.SECONDS)
    public AndroidElement MobileButton;

    private String UserName = "18513571170";

    private String Code = "1234";

    public String codeDisable = "验证码已过期";

    /**
     * 初始化页面
     */
    public LoginPage(AndroidDriver androidDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(androidDriver),this);
        this.androidDriver = androidDriver;
    }


    /**
     * 手机号登录
     */
    public void mobileLogin(){
        LoginButton.click();
        MobileInput.clear();
        MobileInput.sendKeys(UserName);
        MobileCode.clear();
        MobileCode.sendKeys(Code);
        MobileButton.click();
    }


}

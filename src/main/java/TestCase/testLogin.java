package TestCase;

import Model.BasePage;
import org.testng.annotations.Test;


/**
 * @author xinxi
 * 使用PO模式调用组件功能
 */

public class testLogin extends BasePage {

    /**
     * 测试点：
     * 流程:启动雪球->点击主界面的登录按钮->点击登录->输入用户名和错误验证码
     * 断言:如果包含验证码过期则说明登录失败,断言则成功
     */
    @Test
    public void test_login() throws InterruptedException {
         homePage.homeUser();
         loginPage.mobileLogin();
         base.assertContain(loginPage.codeDisable);
    }
}

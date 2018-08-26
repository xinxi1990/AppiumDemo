package Base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.testng.Assert;


/**
 * @author xinxi
 * 封装常用操作
 */


public class Base {

    private static String PKG = "com.xueqiu.android";
    private static String PERF_TYPE = "memoryinfo";
    private static String PSS_TYPE = "totalPss";
    private static int MEMORY_USAGE_WAIT = 30000;
    private static int MEMORY_CAPTURE_WAIT = 10;
    private final AndroidDriver baseDriver;
    private int PSS;
    private TouchAction touchAction;
    private Dimension dimension;
    private String PageSource;
    String info;




    public Base(AndroidDriver baseDriver) {
        this.baseDriver = baseDriver;
    }

    /**
     * 封装显示等待
     * @param :等待时间
     * @param :元素
     * @param :appiumdriver
     * @return
     */
    public WebElement displayWait(int waitTime, By by){
        WebElement wb = null;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(this.baseDriver,waitTime);
            wb =  webDriverWait.until(ExpectedConditions.visibilityOfElementLocated((by)));
            System.out.println(String.format("点击%s元素:等待%s秒",by,waitTime));
        }catch (Exception e){
            System.out.println(String.format("等待%s元素,异常%s",by,e));
        }
        return wb;
    }


    /**
     * 统计内存方法
     * @return map数据
     * @throws InterruptedException
     */
    private HashMap<String, Integer> getMemoryInfo() throws Exception {

        List<List<Object>> data = this.baseDriver.getPerformanceData(PKG, PERF_TYPE, MEMORY_CAPTURE_WAIT);
        HashMap<String, Integer> readableData = new HashMap<String, Integer>();

        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

    /**
     * 统计内存Pss方法
     * @return pss数据
     * @throws InterruptedException
     */
    public int getMemoryPss() throws Exception {
        PSS = getMemoryInfo().get(PSS_TYPE);
        System.out.println("当前pss:" + PSS);
        return PSS;
    }


    /**
     * 断言元素存在则为成功
     */
    public void assert_ok(WebElement webElement){
        if ( webElement.isDisplayed()){
            Assert.assertTrue(true);
        }
        else {
            Assert.assertFalse(true);
        }
    }


    /**
     * 断言元素不存在则为成功
     */
    public void assert_bad(AndroidElement androidElement){
        if ( ! androidElement.isDisplayed()){
            Assert.assertTrue(true);
        }
        else {
            Assert.assertFalse(true);
        }
    }


    /**
     * 循环查找元素并点击
    */
    public void FindElementClick(int waitTime, By by) throws InterruptedException {

        WebElement WebElement = displayWait(waitTime,by);
        while (true){
            if (WebElement.isDisplayed()){
                WebElement.click();
                break;
            }
            else {
                Thread.sleep(1000);
            }
        }
    }

    /**
     * 循环查找元素存在点击,不存在跳过
     */
    public void  FindElementSkip(By by){
        System.out.println("FindElementSkip");
        if (this.baseDriver.findElements(by).size() > 0 ){
            baseDriver.findElement(by).click();
        }
    }



    /**
     * 获取屏幕尺寸
     * @ 屏幕尺寸的width和height
     */
    private Dimension getWindowSize(){
        dimension = this.baseDriver.manage().window().getSize();
        return dimension;
    }



    /**
     * 向上滚动
     * @ looper 次数
     */
    public  void  scroll_up(int loop) throws InterruptedException {

        for (int i = 0; i < loop ; i++) {
            touchAction = new TouchAction(this.baseDriver);
            touchAction.press(PointOption.point((int) (getWindowSize().width * 0.5),(int) (getWindowSize().height * 0.8)));
            touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
            touchAction.moveTo(PointOption.point((int) (getWindowSize().width * 0.5),(int) (getWindowSize().height * 0.3)));
            touchAction.release();
            touchAction.perform();
            System.out.println("向上滑动!");
            Thread.sleep(1000);
        }
    }


    /**
     * 向上滚动
     * @param :次数
     */
    public  void  scroll_down(int loop) throws InterruptedException {

        for (int i = 0; i < loop ; i++) {
            touchAction = new TouchAction(this.baseDriver);
            touchAction.press(PointOption.point((int) (getWindowSize().width * 0.5),(int) (getWindowSize().height * 0.3)));
            touchAction.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
            touchAction.moveTo(PointOption.point((int) (getWindowSize().width * 0.5),(int) (getWindowSize().height * 0.8)));
            touchAction.release();
            touchAction.perform();
            System.out.println("向下滑动!");
            Thread.sleep(1000);
        }
    }

    /**
     * 滑动到某个元素
     */
    public void  scrollToElment(String text){
        this.baseDriver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" +
                        String.format("new UiSelector().text(\"%s\").instance(0));", text)).click();
    }



    /**
     * 获取app性能数据
     * param:性能类型
     */
    public String getPerData(String pckName,String Pertype) throws Exception {

        int timeout = 3;
        if (Pertype.equals("batteryinfo") ){
            info = this.baseDriver.getPerformanceData(pckName,Pertype,timeout).toString();;
        }else if (Pertype.equals("cpuinfo")){
            info = this.baseDriver.getPerformanceData(pckName,Pertype,timeout).toString();
        }

        return info;
    }

    /**
     * 获取log日志
     */
    public  void getLog(){
        baseDriver.manage().logs().getAvailableLogTypes().forEach(x -> System.out.println());
        System.out.println(baseDriver.manage().logs().get("logcat").toJson().toString());


    }

    /**
     * 滑动到某个元素
     */
    public  void ScrollToElement (AndroidElement androidElement){

        JavascriptExecutor js = (JavascriptExecutor) baseDriver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        scrollObject.put("element", ((RemoteWebElement) androidElement).getId());
        js.executeScript("mobile: scroll", scrollObject);
    }


    /**
     * 截图操作:用时间戳保留
     */
    public void screenShot() throws IOException {

        String ScreenPath = System.getProperty("user.dir") + "/src/main/java/ScreenShot/" +
                System.currentTimeMillis() + "_screenshot.png";
        File srcFile = baseDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(ScreenPath));
    }


    /**
     * 切换webview方法
     */
    public void switchToWebView() throws InterruptedException {

        Thread.sleep(5000);
        Set contextNames = baseDriver.getContextHandles();
        boolean Status = true;
        while (Status){
            for (Object contextName : contextNames) {
                if (contextName.toString().contains("WEBVIEW")) {
                    System.out.println("当前的context类型:" + contextNames.toString());
                    baseDriver.context((String) contextName);
                    Status = false;
                }
                else {
                    //System.out.println("WEBVIEW NOT Found!");
                    Thread.sleep(1000);
                }
            }
        }
    }



    /**
     * 断言页面包含某个的元素
     */
    public void assertContain(String text) throws InterruptedException {
        this.sleep(1);
        PageSource = baseDriver.getPageSource();
        System.out.println(PageSource);
        if (PageSource.contains(text)){
            Assert.assertTrue(true);
        }else {
            Assert.assertFalse(true);
        }
    }


    /**
     * 断言页面不包含某个的元素
     */
    public void assertNotContain(String text){
        PageSource = baseDriver.getPageSource();
        if (!PageSource.contains(text)){
            Assert.assertTrue(true);
        }else {
            Assert.assertFalse(true);
        }

    }

    /**
     * 等待
     */
    public void sleep(int time) throws InterruptedException {
        Thread.sleep(1000 * time);
    }


}

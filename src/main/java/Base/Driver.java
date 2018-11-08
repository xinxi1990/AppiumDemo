package Base;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import Untils.Common;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;



/**
 * @author xinxi
 * 创建appium driver
 */

public class Driver {

    public static Logger logger;
    private AndroidDriver driver;
    public  IOSDriver iosDriver;
    private static Driver instance;
    private static String RootPath = System.getProperty("user.dir");
    private static String CapsYaml = RootPath + "/src/main/java/Config/Caps.yaml";
    public static String CapsNewYaml = RootPath + "/src/main/java/Config/CapsNew.yaml";
    public static String chromedriverpath =  RootPath + "/src/main/java/drivers/chromedriver_51/chromedriver";
    public static String UDID = "DDAC13B0-786D-4DC7-A920-4BEAF56CD616";
    private static String AppiumHost = "127.0.0.1";
    private static int AppiumPort = 4723;
    private static int BootStrapPORT = 4724;
    private static int WDAPort = 8001;
    private static String iOSAPP = System.getProperty("user.dir") + "/src/main/java/App/UICatalog.app";
    public static String getPlatform = "iOS";
    private static String startCmd;
    private static long AppiumWaitTime = 5 * 1000; // Appium Server启动时间


    /**
     * 获取app安装包路径
     */
    public static File getAppPath(){
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/src/main/java/App");
        File app = new File(appDir, "com.xueqiu.android.apk");
        return app;
    }


    /**
     * 获取配置appium的capabilities
     * @return driver实例
     */
    private AndroidDriver basedriver() throws MalformedURLException{

        Map map = Common.readYaml(CapsNewYaml);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), Common.Capsmap(map,capabilities));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // 设置全局隐示等待
        return driver;
    }



    /**
     * 组装Appium的capabilities
     * return: driver实例
     */
    public IOSDriver iOSDriver() throws IOException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
        capabilities.setCapability(MobileCapabilityType.UDID, UDID);
        capabilities.setCapability(MobileCapabilityType.APP, iOSAPP);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1800);
        capabilities.setCapability(IOSMobileCapabilityType.WDA_CONNECTION_TIMEOUT, 1800*1000);
        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT,Integer.parseInt(String.valueOf(WDAPort)));
        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS,true);
        capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS,true);
        if ( !UDID.contains("-")){
            capabilities.setCapability(IOSMobileCapabilityType.START_IWDP, true);
            capabilities.setCapability("autoWebview", true);
            capabilities.setCapability("xcodeOrgId", "7Q6C9D7LVN");
            capabilities.setCapability("xcodeSigningId", "iPhone Developer");
            System.out.println("使用iOS真机测试!");
        }
        String url = "http://"+ AppiumHost +":" + AppiumPort + "/wd/hub";
        System.out.println(("appium Remote URL:" + url));
        iosDriver = new IOSDriver(new URL(url),capabilities);
        System.out.println(String.format("capabilities配置:%s", iosDriver.getCapabilities()));
        System.out.println("app启动!");
        return iosDriver;
    }




    /**
     * AndroidDriver使用单例模式创建
     * @return AndroidDriver
     * @throws MalformedURLException
     */
    public static AndroidDriver getInstance() throws IOException, InterruptedException {
        //StartAppiumServer();
        if (instance == null) {
            instance = new Driver();
        }
        return instance.basedriver();
    }


    /**
     * 启动appium server
     * 不需要命令行启动
     */
    public static void StartAppiumServer() throws IOException, InterruptedException {

        String logPath = System.getProperty("user.dir") + "/src/main/java/Log/appium.log";
        Common.clearInfoForFile(logPath);
        if (getPlatform.equals("Android")){
            startCmd = String.format("appium -a %s -p %s -bp %s --session-override --relaxed-security --log %s",
                    AppiumHost,AppiumPort,BootStrapPORT,logPath);
        }else if (getPlatform.equals("iOS")){
            startCmd = String.format("appium -a %s -p %s --session-override --relaxed-security --log %s",
                    AppiumHost,AppiumPort,logPath);
        }
        System.out.println("appium运行命令:" + startCmd);
        Runtime.getRuntime().exec(startCmd);
        Thread.sleep(AppiumWaitTime);
    }


    /**
     * kill appium server
     */
    public static void KillAppiumServer() throws IOException {

        String cmd = "lsof -i:4723";
        Process p = Runtime.getRuntime().exec(cmd);
        InputStream is = p.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("Appium")){
                System.out.println(line);
            }
        }
        is.close();
        reader.close();
        p.destroy();

    }


    /**
     * 初始化logger
     */
    public Logger  initlogger(){
        logger = Logger.getLogger(getClass());
        return logger;

    }



}

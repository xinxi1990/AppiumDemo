package Base;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import Untils.Common;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;



/**
 * @author xinxi
 * 创建appium driver
 */

public class Driver {

    public static Logger logger;
    private AndroidDriver driver;
    private static Driver instance;
    private static String RootPath = System.getProperty("user.dir");
    private static String CapsYaml = RootPath + "/src/main/java/Config/Caps.yaml";
    public static String CapsNewYaml = RootPath + "/src/main/java/Config/CapsNew.yaml";
    public static String chromedriverpath =  RootPath + "/src/main/java/drivers/chromedriver_51/chromedriver";


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

        Map map = Common.readYaml(CapsYaml);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("chromedriverExecutableDir","/Users/xinxi/Documents/idea_Projcet/AppiumDemo/chromedriver/");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), Common.Capsmap(map,capabilities));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // 设置全局隐示等待
        return driver;
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
    private static void StartAppiumServer() throws IOException, InterruptedException {

        String logPath = System.getProperty("user.dir") + "/src/main/java/Log/appium.log";
        Driver driver = new Driver();
        driver.initlogger().info("appium日志地址:" + logPath);
        Common.clearInfoForFile(logPath);
        String cmd = "appium --log " + logPath;
        driver.initlogger().info("appium运行命令:" + cmd);
        Runtime.getRuntime().exec(cmd);
        Thread.sleep(6000);

    }

    /**
     * kill appium server
     */
    private static void KillAppiumServer() throws IOException {

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

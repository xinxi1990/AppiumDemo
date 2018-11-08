package TestCase;


import Base.Driver;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class Test_iOS extends Driver {

    public IOSDriver iosDriver;
    public Driver driver;


    /**
     * 初始化PO
     */
    @BeforeClass
    public void setUp() throws IOException, InterruptedException {
        driver = new Driver();
        driver.KillAppiumServer();
        driver.StartAppiumServer();
        iosDriver = driver.iOSDriver();
    }


    @Test
    public void test_method(){
        System.out.println("iOS测试中");
    }

}

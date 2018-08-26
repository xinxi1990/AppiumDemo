# 项目结构

## Base
封装appium driver
封装有关appium操作的方法,显示等待

## Log
记录appium server日志

## Model
把页面操作封装到Page中,方便testCase调用

## TestCase
测试用例

## Untils
封装读写文件等常用操作

## WeApp
小程序测试


# 参考

# apium检查命令
```
安装：npm install appium-doctor
使用：appium-doctor
```


## 获取页面的Activity名字

```aidl
 adb logcat | grep Displayed 获取页面命令
 
``` 
## appium获取内存数据
```
 https://appiumpro.com/editions/5
```

## appium教程

```aidl
https://anikikun.gitbooks.io/appium-girls-tutorial/content/touch_actoin.html
```

## appium的github
```aidl
https://github.com/appium/appium/blob/master
```

## 关于适配
```
在Android8.0上支持不太好,推荐使用Android6.0测试
```

## chrome查看app中web页面
```$xslt
chrome://inspect/#devices
```

## chromedriver对照表
https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md

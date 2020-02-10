package com.wxyj.wuhan.task;

import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTask {
    private static int  checkTimes = 10;

    public static void main(String[] args)throws Exception {
        System.out.println(getDriver().toString());
        PhantomJSDriver driver = getDriver();


//        driver.get("https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_3");
//        driver.get("https://news.qq.com/zt2020/page/feiyan.htm");
        driver.get("https://ncov.dxy.cn/ncovh5/view/pneumonia");
//        driver.get("https://ncov.dxy.cn/ncovh5/view/pneumonia");
        System.out.println(driver.getPageSource());
        File screenShot = driver.getScreenshotAs(OutputType.FILE); //此段停止执行，提示 Connection reset
        System.out.println(screenShot);

        boolean loadsuccess = false;
        while(!loadsuccess){
            if(initOk(driver,checkTimes,By.id("root"))){
                loadsuccess = true;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.getPageSource();
//        VirusSummary_1-1-96_3lDxKW VirusSummary_1-1-96_2fhqEt
//        VirusSummary_1-1-96_3lDxKW VirusSummary_1-1-96_2fhqEt
//         driver.findElementByClassName("VirusSummary_1-1-89_3lDxKW VirusSummary_1-1-89_2fhqEt")
       // driver.findElement(By.className("Common_1-1-96_3lDRV2")).sendKeys(Keys.ENTER);


        WebElement webElement=driver.findElement(By.id("root"));

        System.out.println(webElement.getText());
        List<WebElement> list=webElement.findElements(By.xpath("./*"));
        //拿到了国内总统计数
        System.out.println( list.get(0).getAttribute("class"));
        System.out.println( list.get(1).getAttribute("class"));
        System.out.println(list.get(0).findElement(By.className("Common_1-1-96_3lDRV2")).getText());
        System.out.println(list.get(0).findElement(By.className("VirusSummary_1-1-96_popavK")).getText());
        System.out.println(list.get(0).findElement(By.className("VirusSummary_1-1-96_popavK")).getText());
        System.out.println(list.get(0).getAttribute("class"));
        System.out.println(webElement.getText());
        System.out.println(webElement);



    }

    public  static PhantomJSDriver getDriver()
    {
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
//        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        dcaps.setCapability("phantomjs.page.settings.XSSAuditingEnabled",true);
        dcaps.setCapability("phantomjs.page.settings.webSecurityEnabled",true);
        dcaps.setCapability("phantomjs.page.settings.localToRemoteUrlAccessEnabled",true);
        dcaps.setCapability("phantomjs.page.settings.XSSAuditingEnabled",true);
//
        dcaps.setCapability("phantomjs.page.settings.loadImages",true);
        //js支持
        dcaps.setJavascriptEnabled(true);

        //驱动支持
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,   "D:\\software\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        //dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,systemProps.getPhantomjsPath());
        dcaps.setCapability("phantomjs.page.settings.userAgent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11 ");
        dcaps.setCapability("phantomjs.page.customHeaders.User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11 ");

          dcaps.setCapability("ignoreProtectedModeSettings", true);
//        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
//        proxy.setProxyType(org.openqa.selenium.Proxy.ProxyType.MANUAL);
//        proxy.setHttpProxy("http://180.155.128.87:47593/");
//        dcaps.setCapability(CapabilityType.PROXY, proxy);



        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        driver.manage().timeouts().pageLoadTimeout(120,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(120,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().setSize(new Dimension(1920,1080));
        return driver;
    }

    public static boolean  initOk(WebDriver driver, int CheckTImes, By by){

        int index = CheckTImes;

        while(index > 0){

            try {
                System.out.println(driver.getPageSource());
                WebElement element = driver.findElement(by);
                if(element != null){
                    return true;
                }
            }catch ( Exception e){

            }finally {

            }

            try {
                Thread.sleep(5000);
            }catch (Exception e){

            }

            index --;
        }
        return false;
    }
}

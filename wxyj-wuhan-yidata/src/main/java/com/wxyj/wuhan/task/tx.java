package com.wxyj.wuhan.task;

import com.alibaba.fastjson.JSON;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class tx {


    public  static void main(String[] args) throws  Exception {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        Map map=new HashMap();
        List ChinaList = new ArrayList();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("headless");//无界面参数
        options.addArguments("no-sandbox");//禁用沙盒 就是被这个参数搞了一天
        WebDriver driver = new ChromeDriver(options);

//        WebDriver driver = new ChromeDriver();

        driver.get("https://news.qq.com/zt2020/page/feiyan.htm");
        // 获取 网页的 title
        List<WebElement> webElement=driver.findElements(By.className("recentNumber"));
        //拿到全国确诊人数
        String [] qg= webElement.get(1).getText().split("\n");
        for(String s:qg)
        {
            map.put("diecount",qg[10]);
            System.out.println(s);
        }
        //死亡人数
        map.put("dieCount",qg[10]);
        //死亡增加
        map.put("dieCountAdd",qg[9].replace("较上日+",""));
//        治愈人数
        map.put("CureCount",qg[7]);
        map.put("CureCountAdd",qg[6].replace("较上日+",""));
//        疑似
        map.put("SuspectedCount",qg[4]);
        map.put("SuspectedCountAdd",qg[3].replace("较上日+",""));
//        确诊
        map.put("DiagnosisCount",qg[1]);
        map.put("SuspectedCountAdd",qg[0].replace("较上日+",""));


        //拿海外人数
        List abroadlist = new ArrayList();
        List<WebElement> abroadwebElement=driver.findElement(By.className("abroadList")).findElements(By.xpath("./*"));
        for(int k=0;k<abroadwebElement.size();k++)
        {
            if(k==0)
            {
                abroadwebElement.get(k).getText();
                System.out.println(abroadwebElement.get(k).getText().split("，"));
                String [] s=abroadwebElement.get(k).getText().split("，");
                map.put("abroadDiagnosisCount",s[0].replace("海外国家确诊","").replace("例",""));
                map.put("abroadDieCount",s[1].replace("死亡","").replace("例",""));
            }
            if(k>1){
                Map m=new HashMap();

                abroadwebElement.get(k).getText();
                System.out.println(abroadwebElement.get(k).getText().split("\n"));
                String [] s = abroadwebElement.get(k).getText().split("\n");
                m.put("Country",s[0]);
                m.put("Diagnosis",s[1]);
                m.put("Cure",s[2]);
                m.put("Die",s[3]);
                abroadlist.add(m);

            }
        }
        map.put("abroadList",abroadlist);



        List<WebElement> clickList = driver.findElement(By.className("chianList")).findElements(By.xpath("./*"));

        //先点一遍
        for(int i=0;i<clickList.size();i++)
        {
            if(i>1)
            {

                clickList.get(i).click();
            }

        }
        List<WebElement> chianList = driver.findElement(By.className("chianList")).findElements(By.xpath("./*"));

        for(int i=0;i<chianList.size();i++)
        {
            if(i>1)
            {
                List <WebElement> placeItemWrap = chianList.get(i).findElements(By.xpath("./*"));
                Map m = new HashMap();
                List l = new ArrayList();
                for(int j=0;j<placeItemWrap.size();j++)
                {
                    if(j==0)
                    {
                        String [] s=placeItemWrap.get(j).getText().split("\n");
                        m.put("dq",s[0]);
                        m.put("xzqz",s[1]);
                        m.put("ljqz",s[2]);
                        m.put("zy",s[3]);
                        m.put("sw",s[4]);
                    }
                    else{
                        Map mm = new HashMap();
                        String [] s=placeItemWrap.get(j).getText().split("\n");
                        mm.put("dq",s[0]);
                        mm.put("xzqz",s[1]);
                        mm.put("ljqz",s[2]);
                        mm.put("zy",s[3]);
                        mm.put("sw",s[4]);
                        l.add(mm);
                    }

                }
                m.put("zlist",l);
                ChinaList.add(m);

            }

        }
        map.put("ChinaList",ChinaList);
        System.out.println(map.toString());
        System.out.println( driver.findElement(By.className("chianList")).findElements(By.className("placeItemWrap ")));

        //下面是最新动态
        List<WebElement> dtlist=driver.findElement(By.id("news")).findElements(By.xpath("./*"));
        List newslist = new ArrayList();
        for(int i=0;i<dtlist.size();i++)
        {

            if(i>1)
            {
                String   []  s = dtlist.get(i).getText().split("\n");
                Map map1 = new HashMap();
                map1.put("time",s[0].replace(":","："));
                map1.put("title",s[1]);
                map1.put("text",s[2]);
                map1.put("source",s[3]);
                newslist.add(map1);
                System.out.println("---"+dtlist.get(i).getText().split("\n"));

            }
        }
        map.put("news",newslist);
        //下面开始拿谣言

        List<WebElement> djpiyaoconlist = driver.findElement(By.id("rumor_slider")).findElements(By.xpath("./*")).get(1).findElements(By.xpath("./*"));

        List piyaocon = new ArrayList();
        //先点一遍
        for(int i=0;i<djpiyaoconlist.size();i++)
        {
            if(i>1)
            {
                Map m= new HashMap();
                djpiyaoconlist.get(i).click();
                List<WebElement> piyaoconlist = driver.findElement(By.id("piyaocon")).findElements(By.xpath("./*"));
                String [] s=piyaoconlist.get(i).getText().split("\n");
                System.out.println( piyaoconlist.get(i).getText());

                m.put("title",s[0]);
                m.put("text",s[1]);
                piyaocon.add(m);
//                for(int j =0 ;j<piyaoconlist.size();j++)
//                {
//
//                    System.out.println( piyaoconlist.get(i).getText());
//                }
            }

        }
        map.put("piyaocon",piyaocon);
        System.out.println(map);
        save(map);


        driver.quit();



    }

    public static void save(Map map) throws  Exception
    {
        StringBuffer str = new StringBuffer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        FileWriter fw = new FileWriter("C:\\wuhan\\"+LocalDateTime.now().format(formatter),false); //file是将要储存文件得地址，true/false是再次保存时是否覆盖上一次文件
        fw.write(JSON.toJSONString(map));
        fw.close();

    }
}

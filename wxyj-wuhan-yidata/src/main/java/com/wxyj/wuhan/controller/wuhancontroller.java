package com.wxyj.wuhan.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
@RestController
@RequestMapping("/wuhan")
public class wuhancontroller {
    @GetMapping("/wh")
    @ApiOperation(value = "实时疫情数据", notes = "实时疫情数据", httpMethod = "GET")
    public static Map  getdata() throws  Exception
    {
        List<File> fileList = new ArrayList<File>();

        File file = new File("C:\\wuhan\\");
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return null;
        }

        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
//                System.out.println(f.getAbsolutePath());
              //  readFile(f.getAbsolutePath());
            }
        }
        for (File f1 : fileList) {
            System.out.println(f1.getName());
        }
        List<File> list = getFileSort("C:\\wuhan\\");
        System.out.println(list);


         Gson gson = new Gson();
         Map<String, Object> map = new HashMap<String, Object>();
         map = gson.fromJson(readfile(list.get(0).toString()), map.getClass());
        Map maps = (Map)JSON.parse(readfile(list.get(0).toString()));
        System.out.println(map);

        return  map;
    }

    /**
     * 获取目录下所有文件(按时间排序)
     *
     * @param path
     * @return
     */
    public static List<File> getFileSort(String path) {

        List<File> list = getFiles(path, new ArrayList<File>());

        if (list != null && list.size() > 0) {

            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }

                }
            });

        }

        return list;
    }

    /**
     *
     * 获取目录下所有文件
     *
     * @param realpath
     * @param files
     * @return
     */
    public static List<File> getFiles(String realpath, List<File> files) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    public static String readfile( String pathname) throws  Exception
    {

        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        return line;


    }

    @GetMapping("/getCommunity")
    @ApiOperation(value = "疫情小区数据查询", notes = "疫情小区数据查询", httpMethod = "GET")
    public Map getCommunity (
            @ApiParam(name = "province", value = "省", required = true)
            @RequestParam String province,
            @ApiParam(name = "city", value = "市", required = true)
            @RequestParam String city,
            @ApiParam(name = "district", value = "区（查询全部区传入“全部”）", required = true)
            @RequestParam
                    String district) throws  Exception {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(readfile("C:\\wuhan1\\疫情小区数据"), map.getClass());
        Map<String,Map<String,Map<String,List>>> m1 = ( Map<String,Map<String,Map<String,List>>>) map.get("community");
        Map returnmap=  new HashMap();
        try{
            if(!("全部".equals(district)))
            {
                Map mapshi = new HashMap();
                mapshi.put(city,m1.get(province).get(city).get(district));
                Map mapsheng = new HashMap();
                mapsheng.put(province,mapshi);
                returnmap.put("community",mapsheng);
            }
            else
            {
                Map mapshi = new HashMap();
                mapshi.put(city,m1.get(province).get(city));
                Map mapsheng = new HashMap();
                mapsheng.put(province,mapshi);
                returnmap.put("community",mapsheng);
            }

        }
        catch (Exception e)
        {
            returnmap.clear();
            returnmap.put("error","查询条件传入错误");
            return returnmap;
        }

        return  returnmap;

    }

    @GetMapping("/getPosition")
    @ApiOperation(value = "三级联动数据，提供疫情小区查询条件", notes = "三级联动数据，提供疫情小区查询条件", httpMethod = "GET")
    public Map getPosition () throws  Exception {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(readfile("C:\\wuhan1\\疫情三级联动"), map.getClass());

        return  map;

    }


}

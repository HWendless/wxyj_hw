package com.wxyj.wuhan.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
                System.out.println(f.getAbsolutePath());
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

}

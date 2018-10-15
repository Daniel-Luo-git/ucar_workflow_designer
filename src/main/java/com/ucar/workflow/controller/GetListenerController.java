package com.ucar.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GetListenerController {

    @ResponseBody
    @RequestMapping("/getListener")
    public Map<String, Object> designer(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");


        File dir = new File(this.getClass().getResource("/").getPath());
        System.out.println(dir);

        /**
         * 获取目录下的所有文件名（包含包名）
         */
        List<String> classNameList = new ArrayList<String>();
        String packageName = "";
        getClassNames(dir,packageName,classNameList);
        Map<String,Object> obj = new HashMap();

        obj.put("names",classNameList);
        return obj;
    }


    public void getClassNames(File packge, String packgeName,List<String> classNames)
    {
        File[] files = packge.listFiles();
        String suffix = null;
        for(File file:files)
        {
            if(file.isDirectory())
                getClassNames(file,packgeName+file.getName()+".",classNames);
            if(file.isFile())
            {
                String fileName = file.getName();
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                String partName = fileName.substring(0,fileName.lastIndexOf("."));
                if(suffix.equals("class"))
                    classNames.add(packgeName+partName);
            }

        }

    }
}
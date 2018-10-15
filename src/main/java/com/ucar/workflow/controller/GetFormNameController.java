package com.ucar.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
public class GetFormNameController {

    @RequestMapping("/getFileName")
    @ResponseBody
    public String getName(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
//        String filePath = request.getServletContext().getRealPath("/");
//        System.out.println(request.getServletContext().getRealPath("/"));
        String thisPath = this.getClass().getResource("/").getPath();
        String path = thisPath.substring(0,thisPath.indexOf("target"))+"\\form";
//        String realPath = filePath.substring(0,filePath.indexOf("out"))+"/form";
        System.out.println(path);
        File file = new File(path);
        File[] files = file.listFiles();
        String names = "";
        for(File f : files){
            names += f.getName()+",";
        }
        names = names.substring(0,names.length()-1);
        return "{\"names\":"+"\""+names+"\"}";
    }


}

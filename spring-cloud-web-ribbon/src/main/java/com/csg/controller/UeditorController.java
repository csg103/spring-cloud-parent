package com.csg.controller;

import com.baidu.ueditor.ActionEnter;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/ueditor")
public class UeditorController implements ServletContextAware {
    private String rootPath;

    @RequestMapping("/do")
    @ResponseBody
    public String ueditorConfig(HttpServletRequest request, HttpServletResponse response) {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        return new ActionEnter(request,UeditorController.class.getClassLoader().getResource("static/js/ueditor/jsp/config.json").getPath()).exec();
        //        return new ActionEnter(request, rootPath, UeditorController.class.getClassLoader().getResource("static/js/ueditor/jsp/config.json").getPath()).exec();
    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("config".equals(action)) {
            OutputStream os = response.getOutputStream();
            IOUtils.copy(UeditorController.class.getClassLoader().getResourceAsStream("config.json"), os);
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> upload(HttpServletRequest request, @RequestParam CommonsMultipartFile upfile) throws IOException {
        Map<String, String> result = Maps.newHashMap();
        System.out.println(upfile.getFileItem().getFieldName());
        String path = getFilePath(upfile);
        File file = new File(path);
        System.out.println(path);
        String state = "SUCCESS";
        //返回类型
        String rootPath = request.getContextPath();
        result.put("url", rootPath + "/ueditor/show?filePath=" + path);
        result.put("size", String.valueOf(file.length()));
        result.put("type", file.getName().substring(file.getName().lastIndexOf(".")));
        result.put("state", state);
        return result;
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public void show(String filePath, HttpServletResponse response) throws IOException {

        File file = getFile(filePath);

        response.setDateHeader("Expires", System.currentTimeMillis() + 1000 * 60 * 60 * 24);
        response.setHeader("Cache-Control", "max-age=60");
        OutputStream os = response.getOutputStream();

        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            IOUtils.copy(is, os);
        } catch (FileNotFoundException e) {
            response.setStatus(404);
            return;
        } finally {
            if (null != is) {
                is.close();
            }
            if (null != os) {
                os.flush();
                os.close();
            }
        }
    }

    protected String getFilePath(CommonsMultipartFile uploadFile){
        String absolutePath = "D:/upload";
        File folder = new File(absolutePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String rawName = uploadFile.getFileItem().getName();
        String fileExt = rawName.substring(rawName.lastIndexOf("."));
        String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;
        File saveFile = new File(absolutePath + File.separator + newName);
        try {
            uploadFile.getFileItem().write(saveFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return absolutePath + "/" + newName;
    }

    protected File getFile(String path){
        File file = new File(path);
        return file;

    }
    @Override
    public void setServletContext(ServletContext servletContext) {
        rootPath = servletContext.getRealPath("/");
    }
}
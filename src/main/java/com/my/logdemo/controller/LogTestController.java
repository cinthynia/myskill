package com.my.logdemo.controller;

import com.google.gson.Gson;
import com.my.logdemo.annotation.WebLog;
import com.my.logdemo.pojo.User;
import com.my.logdemo.service.UserService;
import com.my.logdemo.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/log")
@Slf4j
public class LogTestController {
    @Autowired
    UserService userService;
    @RequestMapping("/debug")
    @ResponseBody
    public String printDebug() {

        String msg = "this is a debug msg !";
        log.debug(msg);
        return msg;
    }

    @RequestMapping("/info")
    @ResponseBody
    public String printInfo() {

        String msg = "this is a info msg !";
        log.info("url:/info,msg={}",msg);
        return msg;
    }

    @RequestMapping("/warn")
    @ResponseBody
    public String printWarn() {

        String msg = "this is a warn msg !";
        log.warn(msg);
        return msg;
    }

    @RequestMapping("/error")
    @ResponseBody
    public String printError() {

        String msg = "this is a error msg !";
        log.error(msg);
        return msg;
    }
    @RequestMapping("/xlog")
    @ResponseBody
    public String printXlog() {

        String msg = "this is a trace msg !";
        log.trace(msg);
        return msg;
    }
    @RequestMapping("/test")
    @ResponseBody
    public String getList() {
        String result = JsonUtil.toJSONString(userService.getAllUserList());
        System.out.println(result);
        return result;
    }
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(int id,String name) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        if(userService.inserUser(user) == 1){
            return "success";
        }
        return "fail" ;
    }
    @RequestMapping("/hello")
    public String getPage(ModelMap modelMap ) {
        modelMap.addAttribute("userList",userService.getAllUserList());
        return "hello";
    }
}

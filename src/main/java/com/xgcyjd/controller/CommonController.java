package com.xgcyjd.controller;

import javafx.application.Application;
import org.omg.CORBA.Request;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class CommonController {

    @RequestMapping(value = "/logout")
    @ResponseBody
    public HashMap<String,Object> logout(HttpServletRequest request){

        HttpSession session = request.getSession();

        HashMap<String,Object> hashMap = new HashMap<>();

        session.removeAttribute("manager");

        hashMap.put("state", 1);

        return hashMap;
    }

    @RequestMapping(value = "/checkLogin")
    @ResponseBody
    public HashMap<String,Object> checkLogin(HttpServletRequest request){

        HttpSession session = request.getSession();

        HashMap<String, Object> hashMap =new HashMap<>();
        if (session.getAttribute("manager")==null && session.getAttribute("user") ==null) {
            hashMap.put("state", 0);
            return hashMap;
        }else {
            hashMap.put("state",1);
            return hashMap;
        }
    }

}

package com.xgcyjd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class CommonController {

    @RequestMapping(value = "/logout")
    @ResponseBody
    public HashMap<String,Object> logout(HttpSession session){

        HashMap<String,Object> hashMap = new HashMap<>();

        session.removeAttribute("manager");

        hashMap.put("state", 1);

        return hashMap;
    }

//    @RequestMapping(value = "/checkLogin")
//    @ResponseBody
//    public HashMap<String,Object> checkLogin(HttpSession session){
//
//        HashMap<String, Object> hashMap =new HashMap<>();
//        if (session.getAttribute("manager")==null && session.getAttribute("user") ==null) {
//            hashMap.put("state", 0);
//            return hashMap;
//        }else {
//            hashMap.put("state",1);
//            return hashMap;
//        }
//    }

}

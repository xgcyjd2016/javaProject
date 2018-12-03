package com.xgcyjd.controller;

import com.xgcyjd.po.Archives;
import com.xgcyjd.po.ArchivesDetail;
import com.xgcyjd.service.ArchivesService;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

@Controller
public class ArchivesController
{

    @Autowired
    ArchivesService archivesService;

    @ResponseBody
    @RequestMapping("/addArchivesDetail")
    public void addArchives(@RequestBody  ArchivesDetail archivesDetail) throws Exception
    {
        archivesService.addArchives(archivesDetail);
    }

    @ResponseBody
    @RequestMapping(value = "/findArchivesDetailByAuthor")
    public HashMap<String , Object> findArchivesDetailByAuthor(@RequestParam(value = "author") int author ,
                                                               @RequestParam(value = "pageNow") int pageNow) throws Exception
    {
        HashMap<String,Object> hashMap;

        hashMap = archivesService.findArchivesDetailByAuthor(author, pageNow);

        return hashMap;
    }

    @ResponseBody
    @RequestMapping("/uanager/checkArchivesDetail")
    public HashMap<String, Object> checkArchivesDetail(@RequestBody ArchivesDetail archivesDetail) throws Exception
    {
        archivesService.checkArchivesDetail(archivesDetail);
        if(archivesDetail.getState() == 2){
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BS-e4cc2aa833da412abd2290bd744bd35b");
//            goEasy.publish("");
        }

        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("archivesDetail" ,archivesDetail);

        return hashMap;
    }
    @ResponseBody
    @RequestMapping("/deleteArchivesDetail")
    public HashMap<String,Object> deleteArchivesDetail( int id) throws Exception
    {
        archivesService.deleteArchivesDetailById(id);

        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("status",0);

        hashMap.put("id", id);

        return hashMap;
    }
    @ResponseBody
    @RequestMapping(value = "/readArchives")
    public HashMap<String,Object> readArchives( int user_id, int archives_detail_id) throws Exception{

        Archives archives = new Archives();

        HashMap<String,Object> hashMap = new HashMap<>();

        archives.setUser_id(user_id);
        archives.setArchives_detail_id(archives_detail_id);

        archivesService.readArchives(archives);

        hashMap.put("user_id",user_id);
        hashMap.put("archives_detail_id",archives_detail_id);

        return hashMap;
    }
    @ResponseBody
    @RequestMapping("/sendArchives")
    public HashMap<String,Object> sendArchives(ArchivesDetail archivesDetail) throws Exception{

        archivesService.sendArchives(archivesDetail);

        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("archivesDetail" ,archivesDetail);

        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BS-e4cc2aa833da412abd2290bd744bd35b");
        goEasy.publish("generalUser",archivesDetail.toString(),new PublishListener(){
            @Override
            public void onSuccess(){
                System.out.println("您的消息已发送，等待审核");
                hashMap.put("status","1");
            }
            @Override
            public void onFailed(GoEasyError error){
                System.out.println("消息发送失败");
                if(new Integer(error.getCode()) == 408){
                    hashMap.put("status",-1);//发送数据到GoEasy失败，可能是因为网络原因，不能与GoEasy建立连接
                }else if(new Integer(error.getCode()) == 500){
                    hashMap.put("status",-2);//GoEasy服务器错误
                }else if(new Integer(error.getCode()) == 900){
                    hashMap.put("status",-3);//超过最大连接数
                }else if(new Integer(error.getCode()) == 901){
                    hashMap.put("status",-4);//消息数量已用完
                }else if(new Integer(error.getCode()) == 902){
                    hashMap.put("status",-5);//restful请求参数没有使用utf-8进行编码
                }
            }
        });
        return hashMap;
    }
}

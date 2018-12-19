package com.xgcyjd.controller;

import com.xgcyjd.po.Archives;
import com.xgcyjd.po.ArchivesDetail;
import com.xgcyjd.po.Result;
import com.xgcyjd.service.ArchivesService;
//import io.goeasy.GoEasy;
//import io.goeasy.publish.GoEasyError;
//import io.goeasy.publish.PublishListener;

import com.xgcyjd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

@RestController
public class ArchivesController
{

    @Autowired
    ArchivesService archivesService;

    @Autowired
    UserService userService;

    @RequestMapping("/addArchivesDetail")
    public Result addArchives(@RequestBody  ArchivesDetail archivesDetail) throws Exception
    {
        archivesService.addArchives(archivesDetail);
        return Result.getInstance(1,"添加成功");
    }

    @RequestMapping("/getAll")
    public Result getAll() throws Exception
    {
        HashMap<String,Object> hashMap = new HashMap<>();

        hashMap.put("user",userService.getAll());

        return Result.getInstance(1,"获取成功").setData("user",userService.getAll());
    }

    @RequestMapping(value = "/findArchivesDetailByAuthor")
    public HashMap<String, Object> findArchivesDetailByAuthor(@RequestParam(value = "author") int author ,
                                                              @RequestParam(value = "pageNow") int pageNow) throws Exception
    {
        HashMap<String,Object> hashMap;

        hashMap = archivesService.findArchivesDetailByAuthor(author, pageNow);

        return hashMap;
    }

    @RequestMapping("/uanager/checkArchivesDetail")
    public Result checkArchivesDetail(@RequestBody ArchivesDetail archivesDetail) throws Exception
    {
        archivesService.checkArchivesDetail(archivesDetail);
//        if(archivesDetail.getState() == 2){
//            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BS-e4cc2aa833da412abd2290bd744bd35b");
////            goEasy.publish("");
//        }

        Result result = Result.getInstance(1,"").setData("archivesDetail",archivesDetail);

        return result;
    }

    @RequestMapping("/deleteArchivesDetail")
    public Result deleteArchivesDetail(int id) throws Exception
    {
        archivesService.deleteArchivesDetailById(id);

        Result result =Result.getInstance(1,"删除成功").setData("id",id);

        return result;
    }

    @RequestMapping(value = "/readArchives")
    public Result readArchives(int user_id, int archives_detail_id) throws Exception{

        Archives archives = new Archives();

        HashMap<String,Object> hashMap = new HashMap<>();

        archives.setUser_id(user_id);
        archives.setArchives_detail_id(archives_detail_id);
        archives.setState(1);

        archivesService.readArchives(archives);

        Result result = Result.getInstance(1,"成功")
                .setData("user_id",user_id)
                .setData("archives_detail_id",archives_detail_id);

        return result;
    }

    @RequestMapping("/sendArchives")
    public Result sendArchives(ArchivesDetail archivesDetail) throws Exception{

        archivesService.sendArchives(archivesDetail);

        Result result = Result.getInstance(1,"成功");
//        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BS-e4cc2aa833da412abd2290bd744bd35b");
//        goEasy.publish("generalUser",archivesDetail.toString(),new PublishListener(){
//            @Override
//            public void onSuccess(){
//                System.out.println("您的消息已发送，等待审核");
//                hashMap.put("status","1");
//            }
//            @Override
//            public void onFailed(GoEasyError error){
//                System.out.println("消息发送失败");
//                if(new Integer(error.getCode()) == 408){
//                    hashMap.put("status",-1);//发送数据到GoEasy失败，可能是因为网络原因，不能与GoEasy建立连接
//                }else if(new Integer(error.getCode()) == 500){
//                    hashMap.put("status",-2);//GoEasy服务器错误
//                }else if(new Integer(error.getCode()) == 900){
//                    hashMap.put("status",-3);//超过最大连接数
//                }else if(new Integer(error.getCode()) == 901){
//                    hashMap.put("status",-4);//消息数量已用完
//                }else if(new Integer(error.getCode()) == 902){
//                    hashMap.put("status",-5);//restful请求参数没有使用utf-8进行编码
//                }
//            }
//        });
        return result;
    }
}

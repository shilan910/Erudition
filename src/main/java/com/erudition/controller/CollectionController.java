package com.erudition.controller;

import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.dao.CollectionDao;
import com.erudition.entity.MessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tsj on 16-5-27.
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    @Qualifier("collectionDao")
    CollectionDao collectionDao;

    @RequestMapping(value = "/addtocollection/{fid}" , method = RequestMethod.GET)
    @ResponseBody
    public MessageStatus addtoCollection(Model model,HttpSession session,@PathVariable("fid") int fid){

        boolean flag=false;
        String message="";
        int status=0;
        UserEntity user = (UserEntity)session.getAttribute("loginUser");
        List<FilesEntity> collections = collectionDao.getByUid(user.getId());

        System.out.println("begin check file!!!");
        for(FilesEntity file:collections){
            System.out.println(file.getId());
            if(file.getId()==fid){
                flag = true;
                break;
            }
        }
        System.out.println("文件最终判断!!!");
        if(!flag){
            collectionDao.createARecord(fid,user.getId());
            System.out.println("插入成功！");
            message = "成功添加至常用目录";
            status = 1;  //成功添加时状态为1
        }else{
            System.out.println("已经存在的记录！");
            message = "常用目录已存在该文件";
            status = 0;  //重复添加添加时状态为0
        }

       return new MessageStatus(message,status);
    }

    @RequestMapping(value = "/showcollections" , method = RequestMethod.GET)
    public String showCollections(Model model,HttpSession session){

        model.addAttribute("showcollections",collectionDao.getByUid((int)session.getAttribute("userid")));
        session.setAttribute("flagofcollection",1);
        session.setAttribute("cateIsActive",1);
        System.out.println("show collections!");
        return "index";

    }
}

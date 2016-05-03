package com.erudition.controller;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.dao.CategoryDao;
import com.erudition.dao.ResourcesDao;
import com.erudition.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsj on 16-4-28.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @RequestMapping(value = "/firstcates" , method = RequestMethod.GET)
    public String getFirstCategory(Model model){
        List<CategoryEntity> firstCategories = new ArrayList<CategoryEntity>();
        firstCategories = categoryDao.getFirstCategory();
        model.addAttribute("firstCates",firstCategories);
        return "index";
    }

    @RequestMapping(value = "/getSecondCategory/{id}" , method = RequestMethod.GET)
    public String getSecondCategory(HttpSession httpSession,@PathVariable ("id") int firstId){
        List<CategoryEntity> secondCategories = new ArrayList<CategoryEntity>();
        secondCategories = categoryDao.getSecondCategoryByFirst(firstId);
        httpSession.setAttribute("category2",secondCategories);
        return "index";
    }

    @RequestMapping(value = "/getThirdCategory/{fid}/{sid}" , method = RequestMethod.GET)
    public String getThirdCategory(HttpSession httpSession,@PathVariable ("fid") int firstId,@PathVariable ("sid") int secondId){
        List<CategoryEntity> secondCategories = new ArrayList<CategoryEntity>();
        secondCategories = categoryDao.getThirdCategoryByFS(firstId, secondId);
        httpSession.setAttribute("category3",secondCategories);
        return "index";
    }
}

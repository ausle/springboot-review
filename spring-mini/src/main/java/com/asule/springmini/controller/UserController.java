package com.asule.springmini.controller;

import com.asule.springmini.Controller;
import com.asule.springmini.RequestMapping;
import com.asule.springmini.ResponseBody;
import com.asule.springmini.entity.User;

/* ------ UserController类 ------- */
@Controller
@RequestMapping("/user")
public class UserController {


    // 测试@ResponseBody的功效
    @RequestMapping("/get")
    @ResponseBody
    public User get(){
        return new User(1,"竹子爱熊猫","男",18);
    }

    // 跳转首页的方法
    @RequestMapping("/")
    public String test(){


        return "index";
    }

    // 测试重定向的功效
    @RequestMapping("/edit")
    public String toEdit(){


        return "redirect:edit";
    }

    public String TEST(){


        return null;
    }
}



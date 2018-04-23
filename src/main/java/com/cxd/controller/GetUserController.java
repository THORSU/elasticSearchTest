package com.cxd.controller;

import com.cxd.pojo.User;
import com.cxd.service.UserService;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by root on 17-7-13.
 */
@Controller
@RequestMapping("/night")
public class GetUserController {
    private static Logger log = Logger.getLogger(GetUserController.class);
    @Autowired
    private UserService userService;
    @RequestMapping(value="/getUser.from", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object getUser(HttpServletRequest request, HttpServletResponse response) {
        User u=userService.getUserById("1");
        System.out.println(u.toString());
        return "1";
    }
}

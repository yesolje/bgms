package com.yesol.bgms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @RequestMapping("/")
    public String goMainPage(Model model, HttpServletRequest request) {
        return "main";
    }

    @RequestMapping("/gameMatch")
    public String goGameMatchPage(Model model, HttpServletRequest request) {
        return "gameMatch";
    }

    @RequestMapping("/attendance")
    public String goAttendancePage(Model model, HttpServletRequest request) {
        return "attendance";
    }

    @RequestMapping("/addGuest")
    public String goAddGuestPage(Model model, HttpServletRequest request) {
        return "addGuest";
    }

    @RequestMapping("/admin")
    public String goAdminPage(Model model, HttpServletRequest request) {
        return "admin";
    }
}

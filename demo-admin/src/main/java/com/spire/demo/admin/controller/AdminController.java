package com.spire.demo.admin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin/name")
    public String adminName() {
        return "real-admin";
    }
}

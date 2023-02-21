package com.spire.demo.web.controller;

import com.spire.demo.core.bean.CoreBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    public TaskClient taskClient;

    @Autowired
    public AdminClient adminClient;

    @GetMapping("/name")
    public String  getName() {
        return "admin: " + adminClient.getAdminName() + "  task name: " + taskClient.getTaskName() + "  task admin name: " + taskClient.getAdminName();
    }
}


@FeignClient("demo-admin")
interface AdminClient {

    @GetMapping("/admin/name")
    String getAdminName();
}

@FeignClient("demo-task")
interface TaskClient {

    @GetMapping("/task/name")
    String getTaskName();

    @GetMapping("/task/admin")
    String getAdminName();
}


package com.spire.demo.task.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    public AdminClient adminClient;

    @GetMapping("/task/admin")
    public String taskAdminName(){
        String msg = adminClient.findAll();
        return "task admin name :" + msg;
    }

    @GetMapping("/task/name")
    @SentinelResource(value = "getName",blockHandler = "blockHandlerMethodByLimit")
    public String taskName(){
        return "task name";
    }

    public String blockHandlerMethodByLimit(BlockException e){
        return "被限流";
    }
}


@FeignClient("demo-admin")
interface AdminClient {
    @GetMapping("/admin/name")
    String findAll();
}

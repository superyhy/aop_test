package com.mu.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mu.demo.annotation.DeleteAnnotation;
import com.mu.demo.annotation.PermissionAnnotation;
import com.mu.demo.annotation.UpdateAnnotation;
import com.mu.demo.entity.TestEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author guozhengMu
 * @version 1.0
 * @date 2020/10/16 11:38
 * @description
 * @modify
 */

@RestController
@RequestMapping(value = "/permission")
public class TestController {

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @PermissionAnnotation
    public JSONObject getGroupList(@RequestBody JSONObject request) {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @GetMapping("/deleteTest/{id}")
    @DeleteAnnotation
    public JSONObject deleteGroupTest(@PathVariable("id") Integer id) {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @GetMapping("/updateTest")
    @UpdateAnnotation
    public JSONObject updateTest(@RequestParam("id") Integer id,@RequestParam("name") String name) {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}

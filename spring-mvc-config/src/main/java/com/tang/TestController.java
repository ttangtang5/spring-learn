package com.tang;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意有默认上下文路径
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
        return "test mvc";
    }
}

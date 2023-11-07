package codenotfound.grpcdemo;

import codenotfound.grpcdemo.client.HelloWorldClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weifengwu
 * @create 2023/11/7 10:29
 */
@RestController
public class TestController {
    @Autowired
    private HelloWorldClient helloWorldClient;

    @RequestMapping("/hello")
    public Object hello() {
        return helloWorldClient.sayHello("nihao", "wolf");
    }
}

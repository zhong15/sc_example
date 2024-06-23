/*
 * MIT License
 *
 * Copyright (c) 2024 Zhong
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package zhong.my.web.bridge.server.web;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Zhong
 * @since 0.0.1
 */
@Controller
public class QueryController {
    private static final Logger log = LoggerFactory.getLogger(QueryController.class);

    @Value("${server.port}")
    private Integer port;
    @Autowired
    private EurekaClientConfigBean eurekaClientConfigBean;
    @Autowired
    private MyWebInterface myWeb;

    @GetMapping("/getEurekaServerUrl")
    @ResponseBody
    public Map<String, String> getEurekaServerUrl() {
        log.info("getEurekaServerUrl()");
        return eurekaClientConfigBean.getServiceUrl();
    }

    @GetMapping("/echo")
    @ResponseBody
    public String echo(@RequestParam("s") String s) {
        log.info("echo(@RequestParam(\"s\") String s)");
        return "my web bridge: " + port + " -> " + myWeb.echo(s);
    }

    @HystrixCommand(fallbackMethod = "defaultPrint")
    @GetMapping("/print")
    @ResponseBody
    public String print(@RequestParam("s") String s) {
        log.info("print(@RequestParam(\"s\") String s)");
        if (Objects.equals(s, "123"))
            throw new IllegalArgumentException();
        log.info("print(@RequestParam(\"s\") String s) 2");
        return "my web bridge: " + port + " -> " + s;
    }

    public String defaultPrint(String s) {
        log.info("defaultPrint(String s)");
        return "my web bridge: " + port + " -> hystrix DEFAULT -> " + s;
    }
}

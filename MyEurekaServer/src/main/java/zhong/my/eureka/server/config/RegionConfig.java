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

package zhong.my.eureka.server.config;

import java.util.HashMap;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.eureka.EurekaServerConfig;

/**
 * @author Zhong
 * @since 0.0.1
 */
// @Configurable
@Configuration
@AutoConfigureBefore(EurekaServerAutoConfiguration.class)
public class RegionConfig {
    private static final Logger log = LoggerFactory.getLogger(RegionConfig.class);

    @PostConstruct
    public void init() {
        log.info("==============================");
        log.info("==============================");
        log.info("==============================");
        log.info("==============================");
    }

    /**
     * fix getRemoteRegionAppWhitelist(String regionName) NullPointerException
     */
    @Bean
    @ConditionalOnMissingBean
    public EurekaServerConfig createEurekaServerConfig(EurekaClientConfig clientConfig) {
        log.info("============================== init EureServerConfig");
        EurekaServerConfigBean bean = new EurekaServerConfigBean();
        if (clientConfig.shouldRegisterWithEureka()) {
            bean.setRegistrySyncRetries(5);
        }
        bean.setRemoteRegionAppWhitelist(new HashMap<>());
        log.info("============================== init EureServerConfig");
        return bean;
    }
}

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

package zhong.my.zuul.gateway.server.backup;

import org.springframework.stereotype.Component;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.appinfo.MyDataCenterInfo;
import com.netflix.discovery.BackupRegistry;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

/**
 * @author Zhong
 * @since 0.0.1
 */
@Component
public class BackupInstanceInfoRegister implements BackupRegistry {
    private Applications apps = new Applications();

    public BackupInstanceInfoRegister() {
        Application myWeb = new Application("my-web");
        apps.addApplication(myWeb);

        InstanceInfo info = InstanceInfo.Builder
                .newBuilder()
                .setAppName("my-web")
                .setVIPAddress("my-web")
                .setSecureVIPAddress("my-web")
                .setInstanceId("my-web-1")
                .setHostName("localhost")
                .setIPAddr("127.0.0.1")
                .setPort(8091)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setStatus(InstanceStatus.UP)
                .build();
        myWeb.addInstance(info);
        info = InstanceInfo.Builder
                .newBuilder()
                .setAppName("my-web")
                .setVIPAddress("my-web")
                .setSecureVIPAddress("my-web")
                .setInstanceId("my-web-2")
                .setHostName("localhost")
                .setIPAddr("127.0.0.1")
                .setPort(8092)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setStatus(InstanceStatus.UP)
                .build();
        myWeb.addInstance(info);
        info = InstanceInfo.Builder
                .newBuilder()
                .setAppName("my-web")
                .setVIPAddress("my-web")
                .setSecureVIPAddress("my-web")
                .setInstanceId("my-web-3")
                .setHostName("localhost")
                .setIPAddr("127.0.0.1")
                .setPort(8093)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setStatus(InstanceStatus.UP)
                .build();
        myWeb.addInstance(info);
        info = InstanceInfo.Builder
                .newBuilder()
                .setAppName("my-web")
                .setVIPAddress("my-web")
                .setSecureVIPAddress("my-web")
                .setInstanceId("my-web-4")
                .setHostName("localhost")
                .setIPAddr("127.0.0.1")
                .setPort(8094)
                .setDataCenterInfo(new MyDataCenterInfo(DataCenterInfo.Name.MyOwn))
                .setStatus(InstanceStatus.UP)
                .build();
        myWeb.addInstance(info);
    }

    @Override
    public Applications fetchRegistry() {
        return apps;
    }

    @Override
    public Applications fetchRegistry(String[] arg0) {
        return apps;
    }
}

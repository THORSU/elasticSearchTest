package com.cxd.controller.createIndex;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @Author:QuincySu
 * @Date:2018/4/20
 */
public class CreateIndex {
    public void CreateIndex(){
        try {
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "elasticsearch").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("172.16.167.128", 9300));
            //todo 假如只有一条数据
            Objects object = new Objects();
            String data = JSON.toJSONString(object);

            IndexResponse response = client.prepareIndex("share", "objects").setSource(data).get();
            if (response.isCreated()) {
                System.out.println("创建成功!");
            }
            client.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

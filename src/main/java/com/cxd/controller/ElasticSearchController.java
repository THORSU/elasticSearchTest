package com.cxd.controller;

import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by su on 17-10-26.
 */
@Controller
@RequestMapping("/night")
public class ElasticSearchController {
    private static Logger log = Logger.getLogger(ElasticSearchController.class);

    @RequestMapping(value="/Search.from", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object search(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
            String GoodsName=request.getParameter("Search");
            Settings settings= ImmutableSettings.settingsBuilder()
                    .put("cluster.name","elasticsearch").build();
            TransportClient client=new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("172.16.167.128",9300));

            SearchRequestBuilder responsebuilder = client.prepareSearch("share").setTypes("objects");
            SearchResponse res=responsebuilder.setQuery(QueryBuilders.wildcardQuery("GoodsName","*"+GoodsName.toLowerCase()+"*"))
                    .setFrom(0).setSize(10).setExplain(true).execute().actionGet();



            SearchHits hits=res.getHits();
            String n="";
//            long num=hits.totalHits();
//            int n=(int)num;
//            String[] result=new String[n];
            if (hits.totalHits()>0){
//                System.out.println(hits.totalHits());
                for (int i=0;i<hits.totalHits();i++) {
//                    System.out.println(hit.getSource());
                    n+= (hits.getAt(i).getSource().get("GoodsName").toString()+",");
//                    System.out.println(result.length);
                }
                return n;

            }else {
                return "0";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}

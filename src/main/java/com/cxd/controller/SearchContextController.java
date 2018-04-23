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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by su on 17-10-26.
 */
@Controller
@RequestMapping("/night")
public class SearchContextController {
    private static Logger log = Logger.getLogger(SearchContextController.class);

    @RequestMapping(value = "/SearchContext.from", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody
    Object search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String GoodsName = request.getParameter("GoodsName");
            Settings settings = ImmutableSettings.settingsBuilder()
                    .put("cluster.name", "elasticsearch").build();
            TransportClient client = new TransportClient(settings);
            client.addTransportAddress(new InetSocketTransportAddress("172.16.167.128", 9300));

            SearchRequestBuilder responsebuilder = client.prepareSearch("share").setTypes("objects");
            SearchResponse res = responsebuilder.setQuery(QueryBuilders.matchPhraseQuery("GoodsName", GoodsName))
                    .setFrom(0).setSize(10).setExplain(true).execute().actionGet();
            SearchHits hits=res.getHits();
            if(hits.totalHits()>0){
                for (SearchHit hit:hits){
                    String result1=hit.getSource().get("GoodsName").toString();
                    String result2=hit.getSource().get("price").toString();
                    String result="商品的详细信息："+ "商品名=" + result1 +
                            ",价格=" + result2  ;
                    return result;
                }

            }else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}

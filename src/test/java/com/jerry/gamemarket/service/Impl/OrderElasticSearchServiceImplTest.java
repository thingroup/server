//package com.jerry.gamemarket.service.Impl;
//
//import com.jerry.gamemarket.entity.OrderMaster;
//import com.jerry.gamemarket.service.OrderElasticSearchService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//
//import java.util.List;
//
//import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
//import static org.junit.Assert.*;
//
///**
// * author by 李兆杰
// * Date 2018/11/23
// */
//public class OrderElasticSearchServiceImplTest {
//    @Autowired
//    OrderElasticSearchService orderElasticSearchService;
//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//    @Test
//    public void count() throws Exception {
//    }
//
//    @Test
//    public void save() throws Exception {
//    }
//
//    @Test
//    public void getAll() throws Exception {
//    }
//
//    @Test
//    public void getByOrderId() throws Exception {
//        List<OrderMaster> list = orderElasticSearchService.getByOrderId("1542800572003107544");
//        System.out.println(list);
//    }
//    @Test
//    public void test() {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("1542800572003107544")).build();
//        List<OrderMaster> orderMasters = elasticsearchTemplate.queryForList(searchQuery, OrderMaster.class);
//        for (OrderMaster orderMaster : orderMasters) {
//            System.out.println(orderMaster.toString());
//        }
//    }
//    @Test
//    public void pageQuery() throws Exception {
//    }
//
//}
//package com.jerry.gamemarket.service.Impl;
//
//import com.jerry.gamemarket.search.OrderMasterElasticsearchDao;
//import com.jerry.gamemarket.entity.OrderMaster;
//import com.jerry.gamemarket.service.OrderElasticSearchService;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.stereotype.Service;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * author by 李兆杰
// * Date 2018/11/23
// */
//@Service
//public class OrderElasticSearchServiceImpl implements OrderElasticSearchService {
//
//    @Autowired
//    OrderMasterElasticsearchDao orderMasterElasticsearchDao;
//    @Override
//    public long count() {
//        return orderMasterElasticsearchDao.count();
//    }
//
//    @Override
//    public OrderMaster save(OrderMaster orderMaster) {
//        return orderMasterElasticsearchDao.save(orderMaster);
//    }
//
//    @Override
//    public Iterable<OrderMaster> getAll() {
//        return orderMasterElasticsearchDao.findAll();
//    }
//
//    @Override
//    public List<OrderMaster> getByOrderId(String orderId) {
//        List<OrderMaster> list = new ArrayList<>();
//
//        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("orderId", orderId);
//
//        Iterable<OrderMaster> iterable = orderMasterElasticsearchDao.search(matchQueryBuilder);
//
//        iterable.forEach(e->list.add(e));
//
//        return list;
//    }
//
//    @Override
//    public Page<OrderMaster> pageQuery(Integer pageNo, Integer pageSize, String kw) {
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//
//                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
//
//                .withPageable(PageRequest.of(pageNo, pageSize))
//
//                .build();
//
//        return orderMasterElasticsearchDao.search(searchQuery);
//    }
//}

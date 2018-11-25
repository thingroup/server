package com.jerry.gamemarket.service.Impl;

import com.jerry.gamemarket.convertor.OrderMaster2OrderDTOConvertor;
import com.jerry.gamemarket.convertor.OrderMaster2StatisticOrderDTOConvertor;
import com.jerry.gamemarket.dao.OrderDetailDao;
import com.jerry.gamemarket.dao.OrderMasterDao;
import com.jerry.gamemarket.dto.CartDTO;
import com.jerry.gamemarket.dto.OrderDTO;
import com.jerry.gamemarket.dto.StatisticOrderDTO;
import com.jerry.gamemarket.entity.OrderDetail;
import com.jerry.gamemarket.entity.OrderMaster;
import com.jerry.gamemarket.entity.ProductInfo;
import com.jerry.gamemarket.enums.OrderStatusEnums;
import com.jerry.gamemarket.enums.PayStatusEnums;
import com.jerry.gamemarket.enums.ResultEnum;
import com.jerry.gamemarket.exception.GameException;
import com.jerry.gamemarket.service.*;
import com.jerry.gamemarket.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private CanteenService canteenService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private PayService payService;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
//        1.查询商品（数量，价格）
        String orderId = KeyUtil.genUniquekey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new GameException(ResultEnum.PRODUCT_NOT_EXIST);
            }
//        2.计算订单原始总价
//        (红包优惠价问题遗留，设置一个满减)
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
//            查看该用户是否拥有优惠券
//            查看订单详情中的产品id可使用的优惠券
//            根据优惠券类型进行优惠
//            if(orderDetail.getProductName())

//            订单详情入数据库
            orderDetail.setDetailId(KeyUtil.genUniquekey());
//            订单生成时就生成一个订单id
            orderDetail.setOrderId(orderId);
//            复制属性内容到订单详情
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);
        }
//        写入订单数据库
            OrderMaster orderMaster = new OrderMaster();
            orderDTO.setOrderId(orderId);
            BeanUtils.copyProperties(orderDTO,orderMaster);
            orderMaster.setOrderAmount(orderAmount);
            orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
            orderMaster.setPayStatus(PayStatusEnums.WAIT.getCode());
            orderMasterDao.save(orderMaster);
//         CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//         cartDTOList.add(cartDTO);
// 4.      扣库存
            List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                    .stream().map(e ->
                    new CartDTO(e.getProductId(),e.getProductQuantity()))
                    .collect(Collectors.toList());
            productService.decreaseStock(cartDTOList);
////         扣包间数量
//             Integer roomQuantity = orderDTO.getRoomQuantity() ;
//             String canteenid = orderDTO.getCanteenId();
//            canteenService.decreaseStock(canteenid,roomQuantity);
            return orderDTO;
    }
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.getOne(orderId);
        if (orderMaster==null){
            throw  new GameException(ResultEnum.Order_NOT_EXIST);
        }
        List<OrderDetail> orderDetailsList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailsList)){
            throw  new GameException(ResultEnum.OrderDetail_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailsList);
        return orderDTO;
    }


    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList=OrderMaster2OrderDTOConvertor.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster =new OrderMaster();
 //    判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new GameException(ResultEnum.ORDER_STATUS_ERROR);
        }
//    修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult==null){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new GameException(ResultEnum.UPDATE_STATUS_ERROR);
        }
//    返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情，orderDTO={}",orderDTO);
            throw new GameException(ResultEnum.UPDATE_STATUS_ERROR);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
//        返回包间库存
//        Integer roomQuantity = orderDTO.getRoomQuantity() ;
//        String canteenid = orderDTO.getCanteenId();
//        canteenService.increaseStock(canteenid,roomQuantity);

//    如果已支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnums.FINISH.getCode())){
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
//        判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={}，orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new GameException(ResultEnum.ORDER_STATUS_ERROR);
        }
//        修改状态
        orderDTO.setOrderStatus(OrderStatusEnums.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult==null){
            log.error("【完结订单】更新失败，orderMaster={}",orderMaster);
            throw new GameException(ResultEnum.UPDATE_STATUS_ERROR);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
//        判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())) {
            log.error("【支付订单完成】订单状态不正确，orderId={}，orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new GameException(ResultEnum.ORDER_STATUS_ERROR);
        }

//        判断支付状态
        if (!orderDTO.getOrderStatus().equals(PayStatusEnums.WAIT.getCode())) {
            log.error("【支付订单完成】订单状态不正确，orderDTO={}", orderDTO);
            throw new GameException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
//        修改支付状态
        orderDTO.setPayStatus(PayStatusEnums.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】更新失败，orderMaster={}", orderMaster);
            throw new GameException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        return orderDTO;
    }
    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterDao.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConvertor.convert(orderMasters.getContent());

        return new PageImpl<>(orderDTOList , pageable , orderMasters.getTotalElements());

    }

//    @Override
//    public Page<OrderDTO> findbyNameAndCanteenId(String buyerName, String canteenId,Pageable pageable) {
//        return orderMasterDao.findByNameAndCanteenId(buyerName,canteenId,pageable);
//    }

    @Override
    public Page<OrderDTO> findByCase(String tip, String text, Pageable pageable) {
        return orderMasterDao.findByCase(tip,text,pageable);
    }

    @Override
    public List<StatisticOrderDTO> statis() {
        List<?> result=orderMasterDao.StatisOrderCount();
        List<StatisticOrderDTO> statisticOrderDTOList =new ArrayList<>();
        for(int i=0;i<result.size();i++){
            StatisticOrderDTO statisticOrderDTO =new StatisticOrderDTO();
            Object[] obj = (Object[])result.get(i);
            statisticOrderDTO.setCanteenId(obj[0].toString());
            statisticOrderDTO.setOrderNum(Integer.parseInt(obj[1].toString()));
            statisticOrderDTO.setCanteenName(obj[2].toString());
            statisticOrderDTOList.add(statisticOrderDTO);
        }
        return statisticOrderDTOList;
    }


}

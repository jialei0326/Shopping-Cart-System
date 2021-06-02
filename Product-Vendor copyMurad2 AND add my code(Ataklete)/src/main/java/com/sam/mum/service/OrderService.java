package com.sam.mum.service;


import com.sam.mum.model.Order;
import com.sam.mum.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void creatOrder(Order order){
        orderRepository.save(order);
    }
}

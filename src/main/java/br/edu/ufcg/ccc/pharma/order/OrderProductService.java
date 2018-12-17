package br.edu.ufcg.ccc.pharma.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderProductService {

    private OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProduct save(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }

    public List<OrderProduct> findAll() {
        return this.orderProductRepository.findAll();
    }
}

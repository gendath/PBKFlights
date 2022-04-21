package com.pbk.flights.Services;

import com.pbk.flights.Dao.OrderDao;
import com.pbk.flights.Entities.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServicesImpl implements OrderServices {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> getAllOrdersFromUser(int userID) {
        return orderDao.findAll().stream().filter(o -> o.getOrderID() == userID).collect(Collectors.toList());
    }

    @Override
    public Order getOrderByID(int orderID) {
        var o = orderDao.findById(orderID).orElse(null);
        if (o == null) throw new RuntimeException("Invalid Order ID");
        return o;
    }

    @Override
    public Order addOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        orderDao.save(order);
        return true;
    }

    @Override
    public boolean removeOrder(int orderID) {
        orderDao.deleteById(orderID);
        return true;
    }

}

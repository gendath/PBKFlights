package com.pbk.flights.Services;

import com.pbk.flights.Entities.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderServices {

    List<Order> getAllOrders();
    List<Order> getAllOrdersFromUser(int userID);
    Order getOrderByID(int orderID);
    Order addOrder(Order order);
    boolean updateOrder(Order order);
    boolean removeOrder(int orderID);
}

package com.maveric.orderms.dao;

import com.maveric.orderms.domain.CreatedOrder;

import java.util.Optional;

public interface IOrderDAO {
    CreatedOrder save(CreatedOrder createdOrder);
    Optional<CreatedOrder> findById(long id);
}

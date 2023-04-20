package com.food.service.repository;

import com.food.service.model.TypePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<TypePayment, Long> {

}

package com.example.net.net.Repository;

import com.example.net.net.entity.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceProductRepository extends JpaRepository<ServiceProduct, Integer> {
    @Query("SELECT sp FROM ServiceProduct sp " +
            "JOIN FETCH sp.product " +
            "WHERE sp.service.serviceId = :serviceId")
    List<ServiceProduct> findByServiceIdWithProduct(@Param("serviceId") Integer serviceId);

    @Query("SELECT sp FROM ServiceProduct sp " +
            "JOIN FETCH sp.service s " +
            "JOIN FETCH s.session sess " +
            "JOIN FETCH sp.product p " +
            "WHERE sess.sessionId = :sessionId")
    List<ServiceProduct> findBySessionIdWithProduct(@Param("sessionId") Integer sessionId);
    List<ServiceProduct> findAllByService_ServiceId(Integer serviceId);
  //  List<ServiceProduct> findAllByService_SessionId(Integer sessionId);
}

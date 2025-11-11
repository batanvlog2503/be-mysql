package com.example.net.net.Repository;

import com.example.net.net.entity.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT sp FROM ServiceProduct sp "+
    "Where sp.service.session.sessionId = :sessionId " +
    "And sp.product.productId = :productId")
    Optional<ServiceProduct> findBySessionIdAndProductId(@Param("sessionId") Integer sessionId, @Param("productId") Integer productId);

    @Modifying
    @Query("Delete From ServiceProduct sp "+
    "Where sp.service.session.sessionId = :sessionId " +
    "And sp.product.productId = :productId")
    void deleteBySessionIdAndProductId(@Param("sessionId") Integer sessionId, @Param("productId") Integer productId);
    List<ServiceProduct> findAllByService_ServiceId(Integer serviceId);
  //  List<ServiceProduct> findAllByService_SessionId(Integer sessionId);
}

package com.example.net.net.Repository;

import com.example.net.net.entity.Service;
import com.example.net.net.entity.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query("SELECT sp FROM ServiceProduct sp JOIN FETCH sp.product WHERE sp.service.serviceId = :serviceId")
    List<ServiceProduct> findByServiceIdWithProduct(@Param("serviceId") Integer serviceId);

    List<Service> findByCustomer_Id(Integer customerId);
//    Dùng query method Spring Data JPA, tự động generate query SELECT * FROM service WHERE customer_id = ?.

    List<Service> findBySession_SessionId(Integer sessionId);
}

package com.example.book.store.repository;

import com.example.book.store.dto.response.CommonListRes;
import com.example.book.store.entities.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,String> {

    @Query(value = "select bill from Bill bill join Customer  customer on customer.id = bill.customerId where " +
            " (bill.userId in :employeeIdList) and " +
            " (:paymentType is null or bill.paymentType = :paymentType) and" +
            " (:time is null or bill.time between :startTime and :endTime) and" +
            " (bill.customerId in :customerIdList) and" +
            " (:customerPhone is null or customer.phoneNumber = :customerPhone)")
    Page<Bill> findBill(List<String> employeeIdList,
                        String paymentType,
                        Date startTime,
                        Date endTime,
                        List<String> customerIdList,
                        String customerPhone,
                        Pageable pageable);
}

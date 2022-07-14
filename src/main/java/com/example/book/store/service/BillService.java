package com.example.book.store.service;

import com.example.book.store.dto.response.BillAndCustomerRes;
import com.example.book.store.dto.response.CommonListRes;
import com.example.book.store.dto.response.CustomBillRes;

import java.util.Date;
import java.util.List;

public interface BillService {
    CommonListRes getAllBill(Integer pageNo, Integer limit);

    CustomBillRes getBillDetail(String billId);


    CommonListRes findBill(String id,
                                      String employeeName,
                                      String paymentType,
                                      Date startTime,
                                      Date endTime,
                                      String customerName,
                                      String customerPhone,
                                      Integer pageNo,
                                      Integer limit);

    CommonListRes findBillVer2(String id,
                               String employeeName,
                               String paymentType,
                               Date starTime,
                               Date endTime,
                               String customerName,
                               String customerPhone,
                               Integer pageNo,
                               Integer limit);
}

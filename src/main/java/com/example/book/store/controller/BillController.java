package com.example.book.store.controller;

import com.example.book.store.constants.BillAuthorConstants;
import com.example.book.store.dto.response.BillAndCustomerRes;
import com.example.book.store.dto.response.CommonListRes;
import com.example.book.store.dto.response.CustomBillRes;
import com.example.book.store.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;

    @PreAuthorize("hasAuthority(\""+ BillAuthorConstants.BILL_VIEW_ALL + "\")")
    @GetMapping("view-all")
    public CommonListRes getAllBill(@RequestParam Integer pageNo,
                                    @RequestParam Integer limit){
        return billService.getAllBill(pageNo,limit);
    }

    @PreAuthorize("hasAuthority(\""+ BillAuthorConstants.BILL_VIEW_DETAIL + "\")")
    @GetMapping("view-detail")
    public CustomBillRes viewBillDetail(@RequestParam String billId){
        return  billService.getBillDetail(billId);
    }

    @GetMapping("find-bill")
    public CommonListRes findBill(  @RequestParam String  id,
                                               @RequestParam String  employeeName,
                                               @RequestParam String  paymentType,
                                               @RequestParam Date    startTime,
                                               @RequestParam Date    endTime,
                                               @RequestParam String  customerName,
                                               @RequestParam String  customerPhone,
                                               @RequestParam Integer pageNo,
                                               @RequestParam Integer limit){
        return billService.findBill(
                id,
                employeeName,
                paymentType,
                startTime,
                endTime,
                customerName,
                customerPhone,
                pageNo,
                limit);

    }

}

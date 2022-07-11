package com.example.book.store.service;
import com.example.book.store.dto.ApiResponse;
import com.example.book.store.entities.Bill;
import com.example.book.store.repository.BillRepository;
import com.example.book.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class SellServiceImpl implements SellService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillRepository billRepository;

    @Override
    public ApiResponse<?> sell(Map<String, Integer> purchaseMap, String paymentType) {
        int total = 0;
        // check map is correct or not
        for(String s : purchaseMap.keySet()){
            if(productRepository.findById(s).isEmpty()){
                return  new ApiResponse<>("Id" + s + "không tồn tại trên hệ thống, kiểm tra lại",0);
            }
            total += productRepository.findById(s).get().getSellPrice()
                    *purchaseMap.get(s);
        }

        Bill bill = new Bill(UUID.randomUUID().toString(),total,"psh",paymentType,new Date());
        billRepository.save(bill);
        return new ApiResponse<>("Thanh toán thành công",total);
    }
}

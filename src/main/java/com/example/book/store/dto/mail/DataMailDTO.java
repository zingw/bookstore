package com.example.book.store.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataMailDTO {
    private String to;
    private String subject;
    private Map<String,Object> props;
}

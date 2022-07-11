package com.example.book.store.model.token;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Getter
@Setter
public class EncodeToken {
    private String userName;
    private List<String> authorList;
}

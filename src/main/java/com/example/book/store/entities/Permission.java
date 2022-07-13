package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "permission")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Permission {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

}

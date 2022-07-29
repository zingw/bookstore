package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Role {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name ="name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}

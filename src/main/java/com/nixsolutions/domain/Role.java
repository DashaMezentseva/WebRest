package com.nixsolutions.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role {


    //    @GeneratedValue(strategy = GenerationType.AUTO)
    //    @Column(name = "roleId")
    @Id
    private Long roleId;
//    @Column(name = "name", nullable = false)
//    @NotNull
    private String name;

    public Role() {
    }

    public Role(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }


}

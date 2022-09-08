package com.dropit.backend_drop_it.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @Enumerated(EnumType.STRING)
    private EAuthority name;

    public Authority() {
    }

    public Authority(EAuthority name) {
        this.name = name;
    }

    public EAuthority getName() {
        return name;
    }
}

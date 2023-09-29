package org.oualid.ssi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_DB_ROLE")
    @SequenceGenerator(name = "GEN_SEQ_DB_ROLE",sequenceName = "SEQ_DB_ROLE")
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String name;

    @OneToOne(mappedBy = "role",fetch = FetchType.EAGER)
    User user;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<Authority> authorities ;

}

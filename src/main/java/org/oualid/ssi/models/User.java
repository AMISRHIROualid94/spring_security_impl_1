package org.oualid.ssi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_DB_USER")
    @SequenceGenerator(name = "GEN_SEQ_DB_USER",sequenceName = "SEQ_DB_USER")
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "algorithm")
    private EncryptionAlgorithm algorithm;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Authority> authorities;
}

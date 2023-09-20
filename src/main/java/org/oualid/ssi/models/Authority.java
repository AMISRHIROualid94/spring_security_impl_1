package org.oualid.ssi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "db_Authority")
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_DB_AUTHORITY")
    @Column(name = "authority_id")
    private Long id;

    @Column(name = "authority_name")
    private String name; //role

    @ManyToOne
    @JoinColumn(name = "user")
    User user;



}

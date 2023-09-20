package org.oualid.ssi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "db_product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_DB_PRODUCT")
    @SequenceGenerator(name = "GEN_SEQ_DB_PRODUCT",sequenceName = "SEQ_DB_PRODUCT")
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
}

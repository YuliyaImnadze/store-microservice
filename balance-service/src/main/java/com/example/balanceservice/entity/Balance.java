//package com.example.balanceservice.entity;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//@Entity
//@Table(name = "balance")
//public class Balance {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "id", nullable = false)
//    private UUID id;
//
//    @Column(name = "value", nullable = false, precision = 19, scale = 2)
//    private BigDecimal value;
//
//    public BigDecimal getValue() {
//        return value;
//    }
//
//    public void setValue(BigDecimal value) {
//        this.value = value;
//    }
//
//    public UUID getId() {
//        return id;
//    }
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//}

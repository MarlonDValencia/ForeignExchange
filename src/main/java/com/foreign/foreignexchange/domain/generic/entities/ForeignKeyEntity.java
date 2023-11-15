package com.foreign.foreignexchange.domain.generic.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Foreign_exchange")
public class ForeignKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;
    public String abbreviation;
    public String fullname;

}

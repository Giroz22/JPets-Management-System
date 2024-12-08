package com.jpets.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StoreEntity {   
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    private String name;

    @OneToMany(
        mappedBy = "store",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PetEntity> pets;

    @OneToMany(
        mappedBy = "store",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ProductEntity> products;
}

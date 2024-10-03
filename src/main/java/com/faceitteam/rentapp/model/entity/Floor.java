package com.faceitteam.rentapp.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "floors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String svgPath;
    private boolean isAvailable = true;
    private Integer number;

    @ElementCollection
    private List<String> photos = new ArrayList<>(); // url || base64

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    private List<Office> offices;
}

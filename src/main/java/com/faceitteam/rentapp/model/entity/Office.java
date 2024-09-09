package com.faceitteam.rentapp.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "offices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ElementCollection
    private List<String> photos; // url || base64
    private BigDecimal hourlyPrice;
    private BigDecimal dailyPrice;
    private BigDecimal monthlyPrice;
    private BigDecimal longTermPrice;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Availability> availabilities;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

}

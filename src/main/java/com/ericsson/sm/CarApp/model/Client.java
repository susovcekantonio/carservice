package com.ericsson.sm.CarApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @SequenceGenerator(name = "client_sequence", allocationSize = 1)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "firstname")

    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "oib")
    private String oib;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name="streetnumber")
    private String streetNumber;

    @Column(name = "zipcode")
    private String zipCode;

    @Column(name = "country")
    private String country;

    @OneToMany(mappedBy = "client")
    private List<Car> cars;
}

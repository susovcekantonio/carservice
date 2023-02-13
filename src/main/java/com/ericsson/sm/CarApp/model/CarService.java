package com.ericsson.sm.CarApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="carservice")
@Getter
@Setter
public class CarService {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carservice_sequence")
    @SequenceGenerator(name="carservice_sequence", allocationSize = 1)
    @Column(name="carservice_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonIgnore
    private Car car;

    @Column(name = "dateofservice")
    private Date dateOfService;
    @Column(name = "workerfirstname")
    private String workerFirstName;
    @Column(name="workerlastname")
    private String workerLastName;
    @Column(name="workdescription")
    private String workDescription;
    @Column(name = "price")
    private Float price;
    @Column(name = "ispaid")
    private boolean isPaid;
}

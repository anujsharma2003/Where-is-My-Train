package com.FindsTrains.WhereIsMyTrain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor


public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String stationName;

    private String stationCode;



    public Station(Long id, String stationName, String stationCode) {
        this.id = id;
        this.stationName = stationName;
        this.stationCode = stationCode;
    }
}

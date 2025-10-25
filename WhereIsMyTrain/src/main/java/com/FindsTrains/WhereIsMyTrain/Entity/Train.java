package com.FindsTrains.WhereIsMyTrain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String trainName;
    private String trainNumber;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TrainSchedule> trainSchedule;


    public Train(Long id, String trainName, String trainNumber, List<TrainSchedule> trainSchedule) {
        this.id = id;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.trainSchedule = trainSchedule;
    }
}

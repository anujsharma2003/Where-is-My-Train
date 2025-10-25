package com.FindsTrains.WhereIsMyTrain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
// Corrected TrainSchedule.java
@Entity
@Getter @Setter @ToString @NoArgsConstructor
public class TrainSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departureTime;
    private String arrivalTime;

    @ManyToOne
    @JoinColumn(name = "train_id")

    @JsonManagedReference
    private Train train;

    @ManyToOne
    @JoinColumn(name = "source_id") // Keep the column name for the database
    private Station sourceId;        // <-- **CHANGED TO CAMELCASE**

    @ManyToOne
    @JoinColumn(name = "destination_id") // Keep the column name for the database
    private Station destinationId;// <-- **CHANGED TO CAMELCASE**

    public TrainSchedule(Long id, String departureTime, String arrivalTime, Train train, Station sourceId, Station destinationId) {
        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.train = train;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
    }
}
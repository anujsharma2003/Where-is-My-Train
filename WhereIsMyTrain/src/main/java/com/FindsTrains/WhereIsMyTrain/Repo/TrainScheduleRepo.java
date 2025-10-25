package com.FindsTrains.WhereIsMyTrain.Repo;

import com.FindsTrains.WhereIsMyTrain.Entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleRepo extends JpaRepository<TrainSchedule,Long> {
   

    List<TrainSchedule> findBySourceIdStationCodeAndDestinationIdStationCode(String sourceCode, String destinationCode);

    List<TrainSchedule> findBySourceIdStationNameAndDestinationIdStationName(String sourceName, String destinationName);
}
package com.FindsTrains.WhereIsMyTrain.Service;

import com.FindsTrains.WhereIsMyTrain.Entity.TrainSchedule;
import com.FindsTrains.WhereIsMyTrain.Repo.TrainScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TrainSearchService {
    private TrainScheduleRepo trainScheduleRepo;

    public List<TrainSchedule> findTrainByStationCode(String sourceCode, String destinationCode) {
        return trainScheduleRepo.
                // UPDATED: The repository method name was corrected to match the
                // camelCase properties in TrainSchedule entity (sourceId and destinationId)
                // and the nested properties in the Station entity (StationCode).
                // This fixed the 'No property found' Spring Data JPA error.
                        findBySourceIdStationCodeAndDestinationIdStationCode(sourceCode, destinationCode);
    }

    public List<TrainSchedule> findTrainByStationName(String sourceName, String destinationName) {
        return trainScheduleRepo.
                // UPDATED: The repository method name was corrected to match the
                // camelCase properties in TrainSchedule entity (sourceId and destinationId)
                // and the nested properties in the Station entity (StationName).
                        findBySourceIdStationNameAndDestinationIdStationName(sourceName, destinationName);
    }
}
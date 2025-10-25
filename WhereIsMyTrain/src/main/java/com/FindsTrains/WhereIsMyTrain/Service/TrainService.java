package com.FindsTrains.WhereIsMyTrain.Service;

import com.FindsTrains.WhereIsMyTrain.Entity.Train;
import com.FindsTrains.WhereIsMyTrain.Repo.TrainRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private TrainRepo trainRepo;

    public TrainService(TrainRepo trainRepo){
        this.trainRepo = trainRepo;
    }
    public List<Train> getAllTrains() {
        return trainRepo.findAll();
    }

    public Train addTrain(Train train) {
        return trainRepo.save(train);
    }
}

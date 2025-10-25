package com.FindsTrains.WhereIsMyTrain.Controller;

import com.FindsTrains.WhereIsMyTrain.Entity.Train;
import com.FindsTrains.WhereIsMyTrain.Service.TrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {


    private TrainService trainService;

    TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }

    @PostMapping
    public Train addTrain(@RequestBody Train train) {
        return trainService.addTrain(train);
    }



}

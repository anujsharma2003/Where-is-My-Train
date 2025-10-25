package com.FindsTrains.WhereIsMyTrain.Controller;

import com.FindsTrains.WhereIsMyTrain.Entity.TrainSchedule;
import com.FindsTrains.WhereIsMyTrain.Service.TrainSearchService;
import com.FindsTrains.WhereIsMyTrain.Service.TrainService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class TrainSearchController {
    private TrainSearchService trainSearchService;
    public TrainSearchController (TrainSearchService trainSearchService){
        this.trainSearchService = trainSearchService;
    }
    @GetMapping("/byCode")
    public List<TrainSchedule> findTrainByStationCode(@RequestParam String sourceCode,
                                                      @RequestParam String destinationCode ){
        return trainSearchService.findTrainByStationCode(sourceCode.toUpperCase(),destinationCode.toUpperCase());
    }
    @GetMapping("/byName")
    public List<TrainSchedule> findTrainByStationName(@RequestParam String sourceName,
                                                      @RequestParam String destinationName ){
        return trainSearchService.findTrainByStationName(sourceName.toUpperCase(),destinationName.toUpperCase());
    }

}

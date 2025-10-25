package com.FindsTrains.WhereIsMyTrain.Controller;

import com.FindsTrains.WhereIsMyTrain.Entity.Station;
import com.FindsTrains.WhereIsMyTrain.Entity.Train;
import com.FindsTrains.WhereIsMyTrain.Entity.TrainSchedule;
import com.FindsTrains.WhereIsMyTrain.Repo.StationRepo;
import com.FindsTrains.WhereIsMyTrain.Repo.TrainRepo;
import com.FindsTrains.WhereIsMyTrain.Repo.TrainScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class Test {
    @Autowired
    StationRepo stationRepo;
    @Autowired
    TrainRepo trainRepo;
    @Autowired
    TrainScheduleRepo trainScheduleRepo;

    @GetMapping
    public void test() {
        Station mumbai = new Station(null, "Mumbai", "BCT");
        Station chennai = new Station(null, "Chennai", "MAS");
        Station kolkata = new Station(null, "Kolkata", "HWH");
        Station bengaluru = new Station(null, "Bengaluru", "SBC");
        stationRepo.saveAll(List.of(mumbai,chennai,kolkata,bengaluru));

        Train mumbaiExpress = new Train(null, "Mumbai Express", "11042", null);
        Train chennaiMail = new Train(null, "Chennai Mail", "12657", null);
        Train howrahRajdhani = new Train(null, "Howrah Rajdhani", "12301", null);
        Train bangaloreUdyan = new Train(null, "Udyan Express", "11301", null);
        trainRepo.saveAll(List.of(chennaiMail,mumbaiExpress,howrahRajdhani,bangaloreUdyan));

        TrainSchedule schedule1 = new TrainSchedule(
                null,
                "06:00",
                "14:30",
                mumbaiExpress,
                mumbai,
                chennai
        );

        TrainSchedule schedule2 = new TrainSchedule(
                null,
                "19:45",
                "07:10",
                chennaiMail,
                chennai,
                kolkata
        );

        TrainSchedule schedule3 = new TrainSchedule(
                null,
                "17:00",
                "05:30",
                howrahRajdhani,
                kolkata,
                bengaluru
        );

        TrainSchedule schedule4 = new TrainSchedule(
                null,
                "08:15",
                "20:45",
                bangaloreUdyan,
                bengaluru,
                mumbai
        );
        trainScheduleRepo.saveAll(List.of(schedule1,schedule2,schedule3,schedule4));
        System.out.println("Data Inserted in DB");
    }

}

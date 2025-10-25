package com.FindsTrains.WhereIsMyTrain.Repo;

import com.FindsTrains.WhereIsMyTrain.Entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepo extends JpaRepository<Train, Long> {
}

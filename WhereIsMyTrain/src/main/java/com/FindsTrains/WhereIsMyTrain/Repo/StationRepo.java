package com.FindsTrains.WhereIsMyTrain.Repo;

import com.FindsTrains.WhereIsMyTrain.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station,Long> {
}

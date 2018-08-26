package my.agro.transportation.management.dao.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.agro.transportation.management.dao.entity.Truck;

public interface TruckRepository extends JpaRepository<Truck, String> {
	@Query("SELECT t FROM Truck t WHERE exists( " + 
			"SELECT cr.carrier FROM CarrierRegion cr " +
			"WHERE cr.carrier = t.carrier AND cr.region = :region" +	
		" )")
	Set<Truck> findTrucksByRegion(@Param("region") String region);
	
	Set<Truck> findByDriver(String driver);
}
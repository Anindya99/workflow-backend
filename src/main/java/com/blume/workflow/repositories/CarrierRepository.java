package com.blume.workflow.repositories;

import com.blume.workflow.models.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    @Query("SELECT c FROM Carrier c JOIN c.routes route WHERE route.id.origin = :origin AND route.id.destination = :destination")
    List<Carrier> findCarriersByRoute(@Param("origin") String origin, @Param("destination") String destination);
}

package com.catchboock.catchbook.repository;


import com.catchboock.catchbook.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    List<Species> findAllSpeciesByFishTypeId(Long id);
}

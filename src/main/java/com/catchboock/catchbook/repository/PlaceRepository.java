package com.catchboock.catchbook.repository;

import com.catchboock.catchbook.entity.Place;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    boolean existsByPlace(@NotNull String place);
}

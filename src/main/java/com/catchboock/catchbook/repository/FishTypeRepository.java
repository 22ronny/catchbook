package com.catchboock.catchbook.repository;

import com.catchboock.catchbook.entity.FishType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishTypeRepository extends JpaRepository<FishType, Long> {
}

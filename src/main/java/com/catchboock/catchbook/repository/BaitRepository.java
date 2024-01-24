package com.catchboock.catchbook.repository;


import com.catchboock.catchbook.entity.Bait;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaitRepository extends JpaRepository<Bait, Long> {

    List<Bait> findAllBaitByFishTypeId(Long id);
}

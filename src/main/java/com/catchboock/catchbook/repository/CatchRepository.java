package com.catchboock.catchbook.repository;

import com.catchboock.catchbook.entity.Catch;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatchRepository extends JpaRepository<Catch, Long> {

    List<Catch> findAll(Specification<Catch> spec);
}

package com.catchboock.catchbook.service;

import com.catchboock.catchbook.entity.Catch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CatchSpecifications {

    public static Specification<Catch> withFilters(Long fishTypeId, LocalDateTime startDate, LocalDateTime endDate, Long speciesId, Long baitId, Long placeId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (fishTypeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("species").get("fishType").get("id"), fishTypeId));
            }

            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("catchTime"), startDate));
            }

            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("catchTime"), endDate));
            }

            if (speciesId != null) {
                predicates.add(criteriaBuilder.equal(root.get("species").get("id"), speciesId));
            }

            if (baitId != null) {
                predicates.add(criteriaBuilder.equal(root.get("bait").get("id"), baitId));
            }

            if (placeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("place").get("id"), placeId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

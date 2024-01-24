package com.catchboock.catchbook.entity;

import com.catchboock.catchbook.entity.Bait;
import com.catchboock.catchbook.entity.Species;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime catchTime;

    @ManyToOne
    @JoinColumn(name = "bait_id")
    private Bait bait;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double weight;

    @Column(columnDefinition = "DOUBLE PRECISION")
    private double length;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}


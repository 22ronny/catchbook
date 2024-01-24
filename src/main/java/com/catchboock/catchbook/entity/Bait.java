package com.catchboock.catchbook.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fishType_id")
    private FishType fishType;

    @NotNull
    @Column(nullable = false, unique = true, length = 25)
    private String bait;

    @Override
    public String toString() {
        return bait;
    }
}

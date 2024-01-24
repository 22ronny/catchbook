package com.catchboock.catchbook.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fishType_id")
    private FishType fishType;

    @NotNull
    @Column(nullable = false, unique = true, length = 25)
    private String name;

    @Override
    public String toString() {
        return name;
    }
}

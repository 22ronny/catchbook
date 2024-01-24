package com.catchboock.catchbook.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FishType {

    @Id
    private Long id;

    private String fishType;

    @Override
    public String toString() {
        return fishType;
    }
}

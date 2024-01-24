package com.catchboock.catchbook.DTO;

import com.catchboock.catchbook.entity.Bait;
import com.catchboock.catchbook.entity.Place;
import com.catchboock.catchbook.entity.Species;

public class CatchDto {
    private Long id;
    private Bait bait;
    private Species species;
    private double weight;
    private double length;
    private Place place;
    private String formattedCatchTime;

    public CatchDto(Long id, Bait bait, Species species, double weight, double length, Place place, String formattedCatchTime) {
        this.id = id;
        this.bait = bait;
        this.species = species;
        this.weight = weight;
        this.length = length;
        this.place = place;
        this.formattedCatchTime = formattedCatchTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bait getBait() {
        return bait;
    }

    public void setBait(Bait bait) {
        this.bait = bait;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getFormattedCatchTime() {
        return formattedCatchTime;
    }

    public void setFormattedCatchTime(String formattedCatchTime) {
        this.formattedCatchTime = formattedCatchTime;
    }
}

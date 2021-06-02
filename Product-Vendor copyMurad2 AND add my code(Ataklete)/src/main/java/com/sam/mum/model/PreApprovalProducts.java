package com.sam.mum.model;

import javax.persistence.*;

@Entity
public class PreApprovalProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double price ;
    private double weight;

    private String imageName;
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private boolean isApproved;

    public PreApprovalProducts(double price, double weight, String imageName, String name, String description) {
        this.price = price;
        this.weight = weight;
        this.imageName = imageName;
        this.name = name;
        this.description = description;


        isApproved = false;
    }

    public PreApprovalProducts() {
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}

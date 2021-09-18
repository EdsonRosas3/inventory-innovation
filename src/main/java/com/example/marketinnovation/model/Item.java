package com.example.marketinnovation.model;

import com.example.marketinnovation.dto.ItemDto;

import javax.persistence.Entity;


@Entity
public class Item extends ModelBase<ItemDto> {
    private String name;
    private String code;
    private Byte[] image;
    /*@OneToOne(targetEntity = SubCategory.class)
    private SubCategory subCategory;

    @OneToOne(targetEntity = SubCategory.class)
    private SubCategory subCategorySt2;*/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }


}

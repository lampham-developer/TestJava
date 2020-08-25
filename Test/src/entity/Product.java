/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Product implements Serializable{
    private String name;
    private String category_tag;

    public Product() {
    }

    public Product(String name, String category_tag) {
        this.name = name;
        this.category_tag = category_tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_tag() {
        return category_tag;
    }

    public void setCategory_tag(String category_tag) {
        this.category_tag = category_tag;
    }

    @Override
    public String toString() {
        return "name : " + name + ", category_tag : " + category_tag + "\n";
    }

    
    
}

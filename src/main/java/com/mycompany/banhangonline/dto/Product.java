package com.mycompany.banhangonline.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;
    @Column(name = "pname")
    private String pname;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "amount")
    private int amount;
    @Column(name = "thumbnail")
    private String link;
    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

    public Product(int pid, String pname, String description, double price, int amount, String link, Category category) {
        this.pid = pid;
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.link = link;
        this.category = category;
    }

    public Product(String pname, String description, double price, int amount, String link, Category category) {
        this.pname = pname;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.link = link;
        this.category = category;
    }

    public Product() {
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the pname
     */
    public String getPname() {
        return pname;
    }

    /**
     * @param pname the pname to set
     */
    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
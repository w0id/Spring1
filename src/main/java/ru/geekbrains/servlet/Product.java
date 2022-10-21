package ru.geekbrains.servlet;

import java.text.DecimalFormat;
import java.util.Random;

public class Product {
    private Integer id = 0;
    private String title;
    private String cost;

    public Product(final String title) {
        Random random = new Random();
        DecimalFormat costFormat = new DecimalFormat(".##");
        this.id = random.nextInt(99);
        this.title = title + random.nextInt(99);
        this.cost = costFormat.format(999.99 * random.nextDouble());
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCost() {
        return cost;
    }
}

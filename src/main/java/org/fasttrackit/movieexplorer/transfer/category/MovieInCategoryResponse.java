package org.fasttrackit.movieexplorer.transfer.category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MovieInCategoryResponse {

    private long id;
    private String title;
    private BigDecimal rate;
    private List<String> categories = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}

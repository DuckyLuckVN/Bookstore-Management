
package com.duan.model;


public class Category 
{
    private String id;
    private String categoryTitle;
    private String categoryDescription;

    public Category(String id, String categoryTitle, String categoryDescription) {
        this.id = id;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    
    
}

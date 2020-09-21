package com.fufu.ecommerce.domain;

import java.util.Objects;
import java.util.Set;

/**
 * A Class that represents product categories
 *
 * @author fuat.karakus
 */
public class Category {

    private Category parent;
    private String title;
    private Set<Category> children;

    public Category(String title) {
        Objects.requireNonNull(title);
        this.title = title;
    }

    public Category(Category parent, String title, Set<Category> children) {
        Objects.requireNonNull(title);
        this.parent = parent;
        this.title = title;
        this.children = children;
    }

    public Category(Category parent, String title) {
        Objects.requireNonNull(title);
        this.parent = parent;
        this.title = title;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return title.equals(category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, title, children);
    }
}

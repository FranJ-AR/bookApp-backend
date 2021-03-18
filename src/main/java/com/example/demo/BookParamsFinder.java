package com.example.demo;

public class BookParamsFinder {
	
	private String titleSubstring;
	
	private Integer authorId;
	
	private Integer categoryId;
	
	private Integer subcategoryId;

	public BookParamsFinder() {
		super();
	}

	public String getTitleSubstring() {
		return titleSubstring;
	}

	public void setTitleSubstring(String titleSubstring) {
		this.titleSubstring = titleSubstring;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
	

}

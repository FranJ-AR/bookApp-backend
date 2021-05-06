package com.example.model;

public class BookParamsFinder {

	private String titleSubstring;

	private Long authorId;

	private Long categoryId;

	private Long subcategoryId;

	public BookParamsFinder() {
		super();
	}

	public String getTitleSubstring() {
		return titleSubstring;
	}

	public void setTitleSubstring(String titleSubstring) {
		this.titleSubstring = titleSubstring;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Long subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

}

package com.universal.book.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private String isbnNo;
	private String bookName;
	private Double bookPrice;
	private Integer bookRef;
	private Boolean status;

	

}

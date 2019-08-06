package com.universal.book.dao.controller.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universal.book.dao.model.Book;

@RestController
@RequestMapping("/book")
public class BookController {

	private List<Book> bookList=null;
	
	@PostConstruct
	public void init() {
		System.out.println("BOOk:: Contoller Post Constuct");
		bookList=Arrays.asList(
				new  Book("COR123", "Corejava", 400.00, 20, true),
				new  Book("ADV123", "Advjava", 350.00, 10, false),
				new  Book("REA123", "SpringReactive", 543.00, 44, false),
				new  Book("ClO123", "CloudNative", 654.00, 55, true),
				new  Book("SPB123", "SpringBoot", 657.00, 66, false),
				new  Book("SPR123", "Spring", 860.00, 77, true),
				new  Book("HAD123", "Hadoop", 654.00, 22, false),
				new  Book("ANG123", "Angularjs", 870.00, 12, true),
				new  Book("PHY123", "Python", 432.00, 33, true),
				new  Book("HIB123", "Hibernate", 870.00, 56, false)
				);
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<Book>> getData() {
		
		List<Book> list=bookList.stream().sorted((b1,b2)->b2.getBookName().
				compareTo(b1.getBookName())).collect(Collectors.toList());
		
		ResponseEntity<List<Book>> book=
				new  ResponseEntity<List<Book>>(list,HttpStatus.OK);
		
		return book;
	}
    
	@GetMapping("/getbookbyname/{name}")
	public ResponseEntity<?> getOneBookByName(@PathVariable String name){
	List<Book> list=bookList.stream().filter(b1->b1.getBookName().toLowerCase()
			.equalsIgnoreCase(name)).collect(Collectors.toList());
	System.err.println(list);
	
	ResponseEntity<?> book=null;
	try {
		book=new ResponseEntity<Book>(list.get(0), HttpStatus.OK);		
		return book;
	}
	catch(Exception e) {	
		book=new ResponseEntity<String>("This  BookName Related Book Not Found is not Found"+e.toString(), HttpStatus.OK);
		return book;
	}
	
	}
	
	@GetMapping("/getbookbyisbn/{isbnNo}")
	public ResponseEntity<?> getOneBookByIsbnNo(@PathVariable String isbnNo){
		List<Book> list=bookList.stream().filter(b1->b1.getIsbnNo().toLowerCase()
			.equalsIgnoreCase(isbnNo)).collect(Collectors.toList());
	
	ResponseEntity<?> book=null;
	try {
	
		book=new ResponseEntity<Book>(list.get(0), HttpStatus.OK);
		return book;
	}catch (Exception e) {
		book=new ResponseEntity<String>("This BookCode Related book is not Found"+e.toString(), HttpStatus.OK);
		return book;
	}
		
	}
	
	@GetMapping("/show")
	public String getMsg() {
		
		return "This is worked with procedure";
	}
	
}


package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.config.Constants;
import com.example.exceptions.CustomCannotReservateLoanAvailableException;
import com.example.exceptions.CustomLoanAlreadyExistsException;
import com.example.exceptions.CustomMaximumUserBooksLoanedException;
import com.example.exceptions.CustomNoCopiesForLoanAvailableException;
import com.example.exceptions.CustomReservationAlreadyExistsException;
import com.example.exceptions.CustomReservationNotExistsException;
import com.example.exceptions.CustomUserPasswordSizeException;
import com.example.exceptions.CustomUserReservationLimitReachedException;
import com.example.exceptions.ErrorMessages;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.BookParamsFinder;
import com.example.model.Category;
import com.example.model.Loan;
import com.example.model.Reservation;
import com.example.model.Subcategory;
import com.example.model.User;
import com.example.model.UserBuilder;
import com.example.services.AuthorService;
import com.example.services.BookService;
import com.example.services.CategoryService;
import com.example.services.CustomValidatorService;
import com.example.services.LoanService;
import com.example.services.ReservationService;
import com.example.services.SubcategoryService;
import com.example.services.UserService;

@Controller
//@RequestMapping("/")
public class AppController {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomValidatorService customValidatorService;

	@Autowired
	private BookService bookService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubcategoryService subcategoryService;

	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private ReservationService reservationService;

	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "/private-action", produces = "application/json")
	public ResponseEntity<Object> logged() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		User user = new User(null, currentPrincipalName, "1");

		return new ResponseEntity<Object>(user, HttpStatus.OK);

	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "/public-action", produces = "application/json")
	public ResponseEntity<Object> anonym() {

		User user = new User(null, "Anonym", "1");

		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@RequestMapping("/add-default")
	@ResponseBody
	public String anyMap() {

		User simpleUser = new User();

		simpleUser.setName("test");

		simpleUser.setPassword("test1");

		userService.saveUser(simpleUser);

		return "Created User";

	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?> register(@RequestBody(required = false) UserBuilder userBuilder) {

		if (userBuilder == null) {

			return new ResponseEntity<ErrorMessages>(ErrorMessages.PARAMETERS_REQUIRED, HttpStatus.BAD_REQUEST);

		}

		if (!userBuilder.hasUsernameAndPassword()) {

			return new ResponseEntity<ErrorMessages>(ErrorMessages.WRONG_PARAMETERS, HttpStatus.BAD_REQUEST);

		}

		try {
			customValidatorService.isValidSizeUserPassword(userBuilder.getUsername(), userBuilder.getPassword());
		} catch (CustomUserPasswordSizeException e) {

			return new ResponseEntity<ErrorMessages>(ErrorMessages.SIZE_USERNAME_PASSWORD, HttpStatus.BAD_REQUEST);

		}

		User user = userBuilder.buildUser();

		System.out.println(user);

		boolean userNameAlreadyExists = userService.existsUser(user.getName());

		if (userNameAlreadyExists) {

			return new ResponseEntity<ErrorMessages>(ErrorMessages.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);

		}

		userService.saveUser(user);

		return new ResponseEntity<Object>(null, HttpStatus.OK);

	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "/all-books", produces = "application/json")
	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> books = bookService.findAllBooks();

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "/all-categories", produces = "application/json")
	public ResponseEntity<List<Category>> getAllCategories() {

		List<Category> categories = categoryService.getAllCategories();

		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "/all-subcategories", produces = "application/json")
	public ResponseEntity<List<Subcategory>> getAllSubCategories() {

		List<Subcategory> subcategories = subcategoryService.getAllSubcategories();

		return new ResponseEntity<List<Subcategory>>(subcategories, HttpStatus.OK);
	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "/authors-by-substring-name/author={author}", produces = "application/json")
	public ResponseEntity<?> getAllAuthorsBySubstringName(@PathVariable("author") String substringAuthor) {

		List<Author> authors = null;

		if (substringAuthor.equals("")) {

			return new ResponseEntity<ErrorMessages>(ErrorMessages.SIZE_AUTHOR, HttpStatus.BAD_REQUEST);

		} else {

			authors = authorService.findAllBySubstringName(substringAuthor);

			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);

		}

	}

	@CrossOrigin(origins = Constants.HOST_URL)
	@PostMapping(value = "/books-by-params", produces = "application/json")
	public ResponseEntity<?> getAllBooksByParams(@RequestBody(required = false) BookParamsFinder bookParamsFinder) {

		// if no params given

		if (bookParamsFinder == null)
			bookParamsFinder = new BookParamsFinder();

		List<Book> books = bookService.findBooksByParams(bookParamsFinder);

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

	}
	
	@GetMapping(value = "book/{id}")
	public ResponseEntity<?> findBook(@PathVariable("id") int id){
		
		Book book = bookService.findById(id);
		
		if(book == null) return getBookIdDoesNotExistsResponse();
		
		return new ResponseEntity<Book>(book,HttpStatus.OK);
	}
	
	//Needs authentification
	@CrossOrigin(origins = Constants.HOST_URL)
	@PostMapping(value = "/add-loan/{id}")
	public ResponseEntity<?> addLoan(@PathVariable("id") Integer bookId) 
			throws CustomNoCopiesForLoanAvailableException, CustomMaximumUserBooksLoanedException, 
			CustomLoanAlreadyExistsException{
		
		try {
		
		User user = getUserAuthentified();
		
		Book book = bookService.findById(bookId);
		
		if(book == null) return getBookIdDoesNotExistsResponse();
		
		loanService.createNewLoan(book, user);
		
		}catch(CustomNoCopiesForLoanAvailableException e1 ) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.NO_COPIES_FOR_LOAN,HttpStatus.BAD_REQUEST);
			
		}catch(CustomMaximumUserBooksLoanedException e2) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.BOOK_ID_NOT_EXISTS,HttpStatus.BAD_REQUEST);
			
		}catch(CustomLoanAlreadyExistsException e3) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.LOAN_ALREADY_EXISTS,HttpStatus.BAD_REQUEST);
			
		}catch(Exception e4) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.UNKNOWN_ERROR_LOAN,HttpStatus.BAD_REQUEST);
		}
	
		
		return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	//Needs authentification
	@CrossOrigin(origins = Constants.HOST_URL)
	@PostMapping(value = "/add-reservation/{id}")
	public ResponseEntity<?> addReservation(@PathVariable("id") int bookId){
		
		try {
		
		User user = getUserAuthentified();
		
		Book book = bookService.findById(bookId);
		
		if(book == null) return getBookIdDoesNotExistsResponse();
		
		reservationService.createNewReservation(book, user);
		
		}catch(CustomReservationAlreadyExistsException e1) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.RESERVATION_ALREADY_EXISTS,HttpStatus.BAD_REQUEST);
		
		}catch(CustomUserReservationLimitReachedException e2) {
		
			return new ResponseEntity<ErrorMessages>(ErrorMessages.RESERVATION_LIMIT_REACHED,HttpStatus.BAD_REQUEST);
		
		}catch (CustomCannotReservateLoanAvailableException e3) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.RESERVATION_UNAVAILABLE_LOAN_AVAILABLE,HttpStatus.BAD_REQUEST);
			
		}catch (Exception e4) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.UNKNOWN_ERROR_RESERVATION,HttpStatus.BAD_REQUEST);	
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	//Needs authentification
	@CrossOrigin(origins = Constants.HOST_URL)
	@DeleteMapping(value = "/remove-reservation/{id}")
	public ResponseEntity<?> removeReservation(@PathVariable("id") int bookId)
	{
		
		User user = getUserAuthentified();
		
		Book book = bookService.findById(bookId);
		
		if(book == null) return getBookIdDoesNotExistsResponse();
		
		try {
			
			reservationService.deleteReservation(book, user);
			
		}catch(CustomReservationNotExistsException e1) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.RESERVATION_NOT_EXISTS,HttpStatus.BAD_REQUEST);
			
		}catch(Exception e2) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.UNKNOWN_ERROR_RESERVATION,HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
		
	}
	
	//Needs authentification
	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "loans")
	public ResponseEntity<?> findLoansByUser(){
		
		User user = getUserAuthentified();
		
		if(user == null) {
			
			return new ResponseEntity<>(ErrorMessages.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
		}
		
		List<Loan> loans = loanService.findLoansByUserId(user.getId());
		
		return new ResponseEntity<List<Loan>>(loans,HttpStatus.OK);
	}
	
	//Needs authentification
	@CrossOrigin(origins = Constants.HOST_URL)
	@GetMapping(value = "reservations")
	public ResponseEntity<?> findReservationsByUser(){
		
		User user = getUserAuthentified();
		
		if(user == null) {
			
			return new ResponseEntity<>(ErrorMessages.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
		}
		
		List<Reservation> reservations = reservationService.findReservationsByUserId(user.getId());
		
		return new ResponseEntity<List<Reservation>>(reservations,HttpStatus.OK);
	}
	
	//Needs authentification
	@GetMapping(value = "user-details")
	public ResponseEntity<?> getUser(){
		
		User user = getUserAuthentified();
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	//Needs authentification
	
	private User getUserAuthentified() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		User user = userService.loadUser(currentPrincipalName);
				
		return user;
		
	}
	
	private ResponseEntity<?> getBookIdDoesNotExistsResponse(){
		
		return new ResponseEntity<ErrorMessages>(ErrorMessages.BOOK_ID_NOT_EXISTS,HttpStatus.BAD_REQUEST);
		
	}
	
	

}

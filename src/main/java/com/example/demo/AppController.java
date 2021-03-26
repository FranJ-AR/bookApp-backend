package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Author;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.entity.Subcategory;
import com.example.entity.User;
import com.example.exceptions.CustomLoanAlreadyExistsException;
import com.example.exceptions.CustomMaximumUserBooksLoanedException;
import com.example.exceptions.CustomNoCopiesForLoanAvailableException;
import com.example.exceptions.CustomReservationAlreadyExistsException;
import com.example.exceptions.CustomReservationNotExistsException;
import com.example.exceptions.CustomUserLoanLimitReachedException;
import com.example.exceptions.CustomUserPasswordSizeException;
import com.example.exceptions.CustomUserReservationLimitReachedException;
import com.example.repositories.LoanRepository;
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
	private BCryptPasswordEncoder bCryptPasswordEncoder1;

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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/private-action", produces = "application/json")
	public ResponseEntity<Object> logged() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();

		User user = new User(null, currentPrincipalName, "1");

		return new ResponseEntity<Object>(user, HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/public-action", produces = "application/json")
	public ResponseEntity<Object> anonym() {

		User user = new User(null, "Anonym", "1");

		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/add-default")
	@ResponseBody
	public String anyMap() {

		User simpleUser = new User();

		simpleUser.setName("test");

		simpleUser.setPassword("test1");

		userService.saveUser(simpleUser);

		String passwrd1 = bCryptPasswordEncoder1.encode("casa1");

		String passwrd2 = bCryptPasswordEncoder1.encode("casa1");

		System.out.println("passK " + passwrd1);

		System.out.println("passK " + passwrd2);

		System.out.println("matches?" + bCryptPasswordEncoder1.matches("casa", passwrd1));

		return "Created User";

	}

	@CrossOrigin(origins = "http://localhost:8080")
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

	@GetMapping(value = "/all-books", produces = "application/json")
	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> books = bookService.findAllBooks();

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

	}

	@GetMapping(value = "/all-categories", produces = "application/json")
	public ResponseEntity<List<Category>> getAllCategories() {

		List<Category> categories = categoryService.getAllCategories();

		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@GetMapping(value = "/all-subcategories", produces = "application/json")
	public ResponseEntity<List<Subcategory>> getAllSubCategories() {

		List<Subcategory> subcategories = subcategoryService.getAllSubcategories();

		return new ResponseEntity<List<Subcategory>>(subcategories, HttpStatus.OK);
	}

	@GetMapping(value = "/authors-by-substring-name", produces = "application/json")
	public ResponseEntity<?> getAllAuthorsBySubstringName(@RequestBody(required = false) Author author) {

		List<Author> authors = null;

		if (author == null || author.getName() == null || author.getName().equals("")) {

			return new ResponseEntity<ErrorMessages>(ErrorMessages.SIZE_AUTHOR, HttpStatus.BAD_REQUEST);

		} else {

			authors = authorService.findAllBySubstringName(author.getName());

			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);

		}

	}

	@GetMapping(value = "/books-by-params", produces = "application/json")
	public ResponseEntity<?> getAllBooksByParams(@RequestBody(required = false) BookParamsFinder bookParamsFinder) {

		// if no params given

		if (bookParamsFinder == null)
			bookParamsFinder = new BookParamsFinder();

		List<Book> books = bookService.findBooksByParams(bookParamsFinder);

		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

	}
	
	//Needs authentification
	@PostMapping(value = "/add-loan/{id}")
	public ResponseEntity<?> addLoan(@PathVariable("id") Integer bookId) 
			throws CustomNoCopiesForLoanAvailableException, CustomMaximumUserBooksLoanedException, 
			CustomLoanAlreadyExistsException{
		
		User user = getUserAuthentified();
		
		
		
		Book book = bookService.findById(bookId);
		
		loanService.createNewLoan(book, user);
		
	
		
		return null;
		
	}
	
	//Needs authentification
	@PostMapping(value = "/add-reservation/{id}")
	public ResponseEntity<?> addReservation(@PathVariable("id") int bookId) throws
	CustomReservationAlreadyExistsException, CustomUserReservationLimitReachedException{
		
		User user = getUserAuthentified();
		
		Book book = bookService.findById(bookId);
		
		reservationService.createNewReservation(book, user);
		
		return null;
		
	}
	
	@PostMapping(value = "/remove-reservation/{id}")
	public ResponseEntity<?> removeReservation(@PathVariable("id") int bookId) throws 
	CustomReservationNotExistsException
	{
		
		User user = getUserAuthentified();
		
		Book book = bookService.findById(bookId);
		
		reservationService.deleteReservation(book, user);
		
		return null;
		
	}
	
	//Needs authentification
	
	private User getUserAuthentified() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		User user = userService.loadUser(currentPrincipalName);
				
		return user;
		
	}
	
	

}

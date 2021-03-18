package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.AuthenticationRequest;
import com.example.auth.AuthenticationResponse;
import com.example.entity.Book;
import com.example.entity.Category;
import com.example.entity.Subcategory;
import com.example.entity.User;
import com.example.exceptions.CustomUserPasswordSizeException;

@Controller
//@RequestMapping("/")
public class AppController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder1;
	
	@Autowired
	CustomValidatorService customValidatorService;
	
	@Autowired
	BookService BookService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SubcategoryService subcategoryService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/private-action", produces="application/json")
	public ResponseEntity<Object> logged()
    {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
         
		User user = new User(null,currentPrincipalName,"1");
       
        return new ResponseEntity<Object>(user, HttpStatus.OK);
        
    }
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/public-action", produces="application/json")
	public ResponseEntity<Object> anonym()
    {
         
		User user = new User(null,"Anonym","1");
       
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
		
		System.out.println("passK "+passwrd1);
		
		System.out.println("passK "+passwrd2);
		
		System.out.println("matches?" +bCryptPasswordEncoder1.matches("casa", passwrd1));
		
		return "Created User";
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?> register(@RequestBody(required=false) UserBuilder userBuilder) {
		
		if(userBuilder == null) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.PARAMETERS_REQUIRED, HttpStatus.BAD_REQUEST);
		
		}
		
		if(!userBuilder.hasUsernameAndPassword()) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.WRONG_PARAMETERS, HttpStatus.BAD_REQUEST);
				
		}
		
		try {
			customValidatorService.isValidSizeUserPassword(userBuilder.getUsername(), userBuilder.getPassword());
		} catch (CustomUserPasswordSizeException e) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.SIZE_USERNAME_PASSWORD,HttpStatus.BAD_REQUEST);
			
		} 
		
		User user = userBuilder.buildUser();
		
		System.out.println(user);
		
		boolean userNameAlreadyExists = userService.existsUser(user.getName());
		
		if(userNameAlreadyExists) {
			
			return new ResponseEntity<ErrorMessages>
			(ErrorMessages.USER_ALREADY_EXISTS,HttpStatus.BAD_REQUEST);	
			
		}
		
		userService.saveUser(user);
		
		return new ResponseEntity<Object>(null,HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/all-books", produces="application/json")
	public ResponseEntity<List<Book>> getAllBooks(){
		
		List<Book> books = BookService.findAllBooks();
				
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/all-categories", produces="application/json")
	public ResponseEntity<List<Category>> getAllCategories(){
		
		List<Category> categories = categoryService.getAllCategories();
		
		return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
	}
	
	@GetMapping(value = "/all-subcategories", produces="application/json")
	public ResponseEntity<List<Subcategory>> getAllSubCategories(){
		
		List<Subcategory> subcategories = subcategoryService.getAllSubcategories();
				
		return new ResponseEntity<List<Subcategory>>(subcategories,HttpStatus.OK);
	}
	
	

}

package com.spring.ecomerces;

import com.spring.ecomerces.dao.UserDAO;
import com.spring.ecomerces.models.User;
import com.spring.ecomerces.service.UserService;
import com.spring.ecomerces.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EcomercesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomercesApplication.class, args);
	}


}

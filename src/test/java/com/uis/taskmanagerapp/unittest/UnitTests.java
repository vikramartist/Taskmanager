package com.uis.taskmanagerapp.unittest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.uis.taskmanagerapp.util.Logger;
import com.uis.taskmanagerapp.util.TaskUtility;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Unit Test For Task Manager App")
public class UnitTests {

	Logger logger = Logger.getInstance();
	Scanner sc = new Scanner(System.in);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@BeforeAll
	public static void start() {
		System.out.println("Testing starts");
	}
	
	@AfterAll
	public static void end() {
		System.out.println("Testing ends");
	}

	@Test
	@Order(1)
	public void isValidateNameWithValidInput() {
		logger.log("--------testing is name valid method------------");
		if (TaskUtility.validateName("validation")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (TaskUtility.validateName("success")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (TaskUtility.validateName("Alphabet")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}
		logger.log("--------------testing is name method done-------------");

	} 

	@Test
	@Order(2)
	public void isValidateNameWithInvalidName() {
		logger.log("--------testing is name invalid method------------");
		if (!TaskUtility.validateName("124578validation")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (!TaskUtility.validateName("     ")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (!TaskUtility.validateName("Validate name method")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (!TaskUtility.validateName("            validation")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (!TaskUtility.validateName("124578962")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}

		if (!TaskUtility.validateName("@#$%^&&")) {
			logger.log("---------------Test Successfull--------------");
			System.out.println("Validation Successfull");
		}
		logger.log("---------------testing is name invalid method done---------");
	}

	@Test
	@Order(3)
	@Disabled
	public void validateDateWithValidInput() {
		try {
			logger.log("------------Testing date validation--------------");
			String date = sc.nextLine();
			Date dt = sdf.parse(date);
			Date newdat=TaskUtility.validateDate(dt);
			if(dt.compareTo(newdat)>=0) {
				logger.log("---------------Test Successfull--------------");
				System.out.println("Validation Successfull");
			}
			
			if(dt.compareTo(newdat)==0) {
				logger.log("---------------Test Successfull--------------");
				System.out.println("Validation Successfull");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	@Order(4)
	@Disabled
	public void validateDateWithInvalidInput() {
		try {
			logger.log("------------Testing date validation--------------");
			System.out.println("Enter the Invalid date(dd/mm/yyyy)");
			String date = sc.nextLine();
			Date dt = sdf.parse(date);
			Date newdat=TaskUtility.validateDate(dt);
			if(newdat==null) { 
				logger.log("---------------Test Successfull--------------");
				System.out.println("Validation Successfull");
			} 
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

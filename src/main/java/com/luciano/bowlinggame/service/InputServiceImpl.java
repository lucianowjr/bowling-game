package com.luciano.bowlinggame.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class InputServiceImpl implements InputService {

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

	@Autowired
	private Scanner scanner;

	@Override
	public String getInput() {
		System.out.println("\n");
		System.out.println("Enter the absolute path to you input file:");
		return scanner.nextLine();
	}

}

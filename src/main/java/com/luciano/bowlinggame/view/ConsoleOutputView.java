package com.luciano.bowlinggame.view;

import org.springframework.stereotype.Component;

@Component
public class ConsoleOutputView implements OutputView {

	@Override
	public void print(String scoreBoard) {
		System.out.print(scoreBoard);
	}

}

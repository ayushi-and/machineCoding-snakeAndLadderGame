package com.example.machineCoding;

import com.example.machineCoding.model.Game;
import com.example.machineCoding.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MachineCodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachineCodingApplication.class, args);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter board size");
		int boardSize = sc.nextInt();
		System.out.println("Enter number of players");
		int numberOfPlayers = sc.nextInt();
		System.out.println("Enter number of snakes");
		int numberOfSnakes = sc.nextInt();
		System.out.println("Enter number of ladders");
		int numberOfLadders = sc.nextInt();

		Game game = new Game(numberOfSnakes, numberOfLadders, boardSize);
		for(int i = 0; i < numberOfPlayers; i++) {
			System.out.println("Enter player name");
			String playerName = sc.next();
			Player player = new Player(playerName);
			game.addPlayers(player);
		}
		game.playGame();
	}

}

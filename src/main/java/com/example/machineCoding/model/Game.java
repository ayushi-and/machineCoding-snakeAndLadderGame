package com.example.machineCoding.model;

import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

@Getter
public class Game {
    private int numberOfSnakes;
    private int numberOfLadders;

    private Queue<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private Board board;
    private Dice dice;

    public Game(int numberOfSnakes, int numberOfLadders, int boardSize) {
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfLadders = numberOfLadders;
        this.players = new ArrayDeque<>();
        snakes = new ArrayList<>(numberOfSnakes);
        ladders = new ArrayList<>(numberOfLadders);
        board = new Board(boardSize);
        dice = new Dice(1, 6, 2);
        initBoard();
    }

    private void initBoard() {
        Set<String> snakeLadderSet = new HashSet<>();
        for(int i = 0; i < numberOfSnakes; i++) {
            while (true) {
                int snakeStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int snakeEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                if(snakeEnd >= snakeStart)
                    continue;
                String startEndPair = String.valueOf(snakeStart) + snakeEnd;
                if(!snakeLadderSet.contains(startEndPair)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakes.add(snake);
                    snakeLadderSet.add(startEndPair);
                    break;
                }
            }
        }
        for(int i = 0; i < numberOfLadders; i++) {
            while (true) {
                int ladderStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int ladderEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                if(ladderStart >= ladderEnd)
                    continue;
                String startEndPair = String.valueOf(ladderStart) + ladderEnd;
                if(!snakeLadderSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.add(ladder);
                    snakeLadderSet.add(startEndPair);
                    break;
                }
            }
        }
    }

    public void addPlayers(Player player) {
        players.add(player);
    }

    //1, 2, 3, 4, 5, 6
    //2, 3, 4, 5, 6, 1
    //3, 4, 5, 6, 1, 2
    public void playGame() {
        while (true) {
            Player player = players.poll();
            int val = dice.roll();
            int newPosition = player.getPosition() + val;
            if(newPosition > board.getEnd()) {
                player.setPosition(player.getPosition());
                players.offer(player);
            }
            else{
                player.setPosition(getNewPosition(newPosition));
                if(player.getPosition() == board.getEnd()) {
                    player.setWon(true);
                    System.out.println("Player " +player.getName() + " Won.");
                }
                else {
                    System.out.println("Setting " +player.getName() + "'s new position to " + player.getPosition());
                    players.offer(player);
                }
            }
            if(players.size() < 2) {
                break;
            }
        }
    }

    private int getNewPosition(int newPosition) {
        for(Snake snake : snakes) {
            if(snake.getHead() == newPosition) {
                System.out.println("Snake Bit");
                return snake.getTail();
            }
        }
        for(Ladder ladder : ladders) {
            if(ladder.getTail() == newPosition) {
                System.out.println("Climbed ladder");
                return ladder.getHead();
            }
        }
        return newPosition;
    }
}

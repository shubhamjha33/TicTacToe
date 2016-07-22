package shubhamjha33.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name player one:");
        String playerOne="one",playerTwo="two";
        try {
            playerOne = br.readLine();
            System.out.println("Enter your name player two:");
            playerTwo=br.readLine();
        }catch(IOException ioex){
            System.out.println(ioex.getMessage());
        }
        Game game=new Game(playerOne,playerOne);
        game.runGame();
	}
}

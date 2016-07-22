package shubhamjha33.tictactoe;

import java.io.*;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Game {
    Board b;
    Player one,two;

    public Game(String playerOneName,String playerTwoName){
        b=new Board();
        one=new Player(playerOneName,1);
        two=new Player(playerTwoName,2);
    }

    public void runGame(){
        int counter=0,currPlayer;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String input;
        int rowx,coly,pos;
        boolean validMove=false;
        System.out.println("Starting the game. Enter board positions using the following config:");
        b.init();
        while(b.isOver()==-1){
            currPlayer=counter+1;
            do {
                validMove=false;
                System.out.println("Player " + currPlayer + " enter the board position(1-9):");
                try {
                    input = br.readLine();
                    pos=Integer.parseInt(input)-1;
                    rowx=pos/3;
                    coly=pos%3;
                    validMove=b.performMove(currPlayer,rowx,coly);
                }catch(IOException ioex){
                    System.out.println(ioex.getMessage());
                }
                b.displayBoard();
                if(!validMove)
                    System.out.println("Invalid Move!! Please try again!!");
            }while(!validMove);
            counter=(counter+1)%2;
            //System.out.println(counter);
        }
        int winner=b.isOver();
        if(winner==1){
            System.out.println(one.getName()+" won");
        }
        else if(winner==2){
            System.out.println(two.getName()+" won");
        }
        else{
            System.out.println("It was a draw");
        }
    }

}

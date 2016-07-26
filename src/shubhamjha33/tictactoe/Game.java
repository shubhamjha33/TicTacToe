package shubhamjha33.tictactoe;

import java.io.*;
import java.net.Socket;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Game implements Runnable {
    Board b;
    Player one,two;

    public Game(Socket playerOne, Socket playerTwo) {
        try {
            b = new Board();
            one = new Player(playerOne,1);
            new Thread(one).start();
            two = new Player(playerTwo,2);
            new Thread(two).start();
        }catch(IOException ioex){
            ioex.printStackTrace();
        }

    }

    @Override
    public void run() {
        int counter=0,currPlayer;
        int rowx,coly,pos;
        boolean validMove;
        String initMessage="Starting the game. Enter board positions using the following config:\n"+b.init();
        one.sendMessage(initMessage);
        two.sendMessage(initMessage);
        one.setReady(false);
        two.setReady(false);
        while(b.isOver() == -1){
            currPlayer = counter + 1;
            do {
                validMove = false;
                String currentBoard = "Current Board:\n"+b.displayBoard();
                pos = 0;
                switch(currPlayer) {
                    case 1: one.setReady(true);
                            two.setReady(false);
                            one.sendMessage(currentBoard);
                            one.sendMessage(Player.MsgCode.REQ_INPUT);
                            pos = one.getNextMove()-1;
                        break;
                    case 2: two.setReady(true);
                            one.setReady(false);
                            two.sendMessage(currentBoard);
                            two.sendMessage(Player.MsgCode.REQ_INPUT);
                            pos = two.getNextMove()-1;
                        break;
                }
                rowx = pos / 3;
                coly = pos % 3;
                validMove = b.performMove(currPlayer,rowx,coly);
                b.displayBoard();
                if(!validMove) {
                    switch(currPlayer) {
                        case 1: one.sendMessage(Player.MsgCode.ERR_MSG);
                            break;
                        case 2: two.sendMessage(Player.MsgCode.ERR_MSG);
                            break;
                    }
                }
            }while(!validMove);
            counter=(counter+1)%2;
            //System.out.println(counter);
        }
        int winner = b.isOver();
        String finalBoard="Final Board:\n"+b.displayBoard();
        one.sendMessage(finalBoard);
        two.sendMessage(finalBoard);
        String gameStatus="";
        if(winner == 1) {
            gameStatus=one.getName()+" won";
        }
        else if(winner == 2) {
            gameStatus=two.getName()+" won";
        }
        else {
            gameStatus="It was a draw";
        }
        gameStatus=gameStatus+"\n";
        one.sendMessage(gameStatus);
        two.sendMessage(gameStatus);
        try {
            one.closeConnection();
            two.closeConnection();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}

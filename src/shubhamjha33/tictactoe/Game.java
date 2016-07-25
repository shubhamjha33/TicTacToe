package shubhamjha33.tictactoe;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Game {
    Board b;
    Player one,two;
    ServerSocket serverSocket;

    public Game(int portNumber){
        try {
            serverSocket = new ServerSocket(portNumber);
            b=new Board();
            Socket socket=serverSocket.accept();
            one=new Player(socket,1);
            socket=serverSocket.accept();
            two=new Player(socket,2);
        }catch(IOException ioex){
            ioex.printStackTrace();
        }

    }

    public void runGame(){
        int counter=0,currPlayer;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String input;
        int rowx,coly,pos;
        boolean validMove=false;
        String initMessage="Starting the game. Enter board positions using the following config:\n"+b.init();
        one.sendMessage(initMessage);
        two.sendMessage(initMessage);
        while(b.isOver()==-1){
            currPlayer=counter+1;
            do {
                validMove=false;
                String currentBoard="Current Board:\n"+b.displayBoard();
                try {
                    pos=0;
                    switch(currPlayer){
                        case 1:one.sendMessage(currentBoard);
                               one.sendMessage(1);
                               pos = one.getNextMove()-1;
                            break;
                        case 2:two.sendMessage(currentBoard);
                               two.sendMessage(1);
                               pos = two.getNextMove()-1;
                            break;
                    }
                    rowx=pos/3;
                    coly=pos%3;
                    validMove=b.performMove(currPlayer,rowx,coly);
                }catch(IOException ioex){
                    System.out.println(ioex.getMessage());
                }
                b.displayBoard();
                if(!validMove) {
                    switch(currPlayer){
                        case 1:one.sendMessage(2);
                            break;
                        case 2:two.sendMessage(2);
                            break;
                    }
                }
            }while(!validMove);
            counter=(counter+1)%2;
            //System.out.println(counter);
        }
        int winner=b.isOver();
        String finalBoard="Final Board:\n"+b.displayBoard();
        one.sendMessage(finalBoard);
        two.sendMessage(finalBoard);
        String gameStatus="";
        if(winner==1){
            gameStatus=one.getName()+" won";
        }
        else if(winner==2){
            gameStatus=two.getName()+" won";
        }
        else{
            gameStatus="It was a draw";
        }
        gameStatus=gameStatus+"\n";
        one.sendMessage(gameStatus);
        two.sendMessage(gameStatus);
        try {
            one.closeConnection();
            two.closeConnection();
            serverSocket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

}

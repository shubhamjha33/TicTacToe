package shubhamjha33.tictactoe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham.jh on 25/07/16.
 */
public class GameManager {

    ServerSocket serverSocket;

    public GameManager(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public void manageGames() {
        List<Thread> gameList = new ArrayList();
        Socket playerOne,playerTwo;
        while(true) {
            try {
                playerOne = serverSocket.accept();
                playerTwo = serverSocket.accept();
                gameList.add(new Thread(new Game(playerOne, playerTwo)));
                gameList.get(gameList.size()-1).start();
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
    }
}

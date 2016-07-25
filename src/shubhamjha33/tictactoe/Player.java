package shubhamjha33.tictactoe;

import java.io.*;
import java.net.Socket;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Player {

    private String name;
    private int playerId;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public Player(Socket socket,int playerId) throws IOException{
        this.socket=socket;
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.playerId=playerId;
        pw.write("Enter your name player "+playerId+"\n");
        pw.flush();
        name=br.readLine();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getNextMove() throws IOException{
        return Integer.parseInt(br.readLine());
    }

    public void sendMessage(int code){
        switch(code){
            case 1:pw.write("Player "+playerId+" enter the board position(1-9):\n");
                break;
            case 2:pw.write("Invalid Move!! Please try again!!\n");
                break;
        }
        pw.flush();
    }

    public void sendMessage(String msg){
        pw.write(msg);
        pw.flush();
    }

    public void closeConnection() throws IOException{
        socket.close();
    }
}

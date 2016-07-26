package shubhamjha33.tictactoe;

import java.io.*;
import java.net.Socket;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Player implements Runnable{

    private String name;
    private int playerId,nextMove;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private volatile boolean ready,dataRead;

    enum MsgCode {
        REQ_INPUT,
        ERR_MSG,
        ERR_WAIT_FOR_TURN;
    }

    public Player(Socket socket,int playerId) throws IOException {
        this.socket=socket;
        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.playerId=playerId;
        pw.write("Enter your name player "+playerId+"\n");
        pw.flush();
        name=br.readLine();
        nextMove=-1;
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

    public synchronized void setReady(boolean ready){
        this.ready=ready;
    }

    public boolean isDataRead() {
        return dataRead;
    }

    public void setDataRead(boolean dataRead) {
        this.dataRead = dataRead;
    }

    @Override
    public void run(){
        try {
            while(true) {
                setDataRead(false);
                if(br==null)
                    break;
                String str = br.readLine();
                if (ready) {
                    nextMove = Integer.parseInt(str);
                    setDataRead(true);
                } else {
                    sendMessage(MsgCode.ERR_WAIT_FOR_TURN);
                }
            }
        } catch(IOException ioex){
            ioex.printStackTrace();
        }
    }

    public int getNextMove() {
        while(!dataRead){}
        return nextMove;
    }

    public void sendMessage(MsgCode code) {
        switch(code){
            case REQ_INPUT:
                pw.write("Player "+playerId+" enter the board position(1-9):\n");
                break;
            case ERR_MSG:pw.write("Invalid Move!! Please try again!!\n");
                break;
            case ERR_WAIT_FOR_TURN:pw.write("Its not your turn!! Please wait for your turn!!\n");
                break;
        }
        pw.flush();
    }

    public void sendMessage(String msg) {
        pw.write(msg);
        pw.flush();
    }

    public void closeConnection() throws IOException {
        br.close();
        br=null;
        pw.close();
        socket.close();
    }
}

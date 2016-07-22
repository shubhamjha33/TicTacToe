package shubhamjha33.tictactoe;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Player {
    private String name;
    private int id;
    public Player(String playerName,int playerId){
        name=playerName;
        id=playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package shubhamjha33.tictactoe;

public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Port number for server not provided");
            System.exit(1);
        }
        int portNumber=0;
        try {
            portNumber = Integer.parseInt(args[0]);
        }catch(NumberFormatException ex){
            ex.printStackTrace();
            System.exit(1);
        }
        GameManager game = new GameManager(portNumber);
        game.manageGames();
	}
}

package shubhamjha33.tictactoe;

/**
 * Created by shubham.jh on 22/07/16.
 */
public class Board {
    private int[][] current_board;

    public Board() {
        current_board=new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                current_board[i][j]=0;
            }
        }
    }

    private boolean isValid(int rowx, int coly) {
        if(rowx >= 0 && rowx < 3 && coly >= 0 && coly < 3){
            return true;
        }
        return false;
    }

    public boolean performMove(int playerId, int rowx, int coly) {
        if(isValid(rowx,coly)){
            if(current_board[rowx][coly]!=0)
                return false;
            else{
                current_board[rowx][coly]=playerId;
                return true;
            }
        }
        return false;
    }

    public int isOver() {
        int counter=0,i,j,playerId;
        for( playerId = 1; playerId <= 2 ; playerId++) {
            //horizontal
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (current_board[i][j]!=playerId)
                        break;
                }
                if(j==3){
                    return playerId;
                }
            }
            //vertical
            for (i = 0; i < 3; i++) {
                for (j = 0; j < 3; j++) {
                    if (current_board[j][i]!=playerId)
                        break;
                }
                if(j==3){
                    return playerId;
                }
            }
            //left diagonal
            for(i=0;i<3;i++){
                if(current_board[i][i]!=playerId)
                    break;
            }
            if(i==3)
                return playerId;
            for(i=0;i<3;i++){
                if(current_board[i][2-i]!=playerId)
                    break;
            }
            if(i==3)
                return playerId;
        }
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                if(current_board[i][j]==0)
                    return -1;
            }
        }
        return 3;
    }

    public String displayBoard() {
        int i,j;
        StringBuffer sb=new StringBuffer("");
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
                if(current_board[i][j]==0)
                    sb.append(" ");
                else if(current_board[i][j]==1)
                    sb.append("X");
                else
                    sb.append("O");
                if(j!=2)
                    sb.append("|");
            }
            sb.append("\n");
            if(i!=2){
                for(j=0;j<6;j++){
                    sb.append("-");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String init(){
        StringBuffer sb=new StringBuffer("");
        int i,j,val;
        for(i=1;i<=3;i++){
            for(j=1;j<=3;j++){
                val=(i-1)*3+j;
                sb.append(val);
                if(j!=3)
                    sb.append("|");
            }
            sb.append("\n");
            if(i!=3){
                for(j=1;j<=6;j++){
                    sb.append("-");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}

public class Board {
    Cell[][] board = new Cell[8][8];

    public Board(){
        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                board[i][j] = new Cell();
            }
        }
    }

    public String[][] getBoard(){
        String[][] array = new String[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                array[i][j] = board[i][j].getSymbol();
            }
        }
        return array;
    }

    public void boardSetShip(int x, int y, String symbol){
        if(board[x][y].getIsOccupiedByShip() == false){
            board[x][y].setOccupiedByShip(symbol);
        }
    }

    public void boardSetSmallShip(int x, int y, String direction, String symbol){
        // set a ship of size 2
        boardIncrementSetShip(x,y,direction,2, symbol);
    }

    public void boardSetMediumShip(int x, int y, String direction, String symbol){
        // set a ship of size 3
        boardIncrementSetShip(x,y,direction,3, symbol);
    }

    public void boardSetMediumBigShip(int x, int y, String direction, String symbol){
        // set a ship of size 4
        boardIncrementSetShip(x,y,direction,4, symbol);
    }

    public void boardSetBigShip(int x, int y, String direction, String symbol){
        // set a ship of size 5
        boardIncrementSetShip(x,y,direction,5, symbol);
    }

    public void boardIncrementSetShip(int x, int y, String direction, int size, String symbol){
        if (direction.equals("v")){  // vertical
            for(int i=0; i<size; i++){
                board[x+i][y].setOccupiedByShip(symbol);
            }
        } else if (direction.equals("h")){  // horizontal
            for(int j=0; j<size; j++){
                board[x][y+j].setOccupiedByShip(symbol);
            }
        }
    }

    public boolean isValidPlaceForShip(int x, int y, int size, String direction, boolean verbose){
        // verbose if when there is a need to print out why it's not valid
        if((x >= 8) || (x < 0) || (y < 0) || (y >= 8)){  // if the coordinate is out of bound
            return false;
        } else if(direction.equals("v")){  // if the size won't be contained within the board
            if((x+size-1) >= 8){
                if(verbose==true) {
                    System.out.println("The ship falls outside of board.");
                }
                return false;
            } else {
                for(int i=x;i<x+size; i++){  // check if the range has been occupied
                    if (board[i][y].getIsOccupiedByShip() == true){
                        if(verbose==true) {
                            System.out.println("There is a ship in the way.");
                        }
                        return false;
                    }
                }
            }
        } else if(direction.equals("h")){
            if((y+size-1) >= 8){
                if (verbose==true) {
                    System.out.println("The ship falls outside of board.");
                }
                return false;
            } else {
                for(int j=y;j<y+size; j++){  // check if the range has been occupied
                    if (board[x][j].getIsOccupiedByShip() == true){
                        if(verbose==true) {
                            System.out.println("There is a ship in the way.");
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isValidMove(int x, int y){
        // return true if the move is valid and hasn't been used before
        if((x >= 8) || (x < 0) || (y < 0) || (y >= 8)) {  // if the coordinate is out of bound
            return false;
        } else if(board[x][y].getIsBombed()==true){  // if the coordinate has been used already
            System.out.println("The coordinate has been tried.");
            return false;
        }
        return true;
    }

    public boolean boardGetIsHit(int x, int y){
        // return true if it is a hit
        if((board[x][y].getIsOccupiedByShip() == true) && (board[x][y].getIsBombed()==false)){
            return true;
        }
        return false;
    }

    public void boardSetHit(int x, int y){
        board[x][y].setBombed();
    }

    public void boardSetMissed(int x, int y){
        board[x][y].setMissed();
    }
}

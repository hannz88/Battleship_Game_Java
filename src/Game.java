import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Game {
    private Player player;
    private Machine machine;
    private int boardLength = 8;
    private Board playerBoardForPlayer = new Board();  // board for the ships of player
    private Board machineBoardForMachine = new Board();  // board for the ships of machine
    private Board machineBoardForPlayer = new Board();  // keeps track of the player's attack on the machine - for player
    private Board playerBoardForMachine = new Board();  // keeps track of machine's attack on the player - for machine
    private DisplayBoard display = new DisplayBoard(this.boardLength, this.boardLength);
//    private int[] shipSize = {2,2,3,4,5};  // the array of ship sizes
//    private int playerShipCompartmentLeft = IntStream.of(shipSize).sum();
//    private int machineShipCompartmentLeft = IntStream.of(shipSize).sum();
    private List<Integer> shipSize = new ArrayList<Integer>();
    private long playerShipCompartmentLeft;
    private long machineShipCompartmentLeft;
    private String turn;

    public Game(Scanner sc){
        // Welcome
        System.out.println("Welcome to the Battleship game!");
        this.normShipSizes();
        // instructions
        this.gameInstruction(sc);
        // ask player for a symbol
        this.askPlayerSymbol(sc);
        // ask machine to select symbol
        this.askMachineSymbol(player.getSymbol());
        // ask player to set ships
//        this.updatePlayerDisplayAndDisplay();  // display the boards for player before starting
        this.updateAndDisplay(playerBoardForPlayer, machineBoardForPlayer);
        this.askPlayerSetShips(sc);
        this.updateAndDisplay(playerBoardForPlayer, machineBoardForPlayer);
        // ask Machine to set ships
        this.askMachineSetShips();
//        System.out.println("The machine's board: ");
//        this.updateAndDisplay(machineBoardForMachine, playerBoardForMachine);
        // recap
        this.gameRecap();
        // play game
        this.playGame(sc);
    }

    public void gameInstruction(Scanner sc){
        // to print out the instructions and ask player to press any letter to continue
        String s0 = String.format("Would you like to read the instructions? Press y to continue, else enter ex to skip: ");
        String s0_1 = String.format("There are %d ships in total. ", shipSize.size());
        String s0_5 = String.format("The individual ship sizes are: " + shipSize.toString());
        String s1 = String.format(s0_1 + s0_5 + ". Press y to continue.");
        String s2 = String.format("The goal is to sink all %d ships of the opponent by guessing where their ships are located at. Press y to continue.", shipSize.size());
        String s3 = "You will be ask to set your own ships first. You will be asked to provide the row and column coordinate" +
                " of the head of the ship.\nYou will also be asked if you want the ship to be set vertically or horizontally. Press y to continue.";
        String s4 = "Then during game play, you'll be asked to provide the row, column coordinate of the opponent board you want to hit. Press y to continue.";
        String s5 = "Do you understand the instructions? Press y to continue.";
        String s6 = "Have fun!";
        String[] array = {s0, s1, s2,s3, s4, s5, s6};
        int i = 0;
        do {
            System.out.println(array[i]);
            String tmp = sc.nextLine();
            if(tmp.equals("y")){
                i += 1;
                if(i==5){
                    System.out.println(array[i]);
                    break;
                }
            } else if (tmp.equals("lite")) {
                this.lite();
                break;
            } else if (tmp.equals("ex")){  //  an escape clause
                break;
            } else {
                System.out.println("Input not recognised.");
            }
        } while (true);
    }

    public void normShipSizes(){
        Integer[] array = {2,2,3,4,5};
        for(Integer num: array){
            this.shipSize.add(num);
        }
        this.playerShipCompartmentLeft = shipSize.stream().mapToLong(Integer::longValue).sum();
        this.machineShipCompartmentLeft = shipSize.stream().mapToLong(Integer::longValue).sum();
    }

    public void lite(){
        // make a lite version of the game
        this.shipSize.clear();
        this.shipSize.add(2);
        this.shipSize.add(3);
        this.playerShipCompartmentLeft = shipSize.stream().mapToLong(Integer::longValue).sum();
        this.machineShipCompartmentLeft = shipSize.stream().mapToLong(Integer::longValue).sum();
        String s1 = String.format("There are %d ships in total. ", shipSize.size());
        String s2 = String.format("The individual ship sizes are: " + shipSize.toString() + ".");
        System.out.println(s1 + s2);
    }

    public void gameRecap(){
        // to reiterate the symbols and ship numbers
        String s1 = String.format("As a recap, your symbol is %s", player.getSymbol());
        String s2 = String.format("If you/ opponent hit a ship, %s will appear on the board.", playerBoardForPlayer.boardGetBombedSymbol());
        String s3 = String.format("If you/ opponent missed, %s will appear on the board.", playerBoardForPlayer.boardGetMissedSymbol());
        String s4 = String.format("Fire away!");
        String[] stringArray = {s1, s2, s3, s4};
        try {
            for (String s : stringArray) {
                printStringWithDelay(s, 1500);
            }
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void askPlayerSymbol(Scanner sc){
        boolean isLenOne = false;
        String input;
        while(isLenOne == false){
            System.out.println("Please choose a single character to represent the player:");
            input = sc.nextLine();
            if(input.length() != 1){
                System.out.println("Input is not of a single character.");
                continue;
            } else {
                player = new Player(input, this.boardLength);
                isLenOne = true;
                break;
            }
        }
    }

    public void askMachineSymbol(String symbol){
        // get machine to create a symbol
        machine = new Machine(symbol, this.boardLength);
    }

    public void askPlayerSetShips(Scanner sc){
        // ask player to set ship manually or automatically
        while(true){
            System.out.println("Would you like to set ship manually? Press y for yes and n for no.\nIf no, the ships' positions will be randomly selected.");
            String input = sc.nextLine();
            if (input.equals("y")) {
                this.playerSetShipManual(sc);
                break;
            } else if (input.equals("n")) {
                this.gameSetShipAutomatic(playerBoardForPlayer, player);
                break;
            } else {
                System.out.println("Input not recognised.");
            }
        }
    }

    public void playerSetShipManual(Scanner sc){
        // ask player to set ship manually
        int x, y;
        String alignment;
        boolean validPlace;
        String s = "Please set a ship of size ";
        for(int size: shipSize){  // loop through the different size of boat
            System.out.println(s + size + ".");
            validPlace = false;
            while(validPlace == false) {
                x = player.askPlayerForRowOfShip(sc);
                y = player.askPlayerForColOfShip(sc);
                alignment = player.askPlayerForShipAlignment(sc);
                if(playerBoardForPlayer.isValidPlaceForShip(x, y, size, alignment, true) == true){  // if all the combination result in legal placement
                    playerBoardForPlayer.boardIncrementSetShip(x,y,alignment,size,player.getSymbol());
                    this.updateAndDisplay(playerBoardForPlayer, machineBoardForPlayer);  // show the player where their ships are currently at
                    break;
                }
            }
        }
    }

    public void askMachineSetShips(){
        // ask the machine to set the ships
        this.gameSetShipAutomatic(machineBoardForMachine, machine);
    }

    public void updateAndDisplay(Board board1, Board board2){
        // take in two board objects and display them
        String[][] array1 = board1.getBoard();
        String[][] array2 = board2.getBoard();
        display.displayBoard(array1, array2);
    }

    public void playGame(Scanner sc){
        String turn;
        while(true){
            // if player's turn
            turn = "player";
            this.gameAskPlayerMove(sc);
            updateAndDisplay(playerBoardForPlayer, machineBoardForPlayer);  // show player the current situation
            if(checkWinningCondition() == true){
                break;
            }
            turn = "machine";
            // machine's turn
            this.gameAskMachineMove();
            updateAndDisplay(playerBoardForPlayer, machineBoardForPlayer);  // show player the situation
//            System.out.println("The machine's board: Machine own vs Player");
//            updateAndDisplay(machineBoardForMachine, playerBoardForMachine);
            if (checkWinningCondition() == true){
                break;
            }
        }
        this.declareWinner(turn);  // declares who won

    }

    public void gameAskPlayerMove(Scanner sc){
        // ask player for the move and update the board
        boolean validMove = false;
        int x, y;
        try {
            while (validMove == false) {
                x = player.askPlayerAttackRow(sc);  // these methods will tell player if their input are out of bound
                y = player.askPlayerAttackCol(sc);
                if (machineBoardForMachine.isValidMove(x, y) == true) {  // if the move is valid, ie if the coord has not been selected before
                    // if hit then set hit
                    if (machineBoardForMachine.boardGetIsHit(x, y) == true) {  // if the coord has ship and not used yet
                        printStringWithDelay("You've hit a ship!", 1500);
                        this.machineShipCompartmentLeft -= 1;
                        machineBoardForPlayer.boardSetHit(x, y);  // set the machine display board for player
                        machineBoardForMachine.boardSetHit(x, y);  // set the machine board coord
                    } else {  // if missed then set missed
                        printStringWithDelay("You've missed.", 1500);
                        machineBoardForPlayer.boardSetMissed(x, y);  // update display for player
                        machineBoardForMachine.boardSetMissed(x, y);  // update machine's own board
                    }
                    validMove = true;
                }
            }
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void gameAskMachineMove(){
        // Get the machine to generate moves
        try {
            boolean valid = false;
            int x, y;
            int[] move;
            while (valid == false) {
                move = machine.generateMove();
                x = move[0];
                y = move[1];
                if (playerBoardForPlayer.isValidMove(x, y) == true) {  // if it's a valid move on the player's board
                    if (playerBoardForPlayer.boardGetIsHit(x, y) == true) {
                        String s = String.format("The Machine has hit your ship at row %d, column %d.\n", x, y);
                        printStringWithDelay(s, 2000);
                        this.playerShipCompartmentLeft -= 1;
                        playerBoardForMachine.boardSetHit(x, y);  // update the machine side display
                        playerBoardForPlayer.boardSetHit(x, y);  // update the player's own
                    } else {  // if the machine's attack is a miss
                        String s = String.format("The Machine's attack at row %d, column %d has been a miss.\n", x, y);
                        printStringWithDelay(s, 2000);
                        playerBoardForPlayer.boardSetMissed(x, y);  // update player
                        playerBoardForMachine.boardSetMissed(x, y);  // update machine display
                    }
                    valid = true;
                }
            }
        } catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public void gameSetShipAutomatic(Board selfBoard, Participant self){
        // set ship automatically
        boolean valid;
        for(int size: this.shipSize){
            valid = false;
            while(valid==false){
                int x = self.generateNum();
                int y = self.generateNum();
                String alignment = self.generateAlignment();
                if(selfBoard.isValidPlaceForShip(x,y,size,alignment,false) == true){
                    selfBoard.boardIncrementSetShip(x,y,alignment,size,machine.getSymbol());
                    break;
                }
            }
        }
    }

    public boolean checkWinningCondition(){
        if((playerShipCompartmentLeft <= 0) || (machineShipCompartmentLeft <= 0)){
            return true;
        }
        return false;
    }

    public void declareWinner(String lastTurn){
        // declare who wins and call the winning function
        try {
            if (lastTurn.equals("player")) {
                printStringWithDelay("You have won! Congratulations, Commander! Have a whiskey!", 2000);
                player.won();
                return;
            }
            printStringWithDelay("The Machine has won! Prepared to be anhilated!", 2000);
            machine.won();
        } catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    private void printStringWithDelay(String str, long miliseconds) throws InterruptedException{
        // print a string follow by a delay
        System.out.println(str);
        TimeUnit.MILLISECONDS.sleep(miliseconds);
    }
}
/* Not used
    public void askPlayerSetSmallShip(Scanner sc){
        boolean validPlace = false;
        int x, y, size = 2;
        String alignment;
        while(validPlace == false){
            x = player.askPlayerForRowOfShip(sc);
            y = player.askPlayerForColOfShip(sc);
            alignment = player.askPlayerForShipAlignment(sc);
            if(playerBoardForPlayer.isValidPlaceForShip(x, y, size, alignment) == true){
                playerBoardForPlayer.boardSetSmallShip(x,y,alignment, player.getSymbol());
                validPlace = true;
            }
        }
    }

    public void askPlayerSetMediumShip(Scanner sc){
        boolean validPlace = false;
        int x, y, size = 3;
        String alignment;
        while(validPlace == false){
            x = player.askPlayerForRowOfShip(sc);
            y = player.askPlayerForColOfShip(sc);
            alignment = player.askPlayerForShipAlignment(sc);
            if(playerBoardForPlayer.isValidPlaceForShip(x, y, size, alignment) == true){
                playerBoardForPlayer.boardSetMediumShip(x,y,alignment, player.getSymbol());
                validPlace = true;
            }
        }
    }

    public void askPlayerSetMediumBigShip(Scanner sc){
        boolean validPlace = false;
        int x, y, size = 4;
        String alignment;
        while(validPlace == false){
            x = player.askPlayerForRowOfShip(sc);
            y = player.askPlayerForColOfShip(sc);
            alignment = player.askPlayerForShipAlignment(sc);
            if(playerBoardForPlayer.isValidPlaceForShip(x, y, size, alignment) == true){
                playerBoardForPlayer.boardSetMediumBigShip(x,y,alignment, player.getSymbol());
                validPlace = true;
            }
        }
    }

    public void askPlayerSetBigShip(Scanner sc){
        boolean validPlace = false;
        int x, y, size = 5;
        String alignment;
        while(validPlace == false){
            x = player.askPlayerForRowOfShip(sc);
            y = player.askPlayerForColOfShip(sc);
            alignment = player.askPlayerForShipAlignment(sc);
            if(playerBoardForPlayer.isValidPlaceForShip(x, y, size, alignment) == true){
                playerBoardForPlayer.boardSetBigShip(x,y,alignment, player.getSymbol());
                validPlace = true;
            }
        }
    }

* */
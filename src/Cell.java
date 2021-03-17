public class Cell {
    private boolean isBombed = false;
    private boolean isOccupiedByShip = false;
    private String symbol = "[]";
    private static final String ansiReset = "\u001B[0m";
    private static final String ansiRed = "\u001B[31m";
    private static final String ansiGreen = "\u001B[32m";

    public void setBombed() {
        this.isBombed = true;
        this.symbol = ansiRed + "X" + ansiReset;
    }

    public void setOccupiedByShip(String symbol){
        this.isOccupiedByShip = true;
        this.setSymbol(symbol);
    }

    public void setMissed(){
        this.isBombed = true;
        this.symbol = ansiGreen + "O" + ansiReset;
    }

    public boolean getIsBombed(){
        return isBombed;
    }

    public boolean getIsOccupiedByShip(){
        return this.isOccupiedByShip;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

}

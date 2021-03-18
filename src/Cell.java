public class Cell {
    private boolean isBombed = false;
    private boolean isOccupiedByShip = false;
    private String symbol = "[]";
    private static final String ansiReset = "\u001B[0m";
    private static final String ansiRed = "\u001B[31m";
    private static final String ansiGreen = "\u001B[32m";
    private String bombedSymbol = ansiRed + "X" + ansiReset;
    private String missedSymbol = ansiGreen + "O" + ansiReset;

    public void setBombed() {
        this.isBombed = true;
        this.symbol = bombedSymbol;
    }

    public String getBombedSymbol(){
        // get the symbol
        return bombedSymbol;
    }

    public String getMissedSymbol(){
        // return the Missed Symbol
        return missedSymbol;
    }

    public void setOccupiedByShip(String symbol){
        this.isOccupiedByShip = true;
        this.setSymbol(symbol);
    }

    public void setMissed(){
        this.isBombed = true;
        this.symbol = missedSymbol;
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

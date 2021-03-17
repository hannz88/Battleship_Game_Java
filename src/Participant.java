import java.util.Random;

public abstract class Participant {
    protected String symbol;
    protected static final String ansiReset = "\u001B[0m";
    protected int currentX;
    protected int currentY;
    protected int boardLength;
    private static Random rand = new Random();

    public Participant(int boardLength){
        this.boardLength = boardLength;
    }

    public void setSymbol(String symbol){
        // set the symbol
        this.symbol = symbol;
    }

    public String getSymbol(){
        return this.symbol;
    };

    public void setCurrentX(int x){
        // set the current x
        this.currentX = x;
    }

    public int getCurrentX(){
        // return the current x coordinate
        return this.currentX;
    }

    public void setCurrentY(int y){
        // set the currentY
        this.currentY = y;
    }

    public int getCurrentY(){
        // return the current Y coordinate
        return this.currentY;
    }

    public abstract void won();

    public int generateNum(){
        // generate a random number within the board length
        return rand.nextInt(boardLength);
    }

    public String generateAlignment(){
        // generate v or h
        int tmp = rand.nextInt(100);
        if (tmp <= 50){
            return "v";
        }
        return "h";
    }
    /*

    public int generateX(){
        // generate the row coord of move
        return rand.nextInt(boardLength);
    }

    public int generateY(){
        // generate the col coord of move
        return rand.nextInt(boardLength);
    }

    public int[] generateMove(){
        int[] ans = new int[2];
        ans[0] = generateX();
        ans[1] = generateY();
        return  ans;
    }

     */
}

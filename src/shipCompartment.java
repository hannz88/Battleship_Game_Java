public class shipCompartment {
    private String symbol = "0";
    private boolean isBombed = false;
    private int x;
    private int y;

    public boolean getIsBombed(){
        return this.isBombed;
    }

    public void setBombed(){
        this.symbol = "x";
        this.isBombed = true;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}

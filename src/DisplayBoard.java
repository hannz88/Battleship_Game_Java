public class DisplayBoard {
    private static final String ansiReset = "\u001B[0m";
    private static final String ansiRed = "\u001B[31m";
    private String sep;
//    private String emptySpaces = "                     ";
    private String emptySpaces = "\t\t\t\t\t\t";
    private int row;
    private int col;

    public DisplayBoard(int row, int col){
        this.row = row;
        this.col = col;
        setSeparator(col);
    }

    public void displayBoard(String[][] array){
        // display the content of board
        this.displayHeader(this.col);
        for(int i=0; i<this.row; i++){
            String rowNum = ansiRed + i + ansiReset;
            String tmp = rowNum + "\t|";
            for(int j=0; j<this.col; j++){
                if(array[i][j].equals("[]")){
                    String emptyCells = String.format("%4s|"," ");
                    tmp += emptyCells;
                } else {
                    String symbol = String.format("%1s%2s%2s|"," ",array[i][j]," ");
                    tmp += symbol;
                }
            }
            System.out.format(tmp+"\n");
            System.out.println(this.sep);
        }
        this.displayColumn(this.col);
    }

    public void displayBoard(String[][] array1, String[][] array2){
        // display the content of board
        this.displayIdentifier();
        this.displayHeader(this.col);
        for(int i=0; i<array1.length; i++){  // both arrays have to be the same dimension
            String rowNum = ansiRed + i + ansiReset;
            String tmp1 = rowNum + "\t|";
            String tmp2 = rowNum + String.format("\t|");
            for(int j=0; j<array1[0].length; j++){
                if(array1[i][j].equals("[]")){
                    String emptyCells = String.format("%4s|"," ");
                    tmp1 += emptyCells;
                } if(array2[i][j].equals("[]")){
                    String emptyCells = String.format("%4s|"," ");
                    tmp2 += emptyCells;
                } if(!array1[i][j].equals("[]")){
                    String symbol = String.format("%1s%2s%2s|"," ",array1[i][j]," ");
                    tmp1 += symbol;
                } if(!array2[i][j].equals("[]")){
                    String symbol = String.format("%1s%2s%2s|"," ",array2[i][j]," ");
                    tmp2 += symbol;
                }
            }
            System.out.format(tmp1+ emptySpaces + tmp2 + "\n");
            System.out.println(this.sep + emptySpaces + this.sep);
        }
        System.out.println(this.displayColumn(this.col) + emptySpaces + this.displayColumn(this.col));
        System.out.println();
    }

    public String displayColumn(int col){
        // display the column
        String colNum = String.format("\t|");
        for(int j=0; j<col; j++){
            String num =  ansiRed+ j + ansiReset ;
            colNum += String.format("%1s%2s%2s|"," ",num," ");
        }
        return colNum;
//        String output = String.format(colNum + emptySpaces + "%1s" + colNum);
//        System.out.println(output);
    }

    public void displayIdentifier(){
        // print out what is what
        String distance = "\t";
        String unit = "     ";
        String s1 = "The player's own board:";
        String s2 = "The opponent's board:";
        for(int i=0; i<4; i++){
            distance += unit;
        }
        distance += emptySpaces;
        String output = String.format(s1 + distance + s2);
        System.out.println(output);
    }

    public void displayHeader(int col){
        // display header
        String tmp = String.format("%4s|", " ");
        for(int i=0; i<col; i++){
            tmp += String.format("%4s|", " ");
        }
        System.out.format(tmp + emptySpaces + tmp + "\n");
        System.out.println(this.sep + emptySpaces + this.sep);
    }

    public void setSeparator(int col){
        // set the separator
        String sep = "----+";
        for(int i=0; i<col; i++){
            sep += "----+";
        }
        this.sep = sep;
    }
}


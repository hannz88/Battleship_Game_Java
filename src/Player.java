import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Player extends Participant{
    public static final String ansiBlue = "\u001B[34m";
//    private int boardLength;

    public Player(String symbol, int boardLength){
        super(boardLength);
        this.setSymbol(symbol);
    }

    @Override
    public String getSymbol(){
        // get the symbol
        return this.ansiBlue + this.symbol + this.ansiReset;
    }

    public int askPlayerForRowOfShip(Scanner sc){
        int row = 0;
        boolean isInt = false;
        while(isInt == false){
            System.out.println("Please enter the row of the head of the ship:");
            if(sc.hasNextInt()){  // if input is int
                int input = sc.nextInt();
                if((input<0) || (input> this.boardLength-1)){  // if input is out bound
                    System.out.println("Input out of bound");
                    continue;
                }
                row = input;  //if input is int and not out of bound
                isInt = true;
            } else if (sc.hasNextLine()){  // if input is string instead
                sc.nextLine();
                int bufferLen = 0;
                try{
                    bufferLen = System.in.available(); // check if the system.in buffer is empty
                } catch(IOException ignored){}
                if (bufferLen > 0 ){  // if there's still something in the buffer, read the next line
                    sc.nextLine();
                }
                System.out.println("Invalid input for row.");
            }
        }
        return row;
    }

    public int askPlayerForColOfShip(Scanner sc){
        int col = 0;
        boolean isInt = false;
        while(isInt == false){
            System.out.println("Please enter the column of the head of the ship:");
            if(sc.hasNextInt()){  // if input is int
                int input = sc.nextInt();
                if((input<0) || (input> this.boardLength-1)){  // if input is out bound
                    System.out.println("Input out of bound");
                    continue;
                }
                col = input;  //if input is int and not out of bound
                isInt = true;
            } else if (sc.hasNextLine()){  // if input is string instead
                sc.nextLine();
                int bufferLen = 0;
                try{
                    bufferLen = System.in.available(); // check if the system.in buffer is empty
                } catch(IOException ignored){}
                if (bufferLen > 0 ){  // if there's still something in the buffer, read the next line
                    sc.nextLine();
                }
                System.out.println("Invalid input for column.");
            }
        }
        return col;
    }

    public String askPlayerForShipAlignment(Scanner sc){
        String alignment;

        while(true){
            if (sc.hasNextLine()) {  // if input is string instead
                sc.nextLine();
                int bufferLen = 0;
                try {
                    bufferLen = System.in.available(); // check if the system.in buffer is empty
                } catch (IOException ignored) {
                }
                if (bufferLen > 0) {  // if there's still something in the buffer, read the next line
                    sc.nextLine();
                }
            }
            System.out.println("Please enter the alignment of the ship. v for vertical, h for horizontal:");
            if(sc.hasNextLine()) {
                String tmp = sc.nextLine();
                if (tmp.equals("v") || tmp.equals("h")) {
                    alignment = tmp;
                    break;
                } else {
                    System.out.println("Input not recognised.");
                    System.out.println(tmp);
                }
            }
        }
        return alignment;
    }

    public int askPlayerAttackRow(Scanner sc){
        int row=0;
        boolean isInt = false;
        while(isInt==false){
            System.out.println("Please enter the row of your target: ");
            if(sc.hasNextInt()){
                int tmp = sc.nextInt();
                if((tmp < 0) || (tmp >= this.boardLength)){
                    System.out.println("Input out of bound.");
                } else {
                    row = tmp;
                    isInt = true;
                }
            } else if (sc.hasNextLine()){
                sc.nextLine();
                int bufferLen = 0;
                try{
                    bufferLen = System.in.available();
                }catch(IOException ignored){}
                if(bufferLen>0){
                    sc.nextLine();
                }
                System.out.println("Input not recognised.");
            }
        }
        return row;
    }

    public int askPlayerAttackCol(Scanner sc){
        int col=0;
        boolean isInt = false;
        while(isInt==false){
            System.out.println("Please enter the column of your target: ");
            if(sc.hasNextInt()){
                int tmp = sc.nextInt();
                if((tmp < 0) || (tmp >= this.boardLength)){
                    System.out.println("Input out of bound.");
                } else {
                    col = tmp;
                    isInt = true;
                }
            } else if (sc.hasNextLine()){
                sc.nextLine();
                int bufferLen = 0;
                try{
                    bufferLen = System.in.available();
                }catch(IOException ignored){}
                if(bufferLen>0){
                    sc.nextLine();
                }
                System.out.println("Input not recognised.");
            }
        }
        return col;
    }

    @Override
    public void won(){
        String cookieMons = "" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZNOZNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMZ?~==?I?~+$$Z88OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM$I?:::,,,::,~:+?$ZDNNMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMM8+?=+?+?I+=:~?:~:?$+DZNO8MMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMM++$77$7?I?~~~==::~~$I$O8DMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMN8$?DNZ~==~:::,,,,,,,:?=ZZDNDNMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMNMNM7IZ,~,,~~::===,,~,=~?O$O88DMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMDD8IOD$+,I:$7~::~~??=7:ZZ$$788DNMNNNMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMI$8NMN$:Z=~II?+~++=+$7OMNZNMNN888NNNNMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMM?DNMDNZI~NN$8DZ=?7N$=IONMMMMMD8OZNN8DNMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMM$ODNNOO8$MMD88?M:$+$NIDNDMNN8OOZONNMMDMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMM?DZ8DMZZ8M$?7$$ZO8ZI8IZ88NDD8Z$$$8DNMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMIMMMMDNNN?~~~~$7$8ZZZIZOZZ$I7I7778DNMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN$~~::::~::=IZ+77?+=+II777OODMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMZI+==~~:::,,,:~++==~~~?II$$8Z8MMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMNM87?+===~~:::,:::~::~=:+I?I$$OZONMMNMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMNZ7??+===~:::~~~===~,:=+777$$OZO8MMDMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMNNMMM87?+++==~~:,,::~:~=I:,~II77$OZZ88MDMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMN=DMMND$?=?++~~~~:,,,,:::=I=:+?I77$$Z8OMDMMMMMMMMMMNNNMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMO8MMM8Z?=++===~=~++=::::,:=:~=7O8O8OOODNMMMMMMMMMMDDDDMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMI?DNM87?++===~~:,,,:::=::::I8NN88NDD8O$MMMMMMMMMMDOO88MMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMNDIZDNZI?+===?+$7$Z?~::=~~~7Z$$7ZZO8OOO8MMMMMMMMMM8OOO8MMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMNI=+O8?I?ZODZO77$$Z77==:~?78ND8788OZZOOMMMMMMMMMMZZZZ8MMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMI~+II++$O7?7$ONNOZ$7?++O888??=7Z77ZOMMMMMMMMMMD$$$Z8MMM\n" +
                "MMMMMMMMMMMNMMMMMMMMMMMMMM~~??+==+$8NZZO?$87Z+:~O$$Z77$ZI?I7OMMMMMMMMMMO$$$$DMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMM8=~I?=~=::~++?77+:::,:77??=++~~+?7ZMMMMMMMMMM$7777DMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMM+=??+~~~:==~~=~,::::,77+=~~::~=?7OMMMMMMMMMN7III7DMMM\n" +
                "MMMMMM,.,~MMMMMMMMMMMMMMMMMMMZ=+=~:,,,,...,::::,+7I7O+===?7Z8MMMMMMMMMDI??I7DMMM\n" +
                "MMMMMM,,,~+NMMMDMM,.?:.NDDDND+=+=~~~~,:,:~I::~:,~7?IZ$$II7ZD8MMMMMMMMMD????7NMMM\n" +
                "MMMMMM+:::?+=MMMMM.8N.ZMMMMM8,.,7I==~::=+$I+~~~:~IZOZ$$O88888MMMMMMMMM8++++$MMMM\n" +
                "MMMMMMM~:+=~~MMMMMMMMMMMMDNNMM.7?+77I?==~7==?N$7$8OOOO88O?ZODMMMMMMMMMO===+ZMMMM\n" +
                "MMMMMMM?+?:~+?7$7$$?+++::~+Z?Z,,?~=?+?++?~==+??I777$O8$??8O8MMMMMMMMMM7====8MMMM\n" +
                "MMMMMMMN$7+?++78D:NNMMMM...OI~:.+II?I?++?Z8DO7$ZOI7$ZZI7O88MMMMMMMMMMN+~~~+NMMMM\n" +
                "MMMMMMM?I7~=7$D8NNDNNNMM...MMND8$??:::~=~::~~:~~=+?7O$7Z88MMMMMMMMMMM8=~~~+MMMMM\n" +
                "MMMMMMN=:,,,,,,....,7MMMMMMMNDN7ON7:$I+=?=~==+??7ZO777Z8DOMMMMMMMMMMM8=::~?MMMMM\n" +
                "MMMMMMN.,,,.,,,,,..,:MMMMMNNOZN~ONN777=Z7?+~~~=~::=?$ZOD?8MMMMMMMMMMM7~:::7MMMMM\n" +
                "MMMMM8,:~:,,,:~~~:~+$MMMMM8OZZ:$NNDN$I8DZ$I++=~~~=?IO88M+ZMMMMMMMMMMM7~::~8MMMMM\n" +
                "MMMMM:~~=====?I7ZO8NNNNDZ?~,,,,.NMMNNM:O8DO$II?I777O8D??7?MMMMMMMMMMM$~:~IMMMMMM\n" +
                "MMNDM==+?I??$O$I???++==~:,,,..,=7IMMMMM.~MMNDOOD8DDDMMMMMNMMMMMMMMMMM?==+OMMMMMM\n" +
                "MMMMMN~,,,:~=~~::::,,,,::::,::~+::8MMMMM.?MMMMMMMMMMMMMMMMMMMMMMMMMMD?==INMMMMMM\n" +
                "MMMMMN:,,,,,,::::::::::~++,,+~~++?MMMMMMM7MMMMMMNNMMMMMMMMMMMMMMMMMMD+++$MMMMMMM\n" +
                "MMMMM~:,,::~=====+++??I?:~=~++?7DMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNN$DMMMMMMM\n" +
                "MMMM?.?+?+==+++?II????I??I:7$NMMMMMMMMMMMMNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNMMM\n" +
                "MMMM.8I$?~:,::~~+++++?+?=$:+MMMMMMMMMMMMMMMMMMMMD?=+=ID8MMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMM8.NOI77=::~~=?IIIIZ,~~O=,MMMMMMMMMMMMMMMMNM$:::,:?,~?OMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMM+.NO?I7$$$7?==+=+?$DMIZ+:MMMMMMMMMMMMMMMMM?+,,..,.:::~MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMM:.ZDII$7I7$I???777OMM7Z+:MMMMMMMMMMMMMMMMMMI........,~MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMM=..N$I$$77$OZOZ$7ZMMMOO7~MMMMMMMMMMMMMMMMMMM7.......,=MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMM7..N8Z$$$$$$Z$ZINMMMMD8$+MMMMMMMMMMMMMMMMMMNN,........MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMM~..N8$I??+?7N:ZMMMMMMDO?MMMMMMMMMMMMMMMMMMMMM........MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMN,.,8$?+??$=.=MMMMMMMDO?MMMMMMMMMMMMMMMMMMMMMN,.....=MMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMN..ZO7I7I..=MMMMMMMMNO+MMMMMMMMMMMMMMMMMMMMMMM.....MMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMO..DZZI...NMMMMMMMMZZIMMMMMMMMMMMMMMMMMMMMMMM~,,..MMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMM:..DI...MMMMMMMMM+.:,+DMMMMMMMMMMMMMMMMMMMMNN....MMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMM.,7...MMMM$~~:~I?=7?$Z$I=+7NMMMMMMMMMMMMMMMMN,,,MMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMNDMMMMDNMMMMMMMMMMNND8?NMMMMMMMMMMMMMMMMMMM:?MMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMDOZ$7ZZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\n" +
                "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN";
        String[] cookie = cookieMons.split("\\r?\\n");
        try{
            for(String line: cookie){
                System.out.println(line);
                TimeUnit.MILLISECONDS.sleep(51);
            }
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}

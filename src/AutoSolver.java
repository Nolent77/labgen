import java.util.Random;


public class AutoSolver {
    public static void main(String[] args) {

        String tableau[][]=
                        {       {"#","E","#","#","#","#","#","#","#","#"},
                                {"#"," ","P","#"," "," "," ","#"," ","#"},
                                {"#","#"," ","#"," ","#","#","#"," ","#"},
                                {"#"," "," "," "," ","#"," ","#"," ","#"},
                                {"#"," ","#","#","#"," "," "," "," ","#"},
                                {"#"," ","#"," "," "," ","#","#"," ","#"},
                                {"#"," ","#"," ","#"," ","#"," "," ","#"},
                                {"#"," ","#"," ","#"," ","#"," ","#","#"},
                                {"#"," "," "," ","#"," ","#"," "," ","#"},
                                {"#","#","#","#","#","#","#","#","S","#"},};
//        tableau = mazeGenerator(10, 30);
        chemin(tableau);

    }
    static boolean realisable(String[][] tab){

        int x=0;
        int y=0;
        boolean fin=false;
        boolean realisable=true;

        for (int i = 0; i < tab[0].length; i++) {
            if (tab[0][i]=="E"){
                x=i;
                y=1;
            }
        }

        while (!fin && tab[x][y] != "S") {

            //System.out.println(tab[x][y]);

            if (tab[x][y + 1] == " " || tab[x][y + 1] == "S") {
                tab[x][y] = "V";
                y += 1;
            }
            else if (tab[x + 1][y] == " " || tab[x + 1][y] == "S") {
                tab[x][y] = "V";
                x += 1;
            }
            else if (tab[x][y - 1] == " " || tab[x][y - 1] == "S") {
                tab[x][y] = "V";
                y -= 1;
            }
            else if (tab[x - 1][y] == " " || tab[x - 1][y] == "S") {
                tab[x][y] = "V";
                x -= 1;
            }
            else if (tab[x][y + 1] == "V") {
                tab[x][y] = "C";
                y += 1;
            }
            else if (tab[x + 1][y] == "V") {
                tab[x][y] = "C";
                x += 1;
            }
            else if (tab[x][y - 1] == "V") {
                tab[x][y] = "C";
                y -= 1;
            }
            else if (tab[x - 1][y] == "V") {
                tab[x][y] = "C";
                x -= 1;
            }
            else {
                realisable=false;
                fin = true;
            }
        }
        return realisable;
    }


    static void affiche(String[][] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }


    static void chemin(String[][] tab){

        int x=0;
        int y=0;
        boolean fin=false;
        boolean realisable=true;

        for (int i = 0; i < tab[0].length; i++) {
            if (tab[0][i]=="E"){
                x=1;
                y=i;
            }
        }

        while (!fin && tab[x][y] != "S") {

            System.out.println(x+"+"+y);

            if (tab[x + 1][y] == " " || tab[x + 1][y] == "S") {
                tab[x][y] = "V";
                x += 1;
            }

            else if (tab[x][y + 1] == " " || tab[x][y + 1] == "S") {
                tab[x][y] = "V";
                y += 1;
            }

            else if (tab[x - 1][y] == " " || tab[x - 1][y] == "S") {
                tab[x][y] = "V";
                x -= 1;
            }

            else if (tab[x][y - 1] == " " || tab[x][y - 1] == "S") {
                tab[x][y] = "V";
                y -= 1;
            }

            else if (tab[x + 1][y] == "V") {
                tab[x][y] = "C";
                x += 1;
            }

            else if (tab[x][y + 1] == "V") {
                tab[x][y] = "C";
                y += 1;
            }

            else if (tab[x - 1][y] == "V") {
                tab[x][y] = "C";
                x -= 1;
            }

            else if (tab[x][y - 1] == "V") {
                tab[x][y] = "C";
                y -= 1;
            }

            else {
                realisable=false;
                fin = true;
            }
        }
        if (!realisable){
            System.out.println("Non réalisable");
        }
        else{
            for (int i = 1; i < tab.length-1; i++) {
                for (int j = 1; j < tab[i].length-1; j++) {
                    if (tab[i][j]=="C") {
                        tab[i][j] = " ";
                    }
                }
            }
            affiche(tab);
        }
    }


//        public static String[][] mazeGenerator(int line, int column) {
//            Random a = new Random();
//            String[][] tab = new String[line][column];
//            for (int i = 0; i < line; i++) {
//                for (int j = 0; j < column; j++) {
//                    if (i == 0 || i == line - 1 || j == 0 || j == column - 1) {
//                        tab[i][j] = "#";
//                    } else {
//                        tab[i][j] = (a.nextInt(4) == 0) ? "#" : " ";
//                    }
//
////                if (i >= 1 && j >= 1 && j < column-1 && i < line-1) {
////                    tab[i][j] = " ";
////                }
//                }
//
//            }
//            return tab;
//        }
public static int randomValue() {
    Random rand = new Random();
    return rand.nextInt(10) + 1;
}
public static String[][] mazeGenerator(int line, int column) {
    boolean alreadyExist = true; // variable to have only one enter
    boolean existingExist = true; // variable to have only one output
    Random a = new Random();
    String[][] tab = new String[line][column];
    for (int i = 0; i < line; i++) {
        for (int j = 0; j < column; j++) {
            if (i == 0 || i == line - 1 || j == 0 || j == column - 1) {
                tab[i][j] = "#";
            } else {
                tab[i][j] = (a.nextInt(2) == 0) ? "#" : " ";
            }
        }
    }
    for (int i = 0; i < line; i++) {
        for (int j = 0; j < column; j++) {
            if ( alreadyExist) {
                tab[0][randomValue()] = "E";
                alreadyExist = false;
            }
            if (existingExist) {
                tab[line - 1][randomValue()] = "S";
                existingExist = false;
            }
            System.out.print(tab[i][j]);

        }
        System.out.println();
    }
    return tab;
}


}
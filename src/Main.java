//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Random;  

public class Main {

    public static int randomValue() {
        Random rand = new Random();
        return rand.nextInt(10) + 1;
    }

    public static String[][] mazeGenerator(int line, int column) {
        boolean alreadyExist = true; // variable to have only one enter
        boolean existingExist = true; // variable to have only one output
        Random aleaM = new Random();
        String[][] tab = new String[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 || i == line - 1 || j == 0 || j == column - 1) {
                    tab[i][j] = "#";
                } else {
                    tab[i][j] = (aleaM.nextInt(4) == 0) ? "#" : " ";
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


        public static void main (String[]args){
            System.out.println(mazeGenerator(10, 30));


        }
    }


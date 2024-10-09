import java.util.Random;
import java.util.Arrays;

public class Main {

    static int line = 11;
    static int column = 31;

    public static int randomValue() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public void gen(int[][] lab, int x, int y, boolean[][] visited) {
        lab[x][y] = ' ';

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};
        Random random = new Random();
        int direction = random.nextInt(4);
        int posDirX = (dx[direction] * 2) + x;
        int posDirY = (dy[direction] * 2) + y;
        if (posDirX > 0 && posDirY > 0 && posDirX < line - 1 && posDirY < column - 1 && !visited[posDirX][posDirY]) {
            visited[posDirX][posDirY] = true;
            lab[posDirX][posDirY] = ' ';
            gen(lab, posDirX, posDirY, visited);
        }
    }

    public static String[][] mazeGenerator(int line, int column) {
        boolean alreadyExist = true; // variable to have only one enter
        boolean existingExist = true; // variable to have only one output
        Random aleaM = new Random();
        String[][] tab = new String[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
//                tab[i][j] = "##";
                // ---- Generate grid empty spaces ----
                if (i > 0 && j > 0 && i < line - 1 && j < column - 1) {
                    tab[i][j] = "  ";
                }
                // ---- Generate grid ----
                if (i % 2 == 0 || j % 2 == 0) {
                    tab[i][j] = "##";
                }
                // ---- Generate border ----
                if (i == 0 || j == 0 || i == line - 1 || j == column - 1) {
                    tab[i][j] = "<>";
                }
            }
        }
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (alreadyExist) {
                    tab[0][randomValue()] = "EE";
                    alreadyExist = false;
                }
                if (existingExist) {
                    tab[line - 1][randomValue()] = "SS";
                    existingExist = false;
                }
                System.out.print(tab[i][j]);
            }
            System.out.println();
        }
        return tab;
    }

    public static void main(String[] args) {
        System.out.println(mazeGenerator(line, column));



    }

}
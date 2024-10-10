import java.util.Random;

public class Main {

    static int line = 5;
    static int column = 5;

    // Function for generating a random value (for E and S specifically)
    public static int randomValue() {
        int valueR = (int) (Math.random() * column);
        while (valueR == 0 || valueR == column - 1) {
            valueR = (int) (Math.random() * column);
        }
        return valueR;
    }

    public static void Shuffle(int[] tableau) {
        Random rand = new Random();
        for (int i = tableau.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int stockageId = tableau[index];
            tableau[index] = tableau[i];
            tableau[i] = stockageId;
        }
    }

    public void mazeGen(String[][] lab, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        lab[x][y] = "   ";

        int[] mixDir = {0, 1, 2, 3};
        Shuffle(mixDir);

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        for (int direction : mixDir) {
            int pDirX = (dx[direction] * 2) + x;
            int pDirY = (dy[direction] * 2) + y;
            if (pDirX > 0 && pDirY > 0 && pDirX < line - 1 && pDirY < column - 1 && !visited[pDirX][pDirY]) {
                visited[pDirX][pDirY] = true;
                lab[dx[direction]+x][dy[direction]+y] = "   ";
                mazeGen(lab, visited, pDirX, pDirY);
            }
        }
    }

//    public static String[][] mazeDisplay(int line, int column) {
//        boolean alreadyExist = true; // variable to have only one enter
//        boolean existingExist = true; // variable to have only one output
//        String[][] tab = new String[line][column];
//        for (int i = 0; i < line; i++) {
//            for (int j = 0; j < column; j++) {
////                tab[i][j] = " # ";
//                // ---- Generate grid empty spaces ----
//                if (i > 0 && j > 0 && i < line - 1 && j < column - 1) {
//                    tab[i][j] = "   ";
//                }
//                // ---- Generate grid ----
//                if (i % 2 == 0 || j % 2 == 0) {
//                    tab[i][j] = " # ";
//                }
//                // ---- Generate border ----
//                if (i == 0 || j == 0 || i == line - 1 || j == column - 1) {
//                    tab[i][j] = "]X[";
//                }
//            }
//        }
//        // meme boucle for et le remplir de false et lorsqu'ion est sur une case on le remplis de false
//        for (int i = 0; i < line; i++) {
//            for (int j = 0; j < column; j++) {
//                if (alreadyExist) {
//                    tab[0][randomValue()] = "-E-";
//                    alreadyExist = false;
//                }
//                if (existingExist) {
//                    tab[line - 1][randomValue()] = "-S-";
//                    existingExist = false;
//                }
//                System.out.print(tab[i][j]);
//            }
//            System.out.println();
//        }
//        return tab;
//    }
    public static String[][] mazeDisplay(int line, int column) {
        String[][] tab = new String[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                tab[i][j] = " # ";
            }
        }
        for (int i = 0; i < line; i++) {
            tab[i][0] = " # ";
            tab[i][column - 1] = " # ";
        }
        for (int j = 0; j < column; j++) {
            tab[0][j] = " # ";
            tab[line - 1][j] = " # ";
        }
        tab[0][randomValue()] = " E ";
        tab[line -1][randomValue()] = " S ";

        return tab;
    }

    public static void showMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[][] lab = new String[line][column];  // Initialisation du tableau lab
        boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

        lab = mazeDisplay(line, column);

        Main m = new Main();
        m.mazeGen(lab, visited, 1, 1);

        System.out.println(mazeDisplay(line, column));  // Affichage du labyrinthe

        showMaze(lab);
    }
}
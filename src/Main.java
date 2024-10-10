import java.util.Random;
import java.util.Scanner;

public class Main {

    // Function for generating a random value (for E and S specifically)
    public static int randomValue(int column) {
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

    public static void mazeGen(String[][] lab, boolean[][] visited, int x, int y, int line, int column) {
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
                lab[dx[direction] + x][dy[direction] + y] = "   ";
                mazeGen(lab, visited, pDirX, pDirY, line, column);
            }
        }
    }

    public static String[][] mazeDisplay(int line, int column, int posE, int posS) {
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

        tab[0][posE] = " E ";
        tab[line - 1][posS] = " S ";

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

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Bienvenue sur Mad Maze !!!");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Mad Maze est un générateur de labyrinthe avec plusieurs options de");
        System.out.println("difficulté dont une option pour créer, avec vos préférences, votre");
        System.out.println("labyrinthe.");
        System.out.println("-----------------------------------------------------------------------");
        Scanner saisie = new Scanner(System.in);

        int line = 0;
        int column = 0;
        int posE = 0;
        int posS = 0;

        int diff = 0;
        while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6) {
            System.out.println("Choisis la difficulté :");
            System.out.println("[ 1 = Facile | 2 = Moyenne | 3 = Difficile | 4 = Extrême | 5 = Impossible | 6 = Personnalisée ]");

            if (saisie.hasNextInt()) {
                diff = saisie.nextInt();
            } else {
                System.out.println("⚠ Saisie incorrecte (merci de respecter les consignes indiquées) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                saisie.next();
            }
        }
        if (diff == 1) { // Facile
            line = 7;
            column = 7;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Facile");
        }
        if (diff == 2) { // Moyenne
            line = 13;
            column = 13;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Moyenne");
        }
        if (diff == 3) { // Difficile
            line = 19;
            column = 19;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Difficile");
        }
        if (diff == 4) { // Extrême
            line = 25;
            column = 25;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Extrême");
        }
        if (diff == 5) { // Impossible
            line = 31;
            column = 31;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Impossible");
            System.out.println("-----------------------------------------------------------------------");
        }
        if (diff == 6) { // Personnalisée
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Personnalisée");
            System.out.println("-----------------------------------------------------------------------");

            // Saisie de la largeur (doit être un entier impair)
            do {
                System.out.println("Saisissez la largeur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                column = saisie.nextInt();
                if (column % 2 == 0) {
                    System.out.println("⚠ La largeur doit être un entier impair ⚠");
                }
            } while (column % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la hauteur (doit être un entier impair)
            do {
                System.out.println("Saisissez la hauteur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                line = saisie.nextInt();
                if (line % 2 == 0) {
                    System.out.println("⚠ La hauteur doit être un entier impair ⚠");
                }
            } while (line % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la position de l'entrée (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de l'entrée (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posE = saisie.nextInt();
                if (posE % 2 == 0 || posE < 1 || posE > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posE % 2 == 0 || posE < 1 || posE > column - 2);

            // Saisie de la position de la sortie (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de la sortie (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posS = saisie.nextInt();
                if (posS % 2 == 0 || posS < 1 || posS > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posS % 2 == 0 || posS < 1 || posS > column - 2);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Génération du labyrinthe");
        System.out.println("-----------------------------------------------------------------------");

        String[][] lab = new String[line][column];  // Initialisation du tableau lab
        boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

        lab = mazeDisplay(line, column, posE, posS);

        Main m = new Main();
        m.mazeGen(lab, visited, 1, 1, line, column);

        System.out.println(mazeDisplay(line, column, posE, posS));  // Affichage du labyrinthe

        showMaze(lab);

    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/* Fonctionnel :

public static void main(String[] args) {

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Bienvenue sur Mad Maze !!!");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Mad Maze est un générateur de labyrinthe avec plusieurs options de");
        System.out.println("difficulté dont une option pour créer, avec vos préférences, votre");
        System.out.println("labyrinthe.");
        System.out.println("-----------------------------------------------------------------------");
        Scanner saisie = new Scanner(System.in);

        int line = 0;
        int column = 0;
        int posE = 0;
        int posS = 0;

        int diff = 0;
        while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6) {
            System.out.println("Choisis la difficulté :");
            System.out.println("[ 1 = Facile | 2 = Moyenne | 3 = Difficile | 4 = Extrême | 5 = Impossible | 6 = Personnalisée ]");

            if (saisie.hasNextInt()) {
                diff = saisie.nextInt();
            } else {
                System.out.println("⚠ Saisie incorrecte (merci de respecter les consignes indiquées) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                saisie.next();
            }
        }
        if (diff == 1) { // Facile
            line = 7;
            column = 7;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Facile");
        }
        if (diff == 2) { // Moyenne
            line = 13;
            column = 13;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Moyenne");
        }
        if (diff == 3) { // Difficile
            line = 19;
            column = 19;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Difficile");
        }
        if (diff == 4) { // Extrême
            line = 25;
            column = 25;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Extrême");
        }
        if (diff == 5) { // Impossible
            line = 31;
            column = 31;
            posE = 1;
            posS = column - 2;
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Impossible");
            System.out.println("-----------------------------------------------------------------------");
        }
        if (diff == 6) { // Personnalisée
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Personnalisée");
            System.out.println("-----------------------------------------------------------------------");

            // Saisie de la largeur (doit être un entier impair)
            do {
                System.out.println("Saisissez la largeur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                column = saisie.nextInt();
                if (column % 2 == 0) {
                    System.out.println("⚠ La largeur doit être un entier impair ⚠");
                }
            } while (column % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la hauteur (doit être un entier impair)
            do {
                System.out.println("Saisissez la hauteur (entier impair) :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                line = saisie.nextInt();
                if (line % 2 == 0) {
                    System.out.println("⚠ La hauteur doit être un entier impair ⚠");
                }
            } while (line % 2 == 0); // Continue tant que ce n'est pas un impair

            // Saisie de la position de l'entrée (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de l'entrée (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posE = saisie.nextInt();
                if (posE % 2 == 0 || posE < 1 || posE > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posE % 2 == 0 || posE < 1 || posE > column - 2);

            // Saisie de la position de la sortie (entier impair entre 1 et column-2)
            do {
                System.out.println("Saisissez la position de la sortie (entier impair entre 1 et " + (column - 2) + ") :");
                while (!saisie.hasNextInt()) {
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    saisie.next(); // Ignorer l'entrée incorrecte
                }
                posS = saisie.nextInt();
                if (posS % 2 == 0 || posS < 1 || posS > column - 2) {
                    System.out.println("⚠ La position doit être un entier impair entre 1 et " + (column - 2) + " ⚠");
                }
            } while (posS % 2 == 0 || posS < 1 || posS > column - 2);
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Génération du labyrinthe");
        System.out.println("-----------------------------------------------------------------------");

        String[][] lab = new String[line][column];  // Initialisation du tableau lab
        boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

        lab = mazeDisplay(line, column, posE, posS);

        Main m = new Main();
        m.mazeGen(lab, visited, 1, 1, line, column);

        System.out.println(mazeDisplay(line, column, posE, posS));  // Affichage du labyrinthe

        showMaze(lab);

    }
}
 */

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
A NOUS :
if (diff == 6) { // Personnalisée
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Difficulté choisie : Personnalisée");
            System.out.println("-----------------------------------------------------------------------");


            // saisie de la largeur
            System.out.println("Saisissez la largeur (entier impair) :");
            column = saisie.nextInt();
            while (line % 2 == 0) {
                if (!saisie.hasNextInt()) {
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println("Saisissez la largeur (entier impair) :");
                    column = saisie.nextInt();
                }
                if (column % 2 == 0) {
                    column = saisie.nextInt();
                } else {
                    System.out.println("⚠ Saisie incorrecte (merci de respecter les consignes indiquées) ⚠");
                    System.out.println("-----------------------------------------------------------------------");
                    saisie.next();
                }
            }

            // saisi de la hauteur
            System.out.println("Saisissez la hauteur (entier impair) :");
            line = saisie.nextInt();
            while (!saisie.hasNextInt()) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("⚠ Saisie incorrecte (merci de saisir un entier) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Saisissez la hauteur (entier impair) :");

                line = saisie.nextInt();
            }

            // saisi de la position de l'entrée
            System.out.println("Saisissez la position de l'entrée (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
            posE = saisie.nextInt();
            while (!saisie.hasNextInt() || posE % 2 == 0) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("⚠ Saisie incorrecte (entier impair requis) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Saisissez la position de l'entrée (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
                posE = saisie.nextInt();
            }

            // saisi de la position de la sortie
            System.out.println("Saisissez la position de la sortie (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
            posS = saisie.nextInt();
            while (!saisie.hasNextInt() || posS % 2 == 0) {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("⚠ Saisie incorrecte (entier impair requis) ⚠");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Saisissez la position de la sortie (entier impair entre 1 inclus et " + (column - 2) + " inclus)");
                posS = saisie.nextInt();
            }
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Génération du labyrinthe");
            System.out.println("-----------------------------------------------------------------------");

            String[][] lab = new String[line][column];  // Initialisation du tableau lab
            boolean[][] visited = new boolean[line][column];  // Initialisation du tableau visited

            lab = mazeDisplay(line, column, posE, posS);

            Main m = new Main();
            m.mazeGen(lab, visited, 1, 1, line, column);

            System.out.println(mazeDisplay(line, column, posE, posS));  // Affichage du labyrinthe

            showMaze(lab);
        }
    }
}
 */
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int counter = 0;

    /**
     * Class that sets variables for different colors in ANSI to use them to make a beautiful maze.
     * @author CF Cergy - L1 - Grp 9 - Draxan
     */
    public class SquareColor {
        // ---- ANSI Codes for Colors ----
        public static final String BACKGROUND_BLACK = "\u001B[40m"; // --- Background Color in Black ---
        public static final String BACKGROUND_YELLOW = "\u001B[43m"; // --- Background Color in Yellow ---
        public static final String BACKGROUND_GREEN = "\u001B[42m"; // --- Background Color in Green ---
        public static final String BACKGROUND_RED = "\u001B[41m"; // --- Background Color in Red ---
        public static final String BACKGROUND_CYAN = "\u001B[46m"; // --- Background Color in Cyan ---
        public static final String BACKGROUND_PURPLE = "\033[95m"; // --- Background Color in Purple ---
        public static final String RESET = "\u001B[0m"; // --- Reset Text Status ---
        public static final String BLACK = "\u001B[30m"; // --- Text Color in Black ---
    }

    public static final Scanner entry = new Scanner(System.in);

//    public static int randomValue(int column) { // Old function to set random positions for
//        int valueR = (int) (Math.random() * column); // the entry E and the exit S
//        while (valueR == 0 || valueR == column - 1) {
//            valueR = (int) (Math.random() * column);
//        }
//        return valueR;
//    }

    /**
     * Void Function that generates the way values of an array of its same type are mixed in a random order.
     * @param array has an integer[] for an array to generate a random number to mix values in an array
     * @return Nothing
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static void Shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int storageId = array[index];
            array[index] = array[i];
            array[i] = storageId;
        }
    }

    /**
     * Void Function that generates a random path in the grid made by the function mazeGen to make it a maze.
     * @param array has a String[][] for an array, host of the maze
     * @param visited has a boolean[][] to assign if the position is already visited
     * @param x has an integer for the current position on the x-axis
     * @param y has an integer for the current position on the y-axis
     * @param line has an integer for the height of the maze to make sure the position is valid
     * @param column has an integer for the length of the maze to make sure the position is valid
     * @return Nothing
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static void mazeGen(String[][] array, boolean[][] visited, int x, int y, int line, int column, boolean Complexite) {
        visited[x][y] = true;
        array[x][y] = " ";
        long startTimeEnd = 0;
        long startTime = 0;
        if (!Complexite) {
            startTime = System.nanoTime();

        }

        int[] mixDir = {0, 1, 2, 3};
        Shuffle(mixDir);

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        for (int direction : mixDir) {
            int pDirX = (dx[direction] * 2) + x;
            int pDirY = (dy[direction] * 2) + y;
            if (pDirX > 0 && pDirY > 0 && pDirX < line - 1 && pDirY < column - 1 && !visited[pDirX][pDirY]) {
                visited[pDirX][pDirY] = true;
                array[dx[direction] + x][dy[direction] + y] = " ";
                counter += 1;
                mazeGen(array, visited, pDirX, pDirY, line, column, true);
            }
        }
        if (!Complexite) {
            startTimeEnd = System.nanoTime();
            System.out.println("le labyrinthe s'est générer en " + ((startTimeEnd - startTime) / 10000000.0) + " milliseconde ");
            System.out.println("et s'est appeler " + counter + " fois");
        }
    }

    /**
     * Fonction String[][] qui imprime une grille pour qu'elle soit ensuite creusée par la fonction mazeGen.
     * @param line en tant qu'entier pour la hauteur de la grille et du labyrinthe plus tard
     * @param column en tant qu'entier pour la largeur de la grille et du labyrinthe plus tard
     * @param posE en tant qu'entier pour la position de l'entrée E
     * @param posS en tant qu'entier pour la position de la sortie S
     * @return tab[line][column]
     * @author CF Cergy - L1 - Grp 9 - Draxan & Nolhan
     */
    public static String[][] mazeGridDisplay(int line, int column, int posE, int posS) {
        String[][] tab = new String[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                tab[i][j] = "#";
            }
        }
        for (int i = 0; i < line; i++) {
            tab[i][0] = "#";
            tab[i][column - 1] = "#";
        }
        for (int j = 0; j < column; j++) {
            tab[0][j] = "#";
            tab[line - 1][j] = "#";
        }

        tab[0][posE] = "E";
        tab[line - 1][posS] = "S";

        return tab;
    }

    /**
     * Fonction void qui imprime l'entièreté du labyrinthe avec de belles couleurs.
     * @param maze en tant que String[][]
     * @return Rien
     * @author CF Cergy - L1 - Grp 9 - Draxan
     */
    public static void showMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                // --- Définie la couleur des murs en noir ---
                if (maze[i][j].equals("#")) {
                    System.out.print(SquareColor.BACKGROUND_BLACK+"   "+SquareColor.RESET);
                }
                // --- Définie la couleur de l'entrée E en Vert et la lettre E en noir ---
                else if (maze[i][j].equals("E")) {
                    System.out.print(SquareColor.BACKGROUND_GREEN+SquareColor.BLACK+" E "+SquareColor.RESET);
                }
                // --- Définie la couleur de la sortie S en rouge et la lettre S en noir ---
                else if (maze[i][j].equals("S")) {
                    System.out.print(SquareColor.BACKGROUND_RED+SquareColor.BLACK+" S "+SquareColor.RESET);
                }
                // --- Définie la couleur du chemin du Solver en Jaune ---
                else if (maze[i][j].equals("V")) {
                    System.out.print(SquareColor.BACKGROUND_YELLOW+"   "+SquareColor.RESET);
                }
                // --- Définie la couleur du joueur en Jaune ---
                else if (maze[i][j].equals("\uD83D\uDE21")) {
                    System.out.print(SquareColor.BACKGROUND_YELLOW+"   "+SquareColor.RESET);
                }
                // --- Définie la couleur du chemin en Cyan ---
                else {
                    System.out.print(SquareColor.BACKGROUND_CYAN+"   "+SquareColor.RESET);
                }
            }
            System.out.println();
        }
    }

    public static void importOrCreate(String choiceF, String save, String play, int diff, int line, int column, int posE, int posS) throws IOException {
        while (!choiceF.equals("IMPORT") && !choiceF.equals("CREATE")) { // Choix du mode

            System.out.println("Choose a mode :");
            System.out.println("[ IMPORT = Import a maze's save | CREATE = Create a new maze ]");
            System.out.println("------------------------------------------------------------------------");

            if (entry.hasNextLine()) {
                choiceF = entry.nextLine();
            } else {
                System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                System.out.println("------------------------------------------------------------------------");
                entry.next();
            }
        }
        if (choiceF.equals("IMPORT")) { // Choisir le fichier .labgen à charger
            String Nom = System.getProperty("user.name");
            String directoryPath = "C:/Users/"+Nom+"/Documents/Labyrinthes/";
            while (!(new File(directoryPath+save+".labgen").isFile())) {
                System.out.println("Choose your save file (example : lab01) :");
                System.out.println("------------------------------------------------------------------------");

                if (entry.hasNextLine()) {
                    save = entry.nextLine();
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    System.out.println("------------------------------------------------------------------------");
                    entry.next();
                }
            }
            String[][] array = SaveLabgen.readLabFile(directoryPath);

            System.out.println("------------------------------------------------------------------------");
            System.out.println("Maze generated");
            System.out.println("------------------------------------------------------------------------");

            showMaze(array); // Affiche le labyrinthe
            while (!play.equals("PLAY") && !play.equals("SOLVE")) { // Jouer ou Solver

                System.out.println("Do you want to play or let the Solver solve it ? :");
                System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                System.out.println("------------------------------------------------------------------------");

                if (entry.hasNextLine()) {
                    play = entry.nextLine();
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    System.out.println("------------------------------------------------------------------------");
                    entry.next();
                }
            }
            if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                System.out.println("Let's play then !");
                System.out.println("------------------------------------------------------------------------");
                Move.move(array);
            }
            if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                AutoSolver.pathSolver(array);
            }
        }
        if (choiceF.equals("CREATE")){ // Créer un labyrinthe
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Mode chosen : Create");
            System.out.println("------------------------------------------------------------------------");
            mazeDifficulty(diff, play, line, column, posE, posS);
        }
    }

    public static void mazeDifficulty(int diff, String play, int line, int column, int posE, int posS) throws IOException {
        while (diff != 1 && diff != 2 && diff != 3 && diff != 4 && diff != 5 && diff != 6) {

            System.out.println("Choose the difficulty :");
            System.out.println("[ 1 = Easy | 2 = Medium | 3 = Hard | 4 = Extreme | 5 = Impossible | 6 = Custom ]");

            if (entry.hasNextInt()) {
                diff = entry.nextInt();
            } else {
                System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                System.out.println("------------------------------------------------------------------------");
                entry.next();
            }
        }
        if (diff == 1) { // Easy - Facile
            line = 7;
            column = 7;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Easy");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play or let the Solver solve it ? :");
                        System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 2) { // Medium - Moyenne
            line = 13;
            column = 13;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Medium");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play or let the Solver solve it ? :");
                        System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 3) { // Hard - Difficile
            line = 19;
            column = 19;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Hard");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play or let the Solver solve it ? :");
                        System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 4) { // Extreme - Extrême
            line = 25;
            column = 25;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Extreme");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play or let the Solver solve it ? :");
                        System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 5) { // Impossible - Impossible
            line = 31;
            column = 31;
            posE = 1;
            posS = column - 2;
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Impossible");
            System.out.println("------------------------------------------------------------------------");
            String mazeS = "";
            mazeS = entry.nextLine();
            do { // Saisie de la commande que veut lancer l'utilisateur
                System.out.println("------------------------------------------------------------------------");
                System.out.println("What do you want to do (in full maj) ?");
                System.out.println("[ GEN = Generate the maze | QUIT = Quit the program ]");
                while (!entry.hasNextLine()) {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    entry.next();
                }
                mazeS = entry.nextLine();
                if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Maze generated");
                    System.out.println("------------------------------------------------------------------------");

                    String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                    boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                    array = mazeGridDisplay(line, column, posE, posS);

                    Main m = new Main();
                    m.mazeGen(array, visited, 1, 1, line, column, false);

                    System.out.println(mazeGridDisplay(line, column, posE, posS));

                    showMaze(array); // Affiche le labyrinthe
                    while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                        System.out.println("Do you want to play or let the Solver solve it ? :");
                        System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                        System.out.println("------------------------------------------------------------------------");

                        if (entry.hasNextLine()) {
                            play = entry.nextLine();
                        } else {
                            System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                            System.out.println("------------------------------------------------------------------------");
                            entry.next();
                        }
                    }
                    if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                        System.out.println("Let's play then !");
                        System.out.println("------------------------------------------------------------------------");
                        Move.move(array);
                    }
                    if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                        AutoSolver.pathSolver(array);
                    }
                    if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                        SaveLabgen.SaveMaze(array);
                    }
                } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Mad Maze is closing, thank you for using it !");
                    System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                    System.out.println("------------------------------------------------------------------------");
                    System.exit(0);
                } else {
                    System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                }
            } while (!mazeS.equals("GEN") && !mazeS.equals("QUIT"));
        }
        if (diff == 6) { // Custom - Personnalisée
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Difficulty Chosen : Custom");
            System.out.println("------------------------------------------------------------------------");
            String mazeS = "";
            do {
                // Saisie de la largeur (doit être un entier impair)
                do {
                    System.out.println("Enter the width (odd integer) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    column = entry.nextInt();
                    if (column % 2 == 0) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (column % 2 == 0); // Boucle tant que ce n'est pas un entier impair

                // Saisie de la hauteur (doit être un entier impair)
                do {
                    System.out.println("Enter the height (odd integer) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    line = entry.nextInt();
                    if (line % 2 == 0) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (line % 2 == 0); // Boucle tant que ce n'est pas un entier impair

                // Saisie de la position de l'entrée (entier impair entre 1 et column-2)
                do {
                    System.out.println("Enter the position of the entry (odd integer between 1 included and " + (column - 2) + " included) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    posE = entry.nextInt();
                    if (posE % 2 == 0 || posE < 1 || posE > column - 2) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (posE % 2 == 0 || posE < 1 || posE > column - 2); // Boucle tant que ce n'est pas un entier impair
                // et que la position sur la grille n'est pas valide

                // Saisie de la position de la sortie (entier impair entre 1 et column-2)
                do {
                    System.out.println("Enter the position of the exit (odd integer between 1 included and " + (column - 2) + " included) :");
                    while (!entry.hasNextInt()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    posS = entry.nextInt();
                    if (posS % 2 == 0 || posS < 1 || posS > column - 2) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (posS % 2 == 0 || posS < 1 || posS > column - 2); // Boucle tant que ce n'est pas un entier impair
                // et que la position sur la grille n'est pas valide

                mazeS = entry.nextLine();
                // Saisie de la commande que veut lancer l'utilisateur
                do {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("What do you want to do (in full maj) ?");
                    System.out.println("[ GEN = Generate the maze | CONF = configure parameters of the maze | QUIT = Quit the program ]");
                    while (!entry.hasNextLine()) {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                        entry.next();
                    }
                    mazeS = entry.nextLine();
                    if (mazeS.equals("GEN")) { // Commande pour générer le labyrinthe et l'afficher
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Maze generated");
                        System.out.println("------------------------------------------------------------------------");

                        String[][] array = new String[line][column]; // Initialisation de array[][] avec line et column
                        boolean[][] visited = new boolean[line][column]; // Initialisation de visited[][] avec line et column

                        array = mazeGridDisplay(line, column, posE, posS);

                        Main m = new Main();
                        m.mazeGen(array, visited, 1, 1, line, column, false);

                        System.out.println(mazeGridDisplay(line, column, posE, posS));

                        showMaze(array); // Affiche le labyrinthe
                        while (!play.equals("PLAY") && !play.equals("SOLVE") && !play.equals("SAVE")) {

                            System.out.println("Do you want to play or let the Solver solve it ? :");
                            System.out.println("[ PLAY = Move in the maze | Solve = Let the Solver cook ]");
                            System.out.println("------------------------------------------------------------------------");

                            if (entry.hasNextLine()) {
                                play = entry.nextLine();
                            } else {
                                System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                                System.out.println("------------------------------------------------------------------------");
                                entry.next();
                            }
                        }
                        if (play.equals("PLAY")) { // Se déplacer dans le labyrinthe
                            System.out.println("Let's play then !");
                            System.out.println("------------------------------------------------------------------------");
                            Move.move(array);
                        }
                        if (play.equals("SOLVE")) { // Laisser le Solver résoudre le labyrinthe
                            AutoSolver.pathSolver(array);
                        }
                        if (play.equals("SAVE")) { // Sauvegarder le labyrinthe
                            SaveLabgen.SaveMaze(array);
                        }
                    } else if (mazeS.equals("CONF")) { // Commande pour reconfigurer les paramètres de génération
                        System.out.println("Reconfiguring the maze :");

                    } else if (mazeS.equals("QUIT")) { // Commande pour fermer le programme
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Mad Maze is closing, thank you for using it !");
                        System.out.println("Credits : Draxan LT, Nolhan O, Liam D, Ghazi K");
                        System.out.println("------------------------------------------------------------------------");
                        System.exit(0);
                    } else {
                        System.out.println("⚠ Incorrect entry (please follow instructions) ⚠");
                    }
                } while (!mazeS.equals("GEN") && !mazeS.equals("CONF") && !mazeS.equals("QUIT"));
            } while (true);
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("------------------------------------------------------------------------");
        System.out.println("  ◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤————Welcome to Mad Maze————◥◣◥◣◥◣◥◣◥◣◥◣◥◣◥");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Mad Maze is a generator of mazes with several difficulty options ");
        System.out.println("including an option to create your own maze with your preferences !!");
        System.out.println("------------------------------------------------------------------------");

        String choiceF = "";
        String save = "";
        String playOrSolve = "";
        int line = 0;
        int column = 0;
        int posE = 0;
        int posS = 0;
        int diff = 0;

        importOrCreate(choiceF, save, playOrSolve, diff, line, column, posE, posS);
    }
}
import java.util.Scanner;

public class Move {
//    public static void main(String[] args) {
//        String tableau[][]=
//                {       {"#","E","#","#","#","#","#","#","#","#"},
//                        {"#"," "," ","#"," "," "," "," "," ","#"},
//                        {"#","#"," ","#"," ","#","#","#"," ","#"},
//                        {"#"," "," "," "," ","#"," ","#"," ","#"},
//                        {"#"," ","#","#","#"," "," "," "," ","#"},
//                        {"#"," ","#"," "," "," ","#","#"," ","#"},
//                        {"#"," ","#"," ","#"," ","#"," "," ","#"},
//                        {"#"," ","#"," ","#"," ","#"," ","#","#"},
//                        {"#"," ","#"," ","#"," ","#"," "," ","#"},
//                        {"#","#","#","#","#","#","#","#","S","#"},};
//        AutoSolver.affiche(tableau);
//        move(tableau);
//    }

    public static void move(String[][]GiveTab) {
        //ajouter x et y
        int x = 0;
        int y = 0;
        boolean End = false;
        //mettre le joueur sous E
        for (int i = 0; i < GiveTab[0].length; i++) {
            if (GiveTab[0][i]=="E") {
                x = 1;
                y = i;
            }

        }
        //placer le joueur
        GiveTab[x][y]="\uD83D\uDE21";
        while (!End){
            //afficher le laby
            Main.showMaze(GiveTab);
            //recuperer les touche
            Scanner sc = new Scanner(System.in);

            String move = sc.nextLine();
            //appeler le solver
            if (move.equals("Ghazi and Liam the bg")){
                GiveTab[x][y]=" ";
                AutoSolver.chemin(GiveTab);
                End = true;
            }
                //les mouvement ZQSD avec un print pour dire qu'il y a un mure
            if (move.equals("d") ||move.equals("D")) {
                if (GiveTab [x][y+1]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x][y+1]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x][y+1]="\uD83D\uDE21";
                    y+=1;
                }
            }
            if (move.equals("q") ||move.equals("Q")) {
                if (GiveTab [x][y-1]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x][y-1]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x][y-1]="\uD83D\uDE21";
                    y-=1;
                }
            }
            if (move.equals("z") ||move.equals("Z")) {
                if (GiveTab [x-1][y]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x-1][y]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x-1][y]="\uD83D\uDE21";
                    x-=1;
                }
            }
            //pour finir la boucle quand on est sur le S
            if (move.equals("s") ||move.equals("S")) {
                if (GiveTab [x+1][y]=="#"){
                    GiveTab[x][y]="\uD83D\uDE21";
                    System.out.println("--------------You can't we have wall----------------");
                }
                if (GiveTab[x+1][y]=="S"){
                    End = true;
                }


                if (GiveTab[x+1][y]== " " ){
                    GiveTab[x][y]=" " ;
                    GiveTab[x+1][y]="\uD83D\uDE21";
                    x+=1;
                }

            }

        }
        //sortir du while pour afficher
        System.out.println("GG you win");

}
}
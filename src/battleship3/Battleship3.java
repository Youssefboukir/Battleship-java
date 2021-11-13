
package battleship3;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Battleship3 {
     public static int numline = 10;
    public static int numCols = 10;
    public static void main(String[] args) {
        int i, j;

        char[][] matrice = new char[numline][numCols ];
        for( i = 0; i < matrice.length; i++){
            for( j = 0; j < matrice[i].length; j++){
                matrice[i][j] = '-';
            }
        }   
        Map<Character,Integer[]> map=new HashMap<>();
            map.put('s', new Integer[] {2,2});
            map.put('d', new Integer[] {2,2});
            map.put('C', new Integer[] {3,1});
            map.put('c', new Integer[] {4,1});
            map.put('P', new Integer[] {5,1});
        
        System.out.println("Les caractères à inserer :\n"
                         + "Char\t\t taille\t\trepetitions");
        for(Map.Entry m:map.entrySet()){
            System.out.println("   "+(char) m.getKey() + "\t\t   " +((Integer[]) m.getValue())[0] + "\t\t   " + ((Integer[]) m.getValue())[1]);
        }       
        for(Map.Entry m:map.entrySet()){  
            int direction;
            int nbr_cases = ((Integer[]) m.getValue())[0];
            int repetition = ((Integer[]) m.getValue())[1];
            boolean continuer;
            do {
                continuer = true;
                direction=(int) (Math.random()*2);
                if(direction==0) {
                    int x =(int) (Math.random() * nbr_cases);
                    int y = (int) (Math.random()*numline-1);
                    System.out.println("Charactere : " + m.getKey() + 
                                    "\n\tPosition : x="+x+"\ty="+y+
                                    "\n\trepetitions : "+repetition+
                                    "\n\tDirection : Verticale");
                    for(i=x;i<x+nbr_cases&&i<numline;i++) {
                        if(matrice[i][y]!='-')
                            continuer=false;
                    }

                    for(i=x;(i<x+nbr_cases)&&i<numline&&continuer;i++)
                        matrice[i][y] = (char) m.getKey();
                    
                } else {
                    int y =(int) (Math.random() * nbr_cases);
                    int x = (int) (Math.random()*numCols-1);
                    System.out.println("Charactere : " + m.getKey() + 
                                    "\n\tPosition : x="+x+"\ty="+y+
                                    "\n\trepetitions : "+repetition+
                                    "\n\tDirection : Horizontale");
                    for(i=y;i<y+nbr_cases&&i<numCols;i++)
                        if(matrice[i][y]!='-')
                            continuer=false;
                    
                    for(i=y;i<y+nbr_cases&&i<numCols&&continuer;i++) {
                        matrice[x][i] = (char) m.getKey();
                    }
                }
                
                if (continuer)
                    repetition--;
                System.out.println("continuer =" + continuer + "   repetitions = " + repetition);
            }while ((!continuer) || repetition!=0);
                afficher_tableau(matrice, numline, numCols);
        }
      int y,x;
     do{  
        do {
             Random r = new Random();
               x = 0+ r.nextInt(numCols -1 );
            Random e = new Random();
               y= 0+ r.nextInt(numline -1 );
            System.out.println("In patrol : \n"+
                            "\tx = " + x +
                            "\ty = " + y);
                if (matrice[x][y]=='-'){
            matrice[x][y]= 'm';}
          afficher_tableau(matrice, numline, numCols);      
        }while ( x<0 || y<0  || matrice[x][y]=='-' || matrice[x][y]=='m' || matrice[x][y]=='D');
        
        char eliminate = matrice[x][y];
        
        matrice = kill(matrice, eliminate, x, y, numCols, numline,'o');
        System.out.println("\n\n############################\n"
                             + "############################\n"
                             + "      Matrice finale : "
                             + "\n  caractère liminé : "+ eliminate +"\n\n");
        afficher_tableau(matrice, numline, numCols);
     }while( x<0 || y<0  || matrice[x][y]=='-' || matrice[x][y]=='m' || matrice[x][y]=='D'|| matrice[x][y]=='s'
                                    || matrice[x][y]=='d'|| matrice[x][y]=='p'|| matrice[x][y]=='C'|| matrice[x][y]=='c');
    }
    
      
    public static char[][] kill(char[][] matrice, char c, int x, int y, int cols, int lines, char direction){
        System.out.println("______________________________________\n\nAvant : Cellule actuelle \n"+
                            "\tx = " + x +
                            "\ty = " + y +
                            "\tCaractere : " + matrice[x][y]);
        afficher_tableau(matrice, lines, cols);
        
        if(matrice[x][y]=='h'){
            matrice[x][y]= 'D';
            
        } else {
            matrice[x][y] = 'h';
        }
        
        System.out.println("Après : Cellule actuelle \n"+
                            "\tx = " + x +
                            "\ty = " + y +
                            "\tCaractere : " + matrice[x][y]);
        afficher_tableau(matrice, lines, cols);
        boolean trouve = false;
        
        if(direction=='x' || direction == 'o') {
            if(x>0 && (matrice[x-1][y] == c || matrice[x-1][y] == 'h' )){
                kill(matrice, c, x-1, y, cols, lines, direction);
                trouve = true;
            }               
            if(x<cols-1 && (matrice[x+1][y] == c || matrice[x+1][y] == 'h' )){
                kill(matrice, c, x+1, y, cols, lines, direction);
                trouve = true;
            }       
        }
        if(!trouve && (direction=='y' || direction == 'o')){
            if(y>0 && ( matrice[x][y-1] == c || matrice[x][y-1] == 'h' ))
                kill(matrice, c, x, y-1, cols, lines, direction);
            if ( y < lines-1 && (matrice[x][y+1] == c || matrice[x][y+1] == 'h' ))
                kill(matrice, c, x, y+1, cols, lines, direction);
        }
        return matrice;
    }   
    
    public static void afficher_tableau(char[][] Tableau, int lines, int cols) {
        for(int i=0;i<lines;i++) {
            System.out.print("Ligne + "+i+"          ");
            for (int j=0;j<cols;j++) {
                System.out.print("   " + Tableau[i][j]);
            }
            System.out.println("\n");
        }
    }
    }   

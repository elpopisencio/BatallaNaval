
<<<<<<< HEAD
import java.util.*;
=======
package principal;
import java.util.Scanner;

>>>>>>> dc299467d3a7539c728f016bf82887119a7b8b8c
/**
 *
 * @author popix
 */
public class Tablero {

    private int[][] tablero;
    private int cantidad;
    private int barco;

    public Tablero(int dim) {
        tablero = new int[dim][dim];
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                tablero[i][j] = 0;
            }
        }
        cantidad = 0;
        barco = 1;
    }

    public boolean insertarBarco(int lon, boolean dirHor, int fil, int col) {
        /*
         * Coloca un barco en la posicion fil, col, con longitud lon en
         * direccion horizantal si dirHor es true. Devuelve true en caso de lograrlo
         */
        boolean retorno = false;
        if (dirHor) {
            if (col + lon <= tablero.length) {
                for (int i = 0; i < lon; i++) {
                    tablero[fil][col + i] = barco;
                }
                cantidad = cantidad + lon;      
                barco = barco + 1;
                retorno = true;
            }
        } else {
            if (fil + lon <= tablero.length) {
                for (int i = 0; i < lon; i++) {
                    tablero[fil + i][col] = barco;
                }
                cantidad = cantidad + lon;
                barco = barco + 1;
                retorno = true;
            }
        }
        return retorno;
    }

    public int atacarBarco(int fil, int col) {
        /*
         * Bombardea la posicion fil, col. 
         * Retorna: 0 si le dio al agua
                    1 si le dio a un barco
                    -1 si hundio un barco
         */
        int aux = 0;
        if (tablero[fil][col] >= 1) {
            if(this.seHundio(fil, col)){
                aux = -1;
            }else{
                aux = 1;
            }
            tablero[fil][col] = -1;
            cantidad--;
        }
        return aux;
    }

    private boolean seHundio(int fil, int col) {
        boolean retorno = true;
        int aux = tablero[fil][col];
        if(     fil > 0 && tablero[fil -1][col] == aux ||
                fil < tablero.length - 1 && tablero[fil + 1][col] == aux ||
                col > 0 && tablero[fil][col-1] == aux ||
                col < tablero.length - 1 && tablero[fil][col+1] == aux){
            retorno = false;
        }
        return retorno;
    }
    
    public boolean quedanBarcos(){
        return cantidad > 0;
    }
    

        public String toString(boolean mio){
        String string = "";
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero.length; j++){
                if(mio && tablero[i][j] > 0){
                    string = string + tablero[i][j] + "  ";
                } else{
                    if(tablero[i][j] < 0){
                        string = string + "*  ";
                    }else{
                        string = string + "-  ";
                    }
                }
            }
            string = string + "\n";
        }
        return string;
    }
    
    // Main de pruebas:
    public static void main(String [ ] args){
        Tablero tablerito = new Tablero(10);
        System.out.println(tablerito.toString(true));
        if(tablerito.insertarBarco(4, false, 6, 8)){
        System.out.println(tablerito.toString(false));
            if(tablerito.atacarBarco(6, 8) == 1){
                System.out.println("le diste");
            }
            System.out.println(tablerito.toString(false));
        }else{
            System.out.println("El barco no entro");
        }
<<<<<<< HEAD
        Scanner scan = new Scanner(System.in);
        int d = scan.nextInt();
=======
        Scanner keyboard = new Scanner(System.in);
        int a = keyboard.nextInt();
        System.out.println(a);
>>>>>>> dc299467d3a7539c728f016bf82887119a7b8b8c
    }
}

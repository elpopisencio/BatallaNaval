/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 *
 * @author popix
 */
public class Tablero {

    private char[][] tablero;
    private int cantidad;
    private int barco;

    public Tablero(int dim) {
        tablero = new char[dim][dim];
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                tablero[i][j] = '-';
            }
        }
        cantidad = 0;
        barco = '1';
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
                    tablero[fil][col + i] = (char)barco;
                }
                cantidad = cantidad + lon;      
                barco = barco + 1;
                retorno = true;
            }
        } else {
            if (fil + lon <= tablero.length) {
                for (int i = 0; i < lon; i++) {
                    tablero[fil + i][col] = (char)barco;
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
            tablero[fil][col] = '*';
            cantidad--;
        }
        return aux;
    }

    private boolean seHundio(int fil, int col) {
        boolean retorno = true;
        char aux = tablero[fil][col];
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
    
    @Override
        public String toString(){
        String string = "";
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero.length; j++){
                string = string + tablero[i][j] + "  ";
            }
            string = string + "\n";
        }
        return string;
    }
    
    // Main de pruebas:
    public static void main(String [ ] args){
        Tablero tablerito = new Tablero(10);
        System.out.println(tablerito.toString());
        if(tablerito.insertarBarco(4, false, 6, 8)){
            if(tablerito.atacarBarco(6, 8) == 1){
                System.out.println("le diste");
            }
            System.out.println(tablerito.toString());
        }else{
            System.out.println("El barco no entro");
        }
        Scanner scan = new Scanner(System.in);
        int d = scan.nextInt();
    }
}

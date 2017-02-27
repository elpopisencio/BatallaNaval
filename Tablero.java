
import java.util.Scanner;

public class Tablero {

    private int[][] tablero;
    private int cantidad;
    private int barco;

    public Tablero(int dim) {
        tablero = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
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
            if (insertarBarcoInterno(lon, dirHor, fil, col)) {
                cantidad = cantidad + lon;
                //barco = barco + 1;
                retorno = true;
            }
        } else {
             if (insertarBarcoInterno(lon, dirHor, fil, col)) {
                cantidad = cantidad + lon;
                //barco = barco + 1;
                retorno = true;
            }
        }
        return retorno;
    }

    private boolean insertarBarcoInterno(int lon, boolean dirHor, int fil, int col) {
        boolean retorno = false;
        if (fil < tablero.length && col < tablero.length) {
            if (lon != 1) {
                if (dirHor) {
                    if (insertarBarco(lon - 1, dirHor, fil, col + 1)) {
                        tablero[fil][col] = barco;
                        retorno = true;
                    }
                } else {
                    if (insertarBarco(lon - 1, dirHor, fil + 1, col)) {
                        tablero[fil][col] = barco;
                        retorno = true;
                    }
                }
            }else{
                tablero[fil][col] = barco;
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
            if (this.seHundio(fil, col)) {
                aux = -1;
            } else {
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
        if (fil > 0 && tablero[fil - 1][col] == aux
                || fil < tablero.length - 1 && tablero[fil + 1][col] == aux
                || col > 0 && tablero[fil][col - 1] == aux
                || col < tablero.length - 1 && tablero[fil][col + 1] == aux) {
            retorno = false;
        }
        return retorno;
    }

    public boolean quedanBarcos() {
        return cantidad > 0;
    }

    public String toString(boolean mio) {
        String string = "";
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (mio && tablero[i][j] > 0) {
                    string = string + tablero[i][j] + "  ";
                } else {
                    if (tablero[i][j] < 0) {
                        string = string + "*  ";
                    } else {
                        string = string + "-  ";
                    }
                }
            }
            string = string + "\n";
        }
        return string;
    }

    // Main de pruebas:
    public static void main(String[] args) {
        Tablero tablerito = new Tablero(10);
        menu(tablerito);
    }

    private static void menu(Tablero tab) {
        int a, b, c;
        boolean band = true;
        while (band) {
            System.out.println("Ingrese un valor:\n"
                    + "1 - Agregar barco\n"
                    + "2 - Bombardear barco\n"
                    + "0 - salir");
            Scanner keyboard = new Scanner(System.in);
            a = keyboard.nextInt();
            if (a == 1) {
                System.out.print("Ingrese coordenadas: \n x: ");
                a = keyboard.nextInt() - 1;
                System.out.print("y: ");
                b = keyboard.nextInt() - 1;
                System.out.println("Ingrese la que corresponda:\n"
                        + "1 - Vertical\n"
                        + "2 - Horizontal");
                c = keyboard.nextInt();
                tab.insertarBarco(3, (c == 2), a, b);
                System.out.println(tab.toString(true));
            }
            if (a == 2){
				System.out.print("Ingrese coordenadas: \n x: ");
                a = keyboard.nextInt() - 1;
                System.out.print("y: ");
                b = keyboard.nextInt() - 1;
                tab.atacarBarco(a, b);
                System.out.println(tab.toString(true));                
			}
			if (a == 0){
				band = false;	
			}
        }
    }
}

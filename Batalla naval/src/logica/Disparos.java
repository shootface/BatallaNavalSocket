
package logica;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author JuanGuaba
 */
public class Disparos {
    private int[][] marint = new int [10][10];
    private boolean[][] mar2boolSalida = new boolean [10][10];
    private Sistema sistema;
    private boolean gano = false;
    private boolean flag = false;
    private int conteo = 0;
    
    public Disparos(Sistema aThis) {
        sistema = aThis;
    }
    
    public void recorrer(JLabel[][] mar){
        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
                if(mar[i][j].getBackground()==Color.BLACK){
                    marint[i][j]=1;
                }else{
                    marint[i][j]=0;
                }
                if(mar[i][j].getBackground()==Color.GREEN){
                    marint[i][j]=2;
                }
                mar2boolSalida[i][j]=false;
            }    
        }
    }
    public boolean ganar(JLabel[][] mar){
        System.out.println("inicio ganar");
        int cont = 0;
        for(int i=0;i<10;i++){
            for(int j=0; j<10;j++){
                if(mar[i][j].getBackground()==Color.RED){
                    cont = cont + 1 ;
                    System.out.println("color : "+mar[i][j].getBackground());
                }
            }    
        }
        if(cont==16){
            gano = true;
        }
        System.out.println("contador : "+cont);
        conteo = cont;
        return gano;
    }
    
    public boolean disparoLlega(int i,int j){
        boolean llego=false;
        if(marint[i][j]==1){
            llego=true;
        }
        if(marint[i][j]==2){
            llego=true;
            flag = true;
        }
        return llego;
    }

    public int[][] getMarint() {
        return marint;
    }

    public boolean[][] getMar2boolSalida() {
        return mar2boolSalida;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getConteo() {
        return conteo;
    }
    
    
    
}


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author migue
 */
public class Main
{
	public static void main(String[] args) {
	
	Scanner teclado = new Scanner(System.in);
		
    String palabra;
    palabra = teclado.nextLine();
    int contador=0;
    char[] a={0,0,0};
    a[0]=palabra.charAt(0);
    
    for(int i =1; i<palabra.length();i++){
        for(int n =0;n<3;n++){
            if(!(palabra.charAt(i) == a[n])){
                a[n]=palabra.charAt(i);
                
            }else{
                i++;
                
            }
            contador++;
            
        }
    }
    System.out.println(contador);
	}
}


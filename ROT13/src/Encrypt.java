/*Justin Yip #7032870
 *Thomas Chapados Muermans #6775179
 *Assignment 4
 *Graphical User Interface, encrypt/decrypt text
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Encrypt {
	
	private String encrypted;
	private char[] ascii;
	private int size;
	
	//Decrypt input text depending on rotNum
	public String rot(int rotNum, String read){
		read = read.toLowerCase();
		ascii = read.toCharArray();
		for(int i=0; i< ascii.length; i++){
			int dAscii = ascii[i];
			if(dAscii>= 32 && dAscii <= 64 || dAscii >= 91 && dAscii <= 96 || dAscii >= 123 && dAscii <= 127 || ascii[i] == '\n' ){
				ascii[i]= ascii[i];
			}
			else if(dAscii+rotNum > 122){
				dAscii = dAscii + rotNum -123 +97;
				ascii[i] =(char)(dAscii);
			}
			else{
				ascii[i] = (char)(ascii[i]+rotNum);
			}
		}
		encrypted = new String(ascii);
		return encrypted;
		
	}
	
	public String rotNum(int rotNum, String read){
		read.toLowerCase();
		ascii = read.toCharArray();
		for(int i=0; i< ascii.length; i++){
			int dAscii = ascii[i];
			if(dAscii>= 32 && dAscii <= 64 || dAscii >= 91 && dAscii <= 96 || dAscii >= 123 && dAscii <= 127 || ascii[i] == '\n' ){
				if(dAscii> 47 && dAscii < 57){
					if(dAscii + rotNum > 57){
						dAscii = dAscii+rotNum-57+47;
						ascii[i] = (char)(dAscii);
					}
					else{
						ascii[i] = (char)(ascii[i]+rotNum);
					}
				}
				ascii[i]= ascii[i];
			}
			else if(dAscii+rotNum > 122){
				dAscii = dAscii + rotNum -122 +96;
				ascii[i] =(char)(dAscii);
			}
			else{
				ascii[i] = (char)(ascii[i]+rotNum);
			}
		}
		encrypted = new String(ascii);
		return encrypted;
		
	}
	
	

}

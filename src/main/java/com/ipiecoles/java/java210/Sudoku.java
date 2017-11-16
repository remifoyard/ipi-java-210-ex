package com.ipiecoles.java.java210;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Sudoku {

	public static final String FIN_SAISIE = "FIN";
	public static boolean resolu = false;
	public short[][] sudokuAResoudre;
	/**
	 * Constructeur par défaut
	 */
	public Sudoku() {		
		sudokuAResoudre = new short[9][9];
	}
	
	public short [][] getSudokuAResoudre(){
		return this.sudokuAResoudre;
	}
	public void setSudokuAResoudre(short [][] valeur){
            this.sudokuAResoudre = valeur;
        }
	
	public boolean ligneSaisieEstCoherente(String ligneSaisie) {		
		if(ligneSaisie == null || ligneSaisie.trim().equals(""))
		{
		    String message = "Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces";
		    System.out.println(message);
			return false;
		}
		else if(ligneSaisie.length()!=3){
		    String message = "Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères";
		    System.out.println(message);
			return false;
		}
		else if(!ligneSaisie.substring(0,1).matches("[0-8]") || !ligneSaisie.substring(1,2).matches("[0-8]") || !ligneSaisie.substring(2,3).matches("[1-9]")){
		    String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9";
		    System.out.println(message);
			return false;
		}
		else
		{
			return true;			
		}
	}
	
	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour initialiser un sudoku à résoudre.
	 * Les coordonnées prennent la forme XYZ avec X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée après chaque saisie. 
	 * Lorsqu'il a terminé sa saisie, il entre la chaîne FIN. La fonction remplit au fur et à mesure un tableau de String
	 * comportant les coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant la méthode ligneSaisieEstCoherente
	 * En cas de mauvaise saisie, la saisie ne doit pas être prise en compte et l'utilisateur doit pouvoir saisie une nouvelle ligne
	 * La fonction doit également gérer le cas où l'utilisateur ne rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le sudoku à résoudre
	 */
	public String[] demandeCoordonneesSudoku() {
		String[] tableauCoordonnees = new String [81];
		int i=0;
	    Scanner sc = new Scanner(System.in);
		String saisie;
		do
		{
		    System.out.print("Inscrivez X Y et la valeur :");
		    try
		    {
		    	//if(scanner.hasNextLines)
			    saisie = sc.nextLine();
		    }
		    catch (NoSuchElementException error) {
				System.out.println("Aucune valeur de saisie.");
				break;
			} 
		    if(!saisie.equals("FIN"))
		    {
				if(ligneSaisieEstCoherente(saisie)==true)
				{
					tableauCoordonnees[i] = saisie;
				}
				i++;
		    }
		}
		while(!saisie.equals("FIN"));
		sc.close();
		//System.out.println(tableauCoordonnees);
		return tableauCoordonnees;
	}
	
	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec X correspondant 
	 * à l'abscisse, Y l'ordonnée et Z la valeur et remplit le tableau sudokuAResoudre avec les bonnes valeurs
	 * au bon endroit. Ex 012, première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle est 
	 * rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */
	public void remplitSudokuATrous(String[] tableauCoordonnees) {
		for(String element : tableauCoordonnees)
		{
			if(element == null){break;}
			String x = element.substring(0, 1);
			int x2 = stringToInt(x);
			short x3 = (short)x2;
			
			String y = element.substring(1, 2);
			int y2 = stringToInt(y);
			short y3 = (short)y2;
			
			String valeur = element.substring(2, 3);
			int valeur2 = stringToInt(valeur);
			short valeur3 = (short)valeur2;
			this.sudokuAResoudre[x3][y3] = valeur3;
		}
    }
	
	private int stringToInt(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console.
	 * Cela doit ressembler exactement à :
	 * -----------------------
	 * |   8   | 4   2 |   6   |
	 * |   3 4 |       | 9 1   |
	 * | 9 6   |       |   8 4 |
	 *  -----------------------
	 * |       | 2 1 6 |       |
	 * |       |       |       |
	 * |       | 3 5 7 |       |
	 *  -----------------------
	 * | 8 4   |       |   7 5 |
	 * |   2 6 |       | 1 3   |
	 * |   9   | 7   1 |   4   |
	 *  -----------------------
	 * 
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu ou non). 
	 * Ce tableau fait 9 par 9 et contient des chiffres de 0 à 9, 0 correspondant à une valeur 
	 * non trouvée (dans ce cas, le programme affiche un blanc à la place de 0
	 */
	public void ecrireSudoku(short[][] sudoku) {
		String message = " -----------------------\n";
		int f = 0;
		int l = 0;
		int s = 0;
		while(f<3)// ligne de 3 lignes
		{
			if(f==0)
			{
				l = 0;
				s = 3;
			}
			else if(f==1)
			{
				l = 3;
				s = 6;
			}
			else if(f==2)
			{
				l = 6;
				s = 9;
			}
			
			for(int i=l;i<s;i++)
			{
				for(int j=0;j<9;j++)
				{
					if(j==0 || j==3 || j==6)
					{
						message +="| ";
					}
					if(sudoku[i][j] == 0)
					{
					    message +="  ";
					}
					else
					{
					    message +=sudoku[i][j]+" ";
					}
					if(j==8)
					{
						message += "|\n";
					}
				}
			}
			message += " -----------------------\n";
			f++;
		}
	    System.out.print(message);
    }
	
	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes : 
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé
	 * 2 : Si le valeur est déjà dans la colone, le chiffre n'est pas autorisé
	 * 3 : Si la valeur est est déjà dans la boite, le chiffre n'est pas autorisé
	 * 
	 * @param abscisse
	 * @param ordonnee
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	public boolean estAutorise(int abscisse, int ordonnee, short chiffre, short[][] sudoku) {
		int i=0;
		for(i=0;i<9;i++)
		{
			if(sudoku[abscisse][i]==chiffre)
			{
				return false;
			}
		}
		
		int j=0;
		for(j=0;j<9;j++)
		{
			if(sudoku[j][ordonnee]==chiffre)
			{
				return false;
			}
		}
		
		int l = 0;
		int s = 0;
		
		int x_dep = 0;
		int x_fin = 0;
		if(abscisse>=0 && abscisse <3){x_dep=0;x_fin=3;}
		if(abscisse>=3 && abscisse <6){x_dep=3;x_fin=6;}
		if(abscisse>=6 && abscisse <9){x_dep=6;x_fin=9;}
		
		int y_dep = 0;
		int y_fin = 0;
		if(ordonnee>=0 && ordonnee <3){y_dep=0;y_fin=3;}
		if(ordonnee>=3 && ordonnee <6){y_dep=3;y_fin=6;}
		if(ordonnee>=6 && ordonnee <9){y_dep=6;y_fin=9;}
		
		for(l=x_dep;l<x_fin;l++)
		{
			for(s=y_dep;s<y_fin;s++)
			{
				if(sudoku[l][s]==chiffre)
				{
					return false;
				}
			}
		}
		return true;
    }

	public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
	    
	    if (abscisse == 9) {
			//Si on est arrivé au bout de la ligne, on remet l'abscisse à 0
		    abscisse = 0;
		    //Et on augmente l'ordonnée
		    if (++ordonnee == 9) {
		    	//On sort de la méthode si on dépasse la dernière colonne
		    	return true; 
			}
		}
	    
		if(sudoku[abscisse][ordonnee]!=0)
		{
			return resoudre(abscisse+1, ordonnee, sudoku);
		}
		else
		{	
    		for(short k=1;k<=9;k++)
    		{
    			if(this.estAutorise(abscisse, ordonnee, k, sudoku))
    			{
    				sudoku[abscisse][ordonnee]=k;
    				if(resoudre(abscisse+1, ordonnee, sudoku))
					{
    					return true;
    					//int z = 0;
					}
    			}
			}
    		sudoku[abscisse][ordonnee]=0;
    		return false;
		}
    }
}
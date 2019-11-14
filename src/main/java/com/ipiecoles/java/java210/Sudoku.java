package com.ipiecoles.java.java210;

import java.util.Scanner;

public class Sudoku {

	final public static String FIN_SAISIE = "FIN";
	public boolean resolu = false;
	public short[][] sudokuAResoudre;

	public static void main(String[] args) {
		String[] newTab = demandeCoordonneesSudoku();
		System.out.println(newTab[0]);
		short[][] target = new short[9][9];
		estAutorise(8, 8, (short) 4, target);
	}

	/**
	 * Constructeur par défaut
	 */
	public Sudoku() {
		this.sudokuAResoudre = new short[9][9];
	}

	public short[][] getSudokuAResoudre() {
		return this.sudokuAResoudre;
	}

	public void setSudokuAResoudre(short[][] sudoku) {
		this.sudokuAResoudre = sudoku;
	}

	public static boolean ligneSaisieEstCoherente(String ligneSaisie) {
		if (ligneSaisie == null || ligneSaisie.trim().isEmpty()) {
			String message = "Les coordonnées du chiffre et/ou sa valeur ne peuvent pas être nulles, vides ou remplies avec des espaces";
			System.out.println(message);
			return false;
		}
		if (ligneSaisie.length() != 3) {
			String message = "Les coordonnées du chiffre et/ou sa valeur doit faire 3 caractères";
			System.out.println(message);
			return false;
		}
		if (Character.getNumericValue(ligneSaisie.charAt(0)) < 0
				|| Character.getNumericValue(ligneSaisie.charAt(0)) > 8) {
			String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9";
			System.out.println(message);
			return false;
		}
		if (Character.getNumericValue(ligneSaisie.charAt(1)) < 0
				|| Character.getNumericValue(ligneSaisie.charAt(1)) > 8) {
			String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9";
			System.out.println(message);
			return false;
		}
		if (Character.getNumericValue(ligneSaisie.charAt(2)) < 1
				|| Character.getNumericValue(ligneSaisie.charAt(2)) > 9) {
			String message = "L'abscisse et l'ordonnée doivent être compris entre 0 et 8, la valeur entre 1 et 9";
			System.out.println(message);
			return false;
		}

		return true;

	}

	/**
	 * Cette méthode invite l'utilisateur à saisir un ensemble de coordonnées pour
	 * initialiser un sudoku à résoudre. Les coordonnées prennent la forme XYZ avec
	 * X correspondant à l'abscisse, Y l'ordonnée et Z la valeur. Seules les
	 * chiffres présents sont à saisir et l'utilisateur doit appuyer sur entrée
	 * après chaque saisie. Lorsqu'il a terminé sa saisie, il entre la chaîne FIN.
	 * La fonction remplit au fur et à mesure un tableau de String comportant les
	 * coordonnées des chiffres saisis.
	 * 
	 * A noter que pour chaque ligne saisie, sa cohérence est vérifiée en appelant
	 * la méthode ligneSaisieEstCoherente En cas de mauvaise saisie, la saisie ne
	 * doit pas être prise en compte et l'utilisateur doit pouvoir saisie une
	 * nouvelle ligne La fonction doit également gérer le cas où l'utilisateur ne
	 * rentre rien mais appuye sur Entrée
	 *
	 * @return Un tableau comportant les coordonnées des chiffres présents dans le
	 *         sudoku à résoudre
	 */
	public static String[] demandeCoordonneesSudoku() {
		System.out.println("rentrer des valeurs");
		String[] ligneSudoku = new String[81];
		String s;
		int i = 0;
		Scanner scan = new Scanner(System.in);
		do {
			s = scan.next();
			if (ligneSaisieEstCoherente(s)) {
				ligneSudoku[i] = s;
				i++;
			} else {
				// System.out.println("non valide");
			}
		} while (!s.equals(FIN_SAISIE) && i < 81);

		return ligneSudoku;
	}

	/**
	 * La méthode prend un tableau de coordonnées de chiffre soud la forme XYZ avec
	 * X correspondant à l'abscisse, Y l'ordonnée et Z la valeur et remplit le
	 * tableau sudokuAResoudre avec les bonnes valeurs au bon endroit. Ex 012,
	 * première ligne deuxième colonne, on met la valeur 2. Lorsqu'une valeur nulle
	 * est rencontrée dans le tableau, on arrête le traitement
	 * 
	 * Pour passer d'une String à un short, on pourra utiliser la méthode
	 * stringToInt(string)
	 * 
	 * @param tableauCoordonnees
	 */
	public void remplitSudokuATrous(String[] tableauCoordonnees) {
		short[][] nouveauSudoku = new short[9][9];
		for (int i = 0; i < tableauCoordonnees.length; i++) {
			int x = Character.getNumericValue(tableauCoordonnees[i].charAt(0));
			int y = Character.getNumericValue(tableauCoordonnees[i].charAt(1));
			int val = Character.getNumericValue(tableauCoordonnees[i].charAt(2));
			nouveauSudoku[x][y] = (short) val;
		}
		this.setSudokuAResoudre(nouveauSudoku);
	}

	private int stringToInt(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * Cette méthode affiche un sudoku de manière formatée sur la console. Cela doit
	 * ressembler exactement à : ----------------------- | 8 | 4 2 | 6 | | 3 4 | | 9
	 * 1 | | 9 6 | | 8 4 | ----------------------- | | 2 1 6 | | | | | | | | 3 5 7 |
	 * | ----------------------- | 8 4 | | 7 5 | | 2 6 | | 1 3 | | 9 | 7 1 | 4 |
	 * -----------------------
	 * 
	 * @param sudoku tableau de short représentant les valeurs d'un sudoku (résolu
	 *               ou non). Ce tableau fait 9 par 9 et contient des chiffres de 0
	 *               à 9, 0 correspondant à une valeur non trouvée (dans ce cas, le
	 *               programme affiche un blanc à la place de 0
	 */
	public void ecrireSudoku(short[][] sudoku) {
		System.out.print(" ");
		System.out.println("-----------------------");
		for (int i = 0; i < sudoku.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < sudoku[i].length; j++) {
				if (sudoku[i][j] == 0) {
					System.out.print(" ");
				} else {
					System.out.print(sudoku[i][j]);
				}
				System.out.print(" ");
				if ((j + 1) % 3 == 0 && j != 0) {
					if (j == 8) {
						System.out.print("|");
					} else {
						System.out.print("| ");
					}
				}
			}
			System.out.println();
			if ((i + 1) % 3 == 0 && i != 0) {
				System.out.println(" -----------------------");
			}
		}
	}

	/**
	 * Cette méthode vérifie si un chiffre est autorisé à la position d'abscisse et
	 * d'ordonnée donnés dans le sudoku en appliquant les règles suivantes :
	 * 
	 * 1 : Si la valeur est déjà dans la ligne, le chiffre n'est pas autorisé 2 : Si
	 * le valeur est déjà dans la colone, le chiffre n'est pas autorisé 3 : Si la
	 * valeur est est déjà dans la boite, le chiffre n'est pas autorisé
	 * 
	 * @param abscisse
	 * @param ordonnee
	 * @param chiffre
	 * @param sudoku
	 * @return
	 */
	public static boolean estAutorise(int abscisse, int ordonnee, short chiffre, short[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[i][ordonnee] == chiffre) {
				return false;
			}
		}
		for (int i = 0; i < sudoku[abscisse].length; i++) {
			if (sudoku[abscisse][i] == chiffre) {
				return false;
			}
		}
		int boxabscisse = ((abscisse) / 3) * 3;
		int boxordonnee = ((ordonnee) / 3) * 3;
		for (int i = boxabscisse; i < (boxabscisse + 3); i++) {
			for (int j = boxordonnee; j < (boxordonnee + 3); j++) {
				if (sudoku[i][j] == chiffre) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean resoudre(int abscisse, int ordonnee, short[][] sudoku) {
		if (abscisse == 9) {
			return true;
		}

		if (sudoku[abscisse][ordonnee] != 0) {
			if (resoudre(ordonnee == 8 ? (abscisse + 1) : abscisse, (ordonnee == 8) ? 0 : ordonnee + 1, sudoku)) {
				return true;
			}
		} else {
			for (short i = 1; i < 10; i++) {
				if (estAutorise(abscisse, ordonnee, i, sudoku)) {
					sudoku[abscisse][ordonnee] = i;
					if (resoudre(ordonnee == 8 ? (abscisse + 1) : abscisse, (ordonnee == 8) ? 0 : ordonnee + 1,
							sudoku)) {
						return true;
					} else {
						sudoku[abscisse][ordonnee] = 0;
					}
				}
			}
		}
		return false;
	}
}

/*
 * closest one yet if (abscisse==9) { return true; } for (int i = abscisse; i <
 * sudoku.length; i++) { for (int j = ordonnee; j < sudoku[i].length; j++) {
 * if(sudoku[i][j]==0) { for(short k=1; k<10; k++) { if(estAutorise(i, j, k,
 * sudoku)) { sudoku[i][j]=k; if(resoudre((j==8)? i+1:i,0,sudoku)) { return
 * true; }else { sudoku[i][j]=0; return false; } } } } if(resoudre((j==8)?
 * i+1:i,(j==8)?0:j+1,sudoku)) { return true; } } } return false;
 */

/*
 * for (int i = abscisse; i < sudoku.length; i++) { for (int j = ordonnee; j <
 * sudoku[i].length; j++) { if(sudoku[i][j]==0) { for(short k=1; k<10; k++) {
 * if(estAutorise(abscisse, ordonnee, k, sudoku)) { sudoku[i][j]=k;
 * if(resoudre(abscisse, ordonnee, sudoku)) { return true; }else {
 * sudoku[i][j]=0; } } } return false; } } } return true;
 */

/*
 * if (abscisse==9) { return true; }
 * 
 * if (sudoku[abscisse][ordonnee]!=0) { if(resoudre(abscisse == 8? (ordonnee +
 * 1): ordonnee, (abscisse + 1) % 9, sudoku)) { return true; } }else { for(short
 * i=1; i<10; i++) { if(estAutorise(abscisse, ordonnee, i, sudoku)) {
 * sudoku[abscisse][ordonnee]=i; if(resoudre(abscisse == 8? (ordonnee + 1) :
 * ordonnee, (abscisse + 1) % 9, sudoku)) { return true; }else {
 * sudoku[abscisse][ordonnee]=0; } } } } return false;
 */

/*
 * int nbnonrempli=0; for (int i = abscisse; i < sudoku.length; i++) { for (int
 * j = ordonnee; j < sudoku[i].length; j++) { if(sudoku[i][j]==0) { int
 * nbchiffreautorisee=0; short chiffreautorisee=0; for(short k=1; k<10; k++) {
 * if (estAutorise(i, j, k, sudoku)) { nbchiffreautorisee++; chiffreautorisee=k;
 * } } if(nbchiffreautorisee==1) { sudoku[i][j]=chiffreautorisee; }else { if
 * (nbnonrempli==0) { abscisse=i; } nbnonrempli++; } } } } if(nbnonrempli!=0) {
 * return resoudre(abscisse, 0, sudoku); }else { return true; } }
 */

/*
 * if(sudoku[abscisse][ordonnee]==0) { int nbSol=0; short sol=0; for(short i=1
 * ;i<10; i++) { if(estAutorise(abscisse, ordonnee, i, sudoku)) { nbSol++;
 * sol=i; } } if(nbSol==1) { sudoku[abscisse][ordonnee]=sol; }else {
 * 
 * } if(ordonnee==8) { if(abscisse==8) { //return true; return resoudre(0, 0,
 * sudoku); } return resoudre(abscisse+1, 0, sudoku); } return
 * resoudre(abscisse, ordonnee+1, sudoku); }else { if(ordonnee==8) {
 * if(abscisse==8) { //return true; return resoudre(0, 0, sudoku); } return
 * resoudre(abscisse+1, 0, sudoku); } return resoudre(abscisse, ordonnee+1,
 * sudoku); } return true;
 */
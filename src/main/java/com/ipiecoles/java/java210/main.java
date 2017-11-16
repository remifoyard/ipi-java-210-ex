package com.ipiecoles.java.java210;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stubFIN
		Sudoku monSudoku = new Sudoku();		
		monSudoku.remplitSudokuATrous(monSudoku.demandeCoordonneesSudoku());
		monSudoku.ecrireSudoku(monSudoku.getSudokuAResoudre());
		if(monSudoku.resoudre(0, 0, monSudoku.getSudokuAResoudre())){
			monSudoku.ecrireSudoku(monSudoku.getSudokuAResoudre());
		} else {
			System.out.println("Pas de solution...");
		}
		
	}

}

/*********************************************************************** 
  Student Name: Spencer Nguyen
  File Name: BigNumCalculator
  Assignment Number: Project 2
  Course: COP5416

This file inputs a text file of expressions and uses the classes
BigNumCalculator and Stacks to print the results.
************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		
		String txtFile    = "addsAndSubtracts.txt";
		String expression = new String();
		
		File file    = new File(txtFile);
		Scanner scnr = new Scanner (file);

		while(scnr.hasNextLine()) {
			
			expression = scnr.nextLine();
			
			BigNumCalculator t = new BigNumCalculator(expression);
			t.printCalculation();
			System.out.println();
		}
		scnr.close();
	}
}

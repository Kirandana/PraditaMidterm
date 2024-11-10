package uts; // 2410101020 - Kirandana Arkanalla

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Functions fungsi = new Functions();
		Scanner scan = new Scanner(System.in);
		
		fungsi.homeMenu();
		System.out.print("Select an option: ");
		int homeOpt = scan.nextInt();
		
		switch(homeOpt) {
		case 1:
			fungsi.itemMenu();
			break;
		case 2:
			fungsi.pesananMenu();
			break;
		case 3:
			fungsi.quit();
		}
		
		scan.close();
	}
	
}
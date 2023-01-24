package multiApis;

import java.util.Scanner;

public class MainApi {

	public static void main(String[] args) throws Throwable {

		Scanner sc = new Scanner(System.in);
		System.out.println("Select one of the options:");
		System.out.println("1. Insert article");
		System.out.println("2. Insert author");
		System.out.println("3. Insert  section");

		int select = sc.nextInt();

		switch (select) {
		case 1:

			artapi art = new artapi();
			art.mainar();
			break;
		case 2:
			authApi auth = new authApi();
			auth.mainath();
			break;
		case 3:
			SecApi sec = new SecApi();
			sec.mainsec();
			break;

		}

	}

}

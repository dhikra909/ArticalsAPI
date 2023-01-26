package multiApis;

import java.util.Scanner;

public class MainApi {

	public static void main(String[] args) throws Throwable {

		Scanner sc = new Scanner(System.in);
		System.out.println("Select one of the options:");
		System.out.println("1. Insert article");
		System.out.println("2. Insert author");
		System.out.println("3. Insert  section");
		System.out.println("4.select top 5 sections with the most articles");

		int select = sc.nextInt();

		switch (select) {
		case 1:

			Artapi art = new Artapi();
			art.mainar();
			break;
		case 2:
			AuthApi auth = new AuthApi();
			auth.mainath();
			break;
		case 3:
			SecApi sec = new SecApi();
			sec.mainsec();
			break;
		case 4:
			Q top = new Q();
			top.q1();
			break;
		}

	}

}

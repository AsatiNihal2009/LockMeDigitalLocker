package com.simplilearn.lockMe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.simplilearn.lockMe.Entity.Credentials;
import com.simplilearn.lockMe.Entity.User;

public class LockMe {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static PrintWriter fileOutput = null;

	public static void main(String[] args) {
		System.out.println("/****************Welcome TO LockMe*****************/\n\n");
		System.out.println("Press:\n 1 For Registration \n 2 For Login");
		try {
			int input = Integer.parseInt(br.readLine());
			switch (input) {
			case 1:
				userRegistration();
				break;
			case 2:
				Login();
				break;
			default:
				System.out.println("Input Correct Value Defined Above next Time");
			}
		} catch (NumberFormatException | IOException f) {
			System.out.println("Please Enter Correct Values next Time");
		}
	}

	private static void Login() {
		File file = new File("database.txt");
		if (file.exists()) {
			System.out.println("/***Login***/\nEnter Username ");
			try {
				String username = br.readLine();
				System.out.println("Enter Password");
				String password = br.readLine();
				BufferedReader br = new BufferedReader(new FileReader(file));
				String s;
				while ((s = br.readLine()) != null) {
					if (username.matches(s)) {
						if (password.matches(br.readLine())) {
							System.out.println("Login Successful:200 OK");
							storeCredentials();
							br.close();
							return;
						}
					}
				}
				System.out.println("Invalid Attempt");
				br.close();
			} catch (IOException e) {
				System.out.println("File Input Error");
			}
		} else {
			System.out.println("File Not found");
			return;
		}
	}

	private static void storeCredentials() {
		System.out.println("/***Locker***/\nPress:\n 1 Enter Credentials \n 2  Fetch Stored Credentials");
		try {
			int input = Integer.parseInt(br.readLine());
			switch (input) {
			case 1:
				inputCredentials();
				break;
			case 2:
				fetchCredentials();
			default:
				System.out.println("");
				break;
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("Please enter correct values");
			return;
		}
	}

	private static void fetchCredentials() {
		File file = new File("locker-file.txt");
		if (file.exists()) {
			try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}
			br.close();
			} catch (IOException e) {
				System.out.println("Error File Opening");
				return;
			}
		}else {
			System.out.println("File Not found");
			return;
		}
		return;
	}

	private static void inputCredentials() {
		System.out.println("Enter Details into Locker");
		Credentials credential = new Credentials();
		try {
			File file = new File("locker-file.txt");
			if (file.exists()) {
				fileOutput = new PrintWriter(new FileWriter(file));
				System.out.println("Enter site Name");
				credential.setSiteName(br.readLine());
				fileOutput.println(credential.getSiteName());
				System.out.println("Enter Username");
				credential.setUsername(br.readLine());
				fileOutput.println(credential.getUsername());
				System.out.println("Enter Password");
				credential.setPassword(br.readLine());
				fileOutput.println(credential.getPassword());
				System.out.println("Credentials  entered successfully");
				return;
			} else {
				throw new FileNotFoundException("No file exist");
			}
		} catch (IOException e) {
			System.out.println("Error Opening File");
			return;
		} finally {
			fileOutput.close();
		}
	}

	private static void userRegistration() {
		File file = new File("database.txt");
		User u = new User();
		try {
			if (file.exists()) {
				fileOutput = new PrintWriter(new FileWriter(file));
				System.out.println("/***User Registration***/\nEnter Username ");
				u.setUsername(br.readLine());
				System.out.println("Enter Password");
				u.setPassword(br.readLine());
				fileOutput.println(u.getUsername());
				fileOutput.println(u.getPassword());
				System.out.println("Registration Successful");
			} else {
				throw new FileNotFoundException("File Doesnot exist");
			}
		} catch (IOException e) {
			System.out.println("File Input Error");
			return;
		} finally {
			fileOutput.close();
		}
	}
}
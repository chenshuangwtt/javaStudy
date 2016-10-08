package com.cs.test.week7.wordcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MySearch {

	public static int filesContainingMatch = 0;
	public static int matchesFound = 0;
	public static int filesSearched = 0;

	public static ArrayList<String> matchesToFind;
	public static ArrayList<String> matchesToIgnore;

	public static boolean matchesToFindCurrentlyInQuotes = false;
	public static boolean matchesToIgnoreCurrentlyInQuotes = false;

	public static void main(String[] args) {
		File homeDir = null;
		if (homeDir == null) {
			homeDir = new File(System.getProperty("user.dir"));
		}

		matchesToFind = new ArrayList<>();
		matchesToIgnore = new ArrayList<>();
		
		addMatch("System");
		
		System.out.println("Searching for:");
		for (String matches : matchesToFind) {
			System.out.println(" - " + matches);
		}

		System.out.println("Ignoring:");
		for (String matches : matchesToIgnore) {
			System.out.println(" - " + matches);
		}

		searchDir(homeDir);

		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("Searched through " + filesSearched + " files.");
		;
		System.out.println("Found " + matchesFound + " matches in " + filesContainingMatch + " files.");
		System.out.println("---------------------------------------------");
		System.out.println();
	}

	public static void addMatch(String str) {

		if (matchesToFindCurrentlyInQuotes) {
			matchesToFind.set(matchesToFind.size() - 1, matchesToFind.get(matchesToFind.size() - 1) + " " + str);
			if (str.endsWith("\"")) {
				matchesToFindCurrentlyInQuotes = false;
			}
			return;
		}

		if (matchesToIgnoreCurrentlyInQuotes) {
			matchesToIgnore.set(matchesToIgnore.size() - 1,
					matchesToIgnore.get(matchesToIgnore.size() - 1) + " " + str);
			if (str.endsWith("\"")) {
				matchesToIgnoreCurrentlyInQuotes = false;
			}
			return;
		}

		if (str.startsWith("--") && !str.equals("--") && !matchesToIgnore.contains(str.toLowerCase().substring(2))) {
			matchesToIgnore.add(str.toLowerCase().substring(2));
			if (str.startsWith("\"") && !str.endsWith("\"")) {
				matchesToIgnoreCurrentlyInQuotes = true;
			}
		} else if (!matchesToFind.contains(str.toLowerCase())) {
			matchesToFind.add(str.toLowerCase());
			if (str.startsWith("\"") && !str.endsWith("\"")) {
				matchesToFindCurrentlyInQuotes = true;
			}
		}
	}

	public static void searchDir(File f) {
		if (!f.isDirectory()) {
			searchFile(f);
			return;
		}

		for (File file : f.listFiles()) {
			if (file.isDirectory()) {
				searchDir(file);
			} else {
				searchFile(file);
			}
		}

	}

	public static void searchFile(File f) {
		try {
			Scanner s = new Scanner(f);

			int currentLine = 1;

			boolean hasFoundMatchInFile = false;

			if (searchText(f.getName())) {
				System.out.println();
				System.out.println("Found match in " + f.getAbsolutePath());
				System.out.println(" - Filename: " + f.getName());
				hasFoundMatchInFile = true;
				matchesFound++;
			}

			while (s.hasNext()) {
				String str = s.nextLine();
				if (searchText(str)) {
					if (!hasFoundMatchInFile) {
						System.out.println();
						System.out.println("Found match in " + f.getAbsolutePath());
						hasFoundMatchInFile = true;
					}
					System.out.println(" - Line " + currentLine + ": " + str);
					matchesFound++;
				}
				currentLine++;
			}

			if (hasFoundMatchInFile) {
				filesContainingMatch++;
			}

			filesSearched++;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static boolean searchText(String text) {
		for (String match : matchesToIgnore) {
			if (text.toLowerCase().contains(match)) {
				return false;
			}
		}

		for (String match : matchesToFind) {
			if (text.toLowerCase().contains(match)) {
				return true;
			}
		}
		return false;
	}

}
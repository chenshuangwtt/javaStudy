package com.cs.test.week7.wordcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static int filesContainingMatch = 0;
	public static int matchesFound = 0;
	public static int filesSearched = 0;

	public static ArrayList<String> matchesToFind;
	public static ArrayList<String> matchesToIgnore;

	public static boolean matchesToFindCurrentlyInQuotes = false;
	public static boolean matchesToIgnoreCurrentlyInQuotes = false;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java -jar FCS.jar <string>     OR \n"
					+ "java -jar FCS.jar -dir:<dir> <string> OR \n" + "java -jar FCS.jar ---help");
			return;
		}

		if (args[0].equalsIgnoreCase("---help")) {
			System.out.println(
					"This tool searches through all text files within a given directory for given matches. It goes though all");
			System.out.println(
					"directories in the given directory as well. To use this, command-line arguments must be set. If you want to");
			System.out.println(
					"search in the current working directory, simply list only the matches you're searching for. If you want to");
			System.out.println(
					"search in a different directory, the first argument must start with \"-dir:\" with the path following. If the");
			System.out.println(
					"path has spaces in it, precede each space with a backslash (\\). After that, list the matches. To show help,");
			System.out.println("the first argument must be \"---help\".");
			System.out.println("");
			System.out.println(
					"This tool is non-case sensitive, meaning that it will ignore the cases of searches. So if a text file contains");
			System.out.println(
					"\"Hello!\" and the match given is \"hello\", then \"Hello!\" will be listed. If a desired match contains several");
			System.out.println(
					"words, surround these with double quotation marks (\"). For example, to search for \"good bye\", the command would");
			System.out.println("look like this:");
			System.out.println("");
			System.out.println("java -jar FCS.jar -dir:<dir> \"good bye\"");
			System.out.println("");
			System.out.println(
					"If you want to ignore some text, precede the text with two dashes (--). Any line containing this in the given");
			System.out.println(
					"text files will not be listed in the output. In addition, quotation marks can also be used if the match to");
			System.out.println(
					"ignore has more than one word. For example, to list any line that contains \"Hello\" but not \"Hello World!\", the");
			System.out.println("command would look like this:");
			System.out.println("");
			System.out.println("java -jar FCS.jar -dir:<dir> Hello --\"Hello World!\"");
			return;
		}

		File homeDir = null;

		if (args.length > 1) {
			if (args[0].toLowerCase().startsWith("-dir:")) {
				String dirStr = args[0].substring(5);
				homeDir = new File(dirStr);
				System.out.println("Searching through " + dirStr);
				if (!homeDir.exists()) {
					System.out.println("Error: File not found.");
					return;
				}
			}
		}

		if (homeDir == null) {
			homeDir = new File(System.getProperty("user.dir"));
		}

		matchesToFind = new ArrayList<>();
		matchesToIgnore = new ArrayList<>();

		for (String matchToFind : args) {
			if (!(matchToFind.equals(args[0]) && matchToFind.toLowerCase().startsWith("-dir:"))) {
				addMatch(matchToFind);
			}
		}

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
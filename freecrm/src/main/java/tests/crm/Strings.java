package tests.crm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Strings {

	/*public static void main(String[] args) {
		/*
		 * regex(); splitString(); RemoveDuplicateCharacters(); ConcatString();
		 
		MakeCapitalFirstWordInLine();
	}*/
@Test
	public static void regex() {

		String input = "The the string String string stringing.";

		String regex = "\\b(\\w+)(\\s+\\1\\b)+";

		// Use compile(regex) if you want case sensitive.
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(input);
		while (m.find()) {
			input = input.replaceAll(m.group(), m.group(1));
		}

		System.out.println(input);
	}

	public static void splitString() {
		String str = "welcome to Automation software testing";
		String[] str1 = str.split(" ");
		for (String val : str1) {
			System.out.println(val);
		}
	}

	public static void RemoveDuplicateCharacters() {
		String s = "softwaretesting";
		String s2 = "";
		for (int i = 0; i < s.length(); i++) {
			Boolean found = false;
			for (int j = 0; j < s2.length(); j++) {
				if (s.charAt(i) == s2.charAt(j)) {
					found = true;
					break;
				}
			}
			if (found == false) {
				s2 = s2.concat(String.valueOf(s.charAt(i)));
			}
		}
		System.out.println(s2);
	}

	public static void ConcatString() {
		String str = "hello!";
		if (str.contains("how")) {
			String str1 = str.concat("how are you?");
			System.out.println(str1);
		} else {
			System.out.println("String doesn't contains");
		}
	}

	public static void MakeCapitalFirstWordInLine() {

		// create object of scanner class.
		Scanner in = new Scanner(System.in);

		// enter sentence here
		System.out.print("Enter sentence here : ");
		String line = in.nextLine();
		String upper_case_line = "";

		// this is for the new line which is generated after conversion.
		Scanner lineScan = new Scanner(line);
		while (lineScan.hasNext()) {
			String word = lineScan.next();
			upper_case_line += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
		}

		// print original line with output.
		System.out.println("Original sentence is : " + line);
		System.out.println("Sentence after convert : " + upper_case_line.trim());
	}

	public static void ReverseByWord() {

		{
			// create object of the string.
			String S;

			Scanner scan = new Scanner(System.in);

			// enter your string here.
			System.out.print("Enter the string : ");
			// will read string and store it in "S" for further process.
			S = scan.nextLine();

			StringTokenizer st = new StringTokenizer(S, " ");

			// strReverseLine is the function used to reverse a string.
			String strReversedLine = "";
			try {
				while (st.hasMoreTokens()) {
					strReversedLine = st.nextToken() + " " + strReversedLine;
				}
				System.out.println("Reversed string by word is : " + strReversedLine);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
		public static void CountTokens()
		{
			//create StringTokenizer object
			String S;
			Scanner scan = new Scanner (System.in);

			// enter your string here.
			System.out.print("Enter the string : ");

			// will read string and store it in "S" for further process.
			S = scan.nextLine();
			StringTokenizer st = new StringTokenizer(S, " ");

			// search for token while the string ends.
			while(st.hasMoreTokens())
			{
				// print all the tokens.
				System.out.println("Remaining are : " + st.countTokens());
				System.out.println(st.nextToken());
			}
		}
		
	}


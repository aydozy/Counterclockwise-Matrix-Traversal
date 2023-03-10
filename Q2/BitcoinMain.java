package Q2;
//-----------------------------------------------------
// Title: Main Class
// Author: Ayda Nil Özyürek
// Description: This class does the comparison between numbers 
//              and prints them.
//------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BitcoinMain {

	public static void main(String[] args) {

		ArrayList<Integer> result = new ArrayList<Integer>();
		CustomStack numbers = new CustomStack();
		CustomStack numbersTmp = new CustomStack();
		CustomStack prices = new CustomStack();
		CustomStack pricesTmp = new CustomStack();

		BufferedReader reader;
		
		//Reads the file line by line and separates the numbers where it sees " ".
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Input filename:");
			String fileName = scanner.nextLine();
			reader = new BufferedReader(new FileReader(fileName));

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);

				String[] items = line.split(" ");
				for (int i = 0; i < items.length; i++) {
					prices.push(Integer.parseInt(items[i]));
				}

				line = reader.readLine();
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		pricesTmp = pricesTmp.addAll(prices); //addAll was used to not copy the reference.

		int counter = 0;

		while (!pricesTmp.isEmpty()) {
			int element = pricesTmp.pop();

			if (counter == 0) {
				result.add(1);
				counter++;
				continue;
			}

			
			// -------------------------------------------------
			// This is the part I call the "model list".
			// For example, let's consider price2.txt and the element value is 70. The model
			// list gives a value of 1 for those greater than 70 and -1 for those less than
			// 70 when comparing 70 with its own and its predecessors. So 70 would have
			// [1,-1,1] model list.
			// -------------------------------------------------
			
			for (int i = counter; i >= 0; i--) {
				int compareElement = prices.getObject(i);

				if (element <= compareElement) {
					numbers.push(1);
				} else {
					numbers.push(-1);
				}
			}
			
			//-------------------------------------------------
			// Here a variable named total is kept. This total gets us to the result we need
			// to find, according to the 1s and -1s in the model list.
			// -------------------------------------------------


			numbersTmp = numbersTmp.addAll(numbers);
			int total = 0;
			for (int i = 0; i < numbers.size(); i++) {
				int number = numbersTmp.pop();
				if (number == -1 && numbersTmp.contains(1)) {
					if (total > 0) {
						total -= 1;
					}
				}
				if (number == 1) {
					total += 1;
				}
			}

			result.add(total);
			numbers.clear(); //I reset my model list every time so that the numbers don't get mixed up.
			numbersTmp.clear();
			counter++;
		}

		System.out.println(result);
	}

}

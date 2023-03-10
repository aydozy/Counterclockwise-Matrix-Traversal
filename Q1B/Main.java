package Q1B;
//-----------------------------------------------------
// Title: Main Class
// Author: Ayda Nil Özyürek
// Description: This class checks whether the indexes in the given lists are adjacent to each other.
//------------------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		BufferedReader reader;
		int[][] matrix;
		ArrayList<Integer> matrixTmp = new ArrayList<Integer>();
		ArrayList<String> firstListTmp = new ArrayList<String>();
		ArrayList<String> secondListTmp = new ArrayList<String>();

		int rows = 0;
		String[] matrixItems = null;
		String[] firstListItems = null;
		String[] secondListItems = null;

		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Input filename:");
			String matrixFileName = scanner.nextLine();
			// Since the file is read as "matrixb.txt" in VPL, I have used substring to read
			// matrixb.txt.
			reader = new BufferedReader(new FileReader(matrixFileName.substring(1, matrixFileName.length() - 1)));

			String matrixLine = reader.readLine();

			while (matrixLine != null) {
				matrixItems = matrixLine.split(" ");

				for (int i = 0; i < matrixItems.length; i++) {
					matrixTmp.add(Integer.parseInt(matrixItems[i]));
				}

				matrixLine = reader.readLine();
				rows++;
			}

			System.out.print("Input filename:");
			String firstListFileName = scanner.nextLine();
			reader = new BufferedReader(new FileReader(firstListFileName.substring(1, firstListFileName.length()-1)));

			String firstListLine = reader.readLine();

			while (firstListLine != null) {
				firstListItems = firstListLine.split("-");

				for (int i = 0; i < firstListItems.length; i++) {
					if (!firstListItems[i].equals("")) {
						firstListTmp.add(firstListItems[i]);
					}
				}

				firstListLine = reader.readLine();
			}

			System.out.print("Input filename:");
			String secondListFileName = scanner.nextLine();
			reader = new BufferedReader(new FileReader(secondListFileName.substring(1, secondListFileName.length()-1)));

			String secondListLine = reader.readLine();

			while (secondListLine != null) {
				secondListItems = secondListLine.split("-");

				for (int i = 0; i < secondListItems.length; i++) {
					secondListTmp.add(secondListItems[i]);
				}

				secondListLine = reader.readLine();
			}

		} catch (Exception e) {
			System.err.println(e);
		}

		matrix = new int[rows][matrixItems.length];

		int column = 0;
		int row = 0;

		for (int i = 0; i < matrixItems.length * rows; i++) {

			matrix[row][column] = matrixTmp.get(i);
			column++;

			if (column == matrixItems.length) {
				row++;
				column = 0;
			}
		}

		LinkedListClass matrixb = new LinkedListClass();

		int top = 0;
		int bottom = rows - 1;
		int left = 0;
		int right = matrixItems.length - 1;

		int direction = 1;
		boolean stop = false;
		while (top <= bottom && left <= right) {
			if (direction == 1) {
				for (int i = top; i <= bottom; i++) {
					if (matrix[i][left] != -1) {
						matrixb.addToEnd(matrix[i][left]);
					} else {
						stop = true;
						break;
					}
				}

				left++;
				direction = 2;
			} else if (direction == 2) {
				for (int i = left; i <= right; i++) {
					if (matrix[bottom][i] != -1) {
						matrixb.addToEnd(matrix[bottom][i]);
					} else {
						stop = true;
						break;
					}
				}

				bottom--;
				direction = 3;
			} else if (direction == 3) {
				for (int i = bottom; i >= top; i--) {
					if (matrix[i][right] != -1) {
						matrixb.addToEnd(matrix[i][right]);
					} else {
						stop = true;
						break;
					}
				}

				right--;
				direction = 4;
			} else if (direction == 4) {
				for (int i = right; i >= left; i--) {
					if (matrix[top][i] != -1) {
						matrixb.addToEnd(matrix[top][i]);
					} else {
						stop = true;
						break;
					}
				}

				top++;
				direction = 1;
			}

			if (stop) {
				break;
			}
		}
		
		//finds the matrix equivalent of the indexes given in the list
		LinkedListClass firstList = new LinkedListClass();
		for (int i = 0; i < firstListTmp.size(); i++) {
			String[] indexes = firstListTmp.get(i).split(",");
			firstList.addToEnd(matrix[Integer.parseInt(indexes[0])][Integer.parseInt(indexes[1])]);
		}
		
		//finds the matrix equivalent of the indexes given in the list
		LinkedListClass secondList = new LinkedListClass();
		for (int i = 0; i < secondListTmp.size(); i++) {
			String[] indexes = secondListTmp.get(i).split(",");
			secondList.addToEnd(matrix[Integer.parseInt(indexes[0])][Integer.parseInt(indexes[1])]);
		}

		
		//The part that compares whether the found values are neighbors or not
		Node lastItemOfFirstList = firstList.getObject(firstList.size() - 1);
		int indexAtMatrix = matrixb.indexOf(lastItemOfFirstList);
		Node firstItemOfSecondList = secondList.getHead();
		//The part that returns "true" if they are neighbors and "false" if they are not
		if (matrixb.getObject(indexAtMatrix + 1).getData() == firstItemOfSecondList.getData()) {
			System.out.println("TRUE");
			return;
		} else {
			System.out.println("FALSE");
			return;
		}

	}
}

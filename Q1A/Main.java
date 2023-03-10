package Q1A;
//-----------------------------------------------------
// Title: Main Class
// Author: Ayda Nil Özyürek
// Description: This class traverses the given matrix counterclockwise 
//              and prints it.
//------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader reader;
		int[][] matrix;
		ArrayList<Integer> matrixTmp = new ArrayList<Integer>();

		int rows = 0;
		String[] items = null;

		// Reads the file line by line
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Input filename:");
			String fileName = scanner.nextLine();
			reader = new BufferedReader(new FileReader(fileName));

			String line = reader.readLine();

			// turn the given matrix into a list
			while (line != null) {
				items = line.split(" ");

				for (int i = 0; i < items.length; i++) {
					matrixTmp.add(Integer.parseInt(items[i]));
				}

				line = reader.readLine();
				rows++;
			}

		} catch (Exception e) {
			System.err.println(e);
		}

		matrix = new int[rows][items.length];

		int row = 0;
		int column = 0;

		// The matrix converted to a list is converted back to a matrix. The purpose of
		// this operation is to not know the number of columns and rows of the matrix
		// entered at the beginning.

		for (int i = 0; i < items.length * rows; i++) {

			matrix[row][column] = matrixTmp.get(i);
			column++;

			if (column == items.length) {
				row++;
				column = 0;
			}
		}

		LinkedListClass result = new LinkedListClass();

		int top = 0;
		int bottom = rows - 1;
		int left = 0;
		int right = items.length - 1;

		// -----------------------------------------
		// I have determined 4 directions here.
		// top to bottom -> direction 1
		// left to right -> direciton 2
		// bottom to top -> direction 3
		// right to left -> direction 4
		// These directions indicate in which order the matrix will move.
		//
		// Each direction can advance until it sees -1. If it doesn't see -1, it moves
		// forward until it finishes the row or column.
		// -----------------------------------------

		int direction = 1;
		boolean stop = false;
		while (top <= bottom && left <= right) {
			if (direction == 1) {
				for (int i = top; i <= bottom; i++) {
					if (matrix[i][left] != -1) {
						result.addToEnd(matrix[i][left]);
					} else {
						stop = true;
						break;
					}
				}

				left++; // Increased left to avoid processing the number at the end of the row from left
						// to right when moving from top to bottom.
				direction = 2;
			} else if (direction == 2) {
				for (int i = left; i <= right; i++) {
					if (matrix[bottom][i] != -1) {
						result.addToEnd(matrix[bottom][i]);
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
						result.addToEnd(matrix[i][right]);
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
						result.addToEnd(matrix[top][i]);
					} else {
						stop = true;
						break;
					}
				}

				top++;
				direction = 1;
			}

			// Process that completely stops traversing
			if (stop) {
				break;
			}
		}
		result.printList();

	}

}

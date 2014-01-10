import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader<E> {

	// reading file
	public static ArrayList firstWord = new ArrayList();
	public static ArrayList secondWord = new ArrayList();
	public static ArrayList thirdWord = new ArrayList();
	public static int counter = 0;
	public static int length = 0;
	public static int[] order = { 0, 1, 2 };

	// Generating order
	static double randomValue1;
	static double randomValue2;
	static int temp;
	static int[] a;

	public static void readWords() {
		File nF = new File("info.txt");
		firstWord.clear();
		secondWord.clear();
		thirdWord.clear();

		try {
			Scanner s = new Scanner(nF);
			s.useDelimiter(",");
			while (s.hasNext()) {
				if (counter == 0) {
					String line = s.next();
					firstWord.add(line);
					counter++;
				} else if (counter == 1) {
					String line = s.next();
					secondWord.add(line);
					counter++;
				} else {
					String line = s.next();
					thirdWord.add(line);
					counter = 0;
				}

				length++;

			}
			s.close();

		} catch (Exception e) {
			System.out.print(e);
		}
		length = firstWord.size();

		// The file is read, create order and shuffle it!
		createInitialOrder();
		shuffleOrder();

		for (int i = 0; i < firstWord.size(); i++) {
			// System.out.print(a[i] + ", ");
		}

	}

	private static void createInitialOrder() {
		a = new int[length];
		for (int i = 0; i < length; i++) {
			a[i] = i;
		}
	}

	public static void shuffleOrder() {
		for (int shuffles = 0; shuffles < a.length; shuffles++) {
			randomValue1 = Math.random();
			randomValue2 = Math.random();
			temp = a[(int) (randomValue1 * length)];
			a[(int) (randomValue1 * length)] = a[(int) (randomValue2 * length)];
			a[(int) (randomValue2 * length)] = temp;
		}

	}
}

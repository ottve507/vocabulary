import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;

public class Writer {

	public static boolean writeToFile(String[] w) {
		try {
			FileWriter fW = new FileWriter("info.txt", true);
			BufferedWriter bW = new BufferedWriter(fW);

			bW.write(",");
			bW.newLine();
			for (int i = 0; i < w.length; i++) {
				bW.write(w[i]);
				if (i != w.length - 1) {
					bW.write(",");
				}
			}

			bW.close();
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
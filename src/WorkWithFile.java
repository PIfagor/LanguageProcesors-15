import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class WorkWithFile {

	/**
	 * @param args
	 * @author Wise Variant 15 Написати програми, що зчитує текст із файлу,
	 *         знаходить найдовше слово і визначає, скільки разів воно
	 *         зустрічається в тексті
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String str = "ass re e1wrq 234 ersdf sdf sfsdf dsf  e11s !sd";
		
		InputStream is = new FileInputStream("res/HP-4.txt");
		Scanner sc = new Scanner(is);
		String maxLength = "";
		int count = 0;
		while (sc.hasNext()) {
			String ss = sc.next();
			if (!ss.matches(".*(\\W).*") && !ss.matches(".*(\\d).*")) {
				//System.out.println(ss);
				if (maxLength.length() < ss.length()) {

					maxLength = ss;
					count = 0;
				}

				if (maxLength.length() == ss.length())
					if (maxLength.equals(ss))
						count++;
			}
		}
		if (maxLength.equals("")) {
			System.out.println("Text does not contain a wor");
		} else {
			System.out.println("World with max length is: " + maxLength
					+ " -happens: " + count + " -times");
		}
		
		is.close();
	}
}

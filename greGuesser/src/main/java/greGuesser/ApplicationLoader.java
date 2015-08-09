package greGuesser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

public class ApplicationLoader {

	private static final String fileName = "greWordList.txt";

	private static Properties loadProperties() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		InputStream input = null;

		input = new FileInputStream("./resources/greWordList.properties");

		// load a properties file
		prop.load(input);
		return prop;
	}

	private static void createPropertiesFileFromWordList() throws IOException {
		FileWriter fw;
		BufferedWriter bw;
		FileReader fr;
		BufferedReader br;
		String s;
		StringTokenizer strtok;
		StringBuilder sb = new StringBuilder();
		try {
			File file = new File(fileName);
			if (file.length() > 1 == false)
				throw new RuntimeException("File empty");
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);

				while ((s = br.readLine()) != null) {
					strtok = new StringTokenizer(s, " ");
					int i = 0;
					while (strtok.hasMoreTokens()) {
						String greWord = strtok.nextToken();
						if (i == 0) {
							sb.append(greWord + " ");
							sb.append(" = ");
						} else {
							sb.append(" " + greWord + " ");
						}
						i++;
					}
					sb.append("\n");
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("File was not found!");
			} catch (IOException e) {
				System.out.println("No file found!");
			}

			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error1!");
		} catch (IOException e) {
			System.out.println("Error2!");
		}
		FileWriter propWriter = new FileWriter(new File("greWordList.properties"));
		propWriter.write(sb.toString());
		propWriter.close();
	}

}

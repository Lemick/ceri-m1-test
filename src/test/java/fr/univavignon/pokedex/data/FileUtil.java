package fr.univavignon.pokedex.data;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public final class FileUtil {
	
	  public static String getFileContent(String fileName) {

			StringBuilder result = new StringBuilder("");

			//Get file from resources folder
			ClassLoader classLoader = FileUtil.class.getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());

			try (Scanner scanner = new Scanner(file)) {

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
				}

				scanner.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return result.toString();

		  }
	  
	  public static File getFile(String fileName) {

			ClassLoader classLoader = FileUtil.class.getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());

			return file;
	  }
	  
	  
	  
	  
}

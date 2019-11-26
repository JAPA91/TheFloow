package com.loadxml;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadXMLFile {
	public static String readXMLFile(String file) throws Exception {
		// Declare BufferedReader to read file content
		BufferedReader reader = new BufferedReader(new FileReader(file));
		// Create a line
		String line = null;
		// Declare StringBuilder to save content that was read from
		// BufferedReader
		StringBuilder stringBuilder = new StringBuilder();
		// Define type of present
		String ls = System.getProperty("line.separator");
		try {
			// While reader find a line that is not null, then append it
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
		} finally {
			// TODO: handle finally clause
			// Finally, close the BufferedReader
			reader.close();
		}
		return stringBuilder.toString();
	}

}

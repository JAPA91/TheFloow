package com.parsexmlTojson;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.loadxml.ReadXMLFile;

public class ConvertToJson {
	// The path to location of Data.xml
	public static String PATH = "D:/Priyanka/new.xml";

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static String JsonConversion() throws Exception, JSONException {

		// Read XML file content and save as String
		String textXML = ReadXMLFile.readXMLFile(PATH);
		// Declare a JsonObject and parse textXML to JsonObject
		JSONObject xmlJSONOBJECT = XML.toJSONObject(textXML);
		// Parse JsonObject to String for printing
		String jsonPrettyString = xmlJSONOBJECT.toString(PRETTY_PRINT_INDENT_FACTOR);
		System.out.println(jsonPrettyString);
		PrintWriter out1 = null;
		try {
			out1 = new PrintWriter(new FileWriter("D:\\Priyanka\\json.txt"));
			out1.write(jsonPrettyString);
		} catch (Exception ex) {
			System.out.println("error: " + ex.toString());
		}

		return jsonPrettyString;
	}
}

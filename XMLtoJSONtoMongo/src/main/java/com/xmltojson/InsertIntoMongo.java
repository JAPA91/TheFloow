package com.xmltojson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.bson.Document;
import org.json.JSONException;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.parsexmlTojson.ConvertToJson;

public class InsertIntoMongo {

	public static final int DEFAULT_IMPORT_SKIP = 10;
	public static final int DEFAULT_IMPORT_LIMIT = 1000000;// TEMP 0;
	public static final int DEFAULT_CHUNK_SIZE = 100000;
	public static final String PARAM_ID = "id";

	public static void main(String[] args) throws JSONException, Exception {

		try {
			MongoClient mongo = new MongoClient("localhost", 27017);

			MongoDatabase db = mongo.getDatabase("mydb");

			MongoCollection<Document> collection = db.getCollection("xmltojson");

			// Converting the xml to json and Getting the json in the pretty text format
			String sjson = ConvertToJson.JsonConversion();

			String id = null;
			// Importing the source and word count to mongodb
			Importer imp = new Importer(sjson, id, collection);

			try (BufferedReader r = new BufferedReader(
					new InputStreamReader(new FileInputStream("D:\\Priyanka\\json.txt"), "UTF-8"))) {
				long startTs = System.currentTimeMillis();
				imp.cleanData();
				imp.importInChunks(r, 0, 1000000, 100000);
				long endTs = System.currentTimeMillis();
				System.out.println(String.format("Imported data in %d seconds.", (endTs - startTs) / 1000));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}

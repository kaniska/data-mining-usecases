/**
 * 
 */
package com.mining.stocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import com.thoughtworks.xstream.XStream;

/**
 * @author Kaniska_Mandal
 * 
 */
public class StockCrawler {

	public StockCrawler() {
		// TODO Auto-generated constructor stub
	}
	
	private static List<String> symbolsList = new ArrayList<String>(1);
	
	public static void loadStockSymbols() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("resource/symbols.txt"));
		scanner.useDelimiter("\r\n");
		while (scanner.hasNext()) {
			symbolsList.add(scanner.next());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Perform Search ....");
		System.out.println("-------------------");
		try {			
			if(symbolsList.isEmpty()) {
				loadStockSymbols();
			}			
			for (String symbol : symbolsList) {
				getStockInfo(symbol);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param symbol 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static void getStockInfo(String symbol) throws ClientProtocolException,
			IOException {
		
		String url = "http://dev.markitondemand.com/Api/Quote?";
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("symbol", symbol));
		
		Content content = Request.Post(url).bodyForm(urlParameters)
				.execute().returnContent();

		XStream xstream = new XStream();
		xstream.processAnnotations(QuoteApiModel.class);
		xstream.processAnnotations(QuoteData.class);
		
		String response = content.asString();
		if(!response.contains("Error")) {
			Object obj = xstream.fromXML(content.asString());
			System.out.println(xstream.toXML(obj));	
		}
	}

}

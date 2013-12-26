/**
 * 
 */
package com.mining.places;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import net.sf.sprockets.Sprockets;
import net.sf.sprockets.google.Place;
import net.sf.sprockets.google.Place.Review;
import net.sf.sprockets.google.Places;
import net.sf.sprockets.google.Places.Params;
import net.sf.sprockets.google.Places.Params.RankBy;
import net.sf.sprockets.google.Places.Response;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

/**
 * @author Kaniska_Mandal
 * 
 */
public class PlacesCrawler {

	/**
	 * 
	 */
	public PlacesCrawler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// my address : lat 37.364763 , long : -122.024266
		// 40.758897, -73.985126

		String address1 = "1058 Shell Blvd, Foster City, CA, USA, 94404";

		String address2 = "Karunamoyee Housing Estate, Salt lake City, West Bengal, India, 700091";

		try {
			Sprockets.getConfig().setProperty("google.api-key",
					"AIzaSyDLrSVso9CDHnmGC_3xDp4X2ObBtYffzaA");

			final Geocoder geocoder = new Geocoder();
			GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
					.setAddress(address1).setLanguage("en")
					.getGeocoderRequest();
			GeocodeResponse geocoderResponse = geocoder
					.geocode(geocoderRequest);

			BigDecimal lat = null;

			BigDecimal lon = null;

			for (GeocoderResult result : geocoderResponse.getResults()) {

				lat = result.getGeometry().getLocation().getLat();

				lon = result.getGeometry().getLocation().getLng();

				break;
			}

			Response<List<Place>> resp = Places.nearbySearch(new Params()
					.location(Double.parseDouble(lat.toString()),
							Double.parseDouble(lon.toString()))
					.keyword("*").radius(2500)
					.maxResults(100));

			TreeSet<Map.Entry<String, Float>> sortedSet = new TreeSet<Map.Entry<String, Float>>(
					new Comparator<Map.Entry<String, Float>>() {
						@Override
						public int compare(Map.Entry<String, Float> e1,
								Map.Entry<String, Float> e2) {
							if (e1.getValue().equals(e2.getValue())) {
								return 1;
							}
							return -(e1.getValue().compareTo(e2.getValue()));
						}
					});

			SortedMap<String, Float> placeRatingMap = new TreeMap<String, Float>();

			/////////////////
			
			
			for (Place place : resp.getResult()) {
				placeRatingMap.put(place.getName(), place.getRating());
				List<String> placeTypes = place.getTypes();
				for (String placeType : placeTypes) {
					System.out.println("#### Place : " + place.getName()							
							+ " :: "+place.getVicinity()
							+ " :: " + placeType + " :: " + place.getRating());
					
					//for (Review review : place.getReviews()) {
						//System.out.println(" ----> " + review.getText());
					//}
				}
			}
	     
			
			///////////
			
			
			sortedSet.addAll(placeRatingMap.entrySet());
			
			for (Iterator iterator = sortedSet.iterator(); iterator.hasNext();) {
				Entry<String, Float> entry = (Entry<String, Float>) iterator
						.next();
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			System.out.println("////////////////////////////////");
			
			/*
			Response<List<Place>> resp2 = Places.radarSearch(new Params()
					.location(37.365871, -122.023651).keyword("burger"));
			for (Place place : resp2.getResult()) {
			}
			*/
			System.out.println("////////////////////////////////");

/*			Response<List<Place>> resp3 = Places.textSearch(new Params()
					.query("Best Crab Restaurant in San Francisco"));
			for (Place place : resp3.getResult()) {
				System.out.println( place.getId() + ":" + place.getName()
						+ ":" + place.getRating() + ":" + place.getReviews()
						+ ":" + place.getLatitude());

			}*/
			System.out.println("////////////////////////////////");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

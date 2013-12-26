package com.mining.linkedin;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.google.code.linkedinapi.client.CompaniesApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProductField;
import com.google.code.linkedinapi.client.enumeration.SearchParameter;
import com.google.code.linkedinapi.schema.Companies;
import com.google.code.linkedinapi.schema.Company;
import com.google.code.linkedinapi.schema.Product;
import com.google.code.linkedinapi.schema.Products;
import com.google.code.linkedinapi.schema.SchemaEntity;

public class LinkedinSearch {

	public static void searchCompanyInfo(LinkedInApiClient client,
			HashMap<Long, SchemaEntity> resultset, String strQuery) {

		Map<SearchParameter, String> searchParameters = new EnumMap<SearchParameter, String>(
				SearchParameter.class);

		// Company company = client.getCompanyByUniversalName(strQuery,
		// EnumSet.allOf(CompanyField.class));

		searchParameters.put(SearchParameter.KEYWORDS, strQuery);
		Companies companyList = client.searchCompanies(searchParameters);
		traverseCompanies(companyList, client, strQuery);

		for (Company company : companyList.getCompanyList()) {
			Products companyProducts = client.getCompanyProducts(
					company.getId(), EnumSet.allOf(ProductField.class));
			for (Product product : companyProducts.getProductList()) {
				// printResult(product);
				resultset.put((long) product.getId().hashCode(), product);
			}
		}
	}

	private static void traverseCompanies(Companies companies,
			CompaniesApiClient client, String qryStr) {
		for (Company company : companies.getCompanyList()) {

			if (company.getName().equalsIgnoreCase(qryStr)) {
				Products companyProducts = client.getCompanyProducts(
						company.getId(), EnumSet.allOf(ProductField.class));
				for (Product product : companyProducts.getProductList()) {
					printResult(product);
				}
			}
		}
	}

	private static void traverseCompanies(Companies companies,
			CompaniesApiClient client) {
		for (Company company : companies.getCompanyList()) {
			System.out.println(" Company Name : "+company.getName());
			/*Products companyProducts = client.getCompanyProducts(
					company.getId(), EnumSet.allOf(ProductField.class));
			for (Product product : companyProducts.getProductList()) {
				printResult(product);
			}*/
		}
	}

	private static void printResult(Product product) {
		System.out.println("================================");
		System.out.println("Name:" + product.getName());
		System.out.println("Type:" + product.getType().getName());
		System.out.println("Timestamp:" + product.getCreationTimestamp());
		System.out.println("Description:" + product.getDescription());
		System.out.println("Features:");
		for (String featureName : product.getFeatures().getFeatureList()) {
			System.out.println("      " + featureName);
		}
		System.out.println("Recommendation count:"
				+ product.getNumRecommendations());
		System.out.println("URL:" + product.getWebsiteUrl());
		System.out
				.println("Category:" + product.getProductCategory().getName());
	}

	public static String linkedinDataToString(Product product) {
		StringBuilder strBuilder = new  StringBuilder();
		strBuilder.append(product.getName()).append("\t").append(product.getType().getName())
			.append("#").append(product.getProductCategory().getName())
			.append("\t").append(product.getNumRecommendations()).append("\t");

		return strBuilder.toString();
	}

	public static void main(String[] args) {

		try {
			// use auth creds for linkedin account kitenga analytics
			String consumerKeyValue = args[0];
			String consumerSecretValue = args[1];
			String accessTokenValue = args[2];
			String tokenSecretValue = args[3];

			final LinkedInApiClientFactory factory = LinkedInApiClientFactory
					.newInstance(consumerKeyValue, consumerSecretValue);

			final LinkedInApiClient client2 = factory.createLinkedInApiClient(
					accessTokenValue, tokenSecretValue);
			Map<SearchParameter, String> searchParameters = new EnumMap<SearchParameter, String>(
					SearchParameter.class);
			String qryStr = "NetApp";
			searchParameters.put(SearchParameter.KEYWORDS, "*");
			Companies companieList = client2.searchCompanies(searchParameters);
			traverseCompanies(companieList, client2);

			// ////////////////////////////////////
			// final CompaniesApiClient client =
			// factory.createCompaniesApiClient(
			// accessTokenValue, tokenSecretValue);
			// Companies companies = client.getFollowedCompanies();
			// traverseCompanies(companies, client);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private static String encodeHTML(String s) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c > 127 || c == '"' || c == '<' || c == '>') {
				out.append("&#" + (int) c + ";");
			} else {
				out.append(c);
			}
		}
		return out.toString();
	}
}

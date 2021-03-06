/**
 * 
 */
package com.mining.linkedin;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.google.code.linkedinapi.client.CompaniesApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.LinkedInAuthenticationClient;
import com.google.code.linkedinapi.schema.Product;
import com.google.code.linkedinapi.schema.SchemaEntity;
import com.mining.linkedin.LinkedinCrawler;
import com.mining.linkedin.LinkedinSearch;

/**
 * @author Kaniska_Mandal
 *
 */
public class LinkedinCrawler extends Configured implements Tool {

	public static class LinkedinDataMapper extends
			Mapper<LongWritable, Text, Text, Text> {
		
		private Text word = new Text();
		private  LinkedInApiClient  client = null;
		private String searchCategory = null;

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			
			HashMap<Long, SchemaEntity> linkedinDataSet = new HashMap<Long, SchemaEntity>();

			System.out.println("Input : "+value);
			
			// assume that the input is a series of simple queries using the Twitter query format
			String query = new String(value.getBytes(), 0, value.getLength());
						
			context.getCounter("MyCrawler", "Linkedin Crawler").increment(1);
			try {
				//System.out.println("### Input data .. "+query);
				
				if(searchCategory.equals("Company")) {
					LinkedinSearch.searchCompanyInfo(client, linkedinDataSet, query);
					for (SchemaEntity linkedinData : linkedinDataSet.values()) {
						word.set(LinkedinSearch.linkedinDataToString((Product)linkedinData));
						context.write(new Text( query.concat("#").concat(((Product)linkedinData).getName()) ), word);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();				
				if (ex.getLocalizedMessage().contains("Did not find company with universal-name")) {
					//System.out.println("Throwing exception : " +ex.getClass().getSimpleName());
					// ignore the exception
				}else 
					throw new IOException(ex.getLocalizedMessage());
			}
		}
		
		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
			super.setup(context);
			
			String consumerKeyValue = context.getConfiguration().get("linkedin.consumer.key");
			String consumerSecretValue = context.getConfiguration().get("linkedin.consumer.secret");
			String accessTokenValue = context.getConfiguration().get("linkedin.access.token");
			String tokenSecretValue = context.getConfiguration().get("linkedin.token.secret");
			
			searchCategory = context.getConfiguration().get("category");
			

			final LinkedInApiClientFactory factory = LinkedInApiClientFactory
					.newInstance(consumerKeyValue, consumerSecretValue);

			client = factory.createLinkedInApiClient(accessTokenValue, tokenSecretValue);
			
		}
		
		@Override
		protected void cleanup(Context context) throws IOException, InterruptedException {
			client = null;
			
		}
		
	}
	
	
	static int printUsage() {
		System.out
				.println("linkedin crawler [-m <maps>] [-r <reduces>] <input> <output>");
		ToolRunner.printGenericCommandUsage(System.out);
		return -1;
	}

	/**
	 * The main driver for word count map/reduce program. Invoke this method to
	 * submit the map/reduce job.
	 * 
	 * @throws IOException
	 *             When there is communication problems with the job tracker.
	 */
	public int run(String[] args) throws Exception {

		DistributedCache.addArchiveToClassPath(new Path("/cache/commons-cli-1.1.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/linkedin-j.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/xpp3_min-1.1.4c.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/xpp3-1.1.3.3_min.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/xstream-1.3.1.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/signpost-core-1.2.1.2.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/commons-codec-1.3.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/jaxb-api.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/jaxb-impl.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/jaxb1-impl.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/jsr173_1.0_api.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/hadoop-annotations-2.0.0-cdh4.3.0.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/hadoop-common-2.0.0-cdh4.3.0.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/hadoop-core-2.0.0-mr1-cdh4.3.0.jar"), getConf());
		DistributedCache.addArchiveToClassPath(new Path("/cache/activation.jar"), getConf());
		
		Job job = new Job(getConf(), "linkedin crawler");
		job.setJarByClass(LinkedinDataMapper.class);

		// the keys are words (strings)
		job.setOutputKeyClass(Text.class);
		// the values are counts (ints)
		job.setOutputValueClass(Text.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setMapperClass(LinkedinDataMapper.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new LinkedinCrawler(),
				args);
		System.exit(res);
	}
	
	
	private static boolean valueMissing(String value){
		if (value == null || value.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	private static Object connectToLinkedin(String consumerKey, String consumerSecret,
			String accessToken, String accessTokenSecret){
		
		return null;
	}

}

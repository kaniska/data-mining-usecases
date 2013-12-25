data-mining-usecases
====================

All the usecases for data mining :

1) Pull stock events in real time at a specified schedule 
 - store the events in Hbase with a pre-configured 

2) Pull Linkedin data in a streaming fashion

Current Status :  
 - (build code) ant all [ TO DO - mavenize the project ]
 - copy the dependency jars into /cache hdfs folder
     ~ hadoop fs -copyFromLocal /project_home/lib /cache
 - run the linkedin search program as a MR app
     ~ hadoop jar /project_home/ltarget/LinkedinSearch.jar com.mining.linkedin.LinkedinCrawler -Dlinkedin.consumer.key=[your_consumer_key] -Dlinkedin.consumer.secret=[your_consumer_secret] -Dlinkedin.access.token=[your_access_token] -Dlinkedin.token.secret=[]  hdfs://[your_server]:8020/user/[your_user_name]/linkedin/input hdfs://[your_server]:8020/user/[your_user_name]/linkedin/output

Future Plan :
 - continuously pull data from linkedin using a scheduler
 - view the product ratings in comparison chart using D3 
 
3) Mine the google place data using a MR program

Current Status :
 - Categorize the places per category per rating
   ~ 
 - front end code 
   ~ 

Future Plan :
 - Add Search Textbox with various Filtering options : Radius, Rating, 
 - Host it in a webserver
 - port it as Android App

4) Recommendation Engine data mining
  -

5) Mine the log data and query in real-time using MongoDB
 ~ monitor the datasource for new data and push them into MongoDB staging collection
 

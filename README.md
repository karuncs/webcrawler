# webcrawler
Crwaling the data from web and determine most occured words until 3 rd level
Brief documentation
I used following packages/libraries/apis
•	Mainly dependency injection, Spring boot framework to make this application simple and readable
•	For elastic search---spring-data-elasticsearch
•	For logging-logback framework
•	For testing  -- spring-boot-starter-test and Junit
•	Multithreaded—threadpoolTaskexecutor
•	mvn clean verify package to create or deploy application in staging or production environment
•	For multithread- ThreadPoolExecutor
•	Jar file can run on different servers by scheduling load balancer .., in this way the application is scalable

Issue
I got a problem of compatability issue and took long time to resolve by reading blogs or forums
Like latest elasticsearch 2.3.0 – we used spring boot 1.3.5 -- while using there is no compatability. I decided to downgrade elastic search and it worked.
Improvements
Needs to improve to collect header and links of each depth .


______________
Task_______
___________
Web Crawler in java
 Create a simple web crawler that, starting with a given web page URL, will extract links that relates to some given search keywords. Crawling should be kept inside the top level domain of the starting URL, with a given depth level.
 
Input: Domain name, depth, keywords to match
 
Requirements:
1. Make use of Object oriented principles and design patterns 
2. Application has to be testable, multi-threaded and scalable 
3. The results are saved into a Elastic Search: as a minimum it has to contain page URL, the links themselves, the matched search term, http headers 
4. Application has to be in java 
5. Application will have some kind of logging functionality 
6. The crawler is easily configurable 
7. A GUI is nice, but not required, Console application is enough 
8. Along with the solution, we would like a small document with bullet points (dont spend a lot of time here) describing main challenges you encountered and also main improvement points you would consider for the solution.




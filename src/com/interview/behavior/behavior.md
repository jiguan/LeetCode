# Behaviro questions

## STAR Method

Describe a complex situation

- S: The format begins with a description of the situation you encountered. Whichever situation you choose- give enough detail to help the interviewer visualize what happened.
- T: Then- you need to give information about the task- such as the complex situation you needed to explain.
- A: The third step is to tell about your actions. This is where you explain the method you chose to help explain the difficult information.
- R:  Lastly- indicate what your results were. Tell how you were able to enhance your audience's understanding of the topic.

## Project

* What technical issue did you deal with？

- S: When I was migrating services to the cloud. The final step is re-directing DB connection to the cloud one. 
- T: I did all kinds of testing. In INT environment, everything is fine. I migrated to PROD, no issues at all. Job done. But after I got home, I get alerted emails saying we cannot retrieve data from the DB.
- A: I first check the network and incoming traffic. Everything is normal. I rolled back my code. I wrote a function to get records to mimic the server's behavior. I found that some DB's credential is broken. The Load Balancer sometime may distribute the request to good DB sometime not.
- R: I contact DBA and asked them to double check the credentials. In the meanwhile, I reported to my manager and make him push the improvement using Jenkins job only to create the database. 


* The most challenging project?

The first project, at that time


* Describe a project didn't go well

- S: As you may know, Expedia purchased a lot of travel related companies. One of companies is called HomeAway. It is very similar to AirBnB. After we acquired this company, we want to integrate its system into mine so that we don't need to maintain 2 systems at the same time.
- T: However, the progress didn't go well. There is no architect or lead involved. Every team thought they know what they need to do but they didn't. For example, for hotels, we collect the payment and pay them. So we already have the payment ID when confirming customers booking. But for HomeAway, the house owners collect the payment and we take a commission. The result is, in order for our system processing the booking, upper stream need to first call their service and then pass the request to us. Then our system need to call them again.
- A: Even though I tried to make myself available, we still encounter all kinds of issues. The project was halted later.
- R: The lesson I learned is even though you are only part of the team, you should also have the full picture. Be nice and talk with each team lead and get to know what need to be done. Communication is most important.

* Describe a time you persuade others

When I was in Expedia, we were in the process of migrating microservices from Data Center to AWS. I was assigned to a task -- design a way to forward requests from DC to AWS. There are 2 approaches, we could either place the proxy 


* Did you lead a team?

- S: We have a service to send notifications. Currently, it pull 5K records every 30 mins. As you can see, some bandwidth was wasted.
- T: I came up with one improvement. Convert it into event driven pipeline. When a new notification comes in, we will push it into RabbitMQ. Two microservers will fetch it which speed up the sending notification process dramatacally.
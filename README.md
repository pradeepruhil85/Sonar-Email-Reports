Sonar-Email-Utility
===================

Sonar-Email-Reports

Sonar Email Utility project is a simple project which wil send the sonar reports of the projects configured in the SONAR
in email.


Sonar email utiltiy project is a java project and can be executed an excecutable jar. 

For the email related configuration, Please see the sonar.properties file,

Features present in the utilty :

Features of Utility :
1.	Automatically include new projects  configured in the SONAR.
2.	Provide differential increase or decrease of violations in the round brackets.
3.	Linux Cron Job is created which will send the email to development team every fortnight.
4.	Exclude projects in Reports just by adding the Project name in Properties.


Tech Stack Used :

1. Sonar Web service api is used to fetch the results from sonar
2. Spring is for DI and sending the email.
3. Veoclity is used to define the eamil template
4. For Build, Maven is used.



Let me know guyz if you face any problem while using the utilty.

Thanks
Pradeep

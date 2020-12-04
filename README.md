# LoanAmortizaiton-with-SOAP-and-APACHE-HTTP
A project to create a loan amortization UI that utilized SOAP and HTTP

Overview
   The project under taken consisted of creating a loan amortization calculator which given three of four variables calculates the forth and optionally emails an amortization schedule to the user’s designated email address. This project offered a slight twist in that the calculation was performed by a WSDL-based SOAP service in the Heroku Cloud environment, and verified the user’s email address using SendGrid’s cloud-based email service. The technologies used to create the system included Java, Axis2, Tomcat, SendGrid, and the Heroku Cloud environment.
   
   Definitions
•	Amortization is a process of reducing a debt by applying regular payments over some time period that typically includes both principle and interest. 
•	Loan Amount represents the magnitude of the loan. 
•	Loan Payment represents the amount of money to be paid on a regular basis that will reduce the balance of the loan and includes an interest payment.
•	Loan Interest Rate is the amount charged as a percentage of the loan amount over each term.
•	Loan Term represents the number of payment periods for the loan.
•	Amortization Schedule is a report showing the characteristics of a loan (amount, payment, interest rate, and term) and a table that has the payment of each term of the loan consisting of payment, principal, interest, and the remaining loan balance.

System Specification
Client Side
-	The Amortization Calculator requires a computer with at least 4 GB memory, multicore CPU(s), and connectivity to the internet.
-	The Java Runtime Environment (JRE) version 8 or greater must be installed.
-	LoanCalcClient.jar must be installed.

Server Side
-	Installation of the Loan Calculation Service into the Heroku Cloud environment that include the SendGrid service interface.

Test Plan & Results
   The testing scope for the Loan Amortization Calculator included four test case scenarios with and without the email option selected, table 3, the expected application functionality, performance testing of the service, environmental dependencies (connectivity), JRE version compatibility, and minimum hardware configurations scenarios.

Testing Scenarios
Application Validation
-	Test case for calculating loan amount with and without email option selection
-	Test case for calculating loan payment with and without email option selection
-	Test case for calculating loan interest rate with and without email option selection
-	Test case for calculating loan term with and without email option selection
-	GUI validates data as numeric and greater than 0

Application Usability
-	GUI allows the user to select the variable to calculate and provides the required fields for entering the three required values to complete the calculation
-	GUI gives the user the option of emailing an amortization schedule to email with the entered loan scenario after 2-step email verification is satisfied
-	GUI reports connectivity issues
-	GUI provides an enabled Calculate button for submitting the loan scenario to the Loan Calc Service
-	GUI provides a Clear button for resetting all values displayed on the screen
-	GUI provides the user with the ability to exit the application
Service Operation Validation 
-	SOAP request and response artifacts created in SoapUI for service operation testing Performance, Scalability, and Average Round Trip Testing

Class Design
   The Loan Amortization Calculator design consists of a Java Swing GUI for selecting the type of amortization calculation desired and a remotely located service running on Tomcat 9.x in the Heroku Cloud to perform the actual calculation. The components communicate securely using the SOAP 1.2 protocol over HTTPS in a synchronous call.





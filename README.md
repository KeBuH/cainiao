# cainiao

CaiNiao Coding Challenge

Perquisites.
For completing coding challenge candidates are required to use following technology stack
Java 1.8 (minimum)
Maven
Spring Boot

Problem Statement.

Imagine that you are developing smart Logistics platform that is building cost-time optimized delivery chain choosing from the set of 3rd party delivery companies available in database.

Say we have 3 Cities [A, B, C] that we are supporting now.
Also, we have 3 parts of the delivery chain
 [First Mile, Transport, Last Mile]

And here is how regular Company profile look like:

Company Name  Route Part   Pickup City  Delivery City  Price  Time
IronMan       First Mile   A            A              2$     4h
SuperMan      Transport    A            B              10$    2 d
SpiderMan     Last Mile    B            B              2$     2h
OldGranny     Last Mile    B            B              0.5$   8h


As a customer of our system I’m interested to deliver my parcel from city A to B in cheapest price.

For simplicity say our customers always value Price vs Time.

So, when user gives us just 2 parameters from: A, to: B

Our system supposed to return best possible solution from example table above:

First Mile: IronMan
Transport: SuperMan
Last Mile: OldGranny
Total Cost: 12.5$
Total Time: 2d 12h

*note: There was an option to choose Spiderman but as we value price more than time, we choose the OldGranny instead.


Now specifics wise your final delivery should be a code base (repository, archive…) that can build executable fat jar with help of maven.

When Jar launched it supposed to run a HTTP server on port 8080 with just one GET end point that accept 2 path params – fromCity, and toCity.

Example:

http://localhost:8080/route?ffromCity=A,toCity=B

After User called that query, we supposed to return data like following, in any format (json, plain text, xml)

First Mile: IronMan
Transport: SuperMan
Last Mile: OldGranny
Total Cost: 12.5$
Total Time: 2d 12h

Important: 

In case we can’t build the Route from city to city Please display necessary message that building route is not possible in response.

Database data with companies to parse should be in same archive with this file (look for data.csv).


Q&A =>>

How challenge is scored?

We mostly interested in High level OOP clarity of solution. So please code with supportability and extendibility in mind. 

Such details as what way file was parsed or any other low-level details is not impactful.

Also, presence/absence of tests is important factor, while you do not need to fully test the solution, please provide couple that you think is covering most of the cases.

What if I have other questions?

Please feel free to ask any questions at any point of time.
But do not worry, questions are not a part of the challenge.

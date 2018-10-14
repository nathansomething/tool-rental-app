# tool-rental-app

This is my submission to the Cardinal Financial Programming Exercise. Written in Java 
8 in Eclipse 4.9, tested using Junit 5.

### Requirements

When I first get any problem set, whether it's a practice problem, a software ticket, 
or a "hey go do they" request, the first thing I try to do is break it down into a 
list actionable requirements. I've done that for this problem and included it in my submission (requirements.txt). As I go through 
the ticket, I check off each requirement as I satisfy it to make sure nothing slips though 
the cracks.

### Organization

I've decided to organize this solution into 3 packages: models, services, and tests. The 
idea is that models store POJO-like data and contain methods that use only on the data 
they contain. Services contain methods that could apply to multiple models or operate 
on data outside of the scope of a model. And tests contains all of the testing code. 

### Models
For this solution I created 4 model classes - ToolType, Tool, Checkout, and RentalAgreement.
ToolType represents the name and pricing information, Tool
For the first 3, I used the factory pattern to an api-like interface for the models. 
I'm personally a fan of this pattern because it creates easier to read code (especially 
for initializing large classes) and allows precise control over read/write permissions. For 
the RentalAgreement class, I decided to make it an immutable class with all public 
methods. This helps to reduce the likelihood of errors and make the class easier to 
test (since each field is being used in the Junit Tests).

### Services
For this solution I created 3 service classes - DateChargabilityService, RoundingService, 
and ToolService.
* DateChargabilityService - contains methods which help determine if a particular date 
  is chargable
* RoundingService - contains a round method, which help to make sure numbers are appropriately 
  rounded to meet requirement specifications
* ToolService - contains methods which manage the tool inventory, such as ensuring 
  there are no duplicate codes and retrieving a tool by its code

### Tests
For the test section, I used a basic Junit setup. There are 8 tests which should cover 
pretty much every case in the solution. The first 6 were taken directly form the problem 
sheet, the later 2 I added to ensure complete code coverage. 
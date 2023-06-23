# How to build software

This project is an attempt at deciphering the methodologies and approach on how to build an application, given a set of requirements. The project aims to employ 
the best practices and standards across the industry, from requirements analysis to design to code. The methodology is summarized in this readme, which will explain and lead you 
to other files and directories in this project.

## The Approach
The requirements for the software are listed [here](./requirements.md). We can assume that these requirements are specific to one or more epics. From the requirements we go to event modelling and discovery, attempting to define the core domain and behvior around the requirements. From the event storming model, we can proceed to create C4 diagrams or UML diagrams as required and also create user stories for each epic. And from those stories we create test scenarios, which should be easy to envision at this point based on story description and event model. Final step is to write code, and a TDD approach can be easily taken here. In short, the approach here:

1. Create epics from requirements
2. Create event model based on requirements
3. Create stories under epics based on event model
4. Write test scenarios and code based on story details and event model



### 1. Create epics from requirements
The epic creation is simply a matter of taking relevant requirements which may seemingly belong together, and placing them under an epic. The epic acts as a bucket for related requirements. Epics for this project are under:


### 2. Create event model based on requirements
Event models are to be created from the requirements based on **event storming**. This is a crucial step to establish common understanding of the requirements, business process flow, ubiquitous language, identify key areas of concerns and the domain model.
For this project, I started with *big picture event storming*, followed that with *process modelling* and then the *software design* event storming techniques. The results of these sessions are [here](./modelling/event%20storming.pdf). 



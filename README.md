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
The epic creation is simply a matter of taking relevant requirements which may seemingly belong together, and placing them under an epic. The epic acts as a bucket for related requirements. For this project, epics are treated as separate github projects, which are listed here [here](https://github.com/muhammad-taha1/modern-application/projects?query=is%3Aopen)


### 2. Create event model based on requirements
Event models are to be created from the requirements based on **event storming**. This is a crucial step to establish common understanding of the requirements, business process flow, ubiquitous language, identify key areas of concerns and the domain model.
For this project, I started with *big picture event storming*, followed that with *process modelling* and then the *software design* event storming techniques. The results of these sessions are [here](./modelling/event%20storming.pdf). 

The event storming also leads to the creation of **ubiquitous language**, a common dictionary of terminology (with common understanding) that should be used throughtout this project. Catalog of ubiquitous language is [here](./ubiquitous-language-catalog.md). This should be updated whenever our model (and the event storming diagrams) change.


### 3. Create stories under epics based on event model
The next step is to define user stories under each epic (github project) here, which typically corresponds to a single behavior that can be achieved in the application. The projects already have some user stories defined in them, based on the event storming diagram. User story should be written in the format:

    As [a user persona], I want [to perform this action] so that [I can accomplish this goal].

This format is easily achievable by looking at the event storming diagram. The actor tells us who the user persona is, the command gives us the action to be performed and the goal to be accomplished can either be given by read model or policy. 

Due to severe symptoms of laziness, most of the stories in the project have no descriptions. It is left on the reader's imagination to fill those out, based on the information we have thus far. However, there is [one story](https://github.com/users/muhammad-taha1/projects/3/views/1?pane=issue&itemId=31639433) with description and acceptance criteria added to it. As a bonus point, I have added the acceptance criteria in BDD format.



### 4. Write test scenarios and code based on story details and event model
Have a look at the following series of medium articles, which covers in detail the idealogy behind the development of this application, along with details on testing:
https://medium.com/@muhammad.taha/list/designing-modern-applications-542ceed3e64f

## business requirements:
### onboarding buddy app
Assist in making a new hire's onboarding journey easy and trackable. The app should allow:
- companies to register to platform, with a central poc for company
- platform can handle details for multiple companies
- companies to create teams. Each team can have a different onboarding experience
- Each team can have admin(s), who would manage onboarding resources and create onboarding plans for their team
- a team can have multiple onboarding plans
- onboarding plan consists of several tasks, and each task is broken down into steps about a particular thing the new hire needs to do. E.g. Setting up desktop can be a task, where the steps can be: install VSCode, install postman etc
- a step can have description, title, links, pdf attachments, ability to be checked off
- system should track completion of onboarding plan & task automatically
- email new hire when an onboarding plan gets assigned to them with due date
- email new hire, POC + team owner when new hire completes onboarding and if onboarding due date has passed
- email new hire if something changes in their onboarding plan after they have been assigned to it
- may need to support other notification types (mobile push) in addition to emails in future
- Same onboarding plan can be completed by many people simultaneously, or different points in time





## tech requirements:
- all services should follow same format/pattern for easier onboarding learning
- needs to follow 12 factor methodology: https://12factor.net/ 
- easy and intuitive way for all services to handle common concerns like logging, error-handling, security etc
- business logic unit testable
- openAPI spec for all projects
- service should create sdks






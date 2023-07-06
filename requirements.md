## business requirements:
### onboarding buddy app
Assist in making a new hire's onboarding journey easy and trackable. The app should allow:
- companies to register to platform, with a central point-of-contact (poc) for the company
- platform should be able to handle multiple companies
- companies can create teams. Each team can have a different onboarding experience
- Each team can have admin(s), who would manage onboarding resources and create onboarding plans for their teams
- a team can have multiple onboarding plans
- onboarding plan consists of several tasks, where each task is broken down into steps about a particular thing the new hire needs to do. E.g. *Setting up desktop* can be a task, where the steps can be: *install VSCode*, *install postman* etc
- a step can have a title, description and ability to be checked off
- system should track completion of onboarding plans & tasks automatically
- An onboarding plan can be assigned to a team's new hire by the team's admin, along with due date and a poc for the new hire (who can be their onboarding buddy)
- email new hire when an onboarding plan gets assigned to them with due date
- email new hire, POC and team owner when new hire completes onboarding, and also if onboarding due date has passed
- new hires should only work on the onboarding plan assigned to them at that point in time. No further changes to their onboarding plan after assignment.
- may need to support other notification types (mobile push) in addition to emails in future
- Same onboarding plan can be completed by many people simultaneously, or at different points in time





## tech requirements:
- all services should follow same format/pattern for easier onboarding learning
- needs to follow 12 factor methodology: https://12factor.net/ 
- easy and intuitive way for all services to handle common concerns like logging, error-handling, security etc
- business logic unit testable
- openAPI spec for all projects






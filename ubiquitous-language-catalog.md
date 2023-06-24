## Common terminologies and their meanings, to be used everywhere in this project

**Company**: A parent entity which can be registered on the application
**Team**: A sub-entity belonging to a company. A team cannot exist without company 
**Company admin**: A user who acts as the owner of the company within the context of our application. This user can add employees in company, create teams, and assign company employees as team admins
**Company employee**: A user belonging to a particular company. The users may or may not belong to a team.
**Team admin**: A user who acts as admin for a particular team. Team amdin can create, update onboarding plans for their team and assign onboarding plan to a team member.
**Team member**: A user belonging to a team. This user has no special privileges and is similar to company employee, the only exception being that the user belongs to a team.
**Assignee**: A team member who has been assigned to complete an onboarding plan. Multiple users in a team can be assigned to the same or multiple onboarding plans at the same time.
**onboarding plan**: An onboarding plan which the assignee needs to complete. An onboarding plan consists of several tasks.
**Task**: A task in an onboarding plan may have one or more steps.
**Step**: Simplest unit of work in an onboarding plan. This equates to something tangible that the assignee can action upon.

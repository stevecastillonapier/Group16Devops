
Use Case: View Population in Cities and Urban cites
Use Case ID: Devops-003
Title: View population of people living in and outside cities by continent, region, and country
Actor: Marketing Manager

Goal:
To view the total population, urban (city) population, and city population for each continent, region, or country.
________________________________________
Preconditions
• The user is logged into the Companies Portal with valid credentials.
• The system has access to an up-to-date dataset of population data by country, region, and continent, including breakdowns of urban and non-urban populations.
________________________________________
Postconditions
•	The system displays a structured table or chart showing total, urban, and non-urban populations grouped by the selected geographic level (continent, region, or country).
________________________________________
Main Flow
1.The user navigates to the company portal.
2.The system prompts the user to select the level of analysis:
a)By Continent
b)By Region
c)By Country
3.The user selects the desired option.
4.The system retrieves the relevant population data from the database:
a)Total population
b)Population living in cities
c)Population not living in cities
5.	The system displays the information in a structured format (table or chart) showing the comparison between total, urban, and rural populations for each selected area.
________________________________________
Alternate / Exception Flows
• A1: If population data for a selected level (e.g., region) is missing or incomplete, the system displays a message:
“Population data is unavailable for the selected region.”
• A2: If the user is not authorized to access population analytics, the system redirects them to the login or access request page.


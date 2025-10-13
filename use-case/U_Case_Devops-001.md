
Use Case: View Countries by Population
Use Case ID:Devops-001
Title: View countries ordered by population with filtering options
Actor: Marketing Managers

Goal:
To view a list of countries sorted by population in descending order, optionally filtered by world, continent, or region, and optionally limited to the top N results.

________________________________________
Preconditions
• User login to Companies Portal using credentials.
• The system has access to a complete and up-to-date dataset of countries, their population, continent, and region.
________________________________________
Postconditions
• The user is shown a list of countries sorted by population according to their selected filters.
• Users selects what report , he/she wants to view.
________________________________________
Main Flow
1.The user navigates to Companies Portal using valid credentials.
2.The system prompts the user to select a filter:
a)All countries
b)By continent (user selects from a list)
c)By region (user selects from a list)
3.User can request the top “N” numbers of countries, in the world, continent or Region.
4.The user submits the request.
5.The system processes the request:
a)Retrieves the relevant countries.
b)Sorts them in descending order by population.
6.The system displays the results in a structured list.
________________________________________
Alternative Flows
AF2: No countries found for selected continent or region. The system notifies the user that no data is available for the chosen filter.
________________________________________
Exceptions
E1: System is unable to retrieve country data (e.g., database error).The system displays an error message indicating a data retrieval issue.
________________________________________
Assumptions
•Country population data is stored and updated regularly.
•The user has a basic understanding of filters and the purpose of the feature.
________________________________________


Use Case: View Cities by Population
Use Case ID: Devops-002
Title: View cities ordered by population with filtering
Actor: Marketing Managers

Goal:
To view a list of cities sorted by population in descending order, optionally filtered by world, continent, region, country, or district, and optionally limited to the top N results.
________________________________________
Preconditions
• The user is logged into the Companies Portal using valid credentials.
• The system has access to an up-to-date dataset of cities, including their population, country, region, continent, and district.
________________________________________
Postconditions
• The user is presented with a list of cities sorted by population according to their selected filters and limit criteria.
________________________________________
Main Flow
1.The user navigates to the company portal.
2.The system displays filtering options:
a)All cities (worldwide)
b)By continent (user selects from a list)
c)By region (user selects from a list)
d)By country (user selects from a list)
e)By district (user selects from a list)
3.The system provides an optional field for the user to enter a value for Top N cities (e.g., top 10 most populated).
4.The user submits their request.
5.The system processes the request:
a)Retrieves relevant cities from the dataset.
b)Applies the selected filters.
c)Sorts the cities by population in descending order.
d)Applies the “Top N” limit if provided.
6.The system displays the results in a structured format (e.g., table or list view).
________________________________________
Alternate / Exception Flows
• No Data Found: If the selected filter returns no cities, the system displays a message: “No results found for the selected criteria.”
• Invalid Input for N: If the user enters an invalid number (e.g., negative or non-numeric), the system prompts for a valid input.
________________________________________





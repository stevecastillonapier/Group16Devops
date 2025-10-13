
Use Case: Search Population by Area
Use Case ID: Devops-004
Title: Search and view population by selected area
Actor: Marketing Managers

Goal:
To allow the user to search and view population data for a specified geographic area such as the world, a continent, region, country, district, or city.
________________________________________
Preconditions
• The user is logged into the Companies Portal with valid credentials.
• The system has access to a complete and up-to-date dataset of population data for all geographic levels (world → continent → region → country → district → city).
________________________________________
Postconditions
• The system displays accurate population information for the selected area in a clear and structured format on the Companies Portal.
________________________________________
Main Flow
1.The user navigates to the “Population Search” feature/page in the Companies Portal.
2.The system presents a search input field and filter options for selecting the population scope:
a)World
b)Continent (user selects from a list)
c)Region (user selects from a list)
d)Country (user selects from a list)
e)District (user selects from a list)
f)City (user selects from a list)
3.The user inputs or selects the desired geographic area.
4.The user submits the search request.
5.The system processes the request:
a)Validates the user’s input.
b)Queries the population database for the relevant data.
c)Retrieves and formats the population results.
6.The system displays the population result for the selected area (e.g. in a chart, or table format).
________________________________________
Alternate / Exception Flows
•A1: If the user enters an invalid or unrecognized area, the system displays:
“Invalid area. Please select a valid location from the list.”
•A2: If population data for the selected area is unavailable, the system displays:
“Population data not found for the selected area.”



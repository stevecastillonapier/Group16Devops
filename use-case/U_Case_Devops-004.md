# Use Case: Search Population by Area

**Use Case ID:** Devops-004

**Title:** Search and view population by selected area

**Actor:** Marketing Managers

**Goal:**
To quickly search for and retrieve the precise total population figure for any specified geographic area (Continent, Country, District, or City) to validate market size assumptions.
________________________________________
## Preconditions
- The Marketing Manager is logged into the Companies Portal with valid credentials.
- The system has access to a complete and up-to-date dataset of population data for all geographic levels (world → continent → region → country → district → city).
________________________________________
## Postconditions
• The system displays accurate population information for the selected area in a clear and structured format on the Companies Portal.
________________________________________
## Main Flow
1. The Marketing Manager navigates to the “Population Search” feature/page in the Companies Portal.
 
2. The system presents a search input field and filter options for selecting the population scope:
a)World
b)Continent (Marketing Manager types continent in prompt. eg. Africa)
c)Region (Marketing Manager types region in prompt. eg. Caribbean)
d)Country (Marketing Manager types country in prompt. eg. United States)
e)District (Marketing Manager types District in prompt. eg. Belize)
f)City (Marketing Manager types City in prompt. eg. Belmopan)

3. The Marketing Manager inputs the desired geographic area.

4. The Marketing Manager  submits the search request.

5. The system processes the request:
a)Validates the user’s input.
b)Queries the population database for the relevant data.
c)Retrieves and formats the population results.

6. The system displays the population result for the selected area (e.g. in a chart, or table format).
________________________________________
## Alternate / Exception Flows
- A1: If the user enters an invalid or unrecognized area, the system displays:
“Invalid area. Please select a valid location from the list.”
- A2: If population data for the selected area is unavailable, the system displays:
“Population data not found for the selected area.”




# Use Case: View Countries by Population (Marketing Focus)

**Use Case ID:** Devops-001

**Title:** Analyze Global Markets (countries) by Population and Region, ordered by population size with filtering options

**Actor:** Marketing Managers

**Goal:** To identify and compare potential target markets by quickly viewing and sorting countries based on population size, filtered by relevant continental or regional classifications.

________________________________________
## Preconditions
- The Marketing Manager is successfully logged into the Companies Portal.
- The system has access to a complete and up-to-date dataset of countries, including their current population, continent, and region data.
________________________________________
## Postconditions
- The system successfully displays a tabular list of countries relevant to the selected filter criteria.
- The countries are accurately sorted in descending order by population size.
- The Marketing Manager has identified the top global or regional markets for potential campaigns or product launches.
________________________________________
## Main Flow
1. The Marketing Manager navigates to Companies Portal using valid credentials.

2. 2.The system prompts the user to select a report from the list:
a)All countries
b)By continent (Marketing Manager types continent in prompt. eg. Africa)
c)By region (Marketing Manager types region in prompt eg. Caribbean)

3. Marketing Manager can request the top “N” numbers of countries, in the world, continent or Region.

4. The user submits the request.

5. The system processes the request:
a) Retrieves the relevant countries.
b) Sorts them in descending order by population.

6. The system displays the results in a structured list.
________________________________________
Alternative Flows
AF2: No countries found for selected continent or region. The system notifies the user that no data is available for the chosen filter.
________________________________________
Exceptions
E1: System is unable to retrieve country data (e.g., database error).The system displays an error message indicating a data retrieval issue.
________________________________________
Assumptions
•Country population data is stored and updated regularly.
•The Marketing Manager has a basic understanding of filters and the purpose of the feature.
________________________________________

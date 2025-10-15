
# Use Case: View Cities by Population (Marketing Focus)

**Use Case ID:** Devops-002

**Title:** Analyze City Markets by Population Density and Size ordered by population with filtering

**Actor:** Marketing Managers

**Goal:** To view a prioritized list of cities, sorted by population (descending), to quickly quantify and assess high-density markets for targeted geo-marketing campaigns.
________________________________________
## Preconditions
- The Marketing Manager is logged into the Companies Portal using valid credentials.
- The system has access to an up-to-date dataset of cities, including their population, country, region, continent, and district.
________________________________________
## Postconditions
- The Marketing Manager is presented with a list of cities sorted by population according to their selected filters and limit criteria.
________________________________________
## Main Flow
1. The Marketing Manager navigates to the company portal.

2. The system displays filtering options:
a)All cities (worldwide)
b)By continent (Marketing Manager types continent in prompt. eg. Africa)
c)By region (Marketing Manager types region in prompt eg. Caribbean)
d)By country (Marketing Manager types country in prompt eg. Belize)
e)By district ((Marketing Manager types district in prompt eg. Cayo))

3. The system provides an optional field for the user to enter a value for Top N cities (e.g., top 10 most populated).

4. The Marketing Manager submits their request.

5. The system processes the request:
a)Retrieves relevant cities from the dataset.
b)Applies the selected filters.
c)Sorts the cities by population in descending order.
d)Applies the “Top N” limit if provided.

6. The system displays the results in a structured format (e.g., table or list view).
________________________________________
## Alternate / Exception Flows
- No Data Found: If the selected filter returns no cities, the system displays a message: “No results found for the selected criteria.”
- Invalid Input for N: If the user enters an invalid number (e.g., negative or non-numeric), the system prompts for a valid input.
________________________________________





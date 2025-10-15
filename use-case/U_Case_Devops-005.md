# Use Case: View Global Language Statistics

**Use Case ID:** Devops-005

**Title:** View number and percentage of people speaking major languages

**Actor:** Marketing Managers

**Goal:**
To view the number of people who speak specific major languages (Chinese, English, Hindi, Spanish, Arabic), ranked from the greatest to smallest, along with their percentage of the world’s total population, to guide localization and content strategy.
________________________________________
## Preconditions
- The Marketing Manager is logged into the Companies Portal with valid credentials.
- The system has access to an updated dataset containing global population and language distribution data.
________________________________________
## Postconditions
- The system displays a ranked list of the selected languages showing:

a) Number of people speaking each language.

b) Percentage of the world population that speaks each language.

c) Proper sorting from the largest to smallest group of speakers.
________________________________________
## Main Flow
1. The Marketing Manager navigates to the Companies Portal.

2. Marketing Manager selects language statistics report.

3. The Marketing Manager submits the request.

4. The system processes the request by:
a)Retrieving the total world population.
b)Retrieving the number of people who speak each language.
c)Calculating the percentage of the world population for each language.
d)Sorting the results from greatest to smallest number of speakers.

5. The system displays the ranked list of languages with their speaker counts and percentages.
________________________________________
## Alternate / Exception Flows
- A1: If the dataset for one or more languages is incomplete, the system displays:
“Population data for some languages is currently unavailable.”
- A2: If the data retrieval fails, the system shows:
“Unable to load language statistics at this time. Please try again later.”




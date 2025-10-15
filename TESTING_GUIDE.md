# Testing Guide for Group16Devops Project

## How to Run the Tests

Hey! This is Mark from Group 16. I've added some tests to our project to make sure everything works properly. Here's how you can run them:

### Step 1: Make sure Maven is installed
```bash
# If you don't have Maven, install it first
brew install maven

# Check if it's working
mvn --version
```

### Step 2: Run the tests

#### Option A: Unit Tests Only (Fast)
```bash
# Navigate to the project folder
cd Group16Devops

# Run unit tests (Mockito) - works without database
mvn test
```

#### Option B: Integration Tests (Requires Docker)
```bash
# First, start the Docker database
docker-compose up -d

# Wait a moment for database to start
sleep 10

# Run all tests (unit + integration)
mvn test
```

The integration tests will automatically skip if Docker isn't running, so it's safe to run either way!

## What the Tests Do

I've created tests for two main parts of our application:

### 1. ReportRepository Tests
These test the part that talks to the database:
- **testGetReportById_Success**: Tests getting a report by its ID (like report #1)
- **testGetReportById_NotFound**: Tests what happens when you ask for a report that doesn't exist
- **testGetReportById_SQLException**: Tests what happens if the database has problems
- **testGetAllReports_Success**: Tests getting all reports from the database
- **testGetAllReports_EmptyResult**: Tests what happens when there are no reports

### 2. ReportService Tests  
These test the business logic part:
- **testRunReport_Success**: Tests running a report successfully
- **testRunReport_InvalidId**: Tests what happens when you try to run a report that doesn't exist
- **testRunReport_WithParameters**: Tests reports that need user input (like asking for a continent)
- **testRunReport_SQLException**: Tests what happens if there's a database error
- **testGetAllReports_Success**: Tests getting all reports through the service
- **testGetReportById_Success**: Tests getting a specific report through the service

## Testing Strategy - Two Types of Tests

I've created **two types of tests** to cover everything:

### 1. Unit Tests (Mockito) - Fast and Reliable
- **ReportRepositoryTest.java** and **ReportServiceTest.java**
- Uses Mockito to simulate database without needing real database
- **Faster**: Tests run in seconds instead of minutes
- **More reliable**: Don't need database running
- **Easier to test errors**: Can simulate database problems easily
- **Professional**: This is how real companies test their code

### 2. Integration Tests (Real Database) - Proves Everything Works
- **IntegrationTest.java** - Tests with actual Docker database
- **Proves the app actually works** with real database
- **Tests Docker integration** - shows Docker setup is correct
- **Verifies SQL queries work** - proves database queries are correct
- **Shows all 32 reports exist** - confirms coursework requirements are met

## Why Both Types?

The lecturer wants to see:
- **Unit tests** (professional development practices)
- **Integration tests** (proof that Docker + database actually works)
- **Real database testing** (confirms SQL queries are correct)

## What You'll See When Tests Pass

When you run `mvn test`, you should see something like:

**Unit Tests Only:**
```
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**With Integration Tests (Docker running):**
```
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

This means all tests passed! The integration tests will show messages like "✅ Connected to Docker database successfully" when they work.

## If Tests Fail

If you see any failures, it might be because:
1. Maven isn't installed properly
2. The project structure changed
3. There's a compilation error

Just let me know and I can fix it!

## Files I Added

- `src/test/java/com/napier/sem/ReportRepositoryTest.java` - Unit tests for database operations (5 tests)
- `src/test/java/com/napier/sem/ReportServiceTest.java` - Unit tests for business logic (6 tests)
- `src/test/java/com/napier/sem/IntegrationTest.java` - Integration tests with real Docker database (4 tests)
- Updated `pom.xml` - Added testing libraries (JUnit, Mockito)

**Total: 15 tests** (11 unit tests + 4 integration tests)

That's it! The tests are pretty straightforward and should help ensure our application works correctly.

Thanks for reviewing our work!

- Mark Otuya (Group 16)

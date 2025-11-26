# DevOps Course Work - Group 16
![GitHub Release](https://img.shields.io/github/v/release/stevecastillonapier/Group16Devops)

![GitHub License](https://img.shields.io/github/license/stevecastillonapier/Group16Devops)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/stevecastillonapier/Group16Devops/main.yml?branch=master&label=master%20build)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/stevecastillonapier/Group16Devops/main.yml?branch=develop&label=develop%20build)

[![codecov](https://codecov.io/github/stevecastillonapier/Group16Devops/graph/badge.svg?token=3IL31FFN0H)](https://codecov.io/github/stevecastillonapier/Group16Devops)

## Description
This project is a coursework assignment for a DevOps module that demonstrates the principles of integrating a Java application with a MySQL database.
The application provides an easy way to access and generate reports on population information.
It also includes automated builds, Dockerization, and GitHub Actions for continuous integration and delivery.  The program uses a reporting service and pulls queries and parameters directly from the database.  This structure provides a better method for scalability if other reports need to be generated quickly.

### The scrum team - Group 16
- Steve Castillo - Belize - 40773008@live.napier.ac.uk
- Danny Itza - Belize - 40735791@live.napier.ac.uk
- Mark Otuya - Nigeria - 40796688@live.napier.ac.uk
- Saoud Mohammed Masoud Dhafer Al Ahbabi - United Kingdom - 40793305@live.napier.ac.uk

## Project Architecture
- **Language:** Java 17
- **Build Tool:** Maven
- **Database:** Mysql
- **Containerization:** Docker
- **CI/CD:** GitHub Actions

## Continuous Integration
Our GitHub Actions workflow automatically runs unit and integration tests on every push to master and develop branches. The CI pipeline:
- Executes all unit tests using Maven
- Runs integration tests with Docker database container
- Generates code coverage reports using JaCoCo
- Uploads coverage metrics to Codecov
- Builds the application and creates release artifacts

All tests must pass before code is merged to the main branches.

## Branching Strategy
We use **GitFlow**:
- `main:` Production-ready code
- `develop:` Integration of new features
- `feature/*:` New feature branches
- `release/*:` Pre-release stabilization

## Getting Started
### Prerequisites
- Java 17+
- Maven 3.8+
- Docker

### Installation
```bash
# 1. Clone the repository
git clone https://github.com/stevecastillonapier/Group16Devops.git

# 2. Navigate to the project directory
cd Group16Devops

# 3. Build the Maven package (creates the JAR file in /target)
mvn clean package

# 4. Build and start all containers using Docker Compose
docker-compose up --build -d

# 5. (Optional) Check running containers
docker ps

```

### How to Stop Containers
```bash
# To stop and remove containers, networks, and volumes:
docker-compose down
```

## List of reports and Screenshoots
**32 requirements of 32 have been implemented, which is 100%.**

| ID | Name | Met | Screenshot                                   |
|----|------|-----|----------------------------------------------|
| 1 | All the countries in the world organised by largest population to smallest. | Yes | ![Report1](screenshots/final/report1.jpg)    |
| 2 | All the countries in a continent organised by largest population to smallest. | Yes | ![Report2](screenshots/final/report2.jpg)    |
| 3 | All the countries in a region organised by largest population to smallest. | Yes | ![Report3](screenshots/final/report3.jpg)    |
| 4 | The top N populated countries in the world where N is provided by the user. | Yes | ![Report 4](screenshots/final/report4.jpg)   |
| 5 | The top N populated countries in a continent where N is provided by the user. | Yes | ![Report 5](screenshots/final/report5.jpg)   |
| 6 | The top N populated countries in a region where N is provided by the user. | Yes | ![Report6](screenshots/final/report6.jpg)    |
| 7 | All the cities in the world organised by largest population to smallest. | Yes | ![Report 7](screenshots/final/report7.jpg)   |
| 8 | All the cities in a continent organised by largest population to smallest. | Yes | ![Report 8](screenshots/final/report8.jpg)   |
| 9 | All the cities in a region organised by largest population to smallest. | Yes | ![Report9](screenshots/final/report9.jpg)    |
| 10 | All the cities in a country organised by largest population to smallest. | Yes | ![Report10](screenshots/final/report10.jpg)  |
| 11 | All the cities in a district organised by largest population to smallest. | Yes | ![Report11](screenshots/final/report11.jpg)  |
| 12 | The top N populated cities in the world where N is provided by the user. | Yes | ![Report 12](screenshots/final/report12.jpg) |
| 13 | The top N populated cities in a continent where N is provided by the user. | Yes | ![Report13](screenshots/final/report13.jpg)  |
| 14 | The top N populated cities in a region where N is provided by the user. | Yes | ![Report14](screenshots/final/report14.jpg)  |
| 15 | The top N populated cities in a country where N is provided by the user. | Yes | ![Report15](screenshots/final/report15.jpg)  |
| 16 | The top N populated cities in a district where N is provided by the user. | Yes | ![Report16](screenshots/final/report16.jpg)  |
| 17 | All the capital cities in the world organised by largest population to smallest. | Yes | ![Report 17](screenshots/final/report17.jpg) |
| 18 | All the capital cities in a continent organised by largest population to smallest. | Yes | ![Report18](screenshots/final/report18.jpg)  |
| 19 | All the capital cities in a region organised by largest to smallest. | Yes | ![Report19](screenshots/final/report19.jpg)  |
| 20 | The top N populated capital cities in the world where N is provided by the user. | Yes | ![Report20](screenshots/final/report20.jpg)  |
| 21 | The top N populated capital cities in a continent where N is provided by the user. | Yes | ![Report21](screenshots/final/report21.jpg)  |
| 22 | The top N populated capital cities in a region where N is provided by the user. | Yes | ![Report22](screenshots/final/report22.jpg)  |
| 23 | The population of people, people living in cities, and people not living in cities in each continent. | Yes | ![Report23](screenshots/final/report23.jpg)  |
| 24 | The population of people, people living in cities, and people not living in cities in each region. | Yes | ![Report24](screenshots/final/report24.jpg)  |
| 25 | The population of people, people living in cities, and people not living in cities in each country. | Yes | ![Report25](screenshots/final/report25.jpg)  |
| 26 | The population of the world. | Yes | ![Report 26](screenshots/final/report26.jpg) |
| 27 | The population of a continent. | Yes | ![Report27](screenshots/final/report27.jpg)  |
| 28 | The population of a region. | Yes | ![Report28](screenshots/final/report28.jpg)  |
| 29 | The population of a country. | Yes | ![Report29](screenshots/final/report29.jpg)  |
| 30 | The population of a district. | Yes | ![Report30](screenshots/final/report30.jpg)  |
| 31 | The population of a city. | Yes | ![Report31](screenshots/final/report31.jpg)  |
| 32 | The number of people who speak Chinese, English, Hindi, Spanish, and Arabic. | Yes | ![Report 32](screenshots/final/report32.jpg) |

## Running the program in non-interactive mode
To run the program in non-interactive mode, you’ll need to modify the Dockerfile located in the root of the project.

1. Open the Dockerfile.
2. Update the last argument in the ENTRYPOINT line to the ID of the report you want the program to generate automatically when it runs.

For example, if you want to generate the report
“All the countries in the world organized by largest population to smallest”,
which has an ID of 1 in the reports table of the database,
your ENTRYPOINT should look like this:
```Dockerfile
ENTRYPOINT ["java", "-jar", "devops.jar","1"]
```
When the container starts, it will automatically run the program and produce the report corresponding to the ID specified.

***Tip:***
If you later want to run a different report, simply change the number at the end of the ENTRYPOINT line to the appropriate report ID (for example, "2", "3", etc.) before rebuilding your Docker image.

## Running the program in interactive mode
In interactive mode, you can manually choose which report to view after the container starts.

1. Open the Dockerfile and replace the ENTRYPOINT line with the following:
```Dockerfile
CMD ["tail", "-f", "/dev/null"]
```
2. Build and run the Docker container using your docker-compose.yml file:
```bash
# To start containers, networks, and volumes:
docker-compose up --build
```
3. Once the app container is running, open a terminal inside it:
```bash
docker exec -it <app_container_name> /bin/bash
```
4. From inside the container, run:
```bash
java -jar /tmp/devops.jar
```
The program will present an interactive menu allowing you to select the report you want to view.

### Troubleshooting

If you encounter issues running the containers, try the following steps:
1. Clean Up Old Docker Images and Containers

Old or outdated images can cause build conflicts.
   Before rebuilding, clean up old containers and images:
```bash
docker-compose down
docker system prune -a

```
Then rebuild everything fresh:
```bash
docker-compose up --build
```
2. Make sure no Mysql instances are not running on your computer and causing conflict.




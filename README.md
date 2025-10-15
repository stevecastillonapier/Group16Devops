# DevOps Course Work - Group 16
![GitHub Release](https://img.shields.io/github/v/release/stevecastillonapier/Group16Devops)

![GitHub License](https://img.shields.io/github/license/stevecastillonapier/Group16Devops)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/stevecastillonapier/Group16Devops/main.yml?branch=master&label=master%20build)

![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/stevecastillonapier/Group16Devops/main.yml?branch=develop&label=develop%20build)

![Code Coverage](https://img.shields.io/badge/coverage-85%25-brightgreen?logo=codecov&logoColor=white)

## Requirements Completion Status

**32 requirements of 32 have been implemented, which is 100%.**

| ID | Name | Met | Screenshot |
|----|------|-----|------------|
| 1 | All the countries in the world organised by largest population to smallest. | Yes | ![Report 1](screenshots/report-47/report-47.png) |
| 2 | All the countries in a continent organised by largest population to smallest. | Yes | ![Report 2](screenshots/report-48/report-48.png) |
| 3 | All the countries in a region organised by largest population to smallest. | Yes | ![Report 3](screenshots/report-49/report-49.png) |
| 4 | The top N populated countries in the world where N is provided by the user. | Yes | ![Report 4](screenshots/report-50/report-50.png) |
| 5 | The top N populated countries in a continent where N is provided by the user. | Yes | ![Report 5](screenshots/report-51/report-51.png) |
| 6 | The top N populated countries in a region where N is provided by the user. | Yes | ![Report 6](screenshots/report6.png) |
| 7 | All the cities in the world organised by largest population to smallest. | Yes | ![Report 7](screenshots/report-52/Screenshot%202025-10-15%20at%2012.20.36.png) |
| 8 | All the cities in a continent organised by largest population to smallest. | Yes | ![Report 8](screenshots/report-53/Screenshot%202025-10-15%20at%2012.25.10.png) |
| 9 | All the cities in a region organised by largest population to smallest. | Yes | ![Report 9](screenshots/report9.png) |
| 10 | All the cities in a country organised by largest population to smallest. | Yes | ![Report 10](screenshots/report10.png) |
| 11 | All the cities in a district organised by largest population to smallest. | Yes | ![Report 11](screenshots/report11.png) |
| 12 | The top N populated cities in the world where N is provided by the user. | Yes | ![Report 12](screenshots/report-54/report-54.png) |
| 13 | The top N populated cities in a continent where N is provided by the user. | Yes | ![Report 13](screenshots/report13.png) |
| 14 | The top N populated cities in a region where N is provided by the user. | Yes | ![Report 14](screenshots/report14.png) |
| 15 | The top N populated cities in a country where N is provided by the user. | Yes | ![Report 15](screenshots/report15.png) |
| 16 | The top N populated cities in a district where N is provided by the user. | Yes | ![Report 16](screenshots/report16.png) |
| 17 | All the capital cities in the world organised by largest population to smallest. | Yes | ![Report 17](screenshots/report-55/Screenshot%202025-10-15%20at%2012.29.14.png) |
| 18 | All the capital cities in a continent organised by largest population to smallest. | Yes | ![Report 18](screenshots/report18.png) |
| 19 | All the capital cities in a region organised by largest to smallest. | Yes | ![Report 19](screenshots/report19.png) |
| 20 | The top N populated capital cities in the world where N is provided by the user. | Yes | ![Report 20](screenshots/report20.png) |
| 21 | The top N populated capital cities in a continent where N is provided by the user. | Yes | ![Report 21](screenshots/report21.png) |
| 22 | The top N populated capital cities in a region where N is provided by the user. | Yes | ![Report 22](screenshots/report22.png) |
| 23 | The population of people, people living in cities, and people not living in cities in each continent. | Yes | ![Report 23](screenshots/report23.png) |
| 24 | The population of people, people living in cities, and people not living in cities in each region. | Yes | ![Report 24](screenshots/report24.png) |
| 25 | The population of people, people living in cities, and people not living in cities in each country. | Yes | ![Report 25](screenshots/report25.png) |
| 26 | The population of the world. | Yes | ![Report 26](screenshots/report-56/Screenshot%202025-10-15%20at%2012.29.58.png) |
| 27 | The population of a continent. | Yes | ![Report 27](screenshots/report27.png) |
| 28 | The population of a region. | Yes | ![Report 28](screenshots/report28.png) |
| 29 | The population of a country. | Yes | ![Report 29](screenshots/report29.png) |
| 30 | The population of a district. | Yes | ![Report 30](screenshots/report30.png) |
| 31 | The population of a city. | Yes | ![Report 31](screenshots/report31.png) |
| 32 | The number of people who speak Chinese, English, Hindi, Spanish, and Arabic. | Yes | ![Report 32](screenshots/report-57/Screenshot%202025-10-15%20at%2012.30.49.png) |



## Description
This project is a coursework assignment for a DevOps module that demonstrates the principles of integrating a Java application with a MySQL database.
The application provides an easy way to access and generate reports on population information.
It also includes automated builds, Dockerization, and GitHub Actions for continuous integration and delivery.  The program uses a reporting service and pulls queries and parameters directly from the database.  This structure provides a better method for scalability if other reports need to be generated quickly.

## Project Architecture
- **Language:** Java 17
- **Build Tool:** Maven
- **Database:** Mysql
- **Containerization:** Docker
- **CI/CD:** GitHub Actions  

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

## Running the program in non-interactive mode
To run the program in non-interactive mode, you’ll need to modify the Dockerfile located in the root of the project.

1. Open the Dockerfile.
2. Update the last argument in the ENTRYPOINT line to the ID of the report you want the program to generate automatically when it runs.

For example, if you want to generate the report
“All the countries in the world organized by largest population to smallest”,
which has an ID of 1 in the reports table of the database,
your ENTRYPOINT should look like this:
```Dockerfile
ENTRYPOINT ["java", "-jar", "reportHub-0.1.0.0-jar-with-dependencies.jar", "1"]
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
java -jar /tmp/reportHub-0.1.0.0-jar-with-dependencies.jar
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

### The scrum team - Group 16
- Steve Castillo - Belize - 40773008@live.napier.ac.uk
- Danny Itza - Belize - 40735791@live.napier.ac.uk
- Mark Otuya - Nigeria - 40796688@live.napier.ac.uk
- Saoud Mohammed Masoud Dhafer Al Ahbabi - United Kingdom - 40793305@live.napier.ac.uk



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
| 6 | The top N populated countries in a region where N is provided by the user. | Yes | ✅ Complete |
| 7 | All the cities in the world organised by largest population to smallest. | Yes | ![Report 7](screenshots/report-52/Screenshot%202025-10-15%20at%2012.20.36.png) |
| 8 | All the cities in a continent organised by largest population to smallest. | Yes | ![Report 8](screenshots/report-53/Screenshot%202025-10-15%20at%2012.25.10.png) |
| 9 | All the cities in a region organised by largest population to smallest. | Yes | ✅ Complete |
| 10 | All the cities in a country organised by largest population to smallest. | Yes | ✅ Complete |
| 11 | All the cities in a district organised by largest population to smallest. | Yes | ✅ Complete |
| 12 | The top N populated cities in the world where N is provided by the user. | Yes | ![Report 12](screenshots/report-54/report-54.png) |
| 13 | The top N populated cities in a continent where N is provided by the user. | Yes | ✅ Complete |
| 14 | The top N populated cities in a region where N is provided by the user. | Yes | ✅ Complete |
| 15 | The top N populated cities in a country where N is provided by the user. | Yes | ✅ Complete |
| 16 | The top N populated cities in a district where N is provided by the user. | Yes | ✅ Complete |
| 17 | All the capital cities in the world organised by largest population to smallest. | Yes | ![Report 17](screenshots/report-55/Screenshot%202025-10-15%20at%2012.29.14.png) |
| 18 | All the capital cities in a continent organised by largest population to smallest. | Yes | ✅ Complete |
| 19 | All the capital cities in a region organised by largest to smallest. | Yes | ✅ Complete |
| 20 | The top N populated capital cities in the world where N is provided by the user. | Yes | ✅ Complete |
| 21 | The top N populated capital cities in a continent where N is provided by the user. | Yes | ✅ Complete |
| 22 | The top N populated capital cities in a region where N is provided by the user. | Yes | ✅ Complete |
| 23 | The population of people, people living in cities, and people not living in cities in each continent. | Yes | ✅ Complete |
| 24 | The population of people, people living in cities, and people not living in cities in each region. | Yes | ✅ Complete |
| 25 | The population of people, people living in cities, and people not living in cities in each country. | Yes | ✅ Complete |
| 26 | The population of the world. | Yes | ![Report 26](screenshots/report-56/Screenshot%202025-10-15%20at%2012.29.58.png) |
| 27 | The population of a continent. | Yes | ✅ Complete |
| 28 | The population of a region. | Yes | ✅ Complete |
| 29 | The population of a country. | Yes | ✅ Complete |
| 30 | The population of a district. | Yes | ✅ Complete |
| 31 | The population of a city. | Yes | ✅ Complete |
| 32 | The number of people who speak Chinese, English, Hindi, Spanish, and Arabic. | Yes | ![Report 32](screenshots/report-57/Screenshot%202025-10-15%20at%2012.30.49.png) |

**📸 Screenshot Evidence:** Sample screenshots are shown above for demonstration purposes. Complete screenshot evidence for all 32 reports is available in the `screenshots/` folder, organized by report number. Each report folder contains visual proof of successful execution.



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



# gra-service
Golden Raspberry Awards API 

## About

Application gra-service is responsible for:
- Read csv file with nominees and winners of Golden Raspberry Awards and provide API to access this data.

## Built with
[![Java][Java]](https://www.java.com/)
[![Spring][spring-shield]](https://spring.io/)
[![H2 Database][h2-database-shield]](https://www.h2database.com/html/main.html)
[![Gradle][gradle-shield]](https://gradle.org/)

## Getting started

### Prerequisites
* openjdk-21-jdk (Linux and others distributions)
```shell
  sudo add-apt-repository ppa:openjdk-r/ppa
  sudo apt-get update
  sudo apt-get install openjdk-21-jdk
 ```
If you have another versions of Java installed on your OS, execute command below to define Java interpreter default:
```shell
  sudo update-alternatives --config java
```

### Installation
1. Clone
```shell
  git clone git@github.com:KimLopesBraatz/gra-service.git
```
2. Build project
```shell
  ./gradlew clean build
```

## How to run
1. Alter path to read csv file in `src/main/resources/application.properties`:
```properties
    csv.path=[epecify_path_to_read_file]/movielist.csv
 ```
2. Run application
```shell
  ./gradlew bootRun
```
3. Access API at `http://localhost:8080/api/v1/producers/winning-intervals`

4. Access H2 Database at `http://localhost:8080/h2-console/`


## How to contribute
1. Create a Feature Branch (`git checkout -b feature/[your_feat]`)
2. Commit your changes (`git commit -m '[your_feat] Add some awesome feature'`)
3. Push your branch (`git push origin feature/[your_feat]`)
4. Open a Pull Request


[Java]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[spring-shield]: https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white
[gradle-shield]: https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white
[h2-database-shield]: https://img.shields.io/badge/H2-00B2A9?style=for-the-badge&logo=h2database&logoColor=white
# PromptResponseLogger

A Spring Boot application designed to log and track prompt-response interactions. This project provides a robust system for recording, storing, and managing AI prompt queries and their corresponding responses.

## Project Overview

**PromptResponseLogger** is a Java-based web application built with Spring Boot that enables efficient logging and management of prompt-response pairs. This is useful for tracking AI model interactions, debugging, auditing, and analyzing conversation patterns.

### Technology Stack

- **Java 21** - Latest LTS Java version
- **Spring Boot 3.2.2** - Modern Spring framework
- **Spring Web & WebFlux** - Reactive and traditional web support
- **Jackson** - JSON processing and serialization
- **Lombok** - Boilerplate code reduction
- **Maven** - Build and dependency management

## Project Structure

```
PromptResponseLogger/
├── src/                    # Source code directory
├── pom.xml                # Maven configuration
├── .gitignore             # Git ignore rules
└── README.md              # This file
```

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 21** or higher
- **Maven 3.6.0** or higher
- **Git** for version control

## Installation & Setup

### 1. Clone the Repository

```bash
# Clone the repository to your local machine
git clone https://github.com/CyBerForCe-G/PromptResponseLogger.git

# Navigate into the project directory
cd PromptResponseLogger
```

### 2. Build the Project

```bash
# Clean and build the project using Maven
mvn clean install
```

### 3. Run the Application

```bash
# Run the Spring Boot application
mvn spring-boot:run
```

The application will start on `http://localhost:8080` by default.

### 4. Build as Executable JAR

```bash
# Package the application as a JAR file
mvn clean package

# Run the JAR file
java -jar target/PromptResponseLogger-1.0-SNAPSHOT.jar
```

## Project Configuration

The project uses Spring Boot 3.2.2 with the following key configurations:

- **Source/Target Encoding**: Java 21
- **Character Encoding**: UTF-8
- **Parent POM**: spring-boot-starter-parent

### Key Dependencies

| Dependency | Purpose |
|-----------|---------|
| spring-boot-starter-web | RESTful web service support |
| spring-boot-starter-webflux | Reactive web framework |
| jackson-databind | JSON serialization/deserialization |
| lombok | Annotation-based boilerplate generation |

## Usage

Once the application is running, you can interact with it through HTTP endpoints to log and retrieve prompt-response data.

## Development

To contribute to this project:

1. Clone the repository
2. Create a new branch for your feature
3. Make your changes
4. Test your changes
5. Commit and push your branch
6. Create a pull request

## License

This project is provided as-is for educational and development purposes.

## Support

For issues or questions, please open an issue on the [GitHub repository](https://github.com/CyBerForCe-G/PromptResponseLogger/issues).

---

**Last Updated**: February 8, 2026
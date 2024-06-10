# Automated Testing with Selenium WebDriver and Apache POI

## Overview

This project is a Java-based automated testing project that utilizes Selenium WebDriver and Apache POI to automate web testing scenarios. 
This README file provides an overview of the project structure, explains the purpose of each file, and outlines the development workflow.

## Project Structure

- **src/main/java/**: Contains the main Java source files.
  - **Project1.java**: The main test automation script written in Java.
  
- **src/test/java/**: Contains test-related Java source files.
  - *(optional)*: Additional test classes or utilities.

- **data/**: Contains test data files.
  - **data.xlsx**: Excel file containing test data for data-driven testing.

- **screenshots/**: Contains screenshots captured during test execution.

- **pom.xml**: Maven Project Object Model (POM) file for managing dependencies and project configuration.

- **README.md**: This file, providing project information and development process.

## Development Process

1. **Project Setup**: Clone the repository and import the project into your preferred Java IDE.

2. **Dependencies**: Ensure that you have the necessary dependencies installed. Update the `pom.xml` file if additional dependencies are required.

3. **Test Script**: Modify the `Project1.java` file to accommodate your specific testing requirements. This file contains the main test automation script written using Selenium WebDriver and Apache POI for Excel file handling.

4. **Test Data**: Define your test data in the `data.xlsx` file located in the `/data` directory. Ensure that the test data aligns with the test scenarios implemented in the test script.

5. **Execution**: Run the tests using your preferred test framework or runner (e.g., TestNG, JUnit). Observe the output in the console logs and review the captured screenshots in the `/screenshots` directory.

6. **Refinement**: Continuously refine and optimize the test script based on feedback, changes in application behavior, or new testing requirements.

7. **Collaboration**: Collaborate with team members by utilizing version control (e.g., Git) and sharing updates, issues, and pull requests through platforms like GitHub.

8. **Documentation**: Document important decisions, updates, and project insights in the README file and other relevant documentation.

## Files Included

- **src/main/java/Project.java**
- **data/data.xlsx**
- **pom.xml**
- **README.md**

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for any improvements, bug fixes, or new features.

## License

[MIT License](LICENSE)

## Acknowledgements

- [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/)
- [Apache POI](https://poi.apache.org/)
- [Maven](https://maven.apache.org/)

## Author

Natnael Haile

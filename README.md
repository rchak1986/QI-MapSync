# QI-MapSync
This is a Web UI Automation package and involves automated test cases for application: http://www.mapsynq.com/#
This project involves the following:
```Selenium WebDriver```
```Maven```
```TestNG```
```Extent Reporting```

# Project Architecture
1. Based on ```Page Object Model - Page Factory``` architecture
2. Each page/ area/ module corresponds to a different ```java class``` with all web elements defined in it.
3. Each page class contains methods for each user actions like clicking, seting text etc.
4. Each test class instantiates page classes and thereby its elements and calls action methods as per the test scenario flow.
5. Each test class ```inherits``` a super class ```TestBase``` which takes care of browser initialization, reporting, test closure etc.
6. Test Classes use TestNG annotation for test flow control and reporting.

# Project Confirguration, Execution & Report
## Prerequisites
```Java 1.7 or above```
```Eclipse IDE Mars.2```
```Eclipse Plugin TestNG```
```Eclipse Plugin Maven```
```Maven```
## Configuration/ Installation
1. Java
   - Download any version of Java (1.7 or above) and follow installation wizard steps.
   - Add JDK bin path in ```path``` environment variable
   - Add environment variable JAVA_HOME with JDK path
2. Eclipse IDE
   - Download Eclipse IDE (any version would work - preferrably Mars or above) from: https://www.eclipse.org/downloads/
   - Create a Eclipse workspace folder
3. TestNG
   - Go To Eclipse Marketplace (Help-> Eclipse Marketplace) and search for TestNG plugin
   - Follow installation wizard steps to install TestNG plugin
4. Maven Plugin
   - Go To Eclipse Marketplace (Help-> Eclipse Marketplace) and search for Maven plugin
   - Follow installation wizard steps to install Maven plugin
5. Maven
   - Download Maven installer from: https://maven.apache.org/download.cgi
   - Follow installation wizard steps to complete installation
   - Add maven installation folder path into environment path variable
   - Add environment variable ```MAVEN_HOME``` with maven installation folder path
   - Open command prompt and execute command ```mvn -version``` to verify correct installation
## Import Project - QI-MapSync
1. Clone or download the project from the git repo - https://github.com/rchak1986/QI-MapSync.git
2. Unzip the folder
3. Open Eclipse and right click in package explorer window
4. Got to import->Maven->Existing Maven Project
5. Browse the pom.xml file from downloaded location
6. Right click on the project and got to Maven->Update Project

## Execution
Execution can be donw in two ways
1. In Eclipse project, Right click on any of the Suite XML and choose the option Run As TestNG Suite
2. In the project directory, execute ```Executor.bat``` file, it will prompt for any suite xml file name. Provide any valid xml name and hit enter

# Test Scenarios
The complete list of functional test cases can be found at: https://github.com/rchak1986/QI-MapSync/blob/master/mapsync/QITestCases.xlsx

## Test Features
1. Can be executed in parallel
2. Can be executed in any of the following browsers - ```Chrome```, ```Firefox```, ```Internet Explorer```, ```Microsoft Edge```
3. Emailable html report is generated for every execution with screenshots for failed steps
4. Test Environment can be configured from ```RunConfig.properties``` file

## Assumptions
All real-time map data have been considered as valid. Access to Database or API endpoints could make test scenarios more realistic. Scenarios like - Appearance of New Incident or Completion of any Incident can not be validated unless those can be simulated via API invokation or DB manupulation.

## Limitations
1. Execution has not been tested on any platform other than Windows [due to unavailablility of environments]
2. Project does not include mobile browser/ headless browsers [unavailibility of environments - system policy protected]
3. Remote execution (Selenium GRID) is not implemented

## Missing Links
Functional Test Cases Covering:
   - Directions Tab: Functionality seems to be not working or requires more visibility on the behavior
   - Register/ Sign In: Complete End to End cycle for registering a new user and signing in with valid user name and password has not been implemented. Functionality seems to be not working.
   - Right Click on Map Icons: Test Cases covering righ clicking functionalities on any map icons have been excluded from the test suites due to inconsistent behavior. Requires more understanding.
   - Navigating to shortcut links Mobile App, Galactio, GPS Navigation, Legend and Calander Sync are excluded from the suites.
   
 ## Areas of Improvement
 1. Test Reporting: Currently Every Test Class produces individual html files even though they are in same suite. Suite wise reporting to be implemented.
 2. Remote Execution: Selenium Gris/ Sauce Lab integration to be implemented
 3. Mobile/ Headless Browsers: To be implemented
 4. Few methods include hard sleeps (due to random inconsistency of application). Hard sleeps to be avoided.
 5. Custom Exception handling to be implemented.

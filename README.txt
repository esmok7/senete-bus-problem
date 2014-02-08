************************************************************************************************************************
Two implementation are available

1. BusImpl - Similar to second solution given in book.
 In this solution minimum number of context switches needed for 3 riders will be 7 (starting with bus arrival)

2. MultiDoorBusImpl - This implementation requires less minimum number of context switches.
 In this solution minimum number of context switches needed for 3 riders will be 5 (starting with bus arrival).
 This uses more instant variables

Functionality testing is covered with unit testing
************************************************************************************************************************


Dependencies
============
Maven  - 3.0.3
JDK - 1.6 or higher


Build
=====
mvn clean package
m

Run unit test
=============
mvn clean test
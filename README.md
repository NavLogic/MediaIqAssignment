# MediaIqAssignment
MediaIqAssignment reading datasources from various sources like database, api and file and searching for the strings parallely

Contains Modules Mediaiq-aggregator aggregates all the jar's and perpares war
Mediaiq-sources jar contains the implementation specific to DataSources like File, Stream, DataBase

HowToRUN:
Firstime
1. go to mediaiq-parent do gradle build first.
2. go to mediaiq-aggregator and do gradle build
3. in mediaiq-aggregator enter gradle bootrun


Now open browser and copy url : http://localhost:8080/swagger-ui.html#/

select static content: click on searchsources enter keyword like "sherlock" and output will be executed with 3 parallel calls to file, db and api and we get matched search results with list<string> stored inside searchresponse.


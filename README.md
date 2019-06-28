# Interview : Lucidworks

**HTTP Utiltity Application**
----------------------------------

 
 **REQUIREMENTS**
*   Java 1.8
*   The Utility should be a SpringBoot App
*   The app should work from the command line for setting the request params as
    well as other options
*   The app should work through REST API calls the same as the command line and
    follow REST best practices
*   The app should have automatic retries with a configurable policy (number of
    retries, exponential backoff, timeouts)
   **Optional bonus features**
   
*   Add a "debug" option which displays additional information about the request
   (response metadata, request timing, etc)
*  Input validation of params
*  Unit tests for retry strategies and other "units"
*  Integration test that starts an HTTP server and tests the utility
*  Proper use of HTTP status codes
*  Use Gradle as the build tool
--------------------------------------------------------------------------------------------    
**URL ENDPOINTS**

* **URL**

 http://localhost:8080/ user
* **Method:**
  
  The request type

  `GET`
  

   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method get --target http://localhost:8080/user --timeout 10  

* **Data Params**

  * **Code:** 200 <br />
    **Content:** `{ RETURNS A LIST OF URLS for the application }`
----------------------------------
* **URL**

 http://localhost:8080/user/register
* **Method:**
  
  The request type

  `POST`
  
   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method post --data {"email":"xyz@gmail.com", "password":"**********"} --target http://localhost:8080/user/register --timeout 10  

* **Data Params**
 * email
 * password
 * format:  {"email":"xyz@gmail.com", "password":"**********"}
 

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ User registered Succesfully! }`
 
* **Error Response:**

    **Content:** `{ error : " User registration could not be done" }`


----------------------------------------------------------------

* **URL**

   http://localhost:8080/note
* **Method:**
  
  The request type

  `POST`
  

   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method post --data {"title":"TITLE GOES HERE", "content":"CONTENT GOES HERE"} --target http://localhost:8080/user/register --timeout 10  

* **Data Params**
  * title
  * content
  * format: {"title":"TITLE GOES HERE", "content":"CONTENT GOES HERE"}
 

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ Return the created note along with details }`
 
* **Error Response:**

    **Content:** `{ error : " Could not create note" }`

---------------------------------------------------------------------------------------

* **URL**

 http://localhost:8080/note
* **Method:**
  
  The request type

  `GET`
  

   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method get --target http://localhost:8080/note --timeout 10  
 

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ Return the list of notes belonging to the user }`
 
* **Error Response:**

    **Content:** `{ error : "No notes exist for the user" }`

--------------------------------------------------------------------------------

* **URL**

 http://localhost:8080/user/note
* **Method:**
  
  The request type

  `POST`
  

   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method post --data {"title":"TITLE GOES HERE", "content":"CONTENT GOES HERE"} --target http://localhost:8080/user/register --timeout 10  

* **Data Params**
 {"title":"TITLE GOES HERE", "content":"CONTENT GOES HERE"}
 

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ Return the created note along with details }`
 
* **Error Response:**

    **Content:** `{ error : " Could not create note" }`

-------------------------------------------------------------------------------------

* **URL**

 http://localhost:8080/weatherC
* **Method:**
  
  The request type

  `GET`
  

   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method get  --target http://localhost:8080/user/weatherC --timeout 10  

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ Return current weather details }`
 
* **Error Response:**

    **Content:** `{ error : " Some system error occured!" }`
-----------------------------------------------------------------------------------------------

* **URL**
This URL is made to demo @RETRY functionality.
 http://localhost:8080/weather
* **Method:**
  
  The request type

  `GET`
  

   **Optional:**
 
   `--debug`
   `--timeout`
   
* **Sample Call:**

 ./call-http --method get  --target http://localhost:8080/user/weather --timeout 10  

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ Return current weather details }`
 
* **Error Response:**

    **Content:** `{ error : " Some system error occured!" }`
-------------------------------------------------------------------------------------------------------


**Notes:**

*  Shell scripts to run call-http comands can be found in Interview/Scripts directory
*  Shell Scripts to run application as deamon service can be found in Interview/Scripts/DeamonService directory
---------------------------------------------------------------------------------------------------------------------

**SAMPLE EXECUTION for Interactive Mode**
* cd to /Interview/Scripts

* ./call-http --method post --data '{"content":"new note","title":"new note "}' --target  http://localhost:8080/note

--------------------------------------------------------------------------------------------------------------------
**SAMPLE EXECUTION for Deamon Mode**
* cd to /Interview/Scripts/DeamonService

* curl --request POST -H "Content-Type: application/json" --data '{"content":"new note","title":"new note "}' http://localhost:8080/note --connect-timeout 10




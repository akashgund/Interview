# Interview

**Title**
----
 Interview Application

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

  <_If making a post request, what should the body payload look like? URL Params rules apply here too._>


  * **Code:** 200 <br />
    **Content:** `{ RETURNS A LIST OF URLS for the application }`




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
 {"email":"xyz@gmail.com", "password":"**********"}
 

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ User registered Succesfully! }`
 
* **Error Response:**

    **Content:** `{ error : " User registration could not be done" }`





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
 {"title":"TITLE GOES HERE", "content":"CONTENT GOES HERE"}
 

* **Success Response:**
  
 
  * **Code:** 200 <br />
    **Content:** `{ Return the created note along with details }`
 
* **Error Response:**

    **Content:** `{ error : " Could not create note" }`



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





* **Notes:**

  <_This is where all uncertainties, commentary, discussion etc. can go. I recommend timestamping and identifying oneself when leaving comments here._> 

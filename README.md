Name: Segun Kuye

Requirements:
- Eclipse

Instructions:
- Also it's a Maven project
- Navigate to the repository that contain the POM file, the run 'mvn test -Photeltest'

Note:- “browser” and “url” value in “hotel_management_test.xml” file in “src/main/resources” directory can be edited to 'firefox' and 'https://xxx.xxx.xx.xxx:3003

  <parameter name="browser" value="chrome"/>
  <parameter name="url" value="http://localhost:3003"/>



Exploratory Test:

1.) Hotel entry could initially be deleted however deletion became impossible later on
2.) Clicking of a Hotel Entry from the list of entries re-directs user to a screen with “Not Found” exception.
3.) Phone number field accepts alphabetical characters, it shouldn't be (no regex)
4.) No error message indicating problem when trying to log in with an incorrect password.
5.) Anyone can access the entry list even without logging in
6.) Don't know if 'Search' element should be a button – no actual search button – user can only search using the 'Enter' key – not everyone will know that.

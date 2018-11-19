# API-REST-Assure

## Example-1:
Test Description: Test a search with a search term and number of 4 videos parameter.

Base URL: http://api.5min.com/

Base path:* search

Search term: Barack Obama

Parameter: num_of_videos

URL: http://api.5min.com/search/barack%20obama/videos.json?num_of_videos=4

Expected Result: We will receive details of the URL to the video and associated title and description in JSON format.

Verifications/Tests:

1. Verify the HTTP response status returned.
2. Verify the response contained the relevant search term
3. Verify that only 4 video entries were returned
4. Verify that there is no duplicate video
5. Print video title, pubDate & duration

## Example-2:
Test Description: Test a search with an id of video and number of 4 related videos parameter.

Base URL: http://api.5min.com/

Base Path: video

Search term: 519218045

Parameter: num_related_return

URL: http://api.5min.com/video/list/info.json?video_ids=519218045&num_related_return=4

Expected Result: We will receive details of that video and 4 related videos including their title, URL and associated image in JSON format.

Verifications/Tests:

1. Verify the HTTP response status returned.
2. Verify that response was successful and relevant to given video_id
3. Verify that 4 additional video entries were returned along with the given video_id
4. Verify that there is no duplicate in related videos
5. Print video title, permittedDeviceTypes status (for “Tablets”,”Handsets”,”ConnectedDevices”,”Computers”) & time duration

## Solutions
### Strategy:
First, it is very reasonable to use a framework/library which provides us testing an API easily in a short period of time and we chose the Rest-assured library. It is better to write the code with below rules.

Single Responsibility:
– Each test has a single responsibility and includes a single assertion.
– Explicit separation has advantages while we are doing black-box testing.

High Level of Abstraction:
– The logic of a test should be written in a high-level way.
– All details such as sending request, creating request, dealing with IO should be done via utility methods not to do with inline methods.

Re-usability:
Write the class, methods etc. that can be re-used for the testing of other endpoints of API.

Documentation:
Add documentation, comments which describe the tests sufficiently.

Extra Tools:
You can format JSON responses with notepad++’s JSON Viewer plugin.

#### Thus, we can run our tests on command prompt by typing below maven command.

##### mvn test –PallApiTests
# kanboard
Need install
- java
- maven

Run test<br>

use command 'mvn -Pname_profile -Dsystem_parameter=value

<br>Profile parameters<br/>
> * ui
> * api

<br>System parameters<br/>
> * browser_name
> * browser_headless
> * url_project_api
> * kanboard_token

> Example:<br>
> 'mvn test' - run all test<br>
> 'mvn test -Pui' - run ui tests<br>
> 'mvn test -Pui -Dbrowser_name=firefox' - run ui tests in firefox browser
﻿# kanboard
<h3>Need install</h3>
- java
- maven

<h3>Raise locally</h3>
1) kanboard 
  - docker-compose file https://github.com/robot-dreams-code/QA-Automation-2/blob/main/lesson%203%20docker/docker-compose.yml
2) report portal 
  - instruction on linux/mac https://reportportal.io/docs/installation-steps/DeployWithDockerOnLinuxMac/
  - instruction on windows https://reportportal.io/docs/installation-steps/DeployWithDockerOnWindows

<h3>Run test<br></h3>
Your can run test without changing code using system property or profiles in console 
>mvn -Pname_profile -Dsystem_property=value

<h4>Profiles</h4>
- ui
- api

<h4><br>System parameters<br/></h4>
> * browser_name (String) - default 'chrome'
> * browser_headless (boolean) - default 'false'
> * url_project (String) - default 'http://localhost/login'
> * url_project_api (String) - default 'http://localhost:80/jsonrpc.php'
> * kanboard_token (String) - check in your personal kanboard profile Settings->API

> Example:<br>
> 'mvn test' - run all test<br>
> 'mvn test -Pui' - run 'ui' tests<br>
> 'mvn test -Pui -Dbrowser_name=firefox' - run 'ui' tests in 'firefox' browser

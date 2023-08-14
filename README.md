# kanboard
<h3>Need install</h3>

- java
- maven

<h3>Raise locally</h3>

1) kanboard <br />
  &nbsp; docker-compose [file](https://github.com/robot-dreams-code/QA-Automation-2/blob/main/lesson%203%20docker/docker-compose.yml)<br />

2) report portal <br />
 &nbsp; instruction on [linux/mac](https://reportportal.io/docs/installation-steps/DeployWithDockerOnLinuxMac/)<br />
 &nbsp; instruction on [windows](https://reportportal.io/docs/installation-steps/DeployWithDockerOnWindows)

<h3>Settings ReportPortal<br></h3>
After run docker-compose report portal your need login as superadmin<br>
In file <strong>reportportal.properties</strong> set next settings

> * rp.endpoint = "your URL"
> * rp.uuid = "your uuid"(click on user icon, choose 'Profile' you will see configuration)
> * rp.launch = "your name launch"
> * rp.project = "your project name"

<h3>Run test<br></h3>
Your can run test without changing code using system property or profiles in console<br /> 

> mvn -Pname_profile -Dsystem_property=value

If the system parameters are not set, are used parameters from _config.property_<br /> 

<h4>Profiles</h4>

> * ui<br/>
> * api

<h4><br>System parameters<br/></h4>

> * browser_name (String)
> * browser_headless (boolean)
> * url_project (String)
> * url_project_api (String)
> * kanboard_token (String)

Example:<br>

> 'mvn test' - run all test<br>
> 'mvn test -Pui' - run 'ui' tests<br>
> 'mvn test -Pui -Dbrowser_name=firefox' - run 'ui' tests in 'firefox' browser

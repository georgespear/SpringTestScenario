# SpringTestScenario
A sample project to demonstrate the issue http://stackoverflow.com/questions/30757044/autowired-httpservletrequest-in-spring-test-integration-tests/31274725#31274725

Contains simple controller, filter and service to illustrate the issue.
When running a test, the instance of request injected in the service, is different from the one passed to the filter.
That's why ControllerITTestCase fails.
There is a hacky way to make it work, though, as done in HackOfControllerITTestCase.

Would be nice to have Spring support for such cases, though.

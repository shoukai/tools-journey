# Cucumber

出现问题：

````$xslt
Exception in thread "main" java.lang.IncompatibleClassChangeError: Found interface cucumber.api.TestCase, but class was expected
	at org.jetbrains.plugins.cucumber.java.run.CucumberJvm2SMFormatter.handleTestCaseStarted(CucumberJvm2SMFormatter.java:80)
	at org.jetbrains.plugins.cucumber.java.run.CucumberJvm2SMFormatter.access$000(CucumberJvm2SMFormatter.java:17)
	at org.jetbrains.plugins.cucumber.java.run.CucumberJvm2SMFormatter$1.receive(CucumberJvm2SMFormatter.java:32)
	at org.jetbrains.plugins.cucumber.java.run.CucumberJvm2SMFormatter$1.receive(CucumberJvm2SMFormatter.java:30)
	at cucumber.runner.EventBus.send(EventBus.java:28)
	at cucumber.runner.TestCase.run(TestCase.java:37)
	at cucumber.runner.Runner.runPickle(Runner.java:44)
	at cucumber.runtime.Runtime.runFeature(Runtime.java:120)
	at cucumber.runtime.Runtime.run(Runtime.java:106)
	at cucumber.api.cli.Main.run(Main.java:35)
	at cucumber.api.cli.Main.main(Main.java:18)
````

参考： [Incompatible class change error](https://github.com/cucumber/cucumber-jvm/issues/1392)



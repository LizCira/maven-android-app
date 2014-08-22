maven-android-app
=================

Building and research process:

1) Downloaded and installed android-sdk and Eclipse.
2) Got to Hello World with eclipse emulator: learned about logcat and a little about ant.
3) Download and installed maven, and updated plugins
4) Opened new maven project: 

 mvn archetype:generate \
  -DarchetypeArtifactId=android-quickstart \
  -DarchetypeGroupId=de.akquinet.android.archetypes \
  -DarchetypeVersion=1.0.6 \
  -DgroupId=your.company \
  -DartifactId=my-android-application
  
5) Make sure android plugin in POM is correct latest version (mvn versions:display-plugin-updates)
6) Gave up on Eclipse emulator (need to switch to Android Studio, also research Android Emulator GPU futher!)
7) Got to Hello World with only java.
8) Some trouble shooting with Maven errors and google gets "mvn package" to build!
  
To compile and run (from project directory): 

mvn package
android avd (launch emulator of your choice)
adb uninstall your.company
adb install target/my-android-application.apk

Note: "adb logcat" to troubleshoo emulator and make sure it's not crashed, which it likely has.

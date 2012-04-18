Group 8 - Robots Stole My Girlfriend
====================================

Directories
-----------

* doc - Here can all documentation related to the project be found (such as RAD and meeting protocols).
* lib - Folder for external libraries, such as Slick and JDom.
* res - Here can images, audio and similar files be found.
* src - Folder for all code.
* test - Folder for all test classes.

How to Run The Game
-------------------

1. Import the project into Eclipse.
2. Open the file .classpath.
3. Replace the file's content with:

        <?xml version="1.0" encoding="UTF-8"?>
        <classpath>
            <classpathentry kind="src" path="src"/>
            <classpathentry kind="src" path="test"/>
            <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER"/>
            <classpathentry kind="lib" path="lib/slick.jar"/>
            <classpathentry kind="lib" path="lib/lwjgl.jar">
                <attributes>
                    <attribute name="org.eclipse.jdt.launching.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY" value="Robots-Stole-My-Girlfriend/lib/natives/windows"/>
                </attributes>
            </classpathentry>
            <classpathentry kind="lib" path="lib/jdom-1.1.3.jar"/>
            <classpathentry kind="lib" path="lib/jorbis-0.0.15.jar"/>
            <classpathentry kind="lib" path="lib/jogg-0.0.7.jar"/>
            <classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
            <classpathentry kind="output" path="bin"/>
        </classpath>

Change the keyword "windows" to "linux" if you are on a Linux system or to "macosx" if you are on a Mac.
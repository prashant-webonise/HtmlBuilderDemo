# HtmlBuilderDemo

An example implementation of a simple builder-pattern inspired html generator. 

This <b>JAVA</b> library provides apis to do all you need to easily generate HTML text.
This is a Java library demonstrated in an  Android App

<b>Usage</b>

Anywhere in your Java class
```java
HtmlBuilder htmlBuilder = new HtmlBuilder()
                .openHtml()
                .openHead()
                .closeHead()
                .newline()
                .openBody()
                .add("inside body")
                .addLink("This is Sparta!!!", "https://www.stackoverflow.com")
                .addNbsp()
                .beginItalic()
                .addBold("bold and italic")
                .endItalic()
                .beginOrderedList()
                .listItem()
                .add("item 1")
                .listItem()
                .add("item 2")
                .endOrderedList()
                .beginDivWithStyle("color : red")
                .addOnClick("Click me", "alert('I am alert');")
                .endDiv()
                .newline()
                .addImage("https://dummy-url.jpg", "suckerr")
                .addHeading("I AM HEADER", "#AAD")
                .beginTable("style=\"border: 1px solid black;\"")
                .addTableRow(true, "row 1", "row 2", "row 3", "row 4")
                .endTableRow()
                .addTableRow("row 5", "row 6", "row 7", "row 8")
                .endTableRow()
                .endTable()
                .addIcon("https://dummy-url.jpg")
                .closeBody()
                .closeHtml();
String htmlTableString = htmlBuilder.getHtml();
```

<b>Screenshots</b>

Output html on chrome

![Alt text](/screenshots/img.png?raw=true)

<b>Integration</b>

- As a gradle dependency
```groovy
compile 'com.prashant.java:htmlbuilder:1.0.0'
```
- As a maven dependency
```xml
<dependency>
    <groupId>com.prashant.java</groupId>
    <artifactId>htmlbuilder</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
</dependency>
```

<b>Note</b>

This library is a result of the work in one of my other projects. I have created this library 
strictly to the requirements and needs at the time of making. However, I have not made the 
library class or the public apis as final. So one can extend 
```java 
com.prashant.java.htmlbuilder.HtmlBuilder.java 
```
class and add/modify/nullify the library scope and abilities.

Inspired from <a href="https://android.googlesource.com/platform/tools/base/+/master/common/src/main/java/com/android/utils/HtmlBuilder.java">AOSP</a>


<b>License</b>

Apache-2.0 
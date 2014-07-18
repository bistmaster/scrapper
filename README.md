OpenCourseWare Java Library
========

Description
========
This library is intended to get all the Courses available from any University which is a member of 
Open Education Consortium.

<h5>ProviderServiceImpl Class</h5>
> it's a service that fetches content from the OECD API. It can return CourseDetail and CourseList bean. 

```java
ProviderServiceImpl service = new ProviderServiceImpl(13);// 13 is a unique provider from OEC
service.getCourseList(); // returns a List<CourseList>
service.getCourseDetails("hashlink") // returns CourseDetail bean
servvice.getCourseContent("linkUrl") // WIP - gets the contents from the MIT or any university
```

<h5>ProviderRequester Class</h5>
> it's a utility class that communicates to the API.

```java
ProviderRequester provider = new ProviderRequester();
provider.setProvider(13); // sets the Provider id
provider.sendRequest("link_of_api"); // returns a JSON-text String
```

<h5>JSONParserUtil Class</h5>
> another utility class that parse JSON-text format and get the value by attribute key

```java
JSONParserUtil parser = new JSONParserUtil();
parser.getValue("JSONTextFormattedValue", "AttributeKey"); // returns a value 
parser.getCoursesOnJson(JSONArray); // return a json-parsed object of List<CourseList>
```

<h5>Maven Dependency</h5>

- json-simple
- junit


please email me at bethoveen.todino@gmail.com

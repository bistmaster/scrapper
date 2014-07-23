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
service.getCourseCount(); // return a number of courses of an institutions
service.getCoursePages(); // return a number of pages
service.getCourseDescription("linkHash") // returns a description of the course
service.getCourseList(pageNumber); // returns a List<CourseList> with description
service.getCourseDetails("hashlink") // returns CourseDetail bean
servvice.getCourseContent("linkUrl") // WIP - gets the contents from the MIT or any university
```

<h5>ProviderRequester Class</h5>
> it's a utility class that communicates to the API. This class is utilized by ProviderServiceImpl.

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
parser.getCoursesOnJson(JSONArray); // return a Iterator Class
```

<h5>Maven Dependency</h5>

- json-simple
- junit

<h5>External library</h5>

- jaunt-api (www.jaunt-api.com)

please email me at bethoveen.todino@gmail.com

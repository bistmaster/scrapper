package org.mit.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mit.bean.CourseList;
/**
 * This is a utility class that parse the JSON object
 * @author Bethoveen
 *
 */

public class JSONParserUtil {
	/**
	 * 
	 * @param arrays A JSONArray of JSON objects
	 * @return List<CourseList>
	 */
	@SuppressWarnings("rawtypes")
	public List<CourseList> getCoursesOnJson(JSONArray arrays){
		List<CourseList> listCourse = new ArrayList<CourseList>();
		if(arrays == null || arrays.size() == 0){
			throw new RuntimeException("Invalid JSONArray");
		}
		
		Iterator i = arrays.iterator();
		while (i.hasNext()) {
			JSONObject json = (JSONObject) i.next();
			CourseList cl = new CourseList();
			cl.setAuthor((String) json.get("author"));
			cl.setHash((String) json.get("linkhash"));
			cl.setId((Long) json.get("id"));
			cl.setLanguage((String) json.get("language"));
			cl.setTitle((String) json.get("title"));
			listCourse.add(cl);
		}		
		return listCourse;
	}	
	
	/**
	 * 
	 * @param jsonText A Json-format String
	 * @param key	   Get the value of the JSON Attribute provided by a key 
	 * @return Object
	 */
	public Object getValue(String jsonText, String key){
		JSONParser jsonParser = new JSONParser();
		Object value = null;
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonText);
			value =  jsonObject.get(key);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return value;
	}
}

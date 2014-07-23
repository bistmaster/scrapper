package org.mit.util;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	public Iterator getCoursesOnJson(JSONArray arrays){
		if(arrays == null || arrays.size() == 0){
			throw new RuntimeException("Provider not found, please supply a correct provider ID");
		}	
		return arrays.iterator();
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

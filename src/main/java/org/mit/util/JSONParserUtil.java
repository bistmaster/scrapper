package org.mit.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.mit.bean.CourseList;


public class JSONParserUtil {

	@SuppressWarnings("rawtypes")
	public List<CourseList> getArrayValues(JSONArray arrays){
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
			cl.setId(Integer.parseInt((String) json.get("id")));
			cl.setLanguage((String) json.get("language"));
			cl.setTitle((String) json.get("title"));
			listCourse.add(cl);
		}		
		return listCourse;
	}	  
	
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

package org.mit.util;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONParserUtil {

	@SuppressWarnings("rawtypes")
	public String getArrayValue(JSONArray arrays){
		if(arrays == null || arrays.size() == 0){
			throw new RuntimeException("Invalid JSONArray");
		}
		
		Iterator i = arrays.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			System.out.println("Title:" + innerObj.get("title") + ", Author :" + innerObj.get("author"));
		}		
		return"";
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

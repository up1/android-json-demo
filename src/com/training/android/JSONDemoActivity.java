package com.training.android;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class JSONDemoActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/** Hashmap for ListView **/
		ArrayList<HashMap<String, String>> reportList = new ArrayList<HashMap<String, String>>();

		/** Parser JSON data **/
		String reports = " "
				+ "{"
				+ "   \"reports\": ["
				+ "       {"
				+ "               \"id\": \"1\","
				+ "               \"name\": \"Report 1\","
				+ "               \"description\": \"Report 1 description\"        "
				+ "       },"
				+ "       {"
				+ "               \"id\": \"2\","
				+ "               \"name\": \"Report 2\","
				+ "               \"description\": \"Report 2 description\"    "
				+ "       }" + " ]" + "}		";
		try {
			JSONObject json = new JSONObject( reports );
//			JSONObject json = new JSONObject( DataUtil.getDataFromURL() );
			JSONArray jsonArray = json.getJSONArray("reports");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String id = jsonObject.getString("id");
				String name = jsonObject.getString("name");
				String description = jsonObject.getString("description");
				
				/** Add data to hashmap **/
				HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", name);
                reportList.add(map);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/** Update data in ListView **/
		ListAdapter adapter = new SimpleAdapter(this, reportList,
				R.layout.list_report, 
				new String[] { "name" },
				new int[] { R.id.name, });
		setListAdapter(adapter);

	}
}
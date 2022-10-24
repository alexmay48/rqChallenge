package com.example.rqchallenge.utils;

import java.util.ArrayList;
import java.util.Map;

public class Utils {

	public static String convertToUriParameters(Map<String, Object> employeeInput) {

		ArrayList<String> entries = new ArrayList<String>();

		for (Map.Entry<String, Object> entry : employeeInput.entrySet()) {
			entries.add(entry.getKey() + "=" + entry.getValue().toString().replace(' ', '+'));
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < entries.size(); i++) {
			sb.append(entries.get(i));
			if (i < entries.size() - 1) {
				sb.append("&");
			}
		}

		return sb.toString();
	}

	public static String convertSpaceToPlus(String str) {

		return str.replace(' ', '+');
	}

}

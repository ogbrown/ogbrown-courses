package com.ogbrown.utility.text;

import java.util.Collection;

public class CsvTextUtility {

	public static String commaSeparateString(Collection<String> c) {
		StringBuilder result = new StringBuilder();
		if (c != null) {
			for(String s : c) {
			    if (result.length() > 0) {
			        result.append(",");
			    }
				result.append(s);
			}
		}
		return result.toString();
	}
}

/**
 * Utils
 *
 * Copyright (C) 2015 qlecler
 *
 * This file is part of LrShopCatalog.
 *
 * LrShopCatalog is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * LrShopCatalog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LrShopCatalog; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package org.lrshopcatalog.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manage a {@link Utils}
 * @author qlecler
 *
 */
public class Utils {
	public static String CATALOG = "<img width=\"175\" height=\"95\" alt=\".*.\"";
	public static String PRODUCT = "<img title=\".*\"";

	/**
	 * Return a list of matches for a specified regex, search and pattern
	 * @param regex
	 * @param search
	 * @param p
	 * @return
	 */
	public static List<String> getMatcher(String regex, String search, Integer p) {
		ArrayList<String> matches = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regex, p);
		Matcher matcher = pattern.matcher(search);
		while (matcher.find())
			matches.add(matcher.group());
		return matches;
	}

	/**
	 * Return the HTML code from a specified fileName
	 * @param fileName
	 * @return
	 */
	public static String getCode(String fileName) {
		String code = "";
		try {
			FileReader input = new FileReader(fileName);
			BufferedReader bufRead = new BufferedReader(input);
			String inputLine;
	        while ((inputLine = bufRead.readLine()) != null)
	        	code = code + "\n" + inputLine;
	        bufRead.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
			System.exit(1);
		}
		return code;
	}
}
/**
 * Catalog
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

package org.lrshopcatalog.main;

/**
 * Manage a {@link Catalog}
 * @author qlecler
 *
 */
public class Catalog {
	private String category;
	private String picture;

	/**
	 * Create a new {@link Catalog} instance
	 */
	public Catalog() {
		this(null, null);
	}
	
	/**
	 * Create a new {@link Catalog} instance
	 * @param category
	 */
	public Catalog(String category) {
		this(category, null);
	}

	/**
	 * Create a new {@link Catalog} instance
	 * @param category
	 * @param picture
	 */
	public Catalog(String category, String picture) {
		this.setCategory(category);
		this.setPicture(picture);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
/**
 * Product
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
 * Manage a {@link Product}
 * @author qlecler
 *
 */
public class Product {
	private String name;
	private String picture;
	private String pictureBig;

	/**
	 * Create a new {@link Product} instance
	 */
	public Product() {
		this(null, null);
	}

	/**
	 * Create a new {@link Product} instance
	 * @param name
	 */
	public Product(String name) {
		this(name, null);
	}

	/**
	 * Create a new {@link Product} instance
	 * @param name
	 * @param picture
	 */
	public Product(String name, String picture) {
		this(name, picture, null);
	}

	/**
	 * Create a new {@link Product} instance
	 * @param name
	 * @param picture
	 * @param pictureBig
	 */
	public Product(String name, String picture, String pictureBig) {
		this.setName(name);
		this.setPicture(picture);
		this.setPictureBig(pictureBig);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPictureBig() {
		return pictureBig;
	}

	public void setPictureBig(String pictureBig) {
		this.pictureBig = pictureBig;
	}
}
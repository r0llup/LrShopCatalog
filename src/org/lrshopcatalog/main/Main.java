/**
 * Main
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.lrshopcatalog.utils.PropertiesLauncher;
import org.lrshopcatalog.utils.Utils;

/**
 * Manage a {@link Main}
 * @author qlecler
 *
 */
public class Main {
	private PropertiesLauncher propertiesLauncher;

	/**
	 * Create a new {@link Main} instance
	 */
	public Main() {
		this.setPropertiesLauncher(new PropertiesLauncher("LrShopCatalog.properties"));
	}

	public PropertiesLauncher getPropertiesLauncher() {
		return propertiesLauncher;
	}

	public void setPropertiesLauncher(PropertiesLauncher propertiesLauncher) {
		this.propertiesLauncher = propertiesLauncher;
	}

	public Properties getProperties() {
		return this.getPropertiesLauncher().getProperties();
	}

	public String getBeautyCatalogFileName() {
		return this.getProperties().getProperty("BeautyCatalogFileName");
	}

	public String getBeautyCatalogProductFileName() {
		return this.getProperties().getProperty("BeautyCatalogProductFileName");
	}

	public String getHealthCatalogFileName() {
		return this.getProperties().getProperty("HealthCatalogFileName");
	}

	public String getHealthCatalogProductFileName() {
		return this.getProperties().getProperty("HealthCatalogProductFileName");
	}

	public String getLrShopCatalogStatic() {
		return this.getProperties().getProperty("LrShopCatalogStatic");
	}

	public String getLrShopProductStatic() {
		return this.getProperties().getProperty("LrShopProductStatic");
	}

	public String getCsvDefaultLocation() {
		return this.getProperties().getProperty("CsvDefaultLocation");
	}
	
	public String getCsvDefaultBeautyCatalogLocation() {
		return this.getProperties().getProperty("CsvDefaultBeautyCatalogLocation");
	}
	
	public String getCsvDefaultHealthCatalogLocation() {
		return this.getProperties().getProperty("CsvDefaultHealthCatalogLocation");
	}

	/**
	 * Parse a Catalog
	 * @param catalogFileName
	 * @return
	 */
	public List<Catalog> parseCatalog(String catalogFileName) {
		List<Catalog> parsedCatalog = new ArrayList<Catalog>();
		List<String> catalog = Utils.getMatcher(Utils.CATALOG,
				Utils.getCode(catalogFileName), Pattern.MULTILINE);
		for (Integer index=0; index < catalog.size(); index++) {
			String c = catalog.get(index);
			String category = c.substring(c.indexOf(
					"<img width=\"175\" height=\"95\" alt=\""),
					c.indexOf("\" title=")).substring(34);
			String picture = this.getLrShopCatalogStatic() +
					c.substring(c.indexOf("src=\""),
					c.lastIndexOf("\"")).substring(16);
			parsedCatalog.add(new Catalog(category, picture));
		}
		return parsedCatalog;
	}

	/**
	 * Parse a Product
	 * @param productFileName
	 * @return
	 */
	public List<Product> parseProduct(String productFileName) {
		List<Product> parsedProduct = new ArrayList<Product>();
		List<String> product = Utils.getMatcher(Utils.PRODUCT,
				Utils.getCode(productFileName), Pattern.MULTILINE);
		for (Integer index=0; index < product.size(); index++) {
			String p = product.get(index);
			String name = p.substring(p.indexOf("<img title=\""),
					p.indexOf("\" alt=")).substring(12);
			String picture = p.substring(p.indexOf("src=\""),
					p.lastIndexOf("\"")).substring(22);
			String pictureBig = picture.substring(0,
					picture.indexOf("_")) + "_lens.jpg";
			picture = this.getLrShopProductStatic() + picture;
			pictureBig = this.getLrShopProductStatic() + pictureBig;
			parsedProduct.add(new Product(name, picture, pictureBig));
		}
		return parsedProduct;
	}

	/**
	 * Retrieve products from a catalog
	 * @param catalogFileName
	 * @return
	 */
	public List<String> getProducts(String catalogFileName) {
		List<String> products = new ArrayList<String>();
		File catalogFolder = new File(catalogFileName);
		File productList[] = catalogFolder.listFiles();
		for (Integer index=0; index < productList.length; index++) {
			File product = productList[index];
		    if (product.getName().contains(".html")) {
		    	products.add(product.getName());
		    }
		}
		return products;
	}

	public static void main(String[] args) {
		Main main = new Main();
		List<Catalog> bc = new ArrayList<Catalog>(main.parseCatalog(
				main.getBeautyCatalogFileName()));
		try {
			File f = new File(main.getCsvDefaultLocation() +
					"BeautyCatalogCategories.csv");
			if(f.exists() && !f.isDirectory()) {
				f.delete();
			}
			FileWriter fileWriter = new FileWriter(main.getCsvDefaultLocation() + 
					"BeautyCatalogCategories.csv");
			fileWriter.append("Category,Picture\n");
			for (Integer index=0; index < bc.size(); index++) {
				fileWriter.append(bc.get(index).getCategory() + "," +
						bc.get(index).getPicture()+"\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
			System.exit(1);
		}
		List<String> pl = new ArrayList<String>(main.getProducts(
				main.getBeautyCatalogProductFileName()));
		for (Integer index=0; index < pl.size(); index++) {
			String pLabel = pl.get(index);
			pLabel = pLabel.substring(0, pLabel.indexOf(".html"));
			File f = new File(main.getCsvDefaultBeautyCatalogLocation() +
					pLabel + ".csv");
			if(f.exists() && !f.isDirectory()) {
				f.delete();
			}
			try {
				FileWriter fileWriter = new FileWriter(main.getCsvDefaultBeautyCatalogLocation() + 
						pLabel + ".csv");
				List<Product> p = new ArrayList<Product>(main.parseProduct(
						main.getBeautyCatalogProductFileName() + pl.get(index)));
				fileWriter.append("Name,Picture,PictureBig\n");
				for (Integer i=0; i < p.size(); i++) {
					fileWriter.append(p.get(i).getName() + "," +
							p.get(i).getPicture() + "," + p.get(i).getPictureBig() + "\n");
				}
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException ex) {
				System.out.println("Error: " + ex.getLocalizedMessage());
				System.exit(1);
			}
		}
		List<Catalog> hc = new ArrayList<Catalog>(main.parseCatalog(
				main.getHealthCatalogFileName()));
		try {
			File f = new File(main.getCsvDefaultLocation() +
					"HealthCatalogCategories.csv");
			if(f.exists() && !f.isDirectory()) {
				f.delete();
			}
			 FileWriter fileWriter = new FileWriter(main.getCsvDefaultLocation() + 
					"HealthCatalogCategories.csv");
			fileWriter.append("Category,Picture\n");
			for (Integer index=0; index < hc.size(); index++) {
				fileWriter.append(hc.get(index).getCategory() + "," +
						hc.get(index).getPicture() + "\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
			System.exit(1);
		}
		pl = new ArrayList<String>(main.getProducts(
				main.getHealthCatalogProductFileName()));
		for (Integer index=0; index < pl.size(); index++) {
			String pLabel = pl.get(index);
			pLabel = pLabel.substring(0, pLabel.indexOf(".html"));
			File f = new File(main.getCsvDefaultHealthCatalogLocation() +
					pLabel + ".csv");
			if(f.exists() && !f.isDirectory()) {
				f.delete();
			}
			try {
				FileWriter fileWriter = new FileWriter(main.getCsvDefaultHealthCatalogLocation() + 
						pLabel + ".csv");
				List<Product> p = new ArrayList<Product>(main.parseProduct(
						main.getHealthCatalogProductFileName() + pl.get(index)));
				fileWriter.append("Name,Picture,PictureBig\n");
				for (Integer i=0; i < p.size(); i++) {
					fileWriter.append(p.get(i).getName() + "," +
							p.get(i).getPicture() + "," + p.get(i).getPictureBig() + "\n");
				}
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException ex) {
				System.out.println("Error: " + ex.getLocalizedMessage());
				System.exit(1);
			}
		}
	}
}
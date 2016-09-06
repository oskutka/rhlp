package com.redhat.oskutka.rhlp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * Servlet implementation class Rebio
 */
@WebServlet("/Rebio")
public class Rebio extends ParsingRestaurantGetter {
	private static final long serialVersionUID = 3901775532572221827L;

	protected String getUrl() {
		String pdfUrl = "";
		try {
			URL pageUrl = new URL("http://www.rebio.cz/Rebio-Park/Nase-nabidka/gn-ha.folder.aspx");
			BufferedReader is = new BufferedReader(new InputStreamReader((InputStream) pageUrl.getContent(), getCharset()));
			String line;
			while ((line = is.readLine()) != null) {
				if (line.contains("Jídelní lístek Rebio Park")) {
					pdfUrl = "http://www.rebio.cz/Rebio-Park/Nase-nabidka/Jidelni-listek-Rebio-Park/" + line.replaceAll(".*href=\"([^\"]*)\".*", "$1");
					break;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return pdfUrl;
	}

	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Informace o alergenech", "Neděle"};
	}

	protected String getDayOpeningTag() {
		return "";
	}

	protected String getDayClosingTag() {
		return "";
	}
	protected String getFreshMenuHTML() {
		try
		{
			StringBuffer result = new StringBuffer();
			URL url = new URL(getUrl());
			PDFParser parser = new PDFParser((InputStream) url.getContent());
			parser.parse();
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setSortByPosition(true);
			try (PDDocument pdDoc = new PDDocument(parser.getDocument())) {
				String parsedText = stripper.getText(pdDoc);
				boolean wasEmptyLine = true;
				for (String line: parseHTML(parsedText).split("\n")) {
					if (!line.matches("^ *(Saláty, dezerty|Obsahuje Basic menu|Informace o alergenech).*")) {
						line = line.trim();
						if (line.length() > 0 || !wasEmptyLine) {
							result.append(line.trim() + "\n");
						}
						wasEmptyLine = (line.length() == 0);
					}
				}
			}
			return "<pre>" + result.toString() + "</pre>";
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}


package com.redhat.oskutka.rhlp.servlet;

import java.io.IOException;
import java.io.InputStream;
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
		return "http://www.rebio.cz/Rebio-Park/Nase-nabidka/Jidelni-listek-Rebio-Park/2899.file.ashx"; // FIXME get the URL from http://www.rebio.cz/Rebio-Park/Nase-nabidka/gn-ha.folder.aspx
	}

	protected String[] getDays() {
		return new String[]{"Pondělí", "Úterý", "Středa", "Čtvrtek", "Pátek", "Přijímáme objednávky", "Neděle"};
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
			String parsedText = stripper.getText(new PDDocument(parser.getDocument()));
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


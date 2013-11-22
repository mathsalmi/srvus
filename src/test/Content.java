package test;

/**
 * Content
 * TODO: delete this
 * @author Matheus Salmi
 */
public class Content {
	public static String get() {
		String content = "<!DOCTYPE html>"
				+ "<html>"
					+ "<body>"
						+ "<h1>Hello world</h1>"
						+ "<form method=\"get\">"
							+ "<fieldset>"
								+ "<legend>GET</legend>"
								+ "<input type=\"text\" name=\"get\">"
								+ "<input type=\"submit\">"
							+ "</fieldset>"
						+ "</form>"
						+ "<form method=\"post\">"
							+ "<fieldset>"
								+ "<legend>POST</legend>"
								+ "<input type=\"text\" name=\"post\">"
								+ "<input type=\"submit\">"
							+ "</fieldset>"
						+ "</form>"
						+ "<form method=\"post\" enctype=\"multipart/form-data\">"
							+ "<fieldset>"
								+ "<legend>FILE</legend>"
								+ "<input type=\"file\" name=\"file\">"
								+ "<input type=\"submit\">"
							+ "</fieldset>"
						+ "</form>"
					+ "</body>"
				+ "</html>";
		
		return content;
	}
}

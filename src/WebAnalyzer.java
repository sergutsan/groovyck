import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides some utility functions that are 
 * helpful when interacting with web pages, like a 
 * list of links and a list of email addresses present
 * on the page's HTML code. 
 */
public final class WebAnalyzer {
    /**
     * A regular expression to identify email addresses
     */
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /*
     * The URL of the page
     */
    private URL url;

    /*
     * A class representing the HTML of the page (DOM-style)
     */
    private HTMLDocument document;

    /**
     * Builds a new WebAnalyzer for the page identified by the URL provided as a string. 
     *
     * @param url a URL pointing to a web page
     *
     * @throws MalformedURLException if the URL is badly formed
     * @throws BadLocationException if the page contains bad locations (that do not exist)
     * @throws IOException if the page cannot be downloaded for any reason
     */
    public WebAnalyzer(String url) throws MalformedURLException, IOException, BadLocationException {
    	  this.url = new URL(url);
    	  document = getHTMLDocument();
    }
	
    private HTMLDocument getHTMLDocument() throws IOException, BadLocationException {
	  HTMLEditorKit kit = new HTMLEditorKit(); 
	  HTMLDocument document = (HTMLDocument) kit.createDefaultDocument(); 
	  document.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
	  Reader reader = new InputStreamReader(url.openStream()); 
	  kit.read(reader, document, 0);
		
	  return document;		
    }
	
    /**
     * Returns the links from the web page.
     * @return the links from the web page.
     */
    public List<String> getLinks() {
	  List<String> result = new ArrayList<String>();
	  for (HTMLDocument.Iterator iterator = document.getIterator(Tag.A);iterator.isValid();iterator.next()) {
		AttributeSet attributes = iterator.getAttributes();
		String href = (String) attributes.getAttribute(Attribute.HREF);
		href = buildAbsoluteURL(href);
		if(!href.equals("") && !href.startsWith("javascript")) {
		    result.add(href);
		}
	  }
	  return result;
    }

    /**
     * Returns the email addresses from the web page.
     * @return the email addresses from the web page.
     */
    public List<String> getEmails() {
	  List<String> result = new ArrayList<String>();

	  ElementIterator iterator = new ElementIterator(document); 
	  Element element; 
    
	  while((element = iterator.next()) != null) {
		AttributeSet attributes = element.getAttributes();
		Object name = attributes.getAttribute(StyleConstants.NameAttribute);

		if (name instanceof Tag) {
		    StringBuffer buffer = new StringBuffer();
		    int count = element.getElementCount();
        
		    for (int i = 0; i<count; i++) {
			  Element child = element.getElement(i);
			  AttributeSet childAttributes = child.getAttributes();

			  if (childAttributes.getAttribute(StyleConstants.NameAttribute) == Tag.CONTENT) {
				int startOffset = child.getStartOffset();
				int endOffset = child.getEndOffset();
				int length = endOffset - startOffset;
						
				try {
				    buffer.append(document.getText(startOffset, length));
				} catch(BadLocationException e) {
				    e.printStackTrace();
				}
			  }
		    }

		    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		    StringTokenizer tokenizer = new StringTokenizer(buffer.toString());
		
		    while(tokenizer.hasMoreTokens()) {
			  String token = tokenizer.nextToken();
			  Matcher matcher = pattern.matcher(token);
			
			  if(matcher.matches()) {
				result.add(token);
			  }
		    }
		}
	  }
	  return result;
    }
	
    private String buildAbsoluteURL(String href) {
	  URI uri = null;
	  try {
		uri = new URI(href);
	  } catch(URISyntaxException e) {
		System.err.println("URI syntax problem: " + href);
		return "";
	  } catch(Exception e) {
		System.err.println("URI problem: " + href);
		return "";
	  }
	  if (uri.isAbsolute()) {
		return href;
	  }
	  String newUrl = "";
	  newUrl += url.getProtocol()+"://";	
	  newUrl += url.getAuthority();
	  String path = url.getPath();
	  int position = path.lastIndexOf('/')+1;
	  path = path.substring(0,position);
	  if(path.equals("")) {
		newUrl += "/";
	  } else {
		newUrl += path;
	  }
	  return newUrl + href;
    }

    public static void main(String args[]) throws Exception {
	  WebAnalyzer wa = new WebAnalyzer("http://www.bbk.ac.uk");
	  List<String> links = wa.getLinks();
	  List<String> emails = wa.getEmails();
	  for (String link : links) {
		System.out.println("Link: " + link);
	  }
	  for (String email : emails) {
		System.out.println("Email: " + email);
	  }
    }
}

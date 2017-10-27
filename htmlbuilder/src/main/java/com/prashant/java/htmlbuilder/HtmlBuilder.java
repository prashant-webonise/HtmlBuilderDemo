package com.prashant.java.htmlbuilder;

import java.net.URL;

/**
 * @author Prashant Singh
 *         <p>
 *         This class is inspired from ASOP. {@see
 *         <a href="https://android.googlesource.com/platform/tools/base/+/master/common/src/main/java/com/android/utils/HtmlBuilder.java"/>}
 */
@SuppressWarnings("ClassWithTooManyMethods")
public class HtmlBuilder {

    private static final String LT_ENTITY = "&lt;";                           //$NON-NLS-1$
    private static final String AMP_ENTITY = "&amp;";                         //$NON-NLS-1$
    private final StringBuilder mStringBuilder;
    private String mTableDataExtra;

    /**
     * Initialize the object with a non-null instance of {@link StringBuilder}
     *
     * @param stringBuilder non-null instance of {@link StringBuilder}
     */
    public HtmlBuilder(StringBuilder stringBuilder) {
        mStringBuilder = stringBuilder;
    }

    public HtmlBuilder() {
        mStringBuilder = new StringBuilder(100);
    }

    /**
     * Adds the html tag "&lt;html&gt;"
     */
    public HtmlBuilder openHtml() {
        addHtml("<html>");
        return this;
    }

    /**
     * Adds the html tag "&lt;body&gt;"
     */
    public HtmlBuilder openBody() {
        addHtml("<body>");
        return this;
    }

    /**
     * Adds the html tag "&lt;head&gt;"
     */
    public HtmlBuilder openHead() {
        addHtml("<head>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/head&gt;"
     */
    public HtmlBuilder closeHead() {
        addHtml("</head>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/html&gt;"
     */
    public HtmlBuilder closeHtml() {
        addHtml("</html>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/body&gt;"
     */
    public HtmlBuilder closeBody() {
        addHtml("</body>");
        return this;
    }

    /**
     * Adds the supplied {@link String} as is.
     *
     * @param html {@link String} instance, expected to be a valid html string
     */
    public HtmlBuilder addHtml(String html) {
        mStringBuilder.append(html);
        return this;
    }

    /**
     * Adds the HTML non-breaking space "&nbsp;"
     */
    public HtmlBuilder addNbsp() {
        mStringBuilder.append("&nbsp;");
        return this;
    }

    /**
     * Adds multiple HTML non-breaking space "&nbsp;"
     *
     * @param count number of times to be added
     */
    public HtmlBuilder addNbsps(int count) {
        for (int i = 0; i < count; i++) {
            addNbsp();
        }
        return this;
    }

    /**
     * Adds html break-line/new-line tag "&lt;BR/&gt;"
     */
    public HtmlBuilder newline() {
        mStringBuilder.append("<BR/>");
        return this;
    }

    /**
     * Adds html break-line/new-line tag "&lt;BR/&gt;" if not already added.
     */
    public HtmlBuilder newlineIfNecessary() {
        // FIXME: 18/10/17
        if (!endsWith(mStringBuilder, "<BR/>")) {
            mStringBuilder.append("<BR/>");
        }
        return this;
    }

    /**
     * Adds the html "&lt;A/&gt;" to the {@code text} and links it with "href" as {@code url}
     * <p>
     * Example : addLink("Google", "www.google.com")
     *
     * @param text The string that needs to be added to the "&lt;A/&gt;" tag
     * @param url  The "href" value for this anchor tag
     */
    public HtmlBuilder addLink(String text, String url) {
        int begin = 0;
        int length = text.length();
        for (; begin < length; begin++) {
            char c = text.charAt(begin);
            if (Character.isWhitespace(c)) {
                mStringBuilder.append(c);
            } else {
                break;
            }
        }
        mStringBuilder.append("<A HREF=\"");
        mStringBuilder.append(url);
        mStringBuilder.append("\">");
        // FIXME: 18/10/17
        appendXmlTextValue(mStringBuilder, text.trim());
        mStringBuilder.append("</A>");
        int end = length - 1;
        for (; end > begin; end--) {
            char c = text.charAt(begin);
            if (Character.isWhitespace(c)) {
                mStringBuilder.append(c);
            }
        }
        return this;
    }

    /**
     * Adds the text {@code text} and assigns the {@code method} to the onclick of the text.
     * <p>
     * Example : addOnClick("Click me", "alert('I am alert');")
     *
     * @param text   The string to be added.
     * @param method The method to be executed on click of the {@code text}
     */
    public HtmlBuilder addOnClick(String text, String method) {
        int begin = 0;
        int length = text.length();
        for (; begin < length; begin++) {
            char c = text.charAt(begin);
            if (Character.isWhitespace(c)) {
                mStringBuilder.append(c);
            } else {
                break;
            }
        }
        mStringBuilder.append("<A onclick=\"");
        mStringBuilder.append(method);
        mStringBuilder.append("\">");
        // FIXME: 18/10/17
        appendXmlTextValue(mStringBuilder, text.trim());
        mStringBuilder.append("</A>");
        int end = length - 1;
        for (; end > begin; end--) {
            char c = text.charAt(begin);
            if (Character.isWhitespace(c)) {
                mStringBuilder.append(c);
            }
        }
        return this;
    }

    public HtmlBuilder add(String text) {
        //// FIXME: 18/10/17
        appendXmlTextValue(mStringBuilder, text);
        return this;
    }

    /**
     * Returns the html string representation as per the tags added before
     *
     * @return html string form the data added before calling this
     */
    public String getHtml() {
        return mStringBuilder.toString();
    }

    /**
     * Adds the html tag "&lt;B&gt;" for BOLD style
     */
    public HtmlBuilder beginBold() {
        mStringBuilder.append("<B>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/B&gt;" for BOLD style
     */
    public HtmlBuilder endBold() {
        mStringBuilder.append("</B>");
        return this;
    }

    /**
     * Encapsulates the supplied string {@code text} with BOLD style tags
     */
    public HtmlBuilder addBold(String text) {
        beginBold();
        add(text);
        endBold();
        return this;
    }

    /**
     * Adds the html tag "&lt;I&gt;" for ITALIC style
     */
    public HtmlBuilder beginItalic() {
        mStringBuilder.append("<I>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/I&gt;" for ITALIC style
     */
    public HtmlBuilder endItalic() {
        mStringBuilder.append("</I>");
        return this;
    }

    /**
     * Encapsulates the supplied string {@code text} with ITALIC style tags
     */
    public HtmlBuilder addItalic(String text) {
        beginItalic();
        add(text);
        endItalic();
        return this;
    }

    /**
     * Adds the html tag "&lt;div&gt;"
     */
    public HtmlBuilder beginDiv() {
        return beginDivWithClassAndStyle(null, null);
    }

    /**
     * Adds the html tag "&lt;div&gt;" and assigns the {@code className} as the html-class to
     * this div
     *
     * @param className the class to assign to this div
     */
    public HtmlBuilder beginDivWithClass(String className) {
        mStringBuilder.append("<div");
        if (className != null) {
            mStringBuilder.append(" class=\"");
            mStringBuilder.append(className);
            mStringBuilder.append("\"");
        }
        mStringBuilder.append('>');
        return this;
    }

    /**
     * Adds the html tag "&lt;div&gt;" and assigns the {@code className} as the html-class and
     * {@code style} as the html inline-style to this div
     *
     * @param className the class to assign to this div
     * @param style     the style to assign to this div
     */
    public HtmlBuilder beginDivWithClassAndStyle(String className, String style) {
        mStringBuilder.append("<div");
        if (className != null) {
            mStringBuilder.append(" class=\"");
            mStringBuilder.append(className);
            mStringBuilder.append("\"");
        }
        if (style != null) {
            mStringBuilder.append(" style=\"");
            mStringBuilder.append(style);
            mStringBuilder.append("\"");
        }
        mStringBuilder.append('>');
        return this;
    }

    /**
     * Adds the html tag "&lt;div&gt;" and assigns the {@code style} as the html inline-style to
     * this div
     * <p>
     * Example : beginDivWithStyle("color : red")
     *
     * @param style the style to assign to this div
     */
    public HtmlBuilder beginDivWithStyle(String style) {
        mStringBuilder.append("<div");
        if (style != null) {
            mStringBuilder.append(" style=\"");
            mStringBuilder.append(style);
            mStringBuilder.append("\"");
        }
        mStringBuilder.append('>');
        return this;
    }

    /**
     * Adds the html tag "&lt;/div&gt;"
     */
    public HtmlBuilder endDiv() {
        mStringBuilder.append("</div>");
        return this;
    }

    /**
     * Adds the {@code text} with font color as {@code fontColor} to make it look like a bold
     * style header
     *
     * @param text      The text to be display as the header
     * @param fontColor The color for the header text
     */
    public HtmlBuilder addHeading(String text, String fontColor) {
        mStringBuilder.append("<font style=\"font-weight:bold; color:").append(fontColor)
                .append(";\">");
        add(text);
        mStringBuilder.append("</font>");
        return this;
    }

    /**
     * Adds the html tag "&lt;UL&gt;"
     */
    public HtmlBuilder beginUnOrderedList() {
        mStringBuilder.append("<UL>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/UL&gt;"
     */
    public HtmlBuilder endUnOrderedList() {
        mStringBuilder.append("</UL>");
        return this;
    }

    /**
     * Adds the html tag "&lt;OL&gt;"
     */
    public HtmlBuilder beginOrderedList() {
        mStringBuilder.append("<OL>");
        return this;
    }

    /**
     * Adds the html tag "&lt;/OL&gt;"
     */
    public HtmlBuilder endOrderedList() {
        mStringBuilder.append("</OL>");
        return this;
    }

    /**
     * Adds the html tag "&lt;LI&gt;"
     */
    public HtmlBuilder listItem() {
        mStringBuilder.append("<LI>");
        return this;
    }

    /**
     * Adds the html tag "&lt;img&gt;" and assigns the {@code url} as the img-src url and
     * {@code altText} as the img-alt alternate text
     * <p>
     * Example : addImage("https://dummy_url.dummy_file.jpg", "This is alternate text");
     * <p>
     * NOTE : REQUIRES INTERNET CONNECTION
     *
     * @param url     url for the image source (img-src) to fetch the image from.
     * @param altText the alternate text to display
     */
    public HtmlBuilder addImage(String url, String altText) {
        addImage(url, altText, null);
        return this;
    }

    /**
     * Adds the html tag "&lt;img&gt;" and assigns the {@code url} as the img-src url,
     * {@code altText} as the img-alt alternate text, and {@code onClickMethod} to the
     * html-onclick method of the image
     * <p>
     * Example : addImage("https://dummy_url.dummy_file.jpg", "This is alternate text",
     * "alert('alert message')");
     * <p>
     * NOTE : REQUIRES INTERNET CONNECTION
     *
     * @param url           url for the image source (img-src) to fetch the image from.
     * @param altText       the alternate text to display
     * @param onClickMethod The method to be executed on click of the image
     */
    public HtmlBuilder addImage(String url, String altText, String onClickMethod) {
        String link = "";
        try {
            link = new URL(url).toURI().toURL().toExternalForm();
        } catch (Throwable t) {
            // pass
        }
        mStringBuilder.append("<img src='");
        mStringBuilder.append(link);
        mStringBuilder.append("'");

        if (altText != null) {
            mStringBuilder.append(" alt=\"");
            mStringBuilder.append(altText);
            mStringBuilder.append("\"");
        }

        if (onClickMethod != null) {
            mStringBuilder.append(" onclick=");
            mStringBuilder.append(onClickMethod);
        }

        mStringBuilder.append(" />");
        return this;
    }

    /**
     * Adds html "&lt;img&gt;" tag as small icon. Loads the image for the icon from {@code src}
     *
     * @param src The source location of the icon
     */
    public HtmlBuilder addIcon(String src) {
        if (src != null) {
            mStringBuilder.append("<img src='");
            mStringBuilder.append(src);
            mStringBuilder.append("' width=16 height=16 border=0 />");
        }
        return this;
    }

    /**
     * Adds the html "&lt;table&gt;" tag. Also sets the {@code tdExtra} as the meta-data to each
     * of the &lt;td&gt; tags inside this html table.
     * <p>
     * Example : beginTable("style=\"border: 1px solid black;\"");
     *
     * @param tdExtra Extra meta-data to be set to each &lt;td&gt; of this table
     */
    public HtmlBuilder beginTable(String tdExtra) {
        mStringBuilder.append("<table>");
        mTableDataExtra = tdExtra;
        return this;
    }

    /**
     * Adds the html "&lt;table&gt;" tag
     */
    public HtmlBuilder beginTable() {
        return beginTable(null);
    }

    /**
     * Adds the html "&lt;/table&gt;" tag
     */
    public HtmlBuilder endTable() {
        mStringBuilder.append("</table>");
        // clear mTableDataExtra at the end of the table
        mTableDataExtra = null;
        return this;
    }

    /**
     * Adds the html "&lt;tr&gt;" tag
     */
    public HtmlBuilder beginTableRow() {
        mStringBuilder.append("<tr>");
        return this;
    }

    /**
     * Adds the html "&lt;/tr&gt;" tag
     */
    public HtmlBuilder endTableRow() {
        mStringBuilder.append("</tr>");
        return this;
    }

    /**
     * Method adds the {@code columns} as "&lt;td&gt;" of the table and encapsulates them with html
     * "&lt;tr&gt;" tag
     *
     * @param isHeader boolean variable, set true if this row will be as a header, false otherwise
     * @param columns  A varargs of string to be added as "&lt;td&gt;"
     */
    public HtmlBuilder addTableRow(boolean isHeader, String... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        beginTableRow();
        addTableCell(isHeader, columns);
        endTableRow();
        return this;
    }

    /**
     * Method adds the {@code columns} as "&lt;td&gt;" tag of the table.
     *
     * @param isHeader boolean variable, set true if this row will be as a header, false otherwise
     * @param columns  A varargs of string to be added as "&lt;td&gt;"
     */
    public HtmlBuilder addTableCell(boolean isHeader, String... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        String tag = "t" + (isHeader ? 'h' : 'd');
        for (String c : columns) {
            mStringBuilder.append('<');
            mStringBuilder.append(tag);
            if (mTableDataExtra != null) {
                mStringBuilder.append(' ');
                mStringBuilder.append(mTableDataExtra);
            }
            mStringBuilder.append('>');
            mStringBuilder.append(c);
            mStringBuilder.append("</");
            mStringBuilder.append(tag);
            mStringBuilder.append('>');
        }
        return this;
    }

    /**
     * Method adds the {@code columns} as "&lt;td&gt;" of the table and encapsulates them with html
     * "&lt;tr&gt;" tag
     *
     * @param columns A varargs of string to be added as "&lt;td&gt;"
     */
    public HtmlBuilder addTableRow(String... columns) {
        return addTableRow(false, columns);
    }

    /**
     * Method adds the {@code columns} as "&lt;td&gt;" tag of the table.
     *
     * @param columns A varargs of string to be added as "&lt;td&gt;"
     */
    public HtmlBuilder addTableCell(String... columns) {
        return addTableCell(false, columns);
    }

    /**
     * Method returns the {@link StringBuilder} instance with all its contents.
     *
     * @return {@code mStringBuilder}
     */
    public StringBuilder getStringBuilder() {
        return mStringBuilder;
    }

    /**
     * Appends text to the given {@link StringBuilder} and escapes it as required for a
     * DOM text node.
     *
     * @param sb        the string builder
     * @param textValue the text value to be appended and escaped
     */
    protected void appendXmlTextValue(StringBuilder sb, String textValue) {
        for (int i = 0, n = textValue.length(); i < n; i++) {
            char c = textValue.charAt(i);
            if (c == '<') {
                sb.append(LT_ENTITY);
            } else if (c == '&') {
                sb.append(AMP_ENTITY);
            } else {
                sb.append(c);
            }
        }
    }

    /**
     * Returns true if the given sequence ends with the given suffix (case
     * sensitive).
     *
     * @param sequence the character sequence to be checked
     * @param suffix   the suffix to look for
     * @return true if the given sequence ends with the given suffix
     */
    protected boolean endsWith(CharSequence sequence, CharSequence suffix) {
        return endsWith(sequence, sequence.length(), suffix);
    }

    /**
     * Returns true if the given sequence ends at the given offset with the given suffix (case
     * sensitive)
     *
     * @param sequence  the character sequence to be checked
     * @param endOffset the offset at which the sequence is considered to end
     * @param suffix    the suffix to look for
     * @return true if the given sequence ends with the given suffix
     */
    protected boolean endsWith(CharSequence sequence, int endOffset,
                               CharSequence suffix) {
        if (endOffset < suffix.length()) {
            return false;
        }
        for (int i = endOffset - 1, j = suffix.length() - 1; j >= 0; i--, j--) {
            if (sequence.charAt(i) != suffix.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

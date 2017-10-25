package com.prashant.java.htmlbuilder;

import java.net.URL;

/**
 * @author Prashant Singh
 *         <p>
 *         This class is inspired from ASOP. {@see
 *         <a href="https://android.googlesource.com/platform/tools/base/+/master/common/src/main/java/com/android/utils/HtmlBuilder.java"/>}
 */
public class HtmlBuilder {

    private static final String LT_ENTITY = "&lt;";                           //$NON-NLS-1$
    private static final String AMP_ENTITY = "&amp;";                         //$NON-NLS-1$
    private final StringBuilder mStringBuilder;
    private String mTableDataExtra;

    public HtmlBuilder(StringBuilder stringBuilder) {
        mStringBuilder = stringBuilder;
    }

    public HtmlBuilder() {
        mStringBuilder = new StringBuilder(100);
    }

    public HtmlBuilder openHtml() {
        addHtml("<html>");
        return this;
    }

    public HtmlBuilder openBody() {
        addHtml("<body>");
        return this;
    }

    public HtmlBuilder openHead() {
        addHtml("<head>");
        return this;
    }

    public HtmlBuilder closeHead() {
        addHtml("</head>");
        return this;
    }

    public HtmlBuilder closeHtml() {
        addHtml("</html>");
        return this;
    }

    public HtmlBuilder closeBody() {
        addHtml("</body>");
        return this;
    }

    public HtmlBuilder addHtml(String html) {
        mStringBuilder.append(html);
        return this;
    }

    public HtmlBuilder addNbsp() {
        mStringBuilder.append("&nbsp;");
        return this;
    }

    public HtmlBuilder addNbsps(int count) {
        for (int i = 0; i < count; i++) {
            addNbsp();
        }
        return this;
    }

    public HtmlBuilder newline() {
        mStringBuilder.append("<BR/>");
        return this;
    }

    public HtmlBuilder newlineIfNecessary() {
        // FIXME: 18/10/17
        if (!endsWith(mStringBuilder, "<BR/>")) {
            mStringBuilder.append("<BR/>");
        }
        return this;
    }

    public HtmlBuilder addLink(String textBefore, String linkText,
                               String textAfter, String url) {
        if (textBefore != null) {
            add(textBefore);
        }
        addLink(linkText, url);
        if (textAfter != null) {
            add(textAfter);
        }
        return this;
    }

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

    public String getHtml() {
        return mStringBuilder.toString();
    }

    public HtmlBuilder beginBold() {
        mStringBuilder.append("<B>");
        return this;
    }

    public HtmlBuilder endBold() {
        mStringBuilder.append("</B>");
        return this;
    }

    public HtmlBuilder addBold(String text) {
        beginBold();
        add(text);
        endBold();
        return this;
    }

    public HtmlBuilder beginItalic() {
        mStringBuilder.append("<I>");
        return this;
    }

    public HtmlBuilder endItalic() {
        mStringBuilder.append("</I>");
        return this;
    }

    public HtmlBuilder addItalic(String text) {
        beginItalic();
        add(text);
        endItalic();
        return this;
    }

    public HtmlBuilder beginDiv() {
        return beginDivWithClassAndStyle(null, null);
    }

    public HtmlBuilder beginDivWithClass(String cssClass) {
        mStringBuilder.append("<div");
        if (cssClass != null) {
            mStringBuilder.append(" class=\"");
            mStringBuilder.append(cssClass);
            mStringBuilder.append("\"");
        }
        mStringBuilder.append('>');
        return this;
    }

    public HtmlBuilder beginDivWithClassAndStyle(String cssClass, String style) {
        mStringBuilder.append("<div");
        if (cssClass != null) {
            mStringBuilder.append(" class=\"");
            mStringBuilder.append(cssClass);
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

    public HtmlBuilder endDiv() {
        mStringBuilder.append("</div>");
        return this;
    }

    public HtmlBuilder addHeading(String text, String fontColor) {
        mStringBuilder.append("<font style=\"font-weight:bold; color:").append(fontColor)
                .append(";\">");
        add(text);
        mStringBuilder.append("</font>");
        return this;
    }

    public HtmlBuilder beginUnOrderedList() {
        mStringBuilder.append("<UL>");
        return this;
    }

    public HtmlBuilder endUnOrderedList() {
        mStringBuilder.append("</UL>");
        return this;
    }

    public HtmlBuilder beginOrderedList() {
        mStringBuilder.append("<OL>");
        return this;
    }

    public HtmlBuilder endOrderedList() {
        mStringBuilder.append("</OL>");
        return this;
    }

    public HtmlBuilder listItem() {
        mStringBuilder.append("<LI>");
        return this;
    }

    public HtmlBuilder addImage(String url, String altText) {
        addImage(url, altText, null);
        return this;
    }

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

    public HtmlBuilder addIcon(String src) {
        if (src != null) {
            mStringBuilder.append("<img src='");
            mStringBuilder.append(src);
            mStringBuilder.append("' width=16 height=16 border=0 />");
        }
        return this;
    }

    public HtmlBuilder beginTable(String tdExtra) {
        mStringBuilder.append("<table>");
        mTableDataExtra = tdExtra;
        return this;
    }

    public HtmlBuilder beginTable() {
        return beginTable(null);
    }

    public HtmlBuilder endTable() {
        mStringBuilder.append("</table>");
        return this;
    }

    public HtmlBuilder beginTableRow() {
        mStringBuilder.append("<tr>");
        return this;
    }

    public HtmlBuilder endTableRow() {
        mStringBuilder.append("</tr>");
        return this;
    }

    public HtmlBuilder addTableRow(boolean isHeader, String... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        beginTableRow();
        addTableCell(isHeader, columns);
        endTableRow();
        return this;
    }

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

    public HtmlBuilder addTableRow(String... columns) {
        return addTableRow(false, columns);
    }

    public HtmlBuilder addTableCell(String... columns) {
        return addTableCell(false, columns);
    }

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

package com.google.gson;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FormattingStyle {
    public static final FormattingStyle COMPACT = new FormattingStyle("", "", false);
    public final String indent;
    public final String newline;
    public final boolean spaceAfterSeparators;

    static {
        new FormattingStyle("\n", "  ", true);
    }

    private FormattingStyle(String str, String str2, boolean z) {
        if (str.matches("[\r\n]*")) {
            if (str2.matches("[ \t]*")) {
                this.newline = str;
                this.indent = str2;
                this.spaceAfterSeparators = z;
                return;
            }
            throw new IllegalArgumentException("Only combinations of spaces and tabs are allowed in indent.");
        }
        throw new IllegalArgumentException("Only combinations of \\n and \\r are allowed in newline.");
    }
}

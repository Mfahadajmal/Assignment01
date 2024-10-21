package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class KeyValueFormatter implements MetadataKey.KeyValueHandler {
    private static final Set FUNDAMENTAL_TYPES = new HashSet(Arrays.asList(Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class));
    public final StringBuilder out;
    public boolean haveSeenValues = false;
    private final String prefix = "[CONTEXT ";
    public final String suffix = " ]";

    public KeyValueFormatter(StringBuilder sb) {
        this.out = sb;
    }

    private static int nextEscapableChar(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt >= ' ' && charAt != '\"' && charAt != '\\') {
                i++;
            } else {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.common.flogger.MetadataKey.KeyValueHandler
    public final void handle(String str, Object obj) {
        char c = ' ';
        if (this.haveSeenValues) {
            this.out.append(' ');
        } else {
            if (this.out.length() > 0) {
                StringBuilder sb = this.out;
                if (sb.length() > 1000 || this.out.indexOf("\n") != -1) {
                    c = '\n';
                }
                sb.append(c);
            }
            this.out.append(this.prefix);
            this.haveSeenValues = true;
        }
        StringBuilder sb2 = this.out;
        sb2.append(str);
        sb2.append('=');
        if (obj == null) {
            sb2.append(true);
            return;
        }
        if (FUNDAMENTAL_TYPES.contains(obj.getClass())) {
            sb2.append(obj);
            return;
        }
        sb2.append('\"');
        String obj2 = obj.toString();
        int i = 0;
        while (true) {
            int nextEscapableChar = nextEscapableChar(obj2, i);
            if (nextEscapableChar != -1) {
                sb2.append((CharSequence) obj2, i, nextEscapableChar);
                i = nextEscapableChar + 1;
                char charAt = obj2.charAt(nextEscapableChar);
                if (charAt != '\t') {
                    if (charAt != '\n') {
                        if (charAt != '\r') {
                            if (charAt != '\"' && charAt != '\\') {
                                sb2.append((char) 65533);
                            }
                        } else {
                            charAt = 'r';
                        }
                    } else {
                        charAt = 'n';
                    }
                } else {
                    charAt = 't';
                }
                sb2.append("\\");
                sb2.append(charAt);
            } else {
                sb2.append((CharSequence) obj2, i, obj2.length());
                sb2.append('\"');
                return;
            }
        }
    }
}

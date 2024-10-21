package com.google.common.flogger.backend;

import com.google.common.flogger.parser.MessageParser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TemplateContext {
    public final String message;
    public final MessageParser parser;

    public TemplateContext(MessageParser messageParser, String str) {
        messageParser.getClass();
        this.parser = messageParser;
        str.getClass();
        this.message = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof TemplateContext) {
            TemplateContext templateContext = (TemplateContext) obj;
            if (this.parser.equals(templateContext.parser) && this.message.equals(templateContext.message)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.message;
        return str.hashCode() ^ this.parser.hashCode();
    }
}

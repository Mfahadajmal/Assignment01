package com.google.common.flogger.parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MessageParser {
    public abstract void parseImpl(MessageBuilder messageBuilder);

    public abstract void unescape(StringBuilder sb, String str, int i, int i2);
}

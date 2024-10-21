package com.google.common.flogger.parameter;

import com.google.common.flogger.backend.FormatChar;
import com.google.common.flogger.backend.FormatOptions;
import com.google.common.flogger.parser.MessageBuilder;
import j$.util.DesugarCollections;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleParameter extends Parameter {
    public static final Map DEFAULT_PARAMETERS;
    private final FormatChar formatChar;

    static {
        EnumMap enumMap = new EnumMap(FormatChar.class);
        for (FormatChar formatChar : FormatChar.values()) {
            SimpleParameter[] simpleParameterArr = new SimpleParameter[10];
            for (int i = 0; i < 10; i++) {
                simpleParameterArr[i] = new SimpleParameter(i, formatChar, FormatOptions.DEFAULT);
            }
            enumMap.put((EnumMap) formatChar, (FormatChar) simpleParameterArr);
        }
        DEFAULT_PARAMETERS = DesugarCollections.unmodifiableMap(enumMap);
    }

    public SimpleParameter(int i, FormatChar formatChar, FormatOptions formatOptions) {
        super(formatOptions, i);
        formatChar.getClass();
        this.formatChar = formatChar;
        if (formatOptions.isDefault()) {
            return;
        }
        int i2 = formatChar.formatChar;
        i2 = formatOptions.shouldUpperCase() ? i2 & 65503 : i2;
        StringBuilder sb = new StringBuilder("%");
        formatOptions.appendPrintfOptions$ar$ds(sb);
        sb.append((char) i2);
    }

    @Override // com.google.common.flogger.parameter.Parameter
    public final void accept$ar$class_merging$ar$class_merging(MessageBuilder messageBuilder, Object obj) {
        messageBuilder.visit(obj, this.formatChar, this.options);
    }
}

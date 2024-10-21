package com.google.common.flogger.parameter;

import com.google.common.flogger.backend.FormatOptions;
import com.google.common.flogger.backend.MessageUtils;
import com.google.common.flogger.parser.MessageBuilder;
import java.util.Calendar;
import java.util.Date;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DateTimeParameter extends Parameter {
    private final DateTimeFormat format;

    public DateTimeParameter(FormatOptions formatOptions, int i, DateTimeFormat dateTimeFormat) {
        super(formatOptions, i);
        char c;
        this.format = dateTimeFormat;
        StringBuilder sb = new StringBuilder("%");
        formatOptions.appendPrintfOptions$ar$ds(sb);
        if (true != formatOptions.shouldUpperCase()) {
            c = 't';
        } else {
            c = 'T';
        }
        sb.append(c);
        sb.append(dateTimeFormat.formatChar);
    }

    @Override // com.google.common.flogger.parameter.Parameter
    public final void accept$ar$class_merging$ar$class_merging(MessageBuilder messageBuilder, Object obj) {
        char c;
        DateTimeFormat dateTimeFormat = this.format;
        if (!(obj instanceof Date) && !(obj instanceof Calendar) && !(obj instanceof Long)) {
            MessageBuilder.appendInvalid(messageBuilder.out, obj, "%t" + dateTimeFormat.formatChar);
            return;
        }
        FormatOptions formatOptions = this.options;
        StringBuilder sb = new StringBuilder("%");
        formatOptions.appendPrintfOptions$ar$ds(sb);
        if (true != formatOptions.shouldUpperCase()) {
            c = 't';
        } else {
            c = 'T';
        }
        sb.append(c);
        sb.append(dateTimeFormat.formatChar);
        messageBuilder.out.append(String.format(MessageUtils.FORMAT_LOCALE, sb.toString(), obj));
    }
}

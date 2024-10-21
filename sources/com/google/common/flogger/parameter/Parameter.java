package com.google.common.flogger.parameter;

import _COROUTINE._BOUNDARY;
import com.google.common.flogger.backend.FormatOptions;
import com.google.common.flogger.parser.MessageBuilder;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Parameter {
    public final int index;
    public final FormatOptions options;

    /* JADX INFO: Access modifiers changed from: protected */
    public Parameter(FormatOptions formatOptions, int i) {
        if (formatOptions != null) {
            if (i >= 0) {
                this.index = i;
                this.options = formatOptions;
                return;
            }
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "invalid index: "));
        }
        throw new IllegalArgumentException("format options cannot be null");
    }

    public abstract void accept$ar$class_merging$ar$class_merging(MessageBuilder messageBuilder, Object obj);
}

package com.google.common.flogger.parser;

import com.google.common.flogger.backend.MessageUtils;
import com.google.common.flogger.backend.TemplateContext;
import com.google.common.flogger.parameter.Parameter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageBuilder {
    protected final Object[] args;
    private final TemplateContext context;
    private int literalStart;
    public int maxIndex;
    public final StringBuilder out;
    public int pmask;

    public MessageBuilder(TemplateContext templateContext, Object[] objArr, StringBuilder sb) {
        this(templateContext);
        this.literalStart = 0;
        objArr.getClass();
        this.args = objArr;
        this.out = sb;
    }

    public static void appendInvalid(StringBuilder sb, Object obj, String str) {
        sb.append("[INVALID: format=");
        sb.append(str);
        sb.append(", type=");
        sb.append(obj.getClass().getCanonicalName());
        sb.append(", value=");
        sb.append(MessageUtils.safeToString(obj));
        sb.append("]");
    }

    public final void addParameterImpl(int i, int i2, Parameter parameter) {
        getParser().unescape(this.out, getMessage(), this.literalStart, i);
        Object[] objArr = this.args;
        int i3 = parameter.index;
        if (i3 < objArr.length) {
            Object obj = objArr[i3];
            if (obj != null) {
                parameter.accept$ar$class_merging$ar$class_merging(this, obj);
            } else {
                this.out.append("null");
            }
        } else {
            this.out.append("[ERROR: MISSING LOG ARGUMENT]");
        }
        this.literalStart = i2;
    }

    public final /* bridge */ /* synthetic */ Object buildImpl() {
        getParser().unescape(this.out, getMessage(), this.literalStart, getMessage().length());
        return this.out;
    }

    public final String getMessage() {
        return this.context.message;
    }

    public final MessageParser getParser() {
        return this.context.parser;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001f, code lost:
    
        if ((r8 instanceof java.math.BigDecimal) == false) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0038, code lost:
    
        if ((r8 instanceof java.math.BigInteger) == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void visit(java.lang.Object r8, com.google.common.flogger.backend.FormatChar r9, com.google.common.flogger.backend.FormatOptions r10) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.flogger.parser.MessageBuilder.visit(java.lang.Object, com.google.common.flogger.backend.FormatChar, com.google.common.flogger.backend.FormatOptions):void");
    }

    public MessageBuilder(TemplateContext templateContext) {
        this.pmask = 0;
        this.maxIndex = -1;
        templateContext.getClass();
        this.context = templateContext;
    }
}

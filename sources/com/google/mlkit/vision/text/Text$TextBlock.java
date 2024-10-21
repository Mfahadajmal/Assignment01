package com.google.mlkit.vision.text;

import android.graphics.Rect;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.vision.text.aidls.TextBlockParcel;
import com.google.mlkit.vision.text.aidls.TextLineParcel;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Text$TextBlock extends Text$TextBase {
    private final List lines;

    public Text$TextBlock(TextLineParcel textLineParcel) {
        super(textLineParcel.text, textLineParcel.boundingBox, textLineParcel.cornerPoints, textLineParcel.recognizedLanguage);
        this.lines = ContextDataProvider.transform(textLineParcel.textElements, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(6));
    }

    public final synchronized List getElements() {
        return this.lines;
    }

    public final synchronized List getLines() {
        return this.lines;
    }

    public Text$TextBlock(String str, Rect rect, List list, String str2, List list2, byte[] bArr) {
        super(str, rect, list, str2);
        this.lines = list2;
    }

    public Text$TextBlock(TextBlockParcel textBlockParcel) {
        super(textBlockParcel.text, textBlockParcel.boundingBox, textBlockParcel.cornerPoints, textBlockParcel.recognizedLanguage);
        this.lines = ContextDataProvider.transform(textBlockParcel.textLines, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(7));
    }

    public Text$TextBlock(String str, Rect rect, List list, String str2, List list2) {
        super(str, rect, list, str2);
        this.lines = list2;
    }
}

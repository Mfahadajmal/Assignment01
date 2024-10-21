package com.google.mlkit.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.libraries.phenotype.client.stable.ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.vision.text.aidls.TextElementParcel;
import com.google.mlkit.vision.text.aidls.TextSymbolParcel;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Text$TextBase {
    public final Rect boundingBox;
    public final String recognizedLanguage;
    private final String text;

    public Text$TextBase(TextElementParcel textElementParcel) {
        this(textElementParcel.text, textElementParcel.boundingBox, textElementParcel.cornerPoints, textElementParcel.recognizedLanguage);
        float f = textElementParcel.confidence;
        float f2 = textElementParcel.angle;
        List list = textElementParcel.textSymbols;
        ContextDataProvider.transform(list == null ? new ArrayList() : list, new ExperimentTokenDecoratorImpl$$ExternalSyntheticLambda3(5));
    }

    public final String getTextInternal() {
        String str = this.text;
        if (str == null) {
            return "";
        }
        return str;
    }

    public Text$TextBase(String str, Rect rect, List list, String str2, byte[] bArr) {
        this(str, rect, list, str2);
    }

    public Text$TextBase(TextSymbolParcel textSymbolParcel) {
        this(textSymbolParcel.text, textSymbolParcel.boundingBox, textSymbolParcel.cornerPoints, "");
        float f = textSymbolParcel.confidence;
        float f2 = textSymbolParcel.angle;
    }

    public Text$TextBase(String str, Rect rect, List list, String str2) {
        this.text = str;
        this.boundingBox = new Rect(rect);
        Point[] pointArr = new Point[list.size()];
        for (int i = 0; i < list.size(); i++) {
            pointArr[i] = new Point((Point) list.get(i));
        }
        this.recognizedLanguage = str2;
    }
}

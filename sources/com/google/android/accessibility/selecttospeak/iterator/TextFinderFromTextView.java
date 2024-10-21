package com.google.android.accessibility.selecttospeak.iterator;

import android.graphics.Rect;
import android.text.Layout;
import android.widget.TextView;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.vision.text.Text$TextBase;
import com.google.mlkit.vision.text.Text$TextBlock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptyList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextFinderFromTextView implements TextLocationFinder {
    private final Object TextFinderFromTextView$ar$textView;
    private final /* synthetic */ int switching_field;

    public TextFinderFromTextView(TextView textView, int i) {
        this.switching_field = i;
        this.TextFinderFromTextView$ar$textView = textView;
    }

    @Override // com.google.android.accessibility.selecttospeak.iterator.TextLocationFinder
    public final boolean getSupportsTextLocation() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.google.common.collect.RangeMap] */
    @Override // com.google.android.accessibility.selecttospeak.iterator.TextLocationFinder
    public final List getTextLocation(boolean z, int i, int i2, int i3) {
        if (this.switching_field != 0) {
            Range open = Range.open(Integer.valueOf(i2 + i), Integer.valueOf(i + i3));
            boolean equals = open.equals(Range.ALL);
            Object obj = this.TextFinderFromTextView$ar$textView;
            ?? r4 = obj;
            if (!equals) {
                r4 = new TreeRangeMap.SubRangeMap(open);
            }
            Map asMapOfRanges = r4.asMapOfRanges();
            ArrayList arrayList = new ArrayList(asMapOfRanges.size());
            Iterator it = asMapOfRanges.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add((Rect) ((Map.Entry) it.next()).getValue());
            }
            return arrayList;
        }
        Layout layout = ((TextView) this.TextFinderFromTextView$ar$textView).getLayout();
        if (layout == null) {
            LogUtils.e("TextFinderFromTextView", "Unable to retrieve layout. Make sure view is fully inflated.", new Object[0]);
            return EmptyList.INSTANCE;
        }
        int i4 = i3 + i;
        int i5 = i2 + i;
        int primaryHorizontal = ((int) layout.getPrimaryHorizontal(i5)) + ((TextView) this.TextFinderFromTextView$ar$textView).getPaddingLeft();
        int primaryHorizontal2 = (int) layout.getPrimaryHorizontal(i4);
        int lineForOffset = layout.getLineForOffset(i5);
        return OnDeviceLanguageIdentificationLogEvent.listOf(new Rect(primaryHorizontal, layout.getLineTop(lineForOffset), primaryHorizontal2, layout.getLineBottom(lineForOffset)));
    }

    public TextFinderFromTextView(Text$TextBlock text$TextBlock, int i) {
        this.switching_field = i;
        this.TextFinderFromTextView$ar$textView = new TreeRangeMap();
        int i2 = 0;
        for (Text$TextBlock text$TextBlock2 : text$TextBlock.getLines()) {
            int i3 = 0;
            for (Text$TextBase text$TextBase : text$TextBlock2.getElements()) {
                Rect rect = text$TextBase.boundingBox;
                int i4 = i2 + i3;
                int length = text$TextBase.getTextInternal().length() + i4;
                Object obj = this.TextFinderFromTextView$ar$textView;
                Range open = Range.open(Integer.valueOf(i4), Integer.valueOf(length));
                if (!open.isEmpty()) {
                    TreeRangeMap treeRangeMap = (TreeRangeMap) obj;
                    treeRangeMap.remove(open);
                    treeRangeMap.entriesByLowerBound.put(open.lowerBound, new TreeRangeMap.RangeMapEntry(open, rect));
                }
                i3 += text$TextBase.getTextInternal().length() + 1;
            }
            i2 += text$TextBlock2.getTextInternal().length() + 1;
        }
    }
}

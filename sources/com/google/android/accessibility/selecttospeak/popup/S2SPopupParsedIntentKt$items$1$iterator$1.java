package com.google.android.accessibility.selecttospeak.popup;

import _COROUTINE._BOUNDARY;
import android.content.ClipData;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SPopupParsedIntentKt$items$1$iterator$1 implements Iterator, KMappedMarker {
    final /* synthetic */ Object S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items;
    private int current;
    private final /* synthetic */ int switching_field;

    public S2SPopupParsedIntentKt$items$1$iterator$1(Object obj, int i) {
        this.switching_field = i;
        this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.switching_field != 0) {
            if (this.current < ((ViewGroup) this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items).getChildCount()) {
                return true;
            }
            return false;
        }
        if (this.current < ((ClipData) this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items).getItemCount()) {
            if (((ClipData) this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items).getItemAt(this.current) != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        if (this.switching_field != 0) {
            int i = this.current;
            this.current = i + 1;
            View childAt = ((ViewGroup) this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items).getChildAt(i);
            if (childAt != null) {
                return childAt;
            }
            throw new IndexOutOfBoundsException();
        }
        int i2 = this.current;
        this.current = i2 + 1;
        ClipData.Item itemAt = ((ClipData) this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items).getItemAt(i2);
        itemAt.getClass();
        return itemAt;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.switching_field != 0) {
            int i = this.current - 1;
            this.current = i;
            ((ViewGroup) this.S2SPopupParsedIntentKt$items$1$iterator$1$ar$$this_items).removeViewAt(i);
            return;
        }
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}

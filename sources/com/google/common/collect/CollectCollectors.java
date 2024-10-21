package com.google.common.collect;

import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.compositor.EventFeedback$$ExternalSyntheticLambda0;
import j$.util.stream.Collector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectCollectors {
    public static final Collector TO_IMMUTABLE_LIST;
    public static final Collector TO_IMMUTABLE_SET;

    static {
        int i = 2;
        TO_IMMUTABLE_LIST = Collector.CC.of(new EventFeedback$$ExternalSyntheticLambda0(i), new UsbConnectManager$$ExternalSyntheticLambda0(9), new CollectCollectors$$ExternalSyntheticLambda21(0), new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(12), new Collector.Characteristics[0]);
        TO_IMMUTABLE_SET = Collector.CC.of(new EventFeedback$$ExternalSyntheticLambda0(3), new UsbConnectManager$$ExternalSyntheticLambda0(10), new CollectCollectors$$ExternalSyntheticLambda21(i), new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(13), new Collector.Characteristics[0]);
        int i2 = 11;
        Collector.CC.of(new EventFeedback$$ExternalSyntheticLambda0(4), new UsbConnectManager$$ExternalSyntheticLambda0(i2), new CollectCollectors$$ExternalSyntheticLambda21(1), new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(i2), new Collector.Characteristics[0]);
    }
}

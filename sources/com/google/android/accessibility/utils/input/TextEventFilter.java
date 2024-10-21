package com.google.android.accessibility.utils.input;

import android.content.Context;
import com.google.android.accessibility.talkback.VoiceActionMonitor;
import io.grpc.internal.InternalSubchannel;
import java.util.ArrayList;
import java.util.Collection;
import org.chromium.net.impl.CronetLogger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextEventFilter {
    public final Context context;
    public final InternalSubchannel.Index textCursorTracker$ar$class_merging;
    public final CronetLogger.CronetEngineBuilderInitializedInfo textEventHistory$ar$class_merging;
    public VoiceActionMonitor voiceActionDelegate$ar$class_merging;
    public int onScreenKeyboardEcho = 0;
    public int physicalKeyboardEcho = 0;
    public long lastKeyEventTime = -1;
    public final Collection listeners = new ArrayList();

    public TextEventFilter(Context context, InternalSubchannel.Index index, CronetLogger.CronetEngineBuilderInitializedInfo cronetEngineBuilderInitializedInfo) {
        this.context = context;
        this.textCursorTracker$ar$class_merging = index;
        this.textEventHistory$ar$class_merging = cronetEngineBuilderInitializedInfo;
    }

    public final int getKeyboardType$ar$edu(long j) {
        if (j - this.lastKeyEventTime < 100) {
            return 2;
        }
        return 1;
    }
}

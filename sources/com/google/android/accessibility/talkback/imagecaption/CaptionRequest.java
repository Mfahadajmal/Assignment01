package com.google.android.accessibility.talkback.imagecaption;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import j$.time.Duration;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CaptionRequest extends Request {
    public static final Duration CAPTION_TIMEOUT_MS = Duration.ofMillis(10000);
    private final boolean isUserRequested;
    protected final AccessibilityNodeInfoCompat node;
    private final OnErrorListener onErrorListener;
    private final OnFinishListener onFinishListener;
    public final int requestId;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnErrorListener {
        void onError$ar$ds(CaptionRequest captionRequest, int i, boolean z);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnFinishListener {
        void onCaptionFinish(CaptionRequest captionRequest, AccessibilityNode accessibilityNode, Result result, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CaptionRequest(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, OnFinishListener onFinishListener, OnErrorListener onErrorListener, boolean z) {
        super(null, CAPTION_TIMEOUT_MS);
        this.requestId = i;
        this.node = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
        this.onFinishListener = onFinishListener;
        this.onErrorListener = onErrorListener;
        this.isUserRequested = z;
    }

    public final void onCaptionFinish(Result result) {
        setEndTimestamp();
        LogUtils.v("CaptionRequest", "onCaptionFinish() ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalText("name", getClass().getSimpleName()), StringBuilderUtils.optionalInt$ar$ds("time", getDurationMillis()), StringBuilderUtils.optionalSubObj("result", result), StringBuilderUtils.optionalSubObj("node", this.node)))), new Object[0]);
        boolean z = this.isUserRequested;
        this.onFinishListener.onCaptionFinish(this, AccessibilityNode.takeOwnership(this.node), result, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onCaptionStart() {
        LogUtils.v("CaptionRequest", "onCaptionStart() name=\"%s\"", getClass().getSimpleName());
        setStartTimestamp();
    }

    @Override // com.google.android.accessibility.talkback.imagecaption.Request
    public final void onError(int i) {
        setEndTimestamp();
        stopTimeoutRunnable();
        LogUtils.e("CaptionRequest", "onError() ".concat(String.valueOf(StringBuilderUtils.joinFields(StringBuilderUtils.optionalText("name", getClass().getSimpleName()), StringBuilderUtils.optionalText("error", errorName(i))))), new Object[0]);
        AccessibilityNode.takeOwnership(this.node);
        this.onErrorListener.onError$ar$ds(this, i, this.isUserRequested);
    }

    public final String toString() {
        return getClass().getSimpleName() + "= " + StringBuilderUtils.joinFields(StringBuilderUtils.optionalSubObj("node", this.node));
    }
}

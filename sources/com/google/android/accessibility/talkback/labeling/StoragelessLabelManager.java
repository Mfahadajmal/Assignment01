package com.google.android.accessibility.talkback.labeling;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.labeling.LabelsFetchRequest;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.labeling.LabelManager;
import java.util.Collections;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StoragelessLabelManager extends TalkBackLabelManager {
    private final LabelManager.State stateReader = new State(this, 0);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State implements LabelManager.State {
        final /* synthetic */ TalkBackLabelManager StoragelessLabelManager$State$ar$this$0;
        private final /* synthetic */ int switching_field;

        public State(TalkBackLabelManager talkBackLabelManager, int i) {
            this.switching_field = i;
            this.StoragelessLabelManager$State$ar$this$0 = talkBackLabelManager;
        }

        @Override // com.google.android.accessibility.utils.labeling.LabelManager.State
        public final long getLabelIdForNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Label labelForViewIdFromCache;
            if (this.switching_field == 0) {
                return -1L;
            }
            if (accessibilityNodeInfoCompat == null) {
                labelForViewIdFromCache = null;
            } else {
                labelForViewIdFromCache = ((CustomLabelManager) this.StoragelessLabelManager$State$ar$this$0).getLabelForViewIdFromCache(accessibilityNodeInfoCompat.getViewIdResourceName());
            }
            if (labelForViewIdFromCache == null) {
                return -1L;
            }
            return labelForViewIdFromCache.mId;
        }

        @Override // com.google.android.accessibility.utils.labeling.LabelManager.State
        public final boolean needsLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.switching_field != 0) {
                return this.StoragelessLabelManager$State$ar$this$0.needsLabel(accessibilityNodeInfoCompat);
            }
            return this.StoragelessLabelManager$State$ar$this$0.needsLabel(accessibilityNodeInfoCompat);
        }

        @Override // com.google.android.accessibility.utils.labeling.LabelManager.State
        public final boolean supportsLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.switching_field == 0 || !((CustomLabelManager) this.StoragelessLabelManager$State$ar$this$0).canAddLabel(accessibilityNodeInfoCompat) || !needsLabel(accessibilityNodeInfoCompat)) {
                return false;
            }
            return true;
        }
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final boolean canAddLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return false;
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager, com.google.android.accessibility.utils.labeling.LabelManager
    public final Label getLabelForViewIdFromCache(String str) {
        return null;
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final void getLabelsFromDatabase(LabelsFetchRequest.OnLabelsFetchedListener onLabelsFetchedListener) {
        if (onLabelsFetchedListener != null) {
            onLabelsFetchedListener.onLabelsFetched(Collections.emptyList());
        }
    }

    @Override // com.google.android.accessibility.talkback.labeling.TalkBackLabelManager
    public final boolean setLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str) {
        return false;
    }

    @Override // com.google.android.accessibility.utils.labeling.LabelManager
    public final LabelManager.State stateReader() {
        return this.stateReader;
    }
}

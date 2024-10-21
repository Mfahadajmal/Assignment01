package androidx.dynamicanimation.animation;

import android.support.v7.widget.SearchView$SearchAutoComplete;
import androidx.cardview.widget.CardView;
import androidx.collection.SimpleArrayMap;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AnimationHandler {
    private static final ThreadLocal sAnimatorHandler = new ThreadLocal();
    public DurationScaleChangeListener33 mDurationScaleChangeListener$ar$class_merging;
    public final NodeBrailler mScheduler$ar$class_merging$ar$class_merging$ar$class_merging;
    public final SimpleArrayMap mDelayedCallbackStartTime = new SimpleArrayMap();
    public final ArrayList mAnimationCallbacks = new ArrayList();
    public final FloatingActionButton.ShadowDelegateImpl mCallbackDispatcher$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
    public final Runnable mRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 12, null);
    public long mCurrentFrameTime = 0;
    public boolean mListDirty = false;
    public float mDurationScale = 1.0f;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AnimationFrameCallback {
        void doAnimationFrame$ar$ds(long j);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DurationScaleChangeListener33 {
        public Object AnimationHandler$DurationScaleChangeListener33$ar$mListener;
        public final /* synthetic */ Object AnimationHandler$DurationScaleChangeListener33$ar$this$0;

        public DurationScaleChangeListener33(Object obj) {
            this.AnimationHandler$DurationScaleChangeListener33$ar$this$0 = obj;
        }

        public final boolean getPreventCornerOverlap() {
            return ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mPreventCornerOverlap;
        }

        public final boolean getUseCompatPadding() {
            return ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mCompatPadding;
        }

        public final void setShadowPadding(int i, int i2, int i3, int i4) {
            ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mShadowBounds.set(i, i2, i3, i4);
            CardView.access$001((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0, i + ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mContentPadding.left, i2 + ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mContentPadding.top, i3 + ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mContentPadding.right, i4 + ((CardView) this.AnimationHandler$DurationScaleChangeListener33$ar$this$0).mContentPadding.bottom);
        }
    }

    public AnimationHandler(NodeBrailler nodeBrailler) {
        this.mScheduler$ar$class_merging$ar$class_merging$ar$class_merging = nodeBrailler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnimationHandler getInstance() {
        ThreadLocal threadLocal = sAnimatorHandler;
        if (threadLocal.get() == null) {
            threadLocal.set(new AnimationHandler(new NodeBrailler()));
        }
        return (AnimationHandler) threadLocal.get();
    }
}

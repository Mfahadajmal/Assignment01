package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SearchView$SearchAutoComplete extends AppCompatAutoCompleteTextView {
    final Runnable mRunShowSoftInputIfNecessary;
    private int mThreshold;

    /* compiled from: PG */
    /* renamed from: android.support.v7.widget.SearchView$SearchAutoComplete$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Runnable {
        final /* synthetic */ Object SearchView$SearchAutoComplete$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.SearchView$SearchAutoComplete$1$ar$this$0 = obj;
        }

        /* JADX WARN: Code restructure failed: missing block: B:177:0x0331, code lost:
        
            if (r12 != false) goto L160;
         */
        /* JADX WARN: Type inference failed for: r4v16, types: [android.animation.ValueAnimator$DurationScaleChangeListener, java.lang.Object] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 1124
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.SearchView$SearchAutoComplete.AnonymousClass1.run():void");
        }

        public /* synthetic */ AnonymousClass1(Object obj, int i, byte[] bArr) {
            this.switching_field = i;
            this.SearchView$SearchAutoComplete$1$ar$this$0 = obj;
        }
    }

    public SearchView$SearchAutoComplete(Context context) {
        this(context, null);
    }

    @Override // android.widget.AutoCompleteTextView
    public final boolean enoughToFilter() {
        if (this.mThreshold > 0 && !super.enoughToFilter()) {
            return false;
        }
        return true;
    }

    @Override // android.support.v7.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return super.onCreateInputConnection(editorInfo);
    }

    @Override // android.view.View
    protected final void onFinishInflate() {
        int i;
        super.onFinishInflate();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (i2 >= 960 && i3 >= 720 && configuration.orientation == 2) {
            i = 256;
        } else if (i2 < 600) {
            i = 160;
        } else {
            i = 192;
        }
        setMinWidth((int) TypedValue.applyDimension(1, i, displayMetrics));
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    protected final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        throw null;
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            }
            if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    throw null;
                }
            }
            i = 4;
        }
        return super.onKeyPreIme(i, keyEvent);
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
        } else {
            throw null;
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public final void setThreshold(int i) {
        super.setThreshold(i);
        this.mThreshold = i;
    }

    public SearchView$SearchAutoComplete(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.autoCompleteTextViewStyle);
    }

    public SearchView$SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRunShowSoftInputIfNecessary = new AnonymousClass1(this, 0);
        this.mThreshold = getThreshold();
    }

    @Override // android.widget.AutoCompleteTextView
    public final void performCompletion() {
    }

    @Override // android.widget.AutoCompleteTextView
    protected final void replaceText(CharSequence charSequence) {
    }
}

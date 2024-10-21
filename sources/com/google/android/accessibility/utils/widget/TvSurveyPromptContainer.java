package com.google.android.accessibility.utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TvSurveyPromptContainer extends FrameLayout {
    public boolean hasSetFocus;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.widget.TvSurveyPromptContainer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ViewGroup.OnHierarchyChangeListener {
        final /* synthetic */ ViewGroup TvSurveyPromptContainer$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(ViewGroup viewGroup, int i) {
            this.switching_field = i;
            this.TvSurveyPromptContainer$1$ar$this$0 = viewGroup;
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewAdded(View view, final View view2) {
            if (this.switching_field != 0) {
                ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = ((CoordinatorLayout) this.TvSurveyPromptContainer$1$ar$this$0).mOnHierarchyChangeListener;
                if (onHierarchyChangeListener != null) {
                    onHierarchyChangeListener.onChildViewAdded(view, view2);
                    return;
                }
                return;
            }
            TvSurveyPromptContainer tvSurveyPromptContainer = (TvSurveyPromptContainer) this.TvSurveyPromptContainer$1$ar$this$0;
            if (!tvSurveyPromptContainer.hasSetFocus && !tvSurveyPromptContainer.trySettingFocus(view2)) {
                final ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(this) { // from class: com.google.android.accessibility.utils.widget.TvSurveyPromptContainer.1.1
                        final /* synthetic */ AnonymousClass1 this$1;

                        {
                            this.this$1 = this;
                        }

                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public final void onGlobalLayout() {
                            ((TvSurveyPromptContainer) this.this$1.TvSurveyPromptContainer$1$ar$this$0).trySettingFocus(view2);
                            if (((TvSurveyPromptContainer) this.this$1.TvSurveyPromptContainer$1$ar$this$0).hasSetFocus && viewTreeObserver.isAlive()) {
                                viewTreeObserver.removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                }
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewRemoved(View view, View view2) {
            if (this.switching_field != 0) {
                ((CoordinatorLayout) this.TvSurveyPromptContainer$1$ar$this$0).onChildViewsChanged(2);
                ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = ((CoordinatorLayout) this.TvSurveyPromptContainer$1$ar$this$0).mOnHierarchyChangeListener;
                if (onHierarchyChangeListener != null) {
                    onHierarchyChangeListener.onChildViewRemoved(view, view2);
                    return;
                }
                return;
            }
            TvSurveyPromptContainer tvSurveyPromptContainer = (TvSurveyPromptContainer) this.TvSurveyPromptContainer$1$ar$this$0;
            if (tvSurveyPromptContainer.hasSetFocus && tvSurveyPromptContainer.getChildCount() == 0) {
                ((TvSurveyPromptContainer) this.TvSurveyPromptContainer$1$ar$this$0).getRootView().findViewById(R.id.preference_root).setImportantForAccessibility(0);
                ((TvSurveyPromptContainer) this.TvSurveyPromptContainer$1$ar$this$0).getRootView().findViewById(R.id.action_bar_container).setImportantForAccessibility(0);
            }
        }
    }

    public TvSurveyPromptContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.hasSetFocus = false;
        setOnHierarchyChangeListener(new AnonymousClass1(this, 0));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i) {
        View focusSearch = super.focusSearch(view, i);
        if (focusSearch == null) {
            return null;
        }
        for (ViewParent parent = focusSearch.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this) {
                return focusSearch;
            }
        }
        return null;
    }

    public final boolean trySettingFocus(View view) {
        if (!this.hasSetFocus && view.getWidth() != 0) {
            if (view.requestFocus()) {
                this.hasSetFocus = true;
                getRootView().findViewById(R.id.preference_root).setImportantForAccessibility(4);
                getRootView().findViewById(R.id.action_bar_container).setImportantForAccessibility(4);
            }
            return this.hasSetFocus;
        }
        return false;
    }
}

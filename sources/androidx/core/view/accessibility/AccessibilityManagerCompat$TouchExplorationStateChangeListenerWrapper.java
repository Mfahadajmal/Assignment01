package androidx.core.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.textfield.DropdownMenuEndIconDelegate;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper implements AccessibilityManager.TouchExplorationStateChangeListener {
    final RetryingNameResolver.ResolutionResultListener mListener$ar$class_merging$85c1b601_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.mListener$ar$class_merging$85c1b601_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper)) {
            return false;
        }
        return this.mListener$ar$class_merging$85c1b601_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.equals(((AccessibilityManagerCompat$TouchExplorationStateChangeListenerWrapper) obj).mListener$ar$class_merging$85c1b601_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    public final int hashCode() {
        return this.mListener$ar$class_merging$85c1b601_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.hashCode();
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = (DropdownMenuEndIconDelegate) this.mListener$ar$class_merging$85c1b601_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.RetryingNameResolver$ResolutionResultListener$ar$this$0;
        AutoCompleteTextView autoCompleteTextView = dropdownMenuEndIconDelegate.autoCompleteTextView;
        if (autoCompleteTextView != null && !EdgeTreatment.isEditable(autoCompleteTextView)) {
            CheckableImageButton checkableImageButton = dropdownMenuEndIconDelegate.endIconView;
            int i = 1;
            if (true == z) {
                i = 2;
            }
            checkableImageButton.setImportantForAccessibility(i);
        }
    }
}

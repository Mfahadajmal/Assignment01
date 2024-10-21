package com.google.android.material.textfield;

import com.google.android.material.shape.EdgeTreatment;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomEndIconDelegate extends EndIconDelegate {
    public CustomEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void setUp() {
        EdgeTreatment.setIconOnLongClickListener$ar$ds(this.endLayout.endIconView);
    }
}

package com.google.android.material.internal;

import com.google.android.libraries.vision.visionkit.base.FileUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CheckableGroup {
    public final Map checkables = new HashMap();
    public final Set checkedIds = new HashSet();
    public FileUtils onCheckedStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean selectionRequired;
    public boolean singleSelection;

    public final boolean checkInternal(MaterialCheckable materialCheckable) {
        Integer valueOf = Integer.valueOf(materialCheckable.getId());
        if (this.checkedIds.contains(valueOf)) {
            return false;
        }
        Map map = this.checkables;
        int i = -1;
        if (this.singleSelection && !this.checkedIds.isEmpty()) {
            i = ((Integer) this.checkedIds.iterator().next()).intValue();
        }
        MaterialCheckable materialCheckable2 = (MaterialCheckable) map.get(Integer.valueOf(i));
        if (materialCheckable2 != null) {
            uncheckInternal(materialCheckable2, false);
        }
        boolean add = this.checkedIds.add(valueOf);
        if (!materialCheckable.isChecked()) {
            materialCheckable.setChecked(true);
        }
        return add;
    }

    public final void onCheckedStateChanged() {
        if (this.onCheckedStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            new HashSet(this.checkedIds);
        }
    }

    public final boolean uncheckInternal(MaterialCheckable materialCheckable, boolean z) {
        Integer valueOf = Integer.valueOf(materialCheckable.getId());
        if (!this.checkedIds.contains(valueOf)) {
            return false;
        }
        if (z && this.checkedIds.size() == 1 && this.checkedIds.contains(valueOf)) {
            materialCheckable.setChecked(true);
            return false;
        }
        boolean remove = this.checkedIds.remove(valueOf);
        if (materialCheckable.isChecked()) {
            materialCheckable.setChecked(false);
        }
        return remove;
    }
}

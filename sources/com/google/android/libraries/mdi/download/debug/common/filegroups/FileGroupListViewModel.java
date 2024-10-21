package com.google.android.libraries.mdi.download.debug.common.filegroups;

import androidx.lifecycle.ViewModel;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FileGroupListViewModel extends ViewModel {
    public final AtomicReference fileGroupList = new AtomicReference(null);
    public String fileGroupFilter = "";
}

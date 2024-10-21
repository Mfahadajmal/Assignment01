package com.google.android.material.motion;

import android.content.res.Resources;
import android.view.View;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialSideContainerBackHelper extends DrawableUtils$OutlineCompatL {
    public MaterialSideContainerBackHelper(View view, byte[] bArr) {
        super(view);
        Resources resources = view.getResources();
        resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    public MaterialSideContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }
}

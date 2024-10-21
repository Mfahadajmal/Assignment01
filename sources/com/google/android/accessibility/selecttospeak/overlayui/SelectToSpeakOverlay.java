package com.google.android.accessibility.selecttospeak.overlayui;

import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SelectToSpeakOverlay {
    View findViewById(int i);

    void fractionalToPixelCoordinates(float[] fArr, int[] iArr);

    int getMaxWindowX();

    int getMaxWindowY();

    void getPixelCoordinates(int[] iArr);

    void hide();

    void pixelToFractionalCoordinates(int[] iArr, float[] fArr);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setPixelCoordinates(int i, int i2);

    void setWatchOutsideTouch$ar$ds();

    void show();

    void updateScreenBounds();
}

package com.google.android.accessibility.brailleime.input;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.PointF;
import android.support.v7.app.AppCompatDelegateImpl;
import android.util.Size;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import j$.util.List;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleInputPlaneTablet extends BrailleInputPlane {
    public BrailleInputPlaneTablet(Context context, Size size, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, int i, BrailleInputOptions brailleInputOptions, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        super(context, size, hapticPatternParser$$ExternalSyntheticLambda1, i, brailleInputOptions, shadowDelegateImpl);
    }

    private final Swipe getReorientedSwipe(Swipe swipe) {
        if (this.isTableTopMode) {
            return Swipe.createFromMirror(swipe);
        }
        return new Swipe(swipe);
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final List buildDotTargetCenters(Size size) {
        return buildDotTargetCentersLandscape(size);
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final BrailleInputPlaneResult createDotHoldAndSwipe(Swipe swipe, BrailleCharacter brailleCharacter) {
        return BrailleInputPlaneResult.createDotHoldAndDotSwipe(getReorientedSwipe(swipe), brailleCharacter);
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final BrailleInputPlaneResult createSwipe(Swipe swipe) {
        return BrailleInputPlaneResult.createSwipe(getReorientedSwipe(swipe));
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final PointF getCaptionCenterPoint(Size size) {
        PointF pointF = new PointF();
        float f = 2.0f;
        pointF.x = size.getWidth() / 2.0f;
        boolean z = this.isTableTopMode;
        float height = size.getHeight();
        if (true == z) {
            f = 3.0f;
        }
        pointF.y = height / f;
        return pointF;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final int[] getInputViewCaptionTranslate(Size size) {
        return new int[2];
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final int getRotateDegree() {
        return 0;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final List readLayoutPoints(Size size) {
        try {
            Context context = this.context;
            boolean z = this.isTableTopMode;
            int i = this.orientation;
            try {
                String string = BrailleUserPreferences.getSharedPreferences$ar$ds(context).getString(context.getString(AppCompatDelegateImpl.Api21Impl.getTabletCalibrationPreferenceKey(z, i, BrailleUserPreferences.isCurrentActiveInputCodeEightDot(context))), "");
                ArrayList arrayList = new ArrayList();
                if (!string.isEmpty()) {
                    return AppCompatDelegateImpl.Api21Impl.parseLayoutPointsString(string);
                }
                return arrayList;
            } catch (JSONException e) {
                throw new ParseException(e.getMessage(), -1);
            }
        } catch (ParseException e2) {
            _BOUNDARY.e("BrailleInputPlaneTablet", "Read saved dots failed.", e2);
            return new ArrayList();
        }
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final void sortDotCentersByGroup(List list, boolean z) {
        List.EL.sort(list, new BrailleInputPlaneTablet$$ExternalSyntheticLambda1(this, z, 0));
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final void sortDotCentersFirstTime(java.util.List list) {
        List.EL.sort(list, new BrailleInputPlanePhone$$ExternalSyntheticLambda1(this, 2));
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final void writeLayoutPoints(java.util.List list, Size size) {
        try {
            Context context = this.context;
            boolean z = this.isTableTopMode;
            int i = this.orientation;
            try {
                BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putString(context.getString(AppCompatDelegateImpl.Api21Impl.getTabletCalibrationPreferenceKey(z, i, BrailleUserPreferences.isCurrentActiveInputCodeEightDot(context))), AppCompatDelegateImpl.Api21Impl.generateLayoutPointsString(list, i, size)).apply();
            } catch (JSONException e) {
                throw new ParseException(e.getMessage(), -1);
            }
        } catch (ParseException unused) {
            _BOUNDARY.e("BrailleInputPlaneTablet", "Write points failed.");
        }
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final Size getInputViewCaptionScreenSize(Size size) {
        return size;
    }
}

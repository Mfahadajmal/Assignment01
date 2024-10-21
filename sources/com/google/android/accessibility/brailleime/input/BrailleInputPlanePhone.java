package com.google.android.accessibility.brailleime.input;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatDelegateImpl;
import android.util.Size;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import j$.util.List;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleInputPlanePhone extends BrailleInputPlane {
    public BrailleInputPlanePhone(Context context, Size size, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, int i, BrailleInputOptions brailleInputOptions, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        super(context, size, hapticPatternParser$$ExternalSyntheticLambda1, i, brailleInputOptions, shadowDelegateImpl);
    }

    private final Swipe getReorientedSwipe(Swipe swipe) {
        Swipe swipe2;
        Swipe.Direction direction;
        if (this.orientation == 1) {
            Swipe.Direction direction2 = swipe.direction;
            if (direction2 == Swipe.Direction.UP) {
                direction = Swipe.Direction.RIGHT;
            } else {
                Swipe.Direction direction3 = Swipe.Direction.DOWN;
                if (direction2 == direction3) {
                    direction = Swipe.Direction.LEFT;
                } else if (direction2 == Swipe.Direction.LEFT) {
                    direction = Swipe.Direction.UP;
                } else if (direction2 == Swipe.Direction.RIGHT) {
                    direction = direction3;
                } else {
                    throw new IllegalArgumentException("unknown direction ".concat(String.valueOf(String.valueOf(direction2))));
                }
            }
            swipe2 = new Swipe(direction, swipe.touchCount);
        } else {
            swipe2 = new Swipe(swipe);
        }
        if (this.isTableTopMode) {
            return Swipe.createFromMirror(swipe2);
        }
        return swipe2;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final List buildDotTargetCenters(Size size) {
        Size size2;
        if (this.orientation == 1) {
            size2 = new Size(size.getHeight(), size.getWidth());
        } else {
            size2 = size;
        }
        List buildDotTargetCentersLandscape = buildDotTargetCentersLandscape(size2);
        if (this.orientation == 1) {
            for (int i = 0; i < buildDotTargetCentersLandscape.size(); i++) {
                buildDotTargetCentersLandscape.set(i, AppCompatDelegateImpl.Api21Impl.mapLandscapeToPortraitForPhone((PointF) buildDotTargetCentersLandscape.get(i), size2, size));
            }
        }
        return buildDotTargetCentersLandscape;
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
        float height;
        float width;
        PointF pointF = new PointF();
        if (this.orientation == 1) {
            if (this.isTableTopMode) {
                width = size.getWidth() / 3.0f;
            } else {
                width = size.getWidth() / 2.0f;
            }
            pointF.x = width;
            pointF.y = size.getHeight() / 2.0f;
        } else {
            pointF.x = size.getWidth() / 2.0f;
            if (this.isTableTopMode) {
                height = size.getHeight() / 3.0f;
            } else {
                height = size.getHeight() / 2.0f;
            }
            pointF.y = height;
        }
        return pointF;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final Size getInputViewCaptionScreenSize(Size size) {
        if (this.orientation == 1) {
            return new Size(size.getHeight(), size.getWidth());
        }
        return size;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final int[] getInputViewCaptionTranslate(Size size) {
        int[] iArr = new int[2];
        if (this.orientation == 1) {
            iArr[0] = -size.getWidth();
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final int getRotateDegree() {
        if (this.orientation == 1) {
            return 270;
        }
        return 0;
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final List readLayoutPoints(Size size) {
        try {
            Context context = this.context;
            boolean z = this.isTableTopMode;
            int i = this.orientation;
            String string = BrailleUserPreferences.getSharedPreferences$ar$ds(context).getString(context.getString(AppCompatDelegateImpl.Api21Impl.getPhoneCalibrationPreferenceKey(z, BrailleUserPreferences.isCurrentActiveInputCodeEightDot(context))), "");
            try {
                ArrayList arrayList = new ArrayList();
                if (!string.isEmpty()) {
                    int i2 = new JSONObject(string).getInt("orientation");
                    Size size2 = new Size(new JSONObject(string).optInt("screen_width", size.getWidth()), new JSONObject(string).optInt("screen_height", size.getHeight()));
                    List parseLayoutPointsString = AppCompatDelegateImpl.Api21Impl.parseLayoutPointsString(string);
                    for (int i3 = 0; i3 < parseLayoutPointsString.size(); i3++) {
                        PointF pointF = (PointF) parseLayoutPointsString.get(i3);
                        if (i2 == 1) {
                            if (i == 2) {
                                Matrix matrix = new Matrix();
                                matrix.preRotate(90.0f);
                                matrix.postTranslate(size.getWidth(), 0.0f);
                                matrix.preScale(size.getHeight() / size2.getWidth(), size.getWidth() / size2.getHeight());
                                float[] fArr = new float[2];
                                matrix.mapPoints(fArr, 0, new float[]{pointF.x, pointF.y}, 0, 1);
                                pointF = new PointF(fArr[0], fArr[1]);
                            }
                        } else if (i2 == 2 && i == 1) {
                            pointF = AppCompatDelegateImpl.Api21Impl.mapLandscapeToPortraitForPhone(pointF, size2, size);
                            arrayList.add(pointF);
                        }
                        arrayList.add(pointF);
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                throw new ParseException(e.getMessage(), -1);
            }
        } catch (ParseException e2) {
            _BOUNDARY.e("BrailleInputPlanePhone", "Read saved dots failed.", e2);
            return new ArrayList();
        }
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final void sortDotCentersByGroup(List list, boolean z) {
        List.EL.sort(list, new BrailleInputPlaneTablet$$ExternalSyntheticLambda1(this, z, 1));
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final void sortDotCentersFirstTime(java.util.List list) {
        List.EL.sort(list, new BrailleInputPlanePhone$$ExternalSyntheticLambda1(this, 0));
    }

    @Override // com.google.android.accessibility.brailleime.input.BrailleInputPlane
    public final void writeLayoutPoints(java.util.List list, Size size) {
        try {
            Context context = this.context;
            try {
                BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putString(context.getString(AppCompatDelegateImpl.Api21Impl.getPhoneCalibrationPreferenceKey(this.isTableTopMode, BrailleUserPreferences.isCurrentActiveInputCodeEightDot(context))), AppCompatDelegateImpl.Api21Impl.generateLayoutPointsString(list, this.orientation, size)).apply();
            } catch (JSONException e) {
                throw new ParseException(e.getMessage(), -1);
            }
        } catch (ParseException unused) {
            _BOUNDARY.e("BrailleInputPlanePhone", "Write points failed.");
        }
    }
}

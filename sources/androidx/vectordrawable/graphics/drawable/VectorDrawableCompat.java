package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompatBuilder$Api21Impl;
import androidx.core.graphics.PathParser$PathDataNode;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import androidx.transition.TransitionUtils;
import com.google.frameworks.client.data.android.interceptor.AsyncInterceptorsClientCallListener;
import java.util.ArrayList;
import org.chromium.net.PrivateKeyType;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VectorDrawableCompat extends VectorDrawableCommon {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    public boolean mAllowCaching;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    public VectorDrawableCompatState mVectorState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VClipPath extends VPath {
        public VClipPath() {
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPath
        public final boolean isClipPath() {
            return true;
        }

        public VClipPath(VClipPath vClipPath) {
            super(vClipPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VFullPath extends VPath {
        float mFillAlpha;
        AsyncInterceptorsClientCallListener.PendingMessage mFillColor$ar$class_merging$ar$class_merging;
        float mStrokeAlpha;
        AsyncInterceptorsClientCallListener.PendingMessage mStrokeColor$ar$class_merging$ar$class_merging;
        Paint.Cap mStrokeLineCap;
        Paint.Join mStrokeLineJoin;
        float mStrokeMiterlimit;
        float mStrokeWidth;
        public int[] mThemeAttrs;
        float mTrimPathEnd;
        float mTrimPathOffset;
        float mTrimPathStart;

        public VFullPath() {
            this.mStrokeWidth = 0.0f;
            this.mStrokeAlpha = 1.0f;
            this.mFillAlpha = 1.0f;
            this.mTrimPathStart = 0.0f;
            this.mTrimPathEnd = 1.0f;
            this.mTrimPathOffset = 0.0f;
            this.mStrokeLineCap = Paint.Cap.BUTT;
            this.mStrokeLineJoin = Paint.Join.MITER;
            this.mStrokeMiterlimit = 4.0f;
        }

        float getFillAlpha() {
            return this.mFillAlpha;
        }

        int getFillColor() {
            return this.mFillColor$ar$class_merging$ar$class_merging.currentStage;
        }

        float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        int getStrokeColor() {
            return this.mStrokeColor$ar$class_merging$ar$class_merging.currentStage;
        }

        float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        @Override // androidx.transition.TransitionUtils.Api28Impl
        public final boolean isStateful() {
            if (!this.mFillColor$ar$class_merging$ar$class_merging.isStateful() && !this.mStrokeColor$ar$class_merging$ar$class_merging.isStateful()) {
                return false;
            }
            return true;
        }

        @Override // androidx.transition.TransitionUtils.Api28Impl
        public final boolean onStateChanged(int[] iArr) {
            return this.mStrokeColor$ar$class_merging$ar$class_merging.onStateChanged(iArr) | this.mFillColor$ar$class_merging$ar$class_merging.onStateChanged(iArr);
        }

        void setFillAlpha(float f) {
            this.mFillAlpha = f;
        }

        void setFillColor(int i) {
            this.mFillColor$ar$class_merging$ar$class_merging.currentStage = i;
        }

        void setStrokeAlpha(float f) {
            this.mStrokeAlpha = f;
        }

        void setStrokeColor(int i) {
            this.mStrokeColor$ar$class_merging$ar$class_merging.currentStage = i;
        }

        void setStrokeWidth(float f) {
            this.mStrokeWidth = f;
        }

        void setTrimPathEnd(float f) {
            this.mTrimPathEnd = f;
        }

        void setTrimPathOffset(float f) {
            this.mTrimPathOffset = f;
        }

        void setTrimPathStart(float f) {
            this.mTrimPathStart = f;
        }

        public VFullPath(VFullPath vFullPath) {
            super(vFullPath);
            this.mStrokeWidth = 0.0f;
            this.mStrokeAlpha = 1.0f;
            this.mFillAlpha = 1.0f;
            this.mTrimPathStart = 0.0f;
            this.mTrimPathEnd = 1.0f;
            this.mTrimPathOffset = 0.0f;
            this.mStrokeLineCap = Paint.Cap.BUTT;
            this.mStrokeLineJoin = Paint.Join.MITER;
            this.mStrokeMiterlimit = 4.0f;
            int[] iArr = vFullPath.mThemeAttrs;
            this.mThemeAttrs = null;
            this.mStrokeColor$ar$class_merging$ar$class_merging = vFullPath.mStrokeColor$ar$class_merging$ar$class_merging;
            this.mStrokeWidth = vFullPath.mStrokeWidth;
            this.mStrokeAlpha = vFullPath.mStrokeAlpha;
            this.mFillColor$ar$class_merging$ar$class_merging = vFullPath.mFillColor$ar$class_merging$ar$class_merging;
            this.mFillRule = vFullPath.mFillRule;
            this.mFillAlpha = vFullPath.mFillAlpha;
            this.mTrimPathStart = vFullPath.mTrimPathStart;
            this.mTrimPathEnd = vFullPath.mTrimPathEnd;
            this.mTrimPathOffset = vFullPath.mTrimPathOffset;
            this.mStrokeLineCap = vFullPath.mStrokeLineCap;
            this.mStrokeLineJoin = vFullPath.mStrokeLineJoin;
            this.mStrokeMiterlimit = vFullPath.mStrokeMiterlimit;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class VPath extends TransitionUtils.Api28Impl {
        int mChangingConfigurations;
        int mFillRule;
        protected PathParser$PathDataNode[] mNodes;
        String mPathName;

        public VPath() {
            this.mNodes = null;
            this.mFillRule = 0;
        }

        public PathParser$PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public String getPathName() {
            return this.mPathName;
        }

        public boolean isClipPath() {
            return false;
        }

        public void setPathData(PathParser$PathDataNode[] pathParser$PathDataNodeArr) {
            PathParser$PathDataNode[] pathParser$PathDataNodeArr2 = this.mNodes;
            if (pathParser$PathDataNodeArr2 != null && pathParser$PathDataNodeArr != null) {
                if (pathParser$PathDataNodeArr2.length == pathParser$PathDataNodeArr.length) {
                    for (int i = 0; i < pathParser$PathDataNodeArr2.length; i++) {
                        PathParser$PathDataNode pathParser$PathDataNode = pathParser$PathDataNodeArr2[i];
                        char c = pathParser$PathDataNode.mType;
                        PathParser$PathDataNode pathParser$PathDataNode2 = pathParser$PathDataNodeArr[i];
                        if (c == pathParser$PathDataNode2.mType && pathParser$PathDataNode.mParams.length == pathParser$PathDataNode2.mParams.length) {
                        }
                    }
                    PathParser$PathDataNode[] pathParser$PathDataNodeArr3 = this.mNodes;
                    for (int i2 = 0; i2 < pathParser$PathDataNodeArr.length; i2++) {
                        pathParser$PathDataNodeArr3[i2].mType = pathParser$PathDataNodeArr[i2].mType;
                        int i3 = 0;
                        while (true) {
                            float[] fArr = pathParser$PathDataNodeArr[i2].mParams;
                            if (i3 < fArr.length) {
                                pathParser$PathDataNodeArr3[i2].mParams[i3] = fArr[i3];
                                i3++;
                            }
                        }
                    }
                    return;
                }
            }
            this.mNodes = NotificationCompatBuilder$Api21Impl.deepCopyNodes(pathParser$PathDataNodeArr);
        }

        public VPath(VPath vPath) {
            this.mNodes = null;
            this.mFillRule = 0;
            this.mPathName = vPath.mPathName;
            int i = vPath.mChangingConfigurations;
            this.mChangingConfigurations = 0;
            this.mNodes = NotificationCompatBuilder$Api21Impl.deepCopyNodes(vPath.mNodes);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VectorDrawableCompatState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        ColorStateList mCachedTint;
        PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint;
        PorterDuff.Mode mTintMode;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState() {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            this.mVPathRenderer = new VPathRenderer();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public final boolean isStateful() {
            VPathRenderer vPathRenderer = this.mVPathRenderer;
            if (vPathRenderer.mIsStateful == null) {
                vPathRenderer.mIsStateful = Boolean.valueOf(vPathRenderer.mRootGroup.isStateful());
            }
            return vPathRenderer.mIsStateful.booleanValue();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        public final void updateCachedBitmap(int i, int i2) {
            this.mCachedBitmap.eraseColor(0);
            Canvas canvas = new Canvas(this.mCachedBitmap);
            VPathRenderer vPathRenderer = this.mVPathRenderer;
            vPathRenderer.drawGroupTree$ar$ds(vPathRenderer.mRootGroup, VPathRenderer.IDENTITY_MATRIX, canvas, i, i2);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            return new VectorDrawableCompat(this);
        }

        public VectorDrawableCompatState(VectorDrawableCompatState vectorDrawableCompatState) {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if (vectorDrawableCompatState != null) {
                this.mChangingConfigurations = vectorDrawableCompatState.mChangingConfigurations;
                this.mVPathRenderer = new VPathRenderer(vectorDrawableCompatState.mVPathRenderer);
                Paint paint = vectorDrawableCompatState.mVPathRenderer.mFillPaint;
                if (paint != null) {
                    this.mVPathRenderer.mFillPaint = new Paint(paint);
                }
                Paint paint2 = vectorDrawableCompatState.mVPathRenderer.mStrokePaint;
                if (paint2 != null) {
                    this.mVPathRenderer.mStrokePaint = new Paint(paint2);
                }
                this.mTint = vectorDrawableCompatState.mTint;
                this.mTintMode = vectorDrawableCompatState.mTintMode;
                this.mAutoMirrored = vectorDrawableCompatState.mAutoMirrored;
            }
        }
    }

    public VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    static int applyAlpha(int i, float f) {
        return (i & 16777215) | (((int) (Color.alpha(i) * f)) << 24);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            DrawableCompat$Api21Impl.canApplyTheme(drawable);
            return false;
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
            ColorFilter colorFilter = this.mColorFilter;
            if (colorFilter == null) {
                colorFilter = this.mTintFilter;
            }
            canvas.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.mTmpFloats);
            float abs = Math.abs(this.mTmpFloats[0]);
            float abs2 = Math.abs(this.mTmpFloats[4]);
            float abs3 = Math.abs(this.mTmpFloats[1]);
            float abs4 = Math.abs(this.mTmpFloats[3]);
            if (abs3 != 0.0f || abs4 != 0.0f) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (this.mTmpBounds.width() * abs));
            int min2 = Math.min(2048, (int) (this.mTmpBounds.height() * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                canvas.translate(this.mTmpBounds.left, this.mTmpBounds.top);
                if (isAutoMirrored() && DrawableCompat$Api23Impl.getLayoutDirection(this) == 1) {
                    canvas.translate(this.mTmpBounds.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.mTmpBounds.offsetTo(0, 0);
                VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
                Bitmap bitmap = vectorDrawableCompatState.mCachedBitmap;
                if (bitmap == null || min != bitmap.getWidth() || min2 != vectorDrawableCompatState.mCachedBitmap.getHeight()) {
                    vectorDrawableCompatState.mCachedBitmap = Bitmap.createBitmap(min, min2, Bitmap.Config.ARGB_8888);
                    vectorDrawableCompatState.mCacheDirty = true;
                }
                if (!this.mAllowCaching) {
                    this.mVectorState.updateCachedBitmap(min, min2);
                } else {
                    VectorDrawableCompatState vectorDrawableCompatState2 = this.mVectorState;
                    if (vectorDrawableCompatState2.mCacheDirty || vectorDrawableCompatState2.mCachedTint != vectorDrawableCompatState2.mTint || vectorDrawableCompatState2.mCachedTintMode != vectorDrawableCompatState2.mTintMode || vectorDrawableCompatState2.mCachedAutoMirrored != vectorDrawableCompatState2.mAutoMirrored || vectorDrawableCompatState2.mCachedRootAlpha != vectorDrawableCompatState2.mVPathRenderer.getRootAlpha()) {
                        this.mVectorState.updateCachedBitmap(min, min2);
                        VectorDrawableCompatState vectorDrawableCompatState3 = this.mVectorState;
                        vectorDrawableCompatState3.mCachedTint = vectorDrawableCompatState3.mTint;
                        vectorDrawableCompatState3.mCachedTintMode = vectorDrawableCompatState3.mTintMode;
                        vectorDrawableCompatState3.mCachedRootAlpha = vectorDrawableCompatState3.mVPathRenderer.getRootAlpha();
                        vectorDrawableCompatState3.mCachedAutoMirrored = vectorDrawableCompatState3.mAutoMirrored;
                        vectorDrawableCompatState3.mCacheDirty = false;
                    }
                }
                VectorDrawableCompatState vectorDrawableCompatState4 = this.mVectorState;
                Rect rect = this.mTmpBounds;
                if (vectorDrawableCompatState4.mVPathRenderer.getRootAlpha() >= 255 && colorFilter == null) {
                    paint = null;
                } else {
                    if (vectorDrawableCompatState4.mTempPaint == null) {
                        vectorDrawableCompatState4.mTempPaint = new Paint();
                        vectorDrawableCompatState4.mTempPaint.setFilterBitmap(true);
                    }
                    vectorDrawableCompatState4.mTempPaint.setAlpha(vectorDrawableCompatState4.mVPathRenderer.getRootAlpha());
                    vectorDrawableCompatState4.mTempPaint.setColorFilter(colorFilter);
                    paint = vectorDrawableCompatState4.mTempPaint;
                }
                canvas.drawBitmap(vectorDrawableCompatState4.mCachedBitmap, (Rect) null, rect, paint);
                canvas.restoreToCount(save);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getAlpha();
        }
        return this.mVectorState.mVPathRenderer.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return DrawableCompat$Api21Impl.getColorFilter(drawable);
        }
        return this.mColorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return new VectorDrawableDelegateState(drawable.getConstantState());
        }
        this.mVectorState.mChangingConfigurations = getChangingConfigurations();
        return this.mVectorState;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return (int) this.mVectorState.mVPathRenderer.mBaseHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return (int) this.mVectorState.mVPathRenderer.mBaseWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.isAutoMirrored();
        }
        return this.mVectorState.mAutoMirrored;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (super.isStateful()) {
            return true;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState != null) {
            if (vectorDrawableCompatState.isStateful()) {
                return true;
            }
            ColorStateList colorStateList = this.mVectorState.mTint;
            if (colorStateList != null) {
                if (colorStateList.isStateful()) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
        }
        return this;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected final boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        ColorStateList colorStateList = vectorDrawableCompatState.mTint;
        boolean z = false;
        if (colorStateList != null && (mode = vectorDrawableCompatState.mTintMode) != null) {
            this.mTintFilter = updateTintFilter$ar$ds(colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (vectorDrawableCompatState.isStateful()) {
            boolean onStateChanged = vectorDrawableCompatState.mVPathRenderer.mRootGroup.onStateChanged(iArr);
            vectorDrawableCompatState.mCacheDirty |= onStateChanged;
            if (onStateChanged) {
                invalidateSelf();
                return true;
            }
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.mVectorState.mVPathRenderer.getRootAlpha() != i) {
            this.mVectorState.mVPathRenderer.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setAutoMirrored(z);
        } else {
            this.mVectorState.mAutoMirrored = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.mColorFilter = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            DrawableCompat$Api21Impl.setTint(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            DrawableCompat$Api21Impl.setTintList(drawable, colorStateList);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.mTint != colorStateList) {
            vectorDrawableCompatState.mTint = colorStateList;
            this.mTintFilter = updateTintFilter$ar$ds(colorStateList, vectorDrawableCompatState.mTintMode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            DrawableCompat$Api21Impl.setTintMode(drawable, mode);
            return;
        }
        VectorDrawableCompatState vectorDrawableCompatState = this.mVectorState;
        if (vectorDrawableCompatState.mTintMode != mode) {
            vectorDrawableCompatState.mTintMode = mode;
            this.mTintFilter = updateTintFilter$ar$ds(vectorDrawableCompatState.mTint, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.mDelegateDrawable;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    final PorterDuffColorFilter updateTintFilter$ar$ds(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList != null && mode != null) {
            return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
        }
        return null;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState constantState) {
            this.mDelegateState = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable() {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable();
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(resources);
            return vectorDrawableCompat;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
            VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(resources, theme);
            return vectorDrawableCompat;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VGroup extends TransitionUtils.Api28Impl {
        int mChangingConfigurations;
        final ArrayList mChildren;
        public String mGroupName;
        final Matrix mLocalMatrix;
        public float mPivotX;
        public float mPivotY;
        float mRotate;
        public float mScaleX;
        public float mScaleY;
        final Matrix mStackedMatrix;
        public int[] mThemeAttrs;
        public float mTranslateX;
        public float mTranslateY;

        public VGroup() {
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            this.mLocalMatrix = new Matrix();
            this.mGroupName = null;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.mRotate;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        @Override // androidx.transition.TransitionUtils.Api28Impl
        public final boolean isStateful() {
            for (int i = 0; i < this.mChildren.size(); i++) {
                if (((TransitionUtils.Api28Impl) this.mChildren.get(i)).isStateful()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.transition.TransitionUtils.Api28Impl
        public final boolean onStateChanged(int[] iArr) {
            boolean z = false;
            for (int i = 0; i < this.mChildren.size(); i++) {
                z |= ((TransitionUtils.Api28Impl) this.mChildren.get(i)).onStateChanged(iArr);
            }
            return z;
        }

        public void setPivotX(float f) {
            if (f != this.mPivotX) {
                this.mPivotX = f;
                updateLocalMatrix();
            }
        }

        public void setPivotY(float f) {
            if (f != this.mPivotY) {
                this.mPivotY = f;
                updateLocalMatrix();
            }
        }

        public void setRotation(float f) {
            if (f != this.mRotate) {
                this.mRotate = f;
                updateLocalMatrix();
            }
        }

        public void setScaleX(float f) {
            if (f != this.mScaleX) {
                this.mScaleX = f;
                updateLocalMatrix();
            }
        }

        public void setScaleY(float f) {
            if (f != this.mScaleY) {
                this.mScaleY = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.mTranslateX) {
                this.mTranslateX = f;
                updateLocalMatrix();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.mTranslateY) {
                this.mTranslateY = f;
                updateLocalMatrix();
            }
        }

        public final void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            this.mLocalMatrix.postRotate(this.mRotate, 0.0f, 0.0f);
            this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        public VGroup(VGroup vGroup, ArrayMap arrayMap) {
            VPath vClipPath;
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            Matrix matrix = new Matrix();
            this.mLocalMatrix = matrix;
            this.mGroupName = null;
            this.mRotate = vGroup.mRotate;
            this.mPivotX = vGroup.mPivotX;
            this.mPivotY = vGroup.mPivotY;
            this.mScaleX = vGroup.mScaleX;
            this.mScaleY = vGroup.mScaleY;
            this.mTranslateX = vGroup.mTranslateX;
            this.mTranslateY = vGroup.mTranslateY;
            int[] iArr = vGroup.mThemeAttrs;
            this.mThemeAttrs = null;
            String str = vGroup.mGroupName;
            this.mGroupName = str;
            int i = vGroup.mChangingConfigurations;
            this.mChangingConfigurations = 0;
            if (str != null) {
                arrayMap.put(str, this);
            }
            matrix.set(vGroup.mLocalMatrix);
            ArrayList arrayList = vGroup.mChildren;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                Object obj = arrayList.get(i2);
                if (obj instanceof VGroup) {
                    this.mChildren.add(new VGroup((VGroup) obj, arrayMap));
                } else {
                    if (obj instanceof VFullPath) {
                        vClipPath = new VFullPath((VFullPath) obj);
                    } else if (obj instanceof VClipPath) {
                        vClipPath = new VClipPath((VClipPath) obj);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.mChildren.add(vClipPath);
                    Object obj2 = vClipPath.mPathName;
                    if (obj2 != null) {
                        arrayMap.put(obj2, vClipPath);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c4  */
    @Override // android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void inflate(android.content.res.Resources r22, org.xmlpull.v1.XmlPullParser r23, android.util.AttributeSet r24, android.content.res.Resources.Theme r25) {
        /*
            Method dump skipped, instructions count: 946
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VPathRenderer {
        public static final Matrix IDENTITY_MATRIX = new Matrix();
        float mBaseHeight;
        float mBaseWidth;
        private int mChangingConfigurations;
        Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        Boolean mIsStateful;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha;
        final VGroup mRootGroup;
        String mRootName;
        Paint mStrokePaint;
        final ArrayMap mVGTargetsMap;
        float mViewportHeight;
        float mViewportWidth;

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = PrivateKeyType.INVALID;
            this.mRootName = null;
            this.mIsStateful = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public final void drawGroupTree$ar$ds(VGroup vGroup, Matrix matrix, Canvas canvas, int i, int i2) {
            float f;
            Path.FillType fillType;
            Path.FillType fillType2;
            vGroup.mStackedMatrix.set(matrix);
            vGroup.mStackedMatrix.preConcat(vGroup.mLocalMatrix);
            canvas.save();
            for (int i3 = 0; i3 < vGroup.mChildren.size(); i3++) {
                TransitionUtils.Api28Impl api28Impl = (TransitionUtils.Api28Impl) vGroup.mChildren.get(i3);
                if (api28Impl instanceof VGroup) {
                    drawGroupTree$ar$ds((VGroup) api28Impl, vGroup.mStackedMatrix, canvas, i, i2);
                } else if (api28Impl instanceof VPath) {
                    VPath vPath = (VPath) api28Impl;
                    float f2 = i / this.mViewportWidth;
                    float f3 = i2 / this.mViewportHeight;
                    float min = Math.min(f2, f3);
                    Matrix matrix2 = vGroup.mStackedMatrix;
                    this.mFinalPathMatrix.set(matrix2);
                    this.mFinalPathMatrix.postScale(f2, f3);
                    float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                    matrix2.mapVectors(fArr);
                    float hypot = (float) Math.hypot(fArr[0], fArr[1]);
                    float hypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                    float f4 = fArr[0];
                    float f5 = fArr[1];
                    float f6 = fArr[2];
                    float f7 = f4 * fArr[3];
                    float f8 = f5 * f6;
                    float max = Math.max(hypot, hypot2);
                    if (max > 0.0f) {
                        f = Math.abs(f7 - f8) / max;
                    } else {
                        f = 0.0f;
                    }
                    if (f != 0.0f) {
                        Path path = this.mPath;
                        path.reset();
                        PathParser$PathDataNode[] pathParser$PathDataNodeArr = vPath.mNodes;
                        if (pathParser$PathDataNodeArr != null) {
                            NotificationCompatBuilder$Api21Impl.nodesToPath(pathParser$PathDataNodeArr, path);
                        }
                        Path path2 = this.mPath;
                        this.mRenderPath.reset();
                        if (vPath.isClipPath()) {
                            Path path3 = this.mRenderPath;
                            if (vPath.mFillRule == 0) {
                                fillType2 = Path.FillType.WINDING;
                            } else {
                                fillType2 = Path.FillType.EVEN_ODD;
                            }
                            path3.setFillType(fillType2);
                            this.mRenderPath.addPath(path2, this.mFinalPathMatrix);
                            canvas.clipPath(this.mRenderPath);
                        } else {
                            VFullPath vFullPath = (VFullPath) vPath;
                            float f9 = vFullPath.mTrimPathStart;
                            if (f9 != 0.0f || vFullPath.mTrimPathEnd != 1.0f) {
                                float f10 = vFullPath.mTrimPathOffset;
                                float f11 = (f9 + f10) % 1.0f;
                                float f12 = (vFullPath.mTrimPathEnd + f10) % 1.0f;
                                if (this.mPathMeasure == null) {
                                    this.mPathMeasure = new PathMeasure();
                                }
                                this.mPathMeasure.setPath(this.mPath, false);
                                float length = this.mPathMeasure.getLength();
                                float f13 = f11 * length;
                                float f14 = f12 * length;
                                path2.reset();
                                if (f13 > f14) {
                                    this.mPathMeasure.getSegment(f13, length, path2, true);
                                    this.mPathMeasure.getSegment(0.0f, f14, path2, true);
                                } else {
                                    this.mPathMeasure.getSegment(f13, f14, path2, true);
                                }
                                path2.rLineTo(0.0f, 0.0f);
                            }
                            this.mRenderPath.addPath(path2, this.mFinalPathMatrix);
                            if (vFullPath.mFillColor$ar$class_merging$ar$class_merging.willDraw()) {
                                AsyncInterceptorsClientCallListener.PendingMessage pendingMessage = vFullPath.mFillColor$ar$class_merging$ar$class_merging;
                                if (this.mFillPaint == null) {
                                    Paint paint = new Paint(1);
                                    this.mFillPaint = paint;
                                    paint.setStyle(Paint.Style.FILL);
                                }
                                Paint paint2 = this.mFillPaint;
                                if (pendingMessage.isGradient()) {
                                    Shader shader = (Shader) pendingMessage.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors;
                                    shader.setLocalMatrix(this.mFinalPathMatrix);
                                    paint2.setShader(shader);
                                    paint2.setAlpha(Math.round(vFullPath.mFillAlpha * 255.0f));
                                } else {
                                    paint2.setShader(null);
                                    paint2.setAlpha(PrivateKeyType.INVALID);
                                    paint2.setColor(VectorDrawableCompat.applyAlpha(pendingMessage.currentStage, vFullPath.mFillAlpha));
                                }
                                paint2.setColorFilter(null);
                                Path path4 = this.mRenderPath;
                                if (vFullPath.mFillRule == 0) {
                                    fillType = Path.FillType.WINDING;
                                } else {
                                    fillType = Path.FillType.EVEN_ODD;
                                }
                                path4.setFillType(fillType);
                                canvas.drawPath(this.mRenderPath, paint2);
                            }
                            if (vFullPath.mStrokeColor$ar$class_merging$ar$class_merging.willDraw()) {
                                AsyncInterceptorsClientCallListener.PendingMessage pendingMessage2 = vFullPath.mStrokeColor$ar$class_merging$ar$class_merging;
                                if (this.mStrokePaint == null) {
                                    Paint paint3 = new Paint(1);
                                    this.mStrokePaint = paint3;
                                    paint3.setStyle(Paint.Style.STROKE);
                                }
                                Paint paint4 = this.mStrokePaint;
                                Paint.Join join = vFullPath.mStrokeLineJoin;
                                if (join != null) {
                                    paint4.setStrokeJoin(join);
                                }
                                Paint.Cap cap = vFullPath.mStrokeLineCap;
                                if (cap != null) {
                                    paint4.setStrokeCap(cap);
                                }
                                paint4.setStrokeMiter(vFullPath.mStrokeMiterlimit);
                                if (pendingMessage2.isGradient()) {
                                    Shader shader2 = (Shader) pendingMessage2.AsyncInterceptorsClientCallListener$PendingMessage$ar$detachedInterceptors;
                                    shader2.setLocalMatrix(this.mFinalPathMatrix);
                                    paint4.setShader(shader2);
                                    paint4.setAlpha(Math.round(vFullPath.mStrokeAlpha * 255.0f));
                                } else {
                                    paint4.setShader(null);
                                    paint4.setAlpha(PrivateKeyType.INVALID);
                                    paint4.setColor(VectorDrawableCompat.applyAlpha(pendingMessage2.currentStage, vFullPath.mStrokeAlpha));
                                }
                                paint4.setColorFilter(null);
                                paint4.setStrokeWidth(vFullPath.mStrokeWidth * f * min);
                                canvas.drawPath(this.mRenderPath, paint4);
                            }
                        }
                    }
                }
            }
            canvas.restore();
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.mRootAlpha = i;
        }

        public VPathRenderer(VPathRenderer vPathRenderer) {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = PrivateKeyType.INVALID;
            this.mRootName = null;
            this.mIsStateful = null;
            ArrayMap arrayMap = new ArrayMap();
            this.mVGTargetsMap = arrayMap;
            this.mRootGroup = new VGroup(vPathRenderer.mRootGroup, arrayMap);
            this.mPath = new Path(vPathRenderer.mPath);
            this.mRenderPath = new Path(vPathRenderer.mRenderPath);
            this.mBaseWidth = vPathRenderer.mBaseWidth;
            this.mBaseHeight = vPathRenderer.mBaseHeight;
            this.mViewportWidth = vPathRenderer.mViewportWidth;
            this.mViewportHeight = vPathRenderer.mViewportHeight;
            int i = vPathRenderer.mChangingConfigurations;
            this.mChangingConfigurations = 0;
            this.mRootAlpha = vPathRenderer.mRootAlpha;
            this.mRootName = vPathRenderer.mRootName;
            String str = vPathRenderer.mRootName;
            if (str != null) {
                arrayMap.put(str, this);
            }
            this.mIsStateful = vPathRenderer.mIsStateful;
        }
    }

    public VectorDrawableCompat(VectorDrawableCompatState vectorDrawableCompatState) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = vectorDrawableCompatState;
        this.mTintFilter = updateTintFilter$ar$ds(vectorDrawableCompatState.mTint, vectorDrawableCompatState.mTintMode);
    }
}

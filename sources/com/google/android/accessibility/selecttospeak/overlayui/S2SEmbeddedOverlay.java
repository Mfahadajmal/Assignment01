package com.google.android.accessibility.selecttospeak.overlayui;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.google.android.accessibility.selecttospeak.UIManager;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SEmbeddedOverlay implements SelectToSpeakOverlay {
    private final ViewGroup contentView;
    private Point displaySizePx;
    private final Point lastPositionPx;
    private final Point maxParentPx;
    public View.OnTouchListener onTouchListener;
    public CharSequence rootViewClassName;
    private final AccessibilityService service;
    private final SurfaceControl surfaceControl;
    public final boolean touchable;
    private final SurfaceControl.Transaction transaction;
    private final SurfaceControlViewHost viewHost;
    private Point viewSizePx;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.google.android.accessibility.selecttospeak.overlayui.S2SEmbeddedOverlay$createView$contentView$1, android.view.ViewGroup] */
    public S2SEmbeddedOverlay(AccessibilityService accessibilityService, int i) {
        SurfaceControlViewHost.SurfacePackage surfacePackage;
        SurfaceControl surfaceControl;
        this.service = accessibilityService;
        final Context defaultScreenDensityContext = SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(accessibilityService);
        ?? r1 = new FrameLayout(defaultScreenDensityContext) { // from class: com.google.android.accessibility.selecttospeak.overlayui.S2SEmbeddedOverlay$createView$contentView$1
            @Override // android.view.ViewGroup, android.view.View
            protected final boolean dispatchHoverEvent(MotionEvent motionEvent) {
                motionEvent.getClass();
                motionEvent.offsetLocation(-getTranslationX(), -getTranslationY());
                return super.dispatchHoverEvent(motionEvent);
            }

            @Override // android.view.ViewGroup, android.view.View
            public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
                View.OnTouchListener onTouchListener;
                motionEvent.getClass();
                motionEvent.offsetLocation(-getTranslationX(), -getTranslationY());
                S2SEmbeddedOverlay s2SEmbeddedOverlay = S2SEmbeddedOverlay.this;
                if (s2SEmbeddedOverlay.touchable && (onTouchListener = s2SEmbeddedOverlay.onTouchListener) != null && onTouchListener.onTouch(this, motionEvent)) {
                    return true;
                }
                return super.dispatchTouchEvent(motionEvent);
            }

            @Override // android.view.View
            public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
                accessibilityNodeInfo.getClass();
                super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
                accessibilityNodeInfo.setClassName(S2SEmbeddedOverlay.this.rootViewClassName);
            }
        };
        r1.removeAllViews();
        LayoutInflater.from(SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(accessibilityService)).inflate(i, (ViewGroup) r1);
        this.contentView = r1;
        this.touchable = true;
        this.lastPositionPx = new Point(-1, -1);
        this.maxParentPx = new Point(-1, -1);
        String str = UIManager.S2S_OVERLAY_IDENTIFIER;
        str.getClass();
        this.rootViewClassName = str;
        this.viewSizePx = new Point(-1, -1);
        this.displaySizePx = new Point(-1, -1);
        Object systemService = accessibilityService.getSystemService("display");
        systemService.getClass();
        SurfaceControlViewHost surfaceControlViewHost = new SurfaceControlViewHost(accessibilityService, ((DisplayManager) systemService).getDisplay(0), new Binder());
        this.viewHost = surfaceControlViewHost;
        surfacePackage = surfaceControlViewHost.getSurfacePackage();
        surfacePackage.getClass();
        surfaceControl = surfacePackage.getSurfaceControl();
        surfaceControl.getClass();
        this.surfaceControl = surfaceControl;
        this.transaction = new SurfaceControl.Transaction();
        updateScreenBounds();
    }

    private final float getLastX() {
        return this.lastPositionPx.x;
    }

    private final float getLastY() {
        return this.lastPositionPx.y;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final View findViewById(int i) {
        return this.contentView.findViewById(i);
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void fractionalToPixelCoordinates(float[] fArr, int[] iArr) {
        iArr[0] = (int) (fArr[0] * this.maxParentPx.x);
        iArr[1] = (int) (fArr[1] * this.maxParentPx.y);
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final int getMaxWindowX() {
        return this.maxParentPx.x;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final int getMaxWindowY() {
        return this.maxParentPx.y;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void getPixelCoordinates(int[] iArr) {
        iArr.getClass();
        iArr[0] = this.lastPositionPx.x;
        iArr[1] = this.lastPositionPx.y;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void hide() {
        SurfaceControl.Transaction reparent;
        LogUtils.d("S2SEmbeddedOverlay", "hide.", new Object[0]);
        reparent = new SurfaceControl.Transaction().reparent(this.surfaceControl, null);
        reparent.apply();
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void pixelToFractionalCoordinates(int[] iArr, float[] fArr) {
        if (this.maxParentPx.x == 0) {
            fArr[0] = 0.0f;
        } else {
            fArr[0] = iArr[0] / this.maxParentPx.x;
        }
        if (this.maxParentPx.y == 0) {
            fArr[1] = 0.0f;
        } else {
            fArr[1] = iArr[1] / this.maxParentPx.y;
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void setPixelCoordinates(int i, int i2) {
        SurfaceControl.Transaction position;
        this.lastPositionPx.x = OnDeviceSmartReplyLogEvent.SmartReply.coerceIn$ar$ds(i, this.maxParentPx.x);
        this.lastPositionPx.y = OnDeviceSmartReplyLogEvent.SmartReply.coerceIn$ar$ds(i2, this.maxParentPx.y);
        position = this.transaction.setPosition(this.surfaceControl, getLastX(), getLastY());
        position.apply();
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void show() {
        SurfaceControl.Transaction visibility;
        SurfaceControl.Transaction layer;
        SurfaceControl.Transaction position;
        LogUtils.d("S2SEmbeddedOverlay", "show.", new Object[0]);
        this.contentView.invalidate();
        SurfaceControl.Transaction transaction = this.transaction;
        SurfaceControl surfaceControl = this.surfaceControl;
        visibility = transaction.setVisibility(surfaceControl, true);
        layer = visibility.setLayer(surfaceControl, 1);
        position = layer.setPosition(this.surfaceControl, getLastX(), getLastY());
        position.apply();
        this.viewHost.setView(this.contentView, this.viewSizePx.x, this.viewSizePx.y);
        this.service.attachAccessibilityOverlayToDisplay(0, this.surfaceControl);
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void updateScreenBounds() {
        OverlayUiUtil overlayUiUtil = OverlayUiUtil.INSTANCE;
        ViewGroup viewGroup = this.contentView;
        viewGroup.getClass();
        Point displaySizePx$ar$ds = OverlayUiUtil.getDisplaySizePx$ar$ds(this.service);
        viewGroup.measure(View.MeasureSpec.makeMeasureSpec(displaySizePx$ar$ds.x, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(displaySizePx$ar$ds.y, Integer.MIN_VALUE));
        this.viewSizePx = new Point(viewGroup.getMeasuredWidth(), viewGroup.getMeasuredHeight());
        Point displaySizePx$ar$ds2 = OverlayUiUtil.getDisplaySizePx$ar$ds(this.service);
        this.displaySizePx = displaySizePx$ar$ds2;
        int i = displaySizePx$ar$ds2.x - this.viewSizePx.x;
        this.maxParentPx.x = Math.max(0, i);
        int i2 = this.displaySizePx.y - this.viewSizePx.y;
        this.maxParentPx.y = Math.max(0, i2);
    }

    @Override // com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay
    public final void setWatchOutsideTouch$ar$ds() {
    }
}

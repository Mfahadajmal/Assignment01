package com.google.android.accessibility.braille.brailledisplay;

import android.R;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.widgets.simple.SimpleOverlay;
import org.chromium.net.NetError;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DraggableOverlay extends SimpleOverlay {
    public static final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    public static final int TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
    public float dragOrigin;
    public boolean dragging;
    private final InternalListener internalListener;
    public final int touchSlopSquare;
    public final WindowManager.LayoutParams touchStealingLayoutParams;
    public final View touchStealingView;
    public final WindowManager windowManager;
    public final WindowManager.LayoutParams windowParams;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InternalListener implements View.OnTouchListener, View.OnHoverListener, Handler.Callback {
        private final Handler handler = new Handler(this);
        private float touchStartX;
        private float touchStartY;

        public InternalListener() {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            if (message.what == 1) {
                DraggableOverlay draggableOverlay = DraggableOverlay.this;
                MotionEvent motionEvent = (MotionEvent) message.obj;
                if (!draggableOverlay.dragging) {
                    draggableOverlay.dragging = true;
                    draggableOverlay.dragOrigin = motionEvent.getRawY();
                    draggableOverlay.onStartDragging();
                    draggableOverlay.windowParams.flags |= 16;
                    draggableOverlay.setParams(draggableOverlay.windowParams);
                    draggableOverlay.windowManager.addView(draggableOverlay.touchStealingView, draggableOverlay.touchStealingLayoutParams);
                    draggableOverlay.contentView.performHapticFeedback(0);
                }
            }
            return true;
        }

        @Override // android.view.View.OnHoverListener
        public final boolean onHover(View view, MotionEvent motionEvent) {
            onTouch(view, SpannableUtils$IdentifierSpan.convertHoverToTouch(motionEvent));
            return false;
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        if (actionMasked == 3) {
                            this.handler.removeMessages(1);
                            DraggableOverlay draggableOverlay = DraggableOverlay.this;
                            if (draggableOverlay.dragging) {
                                draggableOverlay.dragging = false;
                                draggableOverlay.windowParams.flags &= -17;
                                draggableOverlay.windowParams.y = 0;
                                draggableOverlay.setParams(draggableOverlay.windowParams);
                                draggableOverlay.windowManager.removeViewImmediate(draggableOverlay.touchStealingView);
                            }
                        }
                    } else {
                        float rawX = motionEvent.getRawX() - this.touchStartX;
                        float rawY = motionEvent.getRawY() - this.touchStartY;
                        if ((rawX * rawX) + (rawY * rawY) > DraggableOverlay.this.touchSlopSquare) {
                            this.handler.removeMessages(1);
                        }
                        DraggableOverlay draggableOverlay2 = DraggableOverlay.this;
                        if (draggableOverlay2.dragging) {
                            int i = draggableOverlay2.windowParams.gravity & BrailleInputEvent.CMD_CONTROL_NEXT;
                            if (i != 48) {
                                if (i == 80) {
                                    draggableOverlay2.windowParams.y = (int) (draggableOverlay2.dragOrigin - motionEvent.getRawY());
                                }
                            } else {
                                draggableOverlay2.windowParams.y = (int) (motionEvent.getRawY() - draggableOverlay2.dragOrigin);
                            }
                            draggableOverlay2.setParams(draggableOverlay2.windowParams);
                        }
                    }
                } else {
                    this.handler.removeMessages(1);
                    DraggableOverlay draggableOverlay3 = DraggableOverlay.this;
                    if (view == draggableOverlay3.touchStealingView && draggableOverlay3.dragging) {
                        draggableOverlay3.dragging = false;
                        draggableOverlay3.windowParams.flags &= -17;
                        if (draggableOverlay3.windowParams.y > draggableOverlay3.touchStealingView.getHeight() / 2) {
                            int i2 = draggableOverlay3.windowParams.gravity & BrailleInputEvent.CMD_CONTROL_NEXT;
                            if (i2 != 48) {
                                if (i2 == 80) {
                                    draggableOverlay3.windowParams.gravity &= NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH;
                                    draggableOverlay3.windowParams.gravity |= 48;
                                }
                            } else {
                                draggableOverlay3.windowParams.gravity &= NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH;
                                draggableOverlay3.windowParams.gravity |= 80;
                            }
                        }
                        draggableOverlay3.windowParams.y = 0;
                        draggableOverlay3.setParams(draggableOverlay3.windowParams);
                        draggableOverlay3.windowManager.removeViewImmediate(draggableOverlay3.touchStealingView);
                    }
                }
            } else if (view != DraggableOverlay.this.touchStealingView) {
                this.touchStartX = motionEvent.getRawX();
                this.touchStartY = motionEvent.getRawY();
                long j = DraggableOverlay.TAP_TIMEOUT;
                long j2 = DraggableOverlay.LONG_PRESS_TIMEOUT;
                Handler handler = this.handler;
                handler.sendMessageAtTime(handler.obtainMessage(1, motionEvent), motionEvent.getEventTime() + j + j2);
            }
            return false;
        }
    }

    public DraggableOverlay(Context context) {
        super(context);
        this.dragging = false;
        this.windowManager = (WindowManager) context.getSystemService("window");
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.touchSlopSquare = scaledTouchSlop * scaledTouchSlop;
        WindowManager.LayoutParams createOverlayParams = createOverlayParams();
        this.windowParams = createOverlayParams;
        createOverlayParams.width = -1;
        createOverlayParams.height = -2;
        createOverlayParams.gravity = 80;
        createOverlayParams.windowAnimations = R.style.Animation.Translucent;
        setParams(createOverlayParams);
        InternalListener internalListener = new InternalListener();
        this.internalListener = internalListener;
        this.contentView.setOnHoverListener(internalListener);
        this.contentView.setOnTouchListener(internalListener);
        View view = new View(context);
        this.touchStealingView = view;
        view.setOnHoverListener(internalListener);
        view.setOnTouchListener(internalListener);
        WindowManager.LayoutParams createOverlayParams2 = createOverlayParams();
        createOverlayParams2.width = -1;
        createOverlayParams2.height = -1;
        createOverlayParams2.flags |= 32;
        this.touchStealingLayoutParams = createOverlayParams2;
    }

    private static WindowManager.LayoutParams createOverlayParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2032;
        layoutParams.format = -2;
        layoutParams.flags |= 256;
        layoutParams.flags |= 8;
        return layoutParams;
    }

    protected void onStartDragging() {
    }
}

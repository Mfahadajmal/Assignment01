package com.google.android.accessibility.utils.accessibilitybutton;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityButtonController;
import android.accessibilityservice.AccessibilityButtonController$AccessibilityButtonCallback;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.ContextWrapper;
import android.hardware.display.DisplayManager;
import android.os.Message;
import android.util.Pair;
import android.view.Display;
import android.view.accessibility.AccessibilityManager;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.talkback.logging.TrainingMetricHolder;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.PageController$SectionInfo;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.common.collect.ImmutableList;
import j$.util.function.Function$CC;
import java.util.function.Function;
import org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityButtonMonitor {
    public Object AccessibilityButtonMonitor$ar$accessibilityButtonCallback;
    public final Object AccessibilityButtonMonitor$ar$displayListener;
    public final Object AccessibilityButtonMonitor$ar$displayManager;
    public Object AccessibilityButtonMonitor$ar$mCallback;
    public final Object AccessibilityButtonMonitor$ar$mHandler;
    public final ContextWrapper AccessibilityButtonMonitor$ar$mService;
    public int mButtonState;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements DisplayManager.DisplayListener {
        final /* synthetic */ Object AccessibilityButtonMonitor$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.AccessibilityButtonMonitor$1$ar$this$0 = obj;
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayAdded(int i) {
            AccessibilityButtonController accessibilityButtonController;
            if (this.switching_field == 0 && SpannableUtils$IdentifierSpan.isAtLeastR()) {
                AccessibilityButtonMonitor accessibilityButtonMonitor = (AccessibilityButtonMonitor) this.AccessibilityButtonMonitor$1$ar$this$0;
                if (accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$accessibilityButtonCallback != null) {
                    accessibilityButtonController = ((AccessibilityService) accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController(i);
                    accessibilityButtonController.registerAccessibilityButtonCallback(BundleUtils$$ExternalSyntheticApiModelOutline0.m(((AccessibilityButtonMonitor) this.AccessibilityButtonMonitor$1$ar$this$0).AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
                }
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayChanged(int i) {
            if (this.switching_field != 0) {
                KeyboardView keyboardView = (KeyboardView) this.AccessibilityButtonMonitor$1$ar$this$0;
                if (keyboardView.viewContainer != null) {
                    int displayRotationDegrees = _BOUNDARY.getDisplayRotationDegrees(keyboardView.context);
                    if (displayRotationDegrees != 1 && displayRotationDegrees != 3) {
                        return;
                    }
                    ((KeyboardView) this.AccessibilityButtonMonitor$1$ar$this$0).updateViewContainerInternal();
                }
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public final void onDisplayRemoved(int i) {
            AccessibilityButtonController accessibilityButtonController;
            if (this.switching_field == 0 && SpannableUtils$IdentifierSpan.isAtLeastR()) {
                AccessibilityButtonMonitor accessibilityButtonMonitor = (AccessibilityButtonMonitor) this.AccessibilityButtonMonitor$1$ar$this$0;
                if (accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$accessibilityButtonCallback != null) {
                    accessibilityButtonController = ((AccessibilityService) accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController(i);
                    accessibilityButtonController.unregisterAccessibilityButtonCallback(BundleUtils$$ExternalSyntheticApiModelOutline0.m(((AccessibilityButtonMonitor) this.AccessibilityButtonMonitor$1$ar$this$0).AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AccessibilityButtonCallBackHandler extends WeakReferenceHandler {
        private boolean mHasNotifiedSupportability;

        /* renamed from: -$$Nest$mpostDelayedConfirmAccessibilityButtonSupportability$ar$ds, reason: not valid java name */
        static /* bridge */ /* synthetic */ void m186xfcfb09ed(AccessibilityButtonCallBackHandler accessibilityButtonCallBackHandler) {
            LogUtils.d("A11yMenuButtonMonitor", "Post delay to confirm supportability.", new Object[0]);
            accessibilityButtonCallBackHandler.removeMessages(2);
            accessibilityButtonCallBackHandler.removeMessages(1);
            accessibilityButtonCallBackHandler.removeMessages(3);
            accessibilityButtonCallBackHandler.sendEmptyMessageDelayed(3, 1000L);
        }

        public AccessibilityButtonCallBackHandler(AccessibilityButtonMonitor accessibilityButtonMonitor) {
            super(accessibilityButtonMonitor);
            this.mHasNotifiedSupportability = false;
        }

        public final void confirmAccessibilityButtonSupportability(boolean z) {
            removeMessages(2);
            removeMessages(1);
            removeMessages(3);
            obtainMessage(2).sendToTarget();
        }

        /* JADX WARN: Type inference failed for: r6v2, types: [com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor$AccessibilityButtonMonitorCallback, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v5, types: [com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor$AccessibilityButtonMonitorCallback, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v8, types: [com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor$AccessibilityButtonMonitorCallback, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v2, types: [com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor$AccessibilityButtonMonitorCallback, java.lang.Object] */
        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            AccessibilityButtonController accessibilityButtonController;
            boolean isAccessibilityButtonAvailableCompat;
            String str;
            AccessibilityButtonMonitor accessibilityButtonMonitor = (AccessibilityButtonMonitor) obj;
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    int i2 = 2;
                    if (i != 2) {
                        if (i == 3) {
                            if (SpannableUtils$IdentifierSpan.isAtLeastOMR1()) {
                                isAccessibilityButtonAvailableCompat = AccessibilityManager.isAccessibilityButtonSupported();
                            } else {
                                accessibilityButtonController = ((AccessibilityService) accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController();
                                isAccessibilityButtonAvailableCompat = SpannableUtils$IdentifierSpan.isAccessibilityButtonAvailableCompat(accessibilityButtonController);
                            }
                            if (true != isAccessibilityButtonAvailableCompat) {
                                i2 = 1;
                            }
                            accessibilityButtonMonitor.mButtonState = i2;
                            if (!this.mHasNotifiedSupportability) {
                                if (true != isAccessibilityButtonAvailableCompat) {
                                    str = "not supported";
                                } else {
                                    str = "supported";
                                }
                                LogUtils.d("A11yMenuButtonMonitor", "Delayed. Notify that a11y button is %s.", str);
                                accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mCallback.onConfirmSupportability(isAccessibilityButtonAvailableCompat);
                                this.mHasNotifiedSupportability = true;
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    accessibilityButtonMonitor.mButtonState = 2;
                    if (!this.mHasNotifiedSupportability) {
                        LogUtils.d("A11yMenuButtonMonitor", "Notify that a11y button is supported.", new Object[0]);
                        accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mCallback.onConfirmSupportability(true);
                        this.mHasNotifiedSupportability = true;
                        return;
                    }
                    return;
                }
                accessibilityButtonMonitor.mButtonState = 1;
                if (!this.mHasNotifiedSupportability) {
                    LogUtils.d("A11yMenuButtonMonitor", "Notify that a11y button is not supported.", new Object[0]);
                    this.mHasNotifiedSupportability = true;
                    accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mCallback.onConfirmSupportability(false);
                    return;
                }
                return;
            }
            accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mCallback.onAccessibilityButtonClicked();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AccessibilityButtonMonitorCallback {
        void onAccessibilityButtonClicked();

        void onConfirmSupportability(boolean z);
    }

    public AccessibilityButtonMonitor(TrainingConfig trainingConfig, TrainingActivity trainingActivity, TrainingIpcClient.ServiceData serviceData, WindowTrackerFactory windowTrackerFactory) {
        this.mButtonState = -1;
        this.AccessibilityButtonMonitor$ar$displayListener = trainingConfig;
        this.AccessibilityButtonMonitor$ar$mService = trainingActivity;
        this.AccessibilityButtonMonitor$ar$displayManager = serviceData;
        this.AccessibilityButtonMonitor$ar$mHandler = windowTrackerFactory;
    }

    private final int getPageSize() {
        return ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getPages().size();
    }

    public final boolean backToLinkIndexPage() {
        Object obj = this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback;
        if (obj == null) {
            return false;
        }
        this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback = null;
        switchPage(((PageController$SectionInfo) obj).indexPageNumber);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final PageConfig getCurrentPageConfig() {
        int i = this.mButtonState;
        if (i >= 0 && i < getPageSize()) {
            return (PageConfig) ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getPages().get(this.mButtonState);
        }
        return null;
    }

    public final void handleControllerCallbackAvailabilityChanged(boolean z) {
        int i = this.mButtonState;
        if (i != 0) {
            if (i == 1 && z) {
                LogUtils.w("A11yMenuButtonMonitor", "A11y button availability is changed after it's reported as NOT_SUPPORTED. Update state from NOT_SUPPORTED to SUPPORTED.", new Object[0]);
                this.mButtonState = 2;
                return;
            }
            return;
        }
        if (z) {
            ((AccessibilityButtonCallBackHandler) this.AccessibilityButtonMonitor$ar$mHandler).confirmAccessibilityButtonSupportability(true);
        } else {
            AccessibilityButtonCallBackHandler.m186xfcfb09ed((AccessibilityButtonCallBackHandler) this.AccessibilityButtonMonitor$ar$mHandler);
        }
    }

    public final void handleControllerCallbackButtonClicked() {
        int i = this.mButtonState;
        if (i == 0) {
            ((AccessibilityButtonCallBackHandler) this.AccessibilityButtonMonitor$ar$mHandler).confirmAccessibilityButtonSupportability(true);
        } else if (i == 1) {
            LogUtils.w("A11yMenuButtonMonitor", "A11y button is clicked after it's reported as NOT_SUPPORTED. Update state from NOT_SUPPORTED to SUPPORTED.", new Object[0]);
            this.mButtonState = 2;
        }
        ((AccessibilityButtonCallBackHandler) this.AccessibilityButtonMonitor$ar$mHandler).obtainMessage(0).sendToTarget();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.hardware.display.DisplayManager$DisplayListener, java.lang.Object] */
    public final void initAccessibilityButton(AccessibilityButtonMonitorCallback accessibilityButtonMonitorCallback) {
        AccessibilityButtonController accessibilityButtonController;
        AccessibilityButtonController accessibilityButtonController2;
        this.AccessibilityButtonMonitor$ar$mCallback = accessibilityButtonMonitorCallback;
        AccessibilityServiceInfo serviceInfo = ((AccessibilityService) this.AccessibilityButtonMonitor$ar$mService).getServiceInfo();
        if (serviceInfo != null) {
            serviceInfo.flags |= 256;
            ((AccessibilityService) this.AccessibilityButtonMonitor$ar$mService).setServiceInfo(serviceInfo);
        }
        accessibilityButtonController = ((AccessibilityService) this.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController();
        if (SpannableUtils$IdentifierSpan.isAccessibilityButtonAvailableCompat(accessibilityButtonController)) {
            LogUtils.d("A11yMenuButtonMonitor", "Accessibility button is available on initialization.", new Object[0]);
            ((AccessibilityButtonCallBackHandler) this.AccessibilityButtonMonitor$ar$mHandler).confirmAccessibilityButtonSupportability(true);
        } else {
            LogUtils.d("A11yMenuButtonMonitor", "Accessibility button is not available on initialization.", new Object[0]);
            AccessibilityButtonCallBackHandler.m186xfcfb09ed((AccessibilityButtonCallBackHandler) this.AccessibilityButtonMonitor$ar$mHandler);
        }
        this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback = new AccessibilityButtonController$AccessibilityButtonCallback() { // from class: com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor.2
            public final void onAvailabilityChanged(AccessibilityButtonController accessibilityButtonController3, boolean z) {
                LogUtils.d("A11yMenuButtonMonitor", "Accessibility button availability changed. isAvailable=%s", Boolean.valueOf(z));
                AccessibilityButtonMonitor.this.handleControllerCallbackAvailabilityChanged(z);
            }

            public final void onClicked(AccessibilityButtonController accessibilityButtonController3) {
                LogUtils.d("A11yMenuButtonMonitor", "Accessibility button clicked.", new Object[0]);
                AccessibilityButtonMonitor.this.handleControllerCallbackButtonClicked();
            }
        };
        if (!SpannableUtils$IdentifierSpan.isAtLeastR()) {
            accessibilityButtonController.registerAccessibilityButtonCallback(BundleUtils$$ExternalSyntheticApiModelOutline0.m(this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
            return;
        }
        ((DisplayManager) this.AccessibilityButtonMonitor$ar$displayManager).registerDisplayListener(this.AccessibilityButtonMonitor$ar$displayListener, null);
        for (Display display : ((DisplayManager) this.AccessibilityButtonMonitor$ar$displayManager).getDisplays()) {
            accessibilityButtonController2 = ((AccessibilityService) this.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController(display.getDisplayId());
            accessibilityButtonController2.registerAccessibilityButtonCallback(BundleUtils$$ExternalSyntheticApiModelOutline0.m(this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
        }
    }

    public final boolean isAccessibilityButtonSupported() {
        if (this.mButtonState == 2) {
            return true;
        }
        return false;
    }

    public final boolean isFirstPage() {
        Object obj = this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback;
        if (obj == null && this.mButtonState == 0) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (((PageController$SectionInfo) obj).firstPageNumber == this.mButtonState) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isLastPage() {
        if (this.mButtonState != getPageSize() - 1 && !((PageConfig) ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getPages().get(this.mButtonState)).isEndOfSection()) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean previousPage() {
        /*
            r3 = this;
            boolean r0 = r3.isFirstPage()
            if (r0 == 0) goto L8
            r0 = 0
            return r0
        L8:
            java.lang.Object r0 = r3.AccessibilityButtonMonitor$ar$accessibilityButtonCallback
            if (r0 == 0) goto L1d
            int r1 = r3.mButtonState
            com.google.android.accessibility.talkback.trainingcommon.PageController$SectionInfo r0 = (com.google.android.accessibility.talkback.trainingcommon.PageController$SectionInfo) r0
            int r2 = r0.firstPageNumber
            if (r1 != r2) goto L1d
            int r0 = r0.indexPageNumber
            r1 = 0
            r3.AccessibilityButtonMonitor$ar$accessibilityButtonCallback = r1
            r3.switchPage(r0)
            goto L24
        L1d:
            int r0 = r3.mButtonState
            int r0 = r0 + (-1)
            r3.switchPage(r0)
        L24:
            java.lang.Object r0 = r3.AccessibilityButtonMonitor$ar$mHandler
            if (r0 == 0) goto L2f
            com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory r0 = (com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory) r0
            r1 = 10
            r0.onTutorialEvent(r1)
        L2f:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor.previousPage():boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [android.hardware.display.DisplayManager$DisplayListener, java.lang.Object] */
    public final void shutdown() {
        AccessibilityButtonController accessibilityButtonController;
        AccessibilityButtonController accessibilityButtonController2;
        if (!SpannableUtils$IdentifierSpan.isAtLeastR()) {
            accessibilityButtonController = ((AccessibilityService) this.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController();
            accessibilityButtonController.unregisterAccessibilityButtonCallback(BundleUtils$$ExternalSyntheticApiModelOutline0.m(this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
            return;
        }
        ((DisplayManager) this.AccessibilityButtonMonitor$ar$displayManager).unregisterDisplayListener(this.AccessibilityButtonMonitor$ar$displayListener);
        for (Display display : ((DisplayManager) this.AccessibilityButtonMonitor$ar$displayManager).getDisplays()) {
            accessibilityButtonController2 = ((AccessibilityService) this.AccessibilityButtonMonitor$ar$mService).getAccessibilityButtonController(display.getDisplayId());
            accessibilityButtonController2.unregisterAccessibilityButtonCallback(BundleUtils$$ExternalSyntheticApiModelOutline0.m(this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void switchPage(int i) {
        Object obj;
        PageConfig currentPageConfig;
        if (i >= 0 && i < getPageSize()) {
            if (this.mButtonState != -1 && (currentPageConfig = getCurrentPageConfig()) != null) {
                ((TrainingMetricHolder) ((WindowTrackerFactory) this.AccessibilityButtonMonitor$ar$mHandler).WindowTrackerFactory$ar$handlerProvider).onTrainingPageLeft(currentPageConfig.getPageId());
            }
            this.mButtonState = i;
            PageConfig currentPageConfig2 = getCurrentPageConfig();
            if (currentPageConfig2 != null) {
                ((TrainingMetricHolder) ((WindowTrackerFactory) this.AccessibilityButtonMonitor$ar$mHandler).WindowTrackerFactory$ar$handlerProvider).onTrainingPageEntered(currentPageConfig2.getPageId());
            }
            ContextWrapper contextWrapper = this.AccessibilityButtonMonitor$ar$mService;
            int i2 = this.mButtonState;
            PageConfig pageConfig = (PageConfig) ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getPages().get(this.mButtonState);
            int i3 = this.mButtonState;
            TrainingConfig trainingConfig = (TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener;
            final boolean z = false;
            if (trainingConfig.totalPageNumber < 0) {
                trainingConfig.totalPageNumber = 0;
                ImmutableList pages = trainingConfig.getPages();
                int size = pages.size();
                for (int i4 = 0; i4 < size && ((PageConfig) pages.get(i4)).showPageNumber(); i4++) {
                    trainingConfig.totalPageNumber++;
                }
            }
            int i5 = trainingConfig.totalPageNumber;
            Object obj2 = this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback;
            if (obj2 != null) {
                PageController$SectionInfo pageController$SectionInfo = (PageController$SectionInfo) obj2;
                i3 = this.mButtonState - pageController$SectionInfo.firstPageNumber;
                i5 = pageController$SectionInfo.totalPageNumber;
            }
            Pair pair = null;
            if (pageConfig.showPageNumber() && i5 > 1) {
                pair = Pair.create(Integer.valueOf(i3 + 1), Integer.valueOf(i5));
            }
            if (isFirstPage() || getPageSize() == 1 || ((obj = this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback) != null && ((PageController$SectionInfo) obj).totalPageNumber == 1)) {
                z = true;
            }
            final PageConfig pageConfig2 = (PageConfig) ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getPages().get(this.mButtonState);
            final ImmutableList buttons = ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getButtons();
            ((TrainingActivity) contextWrapper).onPageSwitched(i2, pair, new Function() { // from class: com.google.android.accessibility.talkback.trainingcommon.PageController$$ExternalSyntheticLambda0
                public final /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj3) {
                    AccessibilityButtonMonitor accessibilityButtonMonitor = AccessibilityButtonMonitor.this;
                    Object obj4 = accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$mCallback;
                    return new NavigationButtonBar((Context) obj3, buttons, (WindowTrackerFactory) obj4, accessibilityButtonMonitor.mButtonState, z, accessibilityButtonMonitor.isLastPage(), ((TrainingConfig) accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$displayListener).isExitButtonOnlyShowOnLastPage(), ((TrainingConfig) accessibilityButtonMonitor.AccessibilityButtonMonitor$ar$displayListener).isPrevButtonShownOnFirstPage());
                }

                public final /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            });
            return;
        }
        throw new IllegalArgumentException("Out of training range. pageNumber=" + i + ", trainingSize=" + ((TrainingConfig) this.AccessibilityButtonMonitor$ar$displayListener).getPages().size() + ", sectionInfo=" + String.valueOf(this.AccessibilityButtonMonitor$ar$accessibilityButtonCallback));
    }

    public AccessibilityButtonMonitor(AccessibilityService accessibilityService) {
        this.AccessibilityButtonMonitor$ar$displayListener = new AnonymousClass1(this, 0);
        this.mButtonState = 0;
        this.AccessibilityButtonMonitor$ar$mHandler = new AccessibilityButtonCallBackHandler(this);
        this.AccessibilityButtonMonitor$ar$mService = accessibilityService;
        this.AccessibilityButtonMonitor$ar$displayManager = (DisplayManager) accessibilityService.getSystemService("display");
    }
}

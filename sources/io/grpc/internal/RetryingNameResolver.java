package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import android.animation.Animator;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.core.os.UserManagerCompat$Api24Impl;
import androidx.room.SharedSQLiteStatement$stmt$2;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplay;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.braille.brailledisplay.controller.CellsContentManager;
import com.google.android.accessibility.braille.brailledisplay.controller.ContentHelper;
import com.google.android.accessibility.braille.brailledisplay.controller.ContentHelper$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.controller.DisplayInfoWrapper;
import com.google.android.accessibility.braille.brailledisplay.controller.wrapping.EditorWordWrapStrategy;
import com.google.android.accessibility.braille.brailledisplay.controller.wrapping.WordWrapStrategy;
import com.google.android.accessibility.braille.brailledisplay.platform.BrailleDisplayManager;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda6;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableBluetoothDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.brltty.DeviceInfo;
import com.google.android.accessibility.braille.brltty.SupportedDevicesHelper;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$TutorialFinishState;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.UIManager;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.selecttospeak.activities.SelectToSpeakPreferencesActivity;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.lifecycle.ServiceState;
import com.google.android.accessibility.selecttospeak.logging.S2SPipelineAnalytics;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.android.accessibility.selecttospeak.overlayui.S2SOverlayFactory;
import com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.ui.AnimatorHelper;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.android.accessibility.selecttospeak.ui.OverlayCoordinatesAnimator;
import com.google.android.accessibility.selecttospeak.ui.TriggerButtonDragActionDetector;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.VoiceActionMonitor;
import com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.grpc.primes.PrimesCronetInterceptor;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.android.marvin.talkback.R;
import com.google.android.material.internal.CheckableGroup;
import com.google.android.material.internal.MaterialCheckable;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.textfield.EndCompoundLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.flogger.backend.MetadataKeyValueHandlers;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.encoders.json.JsonValueObjectEncoderContext;
import com.google.frameworks.client.data.android.Transport$TransportConfig;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.protobuf.ByteString;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$ConnectProtocol;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$ConnectType;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$DeviceInfo;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$SessionStartedEvent;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$SettingBrailleCode;
import io.grpc.Attributes;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.SynchronizationContext;
import io.grpc.cronet.CronetChannelBuilder;
import io.grpc.internal.CallCredentialsApplyingTransportFactory;
import io.grpc.okhttp.internal.OptionalMethod;
import io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.net.CronetEngine;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class RetryingNameResolver extends ForwardingNameResolver {
    static final Attributes.Key RESOLUTION_RESULT_LISTENER_KEY = new Attributes.Key("io.grpc.internal.RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY");
    public final RetryScheduler retryScheduler;
    public final SynchronizationContext syncContext;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ResolutionResultListener {
        public final /* synthetic */ Object RetryingNameResolver$ResolutionResultListener$ar$this$0;

        public ResolutionResultListener() {
        }

        private final boolean isUsingUsbConnection() {
            if (((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.getType$ar$edu$c2cf13b1_0() == 2) {
                return true;
            }
            return false;
        }

        public final void dismiss(int i) {
            BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, i, 0, this.RetryingNameResolver$ResolutionResultListener$ar$this$0));
        }

        public final String encode(Object obj) {
            StringWriter stringWriter = new StringWriter();
            try {
                Object obj2 = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                JsonValueObjectEncoderContext jsonValueObjectEncoderContext = new JsonValueObjectEncoderContext(stringWriter, ((JsonDataEncoderBuilder) obj2).objectEncoders, ((JsonDataEncoderBuilder) obj2).valueEncoders, ((JsonDataEncoderBuilder) obj2).fallbackEncoder, ((JsonDataEncoderBuilder) obj2).ignoreNullValues);
                jsonValueObjectEncoderContext.add$ar$ds$fc39d10f_0(obj);
                jsonValueObjectEncoderContext.jsonWriter.flush();
            } catch (IOException unused) {
            }
            return stringWriter.toString();
        }

        public final HapticPatternParser$$ExternalSyntheticLambda1 getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
            TalkBackService talkBackService = (TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            if (talkBackService.getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() == null) {
                return null;
            }
            return ((BrailleIme) talkBackService.getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        }

        public final Locale getCurrentLanguage() {
            return ((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getUserPreferredLocale();
        }

        public final Set getEmulatedSplits() {
            AtomicReference atomicReference = SplitCompat.installed;
            return ((SplitCompat) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getEmulatedSplits();
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [javax.inject.Provider, java.lang.Object] */
        public final ListenableFuture getHeader() {
            return SpannableUtils$NonCopyableTextSpan.toListenableFuture(((InternalUsageReportingClient) this.RetryingNameResolver$ResolutionResultListener$ar$this$0.get()).getSpatulaHeader());
        }

        public final PhenotypeProcessReaper getImeConnection$ar$class_merging$ar$class_merging() {
            return ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getImeConnection$ar$class_merging$ar$class_merging();
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [javax.inject.Provider, java.lang.Object] */
        public final Channel getTransportChannel(Transport$TransportConfig transport$TransportConfig) {
            boolean z;
            ThreadUtil.ensureBackgroundThread();
            CronetChannelBuilder forAddress = CronetChannelBuilder.forAddress(transport$TransportConfig.uri().getHost(), transport$TransportConfig.uri().getPort(), (CronetEngine) this.RetryingNameResolver$ResolutionResultListener$ar$this$0.get());
            ((ManagedChannelImplBuilder) forAddress.delegate()).userAgent = new CronetEngine.Builder(transport$TransportConfig.applicationContext).getDefaultUserAgent();
            ManagedChannelBuilder delegate = forAddress.delegate();
            Executor executor = transport$TransportConfig.transportExecutor;
            if (executor != null) {
                ((ManagedChannelImplBuilder) delegate).executorPool = new SharedResourcePool(executor, 1);
            } else {
                ((ManagedChannelImplBuilder) delegate).executorPool = ManagedChannelImplBuilder.DEFAULT_EXECUTOR_POOL;
            }
            Executor executor2 = transport$TransportConfig.networkExecutor;
            ManagedChannelBuilder delegate2 = forAddress.delegate();
            if (executor2 != null) {
                ((ManagedChannelImplBuilder) delegate2).offloadExecutorPool = new SharedResourcePool(executor2, 1);
            } else {
                ((ManagedChannelImplBuilder) delegate2).offloadExecutorPool = ManagedChannelImplBuilder.DEFAULT_EXECUTOR_POOL;
            }
            long j = transport$TransportConfig.grpcIdleTimeoutMillis;
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            ManagedChannelBuilder delegate3 = forAddress.delegate();
            if (j > 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, "idle timeout is %s, but must be positive", j);
            if (timeUnit.toDays(j) >= 30) {
                ((ManagedChannelImplBuilder) delegate3).idleTimeoutMillis = -1L;
            } else {
                ((ManagedChannelImplBuilder) delegate3).idleTimeoutMillis = Math.max(timeUnit.toMillis(j), ManagedChannelImplBuilder.IDLE_MODE_MIN_TIMEOUT_MILLIS);
            }
            int i = transport$TransportConfig.maxMessageSize;
            ContextDataProvider.checkArgument(true, (Object) "maxMessageSize must be >= 0");
            forAddress.maxMessageSize = i;
            Integer num = transport$TransportConfig.trafficStatsUid;
            if (num != null) {
                int intValue = num.intValue();
                forAddress.trafficStatsUidSet = true;
                forAddress.trafficStatsUid = intValue;
            }
            Integer num2 = transport$TransportConfig.trafficStatsTag;
            if (num2 != null) {
                int intValue2 = num2.intValue();
                forAddress.trafficStatsTagSet = true;
                forAddress.trafficStatsTag = intValue2;
            }
            return OnDeviceFaceMeshCreateLogEvent.intercept(forAddress.build(), new PrimesCronetInterceptor(new MetadataUtils$HeaderAttachingClientInterceptor(transport$TransportConfig.recordNetworkMetricsToPrimes, 1)));
        }

        public final void hideBrailleKeyboard() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).hideSelf();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).escapeReminder.increaseExitKeyboardCounter();
        }

        public final void inflateView() {
            ControlOverlaysAnimations controlOverlaysAnimations;
            AnimatorHelper animatorHelper;
            Animator fadeIn;
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            final UIManager uIManager = (UIManager) obj;
            int i = 0;
            LogUtils.v("UIManager", "createControlOverlays %s", uIManager.service);
            SelectToSpeakService selectToSpeakService = uIManager.service;
            selectToSpeakService.getClass();
            SelectToSpeakOverlay createOverlay = S2SOverlayFactory.INSTANCE.createOverlay(selectToSpeakService, ControlOverlays.OverlayTypes.TRIGGER_BUTTON);
            SelectToSpeakOverlay createOverlay2 = S2SOverlayFactory.INSTANCE.createOverlay(selectToSpeakService, ControlOverlays.OverlayTypes.COLLAPSED);
            SelectToSpeakOverlay createOverlay3 = S2SOverlayFactory.INSTANCE.createOverlay(selectToSpeakService, ControlOverlays.OverlayTypes.EXPANDABLE);
            createOverlay.setWatchOutsideTouch$ar$ds();
            createOverlay2.setWatchOutsideTouch$ar$ds();
            createOverlay3.setWatchOutsideTouch$ar$ds();
            NodeMenuRuleCreator nodeMenuRuleCreator = new NodeMenuRuleCreator(SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(selectToSpeakService), createOverlay, createOverlay2, createOverlay3);
            ControlOverlays controlOverlays = uIManager.controlOverlays;
            LogUtils.v("ControlOverlays", "createOverlayViews isInflated %s overlays: %s %s", Boolean.valueOf(controlOverlays.isInflated), createOverlay, createOverlay3);
            controlOverlays.triggerButtonOverlay = createOverlay;
            controlOverlays.collapsedOverlay = createOverlay2;
            controlOverlays.expandableOverlay = createOverlay3;
            controlOverlays.overlayCoordinatesSynchronizer$ar$class_merging = nodeMenuRuleCreator;
            View findViewById = createOverlay2.findViewById(R.id.fixed_control_panel);
            findViewById.getClass();
            controlOverlays.collapsedPanel = (CollapsibleControlPanel) findViewById;
            View findViewById2 = createOverlay3.findViewById(R.id.expandable_control_panel);
            findViewById2.getClass();
            controlOverlays.expandablePanel = (CollapsibleControlPanel) findViewById2;
            CollapsibleControlPanel collapsibleControlPanel = controlOverlays.collapsedPanel;
            if (collapsibleControlPanel != null) {
                collapsibleControlPanel.addControlActionListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(controlOverlays.controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
            }
            CollapsibleControlPanel collapsibleControlPanel2 = controlOverlays.expandablePanel;
            if (collapsibleControlPanel2 != null) {
                collapsibleControlPanel2.addControlActionListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(controlOverlays.controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
            }
            List list = controlOverlays._triggerButtons;
            View findViewById3 = createOverlay.findViewById(R.id.s2s_trigger_button);
            findViewById3.getClass();
            list.add((ImageButton) findViewById3);
            View findViewById4 = createOverlay2.findViewById(R.id.s2s_trigger_button);
            findViewById4.getClass();
            list.add((ImageButton) findViewById4);
            View findViewById5 = createOverlay3.findViewById(R.id.s2s_trigger_button);
            findViewById5.getClass();
            list.add((ImageButton) findViewById5);
            Iterator it = controlOverlays._triggerButtons.iterator();
            while (it.hasNext()) {
                ((ImageButton) it.next()).setOnClickListener(controlOverlays.triggerButtonClickListener);
            }
            controlOverlays.isInflated = true;
            if (uIManager.isNarrowScreen()) {
                CollapsibleControlPanel collapsibleControlPanel3 = uIManager.controlOverlays.expandablePanel;
                if (collapsibleControlPanel3 != null) {
                    collapsibleControlPanel3.setStyle(1);
                }
                if (!SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(uIManager.service) || !UserManagerCompat$Api24Impl.isUserUnlocked(uIManager.service)) {
                    ControlOverlays controlOverlays2 = uIManager.controlOverlays;
                    CollapsibleControlPanel collapsibleControlPanel4 = controlOverlays2.collapsedPanel;
                    if (collapsibleControlPanel4 != null) {
                        collapsibleControlPanel4.removeAction$ar$ds$9d6da02a_0();
                    }
                    CollapsibleControlPanel collapsibleControlPanel5 = controlOverlays2.expandablePanel;
                    if (collapsibleControlPanel5 != null) {
                        collapsibleControlPanel5.removeAction$ar$ds$9d6da02a_0();
                    }
                }
            }
            SelectToSpeakService selectToSpeakService2 = uIManager.service;
            ControlOverlays controlOverlays3 = uIManager.controlOverlays;
            final Context defaultScreenDensityContext = SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(selectToSpeakService2);
            final CollapsibleControlPanel collapsibleControlPanel6 = controlOverlays3.expandablePanel;
            uIManager.controlPanelHorizontalDragActionDetector = new CollapsibleControlPanel.CollapsibleControlPanelDragDetector(defaultScreenDensityContext, collapsibleControlPanel6) { // from class: com.google.android.accessibility.selecttospeak.UIManager.2
                @Override // com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel.CollapsibleControlPanelDragDetector, com.google.android.accessibility.selecttospeak.ui.DragActionDetector
                public final void onStartDragging(View view, float f, float f2) {
                    UIManager.this.setForegroundOverlay(ControlOverlays.OverlayTypes.EXPANDABLE);
                    super.onStartDragging(view, f, f2);
                }

                @Override // com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel.CollapsibleControlPanelDragDetector, com.google.android.accessibility.selecttospeak.ui.DragActionDetector
                public final void onStopDragging(View view, float f, float f2) {
                    super.onStopDragging(view, f, f2);
                    boolean isLayoutRtl = super.isLayoutRtl();
                    float f3 = this.startX - this.lastX;
                    if (isLayoutRtl) {
                        f3 = -f3;
                    }
                    float f4 = this.startPanelWidth;
                    int i2 = this.controlPanelMinWidth;
                    int round = Math.round(f4 + f3);
                    if (round <= i2 || (round < this.controlPanelMaxWidth && (isLayoutRtl ^ this.isDraggingTowardsRight))) {
                        UIManager.this.collapseControlPanel();
                        OptionalMethod optionalMethod = UIManager.this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                        if (optionalMethod != null) {
                            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_DRAG_TO_COLLAPSE$ar$edu);
                            return;
                        }
                        return;
                    }
                    UIManager.this.expandControlPanel();
                    OptionalMethod optionalMethod2 = UIManager.this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (optionalMethod2 != null) {
                        optionalMethod2.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_DRAG_TO_EXPAND$ar$edu);
                    }
                }
            };
            ControlOverlays controlOverlays4 = uIManager.controlOverlays;
            CollapsibleControlPanel.CollapsibleControlPanelDragDetector collapsibleControlPanelDragDetector = uIManager.controlPanelHorizontalDragActionDetector;
            collapsibleControlPanelDragDetector.getClass();
            CollapsibleControlPanel collapsibleControlPanel7 = controlOverlays4.collapsedPanel;
            if (collapsibleControlPanel7 != null) {
                collapsibleControlPanel7.onTouchListener = collapsibleControlPanelDragDetector;
            }
            CollapsibleControlPanel collapsibleControlPanel8 = controlOverlays4.expandablePanel;
            if (collapsibleControlPanel8 != null) {
                collapsibleControlPanel8.onTouchListener = collapsibleControlPanelDragDetector;
            }
            UIManager$$ExternalSyntheticLambda2 uIManager$$ExternalSyntheticLambda2 = new UIManager$$ExternalSyntheticLambda2(obj, i);
            if (collapsibleControlPanel7 != null) {
                collapsibleControlPanel7.setCollapseExpandButtonClickListener(uIManager$$ExternalSyntheticLambda2);
            }
            CollapsibleControlPanel collapsibleControlPanel9 = controlOverlays4.expandablePanel;
            if (collapsibleControlPanel9 != null) {
                collapsibleControlPanel9.setCollapseExpandButtonClickListener(uIManager$$ExternalSyntheticLambda2);
            }
            ControlOverlaysAnimations controlOverlaysAnimations2 = uIManager.overlaysAnimations;
            ControlOverlays controlOverlays5 = uIManager.controlOverlays;
            AnimatorHelper animatorHelper2 = new AnimatorHelper(controlOverlays5.expandablePanel, controlOverlays5.collapsedPanel);
            ImageButton[] imageButtonArr = (ImageButton[]) uIManager.controlOverlays._triggerButtons.toArray(new ImageButton[0]);
            AnimatorHelper animatorHelper3 = new AnimatorHelper((View[]) Arrays.copyOf(imageButtonArr, imageButtonArr.length));
            ControlOverlays controlOverlays6 = uIManager.controlOverlays;
            CollapsibleControlPanel collapsibleControlPanel10 = controlOverlays6.expandablePanel;
            collapsibleControlPanel10.getClass();
            CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator = collapsibleControlPanel10.collapseExpandAnimator;
            collapseExpandAnimator.getClass();
            OverlayCoordinatesAnimator overlayCoordinatesAnimator = new OverlayCoordinatesAnimator(controlOverlays6.overlayCoordinatesSynchronizer$ar$class_merging);
            animatorHelper2.setDuration(1, 250L);
            animatorHelper2.setDuration(2, 200L);
            controlOverlaysAnimations2.controlPanelAnimatorHelper = animatorHelper2;
            animatorHelper3.setDuration(3, 250L);
            animatorHelper3.setDuration(4, 200L);
            controlOverlaysAnimations2.triggerButtonsAnimatorHelper = animatorHelper3;
            controlOverlaysAnimations2.collapseExpandAnimator = collapseExpandAnimator;
            controlOverlaysAnimations2.overlayCoordinatesAnimator = overlayCoordinatesAnimator;
            final SelectToSpeakService selectToSpeakService3 = uIManager.service;
            final NodeMenuRuleCreator nodeMenuRuleCreator2 = uIManager.controlOverlays.overlayCoordinatesSynchronizer$ar$class_merging;
            uIManager.triggerButtonDragActionDetector = new TriggerButtonDragActionDetector(selectToSpeakService3, nodeMenuRuleCreator2) { // from class: com.google.android.accessibility.selecttospeak.UIManager.3
                @Override // com.google.android.accessibility.selecttospeak.ui.TriggerButtonDragActionDetector, com.google.android.accessibility.selecttospeak.ui.DragActionDetector
                public final void onStartDragging(View view, float f, float f2) {
                    super.onStartDragging(view, f, f2);
                    UIManager.this.service.activateMultitaskingIfNecessary();
                }

                /* JADX WARN: Type inference failed for: r0v12, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
                /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
                @Override // com.google.android.accessibility.selecttospeak.ui.TriggerButtonDragActionDetector, com.google.android.accessibility.selecttospeak.ui.DragActionDetector
                public final void onStopDragging(View view, float f, float f2) {
                    int i2;
                    int i3;
                    float[] fArr = new float[2];
                    NodeMenuRuleCreator nodeMenuRuleCreator3 = UIManager.this.controlOverlays.overlayCoordinatesSynchronizer$ar$class_merging;
                    if (nodeMenuRuleCreator3 != null) {
                        nodeMenuRuleCreator3.NodeMenuRuleCreator$ar$ruleViewPager.getPixelCoordinates((int[]) nodeMenuRuleCreator3.NodeMenuRuleCreator$ar$ruleUnlabeledNode);
                        nodeMenuRuleCreator3.NodeMenuRuleCreator$ar$ruleViewPager.pixelToFractionalCoordinates((int[]) nodeMenuRuleCreator3.NodeMenuRuleCreator$ar$ruleUnlabeledNode, fArr);
                    }
                    UIManager uIManager2 = UIManager.this;
                    boolean z = uIManager2.isTriggerButtonAppearanceActive;
                    SharedPreferences.Editor edit = SpannableUtils$IdentifierSpan.getSharedPreferences(uIManager2.service).edit();
                    float f3 = fArr[0];
                    if (uIManager2.isLayoutRtl()) {
                        f3 = 1.0f - f3;
                    }
                    float f4 = fArr[1];
                    SelectToSpeakService selectToSpeakService4 = uIManager2.service;
                    if (z) {
                        i2 = R.string.pref_active_fab_x_as_int_bit_key;
                    } else {
                        i2 = R.string.pref_fab_x_as_int_bit_key;
                    }
                    edit.putInt(selectToSpeakService4.getString(i2), Float.floatToIntBits(SpannableUtils$IdentifierSpan.clampValue(f3, 0.0f, 1.0f)));
                    SelectToSpeakService selectToSpeakService5 = uIManager2.service;
                    if (true != z) {
                        i3 = R.string.pref_fab_y_as_int_bit_key;
                    } else {
                        i3 = R.string.pref_active_fab_y_as_int_bit_key;
                    }
                    edit.putInt(selectToSpeakService5.getString(i3), Float.floatToIntBits(SpannableUtils$IdentifierSpan.clampValue(f4, 0.0f, 1.0f)));
                    edit.apply();
                    ControlOverlaysAnimations controlOverlaysAnimations3 = UIManager.this.overlaysAnimations;
                    OverlayCoordinatesAnimator overlayCoordinatesAnimator2 = controlOverlaysAnimations3.overlayCoordinatesAnimator;
                    if (overlayCoordinatesAnimator2 != null) {
                        overlayCoordinatesAnimator2.cancel();
                        overlayCoordinatesAnimator2.removeAllListeners();
                        overlayCoordinatesAnimator2.overlayCoordinatesSynchronizer$ar$class_merging.getPixelCoordinates(overlayCoordinatesAnimator2.fromPixelCoordinates);
                        overlayCoordinatesAnimator2.toPixelCoordinates[0] = SpannableUtils$IdentifierSpan.clampValue$ar$ds(overlayCoordinatesAnimator2.fromPixelCoordinates[0], overlayCoordinatesAnimator2.overlayCoordinatesSynchronizer$ar$class_merging.getMaxWindowX());
                        overlayCoordinatesAnimator2.toPixelCoordinates[1] = SpannableUtils$IdentifierSpan.clampValue$ar$ds(overlayCoordinatesAnimator2.fromPixelCoordinates[1], overlayCoordinatesAnimator2.overlayCoordinatesSynchronizer$ar$class_merging.getMaxWindowY());
                        overlayCoordinatesAnimator2.setFloatValues(0.0f, 1.0f);
                        overlayCoordinatesAnimator2.setInterpolator(overlayCoordinatesAnimator2.snapToSideInterpolator);
                        overlayCoordinatesAnimator2.setDuration(250L);
                    }
                    OverlayCoordinatesAnimator overlayCoordinatesAnimator3 = controlOverlaysAnimations3.overlayCoordinatesAnimator;
                    if (overlayCoordinatesAnimator3 != null) {
                        overlayCoordinatesAnimator3.start();
                    }
                    OptionalMethod optionalMethod = UIManager.this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (optionalMethod != null) {
                        optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_DRAG_TO_MOVE$ar$edu);
                    }
                }
            };
            Iterator it2 = uIManager.controlOverlays._triggerButtons.iterator();
            while (it2.hasNext()) {
                ((ImageButton) it2.next()).setOnTouchListener(uIManager.triggerButtonDragActionDetector);
            }
            UIManager uIManager2 = (UIManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ControlOverlays controlOverlays7 = uIManager2.controlOverlays;
            if (controlOverlays7.isInflated) {
                controlOverlays7.collapse$ar$ds();
                uIManager2.overlaysAnimations.hideInitialViewsImmediately();
                uIManager2.updateTriggerButtonAppearance(false);
                uIManager2.workingBoardOverlay.setPixelCoordinates(0, 0);
                float[] fArr = new float[2];
                uIManager2.loadTriggerButtonFractionalCoordinates(false, fArr);
                uIManager2.controlOverlays.setFractionalCoordinates(fArr[0], fArr[1]);
                if (!uIManager2.isA11yButtonSupported && (animatorHelper = (controlOverlaysAnimations = uIManager2.overlaysAnimations).triggerButtonsAnimatorHelper) != null && (fadeIn = animatorHelper.fadeIn()) != null) {
                    controlOverlaysAnimations.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging.runAnimation$ar$ds(fadeIn, new SharedSQLiteStatement$stmt$2(controlOverlaysAnimations, 14));
                }
            }
        }

        public final boolean isConnectionValid() {
            return ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isConnectionValid();
        }

        public final boolean isTouchInteracting() {
            return ((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isBrailleImeTouchInteracting();
        }

        public final boolean isViewInflated() {
            return ((UIManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).controlOverlays.isInflated;
        }

        /* renamed from: lambda$onSelectionEnd$0$com-google-android-accessibility-selecttospeak-SelectToSpeakService$4, reason: not valid java name */
        public final /* synthetic */ void m245xf2ec3d4(Boolean bool) {
            if (!bool.booleanValue()) {
                ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).abortHandlingSelection();
            } else {
                ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.MARK_ZONE_TO_SPEAK$ar$edu);
            }
        }

        public final void onAction(int i) {
            LogUtils.d("SelectToSpeakService", "Control action performed. Action=%s", Integer.valueOf(i));
            SelectToSpeakService selectToSpeakService = (SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            if (selectToSpeakService.job == null && i != 6 && i != 8) {
                LogUtils.d("SelectToSpeakService", "Ignore non-PLAY and non-SETTING because there is no active S2SJob.", new Object[0]);
                return;
            }
            if (!selectToSpeakService.uIManager.isUIStable()) {
                LogUtils.d("SelectToSpeakService", "Ignore control action because UI is not stable.", new Object[0]);
                return;
            }
            if (i != 5 && ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).uIManager.isControlPanelExpanded()) {
                ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).uIManager.scheduleCollapseControlPanelAction();
            }
            switch (i) {
                case 1:
                    ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job.reduceSpeechRate();
                    return;
                case 2:
                    ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job.increaseSpeechRate();
                    return;
                case 3:
                    ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job.previousItem();
                    return;
                case 4:
                    ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job.nextItem();
                    return;
                case 5:
                    ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job.stop();
                    return;
                case 6:
                    if (((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).serviceState.isIdle()) {
                        ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).serviceState.setCurrent$ar$edu(ServiceState.State.PROCESSING_CONTINUOUS_READING$ar$edu);
                        ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).handleContinuousReadingRequest(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(this, 7));
                        return;
                    } else {
                        SelectToSpeakJob selectToSpeakJob = ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job;
                        if (selectToSpeakJob != null) {
                            selectToSpeakJob.resume();
                            return;
                        }
                        return;
                    }
                case 7:
                    ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).job.pause();
                    return;
                default:
                    Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                    SelectToSpeakService selectToSpeakService2 = (SelectToSpeakService) obj;
                    selectToSpeakService2.prepareToChangeActivity();
                    Intent intent = new Intent((Context) obj, (Class<?>) SelectToSpeakPreferencesActivity.class);
                    intent.addFlags(268435456);
                    selectToSpeakService2.startActivity(intent);
                    return;
            }
        }

        public final void onBatteryVolumePercentageChanged(int i) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "onBatteryChanged: "));
            ((UsbConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).showBatteryLowDialog();
        }

        public final void onBluetoothTurnedOff() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onBluetoothTurnedOff");
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onConnectivityEnabled(false);
        }

        public final void onBluetoothTurnedOn() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onBluetoothTurnedOn");
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onConnectivityEnabled(true);
        }

        public final void onBonded(BluetoothDevice bluetoothDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onBonded: ".concat(String.valueOf(bluetoothDevice.getName())));
            BtConnectManager btConnectManager = (BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            if (btConnectManager.isConnecting() && btConnectManager.btConnector.device.address().equals(bluetoothDevice.getAddress())) {
                ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).btConnector.connect();
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            onDeviceTextDetectionLoadLogEvent.setBluetoothDevice$ar$ds(bluetoothDevice);
            ((BtConnectManager) obj).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceSeenOrUpdated(onDeviceTextDetectionLoadLogEvent.build());
        }

        public final void onBrailleImeActivated() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).activateBrailleIme();
        }

        public final void onBrailleImeInactivated() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).deactivateBrailleIme();
        }

        public final void onBrailleInputEvent(BrailleInputEvent brailleInputEvent) {
            if (((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).canSendRenderPackets()) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("BrailleDisplayManager", "onReadCommandArrived ".concat(String.valueOf(String.valueOf(brailleInputEvent))));
                if (((KeyguardManager) ((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).context.getSystemService("keyguard")).isKeyguardLocked()) {
                    Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                    ((BrailleDisplayManager) obj).wakeLock.acquire(0L);
                    try {
                        ((BrailleDisplayManager) obj).wakeLock.release();
                    } catch (RuntimeException unused) {
                    }
                }
                ((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).controller$ar$class_merging.onBrailleInputEvent(brailleInputEvent);
            }
        }

        public final void onCheckedChanged(MaterialCheckable materialCheckable, boolean z) {
            if (z) {
                if (!((CheckableGroup) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).checkInternal(materialCheckable)) {
                    return;
                }
            } else {
                CheckableGroup checkableGroup = (CheckableGroup) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                if (!checkableGroup.uncheckInternal(materialCheckable, checkableGroup.selectionRequired)) {
                    return;
                }
            }
            ((CheckableGroup) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).onCheckedStateChanged();
        }

        public final void onComplete() {
            if (((CallCredentialsApplyingTransportFactory.CallCredentialsApplyingTransport) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).pendingApplier.decrementAndGet() == 0) {
                ((CallCredentialsApplyingTransportFactory.CallCredentialsApplyingTransport) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).maybeShutdown();
            }
        }

        public final void onConnectFailure(ConnectableDevice connectableDevice, final boolean z, Exception exc) {
            final String name;
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onConnectFailure: ".concat(String.valueOf(exc.getMessage())));
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.disconnect();
            if (connectableDevice == null) {
                name = null;
            } else {
                name = connectableDevice.name();
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ((Connectioneer) obj).aspectConnection.notifyListeners(new Consumer() { // from class: com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj2) {
                    ((Connectioneer.AspectConnection.Callback) obj2).onConnectFailed$ar$ds(name);
                }

                public final /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        }

        public final void onConnectStarted$ar$edu(int i) {
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(1));
        }

        public final void onConnected(D2dConnection d2dConnection) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onConnectSuccess");
            ConnectableDevice device = d2dConnection.getDevice();
            if (device != null && device.name() != null && ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.getType$ar$edu$c2cf13b1_0() == 1) {
                Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                AppCompatDelegate.Api24Impl.addRememberedDevice(((Connectioneer) obj).context, new Pair(device.name(), device.address()));
            }
            d2dConnection.open$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).d2dConnectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
            Object obj2 = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ((Connectioneer) obj2).aspectConnection.notifyConnectionStatusChanged$ar$edu(1, d2dConnection.getDevice());
        }

        public final void onConnectivityEnabled(boolean z) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onConnectivityEnabled: " + z);
            if (((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isBrailleDisplayEnabled()) {
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.startSearch$ar$edu(5);
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).autoConnectIfPossibleToBondedDevice$ar$edu(6);
            } else {
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.stopSearch$ar$edu(6);
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.disconnect();
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyDeviceListCleared();
            }
        }

        public final void onDetectionChanged(boolean z, boolean z2) {
            int i;
            String string;
            if (z) {
                i = R.string.switch_to_tabletop_announcement;
            } else {
                i = R.string.switch_to_screen_away_announcement;
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            String string2 = ((BrailleIme) obj).getString(i);
            if (((BrailleIme) obj).keyboardView.inTwoStepCalibration()) {
                if (!z2) {
                    string = ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getTwoStepsCalibrationAnnounceString$ar$edu(1);
                }
                string = "";
            } else {
                if (z) {
                    BrailleIme brailleIme = (BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                    string = brailleIme.getString(R.string.calibration_tip_announcement, new Object[]{Integer.valueOf(BrailleUserPreferences.getCurrentTypingLanguageType$ar$edu(brailleIme.getApplicationContext()))});
                }
                string = "";
            }
            SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = null;
            if (z2) {
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(string2, 800, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
                ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).calibrationAnnouncementHandler.postDelayed(new WorkerKt$$ExternalSyntheticLambda0(string, 12), 800L);
            } else {
                AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), string2, 1);
                ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).calibrationAnnouncementHandler.removeCallbacksAndMessages(null);
                Object obj2 = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                OnDeviceTextDetectionLoadLogEvent instance$ar$class_merging$8b242409_0$ar$class_merging = OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging();
                if (((BrailleIme) obj2).keyboardView.inTwoStepCalibration()) {
                    BrailleIme brailleIme2 = (BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                    utteranceCompleteRunnable = brailleIme2.getRepeatAnnouncementRunnable(brailleIme2.getRepeatedTwoStepCalibrationAnnounceString$ar$edu(1));
                }
                instance$ar$class_merging$8b242409_0$ar$class_merging.speak(string, 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, utteranceCompleteRunnable));
            }
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).keyboardView.setTableMode(z);
        }

        public final void onDeviceDeleted(ConnectableDevice connectableDevice) {
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            AppCompatDelegate.Api24Impl.deleteRememberedDevice(((Connectioneer) obj).context, connectableDevice.address());
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(connectableDevice, 1));
        }

        public final void onDeviceListCleared() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onDeviceListCleared");
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyDeviceListCleared();
        }

        public final void onDeviceSeen(BluetoothDevice bluetoothDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onDeviceSeen");
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            onDeviceTextDetectionLoadLogEvent.setBluetoothDevice$ar$ds(bluetoothDevice);
            ConnectableBluetoothDevice build = onDeviceTextDetectionLoadLogEvent.build();
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).foundDevices.add(build);
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceSeenOrUpdated(build);
        }

        public final void onDeviceSeenOrUpdated(ConnectableDevice connectableDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onDeviceSeen");
            if (((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).allowDevice(connectableDevice.name())) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onDeviceSeen allow device seen: " + connectableDevice.name() + ": " + connectableDevice.address());
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).autoConnectIfPossible$ar$edu(new SingletonImmutableSet(connectableDevice), 3);
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(connectableDevice, 0));
            }
        }

        public final void onDisconnected() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onDisconnected");
            Connectioneer connectioneer = (Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            connectioneer.displayProperties = null;
            connectioneer.aspectConnection.notifyConnectionStatusChanged$ar$edu(2, null);
        }

        public final void onDiscoveryFinished() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onDiscoveryFinished");
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSearchStatusChanged();
            BtConnectManager btConnectManager = (BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            if (btConnectManager.btAdapter != null && btConnectManager.isScanningOngoing() && ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).mayScan()) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onDiscoveryFinished restart discovery");
                ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).btAdapter.startDiscovery();
            } else {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onDiscoveryFinished do not restart discovery");
            }
        }

        public final void onDiscoveryStarted() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onDiscoveryStarted");
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSearchStatusChanged();
        }

        public final void onDismiss(View view) {
            if (view.getParent() != null) {
                view.setVisibility(8);
            }
            ((BaseTransientBottomBar) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).dispatchDismiss(0);
        }

        public final void onDisplayReady(BrailleDisplayProperties brailleDisplayProperties) {
            boolean z;
            String str;
            int i;
            int i2;
            if (((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).canSendRenderPackets()) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplayManager", "onDisplayReady");
                Connectioneer.AspectDisplayProperties aspectDisplayProperties = ((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectioneer.aspectDisplayProperties;
                aspectDisplayProperties.connectioneer.displayProperties = brailleDisplayProperties;
                aspectDisplayProperties.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(aspectDisplayProperties, 2));
                Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("BdController", "onDisplayerReady");
                BrailleDisplayManager brailleDisplayManager = (BrailleDisplayManager) obj;
                BdController bdController = brailleDisplayManager.controller$ar$class_merging;
                bdController.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitFeedback(FeedbackManager$Type.DISPLAY_CONNECTED);
                bdController.talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.switchInputMethodToBrailleKeyboard();
                Displayer displayer = brailleDisplayManager.displayer;
                bdController.displayer = displayer;
                bdController.overlayDisplay.start(displayer.displayProperties.numTextCells);
                CellsContentManager cellsContentManager = bdController.cellsContentManager;
                int i3 = displayer.displayProperties.numTextCells;
                cellsContentManager.preferredWrapStrategy = new WordWrapStrategy(i3);
                cellsContentManager.editingWrapStrategy = new EditorWordWrapStrategy(i3);
                cellsContentManager.commonDisplayInfoWrapper = new DisplayInfoWrapper(new ContentHelper(cellsContentManager.translatorManager, cellsContentManager.wrapStrategyRetriever));
                boolean z2 = false;
                z2 = false;
                cellsContentManager.timedMessageDisplayInfoWrapper = new DisplayInfoWrapper(new ContentHelper(cellsContentManager.translatorManager, new ContentHelper$$ExternalSyntheticLambda0(new WordWrapStrategy(i3), z2 ? 1 : 0)));
                cellsContentManager.translatorManager.addOnOutputTablesChangedListener(cellsContentManager.outputCodeChangedListener);
                BrailleUserPreferences.getSharedPreferences$ar$ds(cellsContentManager.context).registerOnSharedPreferenceChangeListener(cellsContentManager.onSharedPreferenceChangeListener);
                bdController.eventManager.onActivate();
                if (bdController.isBrailleKeyboardActivated()) {
                    bdController.getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().onBrailleDisplayConnected();
                }
                boolean readContractedMode = BrailleUserPreferences.readContractedMode(bdController.context);
                BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(bdController.context);
                if (readCurrentActiveInputCodeAndCorrect.isSupportsContracted(bdController.context) && readContractedMode) {
                    z = true;
                } else {
                    z = false;
                }
                BrailleLanguages.Code readCurrentActiveOutputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect(bdController.context);
                if (readCurrentActiveOutputCodeAndCorrect.isSupportsContracted(bdController.context) && readContractedMode) {
                    z2 = true;
                }
                ConnectableDevice connectableDevice = bdController.displayer.device;
                BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(bdController.context);
                String str2 = bdController.displayer.displayProperties.driverCode;
                String name = connectableDevice.name();
                Iterator it = SupportedDevicesHelper.supportedDevices.iterator();
                while (true) {
                    if (it.hasNext()) {
                        DeviceInfo match = ((SupportedDevicesHelper.NameRegexSupportedDevice) it.next()).match(name);
                        if (match != null) {
                            str = match.modelName;
                            break;
                        }
                    } else {
                        str = "";
                        break;
                    }
                }
                boolean z3 = connectableDevice.useHid;
                boolean z4 = connectableDevice instanceof ConnectableBluetoothDevice;
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$SessionStartedEvent.DEFAULT_INSTANCE.createBuilder();
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$DeviceInfo.DEFAULT_INSTANCE.createBuilder();
                if (str2 != null) {
                    builder2.copyOnWrite();
                    BraillebackLogProto$DeviceInfo braillebackLogProto$DeviceInfo = (BraillebackLogProto$DeviceInfo) builder2.instance;
                    braillebackLogProto$DeviceInfo.bitField0_ |= 1;
                    braillebackLogProto$DeviceInfo.displayDriver_ = str2;
                }
                if (str != null) {
                    builder2.copyOnWrite();
                    BraillebackLogProto$DeviceInfo braillebackLogProto$DeviceInfo2 = (BraillebackLogProto$DeviceInfo) builder2.instance;
                    braillebackLogProto$DeviceInfo2.bitField0_ |= 2;
                    braillebackLogProto$DeviceInfo2.displayName_ = str;
                }
                builder.copyOnWrite();
                BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                BraillebackLogProto$DeviceInfo braillebackLogProto$DeviceInfo3 = (BraillebackLogProto$DeviceInfo) builder2.build();
                braillebackLogProto$DeviceInfo3.getClass();
                braillebackLogProto$SessionStartedEvent.deviceInfo_ = braillebackLogProto$DeviceInfo3;
                braillebackLogProto$SessionStartedEvent.bitField0_ |= 256;
                BraillebackLogProto$SettingBrailleCode brailleCodeSetting$ar$ds = BrailleDisplayAnalytics.getBrailleCodeSetting$ar$ds(readCurrentActiveInputCodeAndCorrect, z);
                builder.copyOnWrite();
                BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent2 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                brailleCodeSetting$ar$ds.getClass();
                braillebackLogProto$SessionStartedEvent2.settingBrailleInputCode_ = brailleCodeSetting$ar$ds;
                braillebackLogProto$SessionStartedEvent2.bitField0_ |= 2;
                BraillebackLogProto$SettingBrailleCode brailleCodeSetting$ar$ds2 = BrailleDisplayAnalytics.getBrailleCodeSetting$ar$ds(readCurrentActiveOutputCodeAndCorrect, z2);
                builder.copyOnWrite();
                BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent3 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                brailleCodeSetting$ar$ds2.getClass();
                braillebackLogProto$SessionStartedEvent3.settingBrailleOutputCode_ = brailleCodeSetting$ar$ds2;
                braillebackLogProto$SessionStartedEvent3.bitField0_ |= 4;
                long j = brailleDisplayAnalytics.connectToBrailleDisplayStartTimeMs;
                if (j != 0) {
                    long j2 = brailleDisplayAnalytics.connectToRfcommStartTimeMs;
                    if (j2 == 0) {
                        long j3 = brailleDisplayAnalytics.connectToHidStartTimeMs;
                        if (j3 != 0) {
                            builder.copyOnWrite();
                            BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent4 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                            braillebackLogProto$SessionStartedEvent4.bitField0_ |= 64;
                            braillebackLogProto$SessionStartedEvent4.hidConnectingTimeMs_ = j - j3;
                        }
                    } else {
                        long j4 = j2 - brailleDisplayAnalytics.connectToHidStartTimeMs;
                        builder.copyOnWrite();
                        BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent5 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                        braillebackLogProto$SessionStartedEvent5.bitField0_ |= 64;
                        braillebackLogProto$SessionStartedEvent5.hidConnectingTimeMs_ = j4;
                        long j5 = brailleDisplayAnalytics.connectToBrailleDisplayStartTimeMs - brailleDisplayAnalytics.connectToRfcommStartTimeMs;
                        builder.copyOnWrite();
                        BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent6 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                        braillebackLogProto$SessionStartedEvent6.bitField0_ |= 8;
                        braillebackLogProto$SessionStartedEvent6.bluetoothConnectingTimeMs_ = j5;
                    }
                    long currentTimeMillis = System.currentTimeMillis() - brailleDisplayAnalytics.connectToBrailleDisplayStartTimeMs;
                    builder.copyOnWrite();
                    BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent7 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                    braillebackLogProto$SessionStartedEvent7.bitField0_ |= 16;
                    braillebackLogProto$SessionStartedEvent7.brailleDisplayConnectingTimeMs_ = currentTimeMillis;
                }
                brailleDisplayAnalytics.logConnectionReset();
                if (z4) {
                    i = BraillebackLogProto$ConnectType.CONNECT_TYPE_BLUETOOTH$ar$edu;
                } else {
                    i = BraillebackLogProto$ConnectType.CONNECT_TYPE_USB$ar$edu;
                }
                builder.copyOnWrite();
                BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent8 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                if (i != 0) {
                    braillebackLogProto$SessionStartedEvent8.connectType_ = i - 1;
                    braillebackLogProto$SessionStartedEvent8.bitField0_ |= 32;
                    if (z4) {
                        i2 = BraillebackLogProto$ConnectProtocol.CONNECT_PROTOCOL_RFCOMM$ar$edu;
                    } else {
                        i2 = BraillebackLogProto$ConnectProtocol.CONNECT_PROTOCOL_SERIAL$ar$edu;
                    }
                    builder.copyOnWrite();
                    BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent9 = (BraillebackLogProto$SessionStartedEvent) builder.instance;
                    int i4 = i2 - 1;
                    if (i2 != 0) {
                        braillebackLogProto$SessionStartedEvent9.connectProtocol_ = i4;
                        braillebackLogProto$SessionStartedEvent9.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                        BraillebackLogProto$SessionStartedEvent braillebackLogProto$SessionStartedEvent10 = (BraillebackLogProto$SessionStartedEvent) builder.build();
                        builder3.copyOnWrite();
                        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder3.instance;
                        braillebackLogProto$SessionStartedEvent10.getClass();
                        braillebackLogProto$BraillebackExtension.sessionStartedEvent_ = braillebackLogProto$SessionStartedEvent10;
                        braillebackLogProto$BraillebackExtension.bitField0_ |= 1;
                        brailleDisplayAnalytics.sendLogs((BraillebackLogProto$BraillebackExtension) builder3.build());
                        return;
                    }
                    throw null;
                }
                throw null;
            }
        }

        public final void onEditTextAttached(TextInputLayout textInputLayout) {
            EndCompoundLayout endCompoundLayout = (EndCompoundLayout) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            EditText editText = endCompoundLayout.editText;
            if (editText == textInputLayout.editText) {
                return;
            }
            if (editText != null) {
                editText.removeTextChangedListener(endCompoundLayout.editTextWatcher);
                if (((EndCompoundLayout) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).editText.getOnFocusChangeListener() == ((EndCompoundLayout) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getEndIconDelegate().getOnEditTextFocusChangeListener()) {
                    ((EndCompoundLayout) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).editText.setOnFocusChangeListener(null);
                }
            }
            EndCompoundLayout endCompoundLayout2 = (EndCompoundLayout) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            endCompoundLayout2.editText = textInputLayout.editText;
            EditText editText2 = endCompoundLayout2.editText;
            if (editText2 != null) {
                editText2.addTextChangedListener(endCompoundLayout2.editTextWatcher);
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ((EndCompoundLayout) obj).getEndIconDelegate().onEditTextAttached(((EndCompoundLayout) obj).editText);
            EndCompoundLayout endCompoundLayout3 = (EndCompoundLayout) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            endCompoundLayout3.setOnFocusChangeListenersIfNeeded(endCompoundLayout3.getEndIconDelegate());
        }

        /* JADX WARN: Type inference failed for: r1v3, types: [io.grpc.internal.ConnectionClientTransport, java.lang.Object] */
        public final void onFailure$ar$ds() {
            ((RemoteModelManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).RemoteModelManager$ar$remoteModelManagerInstances.shutdownNow(Status.UNAVAILABLE.withDescription("Keepalive failed. The connection is likely gone"));
        }

        public final void onFinished() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onFinished");
            if (!AppCompatDelegate.Api24Impl.isConnectionEnabled(((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).context)) {
                Connectioneer connectioneer = (Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                if (!connectioneer.usbConnected) {
                    connectioneer.figureEnablement();
                }
            }
        }

        public final void onLaunchSettings() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).contextMenuDialogCallback.onLaunchSettings();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).brailleImeAnalytics.sendTutorialFinishLog$ar$edu(BrailleImeLogProto$TutorialFinishState.FINISHED_BY_LAUNCH_SETTINGS$ar$edu);
        }

        public final void onLock() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", "onLock");
            ((UsbConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).dismissAllDialogs();
        }

        public final void onPlaySound(FeedbackManager$Type feedbackManager$Type) {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitFeedback(feedbackManager$Type);
        }

        public final void onRead() {
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectTraffic.notifyListeners(new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(5));
        }

        public final void onRestartTutorial() {
            BrailleIme brailleIme = (BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            brailleIme.deactivateIfNeeded$ar$ds();
            brailleIme.activateIfNeeded();
        }

        public final void onScreenBright() {
            if (((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() != null) {
                ((BrailleIme) ((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).keyboardView.setKeyboardViewTransparent(false);
            }
        }

        public final void onScreenDim() {
            if (((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() != null) {
                ((BrailleIme) ((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).keyboardView.setKeyboardViewTransparent(true);
            }
        }

        public final void onScreenOff() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onScreenOff");
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.stopSearch$ar$edu(10);
        }

        public final void onScreenOn() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onScreenOn");
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.startSearch$ar$edu(3);
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).autoConnectIfPossibleToBondedDevice$ar$edu(7);
        }

        public final void onSearchFailure() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onSearchFailure");
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyScanningChanged();
        }

        public final void onSearchStatusChanged() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onSearchStatusChanged");
            ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).aspectConnection.notifyScanningChanged();
        }

        public final void onSelectionEnd(Rect rect) {
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ((SelectToSpeakService) obj).serviceState.setCurrent$ar$edu(ServiceState.State.PROCESSING_SELECTION$ar$edu);
            S2SPipelineAnalytics s2SPipelineAnalytics = ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).pipelineAnalytics;
            if (s2SPipelineAnalytics.stopWatch.isRunning && s2SPipelineAnalytics.elapsedMetrics.containsKey("KEY_S2S_UI_READY_TO_SELECTION_START_MS") && s2SPipelineAnalytics.elapsedMetrics.keySet().size() == 1) {
                s2SPipelineAnalytics.recordElapsed("KEY_S2S_SELECTION_START_TO_END_MS");
            } else {
                s2SPipelineAnalytics.cleanupInMemoryMetrics();
            }
            SelectToSpeakService selectToSpeakService = (SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            selectToSpeakService.mlastSelection = rect;
            if (selectToSpeakService.screenshot == null && selectToSpeakService.isScreenshotRequiredOnSelection()) {
                return;
            }
            ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).handleSelection(rect, false, new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(this, 8));
        }

        public final void onSelectionStart$ar$ds() {
            S2SPipelineAnalytics s2SPipelineAnalytics = ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).pipelineAnalytics;
            if (s2SPipelineAnalytics.stopWatch.isRunning && s2SPipelineAnalytics.elapsedMetrics.isEmpty()) {
                s2SPipelineAnalytics.recordElapsed("KEY_S2S_UI_READY_TO_SELECTION_START_MS");
            } else {
                s2SPipelineAnalytics.cleanupInMemoryMetrics();
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ((SelectToSpeakService) obj).serviceState.setCurrent$ar$edu(ServiceState.State.DRAWING$ar$edu);
            if (((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isScreenshotRequiredOnSelection()) {
                if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
                    SelectToSpeakService selectToSpeakService = SelectToSpeakService.getInstance();
                    if (selectToSpeakService != null) {
                        SpannableUtils$NonCopyableTextSpan.takeScreenshot(selectToSpeakService, ((SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).captureListener);
                        return;
                    }
                    return;
                }
                SelectToSpeakService selectToSpeakService2 = (SelectToSpeakService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                selectToSpeakService2.screenCaptureController.requestScreenCaptureAsync(selectToSpeakService2.captureListener);
            }
        }

        public final void onSelectorOverlayShown(CharSequence charSequence) {
            BrailleDisplay brailleDisplay = ((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).brailleDisplay;
            if (brailleDisplay != null && brailleDisplay.isRunning) {
                brailleDisplay.controller.onReadingControlChanged(charSequence);
            }
        }

        public final void onSendPacketToDisplay(byte[] bArr) {
            if (((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).canSendPackets()) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("BrailleDisplayManager", "onSendPacketToDisplay");
                ((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectioneer.aspectTraffic.connectioneer.connectManagerProxy.sendOutgoingPacket(bArr);
            }
        }

        public final void onSsbServiceActivated() {
            VoiceActionMonitor voiceActionMonitor = (VoiceActionMonitor) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            if (!voiceActionMonitor.isHeadphoneOn()) {
                voiceActionMonitor.interruptTalkBackAudio(0);
            }
        }

        public final void onStartFailed() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.e("BrailleDisplayManager", "onStartFailed");
            BrailleDisplayManager brailleDisplayManager = (BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            brailleDisplayManager.connectioneer.aspectConnection.disconnectFromDevice(brailleDisplayManager.displayer.device.address());
            ((BrailleDisplayManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).displayer.stop();
        }

        public final void onSwitchToNextIme() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).switchToNextInputMethod();
        }

        public final void onSwitchToNextInputMethod() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).switchToNextInputMethod();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).brailleImeAnalytics.sendTutorialFinishLog$ar$edu(BrailleImeLogProto$TutorialFinishState.FINISHED_BY_SWITCH_TO_NEXT_INPUTMETHOD$ar$edu);
        }

        public final void onSwitchToOnscreenKeyboard() {
            _BOUNDARY.d("BrailleIme", "onStripClicked");
            BrailleIme brailleIme = (BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            brailleIme.brailleDisplayConnectedAndNotSuspended = false;
            brailleIme.updateInputView();
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getString(R.string.switch_on_screen_keyboard_announcement), 1);
            BrailleIme brailleIme2 = (BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            brailleIme2.keyboardView.setTableMode(brailleIme2.isCurrentTableTopMode());
            BrailleDisplay brailleDisplay = (BrailleDisplay) BrailleIme.brailleDisplayForBrailleIme;
            if (brailleDisplay.isRunning) {
                BdController.AnonymousClass1 anonymousClass1 = (BdController.AnonymousClass1) brailleDisplay.controller.brailleDisplayForBrailleIme;
                BrailleDisplayAnalytics.getInstance(BdController.this.context).logChangeTypingMode(false);
                BdController.this.suspended.set(true);
            }
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).updateNavigationBarColor();
        }

        public final void onTutorialFinished() {
            Context applicationContext = ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).getApplicationContext();
            BrailleUserPreferences.getSharedPreferences$ar$ds(applicationContext).edit().putBoolean(applicationContext.getString(R.string.pref_brailleime_auto_launch_tutorial), false).apply();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).tutorialState$ar$edu = 1;
            ((TalkBackForBrailleImeImpl) BrailleIme.talkBackForBrailleIme).proximitySensorListener.reloadSilenceOnProximity();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).activateBrailleIme();
            BrailleIme brailleIme = (BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            brailleIme.keyboardView.createAndAddInputView(brailleIme.inputPlaneCallback);
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).escapeReminder.startTimer();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).brailleImeAnalytics.sendTutorialFinishLog$ar$edu(BrailleImeLogProto$TutorialFinishState.FINISHED_BY_TUTORIAL_COMPLETED$ar$edu);
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).layoutOrientator$ar$class_merging.startIfNeeded();
        }

        public final void onUnBonded(BluetoothDevice bluetoothDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onUnBonded: ".concat(String.valueOf(bluetoothDevice.getName())));
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            onDeviceTextDetectionLoadLogEvent.setBluetoothDevice$ar$ds(bluetoothDevice);
            ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceDeleted(onDeviceTextDetectionLoadLogEvent.build());
        }

        public final void onUnlock() {
            AppCompatTextViewAutoSizeHelper.Api23Impl.i("UsbConnectManager", "onUnlock");
            ((UsbConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).showBatteryLowDialog();
        }

        public final void onUsbAttached(ConnectableDevice connectableDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onUsbAttached");
            if (((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).shouldUseUsbConnection()) {
                Connectioneer connectioneer = (Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
                connectioneer.usbConnected = true;
                if (!SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(connectioneer.context)) {
                    AppCompatDelegate.Api24Impl.setConnectionEnabled(((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).context, true);
                }
                if (isUsingUsbConnection()) {
                    ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.startSearch$ar$edu(8);
                    if (!((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isConnectingOrConnected()) {
                        ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).submitConnectionRequest$ar$edu(connectableDevice, 9);
                        return;
                    }
                    return;
                }
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.switchTo$ar$edu(2);
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.onStart();
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).submitConnectionRequest$ar$edu(connectableDevice, 9);
            }
        }

        public final void onUsbDetached(ConnectableDevice connectableDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("Connectioneer", "onUsbDetached");
            if (((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).shouldUseUsbConnection()) {
                if (((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.getCurrentlyConnectedDevice().isPresent() && ((ConnectableDevice) ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.getCurrentlyConnectedDevice().get()).equals(connectableDevice)) {
                    ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.disconnect();
                }
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.startSearch$ar$edu(8);
                if (!((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isConnectingOrConnected()) {
                    ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).autoConnectIfPossibleToBondedDevice$ar$edu(8);
                    return;
                }
                return;
            }
            if (isUsingUsbConnection()) {
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.switchTo$ar$edu(1);
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).connectManagerProxy.onStart();
                ((Connectioneer) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).autoConnectIfPossibleToBondedDevice$ar$edu(8);
            }
        }

        public final void onViewReady() {
            _BOUNDARY.d("BrailleIme", "onViewReady");
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).activateBrailleIme();
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).layoutOrientator$ar$class_merging.startIfNeeded();
            if (!((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).keyboardView.isTutorialShown()) {
                ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).showOnBrailleDisplay();
            }
        }

        public final void onViewUpdated() {
            if (!((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).contextMenuDialog.isShowing() && ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).tutorialState$ar$edu != 2) {
                ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).activateBrailleIme();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
        public final void performEditorAction() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).performEditorAction(getImeConnection$ar$class_merging$ar$class_merging().PhenotypeProcessReaper$ar$isKillable);
        }

        public final void releaseView() {
            ControlOverlays controlOverlays = ((UIManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).controlOverlays;
            LogUtils.v("ControlOverlays", "releaseViews isInflated %s overlays: %s %s", Boolean.valueOf(controlOverlays.isInflated), controlOverlays.triggerButtonOverlay, controlOverlays.expandableOverlay);
            controlOverlays.hideAll();
            controlOverlays.foregroundOverlayType = ControlOverlays.OverlayTypes.NONE;
            controlOverlays.triggerButtonOverlay = null;
            controlOverlays.collapsedOverlay = null;
            controlOverlays.expandableOverlay = null;
            controlOverlays.collapsedPanel = null;
            controlOverlays.expandablePanel = null;
            controlOverlays.overlayCoordinatesSynchronizer$ar$class_merging = null;
            controlOverlays._triggerButtons.clear();
            controlOverlays.isInflated = false;
        }

        public final void resolutionAttempted(Status status) {
            if (status.isOk()) {
                ((RetryingNameResolver) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).retryScheduler.reset();
                return;
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            ((RetryingNameResolver) obj).retryScheduler.schedule(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(obj, 4));
        }

        public final void show() {
            BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, this.RetryingNameResolver$ResolutionResultListener$ar$this$0));
        }

        public final int size() {
            return ((ByteString) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).size();
        }

        public final void switchBrailleDisplayOnOrOff() {
            int i;
            BrailleDisplay brailleDisplay = ((TalkBackService) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).brailleDisplay;
            if (brailleDisplay.isRunning) {
                BdController bdController = brailleDisplay.controller;
                boolean isConnectionEnabled = AppCompatDelegate.Api24Impl.isConnectionEnabled(bdController.context);
                AppCompatDelegate.Api24Impl.setConnectionEnabled(bdController.context, !isConnectionEnabled);
                Context context = bdController.context;
                if (true != isConnectionEnabled) {
                    i = R.string.bd_turn_braille_display_on;
                } else {
                    i = R.string.bd_turn_braille_display_off;
                }
                AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), context.getString(i), 1);
            }
        }

        public final void unregisterOrientationChange() {
            ((BrailleIme) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).orientationCallbackDelegate = null;
        }

        public ResolutionResultListener(Object obj) {
            this.RetryingNameResolver$ResolutionResultListener$ar$this$0 = obj;
        }

        public /* synthetic */ ResolutionResultListener(Object obj, byte[] bArr) {
            this.RetryingNameResolver$ResolutionResultListener$ar$this$0 = obj;
        }

        public final void onDisconnected(BluetoothDevice bluetoothDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onDisconnected: ".concat(String.valueOf(bluetoothDevice.getName())));
            if (((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isConnectingOrConnected() && ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).btConnector.device.address().equals(bluetoothDevice.getAddress())) {
                ((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).disconnect();
            }
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public ResolutionResultListener(Set set) {
            this();
            this.RetryingNameResolver$ResolutionResultListener$ar$this$0 = set;
            MetadataKeyValueHandlers.getDefaultHandler(set);
        }

        public final void onConnected(BluetoothDevice bluetoothDevice) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onConnected");
            if (bluetoothDevice.getBondState() != 12) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", String.valueOf(bluetoothDevice.getName()).concat(" is not paired yet."));
                return;
            }
            if (((BtConnectManager) this.RetryingNameResolver$ResolutionResultListener$ar$this$0).isConnectingOrConnected()) {
                return;
            }
            Object obj = this.RetryingNameResolver$ResolutionResultListener$ar$this$0;
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (char[]) null, (byte[]) null, (byte[]) null);
            onDeviceTextDetectionLoadLogEvent.setBluetoothDevice$ar$ds(bluetoothDevice);
            ((BtConnectManager) obj).connectorManagerCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onDeviceSeenOrUpdated(onDeviceTextDetectionLoadLogEvent.build());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RetryingListener extends NameResolver.Listener2 {
        private final NameResolver.Listener2 delegateListener;

        public RetryingListener(NameResolver.Listener2 listener2) {
            this.delegateListener = listener2;
        }

        @Override // io.grpc.NameResolver.Listener2
        public final void onError(Status status) {
            this.delegateListener.onError(status);
            RetryingNameResolver.this.syncContext.execute(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 5, null));
        }

        @Override // io.grpc.NameResolver.Listener2
        public final void onResult(NameResolver.ResolutionResult resolutionResult) {
            if (resolutionResult.attributes.get(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY) == null) {
                NameResolver.Listener2 listener2 = this.delegateListener;
                AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent((byte[]) null, (byte[]) null);
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = resolutionResult.addresses;
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = resolutionResult.attributes;
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = resolutionResult.serviceConfig;
                Attributes.Builder builder = new Attributes.Builder(resolutionResult.attributes);
                builder.set$ar$ds$d0d6fadb_0(RetryingNameResolver.RESOLUTION_RESULT_LISTENER_KEY, new ResolutionResultListener(RetryingNameResolver.this));
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = builder.build();
                listener2.onResult(aggregatedOnDeviceTextDetectionLogEvent.m221build());
                return;
            }
            throw new IllegalStateException("RetryingNameResolver can only be used once to wrap a NameResolver");
        }
    }

    public RetryingNameResolver(NameResolver nameResolver, RetryScheduler retryScheduler, SynchronizationContext synchronizationContext) {
        super(nameResolver);
        this.retryScheduler = retryScheduler;
        this.syncContext = synchronizationContext;
    }

    @Override // io.grpc.internal.ForwardingNameResolver, io.grpc.NameResolver
    public final void shutdown() {
        this.delegate.shutdown();
        this.retryScheduler.reset();
    }

    @Override // io.grpc.NameResolver
    public final void start(NameResolver.Listener2 listener2) {
        this.delegate.start(new RetryingListener(listener2));
    }
}

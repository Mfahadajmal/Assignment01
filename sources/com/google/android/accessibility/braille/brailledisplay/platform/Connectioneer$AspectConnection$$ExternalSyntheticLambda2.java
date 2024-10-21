package com.google.android.accessibility.braille.brailledisplay.platform;

import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.lifecycle.ServiceState;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.talkback.Interpretation$TouchInteraction;
import com.google.android.accessibility.talkback.TalkBackUpdateHelper;
import com.google.android.accessibility.talkback.UserInterface$UserInputEventListener;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.grpc.internal.RetryingNameResolver;
import j$.util.function.Consumer$CC;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Connectioneer$AspectConnection$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ Object Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Connectioneer$AspectConnection$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v35, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.List, java.lang.Object] */
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.switching_field) {
            case 0:
                ((Connectioneer.AspectConnection.Callback) obj).onConnectableDeviceSeenOrUpdated((ConnectableDevice) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0);
                return;
            case 1:
                ((Connectioneer.AspectConnection.Callback) obj).onConnectableDeviceDeleted$ar$ds();
                return;
            case 2:
                ((Connectioneer.AspectDisplayProperties.Callback) obj).onDisplayPropertiesArrived(((Connectioneer.AspectDisplayProperties) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0).connectioneer.displayProperties);
                return;
            case 3:
                ((FloatingActionButton.ShadowDelegateImpl) obj).onPacketArrived((byte[]) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0);
                return;
            case 4:
                BrailleDisplaySettingsFragment.lambda$onModelChanged$7(this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0, (BrailleDisplaySettingsFragment.DevicePreference) obj);
                return;
            case 5:
                boolean booleanValue = ((Boolean) obj).booleanValue();
                Object obj2 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                if (!booleanValue) {
                    ((SelectToSpeakService) obj2).abortHandlingSelection();
                    return;
                } else {
                    ((SelectToSpeakService) obj2).selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.MARK_ZONE_TO_SPEAK$ar$edu);
                    return;
                }
            case 6:
                if (!((Boolean) obj).booleanValue()) {
                    Object obj3 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                    ((SelectToSpeakService) obj3).serviceState.setCurrent$ar$edu(ServiceState.State.IDLE$ar$edu);
                    return;
                }
                return;
            case 7:
                boolean booleanValue2 = ((Boolean) obj).booleanValue();
                Object obj4 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                if (!booleanValue2) {
                    Object obj5 = ((RetryingNameResolver.ResolutionResultListener) obj4).RetryingNameResolver$ResolutionResultListener$ar$this$0;
                    ((SelectToSpeakService) obj5).serviceState.setCurrent$ar$edu(ServiceState.State.IDLE$ar$edu);
                    return;
                }
                ((SelectToSpeakService) ((RetryingNameResolver.ResolutionResultListener) obj4).RetryingNameResolver$ResolutionResultListener$ar$this$0).selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.DIRECT_TO_SPEAK$ar$edu);
                return;
            case 8:
                ((RetryingNameResolver.ResolutionResultListener) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0).m245xf2ec3d4((Boolean) obj);
                return;
            case 9:
                ((TalkBackUpdateHelper) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0).handler.postDelayed((Runnable) obj, 5000L);
                return;
            case 10:
                ((UserInterface$UserInputEventListener) obj).touchInteractionState(((Interpretation$TouchInteraction) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0).interactionActive());
                return;
            case 11:
                AiCoreEndpoint aiCoreEndpoint = (AiCoreEndpoint) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                if (aiCoreEndpoint.aiFeature == null) {
                    LogUtils.d("AiCoreEndpointGemini", "requestDownloadAiFeature - Client or AiFeature is null", new Object[0]);
                    return;
                }
                aiCoreEndpoint.initializeService();
                aiCoreEndpoint.setHasRequestDownload(true);
                SpannableUtils$IdentifierSpan.putBooleanPref(aiCoreEndpoint.perfs, ((AiCoreClientImpl) aiCoreEndpoint.aiCoreClient).context.getResources(), R.string.pref_auto_on_devices_image_description_key, true);
                return;
            case 12:
                ((AiCoreEndpoint) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0).aiCoreOptIn(((Integer) obj).intValue());
                return;
            case 13:
                ((AiCoreEndpoint) this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0).aiCoreOptIn(((Integer) obj).intValue());
                return;
            case 14:
                this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0.add((CharSequence) obj);
                return;
            case 15:
                this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0.addAll((List) obj);
                return;
            case 16:
                Object obj6 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                LinkedHashMap linkedHashMap = (LinkedHashMap) obj6;
                linkedHashMap.put(PageConfig.PageId.PAGE_ID_TV_OVERVIEW, (PageConfig.Builder) obj);
                return;
            case 17:
                Object obj7 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                LinkedHashMap linkedHashMap2 = (LinkedHashMap) obj7;
                linkedHashMap2.put(PageConfig.PageId.PAGE_ID_TV_REMOTE, (PageConfig.Builder) obj);
                return;
            default:
                Object obj8 = this.Connectioneer$AspectConnection$$ExternalSyntheticLambda2$ar$f$0;
                LinkedHashMap linkedHashMap3 = (LinkedHashMap) obj8;
                linkedHashMap3.put(PageConfig.PageId.PAGE_ID_TV_SHORTCUT, (PageConfig.Builder) obj);
                return;
        }
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        switch (this.switching_field) {
            case 0:
                return Consumer$CC.$default$andThen(this, consumer);
            case 1:
                return Consumer$CC.$default$andThen(this, consumer);
            case 2:
                return Consumer$CC.$default$andThen(this, consumer);
            case 3:
                return Consumer$CC.$default$andThen(this, consumer);
            case 4:
                return Consumer$CC.$default$andThen(this, consumer);
            case 5:
                return Consumer$CC.$default$andThen(this, consumer);
            case 6:
                return Consumer$CC.$default$andThen(this, consumer);
            case 7:
                return Consumer$CC.$default$andThen(this, consumer);
            case 8:
                return Consumer$CC.$default$andThen(this, consumer);
            case 9:
                return Consumer$CC.$default$andThen(this, consumer);
            case 10:
                return Consumer$CC.$default$andThen(this, consumer);
            case 11:
                return Consumer$CC.$default$andThen(this, consumer);
            case 12:
                return Consumer$CC.$default$andThen(this, consumer);
            case 13:
                return Consumer$CC.$default$andThen(this, consumer);
            case 14:
                return Consumer$CC.$default$andThen(this, consumer);
            case 15:
                return Consumer$CC.$default$andThen(this, consumer);
            case 16:
                return Consumer$CC.$default$andThen(this, consumer);
            case 17:
                return Consumer$CC.$default$andThen(this, consumer);
            default:
                return Consumer$CC.$default$andThen(this, consumer);
        }
    }
}

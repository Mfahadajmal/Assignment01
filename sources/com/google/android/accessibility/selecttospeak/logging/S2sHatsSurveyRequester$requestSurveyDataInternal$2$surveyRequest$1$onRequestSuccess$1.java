package com.google.android.accessibility.selecttospeak.logging;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.marvin.talkback.R;
import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 extends Lambda implements Function1 {
    private final /* synthetic */ int switching_field;
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$a2eeaa1f_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(13);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$12d1b183_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(12);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$7f78ac82_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(11);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$2a28f0fe_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(10);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$c629e1d6_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(9);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$27f053a7_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(8);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$c74cea98_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(7);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$43cfcecf_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(6);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$ebb6622b_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(5);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$9342e9a0_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(4);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$e5df0f1f_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(3);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$6c47703c_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(2);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE$ar$class_merging$4ceee00e_0 = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(1);
    public static final S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1 INSTANCE = new S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1(int i) {
        super(1);
        this.switching_field = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object obj) {
        boolean z = true;
        switch (this.switching_field) {
            case 0:
                ((Throwable) obj).getClass();
                S2sHatsSurveyRequester s2sHatsSurveyRequester = S2sHatsSurveyRequester.INSTANCE;
                S2sHatsSurveyRequester.surveysClient = new WeakReference(null);
                return Unit.INSTANCE;
            case 1:
                ((Throwable) obj).getClass();
                S2sHatsSurveyRequester s2sHatsSurveyRequester2 = S2sHatsSurveyRequester.INSTANCE;
                S2sHatsSurveyRequester.surveysClient = new WeakReference(null);
                return Unit.INSTANCE;
            case 2:
                AccessibilityServiceInfo accessibilityServiceInfo = (AccessibilityServiceInfo) obj;
                accessibilityServiceInfo.getClass();
                if (accessibilityServiceInfo.getResolveInfo() == null || accessibilityServiceInfo.getResolveInfo().serviceInfo == null || !Intrinsics.areEqual("com.google.android.marvin.talkback.TalkBackService", accessibilityServiceInfo.getResolveInfo().serviceInfo.name)) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                ViewGroup viewGroup = (ViewGroup) obj;
                viewGroup.getClass();
                return (Button) viewGroup.findViewById(R.id.speed_plus_button);
            case 4:
                ViewGroup viewGroup2 = (ViewGroup) obj;
                viewGroup2.getClass();
                return (TextView) viewGroup2.findViewById(R.id.speed_index_text);
            case 5:
                ViewGroup viewGroup3 = (ViewGroup) obj;
                viewGroup3.getClass();
                return (Button) viewGroup3.findViewById(R.id.speed_minus_button);
            case 6:
                ViewGroup viewGroup4 = (ViewGroup) obj;
                viewGroup4.getClass();
                return (Button) viewGroup4.findViewById(R.id.text_size_plus_button);
            case 7:
                ViewGroup viewGroup5 = (ViewGroup) obj;
                viewGroup5.getClass();
                return (TextView) viewGroup5.findViewById(R.id.text_size_index_text);
            case 8:
                ViewGroup viewGroup6 = (ViewGroup) obj;
                viewGroup6.getClass();
                return (Button) viewGroup6.findViewById(R.id.text_size_minus_button);
            case 9:
                if (obj != null) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 10:
                String str = (String) obj;
                str.getClass();
                return str;
            case 11:
                CoroutineContext.Element element = (CoroutineContext.Element) obj;
                if (!(element instanceof CoroutineDispatcher)) {
                    return null;
                }
                return (CoroutineDispatcher) element;
            case 12:
                CoroutineContext.Element element2 = (CoroutineContext.Element) obj;
                if (!(element2 instanceof ExecutorCoroutineDispatcher)) {
                    return null;
                }
                return (ExecutorCoroutineDispatcher) element2;
            default:
                return null;
        }
    }
}

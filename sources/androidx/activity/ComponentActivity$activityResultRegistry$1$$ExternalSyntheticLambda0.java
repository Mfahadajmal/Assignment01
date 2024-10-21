package androidx.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultRegistry;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.traversal.TraversalStrategy;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.SingleSelectView;
import com.google.android.marvin.talkback.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$Event;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.RetryingNameResolver;
import io.perfmark.PerfMark;
import java.io.Serializable;
import org.chromium.net.impl.CronetEngineBuilderImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0;
    public final /* synthetic */ Object ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2;
    public final /* synthetic */ int f$1;
    private final /* synthetic */ int switching_field;

    public ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(TextView textView, Typeface typeface, int i, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = textView;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = typeface;
        this.f$1 = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v33, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object, androidx.activity.result.ActivityResultCallback] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, java.io.Serializable] */
    @Override // java.lang.Runnable
    public final void run() {
        int i;
        Object obj = null;
        byte b = 0;
        switch (this.switching_field) {
            case 0:
                int i2 = this.f$1;
                ActivityResultRegistry activityResultRegistry = (ActivityResultRegistry) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0;
                String str = (String) activityResultRegistry.rcToKey.get(Integer.valueOf(i2));
                if (str != null) {
                    NodeBrailler nodeBrailler = (NodeBrailler) activityResultRegistry.keyToCallback.get(str);
                    if (nodeBrailler != null) {
                        obj = nodeBrailler.NodeBrailler$ar$context;
                    }
                    Object obj2 = ((AccessibilityNodeInfoCompat.CollectionItemInfoCompat) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2).mInfo;
                    if (obj != null) {
                        ?? r1 = nodeBrailler.NodeBrailler$ar$context;
                        if (activityResultRegistry.launchedKeys.remove(str)) {
                            r1.onActivityResult(obj2);
                            return;
                        }
                        return;
                    }
                    activityResultRegistry.pendingResults.remove(str);
                    activityResultRegistry.parsedPendingResults.put(str, obj2);
                    return;
                }
                return;
            case 1:
                ((TextView) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2).setTypeface((Typeface) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0, this.f$1);
                return;
            case 2:
                ((ActivityResultRegistry) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2).dispatchResult(this.f$1, 0, new Intent().setAction("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST").putExtra("androidx.activity.result.contract.extra.SEND_INTENT_EXCEPTION", (Serializable) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0));
                return;
            case 3:
                KeyboardView keyboardView = (KeyboardView) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2;
                keyboardView.tutorialView = new TutorialView(keyboardView.context, (RetryingNameResolver.ResolutionResultListener) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0, keyboardView.getScreenSize());
                keyboardView.tutorialView.switchState$ar$edu$ar$ds(this.f$1);
                RetryingNameResolver.ResolutionResultListener resolutionResultListener = keyboardView.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                KeyboardView.ViewContainer viewContainer = keyboardView.viewContainer;
                TutorialView tutorialView = keyboardView.tutorialView;
                resolutionResultListener.getClass();
                viewContainer.addView$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(tutorialView, new RetryingNameResolver.ResolutionResultListener(resolutionResultListener, b == true ? 1 : 0));
                return;
            case 4:
                SearchScreenOverlay searchScreenOverlay = (SearchScreenOverlay) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0;
                searchScreenOverlay.searchStrategy.cacheNodeTree(searchScreenOverlay.initialFocusedWindow);
                searchScreenOverlay.searchStrategy.searchKeyword(searchScreenOverlay.keywordEditText.getText().toString());
                int i3 = this.f$1;
                if (i3 == 4096) {
                    i = 1;
                } else if (i3 == 8192) {
                    i = 2;
                } else if (i3 == AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT.getId()) {
                    i = 3;
                } else if (i3 == AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT.getId()) {
                    i = 4;
                } else if (i3 == AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId()) {
                    i = 5;
                } else if (i3 == AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN.getId()) {
                    i = 6;
                } else {
                    i = 0;
                }
                AccessibilityNode accessibilityNode = (AccessibilityNode) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2;
                TraversalStrategy traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(accessibilityNode.getCompat(), searchScreenOverlay.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, i);
                searchScreenOverlay.initialFocusedNode = AccessibilityNode.takeOwnership(TraversalStrategyUtils.findFirstFocusInNodeTree(traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, accessibilityNode.getCompat(), i, SpannableUtils$IdentifierSpan.createNodeFilter(0, traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSpeakingNodesCache())));
                searchScreenOverlay.refreshUiState();
                return;
            case 5:
                Layout layout = ((TextView) ((AppCompatActivity) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2).findViewById(R.id.license_activity_textview)).getLayout();
                if (layout != null) {
                    ((ScrollView) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0).scrollTo(0, layout.getLineTop(layout.getLineForOffset(this.f$1)));
                    return;
                }
                return;
            case 6:
                int i4 = this.f$1;
                ?? r12 = this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2;
                ((SingleSelectView) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0).onAnswerSelectClickListener.onClickAnswerSelect$ar$class_merging$a1759ccb_0(new CronetEngineBuilderImpl.QuicHint(Survey$Event.QuestionAnswered.Selection.AnswerType.ANSWER_TYPE_TEXT$ar$edu$def17366_0, ((Survey$AnswerChoice) r12.get(i4)).text_, ((Survey$AnswerChoice) r12.get(i4)).answerOrdinal_));
                long j = SurveyUtils.TIMEOUT_MS;
                return;
            case 7:
                ((BottomSheetBehavior) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2).startSettling((View) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0, this.f$1, false);
                return;
            default:
                try {
                    int i5 = PerfMark.PerfMark$ar$NoOp;
                    ((AbstractStream.TransportState) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0).deframer.request(this.f$1);
                    return;
                } catch (Throwable th) {
                    ((AbstractStream.TransportState) this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0).deframeFailed(th);
                    return;
                }
        }
    }

    public /* synthetic */ ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(ActivityResultRegistry activityResultRegistry, int i, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = activityResultRegistry;
        this.f$1 = i;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = collectionItemInfoCompat;
    }

    public /* synthetic */ ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(KeyboardView keyboardView, RetryingNameResolver.ResolutionResultListener resolutionResultListener, int i, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = keyboardView;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = resolutionResultListener;
        this.f$1 = i;
    }

    public ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(BottomSheetBehavior bottomSheetBehavior, View view, int i, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = view;
        this.f$1 = i;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = bottomSheetBehavior;
    }

    public ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(AbstractStream.TransportState transportState, OnDeviceLanguageIdentificationLogEvent.IdentifiedLanguage identifiedLanguage, int i, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = identifiedLanguage;
        this.f$1 = i;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = transportState;
    }

    public /* synthetic */ ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(Object obj, int i, Object obj2, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = obj;
        this.f$1 = i;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = obj2;
    }

    public /* synthetic */ ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(Object obj, Object obj2, int i, int i2) {
        this.switching_field = i2;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$ed77e6ca_0 = obj;
        this.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0$ar$f$2 = obj2;
        this.f$1 = i;
    }
}

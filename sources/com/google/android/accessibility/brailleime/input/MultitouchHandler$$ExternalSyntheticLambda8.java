package com.google.android.accessibility.brailleime.input;

import android.speech.tts.Voice;
import android.util.Range;
import android.view.accessibility.AccessibilityWindowInfo;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.search.UniversalSearchActor;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.common.collect.ImmutableList;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import j$.util.function.Predicate$CC;
import java.util.Set;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MultitouchHandler$$ExternalSyntheticLambda8 implements Predicate {
    public final /* synthetic */ Object MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ MultitouchHandler$$ExternalSyntheticLambda8(Object obj, int i) {
        this.switching_field = i;
        this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0 = obj;
    }

    public final /* synthetic */ Predicate and(Predicate predicate) {
        switch (this.switching_field) {
            case 0:
                return Predicate$CC.$default$and(this, predicate);
            case 1:
                return Predicate$CC.$default$and(this, predicate);
            case 2:
                return Predicate$CC.$default$and(this, predicate);
            case 3:
                return Predicate$CC.$default$and(this, predicate);
            case 4:
                return Predicate$CC.$default$and(this, predicate);
            case 5:
                return Predicate$CC.$default$and(this, predicate);
            case 6:
                return Predicate$CC.$default$and(this, predicate);
            case 7:
                return Predicate$CC.$default$and(this, predicate);
            default:
                return Predicate$CC.$default$and(this, predicate);
        }
    }

    public final /* synthetic */ Predicate negate() {
        switch (this.switching_field) {
            case 0:
                return Predicate$CC.$default$negate(this);
            case 1:
                return Predicate$CC.$default$negate(this);
            case 2:
                return Predicate$CC.$default$negate(this);
            case 3:
                return Predicate$CC.$default$negate(this);
            case 4:
                return Predicate$CC.$default$negate(this);
            case 5:
                return Predicate$CC.$default$negate(this);
            case 6:
                return Predicate$CC.$default$negate(this);
            case 7:
                return Predicate$CC.$default$negate(this);
            default:
                return Predicate$CC.$default$negate(this);
        }
    }

    public final /* synthetic */ Predicate or(Predicate predicate) {
        switch (this.switching_field) {
            case 0:
                return Predicate$CC.$default$or(this, predicate);
            case 1:
                return Predicate$CC.$default$or(this, predicate);
            case 2:
                return Predicate$CC.$default$or(this, predicate);
            case 3:
                return Predicate$CC.$default$or(this, predicate);
            case 4:
                return Predicate$CC.$default$or(this, predicate);
            case 5:
                return Predicate$CC.$default$or(this, predicate);
            case 6:
                return Predicate$CC.$default$or(this, predicate);
            case 7:
                return Predicate$CC.$default$or(this, predicate);
            default:
                return Predicate$CC.$default$or(this, predicate);
        }
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.switching_field) {
            case 0:
                return ((Range) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0).contains((Range) Long.valueOf(((MultitouchHandler.PointerWithHistory) obj).momentMadeInactive));
            case 1:
                return ((Range) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0).contains((Range) Long.valueOf(((MultitouchHandler.PointerWithHistory) obj).momentMadeInactive));
            case 2:
                String str = TutorialView.TAG;
                return ((BrailleCharacter) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0).dotNumbers.get(((OrderVerifyingClientCall.State) obj).type$ar$edu$88c656f2_0 - 1);
            case 3:
                SelectToSpeakLogSender selectToSpeakLogSender = SelectToSpeakLogSender.INSTANCE;
                return ((Boolean) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0.invoke(obj)).booleanValue();
            case 4:
                Voice voice = (Voice) obj;
                TalkBackService talkBackService = TalkBackService.instance;
                Set<String> features = voice.getFeatures();
                if (features != null && !features.contains("notInstalled") && !voice.isNetworkConnectionRequired()) {
                    if (((String) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0).equals(voice.getLocale().getDisplayName())) {
                        return true;
                    }
                }
                return false;
            case 5:
                AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) obj;
                if (accessibilityWindowInfo != null) {
                    if (accessibilityWindowInfo.getId() == ((UniversalSearchActor) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0).getOverlayId()) {
                        return true;
                    }
                }
                return false;
            case 6:
                ImmutableList immutableList = SelectorController.SELECTOR_SETTINGS;
                if (((SelectorController.ContextualSetting) obj).getSetting() == this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0) {
                    return true;
                }
                return false;
            case 7:
                return ((ContextMenuItem) obj).getContextMenuItemId().equals(((SelectorController) this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0).currentActionId);
            default:
                ImmutableList immutableList2 = SelectorController.SELECTOR_SETTINGS;
                if (((SelectorController.ContextualSetting) obj).getSetting() == this.MultitouchHandler$$ExternalSyntheticLambda8$ar$f$0) {
                    return true;
                }
                return false;
        }
    }
}

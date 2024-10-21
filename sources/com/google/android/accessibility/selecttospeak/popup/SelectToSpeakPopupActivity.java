package com.google.android.accessibility.selecttospeak.popup;

import _COROUTINE._BOUNDARY;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import androidx.core.text.ICUCompat$Api24Impl;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt$special$$inlined$Sequence$1;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigator$navigate$1;
import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1;
import com.google.android.accessibility.selecttospeak.popup.HighlightViewModel;
import com.google.android.accessibility.selecttospeak.popup.TextSizeModel;
import com.google.android.accessibility.selecttospeak.ui.HighlightScrollingTextView;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import io.grpc.okhttp.internal.OptionalMethod;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakPopupActivity extends AppCompatActivity {
    public HighlightViewModel highlightViewModel;
    public SelectToSpeakJobModel jobModel;
    public Button playPauseButton;
    public PopupWindow popupWindow;
    public SharedPreferences prefs;
    public View rootView;
    public HighlightScrollingTextView scrollView;
    public OptionalMethod selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
    public PlusMinusButtons speedButtons;
    public PlusMinusButtons textSizeButtons;
    public TextSizeModel textSizeModel;
    private boolean wasPreviouslyPlaying;

    public final void closeActivity() {
        S2sHatsSurveyRequester s2sHatsSurveyRequester = S2sHatsSurveyRequester.INSTANCE;
        S2sHatsSurveyRequester.cleanUp$ar$ds();
        getJobModel().analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging = null;
        finish();
    }

    public final HighlightViewModel getHighlightViewModel() {
        HighlightViewModel highlightViewModel = this.highlightViewModel;
        if (highlightViewModel != null) {
            return highlightViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("highlightViewModel");
        return null;
    }

    public final SelectToSpeakJobModel getJobModel() {
        SelectToSpeakJobModel selectToSpeakJobModel = this.jobModel;
        if (selectToSpeakJobModel != null) {
            return selectToSpeakJobModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("jobModel");
        return null;
    }

    public final HighlightScrollingTextView getScrollView() {
        HighlightScrollingTextView highlightScrollingTextView = this.scrollView;
        if (highlightScrollingTextView != null) {
            return highlightScrollingTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scrollView");
        return null;
    }

    public final OptionalMethod getSelectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging() {
        OptionalMethod optionalMethod = this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            return optionalMethod;
        }
        Intrinsics.throwUninitializedPropertyAccessException("selectToSpeakClearcutAnalytics");
        return null;
    }

    public final TextSizeModel getTextSizeModel() {
        TextSizeModel textSizeModel = this.textSizeModel;
        if (textSizeModel != null) {
            return textSizeModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("textSizeModel");
        return null;
    }

    @Override // android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        CharSequence charSequenceExtra;
        S2SPopupParsedIntent s2SPopupParsedIntent;
        String string;
        ClipDescription description;
        super.onCreate(bundle);
        Intent intent = getIntent();
        intent.getClass();
        int i = 0;
        if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.SEND") && Intrinsics.areEqual(intent.getType(), "text/plain")) {
            ClipData clipData = intent.getClipData();
            if (clipData != null && (description = clipData.getDescription()) != null && description.hasMimeType("text/plain")) {
                ClipData clipData2 = intent.getClipData();
                clipData2.getClass();
                charSequenceExtra = OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new ViewGroupKt$special$$inlined$Sequence$1(clipData2, 2), "\n", new S2SPopupParsedIntentKt$parseIntent$text$1(this, 0), 30);
            } else {
                charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.TEXT");
            }
        } else {
            charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT");
        }
        View view = null;
        if (charSequenceExtra != null && !OnDeviceStainRemovalLogEvent.isBlank(charSequenceExtra)) {
            s2SPopupParsedIntent = new S2SPopupParsedIntent(charSequenceExtra.toString());
        } else {
            s2SPopupParsedIntent = null;
        }
        if (s2SPopupParsedIntent == null) {
            LogUtils.e("SelectToSpeakPopupActivity", "No text provided in intent.", new Object[0]);
        }
        setContentView(R.layout.layout_contextual_popup);
        if (Build.VERSION.SDK_INT >= 35) {
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.root);
            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(viewGroup, new SelectToSpeakPopupActivity$$ExternalSyntheticLambda0(viewGroup, i));
        }
        this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(this);
        if (s2SPopupParsedIntent != null) {
            string = s2SPopupParsedIntent.text;
        } else {
            string = getString(R.string.s2s_popup_error_blank_text_in_intent);
            string.getClass();
        }
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this);
        sharedPreferences.getClass();
        this.prefs = sharedPreferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences = null;
        }
        LogUtils.minLogLevel = SpannableUtils$IdentifierSpan.getIntFromStringPref(sharedPreferences, getResources(), R.string.pref_log_level_key, R.string.pref_log_level_default);
        SelectToSpeakJobModel selectToSpeakJobModel = (SelectToSpeakJobModel) new ViewModelProvider(this).get(SelectToSpeakJobModel.class);
        selectToSpeakJobModel.getClass();
        this.jobModel = selectToSpeakJobModel;
        HighlightViewModel highlightViewModel = (HighlightViewModel) new ViewModelProvider(this, new HighlightViewModel.HighlightViewModelFactory(getColor(R.color.contextual_popup_highlight_color))).get(HighlightViewModel.class);
        highlightViewModel.getClass();
        this.highlightViewModel = highlightViewModel;
        float dimension = getResources().getDimension(R.dimen.contextual_popup_text_size);
        SharedPreferences sharedPreferences2 = this.prefs;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            sharedPreferences2 = null;
        }
        TextSizeModel textSizeModel = (TextSizeModel) new ViewModelProvider(this, new TextSizeModel.TextViewModelFactory(dimension, sharedPreferences2.getInt(getString(R.string.s2s_pref_popup_text_size), 1))).get(TextSizeModel.class);
        textSizeModel.getClass();
        this.textSizeModel = textSizeModel;
        getJobModel().analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging = getSelectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging();
        getTextSizeModel().analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging = getSelectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging();
        View findViewById = findViewById(R.id.root);
        findViewById.getClass();
        this.rootView = findViewById;
        if (findViewById == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        } else {
            view = findViewById;
        }
        view.setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 4));
        findViewById(R.id.tools_background).setOnClickListener(new SelectToSpeakPopupActivity$$ExternalSyntheticLambda4(0));
        View findViewById2 = findViewById(R.id.text_to_read);
        HighlightScrollingTextView highlightScrollingTextView = (HighlightScrollingTextView) findViewById2;
        View findViewById3 = findViewById(R.id.text_shadow_background_top);
        findViewById3.getClass();
        highlightScrollingTextView.shadowTop = findViewById3;
        View findViewById4 = findViewById(R.id.text_shadow_background);
        findViewById4.getClass();
        highlightScrollingTextView.shadowBottom = findViewById4;
        findViewById2.getClass();
        this.scrollView = highlightScrollingTextView;
        View findViewById5 = findViewById(R.id.play_pause_button);
        Button button = (Button) findViewById5;
        button.setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 5));
        findViewById5.getClass();
        this.playPauseButton = button;
        ((Button) findViewById(R.id.close_button)).setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 6));
        View findViewById6 = findViewById(R.id.settings_button);
        ((Button) findViewById6).setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 7));
        findViewById6.getClass();
        this.speedButtons = new PlusMinusButtons(new SelectToSpeakPopupActivity$setupViews$18(this, 3), new SelectToSpeakPopupActivity$setupViews$18(this, 4), new SelectToSpeakPopupActivity$setupViews$18(this, 5), new SharedSQLiteStatement$stmt$2(this, 17), new SharedSQLiteStatement$stmt$2(this, 18), S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$e5df0f1f_0, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$9342e9a0_0, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$ebb6622b_0);
        this.textSizeButtons = new PlusMinusButtons(new SharedSQLiteStatement$stmt$2(this, 19), new SharedSQLiteStatement$stmt$2(this, 20), new SelectToSpeakPopupActivity$setupViews$18(this, 1), new SelectToSpeakPopupActivity$setupViews$18(this, 0), new SelectToSpeakPopupActivity$setupViews$18(this, 2), S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$43cfcecf_0, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$c74cea98_0, S2sHatsSurveyRequester$requestSurveyDataInternal$2$surveyRequest$1$onRequestSuccess$1.INSTANCE$ar$class_merging$27f053a7_0);
        getJobModel().getOnUserInitiatedFinished().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 2), 0));
        getJobModel().getOnProgramInitiatedFinished().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 3), 0));
        getJobModel().isPaused().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 4), 0));
        getHighlightViewModel().getHighlightedText().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 5), 0));
        getHighlightViewModel().getHighlightedTextIndex().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 6), 0));
        getHighlightViewModel().getInvalidate().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 7), 0));
        getTextSizeModel().getTextSizePx().observe(this, new SelectToSpeakPopupActivity$sam$androidx_lifecycle_Observer$0(new S2SPopupParsedIntentKt$parseIntent$text$1(this, 8), 0));
        HighlightViewModel highlightViewModel2 = getHighlightViewModel();
        string.getClass();
        highlightViewModel2.spannable = new SpannableString(string);
        highlightViewModel2.getHighlightedText().setValue(highlightViewModel2.spannable);
        SelectToSpeakJobModel jobModel = getJobModel();
        jobModel.speechController = (SpeechControllerImpl) new Function1() { // from class: com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity$createSpeechController$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                SpeechController.Delegate delegate = (SpeechController.Delegate) obj;
                delegate.getClass();
                return new SpeechControllerImpl(SelectToSpeakPopupActivity.this.getApplicationContext(), delegate);
            }
        }.invoke(jobModel.speechControllerDelegate);
        getScrollView().post(new ContextMenuDialog$$ExternalSyntheticLambda5(this, 18));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        super.onPause();
        this.wasPreviouslyPlaying = Intrinsics.areEqual(getJobModel().isPaused().getValue(), false);
        getJobModel().pause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        if (this.wasPreviouslyPlaying) {
            getJobModel().resume();
        }
    }

    public final void showSurveyAndCloseActivity(int i) {
        LogUtils.w("SelectToSpeakPopupActivity", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "onFinish. User initiated : "), new Object[0]);
        PopupWindow popupWindow = this.popupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        getTextSizeModel().analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging = null;
        if (i >= 2) {
            LogUtils.e("SelectToSpeakPopupActivity", "User initiated finish multiple times", new Object[0]);
            closeActivity();
        } else {
            Context applicationContext = getApplicationContext();
            applicationContext.getClass();
            S2sHatsSurveyRequester.requestSurveyData(applicationContext, ICUCompat$Api24Impl.getLifecycleScope(this), new Navigator$navigate$1(applicationContext, this, 5, null));
        }
    }
}

package com.google.android.libraries.surveys.internal.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import com.google.android.libraries.processinit.CurrentProcess;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.scone.proto.Survey$AnswerChoice;
import com.google.scone.proto.Survey$AnswerChoices;
import com.google.scone.proto.Survey$Payload;
import com.google.scone.proto.Survey$PrivacySettings;
import com.google.scone.proto.Survey$ProductContext;
import com.google.scone.proto.Survey$Question;
import com.google.scone.proto.Survey$SingleSelect;
import googledata.experiments.mobile.surveys_android.features.Hats;
import googledata.experiments.mobile.surveys_android.features.HatsV1M7Bugfixes;
import j$.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SurveyUtils {
    public static final long TIMEOUT_MS = TimeUnit.DAYS.toMillis(1);

    /* JADX WARN: Removed duplicated region for block: B:15:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0143  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.scone.proto.Survey$ClientContext createClientContext(android.content.Context r5) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.surveys.internal.utils.SurveyUtils.createClientContext(android.content.Context):com.google.scone.proto.Survey$ClientContext");
    }

    public static String getCertFingerprint(PackageInfo packageInfo) {
        if (packageInfo.signatures != null && packageInfo.signatures.length > 0) {
            try {
                byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
                if (digest == null) {
                    return null;
                }
                int length = digest.length;
                StringBuilder sb = new StringBuilder(length + length);
                for (byte b : digest) {
                    sb.append(String.format("%02x", Byte.valueOf(b)));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                Log.e("SurveyUtils", "Can't find SHA1.", e);
            }
        }
        return null;
    }

    public static String getFollowUpUrl(String str) {
        String str2;
        if (!TextUtils.isEmpty(str) && Patterns.WEB_URL.matcher(ContextDataProvider.toLowerCase(str)).matches()) {
            if (URLUtil.isHttpUrl(str) || URLUtil.isHttpsUrl(str)) {
                Uri parse = Uri.parse(str);
                try {
                    CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (!FlagsUtil.isBugfixEnabled(HatsV1M7Bugfixes.INSTANCE.get().fixThankyouMissingUrlQuery(FlagsUtil.phenotypeContext))) {
                        if (parse.getQuery() == null) {
                            str2 = "";
                        } else {
                            str2 = URLEncoder.encode(parse.getQuery(), "utf-8");
                        }
                        return new URI(parse.getScheme(), parse.getAuthority(), parse.getPath(), str2).toString();
                    }
                    return new URI(parse.getScheme(), parse.getAuthority(), parse.getPath(), parse.getQuery(), null).toString();
                } catch (UnsupportedEncodingException | URISyntaxException e) {
                    Log.e("SurveyUtils", e.getMessage());
                }
            }
            Log.e("SurveyUtils", "Follow up URL is not http or https.");
            return "";
        }
        Log.e("SurveyUtils", "Follow up URL was empty or invalid.");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getLibraryVersion() {
        CurrentProcess currentProcess = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        String libraryVersion = Hats.INSTANCE.get().libraryVersion(FlagsUtil.phenotypeContext);
        if (libraryVersion.equals("1")) {
            CurrentProcess currentProcess2 = FlagsUtil.flagProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            return Hats.INSTANCE.get().libraryVersionDev(FlagsUtil.phenotypeContext);
        }
        return libraryVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getLibraryVersionAsInt() {
        String libraryVersion = getLibraryVersion();
        if (libraryVersion.contains(".")) {
            libraryVersion = libraryVersion.substring(libraryVersion.lastIndexOf(".") + 1);
        }
        return Integer.parseInt(libraryVersion);
    }

    public static MessageLite getMessage(MessageLite messageLite, byte[] bArr) {
        try {
            return messageLite.toBuilder().mergeFrom(bArr, ExtensionRegistryLite.getGeneratedRegistry()).build();
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Bundle getPsdBundle(Survey$ProductContext survey$ProductContext) {
        Survey$ProductContext.SensitiveContext sensitiveContext = survey$ProductContext.sensitiveContext_;
        if (sensitiveContext == null) {
            sensitiveContext = Survey$ProductContext.SensitiveContext.DEFAULT_INSTANCE;
        }
        Internal.ProtobufList protobufList = sensitiveContext.customEntry_;
        int size = protobufList.size();
        Bundle bundle = new Bundle(size);
        for (int i = 0; i < size; i++) {
            Survey$ProductContext.SensitiveContext.CustomEntry customEntry = (Survey$ProductContext.SensitiveContext.CustomEntry) protobufList.get(i);
            bundle.putString(customEntry.name_, customEntry.value_);
        }
        return bundle;
    }

    public static void hideSoftKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isInTalkBackMode(Context context) {
        return ((AccessibilityManager) context.getSystemService("accessibility")).isEnabled();
    }

    public static boolean isPiiCollectionEnabled(Survey$Payload survey$Payload) {
        Survey$PrivacySettings survey$PrivacySettings = survey$Payload.privacySettings_;
        if (survey$PrivacySettings == null) {
            survey$PrivacySettings = Survey$PrivacySettings.DEFAULT_INSTANCE;
        }
        return survey$PrivacySettings.piiCollectionEnabled_;
    }

    public static boolean isWatchPlatform(Context context) {
        if ((context.getResources().getConfiguration().uiMode & 15) == 6) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean shouldDisplaySurveyControls(Survey$Payload survey$Payload) {
        Survey$SingleSelect survey$SingleSelect;
        if (survey$Payload.question_.size() <= 1) {
            Survey$Question survey$Question = (Survey$Question) survey$Payload.question_.get(0);
            int forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(survey$Question.questionType_);
            if (forNumber$ar$edu$83e82a14_0 == 0) {
                forNumber$ar$edu$83e82a14_0 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
            }
            if (forNumber$ar$edu$83e82a14_0 == Survey$Question.QuestionType.QUESTION_TYPE_SINGLE_SELECT$ar$edu) {
                if (survey$Question.questionCase_ == 4) {
                    survey$SingleSelect = (Survey$SingleSelect) survey$Question.question_;
                } else {
                    survey$SingleSelect = Survey$SingleSelect.DEFAULT_INSTANCE;
                }
                Survey$AnswerChoices survey$AnswerChoices = survey$SingleSelect.answerChoices_;
                if (survey$AnswerChoices == null) {
                    survey$AnswerChoices = Survey$AnswerChoices.DEFAULT_INSTANCE;
                }
                Iterator<E> it = survey$AnswerChoices.answerChoice_.iterator();
                while (it.hasNext()) {
                    int forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.forNumber$ar$edu$ac62307f_0(((Survey$AnswerChoice) it.next()).answerType_);
                    if (forNumber$ar$edu$ac62307f_0 == 0) {
                        forNumber$ar$edu$ac62307f_0 = Survey$AnswerChoice.AnswerType.UNRECOGNIZED$ar$edu$57c31185_0;
                    }
                    if (forNumber$ar$edu$ac62307f_0 == Survey$AnswerChoice.AnswerType.ANSWER_TYPE_WRITE_IN$ar$edu) {
                        return true;
                    }
                }
                return false;
            }
            int forNumber$ar$edu$83e82a14_02 = Survey$Question.QuestionType.forNumber$ar$edu$83e82a14_0(survey$Question.questionType_);
            if (forNumber$ar$edu$83e82a14_02 == 0) {
                forNumber$ar$edu$83e82a14_02 = Survey$Question.QuestionType.UNRECOGNIZED$ar$edu$f46b3ccb_0;
            }
            if (forNumber$ar$edu$83e82a14_02 == Survey$Question.QuestionType.QUESTION_TYPE_RATING$ar$edu) {
                return false;
            }
        }
        return true;
    }

    public static void showSoftKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }
}

package com.google.android.accessibility.talkback.actor.gemini;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.clearcut.internal.ClearcutLoggerApiImpl$$ExternalSyntheticLambda2;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.ListenableFuture;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeminiRestEndpoint implements GeminiActor.GeminiEndpoint {
    private final Context context;
    private final String model;
    private final String prefixPrompt;
    private final ExecutionList.RunnableExecutorPair requestPerformer$ar$class_merging$ar$class_merging$ar$class_merging;
    private final String safetyThresholdDangerousContent;
    private final String safetyThresholdHarassment;
    private final String safetyThresholdHateSpeech;
    private final String safetyThresholdSexuallyExplicit;
    private final String url;
    private final String urlWithApiKey;

    public GeminiRestEndpoint(Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.context = context;
        String geminiModel = GeminiConfig.geminiModel(context);
        this.model = geminiModel;
        this.url = "https://generativelanguage.googleapis.com/v1beta/models/" + geminiModel + ":generateContent";
        if (!TextUtils.isEmpty("")) {
            this.urlWithApiKey = "https://generativelanguage.googleapis.com/v1beta/models/" + geminiModel + ":generateContent?key=";
        } else {
            this.urlWithApiKey = "";
        }
        this.requestPerformer$ar$class_merging$ar$class_merging$ar$class_merging = runnableExecutorPair;
        this.safetyThresholdHarassment = GeminiConfig.INSTANCE.get().safetyThresholdHarassment(context);
        this.safetyThresholdHateSpeech = GeminiConfig.INSTANCE.get().safetyThresholdHateSpeech(context);
        this.safetyThresholdSexuallyExplicit = GeminiConfig.INSTANCE.get().safetyThresholdSexuallyExplicit(context);
        this.safetyThresholdDangerousContent = GeminiConfig.INSTANCE.get().safetyThresholdDangerousContent(context);
        this.prefixPrompt = GeminiConfig.INSTANCE.get().prefixPrompt(context);
        SpannableUtils$IdentifierSpan.getSharedPreferences(context);
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final void cancelCommand() {
        this.requestPerformer$ar$class_merging$ar$class_merging$ar$class_merging.cancelExistingRequestIfNeeded();
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final boolean createRequestGeminiCommand(String str, Bitmap bitmap, boolean z, GeminiActor.GeminiResponseListener geminiResponseListener) {
        String encodeToString;
        String str2;
        LogUtils.v("GeminiEndpoint", "createRequestGeminiCommand - %s", this.model);
        if (TextUtils.isEmpty(this.urlWithApiKey) && !((Task) this.requestPerformer$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable).isSuccessful()) {
            LogUtils.d("GeminiEndpoint", "Gemini API is not supported", new Object[0]);
            geminiResponseListener.onError$ar$edu(1);
        } else if (!SpannableUtils$IdentifierSpan.isNetworkConnected(this.context)) {
            LogUtils.d("GeminiEndpoint", "Internet is not connected", new Object[0]);
            geminiResponseListener.onError$ar$edu(3);
        } else if (bitmap == null) {
            LogUtils.d("GeminiEndpoint", "screenshot is not provided.", new Object[0]);
            geminiResponseListener.onError$ar$edu(4);
        } else if (TextUtils.isEmpty(str)) {
            LogUtils.d("GeminiEndpoint", "command part is empty.", new Object[0]);
            geminiResponseListener.onError$ar$edu(5);
        } else {
            try {
                JSONArray put = new JSONArray().put(0, new JSONObject().put("category", "HARM_CATEGORY_HARASSMENT").put("threshold", this.safetyThresholdHarassment)).put(1, new JSONObject().put("category", "HARM_CATEGORY_HATE_SPEECH").put("threshold", this.safetyThresholdHateSpeech)).put(2, new JSONObject().put("category", "HARM_CATEGORY_SEXUALLY_EXPLICIT").put("threshold", this.safetyThresholdSexuallyExplicit)).put(3, new JSONObject().put("category", "HARM_CATEGORY_DANGEROUS_CONTENT").put("threshold", this.safetyThresholdDangerousContent));
                byte[] encodeImageToByteArray = SpannableUtils$NonCopyableTextSpan.encodeImageToByteArray(bitmap);
                if (encodeImageToByteArray == null) {
                    encodeToString = "";
                } else {
                    encodeToString = Base64.encodeToString(encodeImageToByteArray, 2);
                }
                if (TextUtils.isEmpty(encodeToString)) {
                    LogUtils.e("GeminiEndpoint", "Bitmap compression failed!", new Object[0]);
                    geminiResponseListener.onError$ar$edu(6);
                    return false;
                }
                String str3 = this.prefixPrompt + str;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("text", str3);
                JSONArray put2 = new JSONArray().put(0, jSONObject).put(1, new JSONObject().put("inlineData", new JSONObject().put("mimeType", "image/jpeg").put("data", encodeToString)));
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("contents", new JSONArray().put(0, new JSONObject().put("parts", put2)));
                jSONObject2.put("safetySettings", put);
                LogUtils.v("GeminiDataFieldUtils", "Message Body JSONArray parts:%s", put2);
                LogUtils.v("GeminiDataFieldUtils", "Message Body JSONArray safetySettings:%s", put);
                if (TextUtils.isEmpty(this.urlWithApiKey)) {
                    str2 = this.url;
                } else {
                    str2 = this.urlWithApiKey;
                }
                ExecutionList.RunnableExecutorPair runnableExecutorPair = this.requestPerformer$ar$class_merging$ar$class_merging$ar$class_merging;
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(geminiResponseListener);
                ProxyRequest.Builder builder = new ProxyRequest.Builder(str2);
                StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(true, "Unrecognized http method code.");
                builder.httpMethod = 1;
                if (((Task) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable).isSuccessful()) {
                    LogUtils.d("GeminiRestRequestPerformer", "Use Spatula Header", new Object[0]);
                    builder.putHeader$ar$ds("X-Goog-Spatula", (String) ((Task) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable).getResult());
                }
                builder.putHeader$ar$ds("Content-Type", "application/json");
                builder.body = jSONObject2.toString().getBytes();
                Object obj = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$executor;
                if (builder.body == null) {
                    builder.body = new byte[0];
                }
                ProxyRequest proxyRequest = new ProxyRequest(2, builder.googleUrl, builder.httpMethod, 3000L, builder.body, builder.headers);
                TaskApiCall.Builder builder2 = new TaskApiCall.Builder();
                builder2.TaskApiCall$Builder$ar$execute = new ClearcutLoggerApiImpl$$ExternalSyntheticLambda2((GoogleApi) obj, proxyRequest, 1);
                builder2.methodKey = 1518;
                ListenableFuture listenableFuture = SpannableUtils$NonCopyableTextSpan.toListenableFuture(((GoogleApi) obj).doWrite(builder2.build()));
                runnableExecutorPair.cancelExistingRequestIfNeeded();
                runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next = listenableFuture;
                ContextDataProvider.addCallback(listenableFuture, new ArateaEndpoint.AnonymousClass1(runnableExecutorPair, shadowDelegateImpl, listenableFuture, 2), DirectExecutor.INSTANCE);
                return true;
            } catch (JSONException e) {
                LogUtils.e("GeminiEndpoint", "Error processing Gemini request: ".concat(String.valueOf(e.getMessage())), new Object[0]);
                return false;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final boolean hasPendingTransaction() {
        ?? r0 = this.requestPerformer$ar$class_merging$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next;
        if (r0 != 0 && !r0.isDone()) {
            return true;
        }
        return false;
    }
}

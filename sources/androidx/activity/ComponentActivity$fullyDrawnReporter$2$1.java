package androidx.activity;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavInflater;
import androidx.room.RoomDatabase;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ComponentActivity$fullyDrawnReporter$2$1 extends Lambda implements Function0 {
    final /* synthetic */ Object ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentActivity$fullyDrawnReporter$2$1(Object obj, int i) {
        super(0);
        this.switching_field = i;
        this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0 = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.lang.Object, androidx.lifecycle.ViewModelStoreOwner] */
    /* JADX WARN: Type inference failed for: r0v28, types: [androidx.savedstate.SavedStateRegistryOwner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [androidx.savedstate.SavedStateRegistryOwner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v10 */
    @Override // kotlin.jvm.functions.Function0
    public final /* synthetic */ Object invoke() {
        List list;
        boolean z = false;
        Bundle bundle = 0;
        Application application = null;
        switch (this.switching_field) {
            case 0:
                ((ComponentActivity) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).reportFullyDrawn();
                return Unit.INSTANCE;
            case 1:
                ComponentActivity componentActivity = (ComponentActivity) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                Application application2 = componentActivity.getApplication();
                if (componentActivity.getIntent() != null) {
                    bundle = ((ComponentActivity) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).getIntent().getExtras();
                }
                return new SavedStateViewModelFactory(application2, this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0, bundle);
            case 2:
                Object obj = this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                return new TaskQueue(((ComponentActivity) obj).reportFullyDrawnExecutor$ar$class_merging, new ComponentActivity$fullyDrawnReporter$2$1(obj, 0));
            case 3:
                OnBackPressedDispatcher onBackPressedDispatcher = new OnBackPressedDispatcher(new SearchView$SearchAutoComplete.AnonymousClass1(this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0, 7, bundle));
                if (Build.VERSION.SDK_INT >= 33) {
                    Object obj2 = this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                    if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                        new Handler(Looper.getMainLooper()).post(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(obj2, onBackPressedDispatcher, 11, (byte[]) bundle));
                    } else {
                        ((ComponentActivity) obj2).addObserverForBackInvoker(onBackPressedDispatcher);
                    }
                }
                return onBackPressedDispatcher;
            case 4:
                ((OnBackPressedDispatcher) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).onBackPressed();
                return Unit.INSTANCE;
            case 5:
                ((OnBackPressedDispatcher) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).onBackCancelled();
                return Unit.INSTANCE;
            case 6:
                ((OnBackPressedDispatcher) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).onBackPressed();
                return Unit.INSTANCE;
            case 7:
                return SavedStateHandleSupport.getSavedStateHandlesVM(this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0);
            case 8:
                Context applicationContext = ((NavBackStackEntry) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).context.getApplicationContext();
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                }
                ?? r0 = this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                return new SavedStateViewModelFactory(application, r0, ((NavBackStackEntry) r0).getArguments());
            case 9:
                NavController navController = (NavController) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                return new NavInflater(navController.context, navController._navigatorProvider);
            case 10:
                Pair fragArgsAndRegex = ((NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).getFragArgsAndRegex();
                if (fragArgsAndRegex != null && (list = (List) fragArgsAndRegex.first) != null) {
                    return list;
                }
                return new ArrayList();
            case 11:
                NavDeepLink navDeepLink = (NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                String str = navDeepLink.uriPattern;
                if (str == null || Uri.parse(str).getFragment() == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                String fragment = Uri.parse(navDeepLink.uriPattern).getFragment();
                StringBuilder sb = new StringBuilder();
                fragment.getClass();
                NavDeepLink.buildRegex$ar$ds(fragment, arrayList, sb);
                return new Pair(arrayList, sb.toString());
            case 12:
                String str2 = (String) ((NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).fragRegex$delegate.getValue();
                if (str2 == null) {
                    return null;
                }
                return Pattern.compile(str2, 2);
            case 13:
                Pair fragArgsAndRegex2 = ((NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).getFragArgsAndRegex();
                if (fragArgsAndRegex2 == null) {
                    return null;
                }
                return (String) fragArgsAndRegex2.second;
            case 14:
                String str3 = ((NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).uriPattern;
                if (str3 != null && Uri.parse(str3).getQuery() != null) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 15:
                String str4 = ((NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).mimeTypeRegex;
                if (str4 == null) {
                    return null;
                }
                return Pattern.compile(str4);
            case 16:
                String str5 = ((NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).pathRegex;
                if (str5 == null) {
                    return null;
                }
                return Pattern.compile(str5, 2);
            case 17:
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                NavDeepLink navDeepLink2 = (NavDeepLink) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0;
                if (navDeepLink2.isParameterizedQuery()) {
                    Uri parse = Uri.parse(navDeepLink2.uriPattern);
                    for (String str6 : parse.getQueryParameterNames()) {
                        StringBuilder sb2 = new StringBuilder();
                        List<String> queryParameters = parse.getQueryParameters(str6);
                        if (queryParameters.size() <= 1) {
                            queryParameters.getClass();
                            String str7 = (String) OnDeviceLanguageIdentificationLogEvent.firstOrNull(queryParameters);
                            if (str7 == null) {
                                navDeepLink2.isSingleQueryParamValueOnly = true;
                                str7 = str6;
                            }
                            Matcher matcher = NavDeepLink.FILL_IN_PATTERN.matcher(str7);
                            NavDeepLink.ParamQuery paramQuery = new NavDeepLink.ParamQuery();
                            int i = 0;
                            while (matcher.find()) {
                                String group = matcher.group(1);
                                group.getClass();
                                paramQuery.arguments.add(group);
                                str7.getClass();
                                String substring = str7.substring(i, matcher.start());
                                substring.getClass();
                                sb2.append(Pattern.quote(substring));
                                sb2.append("(.+?)?");
                                i = matcher.end();
                            }
                            if (i < str7.length()) {
                                str7.getClass();
                                String substring2 = str7.substring(i);
                                substring2.getClass();
                                sb2.append(Pattern.quote(substring2));
                            }
                            paramQuery.paramRegex = OnDeviceStainRemovalLogEvent.replace$default$ar$ds(sb2.toString(), ".*", "\\E.*\\Q");
                            str6.getClass();
                            linkedHashMap.put(str6, paramQuery);
                        } else {
                            throw new IllegalArgumentException("Query parameter " + str6 + " must only be present once in " + navDeepLink2.uriPattern + ". To support repeated query parameters, use an array type for your argument and the pattern provided in your URI will be used to parse each query parameter instance.");
                        }
                    }
                }
                return linkedHashMap;
            case 18:
                if (!((RoomDatabase) ((PlusMinusButtons) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).PlusMinusButtons$ar$canMinus).inCompatibilityMode$room_runtime_release() || ((RoomDatabase) ((PlusMinusButtons) this.ComponentActivity$fullyDrawnReporter$2$1$ar$this$0).PlusMinusButtons$ar$canMinus).isOpenInternal()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            case 19:
                return Unit.INSTANCE;
            default:
                return Unit.INSTANCE;
        }
    }
}

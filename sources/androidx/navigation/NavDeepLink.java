package androidx.navigation;

import _COROUTINE._BOUNDARY;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.ComponentActivity$fullyDrawnReporter$2$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.SynchronizedLazyImpl;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavDeepLink {
    public final String action;
    private final Lazy fragArgs$delegate;
    private final Lazy fragArgsAndRegex$delegate;
    public final Lazy fragPattern$delegate;
    public final Lazy fragRegex$delegate;
    public boolean isExactDeepLink;
    private final Lazy isParameterizedQuery$delegate;
    public boolean isSingleQueryParamValueOnly;
    public final String mimeType;
    public final Lazy mimeTypePattern$delegate;
    public String mimeTypeRegex;
    public final List pathArgs;
    private final Lazy pathPattern$delegate;
    public String pathRegex;
    private final Lazy queryArgsMap$delegate;
    public final String uriPattern;
    private static final Pattern SCHEME_PATTERN = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");
    public static final Pattern FILL_IN_PATTERN = Pattern.compile("\\{(.+?)\\}");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MimeType implements Comparable {
        public String subType;
        public String type;

        public MimeType(String str) {
            List list;
            List list2;
            str.getClass();
            Pattern compile = Pattern.compile("/");
            compile.getClass();
            Matcher matcher = new Regex(compile).nativePattern.matcher(str);
            if (!matcher.find()) {
                list = OnDeviceLanguageIdentificationLogEvent.listOf(str.toString());
            } else {
                ArrayList arrayList = new ArrayList(10);
                int i = 0;
                do {
                    arrayList.add(str.subSequence(i, matcher.start()).toString());
                    i = matcher.end();
                } while (matcher.find());
                arrayList.add(str.subSequence(i, str.length()).toString());
                list = arrayList;
            }
            if (!list.isEmpty()) {
                ListIterator listIterator = list.listIterator(list.size());
                while (listIterator.hasPrevious()) {
                    if (((String) listIterator.previous()).length() != 0) {
                        int nextIndex = listIterator.nextIndex() + 1;
                        if (nextIndex >= 0) {
                            if (nextIndex == 0) {
                                list2 = EmptyList.INSTANCE;
                            } else if (nextIndex >= list.size()) {
                                list2 = OnDeviceLanguageIdentificationLogEvent.toList(list);
                            } else if (nextIndex == 1) {
                                list2 = OnDeviceLanguageIdentificationLogEvent.listOf(OnDeviceLanguageIdentificationLogEvent.first(list));
                            } else {
                                ArrayList arrayList2 = new ArrayList(nextIndex);
                                Iterator it = list.iterator();
                                int i2 = 0;
                                while (it.hasNext()) {
                                    arrayList2.add(it.next());
                                    i2++;
                                    if (i2 == nextIndex) {
                                        break;
                                    }
                                }
                                list2 = OnDeviceLanguageIdentificationLogEvent.optimizeReadOnlyList(arrayList2);
                            }
                            this.type = (String) list2.get(0);
                            this.subType = (String) list2.get(1);
                        }
                        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(nextIndex, "Requested element count ", " is less than zero."));
                    }
                }
            }
            list2 = EmptyList.INSTANCE;
            this.type = (String) list2.get(0);
            this.subType = (String) list2.get(1);
        }

        @Override // java.lang.Comparable
        public final int compareTo(MimeType mimeType) {
            mimeType.getClass();
            boolean areEqual = Intrinsics.areEqual(this.type, mimeType.type);
            boolean areEqual2 = Intrinsics.areEqual(this.subType, mimeType.subType);
            int i = true != areEqual ? 0 : 2;
            return areEqual2 ? i + 1 : i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ParamQuery {
        public final List arguments = new ArrayList();
        public String paramRegex;
    }

    public NavDeepLink(String str, String str2, String str3) {
        this.uriPattern = str;
        this.action = str2;
        this.mimeType = str3;
        ArrayList arrayList = new ArrayList();
        this.pathArgs = arrayList;
        this.pathPattern$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 16));
        this.isParameterizedQuery$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 14));
        this.queryArgsMap$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.NONE$ar$edu, new ComponentActivity$fullyDrawnReporter$2$1(this, 17));
        this.fragArgsAndRegex$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.NONE$ar$edu, new ComponentActivity$fullyDrawnReporter$2$1(this, 11));
        this.fragArgs$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.NONE$ar$edu, new ComponentActivity$fullyDrawnReporter$2$1(this, 10));
        this.fragRegex$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.NONE$ar$edu, new ComponentActivity$fullyDrawnReporter$2$1(this, 13));
        this.fragPattern$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 12));
        this.mimeTypePattern$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2$1(this, 15));
        if (str != null) {
            StringBuilder sb = new StringBuilder("^");
            if (!SCHEME_PATTERN.matcher(str).find()) {
                sb.append("http[s]?://");
            }
            Matcher matcher = Pattern.compile("(\\?|\\#|$)").matcher(str);
            matcher.find();
            boolean z = false;
            String substring = str.substring(0, matcher.start());
            substring.getClass();
            buildRegex$ar$ds(substring, arrayList, sb);
            if (!OnDeviceStainRemovalLogEvent.contains$default$ar$ds(sb, ".*") && !OnDeviceStainRemovalLogEvent.contains$default$ar$ds(sb, "([^/]+?)")) {
                z = true;
            }
            this.isExactDeepLink = z;
            sb.append("($|(\\?(.)*)|(\\#(.)*))");
            this.pathRegex = OnDeviceStainRemovalLogEvent.replace$default$ar$ds(sb.toString(), ".*", "\\E.*\\Q");
        }
        if (str3 == null) {
            return;
        }
        if (Pattern.compile("^[\\s\\S]+/[\\s\\S]+$").matcher(str3).matches()) {
            MimeType mimeType = new MimeType(str3);
            this.mimeTypeRegex = OnDeviceStainRemovalLogEvent.replace$default$ar$ds("^(" + mimeType.type + "|[*]+)/(" + mimeType.subType + "|[*]+)$", "*|[*]", "[\\s\\S]");
            return;
        }
        throw new IllegalArgumentException("The given mimeType " + str3 + " does not match to required \"type/subtype\" format");
    }

    public static final void buildRegex$ar$ds(String str, List list, StringBuilder sb) {
        Matcher matcher = FILL_IN_PATTERN.matcher(str);
        int i = 0;
        while (matcher.find()) {
            String group = matcher.group(1);
            group.getClass();
            list.add(group);
            if (matcher.start() > i) {
                String substring = str.substring(i, matcher.start());
                substring.getClass();
                sb.append(Pattern.quote(substring));
            }
            sb.append("([^/]+?)");
            i = matcher.end();
        }
        if (i < str.length()) {
            String substring2 = str.substring(i);
            substring2.getClass();
            sb.append(Pattern.quote(substring2));
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof NavDeepLink)) {
            NavDeepLink navDeepLink = (NavDeepLink) obj;
            if (Intrinsics.areEqual(this.uriPattern, navDeepLink.uriPattern) && Intrinsics.areEqual(this.action, navDeepLink.action) && Intrinsics.areEqual(this.mimeType, navDeepLink.mimeType)) {
                return true;
            }
        }
        return false;
    }

    public final List getFragArgs() {
        return (List) this.fragArgs$delegate.getValue();
    }

    public final Pair getFragArgsAndRegex() {
        return (Pair) this.fragArgsAndRegex$delegate.getValue();
    }

    public final boolean getMatchingPathArguments(Matcher matcher, Bundle bundle, Map map) {
        List list = this.pathArgs;
        ArrayList arrayList = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(list));
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
            }
            String str = (String) obj;
            String decode = Uri.decode(matcher.group(i2));
            NavArgument navArgument = (NavArgument) map.get(str);
            try {
                decode.getClass();
                parseArgument$ar$ds(bundle, str, decode, navArgument);
                arrayList.add(Unit.INSTANCE);
                i = i2;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return true;
    }

    public final boolean getMatchingQueryArguments(Uri uri, Bundle bundle, Map map) {
        Matcher matcher;
        Iterator it;
        String query;
        Iterator it2 = getQueryArgsMap().entrySet().iterator();
        loop0: while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            String str = (String) entry.getKey();
            ParamQuery paramQuery = (ParamQuery) entry.getValue();
            List<String> queryParameters = uri.getQueryParameters(str);
            if (this.isSingleQueryParamValueOnly && (query = uri.getQuery()) != null && !Intrinsics.areEqual(query, uri.toString())) {
                queryParameters = OnDeviceLanguageIdentificationLogEvent.listOf(query);
            }
            if (queryParameters != null) {
                for (String str2 : queryParameters) {
                    String str3 = paramQuery.paramRegex;
                    if (str3 != null) {
                        matcher = Pattern.compile(str3, 32).matcher(str2);
                    } else {
                        matcher = null;
                    }
                    int i = 0;
                    if (matcher == null || !matcher.matches()) {
                        return false;
                    }
                    Bundle bundle2 = new Bundle();
                    try {
                        List list = paramQuery.arguments;
                        ArrayList arrayList = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(list));
                        for (Object obj : list) {
                            int i2 = i + 1;
                            if (i < 0) {
                                OnDeviceLanguageIdentificationLogEvent.throwIndexOverflow();
                            }
                            String str4 = (String) obj;
                            String group = matcher.group(i2);
                            if (group == null) {
                                group = "";
                            }
                            try {
                                NavArgument navArgument = (NavArgument) map.get(str4);
                                if (!bundle.containsKey(str4)) {
                                    StringBuilder sb = new StringBuilder();
                                    it = it2;
                                    try {
                                        sb.append('{');
                                        sb.append(str4);
                                        sb.append('}');
                                        if (!Intrinsics.areEqual(group, sb.toString())) {
                                            parseArgument$ar$ds(bundle2, str4, group, navArgument);
                                        }
                                        arrayList.add(Unit.INSTANCE);
                                        i = i2;
                                        it2 = it;
                                    } catch (IllegalArgumentException unused) {
                                    }
                                } else {
                                    it = it2;
                                    if (navArgument != null) {
                                        NavType navType = navArgument.type;
                                        Object obj2 = navType.get(bundle, str4);
                                        str4.getClass();
                                        if (bundle.containsKey(str4)) {
                                            navType.put(bundle, str4, navType.parseValue(group, obj2));
                                        } else {
                                            throw new IllegalArgumentException("There is no previous value in this bundle.");
                                            break loop0;
                                        }
                                    } else {
                                        continue;
                                    }
                                    arrayList.add(Unit.INSTANCE);
                                    i = i2;
                                    it2 = it;
                                }
                            } catch (IllegalArgumentException unused2) {
                                it = it2;
                                it2 = it;
                            }
                        }
                        it = it2;
                        bundle.putAll(bundle2);
                    } catch (IllegalArgumentException unused3) {
                    }
                    it2 = it;
                }
            }
        }
        return true;
    }

    public final Pattern getPathPattern() {
        return (Pattern) this.pathPattern$delegate.getValue();
    }

    public final Map getQueryArgsMap() {
        return (Map) this.queryArgsMap$delegate.getValue();
    }

    public final int hashCode() {
        int i;
        int i2;
        String str = this.uriPattern;
        int i3 = 0;
        if (str != null) {
            i = str.hashCode();
        } else {
            i = 0;
        }
        String str2 = this.action;
        if (str2 != null) {
            i2 = str2.hashCode();
        } else {
            i2 = 0;
        }
        int i4 = i * 31;
        String str3 = this.mimeType;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        return ((i4 + i2) * 31) + i3;
    }

    public final boolean isParameterizedQuery() {
        return ((Boolean) this.isParameterizedQuery$delegate.getValue()).booleanValue();
    }

    public final void parseArgument$ar$ds(Bundle bundle, String str, String str2, NavArgument navArgument) {
        if (navArgument != null) {
            str.getClass();
            NavType navType = navArgument.type;
            navType.put(bundle, str, navType.parseValue(str2));
            return;
        }
        bundle.putString(str, str2);
    }
}

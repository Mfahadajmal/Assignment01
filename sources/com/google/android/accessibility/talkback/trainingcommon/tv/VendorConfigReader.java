package com.google.android.accessibility.talkback.trainingcommon.tv;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.trainingcommon.ExternalDrawableResource;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.tv.TvPageConfig;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Comparator$CC;
import j$.util.Comparator$EL;
import j$.util.Optional;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VendorConfigReader {
    public static final /* synthetic */ int VendorConfigReader$ar$NoOp = 0;
    private static final Map vendorConfigCache = new HashMap();
    static final ImmutableList CUSTOMIZABLE_PAGES = ImmutableList.of((Object) PageConfig.PageId.PAGE_ID_TV_OVERVIEW, (Object) PageConfig.PageId.PAGE_ID_TV_REMOTE, (Object) PageConfig.PageId.PAGE_ID_TV_SHORTCUT);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InvalidConfigException extends Exception {
        public InvalidConfigException(String str) {
            super(str);
        }

        public InvalidConfigException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VendorPackage {
        public final String packageName;
        public final Resources resources;

        public VendorPackage() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof VendorPackage) {
                VendorPackage vendorPackage = (VendorPackage) obj;
                if (this.packageName.equals(vendorPackage.packageName()) && this.resources.equals(vendorPackage.resources())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.packageName.hashCode() ^ 1000003) * 1000003) ^ this.resources.hashCode();
        }

        public final String packageName() {
            return this.packageName;
        }

        public final Resources resources() {
            return this.resources;
        }

        public final String toString() {
            return "VendorPackage{packageName=" + this.packageName + ", resources=" + this.resources.toString() + "}";
        }

        public VendorPackage(String str, Resources resources) {
            this();
            if (str == null) {
                throw new NullPointerException("Null packageName");
            }
            this.packageName = str;
            if (resources != null) {
                this.resources = resources;
                return;
            }
            throw new NullPointerException("Null resources");
        }
    }

    static TvPageConfig readPageContentFromJson(JSONObject jSONObject, boolean z, VendorPackage vendorPackage) {
        TvPageConfig.Builder builder = TvPageConfig.builder();
        if (jSONObject.has("enabled")) {
            builder.setEnabled$ar$ds$5aaec64b_0(jSONObject.getBoolean("enabled"));
        }
        if (jSONObject.has("title")) {
            String string = jSONObject.getString("title");
            if (string.length() > 0) {
                builder.title = string;
            }
        }
        if (jSONObject.has("summary")) {
            String string2 = jSONObject.getString("summary");
            if (string2.length() > 0) {
                builder.summary = string2;
            }
        }
        if (z && jSONObject.has("image")) {
            Resources resources = vendorPackage.resources;
            String str = vendorPackage.packageName;
            String string3 = jSONObject.getString("image");
            if (string3.length() > 0) {
                int identifier = resources.getIdentifier(string3, "drawable", str);
                if (identifier != 0) {
                    builder.TvPageConfig$Builder$ar$image = new ExternalDrawableResource(str, identifier);
                } else {
                    throw new InvalidConfigException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(string3, "Image '", "' not found."));
                }
            }
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static VendorConfig retrieveConfig(Context context) {
        if (context == null) {
            return null;
        }
        Map map = vendorConfigCache;
        if (map.containsKey(context)) {
            Optional optional = (Optional) map.get(context);
            optional.getClass();
            return (VendorConfig) optional.orElse(null);
        }
        PackageManager packageManager = context.getPackageManager();
        ImmutableList sortedCopyOf = ImmutableList.sortedCopyOf(Comparator$EL.reversed(Comparator$CC.comparingInt(new NodePathDescription$$ExternalSyntheticLambda1(2))), packageManager.queryBroadcastReceivers(new Intent("com.google.android.accessibility.talkback.training.tv.CUSTOMIZATION"), 0));
        int i = ((RegularImmutableList) sortedCopyOf).size;
        for (int i2 = 0; i2 < i; i2++) {
            ResolveInfo resolveInfo = (ResolveInfo) sortedCopyOf.get(i2);
            if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.applicationInfo != null) {
                String str = resolveInfo.activityInfo.packageName;
                try {
                    Resources resourcesForApplication = packageManager.getResourcesForApplication(str);
                    LogUtils.i("VendorConfigReader", "Found customization package %s", str);
                    VendorConfig retrieveConfigFromPackage = retrieveConfigFromPackage(new VendorPackage(str, resourcesForApplication));
                    if (retrieveConfigFromPackage != null) {
                        vendorConfigCache.put(context, Optional.of(retrieveConfigFromPackage));
                        return retrieveConfigFromPackage;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    LogUtils.e("VendorConfigReader", "Failed to find resources for: %s", str);
                }
            }
        }
        vendorConfigCache.put(context, Optional.empty());
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static VendorConfig retrieveConfigFromPackage(VendorPackage vendorPackage) {
        ImmutableList copyOf;
        String str;
        TvPageConfig readPageContentFromJson;
        Resources resources = vendorPackage.resources;
        int identifier = resources.getIdentifier("talkback_tutorial_config", "raw", vendorPackage.packageName);
        if (identifier == 0) {
            LogUtils.e("VendorConfigReader", "JSON file (talkback_tutorial_config.json) not found.", new Object[0]);
            return null;
        }
        try {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(resources.openRawResource(identifier), StandardCharsets.UTF_8);
                StringBuilder sb = new StringBuilder();
                ContextDataProvider.copyReaderToBuilder$ar$ds(inputStreamReader, sb);
                JSONObject jSONObject = new JSONObject(sb.toString());
                HashMap hashMap = new HashMap();
                ImmutableList immutableList = CUSTOMIZABLE_PAGES;
                int i = ((RegularImmutableList) immutableList).size;
                for (int i2 = 0; i2 < i; i2++) {
                    PageConfig.PageId pageId = (PageConfig.PageId) immutableList.get(i2);
                    try {
                        switch (pageId.ordinal()) {
                            case 59:
                                str = "overview_step";
                                break;
                            case 60:
                                str = "remote_step";
                                break;
                            case 61:
                                str = "shortcut_step";
                                break;
                            default:
                                throw new AssertionError(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(pageId, "No JSON keyword defined for PageId ", "."));
                        }
                        if (!jSONObject.has(str)) {
                            readPageContentFromJson = TvPageConfig.builder().build();
                        } else {
                            try {
                                readPageContentFromJson = readPageContentFromJson(jSONObject.getJSONObject(str), false, vendorPackage);
                            } catch (JSONException e) {
                                throw new InvalidConfigException("Invalid JSON for page ".concat(str), e);
                            }
                        }
                        hashMap.put(pageId, readPageContentFromJson);
                    } catch (InvalidConfigException e2) {
                        LogUtils.e("VendorConfigReader", e2.getMessage(), new Object[0]);
                        return null;
                    }
                }
                try {
                    if (!jSONObject.has("custom_steps")) {
                        copyOf = RegularImmutableList.EMPTY;
                    } else {
                        ArrayList arrayList = new ArrayList();
                        try {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("custom_steps");
                            if (jSONObject2.has("steps")) {
                                JSONArray jSONArray = jSONObject2.getJSONArray("steps");
                                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                                    TvPageConfig readPageContentFromJson2 = readPageContentFromJson(jSONArray.getJSONObject(i3), true, vendorPackage);
                                    String str2 = readPageContentFromJson2.title;
                                    if (str2 != null && str2.length() != 0) {
                                        String str3 = readPageContentFromJson2.summary;
                                        if (str3 != null && str3.length() != 0) {
                                            arrayList.add(readPageContentFromJson2);
                                        } else {
                                            throw new InvalidConfigException("Custom steps must have a summary.");
                                        }
                                    } else {
                                        throw new InvalidConfigException("Custom steps must have a title.");
                                    }
                                }
                            }
                            copyOf = ImmutableList.copyOf((Collection) arrayList);
                        } catch (JSONException e3) {
                            throw new InvalidConfigException("Invalid JSON for custom steps.", e3);
                        }
                    }
                    return new VendorConfig(ImmutableMap.copyOf((Map) hashMap), ImmutableList.copyOf((Collection) copyOf));
                } catch (InvalidConfigException e4) {
                    LogUtils.e("VendorConfigReader", e4, "%s", e4.getMessage());
                    return null;
                }
            } catch (IOException | JSONException e5) {
                LogUtils.e("VendorConfigReader", e5.getMessage(), new Object[0]);
                return null;
            }
        } catch (Resources.NotFoundException unused) {
            LogUtils.e("VendorConfigReader", "JSON file (talkback_tutorial_config.json) could not be opened.", new Object[0]);
            return null;
        }
    }
}

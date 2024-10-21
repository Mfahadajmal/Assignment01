package com.google.android.libraries.performance.primes.metrics.network;

import com.google.common.collect.ImmutableSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetricCollector {
    private static final Pattern ALLOWED_SANITIZED_PATH_PATTERN;
    private static final Pattern CONTENT_TYPE_PATTERN;
    public static final Pattern FILENAME_PATTERN;
    public static final Pattern FILENAME_PATTERN_WITH_EXTENSION;
    private static final Pattern IP_ADDRESS_PATTERN;
    public static final Pattern PARAMETERS_PATTERN;
    private static final Pattern PATH_WITH_SUBDOMAIN_PATTERN;
    private final Provider configsProvider;

    static {
        ImmutableSet.construct(4, "googleapis.com", "adwords.google.com", "m.google.com", "sandbox.google.com");
        CONTENT_TYPE_PATTERN = Pattern.compile("(?:[^\\/]*\\/)([^;]*)");
        PARAMETERS_PATTERN = Pattern.compile("([^\\?]+)(\\?+)");
        PATH_WITH_SUBDOMAIN_PATTERN = Pattern.compile("((?:https?:\\/\\/|)[a-zA-Z0-9-_\\.]+(?::\\d+)?)(.*)?");
        FILENAME_PATTERN = Pattern.compile("(.*)(?<!https?:\\/)(?:\\/[\\w]+$)");
        FILENAME_PATTERN_WITH_EXTENSION = Pattern.compile("(.*)(?<!https?:\\/)(?:\\/[\\w]+\\.[\\w]*$)");
        ALLOWED_SANITIZED_PATH_PATTERN = Pattern.compile("([a-zA-Z0-9-_]+)");
        IP_ADDRESS_PATTERN = Pattern.compile("\\b([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})(:\\d{1,5})?\\b");
    }

    public NetworkMetricCollector(Provider provider) {
        this.configsProvider = provider;
    }

    static String getDomainFromUrl(String str) {
        Matcher matcher = PATH_WITH_SUBDOMAIN_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    static String trimIpAddress(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = IP_ADDRESS_PATTERN.matcher(str);
        if (matcher.find()) {
            return matcher.replaceFirst("<ip>");
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric getMetric(java.lang.Iterable r15) {
        /*
            Method dump skipped, instructions count: 855
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.network.NetworkMetricCollector.getMetric(java.lang.Iterable):logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric");
    }
}

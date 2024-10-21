package com.google.android.libraries.storage.file.backends;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.storage.file.common.internal.LiteTransformFragments;
import com.google.common.collect.ImmutableList;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidUri {
    public static final Pattern MODULE_PATTERN = Pattern.compile("[a-z]+(_[a-z]+)*");
    static final Account SHARED_ACCOUNT = AccountSerialization.SHARED_ACCOUNT;
    public static final Set RESERVED_MODULES = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList("default", "unused", "special", "reserved", "shared", "virtual", "managed")));
    public static final Set VALID_LOCATIONS = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList("files", "cache", "managed", "directboot-files", "directboot-cache", "external")));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private String packageName;
        private String location = "files";
        private String module = "common";
        private Account account = AndroidUri.SHARED_ACCOUNT;
        private String relativePath = "";
        private final ImmutableList.Builder encodedSpecs = new ImmutableList.Builder();

        public Builder(Context context) {
            DisplayStats.checkArgument(true, "Context cannot be null", new Object[0]);
            this.packageName = context.getPackageName();
        }

        public final Uri build() {
            boolean z;
            boolean z2;
            String str;
            String str2 = this.location;
            String str3 = this.module;
            Account account = this.account;
            Account account2 = AccountSerialization.SHARED_ACCOUNT;
            boolean z3 = true;
            if (account.type.indexOf(58) == -1) {
                z = true;
            } else {
                z = false;
            }
            DisplayStats.checkArgument(z, "Account type contains ':'.", new Object[0]);
            if (account.type.indexOf(47) == -1) {
                z2 = true;
            } else {
                z2 = false;
            }
            DisplayStats.checkArgument(z2, "Account type contains '/'.", new Object[0]);
            if (account.name.indexOf(47) != -1) {
                z3 = false;
            }
            DisplayStats.checkArgument(z3, "Account name contains '/'.", new Object[0]);
            if (AccountSerialization.isSharedAccount(account)) {
                str = "shared";
            } else {
                str = account.type + ":" + account.name;
            }
            return new Uri.Builder().scheme("android").authority(this.packageName).path("/" + str2 + "/" + str3 + "/" + str + "/" + this.relativePath).encodedFragment(LiteTransformFragments.joinTransformSpecs(this.encodedSpecs.build())).build();
        }

        public final void setDirectBootFilesLocation$ar$ds() {
            DisplayStats.checkArgument(AndroidUri.VALID_LOCATIONS.contains("directboot-files"), "The only supported locations are %s: %s", AndroidUri.VALID_LOCATIONS, "directboot-files");
            this.location = "directboot-files";
        }

        public final void setModule$ar$ds(String str) {
            DisplayStats.checkArgument(AndroidUri.MODULE_PATTERN.matcher(str).matches(), "Module must match [a-z]+(_[a-z]+)*: %s", str);
            DisplayStats.checkArgument(!AndroidUri.RESERVED_MODULES.contains(str), "Module name is reserved and cannot be used: %s", str);
            this.module = str;
        }

        public final void setRelativePath$ar$ds(String str) {
            if (str.startsWith("/")) {
                str = str.substring(1);
            }
            Pattern pattern = AndroidUri.MODULE_PATTERN;
            this.relativePath = str;
        }
    }
}

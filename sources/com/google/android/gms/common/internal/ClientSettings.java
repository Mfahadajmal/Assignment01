package com.google.android.gms.common.internal;

import android.accounts.Account;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import j$.util.DesugarCollections;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientSettings {
    public final Account account;
    public final Set allRequestedScopes;
    public final Map optionalApiSettingsMap;
    public final String realClientClassName;
    public final String realClientPackageName;
    public final Set requiredScopes;
    public Integer sessionId;
    public final SignInOptions signInOptions;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object ClientSettings$Builder$ar$account;
        public Object ClientSettings$Builder$ar$realClientClassName;
        public Object ClientSettings$Builder$ar$realClientPackageName;
        public Object ClientSettings$Builder$ar$requiredScopes;
        public final Object ClientSettings$Builder$ar$signInOptions;

        public Builder() {
            this.ClientSettings$Builder$ar$signInOptions = SignInOptions.DEFAULT;
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Set, java.lang.Object] */
        public final ClientSettings build() {
            Object obj = this.ClientSettings$Builder$ar$account;
            ?? r2 = this.ClientSettings$Builder$ar$requiredScopes;
            Object obj2 = this.ClientSettings$Builder$ar$realClientPackageName;
            return new ClientSettings((Account) obj, r2, (String) obj2, (String) this.ClientSettings$Builder$ar$realClientClassName, (SignInOptions) this.ClientSettings$Builder$ar$signInOptions);
        }

        public Builder(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
            this.ClientSettings$Builder$ar$signInOptions = shadowDelegateImpl;
        }
    }

    public ClientSettings(Account account, Set set, String str, String str2, SignInOptions signInOptions) {
        Set unmodifiableSet;
        this.account = account;
        if (set == null) {
            unmodifiableSet = Collections.emptySet();
        } else {
            unmodifiableSet = DesugarCollections.unmodifiableSet(set);
        }
        this.requiredScopes = unmodifiableSet;
        Map emptyMap = Collections.emptyMap();
        this.optionalApiSettingsMap = emptyMap;
        this.realClientPackageName = str;
        this.realClientClassName = str2;
        this.signInOptions = signInOptions;
        HashSet hashSet = new HashSet(unmodifiableSet);
        Iterator it = emptyMap.values().iterator();
        while (it.hasNext()) {
            Object obj = ((AppLifecycleMonitor) it.next()).AppLifecycleMonitor$ar$tracker;
            hashSet.addAll(null);
        }
        this.allRequestedScopes = DesugarCollections.unmodifiableSet(hashSet);
    }
}

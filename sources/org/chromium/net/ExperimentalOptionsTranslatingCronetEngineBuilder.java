package org.chromium.net;

import _COROUTINE._BOUNDARY;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.net.CronetEngine;
import org.chromium.net.DnsOptions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExperimentalOptionsTranslatingCronetEngineBuilder extends ICronetEngineBuilder {
    private static final Set SUPPORTED_OPTIONS = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(1, 2, 3)));
    private final ICronetEngineBuilder mDelegate;
    private final List mExperimentalOptionsPatches = new ArrayList();
    private JSONObject mParsedExperimentalOptions;

    /* compiled from: PG */
    @FunctionalInterface
    /* loaded from: classes.dex */
    interface ExperimentalOptionsPatch {
        void applyTo(JSONObject jSONObject);
    }

    public ExperimentalOptionsTranslatingCronetEngineBuilder(ICronetEngineBuilder iCronetEngineBuilder) {
        this.mDelegate = iCronetEngineBuilder;
    }

    public static JSONObject createDefaultIfAbsent(JSONObject jSONObject, String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
            try {
                jSONObject.put(str, optJSONObject);
            } catch (JSONException e) {
                throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "Failed adding a default object for key [", "]"), e);
            }
        }
        return optJSONObject;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder addPublicKeyPins(String str, Set<byte[]> set, boolean z, Date date) {
        this.mDelegate.addPublicKeyPins(str, set, z, date);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder addQuicHint(String str, int i, int i2) {
        this.mDelegate.addQuicHint(str, i, i2);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ExperimentalCronetEngine build() {
        if (this.mParsedExperimentalOptions == null && this.mExperimentalOptionsPatches.isEmpty()) {
            return this.mDelegate.build();
        }
        if (this.mParsedExperimentalOptions == null) {
            this.mParsedExperimentalOptions = new JSONObject();
        }
        Iterator it = this.mExperimentalOptionsPatches.iterator();
        while (it.hasNext()) {
            try {
                ((ExperimentalOptionsPatch) it.next()).applyTo(this.mParsedExperimentalOptions);
            } catch (JSONException e) {
                throw new IllegalStateException("Unable to apply JSON patch!", e);
            }
        }
        this.mDelegate.setExperimentalOptions(this.mParsedExperimentalOptions.toString());
        return this.mDelegate.build();
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enableBrotli(boolean z) {
        this.mDelegate.enableBrotli(z);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enableHttp2(boolean z) {
        this.mDelegate.enableHttp2(z);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enableHttpCache(int i, long j) {
        this.mDelegate.enableHttpCache(i, j);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enableNetworkQualityEstimator(boolean z) {
        this.mDelegate.enableNetworkQualityEstimator(z);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean z) {
        this.mDelegate.enablePublicKeyPinningBypassForLocalTrustAnchors(z);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enableQuic(boolean z) {
        this.mDelegate.enableQuic(z);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder enableSdch(boolean z) {
        this.mDelegate.enableSdch(z);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final String getDefaultUserAgent() {
        return this.mDelegate.getDefaultUserAgent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.ICronetEngineBuilder
    public final long getLogCronetInitializationRef() {
        return this.mDelegate.getLogCronetInitializationRef();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.chromium.net.ICronetEngineBuilder
    public final Set<Integer> getSupportedConfigOptions() {
        return SUPPORTED_OPTIONS;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setConnectionMigrationOptions(final ConnectionMigrationOptions connectionMigrationOptions) {
        if (this.mDelegate.getSupportedConfigOptions().contains(1)) {
            this.mDelegate.setConnectionMigrationOptions(connectionMigrationOptions);
            return this;
        }
        this.mExperimentalOptionsPatches.add(new ExperimentalOptionsPatch() { // from class: org.chromium.net.ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticLambda2
            @Override // org.chromium.net.ExperimentalOptionsTranslatingCronetEngineBuilder.ExperimentalOptionsPatch
            public final void applyTo(JSONObject jSONObject) {
                ConnectionMigrationOptions connectionMigrationOptions2 = ConnectionMigrationOptions.this;
                JSONObject createDefaultIfAbsent = ExperimentalOptionsTranslatingCronetEngineBuilder.createDefaultIfAbsent(jSONObject, "QUIC");
                if (connectionMigrationOptions2.getEnableDefaultNetworkMigration() != null) {
                    createDefaultIfAbsent.put("migrate_sessions_on_network_change_v2", connectionMigrationOptions2.getEnableDefaultNetworkMigration());
                }
                if (connectionMigrationOptions2.getAllowServerMigration() != null) {
                    createDefaultIfAbsent.put("allow_server_migration", connectionMigrationOptions2.getAllowServerMigration());
                }
                if (connectionMigrationOptions2.getMigrateIdleConnections() != null) {
                    createDefaultIfAbsent.put("migrate_idle_sessions", connectionMigrationOptions2.getMigrateIdleConnections());
                }
                if (connectionMigrationOptions2.getIdleMigrationPeriodSeconds() != null) {
                    createDefaultIfAbsent.put("idle_session_migration_period_seconds", connectionMigrationOptions2.getIdleMigrationPeriodSeconds());
                }
                if (connectionMigrationOptions2.getRetryPreHandshakeErrorsOnAlternateNetwork() != null) {
                    createDefaultIfAbsent.put("retry_on_alternate_network_before_handshake", connectionMigrationOptions2.getRetryPreHandshakeErrorsOnAlternateNetwork());
                }
                if (connectionMigrationOptions2.getMaxTimeOnNonDefaultNetworkSeconds() != null) {
                    createDefaultIfAbsent.put("max_time_on_non_default_network_seconds", connectionMigrationOptions2.getMaxTimeOnNonDefaultNetworkSeconds());
                }
                if (connectionMigrationOptions2.getMaxPathDegradingEagerMigrationsCount() != null) {
                    createDefaultIfAbsent.put("max_migrations_to_non_default_network_on_path_degrading", connectionMigrationOptions2.getMaxPathDegradingEagerMigrationsCount());
                }
                if (connectionMigrationOptions2.getMaxWriteErrorEagerMigrationsCount() != null) {
                    createDefaultIfAbsent.put("max_migrations_to_non_default_network_on_write_error", connectionMigrationOptions2.getMaxWriteErrorEagerMigrationsCount());
                }
                if (connectionMigrationOptions2.getEnablePathDegradationMigration() != null) {
                    boolean booleanValue = connectionMigrationOptions2.getEnablePathDegradationMigration().booleanValue();
                    createDefaultIfAbsent.put("allow_port_migration", booleanValue);
                    if (connectionMigrationOptions2.getAllowNonDefaultNetworkUsage() != null) {
                        boolean booleanValue2 = connectionMigrationOptions2.getAllowNonDefaultNetworkUsage().booleanValue();
                        if (!booleanValue && booleanValue2) {
                            throw new IllegalArgumentException("Unable to turn on non-default network usage without path degradation migration!");
                        }
                        if (booleanValue && booleanValue2) {
                            createDefaultIfAbsent.put("migrate_sessions_early_v2", true);
                            createDefaultIfAbsent.put("migrate_sessions_on_network_change_v2", true);
                        } else {
                            createDefaultIfAbsent.put("migrate_sessions_early_v2", false);
                        }
                    }
                }
            }
        });
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setDnsOptions(final DnsOptions dnsOptions) {
        if (this.mDelegate.getSupportedConfigOptions().contains(2)) {
            this.mDelegate.setDnsOptions(dnsOptions);
            return this;
        }
        this.mExperimentalOptionsPatches.add(new ExperimentalOptionsPatch() { // from class: org.chromium.net.ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticLambda1
            @Override // org.chromium.net.ExperimentalOptionsTranslatingCronetEngineBuilder.ExperimentalOptionsPatch
            public final void applyTo(JSONObject jSONObject) {
                DnsOptions dnsOptions2 = DnsOptions.this;
                JSONObject createDefaultIfAbsent = ExperimentalOptionsTranslatingCronetEngineBuilder.createDefaultIfAbsent(jSONObject, "AsyncDNS");
                if (dnsOptions2.getUseBuiltInDnsResolver() != null) {
                    createDefaultIfAbsent.put("enable", dnsOptions2.getUseBuiltInDnsResolver());
                }
                JSONObject createDefaultIfAbsent2 = ExperimentalOptionsTranslatingCronetEngineBuilder.createDefaultIfAbsent(jSONObject, "StaleDNS");
                if (dnsOptions2.getEnableStaleDns() != null) {
                    createDefaultIfAbsent2.put("enable", dnsOptions2.getEnableStaleDns());
                }
                if (dnsOptions2.getPersistHostCache() != null) {
                    createDefaultIfAbsent2.put("persist_to_disk", dnsOptions2.getPersistHostCache());
                }
                if (dnsOptions2.getPersistHostCachePeriodMillis() != null) {
                    createDefaultIfAbsent2.put("persist_delay_ms", dnsOptions2.getPersistHostCachePeriodMillis());
                }
                if (dnsOptions2.getStaleDnsOptions() != null) {
                    DnsOptions.StaleDnsOptions staleDnsOptions = dnsOptions2.getStaleDnsOptions();
                    if (staleDnsOptions.getAllowCrossNetworkUsage() != null) {
                        createDefaultIfAbsent2.put("allow_other_network", staleDnsOptions.getAllowCrossNetworkUsage());
                    }
                    if (staleDnsOptions.getFreshLookupTimeoutMillis() != null) {
                        createDefaultIfAbsent2.put("delay_ms", staleDnsOptions.getFreshLookupTimeoutMillis());
                    }
                    if (staleDnsOptions.getUseStaleOnNameNotResolved() != null) {
                        createDefaultIfAbsent2.put("use_stale_on_name_not_resolved", staleDnsOptions.getUseStaleOnNameNotResolved());
                    }
                    if (staleDnsOptions.getMaxExpiredDelayMillis() != null) {
                        createDefaultIfAbsent2.put("max_expired_time_ms", staleDnsOptions.getMaxExpiredDelayMillis());
                    }
                }
                JSONObject createDefaultIfAbsent3 = ExperimentalOptionsTranslatingCronetEngineBuilder.createDefaultIfAbsent(jSONObject, "QUIC");
                if (dnsOptions2.getPreestablishConnectionsToStaleDnsResults() != null) {
                    createDefaultIfAbsent3.put("race_stale_dns_on_connection", dnsOptions2.getPreestablishConnectionsToStaleDnsResults());
                }
            }
        });
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setExperimentalOptions(String str) {
        JSONObject jSONObject = null;
        if (str != null && !str.isEmpty()) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                throw new IllegalArgumentException("Experimental options parsing failed", e);
            }
        }
        this.mParsedExperimentalOptions = jSONObject;
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setLibraryLoader(CronetEngine.Builder.LibraryLoader libraryLoader) {
        this.mDelegate.setLibraryLoader(libraryLoader);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setQuicOptions(final QuicOptions quicOptions) {
        if (this.mDelegate.getSupportedConfigOptions().contains(3)) {
            this.mDelegate.setQuicOptions(quicOptions);
            return this;
        }
        this.mExperimentalOptionsPatches.add(new ExperimentalOptionsPatch() { // from class: org.chromium.net.ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticLambda3
            @Override // org.chromium.net.ExperimentalOptionsTranslatingCronetEngineBuilder.ExperimentalOptionsPatch
            public final void applyTo(JSONObject jSONObject) {
                QuicOptions quicOptions2 = QuicOptions.this;
                JSONObject createDefaultIfAbsent = ExperimentalOptionsTranslatingCronetEngineBuilder.createDefaultIfAbsent(jSONObject, "QUIC");
                if (!quicOptions2.getQuicHostAllowlist().isEmpty()) {
                    createDefaultIfAbsent.put("host_whitelist", ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticBackport0.m(",", quicOptions2.getQuicHostAllowlist()));
                }
                if (!quicOptions2.getEnabledQuicVersions().isEmpty()) {
                    createDefaultIfAbsent.put("quic_version", ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticBackport0.m(",", quicOptions2.getEnabledQuicVersions()));
                }
                if (!quicOptions2.getConnectionOptions().isEmpty()) {
                    createDefaultIfAbsent.put("connection_options", ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticBackport0.m(",", quicOptions2.getConnectionOptions()));
                }
                if (!quicOptions2.getClientConnectionOptions().isEmpty()) {
                    createDefaultIfAbsent.put("client_connection_options", ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticBackport0.m(",", quicOptions2.getClientConnectionOptions()));
                }
                if (!quicOptions2.getExtraQuicheFlags().isEmpty()) {
                    createDefaultIfAbsent.put("set_quic_flags", ExperimentalOptionsTranslatingCronetEngineBuilder$$ExternalSyntheticBackport0.m(",", quicOptions2.getExtraQuicheFlags()));
                }
                if (quicOptions2.getInMemoryServerConfigsCacheSize() != null) {
                    createDefaultIfAbsent.put("max_server_configs_stored_in_properties", quicOptions2.getInMemoryServerConfigsCacheSize());
                }
                if (quicOptions2.getHandshakeUserAgent() != null) {
                    createDefaultIfAbsent.put("user_agent_id", quicOptions2.getHandshakeUserAgent());
                }
                if (quicOptions2.getRetryWithoutAltSvcOnQuicErrors() != null) {
                    createDefaultIfAbsent.put("retry_without_alt_svc_on_quic_errors", quicOptions2.getRetryWithoutAltSvcOnQuicErrors());
                }
                if (quicOptions2.getEnableTlsZeroRtt() != null) {
                    createDefaultIfAbsent.put("disable_tls_zero_rtt", !quicOptions2.getEnableTlsZeroRtt().booleanValue());
                }
                if (quicOptions2.getPreCryptoHandshakeIdleTimeoutSeconds() != null) {
                    createDefaultIfAbsent.put("max_idle_time_before_crypto_handshake_seconds", quicOptions2.getPreCryptoHandshakeIdleTimeoutSeconds());
                }
                if (quicOptions2.getCryptoHandshakeTimeoutSeconds() != null) {
                    createDefaultIfAbsent.put("max_time_before_crypto_handshake_seconds", quicOptions2.getCryptoHandshakeTimeoutSeconds());
                }
                if (quicOptions2.getIdleConnectionTimeoutSeconds() != null) {
                    createDefaultIfAbsent.put("idle_connection_timeout_seconds", quicOptions2.getIdleConnectionTimeoutSeconds());
                }
                if (quicOptions2.getRetransmittableOnWireTimeoutMillis() != null) {
                    createDefaultIfAbsent.put("retransmittable_on_wire_timeout_milliseconds", quicOptions2.getRetransmittableOnWireTimeoutMillis());
                }
                if (quicOptions2.getCloseSessionsOnIpChange() != null) {
                    createDefaultIfAbsent.put("close_sessions_on_ip_change", quicOptions2.getCloseSessionsOnIpChange());
                }
                if (quicOptions2.getGoawaySessionsOnIpChange() != null) {
                    createDefaultIfAbsent.put("goaway_sessions_on_ip_change", quicOptions2.getGoawaySessionsOnIpChange());
                }
                if (quicOptions2.getInitialBrokenServicePeriodSeconds() != null) {
                    createDefaultIfAbsent.put("initial_delay_for_broken_alternative_service_seconds", quicOptions2.getInitialBrokenServicePeriodSeconds());
                }
                if (quicOptions2.getIncreaseBrokenServicePeriodExponentially() != null) {
                    createDefaultIfAbsent.put("exponential_backoff_on_initial_delay", quicOptions2.getIncreaseBrokenServicePeriodExponentially());
                }
                if (quicOptions2.getDelayJobsWithAvailableSpdySession() != null) {
                    createDefaultIfAbsent.put("delay_main_job_with_available_spdy_session", quicOptions2.getDelayJobsWithAvailableSpdySession());
                }
            }
        });
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setStoragePath(String str) {
        this.mDelegate.setStoragePath(str);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setThreadPriority(int i) {
        this.mDelegate.setThreadPriority(i);
        return this;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public final ICronetEngineBuilder setUserAgent(String str) {
        this.mDelegate.setUserAgent(str);
        return this;
    }
}

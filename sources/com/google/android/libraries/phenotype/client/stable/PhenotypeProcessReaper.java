package com.google.android.libraries.phenotype.client.stable;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.MenuPopupWindow;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.ListView;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiCallRunner;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeProcessReaper {
    private static boolean scheduled;
    public final Object PhenotypeProcessReaper$ar$executorProvider;
    public final Object PhenotypeProcessReaper$ar$isKillable;
    public final int pollingMinutes;

    public PhenotypeProcessReaper(MenuPopupWindow menuPopupWindow, MenuBuilder menuBuilder, int i) {
        this.PhenotypeProcessReaper$ar$executorProvider = menuPopupWindow;
        this.PhenotypeProcessReaper$ar$isKillable = menuBuilder;
        this.pollingMinutes = i;
    }

    public final ListView getListView() {
        return ((ListPopupWindow) this.PhenotypeProcessReaper$ar$executorProvider).mDropDownList;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [com.google.common.base.Supplier, java.lang.Object] */
    public final void scheduleReap() {
        synchronized (PhenotypeProcessReaper.class) {
            if (!scheduled) {
                final FlagStore$$ExternalSyntheticLambda3 flagStore$$ExternalSyntheticLambda3 = new FlagStore$$ExternalSyntheticLambda3(this, 4);
                final long j = this.pollingMinutes;
                final TimeUnit timeUnit = TimeUnit.MINUTES;
                final ListeningScheduledExecutorService listeningScheduledExecutorService = (ListeningScheduledExecutorService) this.PhenotypeProcessReaper$ar$executorProvider.get();
                DefaultExperimentTokenDecorator.crashOnFailure(listeningScheduledExecutorService.schedule(new Runnable() { // from class: com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        flagStore$$ExternalSyntheticLambda3.run();
                        DefaultExperimentTokenDecorator.crashOnFailure(listeningScheduledExecutorService.schedule(this, j, timeUnit));
                    }
                }, j, timeUnit));
                scheduled = true;
            }
        }
    }

    public PhenotypeProcessReaper(InputConnection inputConnection, EditorInfo editorInfo, int i) {
        this.PhenotypeProcessReaper$ar$isKillable = inputConnection;
        this.PhenotypeProcessReaper$ar$executorProvider = editorInfo;
        this.pollingMinutes = i;
    }

    public PhenotypeProcessReaper(ApiCallRunner apiCallRunner, int i, GoogleApi googleApi) {
        this.PhenotypeProcessReaper$ar$isKillable = apiCallRunner;
        this.pollingMinutes = i;
        this.PhenotypeProcessReaper$ar$executorProvider = googleApi;
    }

    public PhenotypeProcessReaper(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
        this.PhenotypeProcessReaper$ar$isKillable = colorStateList;
        this.PhenotypeProcessReaper$ar$executorProvider = configuration;
        this.pollingMinutes = theme == null ? 0 : theme.hashCode();
    }

    public PhenotypeProcessReaper(Supplier supplier) {
        PrimesController$$ExternalSyntheticLambda9 primesController$$ExternalSyntheticLambda9 = new PrimesController$$ExternalSyntheticLambda9(16);
        this.PhenotypeProcessReaper$ar$executorProvider = supplier;
        this.pollingMinutes = Math.max(5, 10);
        this.PhenotypeProcessReaper$ar$isKillable = primesController$$ExternalSyntheticLambda9;
    }
}

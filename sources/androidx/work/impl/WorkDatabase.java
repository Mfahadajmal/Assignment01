package androidx.work.impl;

import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import androidx.room.RoomDatabase;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class WorkDatabase extends RoomDatabase {
    public static final AppCompatTextClassifierHelper$Api26Impl Companion$ar$class_merging$2926d319_0 = new AppCompatTextClassifierHelper$Api26Impl();

    public abstract DependencyDao dependencyDao();

    public abstract PreferenceDao preferenceDao();

    public abstract SystemIdInfoDao systemIdInfoDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkProgressDao workProgressDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();
}

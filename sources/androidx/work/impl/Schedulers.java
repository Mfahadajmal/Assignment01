package androidx.work.impl;

import android.database.Cursor;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.core.widget.ImageViewCompat$Api21Impl;
import androidx.core.widget.NestedScrollView;
import androidx.room.RoomSQLiteQuery;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Schedulers {
    public static final /* synthetic */ int Schedulers$ar$NoOp = 0;
    private static final String TAG = Logger.tagWithPrefix("Schedulers");

    private static void markScheduled$ar$class_merging$ar$class_merging$ar$class_merging(WorkSpecDao workSpecDao, WindowCallbackWrapper.Api26Impl api26Impl, List list) {
        long currentTimeMillis;
        if (list.size() > 0) {
            currentTimeMillis = System.currentTimeMillis();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                workSpecDao.markWorkSpecScheduled$ar$ds(((WorkSpec) it.next()).id, currentTimeMillis);
            }
        }
    }

    public static void schedule(Configuration configuration, WorkDatabase workDatabase, List list) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        ArrayList arrayList;
        RoomSQLiteQuery roomSQLiteQuery2;
        int columnIndexOrThrow14;
        int columnIndexOrThrow15;
        int columnIndexOrThrow16;
        int columnIndexOrThrow17;
        int columnIndexOrThrow18;
        int columnIndexOrThrow19;
        int columnIndexOrThrow20;
        int columnIndexOrThrow21;
        int columnIndexOrThrow22;
        int columnIndexOrThrow23;
        int columnIndexOrThrow24;
        int columnIndexOrThrow25;
        int columnIndexOrThrow26;
        int columnIndexOrThrow27;
        int i;
        boolean z;
        int i2;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        boolean z4;
        int i5;
        boolean z5;
        int i6;
        boolean z6;
        int i7;
        boolean z7;
        int i8;
        boolean z8;
        int i9;
        boolean z9;
        int i10;
        boolean z10;
        if (list == null || list.size() == 0) {
            return;
        }
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time", 0);
            ((WorkSpecDao_Impl) workSpecDao).__db.assertNotSuspendingTransaction();
            Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(((WorkSpecDao_Impl) workSpecDao).__db, acquire);
            try {
                columnIndexOrThrow = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "id");
                columnIndexOrThrow2 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "state");
                columnIndexOrThrow3 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "worker_class_name");
                columnIndexOrThrow4 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "input_merger_class_name");
                columnIndexOrThrow5 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "input");
                columnIndexOrThrow6 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "output");
                columnIndexOrThrow7 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "initial_delay");
                columnIndexOrThrow8 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "interval_duration");
                columnIndexOrThrow9 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "flex_duration");
                columnIndexOrThrow10 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "run_attempt_count");
                columnIndexOrThrow11 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "backoff_policy");
                columnIndexOrThrow12 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "backoff_delay_duration");
                columnIndexOrThrow13 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "last_enqueue_time");
                roomSQLiteQuery = acquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = acquire;
            }
            try {
                int columnIndexOrThrow28 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "minimum_retention_duration");
                int columnIndexOrThrow29 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "schedule_requested_at");
                int columnIndexOrThrow30 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "run_in_foreground");
                int columnIndexOrThrow31 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "out_of_quota_policy");
                int columnIndexOrThrow32 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "period_count");
                int columnIndexOrThrow33 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "generation");
                int columnIndexOrThrow34 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "next_schedule_time_override");
                int columnIndexOrThrow35 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "next_schedule_time_override_generation");
                int columnIndexOrThrow36 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "stop_reason");
                int columnIndexOrThrow37 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "trace_tag");
                int columnIndexOrThrow38 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "required_network_type");
                int columnIndexOrThrow39 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "required_network_request");
                int columnIndexOrThrow40 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_charging");
                int columnIndexOrThrow41 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_device_idle");
                int columnIndexOrThrow42 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_battery_not_low");
                int columnIndexOrThrow43 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_storage_not_low");
                int columnIndexOrThrow44 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "trigger_content_update_delay");
                int columnIndexOrThrow45 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "trigger_max_content_delay");
                int columnIndexOrThrow46 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "content_uri_triggers");
                int i11 = columnIndexOrThrow34;
                ArrayList arrayList2 = new ArrayList(query$ar$ds$e1ca310e_0.getCount());
                while (true) {
                    arrayList = arrayList2;
                    if (!query$ar$ds$e1ca310e_0.moveToNext()) {
                        break;
                    }
                    String string = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow);
                    int intToState$ar$edu = AppCompatTextHelper.Api26Impl.intToState$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow2));
                    String string2 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow3);
                    String string3 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow4);
                    Data fromByteArray = Data.fromByteArray(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow5));
                    Data fromByteArray2 = Data.fromByteArray(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow6));
                    long j = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow7);
                    long j2 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow8);
                    long j3 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow9);
                    int i12 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow10);
                    int intToBackoffPolicy$ar$edu = AppCompatTextHelper.Api26Impl.intToBackoffPolicy$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow11));
                    long j4 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow12);
                    long j5 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow13);
                    int i13 = columnIndexOrThrow28;
                    long j6 = query$ar$ds$e1ca310e_0.getLong(i13);
                    int i14 = columnIndexOrThrow29;
                    long j7 = query$ar$ds$e1ca310e_0.getLong(i14);
                    int i15 = columnIndexOrThrow6;
                    int i16 = columnIndexOrThrow30;
                    if (query$ar$ds$e1ca310e_0.getInt(i16) != 0) {
                        columnIndexOrThrow30 = i16;
                        i6 = columnIndexOrThrow31;
                        z6 = true;
                    } else {
                        columnIndexOrThrow30 = i16;
                        i6 = columnIndexOrThrow31;
                        z6 = false;
                    }
                    int intToOutOfQuotaPolicy$ar$edu = AppCompatTextHelper.Api26Impl.intToOutOfQuotaPolicy$ar$edu(query$ar$ds$e1ca310e_0.getInt(i6));
                    columnIndexOrThrow31 = i6;
                    int i17 = columnIndexOrThrow32;
                    int i18 = query$ar$ds$e1ca310e_0.getInt(i17);
                    columnIndexOrThrow32 = i17;
                    int i19 = columnIndexOrThrow33;
                    int i20 = query$ar$ds$e1ca310e_0.getInt(i19);
                    columnIndexOrThrow33 = i19;
                    int i21 = i11;
                    long j8 = query$ar$ds$e1ca310e_0.getLong(i21);
                    i11 = i21;
                    int i22 = columnIndexOrThrow35;
                    int i23 = query$ar$ds$e1ca310e_0.getInt(i22);
                    columnIndexOrThrow35 = i22;
                    int i24 = columnIndexOrThrow36;
                    int i25 = query$ar$ds$e1ca310e_0.getInt(i24);
                    columnIndexOrThrow36 = i24;
                    int i26 = columnIndexOrThrow37;
                    String string4 = query$ar$ds$e1ca310e_0.isNull(i26) ? null : query$ar$ds$e1ca310e_0.getString(i26);
                    columnIndexOrThrow37 = i26;
                    int i27 = columnIndexOrThrow38;
                    int intToNetworkType$ar$edu = AppCompatTextHelper.Api26Impl.intToNetworkType$ar$edu(query$ar$ds$e1ca310e_0.getInt(i27));
                    columnIndexOrThrow38 = i27;
                    int i28 = columnIndexOrThrow39;
                    NetworkRequestCompat networkRequest$work_runtime_release = AppCompatTextHelper.Api26Impl.toNetworkRequest$work_runtime_release(query$ar$ds$e1ca310e_0.getBlob(i28));
                    columnIndexOrThrow39 = i28;
                    int i29 = columnIndexOrThrow40;
                    if (query$ar$ds$e1ca310e_0.getInt(i29) != 0) {
                        columnIndexOrThrow40 = i29;
                        i7 = columnIndexOrThrow41;
                        z7 = true;
                    } else {
                        columnIndexOrThrow40 = i29;
                        i7 = columnIndexOrThrow41;
                        z7 = false;
                    }
                    if (query$ar$ds$e1ca310e_0.getInt(i7) != 0) {
                        columnIndexOrThrow41 = i7;
                        i8 = columnIndexOrThrow42;
                        z8 = true;
                    } else {
                        columnIndexOrThrow41 = i7;
                        i8 = columnIndexOrThrow42;
                        z8 = false;
                    }
                    if (query$ar$ds$e1ca310e_0.getInt(i8) != 0) {
                        columnIndexOrThrow42 = i8;
                        i9 = columnIndexOrThrow43;
                        z9 = true;
                    } else {
                        columnIndexOrThrow42 = i8;
                        i9 = columnIndexOrThrow43;
                        z9 = false;
                    }
                    if (query$ar$ds$e1ca310e_0.getInt(i9) != 0) {
                        columnIndexOrThrow43 = i9;
                        i10 = columnIndexOrThrow44;
                        z10 = true;
                    } else {
                        columnIndexOrThrow43 = i9;
                        i10 = columnIndexOrThrow44;
                        z10 = false;
                    }
                    long j9 = query$ar$ds$e1ca310e_0.getLong(i10);
                    columnIndexOrThrow44 = i10;
                    int i30 = columnIndexOrThrow45;
                    long j10 = query$ar$ds$e1ca310e_0.getLong(i30);
                    columnIndexOrThrow45 = i30;
                    int i31 = columnIndexOrThrow46;
                    columnIndexOrThrow46 = i31;
                    int i32 = columnIndexOrThrow5;
                    arrayList.add(new WorkSpec(string, intToState$ar$edu, string2, string3, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(networkRequest$work_runtime_release, intToNetworkType$ar$edu, z7, z8, z9, z10, j9, j10, AppCompatTextHelper.Api26Impl.byteArrayToSetOfTriggers(query$ar$ds$e1ca310e_0.getBlob(i31))), i12, intToBackoffPolicy$ar$edu, j4, j5, j6, j7, z6, intToOutOfQuotaPolicy$ar$edu, i18, i20, j8, i23, i25, string4));
                    columnIndexOrThrow28 = i13;
                    columnIndexOrThrow6 = i15;
                    columnIndexOrThrow29 = i14;
                    arrayList2 = arrayList;
                    columnIndexOrThrow5 = i32;
                }
                query$ar$ds$e1ca310e_0.close();
                roomSQLiteQuery.release();
                markScheduled$ar$class_merging$ar$class_merging$ar$class_merging(workSpecDao, configuration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging, arrayList);
                int i33 = configuration.maxSchedulerLimit;
                RoomSQLiteQuery acquire2 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))", 1);
                acquire2.bindLong(1, 20L);
                ((WorkSpecDao_Impl) workSpecDao).__db.assertNotSuspendingTransaction();
                Cursor query$ar$ds$e1ca310e_02 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(((WorkSpecDao_Impl) workSpecDao).__db, acquire2);
                try {
                    columnIndexOrThrow14 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "id");
                    columnIndexOrThrow15 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "state");
                    columnIndexOrThrow16 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "worker_class_name");
                    columnIndexOrThrow17 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "input_merger_class_name");
                    columnIndexOrThrow18 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "input");
                    columnIndexOrThrow19 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "output");
                    columnIndexOrThrow20 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "initial_delay");
                    columnIndexOrThrow21 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "interval_duration");
                    columnIndexOrThrow22 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "flex_duration");
                    columnIndexOrThrow23 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "run_attempt_count");
                    columnIndexOrThrow24 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "backoff_policy");
                    columnIndexOrThrow25 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "backoff_delay_duration");
                    columnIndexOrThrow26 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "last_enqueue_time");
                    columnIndexOrThrow27 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "minimum_retention_duration");
                    roomSQLiteQuery2 = acquire2;
                } catch (Throwable th2) {
                    th = th2;
                    roomSQLiteQuery2 = acquire2;
                }
                try {
                    int columnIndexOrThrow47 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "schedule_requested_at");
                    int columnIndexOrThrow48 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "run_in_foreground");
                    int columnIndexOrThrow49 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "out_of_quota_policy");
                    int columnIndexOrThrow50 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "period_count");
                    int columnIndexOrThrow51 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "generation");
                    int columnIndexOrThrow52 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "next_schedule_time_override");
                    int columnIndexOrThrow53 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "next_schedule_time_override_generation");
                    int columnIndexOrThrow54 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "stop_reason");
                    int columnIndexOrThrow55 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "trace_tag");
                    int columnIndexOrThrow56 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "required_network_type");
                    int columnIndexOrThrow57 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "required_network_request");
                    int columnIndexOrThrow58 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "requires_charging");
                    int columnIndexOrThrow59 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "requires_device_idle");
                    int columnIndexOrThrow60 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "requires_battery_not_low");
                    int columnIndexOrThrow61 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "requires_storage_not_low");
                    int columnIndexOrThrow62 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "trigger_content_update_delay");
                    int columnIndexOrThrow63 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "trigger_max_content_delay");
                    int columnIndexOrThrow64 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_02, "content_uri_triggers");
                    int i34 = columnIndexOrThrow27;
                    ArrayList arrayList3 = new ArrayList(query$ar$ds$e1ca310e_02.getCount());
                    while (query$ar$ds$e1ca310e_02.moveToNext()) {
                        String string5 = query$ar$ds$e1ca310e_02.getString(columnIndexOrThrow14);
                        int intToState$ar$edu2 = AppCompatTextHelper.Api26Impl.intToState$ar$edu(query$ar$ds$e1ca310e_02.getInt(columnIndexOrThrow15));
                        String string6 = query$ar$ds$e1ca310e_02.getString(columnIndexOrThrow16);
                        String string7 = query$ar$ds$e1ca310e_02.getString(columnIndexOrThrow17);
                        Data fromByteArray3 = Data.fromByteArray(query$ar$ds$e1ca310e_02.getBlob(columnIndexOrThrow18));
                        Data fromByteArray4 = Data.fromByteArray(query$ar$ds$e1ca310e_02.getBlob(columnIndexOrThrow19));
                        long j11 = query$ar$ds$e1ca310e_02.getLong(columnIndexOrThrow20);
                        long j12 = query$ar$ds$e1ca310e_02.getLong(columnIndexOrThrow21);
                        long j13 = query$ar$ds$e1ca310e_02.getLong(columnIndexOrThrow22);
                        int i35 = query$ar$ds$e1ca310e_02.getInt(columnIndexOrThrow23);
                        int intToBackoffPolicy$ar$edu2 = AppCompatTextHelper.Api26Impl.intToBackoffPolicy$ar$edu(query$ar$ds$e1ca310e_02.getInt(columnIndexOrThrow24));
                        long j14 = query$ar$ds$e1ca310e_02.getLong(columnIndexOrThrow25);
                        long j15 = query$ar$ds$e1ca310e_02.getLong(columnIndexOrThrow26);
                        int i36 = i34;
                        long j16 = query$ar$ds$e1ca310e_02.getLong(i36);
                        i34 = i36;
                        int i37 = columnIndexOrThrow47;
                        long j17 = query$ar$ds$e1ca310e_02.getLong(i37);
                        columnIndexOrThrow47 = i37;
                        int i38 = columnIndexOrThrow48;
                        if (query$ar$ds$e1ca310e_02.getInt(i38) != 0) {
                            columnIndexOrThrow48 = i38;
                            i = columnIndexOrThrow49;
                            z = true;
                        } else {
                            columnIndexOrThrow48 = i38;
                            i = columnIndexOrThrow49;
                            z = false;
                        }
                        int intToOutOfQuotaPolicy$ar$edu2 = AppCompatTextHelper.Api26Impl.intToOutOfQuotaPolicy$ar$edu(query$ar$ds$e1ca310e_02.getInt(i));
                        columnIndexOrThrow49 = i;
                        int i39 = columnIndexOrThrow50;
                        int i40 = query$ar$ds$e1ca310e_02.getInt(i39);
                        columnIndexOrThrow50 = i39;
                        int i41 = columnIndexOrThrow51;
                        int i42 = query$ar$ds$e1ca310e_02.getInt(i41);
                        columnIndexOrThrow51 = i41;
                        int i43 = columnIndexOrThrow52;
                        long j18 = query$ar$ds$e1ca310e_02.getLong(i43);
                        columnIndexOrThrow52 = i43;
                        int i44 = columnIndexOrThrow53;
                        int i45 = query$ar$ds$e1ca310e_02.getInt(i44);
                        columnIndexOrThrow53 = i44;
                        int i46 = columnIndexOrThrow54;
                        int i47 = query$ar$ds$e1ca310e_02.getInt(i46);
                        columnIndexOrThrow54 = i46;
                        int i48 = columnIndexOrThrow55;
                        String string8 = query$ar$ds$e1ca310e_02.isNull(i48) ? null : query$ar$ds$e1ca310e_02.getString(i48);
                        columnIndexOrThrow55 = i48;
                        int i49 = columnIndexOrThrow56;
                        int intToNetworkType$ar$edu2 = AppCompatTextHelper.Api26Impl.intToNetworkType$ar$edu(query$ar$ds$e1ca310e_02.getInt(i49));
                        columnIndexOrThrow56 = i49;
                        int i50 = columnIndexOrThrow57;
                        NetworkRequestCompat networkRequest$work_runtime_release2 = AppCompatTextHelper.Api26Impl.toNetworkRequest$work_runtime_release(query$ar$ds$e1ca310e_02.getBlob(i50));
                        columnIndexOrThrow57 = i50;
                        int i51 = columnIndexOrThrow58;
                        if (query$ar$ds$e1ca310e_02.getInt(i51) != 0) {
                            columnIndexOrThrow58 = i51;
                            i2 = columnIndexOrThrow59;
                            z2 = true;
                        } else {
                            columnIndexOrThrow58 = i51;
                            i2 = columnIndexOrThrow59;
                            z2 = false;
                        }
                        if (query$ar$ds$e1ca310e_02.getInt(i2) != 0) {
                            columnIndexOrThrow59 = i2;
                            i3 = columnIndexOrThrow60;
                            z3 = true;
                        } else {
                            columnIndexOrThrow59 = i2;
                            i3 = columnIndexOrThrow60;
                            z3 = false;
                        }
                        if (query$ar$ds$e1ca310e_02.getInt(i3) != 0) {
                            columnIndexOrThrow60 = i3;
                            i4 = columnIndexOrThrow61;
                            z4 = true;
                        } else {
                            columnIndexOrThrow60 = i3;
                            i4 = columnIndexOrThrow61;
                            z4 = false;
                        }
                        if (query$ar$ds$e1ca310e_02.getInt(i4) != 0) {
                            columnIndexOrThrow61 = i4;
                            i5 = columnIndexOrThrow62;
                            z5 = true;
                        } else {
                            columnIndexOrThrow61 = i4;
                            i5 = columnIndexOrThrow62;
                            z5 = false;
                        }
                        long j19 = query$ar$ds$e1ca310e_02.getLong(i5);
                        columnIndexOrThrow62 = i5;
                        int i52 = columnIndexOrThrow63;
                        long j20 = query$ar$ds$e1ca310e_02.getLong(i52);
                        columnIndexOrThrow63 = i52;
                        int i53 = columnIndexOrThrow64;
                        columnIndexOrThrow64 = i53;
                        arrayList3.add(new WorkSpec(string5, intToState$ar$edu2, string6, string7, fromByteArray3, fromByteArray4, j11, j12, j13, new Constraints(networkRequest$work_runtime_release2, intToNetworkType$ar$edu2, z2, z3, z4, z5, j19, j20, AppCompatTextHelper.Api26Impl.byteArrayToSetOfTriggers(query$ar$ds$e1ca310e_02.getBlob(i53))), i35, intToBackoffPolicy$ar$edu2, j14, j15, j16, j17, z, intToOutOfQuotaPolicy$ar$edu2, i40, i42, j18, i45, i47, string8));
                    }
                    query$ar$ds$e1ca310e_02.close();
                    roomSQLiteQuery2.release();
                    markScheduled$ar$class_merging$ar$class_merging$ar$class_merging(workSpecDao, configuration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging, arrayList3);
                    arrayList3.addAll(arrayList);
                    List allEligibleWorkSpecsForScheduling$ar$ds = workSpecDao.getAllEligibleWorkSpecsForScheduling$ar$ds();
                    workDatabase.setTransactionSuccessful();
                    workDatabase.endTransaction();
                    if (arrayList3.size() > 0) {
                        WorkSpec[] workSpecArr = (WorkSpec[]) arrayList3.toArray(new WorkSpec[arrayList3.size()]);
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            Scheduler scheduler = (Scheduler) it.next();
                            if (scheduler.hasLimitedSchedulingSlots()) {
                                scheduler.schedule(workSpecArr);
                            }
                        }
                    }
                    if (allEligibleWorkSpecsForScheduling$ar$ds.size() > 0) {
                        WorkSpec[] workSpecArr2 = (WorkSpec[]) allEligibleWorkSpecsForScheduling$ar$ds.toArray(new WorkSpec[allEligibleWorkSpecsForScheduling$ar$ds.size()]);
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            Scheduler scheduler2 = (Scheduler) it2.next();
                            if (!scheduler2.hasLimitedSchedulingSlots()) {
                                scheduler2.schedule(workSpecArr2);
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query$ar$ds$e1ca310e_02.close();
                    roomSQLiteQuery2.release();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                query$ar$ds$e1ca310e_0.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th5) {
            workDatabase.endTransaction();
            throw th5;
        }
    }
}

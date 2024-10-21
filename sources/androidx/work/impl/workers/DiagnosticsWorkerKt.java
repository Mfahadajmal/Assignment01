package androidx.work.impl.workers;

import android.database.Cursor;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.core.widget.NestedScrollView;
import androidx.room.RoomSQLiteQuery;
import androidx.work.Logger;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkNameDao_Impl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkTagDao;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DiagnosticsWorkerKt {
    public static final /* synthetic */ int DiagnosticsWorkerKt$ar$NoOp = 0;
    private static final String TAG = Logger.tagWithPrefix("DiagnosticsWrkr");

    public static final void workSpecRows$ar$ds(WorkNameDao workNameDao, WorkTagDao workTagDao, SystemIdInfoDao systemIdInfoDao, List list) {
        Integer num;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WorkSpec workSpec = (WorkSpec) it.next();
            SystemIdInfo $default$getSystemIdInfo = SystemJobService.Api28Impl.$default$getSystemIdInfo(systemIdInfoDao, AppCompatTextHelper.Api24Impl.generationalId(workSpec));
            if ($default$getSystemIdInfo != null) {
                num = Integer.valueOf($default$getSystemIdInfo.systemId);
            } else {
                num = null;
            }
            String str = workSpec.id;
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT name FROM workname WHERE work_spec_id=?", 1);
            acquire.bindString(1, str);
            WorkNameDao_Impl workNameDao_Impl = (WorkNameDao_Impl) workNameDao;
            workNameDao_Impl.__db.assertNotSuspendingTransaction();
            Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(workNameDao_Impl.__db, acquire);
            try {
                ArrayList arrayList = new ArrayList(query$ar$ds$e1ca310e_0.getCount());
                while (query$ar$ds$e1ca310e_0.moveToNext()) {
                    arrayList.add(query$ar$ds$e1ca310e_0.getString(0));
                }
                query$ar$ds$e1ca310e_0.close();
                acquire.release();
                String joinToString$default$ar$ds = OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(arrayList, ",", null, null, null, 62);
                String joinToString$default$ar$ds2 = OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(workTagDao.getTagsForWorkSpecId(workSpec.id), ",", null, null, null, 62);
                StringBuilder sb = new StringBuilder("\n");
                sb.append(workSpec.id);
                sb.append("\t ");
                sb.append(workSpec.workerClassName);
                sb.append("\t ");
                sb.append(num);
                sb.append("\t ");
                int i = workSpec.state$ar$edu;
                String stringGeneratedffb196af7127d286 = AppCompatReceiveContentHelper$OnDropApi24Impl.toStringGeneratedffb196af7127d286(i);
                if (i != 0) {
                    sb.append(stringGeneratedffb196af7127d286);
                    sb.append("\t ");
                    sb.append(joinToString$default$ar$ds);
                    sb.append("\t ");
                    sb.append(joinToString$default$ar$ds2);
                    sb.append('\t');
                } else {
                    throw null;
                }
            } catch (Throwable th) {
                query$ar$ds$e1ca310e_0.close();
                acquire.release();
                throw th;
            }
        }
    }
}

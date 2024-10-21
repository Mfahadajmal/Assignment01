package androidx.room.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ReportFragment;
import com.google.android.accessibility.selecttospeak.popup.S2SPopupParsedIntentKt$parseIntent$text$1;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.GeneratorSequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TableInfo {
    public static final Lifecycle.Event.Companion Companion$ar$class_merging$7039af36_0 = new Lifecycle.Event.Companion();
    public final Map columns;
    public final Set foreignKeys;
    public final Set indices;
    public final String name;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Column {
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String str, String str2, boolean z, int i, String str3, int i2) {
            int i3;
            this.name = str;
            this.type = str2;
            this.notNull = z;
            this.primaryKeyPosition = i;
            this.defaultValue = str3;
            this.createdFrom = i2;
            String upperCase = str2.toUpperCase(Locale.ROOT);
            upperCase.getClass();
            if (OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "INT")) {
                i3 = 3;
            } else {
                int i4 = 2;
                if (!OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "CHAR") && !OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "CLOB") && !OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "TEXT")) {
                    if (OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "BLOB")) {
                        i3 = 5;
                    } else {
                        i4 = 4;
                        if (!OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "REAL") && !OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "FLOA") && !OnDeviceStainRemovalLogEvent.contains$default$ar$ds(upperCase, "DOUB")) {
                            i3 = 1;
                        }
                    }
                }
                i3 = i4;
            }
            this.affinity = i3;
        }

        public final boolean equals(Object obj) {
            String str;
            String str2;
            String str3;
            if (this == obj) {
                return true;
            }
            if (obj instanceof Column) {
                Column column = (Column) obj;
                column.getClass();
                if (this.primaryKeyPosition == column.primaryKeyPosition && Intrinsics.areEqual(this.name, column.name) && this.notNull == column.notNull && ((this.createdFrom != 1 || column.createdFrom != 2 || (str3 = this.defaultValue) == null || ReportFragment.LifecycleCallbacks.Companion.defaultValueEqualsCommon(str3, column.defaultValue)) && ((this.createdFrom != 2 || column.createdFrom != 1 || (str2 = column.defaultValue) == null || ReportFragment.LifecycleCallbacks.Companion.defaultValueEqualsCommon(str2, this.defaultValue)) && ((this.createdFrom != column.createdFrom || ((str = this.defaultValue) == null ? column.defaultValue == null : ReportFragment.LifecycleCallbacks.Companion.defaultValueEqualsCommon(str, column.defaultValue))) && this.affinity == column.affinity)))) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            int i;
            int hashCode = this.name.hashCode() * 31;
            if (true != this.notNull) {
                i = 1237;
            } else {
                i = 1231;
            }
            return ((((hashCode + this.affinity) * 31) + i) * 31) + this.primaryKeyPosition;
        }

        public final String toString() {
            String joinToString$default$ar$ds$9e9f5bd_0;
            StringBuilder sb = new StringBuilder("\n            |Column {\n            |   name = '");
            sb.append(this.name);
            sb.append("',\n            |   type = '");
            sb.append(this.type);
            sb.append("',\n            |   affinity = '");
            sb.append(this.affinity);
            sb.append("',\n            |   notNull = '");
            sb.append(this.notNull);
            sb.append("',\n            |   primaryKeyPosition = '");
            sb.append(this.primaryKeyPosition);
            sb.append("',\n            |   defaultValue = '");
            String str = this.defaultValue;
            if (str == null) {
                str = "undefined";
            }
            sb.append(str);
            sb.append("'\n            |}\n        ");
            joinToString$default$ar$ds$9e9f5bd_0 = OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(OnDeviceStainRemovalLogEvent.trimMargin$default$ar$ds(sb.toString())), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
            return joinToString$default$ar$ds$9e9f5bd_0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ForeignKey {
        public final List columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String str, String str2, String str3, List list, List list2) {
            list.getClass();
            list2.getClass();
            this.referenceTable = str;
            this.onDelete = str2;
            this.onUpdate = str3;
            this.columnNames = list;
            this.referenceColumnNames = list2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ForeignKey) {
                ForeignKey foreignKey = (ForeignKey) obj;
                if (Intrinsics.areEqual(this.referenceTable, foreignKey.referenceTable) && Intrinsics.areEqual(this.onDelete, foreignKey.onDelete) && Intrinsics.areEqual(this.onUpdate, foreignKey.onUpdate) && Intrinsics.areEqual(this.columnNames, foreignKey.columnNames)) {
                    return Intrinsics.areEqual(this.referenceColumnNames, foreignKey.referenceColumnNames);
                }
            }
            return false;
        }

        public final int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public final String toString() {
            String joinToString$default$ar$ds$9e9f5bd_0;
            StringBuilder sb = new StringBuilder("\n            |ForeignKey {\n            |   referenceTable = '");
            sb.append(this.referenceTable);
            sb.append("',\n            |   onDelete = '");
            sb.append(this.onDelete);
            sb.append("',\n            |   onUpdate = '");
            sb.append(this.onUpdate);
            sb.append("',\n            |   columnNames = {");
            ReportFragment.LifecycleCallbacks.Companion.joinToStringMiddleWithIndent(OnDeviceLanguageIdentificationLogEvent.sorted(this.columnNames));
            sb.append(Unit.INSTANCE);
            sb.append("\n            |   referenceColumnNames = {");
            ReportFragment.LifecycleCallbacks.Companion.joinToStringEndWithIndent(OnDeviceLanguageIdentificationLogEvent.sorted(this.referenceColumnNames));
            sb.append(Unit.INSTANCE);
            sb.append("\n            |}\n        ");
            joinToString$default$ar$ds$9e9f5bd_0 = OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(OnDeviceStainRemovalLogEvent.trimMargin$default$ar$ds(sb.toString())), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
            return joinToString$default$ar$ds$9e9f5bd_0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Index {
        public final List columns;
        public final String name;
        public List orders;
        public final boolean unique;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0, types: [java.util.List, java.util.Collection, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.util.ArrayList] */
        public Index(String str, boolean z, List list, List list2) {
            list.getClass();
            list2.getClass();
            this.name = str;
            this.unique = z;
            this.columns = list;
            this.orders = list2;
            if (list2.isEmpty()) {
                int size = list.size();
                list2 = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    list2.add("ASC");
                }
            }
            this.orders = list2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Index) {
                Index index = (Index) obj;
                if (this.unique == index.unique && Intrinsics.areEqual(this.columns, index.columns) && Intrinsics.areEqual(this.orders, index.orders)) {
                    if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(this.name, "index_")) {
                        return OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(index.name, "index_");
                    }
                    return Intrinsics.areEqual(this.name, index.name);
                }
            }
            return false;
        }

        public final int hashCode() {
            int hashCode;
            if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(this.name, "index_")) {
                hashCode = -1184239155;
            } else {
                hashCode = this.name.hashCode();
            }
            return (((((hashCode * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode()) * 31) + this.orders.hashCode();
        }

        public final String toString() {
            String joinToString$default$ar$ds$9e9f5bd_0;
            StringBuilder sb = new StringBuilder("\n            |Index {\n            |   name = '");
            sb.append(this.name);
            sb.append("',\n            |   unique = '");
            sb.append(this.unique);
            sb.append("',\n            |   columns = {");
            ReportFragment.LifecycleCallbacks.Companion.joinToStringMiddleWithIndent(this.columns);
            sb.append(Unit.INSTANCE);
            sb.append("\n            |   orders = {");
            ReportFragment.LifecycleCallbacks.Companion.joinToStringEndWithIndent(this.orders);
            sb.append(Unit.INSTANCE);
            sb.append("\n            |}\n        ");
            joinToString$default$ar$ds$9e9f5bd_0 = OnDeviceSmartReplyLogEvent.joinToString$default$ar$ds$9e9f5bd_0(new GeneratorSequence(OnDeviceStainRemovalLogEvent.lineSequence(OnDeviceStainRemovalLogEvent.trimMargin$default$ar$ds(sb.toString())), (Function1) new S2SPopupParsedIntentKt$parseIntent$text$1(11, (byte[]) null), 3), "\n", null, 62);
            return joinToString$default$ar$ds$9e9f5bd_0;
        }
    }

    public TableInfo(String str, Map map, Set set, Set set2) {
        set.getClass();
        this.name = str;
        this.columns = map;
        this.foreignKeys = set;
        this.indices = set2;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02f6 A[Catch: all -> 0x0323, TryCatch #0 {all -> 0x0323, all -> 0x0306, blocks: (B:44:0x01ac, B:49:0x01c5, B:50:0x01ca, B:52:0x01d0, B:59:0x01dc, B:62:0x01ed, B:98:0x02e2, B:100:0x02f6, B:104:0x02e6, B:107:0x0307, B:108:0x030a, B:112:0x030f, B:64:0x01f7, B:70:0x021c, B:71:0x0228, B:73:0x022e, B:80:0x0239, B:83:0x0251, B:87:0x026d, B:88:0x028e, B:90:0x0294, B:92:0x02a4, B:93:0x02c3, B:95:0x02c9, B:97:0x02d9), top: B:43:0x01ac }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02f5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.room.util.TableInfo read(androidx.sqlite.db.SupportSQLiteDatabase r31, java.lang.String r32) {
        /*
            Method dump skipped, instructions count: 826
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.util.TableInfo.read(androidx.sqlite.db.SupportSQLiteDatabase, java.lang.String):androidx.room.util.TableInfo");
    }

    public final boolean equals(Object obj) {
        return ReportFragment.LifecycleCallbacks.Companion.equalsCommon(this, obj);
    }

    public final int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.foreignKeys.hashCode();
    }

    public final String toString() {
        Collection collection;
        StringBuilder sb = new StringBuilder("\n            |TableInfo {\n            |    name = '");
        sb.append(this.name);
        sb.append("',\n            |    columns = {");
        sb.append(ReportFragment.LifecycleCallbacks.Companion.formatString(OnDeviceLanguageIdentificationLogEvent.sortedWith(this.columns.values(), new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(3))));
        sb.append("\n            |    foreignKeys = {");
        sb.append(ReportFragment.LifecycleCallbacks.Companion.formatString(this.foreignKeys));
        sb.append("\n            |    indices = {");
        Set set = this.indices;
        if (set != null) {
            collection = OnDeviceLanguageIdentificationLogEvent.sortedWith(set, new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(4));
        } else {
            collection = EmptyList.INSTANCE;
        }
        sb.append(ReportFragment.LifecycleCallbacks.Companion.formatString(collection));
        sb.append("\n            |}\n        ");
        return OnDeviceStainRemovalLogEvent.trimMargin$default$ar$ds(sb.toString());
    }
}

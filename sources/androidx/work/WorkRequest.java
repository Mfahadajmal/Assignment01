package androidx.work;

import android.support.v7.widget.AppCompatSpinner;
import androidx.work.impl.model.WorkSpec;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public class WorkRequest {
    public static final AppCompatSpinner.Api23Impl Companion$ar$class_merging$867ad24e_0$ar$class_merging = new AppCompatSpinner.Api23Impl(null);
    private final UUID id;
    public final Set tags;
    public final WorkSpec workSpec;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public UUID id;
        public final Set tags;
        public WorkSpec workSpec;
        private final Class workerClass;

        public Builder(Class cls, byte[] bArr) {
            this(cls);
        }

        public final /* synthetic */ WorkRequest buildInternal$work_runtime_release() {
            return new OneTimeWorkRequest(this);
        }

        public Builder(Class cls) {
            this.workerClass = cls;
            UUID randomUUID = UUID.randomUUID();
            randomUUID.getClass();
            this.id = randomUUID;
            String uuid = this.id.toString();
            uuid.getClass();
            String name = cls.getName();
            name.getClass();
            this.workSpec = new WorkSpec(uuid, 0, name, (String) null, (Data) null, (Data) null, 0L, 0L, 0L, (Constraints) null, 0, 0, 0L, 0L, 0L, 0L, false, 0, 0, 0L, 0, 0, (String) null, 16777210);
            String name2 = cls.getName();
            name2.getClass();
            LinkedHashSet linkedHashSet = new LinkedHashSet(OnDeviceLanguageIdentificationLogEvent.mapCapacity(1));
            linkedHashSet.add(new String[]{name2}[0]);
            this.tags = linkedHashSet;
        }
    }

    public WorkRequest(UUID uuid, WorkSpec workSpec, Set set) {
        uuid.getClass();
        workSpec.getClass();
        this.id = uuid;
        this.workSpec = workSpec;
        this.tags = set;
    }

    public final String getStringId() {
        String uuid = this.id.toString();
        uuid.getClass();
        return uuid;
    }
}

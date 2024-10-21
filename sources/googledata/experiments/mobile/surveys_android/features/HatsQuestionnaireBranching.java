package googledata.experiments.mobile.surveys_android.features;

import android.content.Context;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers$SupplierOfInstance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HatsQuestionnaireBranching implements Supplier {
    private static final HatsQuestionnaireBranching INSTANCE = new HatsQuestionnaireBranching();
    private final Supplier supplier = new Suppliers$SupplierOfInstance(new HatsQuestionnaireBranchingFlagsImpl());

    public static boolean enableQuestionnaireBranching(Context context) {
        return INSTANCE.get().enableQuestionnaireBranching(context);
    }

    public static boolean enableRatingQuestionnaireBranching(Context context) {
        return INSTANCE.get().enableRatingQuestionnaireBranching(context);
    }

    @Override // com.google.common.base.Supplier
    public final HatsQuestionnaireBranchingFlags get() {
        return (HatsQuestionnaireBranchingFlags) ((Suppliers$SupplierOfInstance) this.supplier).instance;
    }
}

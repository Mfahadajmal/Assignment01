package android.support.v4.app;

import android.content.res.Configuration;
import androidx.core.util.Consumer;
import com.google.android.libraries.phenotype.client.lockdown.FlagExemptionsReader;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FragmentActivity$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ Object FragmentActivity$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FragmentActivity$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            FlagExemptionsReader flagExemptionsReader = (FlagExemptionsReader) obj;
                            FragmentManager fragmentManager = (FragmentManager) this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0;
                            if (fragmentManager.isParentAdded()) {
                                fragmentManager.dispatchPictureInPictureModeChanged(flagExemptionsReader.doFlagLockdownRuntimeValidations, false);
                                return;
                            }
                            return;
                        }
                        FlagExemptionsReader flagExemptionsReader2 = (FlagExemptionsReader) obj;
                        FragmentManager fragmentManager2 = (FragmentManager) this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0;
                        if (fragmentManager2.isParentAdded()) {
                            fragmentManager2.dispatchMultiWindowModeChanged(flagExemptionsReader2.doFlagLockdownRuntimeValidations, false);
                            return;
                        }
                        return;
                    }
                    Integer num = (Integer) obj;
                    FragmentManager fragmentManager3 = (FragmentManager) this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0;
                    if (fragmentManager3.isParentAdded() && num.intValue() == 80) {
                        fragmentManager3.dispatchLowMemory(false);
                        return;
                    }
                    return;
                }
                Configuration configuration = (Configuration) obj;
                FragmentManager fragmentManager4 = (FragmentManager) this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0;
                if (fragmentManager4.isParentAdded()) {
                    fragmentManager4.dispatchConfigurationChanged(configuration, false);
                    return;
                }
                return;
            }
            ((FragmentActivity) this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0).mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
            return;
        }
        ((FragmentActivity) this.FragmentActivity$$ExternalSyntheticLambda2$ar$f$0).mFragments$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.noteStateNotSaved();
    }
}

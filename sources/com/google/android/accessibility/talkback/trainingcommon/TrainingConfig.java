package com.google.android.accessibility.talkback.trainingcommon;

import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TrainingConfig {
    private final ImmutableList buttons;
    private final boolean exitButtonOnlyShowOnLastPage;
    private final int name;
    private final ImmutableList pages;
    private final boolean prevButtonShownOnFirstPage;
    private final boolean supportNavigateUpArrow;
    public int totalPageNumber;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public ImmutableList buttons;
        private final int trainingName;
        private List pages = new ArrayList();
        private boolean isExitButtonOnlyShowOnLastPage = false;
        public boolean isPrevButtonShownOnFirstPage = false;
        private boolean isSupportNavigateUpArrow = false;

        public Builder(int i) {
            this.trainingName = i;
        }

        public final void addPage$ar$ds(PageConfig.Builder builder, boolean z, boolean z2, boolean z3) {
            if (!z) {
                builder.hasNavigationButtonBar = false;
            }
            if (!z2) {
                builder.hidePageNumber$ar$ds();
            }
            if (z3) {
                builder.setEndOfSection$ar$ds();
            }
            this.pages.add(builder.build());
        }

        public final Builder addPageEndOfSection(PageConfig.Builder builder) {
            addPage$ar$ds(builder, true, true, true);
            return this;
        }

        public final Builder addPageWithoutNumberAndNavigationBar(PageConfig.Builder builder) {
            addPage$ar$ds(builder, false, false, false);
            return this;
        }

        public final void addPages$ar$ds(PageConfig.Builder... builderArr) {
            for (PageConfig.Builder builder : builderArr) {
                this.pages.add(builder.build());
            }
        }

        public final TrainingConfig build() {
            return new TrainingConfig(this.trainingName, ImmutableList.copyOf((Collection) this.pages), this.buttons, this.isExitButtonOnlyShowOnLastPage, this.isPrevButtonShownOnFirstPage, this.isSupportNavigateUpArrow);
        }

        public final void setExitButtonOnlyShowOnLastPage$ar$ds() {
            this.isExitButtonOnlyShowOnLastPage = true;
        }

        public final void setPages$ar$ds(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.pages.add(((PageConfig.Builder) it.next()).build());
            }
        }

        public final void setSupportNavigateUpArrow$ar$ds() {
            this.isSupportNavigateUpArrow = true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum TrainingId {
        TRAINING_ID_ON_BOARDING_TALKBACK,
        TRAINING_ID_FIRST_RUN_AFTER_UPDATED_ON_BOARDING_TALKBACK,
        TRAINING_ID_ON_BOARDING_FOR_MULTIFINGER_GESTURES,
        TRAINING_ID_TUTORIAL_FOR_WATCH,
        TRAINING_ID_TUTORIAL_FOR_TV,
        TRAINING_ID_FIRST_RUN_TUTORIAL,
        TRAINING_ID_TUTORIAL,
        TRAINING_ID_TUTORIAL_PRACTICE_GESTURE,
        TRAINING_ID_TUTORIAL_PRACTICE_GESTURE_PRE_R,
        TRAINING_ID_TUTORIAL_SPELL_CHECK,
        TRAINING_ID_VOICE_COMMAND_HELP,
        TRAINING_ID_VOICE_COMMAND_HELP_FOR_WATCH
    }

    public TrainingConfig() {
        this.totalPageNumber = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TrainingConfig) {
            TrainingConfig trainingConfig = (TrainingConfig) obj;
            if (this.name == trainingConfig.getName() && ContextDataProvider.equalsImpl(this.pages, trainingConfig.getPages()) && ContextDataProvider.equalsImpl(this.buttons, trainingConfig.getButtons()) && this.exitButtonOnlyShowOnLastPage == trainingConfig.isExitButtonOnlyShowOnLastPage() && this.prevButtonShownOnFirstPage == trainingConfig.isPrevButtonShownOnFirstPage() && this.supportNavigateUpArrow == trainingConfig.isSupportNavigateUpArrow()) {
                return true;
            }
        }
        return false;
    }

    public final ImmutableList getButtons() {
        return this.buttons;
    }

    public final int getName() {
        return this.name;
    }

    public final ImmutableList getPages() {
        return this.pages;
    }

    public final int hashCode() {
        int i;
        int i2;
        int hashCode = ((((this.name ^ 1000003) * 1000003) ^ this.pages.hashCode()) * 1000003) ^ this.buttons.hashCode();
        int i3 = 1237;
        if (true != this.exitButtonOnlyShowOnLastPage) {
            i = 1237;
        } else {
            i = 1231;
        }
        int i4 = ((hashCode * 1000003) ^ i) * 1000003;
        if (true != this.prevButtonShownOnFirstPage) {
            i2 = 1237;
        } else {
            i2 = 1231;
        }
        int i5 = (i4 ^ i2) * 1000003;
        if (true == this.supportNavigateUpArrow) {
            i3 = 1231;
        }
        return i5 ^ i3;
    }

    public final boolean isExitButtonOnlyShowOnLastPage() {
        return this.exitButtonOnlyShowOnLastPage;
    }

    public final boolean isPrevButtonShownOnFirstPage() {
        return this.prevButtonShownOnFirstPage;
    }

    public final boolean isSupportNavigateUpArrow() {
        return this.supportNavigateUpArrow;
    }

    public final String toString() {
        ImmutableList immutableList = this.buttons;
        return "TrainingConfig{name=" + this.name + ", pages=" + this.pages.toString() + ", buttons=" + immutableList.toString() + ", exitButtonOnlyShowOnLastPage=" + this.exitButtonOnlyShowOnLastPage + ", prevButtonShownOnFirstPage=" + this.prevButtonShownOnFirstPage + ", supportNavigateUpArrow=" + this.supportNavigateUpArrow + "}";
    }

    public TrainingConfig(int i, ImmutableList immutableList, ImmutableList immutableList2, boolean z, boolean z2, boolean z3) {
        this();
        this.name = i;
        if (immutableList != null) {
            this.pages = immutableList;
            if (immutableList2 != null) {
                this.buttons = immutableList2;
                this.exitButtonOnlyShowOnLastPage = z;
                this.prevButtonShownOnFirstPage = z2;
                this.supportNavigateUpArrow = z3;
                return;
            }
            throw new NullPointerException("Null buttons");
        }
        throw new NullPointerException("Null pages");
    }
}

package androidx.core.view.accessibility;

import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AccessibilityViewCommand {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MoveAtGranularityArguments extends CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MoveHtmlArguments extends CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MoveWindowArguments extends CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ScrollToPositionArguments extends CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SetProgressArguments extends CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SetSelectionArguments extends CommandArguments {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SetTextArguments extends CommandArguments {
    }

    boolean perform(View view, CommandArguments commandArguments);
}

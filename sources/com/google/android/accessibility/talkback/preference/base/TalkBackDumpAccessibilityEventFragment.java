package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TtsSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.AccessibilitySuiteSwitchPreference;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackDumpAccessibilityEventFragment extends TalkbackBaseFragment {
    private final List<EventDumperSwitchPreference> switchPreferences = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class EventDumperSwitchPreference extends AccessibilitySuiteSwitchPreference implements Preference.OnPreferenceChangeListener {
        private final int eventType;

        public EventDumperSwitchPreference(Context context, int i) {
            super(context);
            this.eventType = i;
            setOnPreferenceChangeListener(this);
            String eventTypeToString = AccessibilityEvent.eventTypeToString(i);
            SpannableString spannableString = new SpannableString(eventTypeToString);
            spannableString.setSpan(new TtsSpan.TextBuilder(eventTypeToString.replaceAll("_", " ")).build(), 0, eventTypeToString.length(), 0);
            setTitle(spannableString);
            setChecked((SpannableUtils$IdentifierSpan.getSharedPreferences(getContext()).getInt(getContext().getString(R.string.pref_dump_event_mask_key), 0) & i) != 0);
        }

        @Override // androidx.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object obj) {
            int i;
            boolean booleanValue = ((Boolean) obj).booleanValue();
            SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(getContext());
            int i2 = sharedPreferences.getInt(getContext().getString(R.string.pref_dump_event_mask_key), 0);
            if (booleanValue) {
                i = this.eventType | i2;
            } else {
                i = (~this.eventType) & i2;
            }
            sharedPreferences.edit().putInt(getContext().getString(R.string.pref_dump_event_mask_key), i).apply();
            return true;
        }
    }

    private void setDumpAllEvents(boolean z) {
        int i;
        Context context = getContext();
        SharedPreferences.Editor edit = SpannableUtils$IdentifierSpan.getSharedPreferences(context).edit();
        String string = context.getString(R.string.pref_dump_event_mask_key);
        if (true != z) {
            i = 0;
        } else {
            i = -1;
        }
        edit.putInt(string, i).apply();
        Iterator<EventDumperSwitchPreference> it = this.switchPreferences.iterator();
        while (it.hasNext()) {
            it.next().setChecked(z);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_activity_dump_a11y_event);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.dump_a11y_event_menu, menu);
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        Context context = getContext();
        SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.dump_events_preferences);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int[] allEventTypes = SpannableUtils$IdentifierSpan.getAllEventTypes();
        for (int i = 0; i < 25; i++) {
            EventDumperSwitchPreference eventDumperSwitchPreference = new EventDumperSwitchPreference(context, allEventTypes[i]);
            this.switchPreferences.add(eventDumperSwitchPreference);
            preferenceScreen.addPreference$ar$ds(eventDumperSwitchPreference);
        }
    }

    @Override // android.support.v4.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.disable_all) {
            setDumpAllEvents(false);
            return true;
        }
        if (itemId != R.id.enable_all) {
            return false;
        }
        setDumpAllEvents(true);
        return true;
    }
}

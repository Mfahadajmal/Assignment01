package com.google.android.accessibility.talkback.trainingcommon.content;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient;
import com.google.android.accessibility.utils.widget.NonScrollableListView;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextList extends PageContentConfig {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TextListAdapter extends BaseAdapter {
        private final Context context;
        private final List titleSummaryPairList = new ArrayList();

        public TextListAdapter(Context context, String[] strArr, String[] strArr2) {
            boolean z;
            this.context = context;
            if (strArr.length == strArr2.length) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z);
            for (int i = 0; i < strArr.length; i++) {
                this.titleSummaryPairList.add(new Pair(strArr[i], strArr2[i]));
            }
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return this.titleSummaryPairList.size();
        }

        @Override // android.widget.Adapter
        public final Pair getItem(int i) {
            return (Pair) this.titleSummaryPairList.get(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.training_list_item, viewGroup, false);
            ((TextView) inflate.findViewById(R.id.training_list_item_title)).setText((CharSequence) getItem(i).first);
            TextView textView = (TextView) inflate.findViewById(R.id.training_list_item_summary);
            String str = (String) getItem(i).second;
            if (textView != null && !TextUtils.isEmpty(str)) {
                textView.setText((CharSequence) getItem(i).second);
                textView.setVisibility(0);
            }
            return inflate;
        }
    }

    @Override // com.google.android.accessibility.talkback.trainingcommon.content.PageContentConfig
    public final View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, TrainingIpcClient.ServiceData serviceData) {
        View inflate = layoutInflater.inflate(R.layout.training_text_list, viewGroup, false);
        NonScrollableListView nonScrollableListView = (NonScrollableListView) inflate.findViewById(R.id.training_text_list);
        String[] stringArray = context.getResources().getStringArray(R.array.tutorial_scrolling_item_titles);
        String[] strArr = new String[stringArray.length];
        Arrays.fill(strArr, "");
        nonScrollableListView.setAdapter((ListAdapter) new TextListAdapter(context, stringArray, strArr));
        nonScrollableListView.setDividerHeight(context.getResources().getDimensionPixelSize(R.dimen.training_list_item_padding));
        return inflate;
    }
}

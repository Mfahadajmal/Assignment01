package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements MenuView.ItemView, AbsListView.SelectionBoundsAdjuster {
    private Drawable mBackground;
    private CheckBox mCheckBox;
    private LinearLayout mContent;
    public boolean mForceShowIcon;
    public ImageView mGroupDivider;
    public boolean mHasListDivider;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    public MenuItemImpl mItemData;
    public boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listMenuViewStyle);
    }

    private final void addContentView(View view) {
        addContentView(view, -1);
    }

    private final LayoutInflater getInflater() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getContext());
        }
        return this.mInflater;
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.mGroupDivider;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mGroupDivider.getLayoutParams();
            rect.top += this.mGroupDivider.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final MenuItemImpl getItemData() {
        return this.mItemData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.support.v7.view.menu.MenuView$ItemView, android.support.v7.view.menu.ListMenuItemView, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r4v3, types: [android.widget.CheckBox] */
    /* JADX WARN: Type inference failed for: r4v7, types: [android.widget.CompoundButton] */
    /* JADX WARN: Type inference failed for: r4v9, types: [android.widget.RadioButton] */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.widget.CheckBox] */
    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final void initialize$ar$ds(MenuItemImpl menuItemImpl) {
        int i;
        ?? r4;
        RadioButton radioButton;
        CheckBox checkBox;
        int i2;
        ImageView imageView;
        int i3;
        String sb;
        this.mItemData = menuItemImpl;
        int i4 = 0;
        if (true != menuItemImpl.isVisible()) {
            i = 8;
        } else {
            i = 0;
        }
        setVisibility(i);
        CharSequence titleForItemView = menuItemImpl.getTitleForItemView(this);
        if (titleForItemView != null) {
            this.mTitleView.setText(titleForItemView);
            if (this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        } else if (this.mTitleView.getVisibility() != 8) {
            this.mTitleView.setVisibility(8);
        }
        boolean isCheckable = menuItemImpl.isCheckable();
        if (isCheckable || this.mRadioButton != null || this.mCheckBox != null) {
            if (this.mItemData.isExclusiveCheckable()) {
                if (this.mRadioButton == null) {
                    RadioButton radioButton2 = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                    this.mRadioButton = radioButton2;
                    addContentView(radioButton2);
                }
                r4 = this.mRadioButton;
                ?? r5 = this.mCheckBox;
                radioButton = r5;
                checkBox = r5;
            } else {
                if (this.mCheckBox == null) {
                    CheckBox checkBox2 = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                    this.mCheckBox = checkBox2;
                    addContentView(checkBox2);
                }
                r4 = this.mCheckBox;
                radioButton = this.mRadioButton;
                checkBox = r4;
            }
            if (isCheckable) {
                r4.setChecked(this.mItemData.isChecked());
                if (r4.getVisibility() != 0) {
                    r4.setVisibility(0);
                }
                if (radioButton != null && radioButton.getVisibility() != 8) {
                    radioButton.setVisibility(8);
                }
            } else {
                if (checkBox != null) {
                    checkBox.setVisibility(8);
                }
                RadioButton radioButton3 = this.mRadioButton;
                if (radioButton3 != null) {
                    radioButton3.setVisibility(8);
                }
            }
        }
        boolean shouldShowShortcut = menuItemImpl.shouldShowShortcut();
        menuItemImpl.getShortcut();
        if (shouldShowShortcut && this.mItemData.shouldShowShortcut()) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        if (i2 == 0) {
            TextView textView = this.mShortcutView;
            MenuItemImpl menuItemImpl2 = this.mItemData;
            char shortcut = menuItemImpl2.getShortcut();
            if (shortcut == 0) {
                sb = "";
            } else {
                Resources resources = menuItemImpl2.mMenu.mContext.getResources();
                StringBuilder sb2 = new StringBuilder();
                if (ViewConfiguration.get(menuItemImpl2.mMenu.mContext).hasPermanentMenuKey()) {
                    sb2.append(resources.getString(R.string.abc_prepend_shortcut_label));
                }
                if (menuItemImpl2.mMenu.isQwertyMode()) {
                    i3 = menuItemImpl2.mShortcutAlphabeticModifiers;
                } else {
                    i3 = menuItemImpl2.mShortcutNumericModifiers;
                }
                MenuItemImpl.appendModifier(sb2, i3, 65536, resources.getString(R.string.abc_menu_meta_shortcut_label));
                MenuItemImpl.appendModifier(sb2, i3, 4096, resources.getString(R.string.abc_menu_ctrl_shortcut_label));
                MenuItemImpl.appendModifier(sb2, i3, 2, resources.getString(R.string.abc_menu_alt_shortcut_label));
                MenuItemImpl.appendModifier(sb2, i3, 1, resources.getString(R.string.abc_menu_shift_shortcut_label));
                MenuItemImpl.appendModifier(sb2, i3, 4, resources.getString(R.string.abc_menu_sym_shortcut_label));
                MenuItemImpl.appendModifier(sb2, i3, 8, resources.getString(R.string.abc_menu_function_shortcut_label));
                if (shortcut != '\b') {
                    if (shortcut != '\n') {
                        if (shortcut != ' ') {
                            sb2.append(shortcut);
                        } else {
                            sb2.append(resources.getString(R.string.abc_menu_space_shortcut_label));
                        }
                    } else {
                        sb2.append(resources.getString(R.string.abc_menu_enter_shortcut_label));
                    }
                } else {
                    sb2.append(resources.getString(R.string.abc_menu_delete_shortcut_label));
                }
                sb = sb2.toString();
            }
            textView.setText(sb);
        }
        if (this.mShortcutView.getVisibility() != i2) {
            this.mShortcutView.setVisibility(i2);
        }
        Drawable icon = menuItemImpl.getIcon();
        MenuBuilder menuBuilder = this.mItemData.mMenu;
        boolean z = this.mForceShowIcon;
        if ((z || this.mPreserveIconSpacing) && ((imageView = this.mIconView) != null || icon != null || this.mPreserveIconSpacing)) {
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.mIconView = imageView2;
                addContentView(imageView2, 0);
            }
            if (icon == null && !this.mPreserveIconSpacing) {
                this.mIconView.setVisibility(8);
            } else {
                ImageView imageView3 = this.mIconView;
                if (true != z) {
                    icon = null;
                }
                imageView3.setImageDrawable(icon);
                if (this.mIconView.getVisibility() != 0) {
                    this.mIconView.setVisibility(0);
                }
            }
        }
        setEnabled(menuItemImpl.isEnabled());
        boolean hasSubMenu = menuItemImpl.hasSubMenu();
        ImageView imageView4 = this.mSubMenuArrowView;
        if (imageView4 != null) {
            if (true != hasSubMenu) {
                i4 = 8;
            }
            imageView4.setVisibility(i4);
        }
        setContentDescription(menuItemImpl.mContentDescription);
    }

    @Override // android.view.View
    protected final void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.mBackground);
        TextView textView = (TextView) findViewById(R.id.title);
        this.mTitleView = textView;
        int i = this.mTextAppearance;
        if (i != -1) {
            textView.setTextAppearance(this.mTextAppearanceContext, i);
        }
        this.mShortcutView = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.mSubMenuArrowView = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.mSubMenuArrow);
        }
        this.mGroupDivider = (ImageView) findViewById(R.id.group_divider);
        this.mContent = (LinearLayout) findViewById(R.id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        if (this.mIconView != null && this.mPreserveIconSpacing) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final boolean prefersCondensedTitle() {
        return false;
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(getContext(), attributeSet, R$styleable.MenuView, i, 0);
        this.mBackground = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(5);
        this.mTextAppearance = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(1, -1);
        this.mPreserveIconSpacing = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getBoolean(7, false);
        this.mTextAppearanceContext = context;
        this.mSubMenuArrow = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(8);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{android.R.attr.divider}, R.attr.dropDownListViewStyle, 0);
        this.mHasListDivider = obtainStyledAttributes.hasValue(0);
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
        obtainStyledAttributes.recycle();
    }

    private final void addContentView(View view, int i) {
        LinearLayout linearLayout = this.mContent;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }
}

package android.support.v7.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.content.ContextCompat$Api24Impl;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.MenuItemCompat$Api26Impl;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SupportMenuInflater extends MenuInflater {
    static final Class[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE;
    static final Class[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE;
    final Object[] mActionProviderConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    final Context mContext;
    public Object mRealOwner;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class[] PARAM_TYPES = {MenuItem.class};
        private Method mMethod;
        private final Object mRealOwner;

        public InflatedOnMenuItemClickListener(Object obj, String str) {
            this.mRealOwner = obj;
            Class<?> cls = obj.getClass();
            try {
                this.mMethod = cls.getMethod(str, PARAM_TYPES);
            } catch (Exception e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.mMethod.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.mMethod.invoke(this.mRealOwner, menuItem)).booleanValue();
                }
                this.mMethod.invoke(this.mRealOwner, menuItem);
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MenuState {
        public int groupCategory;
        public int groupCheckable;
        public boolean groupEnabled;
        public int groupId;
        public int groupOrder;
        public boolean groupVisible;
        ContextCompat$Api24Impl itemActionProvider$ar$class_merging;
        public String itemActionProviderClassName;
        public String itemActionViewClassName;
        public int itemActionViewLayout;
        public boolean itemAdded;
        public int itemAlphabeticModifiers;
        public char itemAlphabeticShortcut;
        public int itemCategoryOrder;
        public int itemCheckable;
        public boolean itemChecked;
        public CharSequence itemContentDescription;
        public boolean itemEnabled;
        public int itemIconResId;
        public ColorStateList itemIconTintList = null;
        public PorterDuff.Mode itemIconTintMode = null;
        public int itemId;
        public String itemListenerMethodName;
        public int itemNumericModifiers;
        public char itemNumericShortcut;
        public int itemShowAsAction;
        public CharSequence itemTitle;
        public CharSequence itemTitleCondensed;
        public CharSequence itemTooltipText;
        public boolean itemVisible;
        public final Menu menu;

        public MenuState(Menu menu) {
            this.menu = menu;
            resetGroup();
        }

        public static final char getShortcut$ar$ds(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        public final SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu addSubMenu = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem(addSubMenu.getItem());
            return addSubMenu;
        }

        public final Object newInstance(String str, Class[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, SupportMenuInflater.this.mContext.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return constructor.newInstance(objArr);
            } catch (Exception e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: ".concat(String.valueOf(str)), e);
                return null;
            }
        }

        public final void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }

        public final void setItem(MenuItem menuItem) {
            boolean z;
            MenuItem enabled = menuItem.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled);
            boolean z2 = false;
            if (this.itemCheckable > 0) {
                z = true;
            } else {
                z = false;
            }
            enabled.setCheckable(z).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            int i = this.itemShowAsAction;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.itemListenerMethodName != null) {
                if (!SupportMenuInflater.this.mContext.isRestricted()) {
                    SupportMenuInflater supportMenuInflater = SupportMenuInflater.this;
                    if (supportMenuInflater.mRealOwner == null) {
                        supportMenuInflater.mRealOwner = supportMenuInflater.findRealOwner(supportMenuInflater.mContext);
                    }
                    menuItem.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(supportMenuInflater.mRealOwner, this.itemListenerMethodName));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            if (this.itemCheckable >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    MenuItemWrapperICS menuItemWrapperICS = (MenuItemWrapperICS) menuItem;
                    try {
                        if (menuItemWrapperICS.mSetExclusiveCheckableMethod == null) {
                            menuItemWrapperICS.mSetExclusiveCheckableMethod = menuItemWrapperICS.mWrappedObject.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                        }
                        menuItemWrapperICS.mSetExclusiveCheckableMethod.invoke(menuItemWrapperICS.mWrappedObject, true);
                    } catch (Exception e) {
                        Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
                    }
                }
            }
            String str = this.itemActionViewClassName;
            if (str != null) {
                menuItem.setActionView((View) newInstance(str, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, SupportMenuInflater.this.mActionViewConstructorArguments));
                z2 = true;
            }
            int i2 = this.itemActionViewLayout;
            if (i2 > 0) {
                if (!z2) {
                    menuItem.setActionView(i2);
                } else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            ContextCompat$Api24Impl contextCompat$Api24Impl = this.itemActionProvider$ar$class_merging;
            if (contextCompat$Api24Impl != null) {
                if (menuItem instanceof SupportMenuItem) {
                    ((SupportMenuItem) menuItem).setSupportActionProvider$ar$ds$ar$class_merging(contextCompat$Api24Impl);
                } else {
                    Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
                }
            }
            CharSequence charSequence = this.itemContentDescription;
            boolean z3 = menuItem instanceof SupportMenuItem;
            if (z3) {
                ((SupportMenuItem) menuItem).setContentDescription$ar$ds(charSequence);
            } else {
                MenuItemCompat$Api26Impl.setContentDescription(menuItem, charSequence);
            }
            CharSequence charSequence2 = this.itemTooltipText;
            if (z3) {
                ((SupportMenuItem) menuItem).setTooltipText$ar$ds(charSequence2);
            } else {
                MenuItemCompat$Api26Impl.setTooltipText(menuItem, charSequence2);
            }
            char c = this.itemAlphabeticShortcut;
            int i3 = this.itemAlphabeticModifiers;
            if (z3) {
                ((SupportMenuItem) menuItem).setAlphabeticShortcut(c, i3);
            } else {
                MenuItemCompat$Api26Impl.setAlphabeticShortcut(menuItem, c, i3);
            }
            char c2 = this.itemNumericShortcut;
            int i4 = this.itemNumericModifiers;
            if (z3) {
                ((SupportMenuItem) menuItem).setNumericShortcut(c2, i4);
            } else {
                MenuItemCompat$Api26Impl.setNumericShortcut(menuItem, c2, i4);
            }
            PorterDuff.Mode mode = this.itemIconTintMode;
            if (mode != null) {
                if (z3) {
                    ((SupportMenuItem) menuItem).setIconTintMode(mode);
                } else {
                    MenuItemCompat$Api26Impl.setIconTintMode(menuItem, mode);
                }
            }
            ColorStateList colorStateList = this.itemIconTintList;
            if (colorStateList != null) {
                if (z3) {
                    ((SupportMenuItem) menuItem).setIconTintList(colorStateList);
                } else {
                    MenuItemCompat$Api26Impl.setIconTintList(menuItem, colorStateList);
                }
            }
        }
    }

    static {
        Class[] clsArr = {Context.class};
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = clsArr;
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = clsArr;
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.mContext = context;
        Object[] objArr = {context};
        this.mActionViewConstructorArguments = objArr;
        this.mActionProviderConstructorArguments = objArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void parseMenu(org.xmlpull.v1.XmlPullParser r17, android.util.AttributeSet r18, android.view.Menu r19) {
        /*
            Method dump skipped, instructions count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.SupportMenuInflater.parseMenu(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    public final Object findRealOwner(Object obj) {
        if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
            return findRealOwner(((ContextWrapper) obj).getBaseContext());
        }
        return obj;
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i, Menu menu) {
        if (menu instanceof SupportMenu) {
            XmlResourceParser xmlResourceParser = null;
            boolean z = false;
            try {
                try {
                    xmlResourceParser = this.mContext.getResources().getLayout(i);
                    AttributeSet asAttributeSet = Xml.asAttributeSet(xmlResourceParser);
                    if (menu instanceof MenuBuilder) {
                        MenuBuilder menuBuilder = (MenuBuilder) menu;
                        if (!menuBuilder.mPreventDispatchingItemsChanged) {
                            menuBuilder.stopDispatchingItemsChanged();
                            z = true;
                        }
                    }
                    parseMenu(xmlResourceParser, asAttributeSet, menu);
                    if (xmlResourceParser != null) {
                        return;
                    } else {
                        return;
                    }
                } catch (IOException e) {
                    throw new InflateException("Error inflating menu XML", e);
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } finally {
                if (z) {
                    ((MenuBuilder) menu).startDispatchingItemsChanged();
                }
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            }
        }
        super.inflate(i, menu);
    }
}

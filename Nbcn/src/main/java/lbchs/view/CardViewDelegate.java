/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package lbchs.view;

import android.graphics.drawable.Drawable;

interface CardViewDelegate {
    void setBackgroundDrawable(Drawable var1);

    Drawable getBackground();

    boolean getUseCompatPadding();

    boolean getPreventCornerOverlap();

    float getRadius();

    void setShadowPadding(int var1, int var2, int var3, int var4);
}


/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.graphics.drawable.Drawable
 *  java.lang.Object
 */
package jiesheng;

import android.graphics.drawable.Drawable;

interface CardViewDelegate {
    public void setBackgroundDrawable(Drawable var1);

    public Drawable getBackground();

    public boolean getUseCompatPadding();

    public boolean getPreventCornerOverlap();

    public float getRadius();

    public void setShadowPadding(int var1, int var2, int var3, int var4);
}


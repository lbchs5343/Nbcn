/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 */
package jiesheng;

import android.content.Context;

interface CardViewImpl {
    public void initialize(CardViewDelegate var1, Context var2, int var3, float var4, float var5, float var6);

    public void setRadius(CardViewDelegate var1, float var2);

    public float getRadius(CardViewDelegate var1);

    public void setElevation(CardViewDelegate var1, float var2);

    public float getElevation(CardViewDelegate var1);

    public void initStatic();

    public void setMaxElevation(CardViewDelegate var1, float var2);

    public float getMaxElevation(CardViewDelegate var1);

    public float getMinWidth(CardViewDelegate var1);

    public float getMinHeight(CardViewDelegate var1);

    public void updatePadding(CardViewDelegate var1);

    public void onCompatPaddingChanged(CardViewDelegate var1);

    public void onPreventCornerOverlapChanged(CardViewDelegate var1);

    public void setBackgroundColor(CardViewDelegate var1, int var2);
}


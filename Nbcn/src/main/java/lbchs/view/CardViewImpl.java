/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 */
package lbchs.view;

import android.content.Context;

interface CardViewImpl {
    void initialize(CardViewDelegate var1, Context var2, int var3, float var4, float var5, float var6);

    void setRadius(CardViewDelegate var1, float var2);

    float getRadius(CardViewDelegate var1);

    void setElevation(CardViewDelegate var1, float var2);

    float getElevation(CardViewDelegate var1);

    void initStatic();

    void setMaxElevation(CardViewDelegate var1, float var2);

    float getMaxElevation(CardViewDelegate var1);

    float getMinWidth(CardViewDelegate var1);

    float getMinHeight(CardViewDelegate var1);

    void updatePadding(CardViewDelegate var1);

    void onCompatPaddingChanged(CardViewDelegate var1);

    void onPreventCornerOverlapChanged(CardViewDelegate var1);

    void setBackgroundColor(CardViewDelegate var1, int var2);
}


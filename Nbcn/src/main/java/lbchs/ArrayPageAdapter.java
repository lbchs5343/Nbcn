/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  android.view.View
 *  java.lang.Object
 *  java.lang.Override
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Collection
 */
package lbchs;

import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayPageAdapter extends BasePageAdapter {
    public ArrayList<View> mListViews;

    public ArrayPageAdapter() {
        mListViews = new ArrayList<>();
    }

    public ArrayPageAdapter(ArrayList<View> arrayList) {
        mListViews = arrayList;
    }

    public ArrayPageAdapter(View[] viewArray) {
        this.mListViews = new ArrayList<>();
        Collections.addAll(this.mListViews, viewArray);
    }

    @Override
    public void destroyItem(View view, int n, Object object) {
        ((PageView) view).removeView(this.mListViews.get(n));
    }

    @Override
    public int getCount() {
        return this.mListViews.size();
    }

    @Override
    public Object instantiateItem(View view, int n) {
        ((PageView) view).addView(this.mListViews.get(n), 0);
        return this.mListViews.get(n);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void add(View view) {
        this.mListViews.add(view);
    }

    public void insert(int n, View view) {
        this.mListViews.add(n, view);
    }

    public View remove(int n) {
        return this.mListViews.remove(n);
    }

    public boolean remove(View view) {
        return this.mListViews.remove(view);
    }

    public View getItem(int n) {
        return this.mListViews.get(n);
    }

    public ArrayList<View> getData() {
        return this.mListViews;
    }
}


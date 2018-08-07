package com.example.ekint.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/**
 * SmartFragmentStatePagerAdapter is a Fragment Adapter that controls the fragments shown on the screen.
 * Designed for the tabs on the journal fragment
 * @author ekint
 * @version 1.0
 */

public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    /**
     * Sparse array to keep track of registered fragments in memory
     */
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public SmartFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Register the fragment when the item is instantiated
     * @param container container viewgroup of item
     * @param position position of item in view group
     * @return fragment that corresponds to position specified in parameter
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Unregister when the item is inactive
     * @param container container viewgroup of item
     * @param position  position of item in view group
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    /**
     * Returns the fragment for the position (if instantiated)
     * @param position position of item in view group
     * @return         fragment that corresponds to position specified in parameter
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}

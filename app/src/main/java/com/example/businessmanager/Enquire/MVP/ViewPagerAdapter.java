package com.example.businessmanager.Enquire.MVP;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.businessmanager.Enquire.MVP.Contacted.ContactedFragment;
import com.example.businessmanager.Enquire.MVP.Pending.PendingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    Context context;

    public ViewPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0: return new PendingFragment();
            case 1: return new ContactedFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: return "Pending";
            case 1: return "Contacted";
            default: return null;
        }
    }
}

package com.example.businessmanager.Enquire.MVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.businessmanager.Enquire.MVP.Contacted.ContactedFragment;
import com.example.businessmanager.Enquire.MVP.Pending.PendingFragment;
import com.example.businessmanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnquiryActivity extends AppCompatActivity implements PendingFragment.PendingListener, ContactedFragment.ContactListener
{
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar_back)
    ImageView imageView;
    @BindView(R.id.toolbar_text)
    TextView heading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquire);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        heading.setText("Enquiry");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void UpdateContact() {
        final ContactedFragment fragment = new ContactedFragment();
        fragment.refresh();
    }
}

package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
          toolbar = findViewById(R.id.toolbar2);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.view_p);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(getIntent().getStringExtra("name"));
        }
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);



    }


    private void setupViewPager(ViewPager viewPager1) {
        ViewPagerAdabter adabter = new ViewPagerAdabter(getSupportFragmentManager());
        int code = getIntent().getIntExtra("data", -5);
        Bundle bundle = new Bundle();
        bundle.putInt("data", code);
        EpsonF epsonF = new EpsonF();
        epsonF.setArguments(bundle);
        Comment comment = new Comment();
        Bundle bundle1 = new Bundle();
        int code1 = getIntent().getIntExtra("data1", -55);
        bundle1.putInt("data1", code1);

        String username = getIntent().getStringExtra("username1");
        bundle1.putString("username", username);
        comment.setArguments(bundle1);
        comment.setArguments(bundle1);
        adabter.addFragment(new Details(), "detail");
        adabter.addFragment(epsonF, "epson");
        adabter.addFragment(comment, "comment");
        viewPager1.setAdapter(adabter);
    }

    class ViewPagerAdabter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> fragments = new ArrayList<>();
        private final ArrayList<String> strings = new ArrayList<>();


        ViewPagerAdabter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            strings.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {


            return strings.get(position);
        }
    }


}
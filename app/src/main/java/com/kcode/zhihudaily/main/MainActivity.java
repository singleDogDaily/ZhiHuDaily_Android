package com.kcode.zhihudaily.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.BaseActivity;

public class MainActivity extends BaseActivity{

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar  = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_main);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,mToolbar,R.string.title_main,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //add fragment
        MainFragment fragment = MainFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,fragment)
                .commit();

        //create presenter
        new MainPresenter(fragment);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void bindView() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTitle(String title){
        if (mToolbar != null) {
           mToolbar.setTitle(title);
        }
    }

    public String getToolbarTitle() {
        return mToolbar.getTitle().toString();
    }
}

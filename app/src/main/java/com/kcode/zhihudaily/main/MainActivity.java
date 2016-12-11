package com.kcode.zhihudaily.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kcode.zhihudaily.R;
import com.kcode.zhihudaily.base.App;
import com.kcode.zhihudaily.base.BaseActivity;
import com.kcode.zhihudaily.bean.Other;
import com.kcode.zhihudaily.setting.SettingsActivity;
import com.kcode.zhihudaily.theme.ThemeFragment;
import com.kcode.zhihudaily.utils.L;
import com.kcode.zhihudaily.utils.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private final static L log = LogFactory.create(MainActivity.class);

    private MenuItem menuItem;
    private SharedPreferences spf;

    private DrawerLayout drawer;
    private LinearLayout linearLayout;

    private MainFragment fragment;
    private NavigationDrawerFragment navigationDrawerFragment;

    private int currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spf = getSharedPreferences(App.SPF, MODE_PRIVATE);
        //初始化主题
        initTheme();

        currentId = Other.MAIN_THEME_ID;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.title_main);
        setSupportActionBar(mToolbar);

        linearLayout = $(R.id.linearLayout);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.title_main, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //add fragment
        fragment = MainFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        //create presenter
        new MainPresenter(fragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindView() {

    }

    public void closeDrawer(int gravity) {
        drawer.closeDrawer(gravity);
    }

    public void showThemeFragment(Other other) {

        if (currentId == other.getId()) {
            return;
        }

        if (other.getId() == Other.MAIN_THEME_ID) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();

            return;
        }

        ThemeFragment themeFragment = (ThemeFragment) getSupportFragmentManager()
                .findFragmentByTag(ThemeFragment.class.getSimpleName());
        if (themeFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,ThemeFragment.newInstance(other),ThemeFragment.class.getSimpleName())
                    .commit();
        }else {
            themeFragment.changeTheme(other);
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(fragment)
                    .show(themeFragment)
                    .commit();
        }

        currentId = other.getId();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);

        menuItem = menu.findItem(R.id.action_model);
        if (getString(R.string.night_model).equals(spf.getString(App.ACTION_MODEL, getString(R.string.day_model)))) {
            menuItem.setTitle(getString(R.string.day_model));
            setTheme(R.style.NightTheme);
        } else {
            menuItem.setTitle(getString(R.string.night_model));
            setTheme(R.style.DayTheme);
        }
        refreshUI();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        log.i(item.getTitle().toString());
        switch (item.getItemId()) {
            case R.id.action_model:
                if ((getString(R.string.night_model)).equals(item.getTitle())) {
                    menuItem.setTitle(R.string.day_model);
                    spf.edit()
                            .putString(App.ACTION_MODEL, getString(R.string.night_model))
                            .apply();
                    setTheme(R.style.NightTheme);
                } else {
                    menuItem.setTitle(R.string.night_model);
                    spf.edit()
                            .putString(App.ACTION_MODEL, getString(R.string.day_model))
                            .apply();
                    setTheme(R.style.DayTheme);
                }
                refreshUI();
                break;
            case R.id.action_settings:
                start2Setting();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void start2Setting() {
        startActivity(SettingsActivity.class);
    }

    public void setToolbarTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public String getToolbarTitle() {
        return mToolbar.getTitle().toString();
    }


    private void initTheme() {
        if (getString(R.string.night_model).equals(spf.getString(App.ACTION_MODEL, getString(R.string.day_model)))) {
            log.i("night theme");
            setTheme(R.style.NightTheme);
        } else {
            log.i("day theme");
            setTheme(R.style.DayTheme);
        }
    }


    private void refreshUI() {
        showAnimation();
        refreshStatusBar();

        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        TypedValue textColor2 = new TypedValue();//今日热闻字体颜色
        TypedValue barBackground = new TypedValue();//ActionBar背景色

        Resources.Theme theme = getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
        theme.resolveAttribute(R.attr.clockTextColor2, textColor2, true);
        theme.resolveAttribute(R.attr.clockBarBackground, barBackground, true);

        mToolbar.setBackgroundResource(barBackground.resourceId);
        linearLayout.setBackgroundResource(background.resourceId);

        refreshRecyclerView(background, textColor, fragment.mRecyclerView);
        refreshRecyclerView(background,textColor,navigationDrawerFragment.mRecyclerView);

    }

    private void refreshRecyclerView(TypedValue background,TypedValue textColor,RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup childView = (ViewGroup) recyclerView.getChildAt(i);
            childView.setBackgroundResource(background.resourceId);
            View ll_item_story = childView.findViewById(R.id.ll_item_story);
            //headerView中的AutoScrollViewPager不需要变色
            if (ll_item_story == null) {
                continue;
            }
            ll_item_story.setBackgroundResource(background.resourceId);
            TextView tv_time = (TextView) childView.findViewById(R.id.tv_time);
            if (tv_time != null) {
                tv_time.setTextColor(getResources().getColor(textColor.resourceId));
            }

            CardView cardView = (CardView) childView.findViewById(R.id.cardView);
            if (cardView != null) {
                cardView.setCardBackgroundColor(background.resourceId);
            }

            View rl_item_story = childView.findViewById(R.id.rl_item_story);
            if (rl_item_story != null) {
                rl_item_story.setBackgroundResource(background.resourceId);
            }

            TextView tv_title = (TextView) childView.findViewById(R.id.tv_title);
            if (tv_title != null) {
                tv_title.setTextColor(getResources().getColor(textColor.resourceId));
            }
        }

        //让 RecyclerView 缓存在 Pool 中的 Item 失效
        //那么，如果是ListView，要怎么做呢？这里的思路是通过反射拿到 AbsListView 类中的 RecycleBin 对象，然后同样再用反射去调用 clear 方法
        Class<RecyclerView> recyclerViewClass = RecyclerView.class;
        try {
            Field declaredField = recyclerViewClass.getDeclaredField("mRecycler");
            declaredField.setAccessible(true);
            Method declaredMethod = Class.forName(RecyclerView.Recycler.class.getName()).getDeclaredMethod("clear", (Class<?>[]) new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredField.get(recyclerView), new Object[0]);
            RecyclerView.RecycledViewPool recycledViewPool = recyclerView.getRecycledViewPool();
            recycledViewPool.clear();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //刷新状态栏
    private void refreshStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            getWindow().setStatusBarColor(getResources().getColor(typedValue.resourceId));
        }
    }


    /**
     * 展示一个切换动画
     */
    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setDrawingCacheEnabled(false);
            view.destroyDrawingCache();
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    /**
     * 获取一个 View 的缓存视图
     *
     * @param view
     * @return
     */
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
            return;
        }
        super.onBackPressed();
    }
}

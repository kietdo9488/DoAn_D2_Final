package com.example.banhanggiadung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.banhanggiadung.adapter.CategoryAdapterKiet;
import com.example.banhanggiadung.adapter.ProductAdapter;
import com.example.banhanggiadung.fragment.AbstractFragment;
import com.example.banhanggiadung.fragment.CartFragment;
import com.example.banhanggiadung.fragment.HomeFragment;
import com.example.banhanggiadung.fragment.ProductListFragment;
import com.example.banhanggiadung.fragment.UserFragment;
import com.example.banhanggiadung.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private FragmentTransaction transaction;
    private int questionId;
    private AbstractFragment fragment = null;
    private BottomNavigationView bottomNavigationView;

    DrawerLayout drawerLayout;

    ViewFlipper viewFlipper;

    private NavigationView navigationView;

    //----------------------------------
    private static Activity mainActivitySave;

    //
    private TextView tv_screen;

    private SearchView searchView;

    private Toolbar toolbar;

//    fields fragment
private CategoryAdapterKiet categoryAdapterKiet;
    public static void setMainActivitySave(Activity mainActivitySave) {
        MainActivity.mainActivitySave = mainActivitySave;
    }

    public static Activity getMainActivitySave() {
        return mainActivitySave;
    }
    //------------------------------------------

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        navigationView = findViewById(R.id.nav_main);



        String[] items = {"Item1", "Item2", "Item3"};



        //gan bien de no con biet duong de tai su dung
        questionId = 0;
        //---------Gan gia tri de xai o nhieu noi goi--------
        MainActivity.setMainActivitySave(MainActivity.this);
        //-----------Anh xa ------------------
        anhXa();
        //----------ActionBar-------//
        ActionBar();
        //----------viewFlipper-------//
//        ActionViewFlipper();
        //----------Set number-------//
//        createNum(4, 1);
        //------------------------//
        //Set default selected
        updateUI(1);
//        Click to visiable navigationView left
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navigationView.;
//            }
//        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.HOME:
                        updateUI(1);
                        tv_screen.setText("Home");
                        break;
                    case R.id.PRODUCTLIST:
                        updateUI(2);
                        tv_screen.setText("Products");
                        break;
                    case R.id.CART:
                        updateUI(3);
                        tv_screen.setText("Cart");
                        break;
                    case R.id.USER:
                        updateUI(4);
                        tv_screen.setText("User");
                        break;
                }
                return true;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_product_management1:
                        Intent intent = new Intent(getApplicationContext() , ManageCategory.class );
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setMaxWidth(Integer.MIN_VALUE);
                

            }
        });




    }

    private void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void anhXa() {
        drawerLayout = findViewById(R.id.drawerLayout);
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        navigationView = findViewById(R.id.nav_main);
        tv_screen = findViewById(R.id.tv_screen);
        searchView = findViewById(R.id.action_search);
        toolbar = findViewById(R.id.toolbar);
        viewFlipper = findViewById(R.id.viewFlipper);
    }

    private void ActionViewFlipper()
    {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        mangQuangCao.add("https://cdn.tgdd.vn/2021/11/CookDish/cac-loai-do-gia-dung-hien-dai-cho-can-bep-nha-ban-them-tien-avt-1200x676.jpg");
        mangQuangCao.add("https://giadungsato.com/Uploads/images/giadungsato(2).jpg");
        mangQuangCao.add("https://ominsu.com.vn/wp-content/uploads/2017/09/Anh-NOI-CHAO-.jpg");
        for (int i = 0; i< mangQuangCao.size(); i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
    }

    private void updateUI(int abstractFragment) {
        questionId = abstractFragment;
        if (getSupportFragmentManager().findFragmentByTag(questionId + "") != null) {
            fragment = (AbstractFragment) getSupportFragmentManager().findFragmentByTag(questionId + "");
        } else {
            switch (abstractFragment) {
                case 1:
                    Log.d("TAG", "updateUI: 1");
                    fragment = new HomeFragment();
                    break;
                case 2:
                    Log.d("TAG", "updateUI: 2");
                    fragment = new ProductListFragment();
                    break;
                case 3:
                    Log.d("TAG", "updateUI: 3");
                    fragment = new CartFragment();
                    break;
                case 4:
                    Log.d("TAG", "updateUI: 4");
                    fragment = new UserFragment();
                    break;
            }
        }
        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment, questionId + "");
            if (getSupportFragmentManager().findFragmentByTag(questionId + "") == null) {
                transaction.addToBackStack(null);
            }
            transaction.commit();
        }
    }


}
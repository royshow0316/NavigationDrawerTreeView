package com.ietm.navigationdrawertreeview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ietm.navigationdrawertreeview.treeview.Element;
import com.ietm.navigationdrawertreeview.treeview.TreeViewAdapter;
import com.ietm.navigationdrawertreeview.treeview.TreeViewItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String NAV_ITEM_ID = "nav_index";

    DrawerLayout drawerLayout;
    TextView contentView;

    private int navItemId;

    private ArrayList<Element> elements;
    private ArrayList<Element> elementsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        init();

        ListView treeview = (ListView) findViewById(R.id.treeListView);
        TreeViewAdapter treeViewAdapter = new TreeViewAdapter(elements, elementsData, inflater);

        TreeViewItemClickListener treeViewItemClickListener = new TreeViewItemClickListener(treeViewAdapter);
        treeview.setAdapter(treeViewAdapter);
        treeview.setOnItemClickListener(treeViewItemClickListener);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentView = (TextView) findViewById(R.id.contentView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView view = (NavigationView) findViewById(R.id.navigationView);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, menuItem.getTitle() + " pressed", Toast.LENGTH_LONG).show();
                navigateTo(menuItem);

                drawerLayout.closeDrawers();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

//        if(null != savedInstanceState){
//            navItemId = savedInstanceState.getInt(NAV_ITEM_ID, R.id.navigationItem1);
//        }
//        else{
//            navItemId = R.id.navigationItem1;
//        }
//        navigateTo(view.getMenu().findItem(navItemId));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateTo(MenuItem menuItem){
        contentView.setText(menuItem.getTitle());

        navItemId = menuItem.getItemId();
        menuItem.setChecked(true);
    }

    private void init() {
        elements = new ArrayList<Element> ();
        elementsData = new ArrayList<Element> ();

        Element element1 = new Element("模組A第一層", Element.TOP_LEVEL, 0, Element.NO_PARENT, true, false);

        Element element2 = new Element("模組A第二層", Element.TOP_LEVEL + 1, 1, element1.getId(), true, false);
        Element element3 = new Element("模組A第三層", Element.TOP_LEVEL + 2, 2, element2.getId(), true, false);
        Element element4 = new Element("模組A第四層", Element.TOP_LEVEL + 3, 3, element3.getId(), false, false);

        Element element5 = new Element("模組A第二層-2", Element.TOP_LEVEL + 1, 4, element1.getId(), true, false);
        Element element6 = new Element("模組A第三層-2", Element.TOP_LEVEL + 2, 5, element5.getId(), true, false);
        Element element7 = new Element("模組A第四層-2", Element.TOP_LEVEL + 3, 6, element6.getId(), false, false);

        Element element8 = new Element("模組A第二層-3", Element.TOP_LEVEL + 1, 7, element1.getId(), false, false);

        Element element9 = new Element("模組B第一層", Element.TOP_LEVEL, 8, Element.NO_PARENT, true, false);
        Element element10 = new Element("模組B第二層", Element.TOP_LEVEL + 1, 9, element9.getId(), true, false);
        Element element11 = new Element("模組B第三層", Element.TOP_LEVEL + 2, 10, element10.getId(), true, false);
        Element element12 = new Element("模組B第四層", Element.TOP_LEVEL + 3, 11, element11.getId(), true, false);
        Element element13 = new Element("模組B第五層", Element.TOP_LEVEL + 4, 12, element12.getId(), false, false);

        elements.add(element1);
        elements.add(element9);

        elementsData.add(element1);
        elementsData.add(element2);
        elementsData.add(element3);
        elementsData.add(element4);
        elementsData.add(element5);
        elementsData.add(element6);
        elementsData.add(element7);
        elementsData.add(element8);
        elementsData.add(element9);
        elementsData.add(element10);
        elementsData.add(element11);
        elementsData.add(element12);
        elementsData.add(element13);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_ITEM_ID, navItemId);
    }
}

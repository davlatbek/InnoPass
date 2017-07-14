package innopolis.innopass.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import innopolis.innopass.R;
import innopolis.innopass.models.User;
import innopolis.innopass.utilities.SessionManager;
import innopolis.innopass.utilities.TempData;

public class StudentProfileActivity extends AppCompatActivity {
    String[] left_menu_items;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer) ListView listView;
    @BindView(R.id.userProfilePhoto) CircleImageView userProfilePhoto;
    @BindView(R.id.editUserName) EditText editUserName;
    @BindView(R.id.editUserSurname) EditText editUserSurname;
    @BindView(R.id.editUserMiddle) EditText editUserMiddlename;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        Long user_id = getIntent().getLongExtra("user_id", -1L);
        User user = TempData.getUserById(user_id);
        userProfilePhoto.setImageResource(user.getPhotoId());
        editUserName.setText(user.getFirstName());
        editUserSurname.setText(user.getSurname());
        editUserMiddlename.setText(user.getMiddleName());

        left_menu_items = getResources().getStringArray(R.array.nav_drawer_user);
        listView = (ListView) findViewById(R.id.left_drawer);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, left_menu_items));
        sessionManager = new SessionManager(this.getApplicationContext());
        sessionManager.checkLogin();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.logout:
                sessionManager.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDrawer() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Queries"));
//        tabLayout.addTab(tabLayout.newTab().setText("Cards"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(
//                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                Toast.makeText(getApplicationContext(), "reselected", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}

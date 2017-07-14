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
import innopolis.innopass.models.entities.User;
import innopolis.innopass.utilities.SessionManager;
import innopolis.innopass.utilities.TempData;

public class UserProfileActivity extends AppCompatActivity {
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
}

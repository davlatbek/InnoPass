package innopolis.innopass.views.activities;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import innopolis.innopass.R;
import innopolis.innopass.presenters.AdminProfilePresenter;
import innopolis.innopass.utilities.SessionManager;
import innopolis.innopass.views.fragments.CardsListFragment;
import innopolis.innopass.views.fragments.QueriesListFragment;
import innopolis.innopass.views.fragments.UsersListFragment;

public class AdminProfileActivity extends AppCompatActivity implements IAdminProfileView {
    AdminProfilePresenter adminProfilePresenter;
    String[] left_menu_items;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer) ListView drawerListView;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        ButterKnife.bind(this);
        adminProfilePresenter = AdminProfilePresenter.getInstance(this, getApplicationContext());

        left_menu_items = getResources().getStringArray(R.array.nav_drawer);
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, left_menu_items));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());

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
            case R.id.logout:
                sessionManager.logout();
                return true;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            //profile, users, cards, queries, about, quit
            Fragment fragment = null;
            switch (position){
                case 0:
                    if (getFragmentManager().getBackStackEntryCount() > 0)
                        getFragmentManager().beginTransaction().remove(
                            getFragmentManager().findFragmentById(
                                    R.id.main_admin_profile_layout)).commit();
                    break;
                case 1:
                    fragment = new UsersListFragment();
                    getFragmentManager().beginTransaction().replace(R.id.main_admin_profile_layout, fragment).commit();
                    break;
                case 2:
                    fragment = new CardsListFragment();
                    getFragmentManager().beginTransaction().replace(
                            R.id.main_admin_profile_layout, fragment).commit();
                    break;
                case 3:
                    fragment = new QueriesListFragment();
                    getFragmentManager().beginTransaction().replace(
                            R.id.main_admin_profile_layout, fragment).commit();
                    break;
                case 4:
                    AlertDialog.Builder dialogAbout = new AlertDialog.Builder(getApplicationContext());
                    dialogAbout.setTitle("About")
                            .setMessage("Innopolis PassCard App. 2017. \nInnopolis\nDavlet Israilov")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();
                    break;
                case 5:
                    System.exit(0);
            }

            // Highlight the selected item, update the title, and close the drawer
            drawerListView.setItemChecked(position, true);
            getSupportActionBar().setTitle(left_menu_items[position]);
            drawerLayout.closeDrawer(drawerListView);
        }
    }
}

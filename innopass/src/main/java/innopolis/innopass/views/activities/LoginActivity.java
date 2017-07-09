package innopolis.innopass.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import innopolis.innopass.R;
import innopolis.innopass.database.DatabaseHelper;
import innopolis.innopass.interfaces.view_interfaces.ILoginView;
import innopolis.innopass.presenters.LoginPresenter;
import innopolis.innopass.utilities.TempData;

/**
 * Created by davlet on 7/04/17.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {
    @BindView(R.id.editTextLogin) EditText editTextLogin;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.buttonLogin) Button buttonLogin;
    @BindView(R.id.buttonRegister) Button buttonRegister;
    private Context context;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        ButterKnife.bind(this);
        loginPresenter = LoginPresenter.getInstance(this, DatabaseHelper.getInstance(this));
    }

    @Override
    public void goToUserProfile() {
        Intent intent = new Intent(context, SettingsActivity.class);
        intent.putExtra("student_id", TempData.getStudents().get(0).getId());
        startActivity(intent);
    }

    @Override
    public void goToRegistrationPage() {
        Intent intent = new Intent(context, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.buttonLogin)
    public void OnClickLoginButton(View view){
        loginPresenter.validateCredentials(editTextLogin.getText().toString(),
                editTextPassword.getText().toString());
    }

    @OnClick(R.id.buttonRegister)
    public void OnClickRegisterButton(View view){
        startActivity(new Intent(context, RegistrationActivity.class));
    }
}

package innopolis.innopass.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import innopolis.innopass.R;
import innopolis.innopass.presenters.LoginPresenter;
import innopolis.innopass.utilities.SessionManager;

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
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        ButterKnife.bind(this);
        loginPresenter = LoginPresenter.getInstance(this, context);
        sessionManager = new SessionManager(context);
        editTextLogin.setText(sessionManager.getUserDetails().get(SessionManager.KEY_LOGIN));
        editTextPassword.setText(sessionManager.getUserDetails().get(SessionManager.KEY_PASS));
    }

    @Override
    public void goToUserProfile(Long id) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra("user_id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ActivityCompat.finishAffinity(LoginActivity.this);
    }

    @Override
    public void goToAdminProfile() {
        Intent intent = new Intent(context, AdminProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ActivityCompat.finishAffinity(LoginActivity.this);
    }

    @Override
    public void goToRegistrationPage() {
        Intent intent = new Intent(context, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTextLoginPassword(String login, String password) {
        editTextLogin.setText(login);
        editTextPassword.setText(password);
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

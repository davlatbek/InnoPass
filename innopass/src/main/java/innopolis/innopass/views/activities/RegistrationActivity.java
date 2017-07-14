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

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import innopolis.innopass.R;
import innopolis.innopass.models.entities.User;
import innopolis.innopass.presenters.RegistrationPresenter;

/**
 * Created by davlet on 6/20/17.
 */

public class RegistrationActivity extends AppCompatActivity implements IRegistrationView {
    @BindView(R.id.editRegLogin) EditText editTextLogin;
    @BindView(R.id.editRegPass) EditText editTextPass;
    @BindView(R.id.editRegPasswordConfirm) EditText editTextPassConfirm;
    @BindView(R.id.buttonRegRegister) Button buttonRegister;
    @BindView(R.id.buttonRegCancel) Button buttonCancel;
    @BindView(R.id.editRegFirstName) EditText editFirstName;
    @BindView(R.id.editRegSurname) EditText editSurname;
    @BindView(R.id.editRegMiddleName) EditText editRegMiddleName;
    @BindView(R.id.editRegDob) EditText editRegDateOfBirth;
    private Context context;
    private RegistrationPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = this;
        ButterKnife.bind(this);
        presenter = RegistrationPresenter.getInstance(this, context);
    }

    private boolean validateFields() {
        if (editFirstName.getText().toString().isEmpty()){
            editFirstName.setError("First name can't be empty");
            return false;
        }
        if (editSurname.getText().toString().isEmpty()) {
            editSurname.setError("Surname can't be empty");
            return false;
        }
        if (!editTextLogin.getText().toString().matches("^[a-zA-Z0-9._-]{3,}$")){
            editTextLogin.setError(getResources().getString(R.string.reg_login_error));
            return false;
        }
        if (!editTextPass.getText().toString().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")){
            editTextPass.setError(getResources().getString(R.string.reg_password_error));
            return false;
        }
        return true;
    }

    @OnClick(R.id.buttonRegRegister)
    @Override
    public void registerUserButton(View view) {
        if (validateFields())
            presenter.registerNewUser(new User(editTextLogin.getText().toString(),
                editTextPass.getText().toString(), editFirstName.getText().toString(),
                editSurname.getText().toString(), editRegMiddleName.getText().toString(),
                Calendar.getInstance(), R.drawable.lillie));
    }

    @OnClick(R.id.buttonRegCancel)
    @Override
    public void cancelRegistration(View view) {
        startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void goToLoginScreen() {
        startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

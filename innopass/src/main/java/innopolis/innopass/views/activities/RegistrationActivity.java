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
import innopolis.innopass.interfaces.view_interfaces.IRegistrationView;
import innopolis.innopass.models.Student;
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

    @OnClick(R.id.buttonRegRegister)
    @Override
    public void registerUserButton(View view) {
        presenter.registerNewUser(new Student(editTextLogin.getText().toString(),
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

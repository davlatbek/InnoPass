package innopolis.innopass.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import innopolis.innopass.R;
import innopolis.innopass.database.DatabaseHelper;
import innopolis.innopass.interfaces.view_interfaces.IRegistrationView;
import innopolis.innopass.presenters.RegistrationPresenter;

/**
 * Created by davlet on 6/20/17.
 */

public class RegistrationActivity extends AppCompatActivity implements IRegistrationView {
    @BindView(R.id.editTextLog) EditText editTextLogin;
    @BindView(R.id.editTextPass) EditText editTextPass;
    @BindView(R.id.editTextPasswordConfirm) EditText editTextPassConfirm;
    @BindView(R.id.buttonRegistration) Button buttonRegister;
    @BindView(R.id.buttonCancelRegistration) Button buttonCancel;
    private Context context;
    private RegistrationPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = this;
        presenter = RegistrationPresenter.getInstance(this, DatabaseHelper.getInstance(this));
    }

    @OnClick(R.id.buttonRegistration)
    @Override
    public void registerUserButton(View view) {

    }

    @OnClick(R.id.buttonCancelRegistration)
    @Override
    public void cancelRegistration() {
        startActivity(new Intent(context, LoginActivity.class));
    }
}

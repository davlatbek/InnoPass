package innopolis.innopass.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import innopolis.innopass.R;
import innopolis.innopass.utilities.TempData;
import innopolis.innopass.utilities.TempUsers;

/**
 * Created by davlet on 7/04/17.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText editTextLogin;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private Context context;;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        initializeViews();
    }

    private void initializeViews() {
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        setListeners();
    }

    private void setListeners() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateLoginPassword(editTextLogin.getText().toString(),
                        editTextPassword.getText().toString())){
                    if (editTextLogin.getText().toString().contains("admin")) {
//                        Intent intent = new Intent(context, AdminPageActivity.class);
//                        intent.putExtra("login", editTextLogin.getText().toString());
//                        startActivity(intent);
                        startActivity(new Intent(context, SettingsActivity.class));
                    } else {
                        //TODO implement login to user_id map
                        Intent intent = new Intent(context, StudentPageActivity.class);
                        intent.putExtra("student_id", TempData.getStudents().get(0).getId());
                        startActivity(intent);
                    }
                }
                else Toast.makeText(context, "Wrong login or password!", Toast.LENGTH_SHORT).show();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegistrationActivity.class));
            }
        });
    }

    private boolean validateLoginPassword(String login, String password) {
        return TempUsers.existsUser(login, password);
    }
}

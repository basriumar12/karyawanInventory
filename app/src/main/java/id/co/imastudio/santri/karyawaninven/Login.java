package id.co.imastudio.santri.karyawaninven;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.co.imastudio.santri.karyawaninven.adapterLogin.LoginDataBaseAdapter;

public class Login extends AppCompatActivity
{
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;
    SessionManager sesion;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //sessino manager
        sesion = new SessionManager(getApplicationContext());


        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub


                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }
    // Methos to handleClick Event of Sign In Button
    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(Login.this);
        dialog.setContentView(R.layout.log_in);
        dialog.setTitle("");

        // get the Refferences of views
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.loginAkun);

        // Set On ClickListener
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                // check if the Stored password matches with  Password entered by user
                if(password.equals(storedPassword))
                {

                    sesion.createSession(editTextUserName.getText().toString());

                    Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (Login.this, MainActivity.class);
                    intent.putExtra("username", userName);
                    intent.putExtra("pass",password);

                    startActivity(intent);
                    dialog.dismiss();
                }
                else
                {
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}

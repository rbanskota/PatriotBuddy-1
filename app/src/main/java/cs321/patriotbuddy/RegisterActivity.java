package cs321.patriotbuddy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void register(View view) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        EditText emailText = findViewById(R.id.email);
        final String email = emailText.getText().toString();
        EditText passwordText = findViewById(R.id.email);
        String password = passwordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("HELLO", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            user.sendEmailVerification()
//                                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//
//                                            // [END_EXCLUDE]
//                                        }
//                                    });
                            Intent intent = new Intent(RegisterActivity.this, ProfileDisplay.class);
                            intent.putExtra("user", user);
                            intent.putExtra("username", email);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("HELLO", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}

package au.kesbokar.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

    EditText edtemail;
    EditText edtPassword;
    Button btnLogin;
    String Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtemail = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Username = edtemail.getText().toString();
                Password = edtPassword.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(loginActivity.this);
                String url = "https://thp.techeela.net/api/login?email="+Username+"&password="+Password;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        
                        Log.i("Response", response.toString());
                        Toast.makeText(loginActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(loginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.i("Error", "Errorr");
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
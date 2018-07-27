package br.com.stampstudio.checkinqard;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btnCheckin;
    private Button btnHistory;
    private TextView txtNextCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheckin = findViewById(R.id.btnCheckin);
        btnHistory = findViewById(R.id.btnHistory);
        txtNextCard = findViewById(R.id.txtNextCard);

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Bater Cartão");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue history = Volley.newRequestQueue(MainActivity.this);
                String historyURL = Helper.getConfigValue(MainActivity.this, "history_url");

                StringRequest historyRequest = new StringRequest(Request.Method.POST, historyURL,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                System.out.println(response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(MainActivity.this,  "Opa! Algo deu errado!", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<>();
                        params.put("idEmployee", "1");

                        return params;
                    }
                };

                history.add(historyRequest);
            }
        });

        String nextCardURL = Helper.getConfigValue(this, "nextcard_url");

        RequestQueue nextCard = Volley.newRequestQueue(this);

        StringRequest nextCardRequest = new StringRequest(Request.Method.POST, nextCardURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        txtNextCard.setText(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(MainActivity.this,  "Opa! Algo deu errado!", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("idEmployee", "1");

                return params;
            }
        };

        nextCard.add(nextCardRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String KEY = Helper.getConfigValue(this, "qr_key");
        String URL = Helper.getConfigValue(this, "checkin_url");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result.getContents() != null) {
            if(result.getContents().equals(KEY)) {
                RequestQueue queue = Volley.newRequestQueue(this);

                StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Toast.makeText(MainActivity.this,  response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(MainActivity.this,  "Opa! Algo deu errado!", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<>();
                        params.put("idEmployee", "1");

                        return params;
                    }
                };
                queue.add(postRequest);

            } else {
                Toast.makeText(this, "QR Code inválido", Toast.LENGTH_SHORT).show();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

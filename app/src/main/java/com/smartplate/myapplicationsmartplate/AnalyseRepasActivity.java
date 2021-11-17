package com.smartplate.myapplicationsmartplate;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AnalyseRepasActivity extends AppCompatActivity {
    TextView analyseRepas;
    TextView calorie;
    TextView proteines;
    TextView glucides;
    TextView lipides;
    Button rechercheNutriton;
    Aliments alimentsReconnu;
    String reponseAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyse_repas_activity);
        Intent intent = getIntent();
        alimentsReconnu = intent.getParcelableExtra("resultat_reco");
        initActivity();
        analyseRepas.setText(alimentsReconnu.getName());

    }

    private void initActivity() {
        analyseRepas = (TextView)findViewById(R.id.text_analyse_repas_activity);
        rechercheNutriton = findViewById(R.id.recherche_nutriment_button);
        calorie = (TextView)findViewById(R.id.calories);
        proteines = (TextView)findViewById(R.id.proteine);
        lipides = (TextView)findViewById(R.id.lipide);
        glucides = (TextView)findViewById(R.id.glucides);
        createNutritionalResearch();
    }

    private void createNutritionalResearch() {
        rechercheNutriton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        //tache en backgroud
                        try {
                            requestNutritonalInfo();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            //tache sur le main
                            @Override
                            public void run() {
                                parseNutrionalInfo(reponseAPI);
                            }
                        });
                    }
                });
            }
        });
    }

    private void requestNutritonalInfo() throws IOException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("query",alimentsReconnu.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(jsonObject.toString(),mediaType);
        Request request = new Request.Builder()
                .url("https://trackapi.nutritionix.com/v2/natural/nutrients")
                .method("POST", body)
                .addHeader("x-app-id", "6497ff49")
                .addHeader("x-app-key", "87e2de7e7e7ea3266e3abfed0a1d1ecb")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        reponseAPI = response.body().string();
    }
    private void parseNutrionalInfo (String response){
        Gson gson = new Gson();
        NutritionalInfo nutritionalInfo = gson.fromJson(response,NutritionalInfo.class);
        List<NutritionalInfo.FoodsBean>nutritionalInfos = nutritionalInfo.getFoods();
        double factor;
        factor = (double) nutritionalInfos.get(0).getServing_weight_grams()/100;
        double calories = nutritionalInfos.get(0).getNf_calories()/factor;
        double lipide = nutritionalInfos.get(0).getNf_total_fat()/factor;
        double proteine = nutritionalInfos.get(0).getNf_protein()/factor;
        double glucide = nutritionalInfos.get(0).getNf_total_carbohydrate()/factor;
        calorie.setText("Energie : "+String.format("%.2f",calories)+" Kcal");
        glucides.setText("Glucides : "+String.format("%.2f",glucide)+" g");
        lipides.setText("Lipides : "+String.format("%.2f",lipide)+" g");
        proteines.setText("Proteines : "+String.format("%.2f",proteine)+" g");
    }
}
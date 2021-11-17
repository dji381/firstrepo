package com.smartplate.myapplicationsmartplate;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.exifinterface.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.smartplate.myapplicationsmartplate.views.alimentAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements alimentAdapter.RecyclerViewClickListener {
    private TextView RepasDescription;
    private ImageView PhotoRepas;
    private Button ButtonTakePic;
    private Button ButtonReco;
    private String photoPath;
    private String reponseAPI;
    private RecyclerView mRecyclerView;
    private List<Aliments>aliments1;
    private alimentAdapter monAdapter;
    private final int STORAGE_PERMISSION_CODE = 1;
    ActivityResultLauncher<Intent> photoActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    // récupéré l'image
                    Bitmap image = BitmapFactory.decodeFile(photoPath);
                    //afficher l'image
                    PhotoRepas.setImageBitmap(rotateImage(image));
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
    }
    /**
     * Methode qui lance l'activité
     */
    private void initActivity(){
        RepasDescription = findViewById(R.id.Repas);
        PhotoRepas = findViewById(R.id.Photo_repas);
        ButtonTakePic = findViewById(R.id.button_take_pic);
        ButtonReco = findViewById(R.id.Reco);
        mRecyclerView = (RecyclerView)findViewById(R.id.affichageRepas);
        /**
        *appel de la methode qui lance l'appareil photo en cliquant sur le boutton
        */
        createListenerBtnPrendrePhoto();
        // methode qui lance la reco d'image
        createListenerBtnReco();
    }

    /**
     * creation d'un listener pour le boutton photo qui lance la methode pour ouvrir
     * et stocker les photo
     */
    private void createListenerBtnPrendrePhoto(){
        ButtonTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               prendreUnePhoto();
            }
        });
    }
    // listener pour le bouton de reco
    private void createListenerBtnReco(){
        ButtonReco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // permission pour creation de fichier
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"Reconnaisance en cours !",Toast.LENGTH_SHORT).show();
                }else {
                    requestStoragePermission();
                }
                ExecutorService executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.getMainLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // tache en backgroud
                            envoyerPhotoApiokhttp();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // tache sur le main
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                parse(reponseAPI);
                                monAdapter = new alimentAdapter(aliments1,MainActivity.this);
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                mRecyclerView.setAdapter(monAdapter);

                                // object aliment qui recupére le tab reponse et affiche dans une recycler view
                            }
                        });
                    }
                });
            }
        });

    }

    private void requestStoragePermission() {
        // si l'utilisateur refuse l'accés et tente de relancer une requete
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this).setTitle("Permission requise").setMessage("Besoin de l'autorisation pour identifier votre repas").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

                }
            }).setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GANRANTIE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission REFUSEE", Toast.LENGTH_SHORT).show();
            }
        }
    }

    ;

    private void envoyerPhotoApiokhttp() throws IOException {
        // generation de l'image
        Bitmap fullSizeBitmap = BitmapFactory.decodeFile(photoPath);


        // Transformation de l'image en file
        File reducedFile = getBitmapFile(rotateImage(fullSizeBitmap));
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.MINUTES).writeTimeout(5,TimeUnit.MINUTES).readTimeout(5,TimeUnit.MINUTES);
        OkHttpClient client = builder.build();
        MediaType mediaType = MediaType.parse("multipart/form-data");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image",reducedFile.getName()+".jpg",
                        RequestBody.create(reducedFile,MediaType.parse("application/octet-stream")))
                .build();
        Request request = new Request.Builder()
                .url("https://api.logmeal.es/v2/image/recognition/complete/v0.9?language=eng")
                .method("POST", body)
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Authorization", "Bearer e81bfd175b68b2994c17355657a5116d7788aa05")
                .build();
        Response response = client.newCall(request).execute();
        reponseAPI = response.body().string();
        System.out.println(reponseAPI);



    }
    // Methode pour parse Json
    private void parse (String responseBody){
        aliments1 = new ArrayList<>();
        Gson gson = new Gson();
        JSonObjectRequest jSonObjectRequest = gson.fromJson(responseBody,JSonObjectRequest.class);
        List<JSonObjectRequest.RecognitionResultsBean> recognition = jSonObjectRequest.getRecognition_results();
        for (int i=0; i<recognition.size();i++){
            aliments1.add(new Aliments(recognition.get(i).getName(),jSonObjectRequest.getImageId(),recognition.get(i).getId())) ;
            //créér à un objet aliment que l'ont envoi à un tableau
        }


    }
    //Methode pour traduire une image en fichier
    private File getBitmapFile (Bitmap reducedBitmap){
        File file = new File(Environment.getExternalStorageDirectory()+File.separator+"reducted_file");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        reducedBitmap.compress(Bitmap.CompressFormat.JPEG,50,bos);
        byte[] bitmapdata = bos.toByteArray();
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Methode pour ouvrir l'app photo et stocker la photo dans une ficher temporaire
     */
    private void prendreUnePhoto(){
        //Cree un intent pour acceder à l'appareil photo
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //test pour contrôle que l'intent peut être géré
        if (intent.resolveActivity(getPackageManager())!=null){
            //Créér un nom de ficher unique pour ficher temporaire
            String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            //creation d'un fichier pour recupérer le chemin du directory
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time,".jpg",photoDir);
                // chemin de la photo
                photoPath = photoFile.getAbsolutePath();
                // créér l'URI
                Uri photoUri = FileProvider.getUriForFile(MainActivity.this,
                        MainActivity.this.getApplicationContext().getPackageName()+".provider",
                        photoFile);
                //Transfert de l'uri vers l'intent pour enregistrement photo temporaire
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                //ouvrir l'activity par rapport à l'intent
                photoActivityResultLauncher.launch(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //method pour rotate l'image
    private Bitmap rotateImage (Bitmap bitmap){
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(photoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
        Matrix matrix = new Matrix();
        switch (orientation){
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            default:
        }
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return rotatedBitmap;

    }

    @Override
    public void onClick(int position) {
        //listener pour lancer une nouvelle activité a partir d'un click recyclerview
        Intent intent = new Intent(MainActivity.this,AnalyseRepasActivity.class);
        intent.putExtra("resultat_reco",aliments1.get(position));
        startActivity(intent);

    }
}
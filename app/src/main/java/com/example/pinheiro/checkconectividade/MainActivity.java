package com.example.pinheiro.checkconectividade;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NetworkInfo info;
    private static final String DEBUG_TAG = "NetworkStatusExample";
    Context context;


    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.fragment_container);

        isConectado();


    }

    @Override
    protected void onStart() {
        super.onStart();

        isConectado();

    }



    private void isConectado() {

        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        FragmentConexaoFailed fragmentConexaoFailed = new FragmentConexaoFailed();
        

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo info =

                connectivityManager.getActiveNetworkInfo();

        boolean isConectado =

                info != null &&
                        info.isConnectedOrConnecting();

        if (isConectado) {

            Toast.makeText(this, "você está conectado", Toast.LENGTH_SHORT).show();


            if (fragmentTransaction != null) {

                Log.d("LOG", ">>>><<<<<<" + fragmentConexaoFailed.isAdded());

                fragmentTransaction.remove(fragmentConexaoFailed);
                fragmentTransaction.commit();
                frameLayout.setVisibility(View.INVISIBLE);

            }

            } else {

                Toast.makeText(this, "você não está conectado", Toast.LENGTH_SHORT).show();

                if (fragmentTransaction != null) {

                    fragmentTransaction.replace(R.id.fragment_container, fragmentConexaoFailed);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();


                }


            }

        }

}







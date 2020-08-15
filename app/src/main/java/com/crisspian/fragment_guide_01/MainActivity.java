package com.crisspian.fragment_guide_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.crisspian.fragment_guide_01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean frag = false;
    public  static final String KEY_ONE ="KEY_ONE";
    public  static final String KEY_DOS ="KEY_DOS";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(KEY_ONE,frag );
        outState.putString(KEY_DOS, "OPEN" );
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState != null){
            frag = savedInstanceState.getBoolean(KEY_ONE);

        }
        if(frag ){

            binding.button.setText("Close");
        }

        binding.button.setText("Click me!");

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!frag){
                      showFragment( "holaaaaa");
                }
                else {
                    closefragment();
                }


            }
        });

    }

    private void showFragment(String saludo) {
        // Generamos la instancia del fragmento gracias al factory method
        FirstFragment firstFragment = FirstFragment.newInstance(saludo);
        //Obtener instancia del FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Obtenemos e instanciamos una transacción
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // añadir el fragmento y lo asociamos al contenedor donde se mostrara
        fragmentTransaction.add(R.id.content_fragment, firstFragment)
                //.addToBackStack(null)
                .commit();
        binding.button.setText("close");
        frag=true;
    }
    private void closefragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_fragment);
        if (fragment != null){
            FragmentTransaction FragmentTransaction = fragmentManager.beginTransaction();
            FragmentTransaction.remove(fragment).commit();
        }
        binding.button.setText("open");
        frag=false;
    }

}
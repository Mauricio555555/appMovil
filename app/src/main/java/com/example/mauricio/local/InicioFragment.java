package com.example.mauricio.local;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    //private TextView info_text;
    private WebView winfo_text;
    private FragmentActivity myContext;
    public ImageButton agregar,ver,acercade,salir;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }
    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        agregar= (ImageButton) view.findViewById(R.id.btnpone);
        ver = (ImageButton) view.findViewById(R.id.btnconfe);
        acercade= (ImageButton) view.findViewById(R.id.btnacerca);
        salir= (ImageButton) view.findViewById(R.id.btnsal);

        agregar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //  Log.e("fdgfdfgfgfgfh--->","SI");
               Fragment fragment = null;
                Class fragmentClass= GuardarFragment.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

                      }
        });
     /*   ver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  Log.e("fdgfdfgfgfgfh--->","SI");
                Fragment fragment = null;
                Class fragmentClass= PonenciasFragment.class;
                try{
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager=myContext.getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            }
        });*/
        acercade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //  Log.e("fdgfdfgfgfgfh--->","SI");

                AlertDialog.Builder uBuilder2 = new AlertDialog.Builder(InicioFragment.super.getContext());
                View aView2 = getLayoutInflater().inflate(R.layout.acercade, null);
                uBuilder2.setView(aView2);
                final AlertDialog dialog2 = uBuilder2.create();
                dialog2.show();
                Button close = (Button) aView2.findViewById(R.id.close);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.cancel();
                    }
                });

            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               myContext.finish();
            }
        });


       //info_text=(TextView) view.findViewById(R.id.info_text);
       //info_text.setText(Html.fromHtml(getResources().getString(R.string.textoInicio)));

       //winfo_text=(WebView)view.findViewById(R.id.winfo_text);
     //  winfo_text.loadData(getResources().getString(R.string.textoInicio), "text/html","utf-8");
     //   winfo_text.setBackgroundColor(Color.TRANSPARENT);
        return view;
    }

}

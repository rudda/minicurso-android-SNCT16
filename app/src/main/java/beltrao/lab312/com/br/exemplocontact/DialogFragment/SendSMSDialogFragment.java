package beltrao.lab312.com.br.exemplocontact.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import beltrao.lab312.com.br.exemplocontact.R;
import beltrao.lab312.com.br.exemplocontact.SMS.SimpleSMS;

/**
 * Created by Rudda Beltrao on 13/10/2016.
 */

@SuppressLint("ValidFragment")
public class SendSMSDialogFragment extends android.support.v4.app.DialogFragment{

    private TextView enviar;
    private TextView cancelar;
    private TextView tvdestino;

    private Context com;
    private String destino;
    private String phone;
    private EditText msg;


    public SendSMSDialogFragment( String phone, String destino, Context com) {

        this.phone = phone;
        this.destino = destino;

        this.com = com;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View v = inflater.inflate(R.layout.layout_send_sms, container);


        enviar = (TextView) v.findViewById(R.id.enviar);
        cancelar = (TextView) v.findViewById(R.id.cancelar);
        tvdestino = (TextView) v.findViewById(R.id.destino);
        msg = (EditText) v.findViewById(R.id.msg);

        tvdestino.setText(""+destino+" "+phone);



        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dismiss();
            }
        });



        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleSMS simpleSMS = new SimpleSMS(com);

                boolean enviou = simpleSMS.sendSMS(phone, msg.getText().toString()+"\n MINICURSO-ANDROID-ICET");

               if( enviou ){


                   Toast.makeText(getActivity(), "Enviando SMS ...", Toast.LENGTH_SHORT).show();
                   dismiss();
               }

                else{

                   Toast.makeText(getActivity(), "Falha ao enviar SMS ...", Toast.LENGTH_SHORT).show();


               }


            }
        });


        return v;

    }









}

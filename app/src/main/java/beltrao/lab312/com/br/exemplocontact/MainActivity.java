package beltrao.lab312.com.br.exemplocontact;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsMessage;

import java.util.ArrayList;
import java.util.List;

import beltrao.lab312.com.br.exemplocontact.Adapter.ContactAdapter;
import beltrao.lab312.com.br.exemplocontact.Domain.ContatosUtil;
import beltrao.lab312.com.br.exemplocontact.Receiver.SMSReceiver;
import beltrao.lab312.com.br.exemplocontact.SMS.SimpleSMS;

public class MainActivity extends AppCompatActivity {

        private List<ContatosUtil> contatos;
        private ContactAdapter adp;
        private RecyclerView r;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            contatos = new ArrayList<>();

            r = (RecyclerView) findViewById(R.id.mRecycler);


            LinearLayoutManager ll = new LinearLayoutManager(getApplicationContext());
            ll.setOrientation(LinearLayoutManager.VERTICAL);
            r.setLayoutManager(ll);



            new Thread(new Runnable() {
                @Override
                public void run() {

                    contatos = ContatosUtil.build(MainActivity.this).getContatos();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            adp = new ContactAdapter(contatos, MainActivity.this, MainActivity.this);
                            adp.notifyDataSetChanged();
                            r.setAdapter(adp);

                        }

                    });
                }
            }).start();




        }



    }

package beltrao.lab312.com.br.exemplocontact.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.List;
import java.util.Random;

import beltrao.lab312.com.br.exemplocontact.DialogFragment.SendSMSDialogFragment;
import beltrao.lab312.com.br.exemplocontact.Domain.ContatosUtil;
import beltrao.lab312.com.br.exemplocontact.R;

/**
 * Created by Rudda Beltrao on 08/10/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.holder> {


    private List<ContatosUtil> nomes;
    private Context com;
    //private Typeface custom_font;
    private AppCompatActivity mAppCompatActivity;

    public ContactAdapter(List<ContatosUtil> nomes, Context com, AppCompatActivity mAppCompatActivity) {

        this.nomes = nomes;
        this.com = com;
        this.mAppCompatActivity = mAppCompatActivity;
       // this.pakage = pakage;
      //  custom_font = Typeface.createFromAsset(com.getAssets(), "quicksand.ttf");

    }


    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(com).inflate(R.layout.layout_contatos, parent, false);

        holder mHolder = new holder(v);


        return mHolder;


    }


    @Override
    public void onBindViewHolder(holder holder, final int position) {
        int[] mMaterialColors = com.getResources().getIntArray(R.array.colors);
        Random RANDOM = new Random();

        holder.name.setText(nomes.get(position).getNome());
        holder.mMaterialLetterIcon.setLetterSize(30);
        holder.mMaterialLetterIcon.setLettersNumber(1);
        //holder.mMaterialLetterIcon.setLetterTypeface(custom_font);
        holder.mMaterialLetterIcon.setLetterColor(Color.WHITE);
        holder.mMaterialLetterIcon.setShapeColor(mMaterialColors[RANDOM.nextInt(mMaterialColors.length)]);
        holder.mMaterialLetterIcon.setLetter(nomes.get(position).getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction ft = mAppCompatActivity.getSupportFragmentManager().beginTransaction();
                SendSMSDialogFragment sendSMSDialogFragment = new SendSMSDialogFragment(nomes.get(position).getNumber(),
                        nomes.get(position).getNome(),
                        com);
                sendSMSDialogFragment.show(ft, "Enviar SMS");


            }
        });

    }

    @Override
    public int getItemCount() {

        return nomes.size();
    }


    class holder extends RecyclerView.ViewHolder {
        public TextView name;
        public MaterialLetterIcon mMaterialLetterIcon;

        public holder(View itemView) {
            super(itemView);

            mMaterialLetterIcon = (MaterialLetterIcon) itemView.findViewById(R.id.materialLetterCollorID);
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }
}

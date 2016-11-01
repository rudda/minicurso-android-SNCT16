package beltrao.lab312.com.br.exemplocontact.Domain;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Rudda Beltrao on 13/10/2016.
 */


public class ContatosUtil {

    private String number;
    private String nome;
    private String ID;
    private Context com;
    private List<ContatosUtil> contatos;
    private String ids[];
    private static ContatosUtil c;

    public static ContatosUtil build(Context com) {

        if(c==null){

            return c = new ContatosUtil(com);
        }

        else {


            return c;
        }


    }

    public ContatosUtil(Context com) {
        this.com = com;
        contatos = new ArrayList<>();

    }

    public ContatosUtil() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String getTelefone(String id) {

        final String[] projection = {
                ContactsContract.CommonDataKinds.Phone.TYPE,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // Consulta os telefones filtrando pelo Id do contato.
        Cursor c = com.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                        " = ?",
                new String[]{id}, null);
        while (c.moveToNext()) {
            int tipo = c.getInt(c.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.TYPE));

                return c.getString(c.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));


        }
        c.close();

        return null;
    }


    public List<ContatosUtil> getContatos() {

        Cursor cursor = com.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int nameIdx = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        int numberIdx = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);

        ids = new String[cursor.getCount()];

        Log.i("CON", "size " + cursor.getCount());
        if (cursor.moveToFirst()) {
            do {
                //Extrai o nome
                String name = cursor.getString(nameIdx).toLowerCase();
                //Extrai o ID
                String ID = cursor.getString(numberIdx);

                ContatosUtil c = new ContatosUtil();
                c.setNumber(getTelefone(ID));
                c.setNome(name);
                c.setID(ID);

                ids[cursor.getPosition()] = name;
                contatos.add(c);

            } while (cursor.moveToNext());
        }


        orderABC();
        return contatos;
    }


    public List<ContatosUtil> getContatosFromSIM() {

        try {
            String clsSimPhonename = null;
            String clsSimphoneNo = null;

            Uri simUri = Uri.parse("content://icc/adn");
            Cursor cursorSim = com.getContentResolver().query(simUri, null,
                    null, null, null);

            while (cursorSim.moveToNext()) {
                clsSimPhonename = cursorSim.getString(cursorSim
                        .getColumnIndex("name"));
                clsSimphoneNo = cursorSim.getString(cursorSim
                        .getColumnIndex("number"));

                clsSimphoneNo.replaceAll("\\D", "");
                clsSimphoneNo.replaceAll("&", "");
                clsSimPhonename = clsSimPhonename.replace("|", "");

                ContatosUtil c = new ContatosUtil();
                c.setNome(clsSimPhonename);
                c.setNumber(clsSimphoneNo);
                contatos.add(c) ;

            }


        }
        catch (Exception e){

        }

        return  contatos;
    }




    private void orderABC() {

        Arrays.sort(ids);
        List<ContatosUtil> aux = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {

            for (int j = 0; j < ids.length; j++) {

                if (contatos.get(j).getNome().equals(ids[i]))
                    aux.add(contatos.get(j));

            }

        }

        contatos.clear();
        contatos = aux;

    }

}
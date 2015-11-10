package com.exsoft.amy.dataatk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.exsoft.amy.dataatk.domain.ATK;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ATK atk = new ATK();
    DBAdapter dbAdapter = null;

    EditText txtNamabarang, txtMerek, txtSatuan, txtJumlah, txtHarga;
    ListView listAtk;
    Button btnSimpan;
    ATK editATK;

    private static final String OPTION[] = {"Edit", "Delete"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DBAdapter(getApplicationContext());

        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        txtNamabarang = (EditText) findViewById(R.id.txtNama);
        txtMerek = (EditText) findViewById(R.id.txtMerek);
        txtSatuan = (EditText) findViewById(R.id.txtSatuan);
        txtJumlah = (EditText) findViewById(R.id.txtJumlah);
        txtHarga = (EditText) findViewById(R.id.txtHarga);
        listAtk = (ListView) findViewById(R.id.listAtk);

        listAtk.setOnItemClickListener(new ListItemClick());
        listAtk.setAdapter(new ListAtkAdapter(dbAdapter
                .getAllAtk()));
    }

    public class ListItemClick implements AdapterView
            .OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view,
                                int position, long id) {
            // TODO Auto-generated method stub
            final ATK atk = (ATK) listAtk
                    .getItemAtPosition(position);
            showOptionDialog(atk);
        }
    }

    public void showOptionDialog(ATK atk) {
        final ATK mAtk;
        mAtk = atk;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Test")
                .setItems(OPTION, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int post) {
                        // TODO Auto-generated method stub
                        switch (post) {
                            case 0:
                                editATK = mAtk;
                                txtNamabarang.setText(mAtk.getNamabarang());
                                txtMerek.setText(mAtk.getMerek());
                                txtSatuan.setText(mAtk.getSatuan());
                                txtJumlah.setText(mAtk.getJumlah());
                                txtHarga.setText(mAtk.getHarga());
                                btnSimpan.setText("Edit");
                                break;
                            case 1:
                                dbAdapter.delete(mAtk);
                                listAtk.setAdapter
                                        (new ListAtkAdapter(dbAdapter.getAllAtk()));
                                break;
                            default:
                                break;
                        }
                    }
                });
        final Dialog d = builder.create();
        d.show();
    }

    public void save(View v) {
        if(txtNamabarang.getText().length() == 0 ||
                txtMerek.getText().length() == 0 ||
                txtSatuan.getText().length() == 0 ||
                txtJumlah.getText().length() == 0 ||
                txtHarga.getText().length() == 0) {
            txtNamabarang.setError("Cannot Empty");
            txtMerek.setError("Cannot Empty");
            txtSatuan.setError("Cannot Empty");
            txtJumlah.setError("Cannot Empty");
            txtHarga.setError("Cannot Empty");
        } else {
            if(btnSimpan.getText().equals("Edit")) {
                editATK.setNamabarang(txtNamabarang.getText().toString());
                editATK.setMerek(txtMerek.getText().toString());
                editATK.setSatuan(txtSatuan.getText().toString());
                editATK.setJumlah(txtJumlah.getText().toString());
                editATK.setHarga(txtHarga.getText().toString());
                dbAdapter.updateATK(editATK);
                btnSimpan.setText("Simpan");
            } else {
                atk.setNamabarang(txtNamabarang.getText().toString());
                atk.setMerek(txtMerek.getText().toString());
                atk.setSatuan(txtSatuan.getText().toString());
                atk.setJumlah(txtJumlah.getText().toString());
                atk.setHarga(txtHarga.getText().toString());
                dbAdapter.save(atk);
            }
            txtNamabarang.setText("");
            txtMerek.setText("");
            txtSatuan.setText("");
            txtJumlah.setText("");
            txtHarga.setText("");
        }
        listAtk.setAdapter(new ListAtkAdapter(dbAdapter
                .getAllAtk()));
    }

    public class ListAtkAdapter extends BaseAdapter {
        private List<ATK> listAtk;

        public ListAtkAdapter(List<ATK> listAtk) {
            this.listAtk = listAtk;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return this.listAtk.size();
        }

        @Override
        public ATK getItem(int position) {
            // TODO Auto-generated method stub
            return this.listAtk.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            // TODO Auto-generated method stub
            if(convertView == null) {
                convertView = LayoutInflater
                        .from(getApplicationContext())
                        .inflate(R.layout.list_layout, parent, false);
            }
            final ATK atk = getItem(position);
            if(atk != null) {
                TextView txtNamabarang = (TextView) convertView
                        .findViewById(R.id.txtNamabarang);
                txtNamabarang.setText(atk.getNamabarang());
                TextView txtMerek = (TextView) convertView
                        .findViewById(R.id.txtMerek);
                txtMerek.setText(atk.getMerek());
                TextView txtSatuan = (TextView) convertView
                        .findViewById(R.id.txtSatuan);
                txtSatuan.setText(atk.getSatuan());
                TextView txtJumlah = (TextView) convertView
                        .findViewById(R.id.txtJumlah);
                txtJumlah.setText(atk.getJumlah());
                TextView txtHarga = (TextView) convertView
                        .findViewById(R.id.txtHarga);
                txtHarga.setText(atk.getHarga());
            }
            return convertView;
        }
    }
}

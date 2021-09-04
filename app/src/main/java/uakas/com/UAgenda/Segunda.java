package uakas.com.UAgenda;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Entidades.Contato;

public class Segunda extends AppCompatActivity {

    private ImageView foto;
    private EditText nome;
    private EditText telefone;
    private EditText email;
    private EditText endereco;

    Contato contato;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        nome=findViewById(R.id.etNome);
        telefone=findViewById(R.id.etTelefone);
        email=findViewById(R.id.etEmail);
        endereco=findViewById(R.id.etEndereco);
        foto=findViewById(R.id.ivFoto);
        Intent it = getIntent();

        /*if (it.getSerializableExtra("Contato")!=null){
            contato= (Contato) it.getSerializableExtra("Contato");
            nome.setText(contato.getNome());
            telefone.setText(contato.getTelefone());
            email.setText(contato.getEmail());
            endereco.setText(contato.getEndereco());
            if(contato.getFoto()!=null){
                foto.setImageBitmap(contato.getFoto());
            }
            else {
                contato = new Contato();
            }
        }*/
    }
}

package uakas.com.UAgenda;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CameraMetadata;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.ByteArrayOutputStream;

import Entidades.Contato;
import crud.Dados;

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

        if (it.getSerializableExtra("Contato")!=null){
            contato= (Contato) it.getSerializableExtra("Contato");
            nome.setText(contato.getNome());
            telefone.setText(contato.getTelefone());
            email.setText(contato.getEmail());
            endereco.setText(contato.getEndereco());

            if(contato.getFoto()!=null){
                foto.setImageBitmap(BitmapFactory.decodeByteArray(contato.getFoto(), 0,contato.getFoto().length));
            }
        }
        else {
            contato = new Contato();
        }
    }
    public void cancelar(View view){
        setResult(RESULT_CANCELED);
        onBackPressed();
    }
    public void salvar(View view){
        contato.setNome(nome.getText().toString());
        contato.setNome(telefone.getText().toString());
        contato.setNome(email.getText().toString());
        contato.setNome(endereco.getText().toString());
        Dados.salvar(contato);
        setResult(RESULT_OK);
        finish();
    }
    public void capturaImg(View view){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(it.resolveActivity(getPackageManager())!=null)
            startActivityForResult(it, CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE);
    }

    /*public void importa(View view){

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CameraMetadata.REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE){
            if(resultCode==RESULT_OK){
                Bitmap img=(Bitmap) data.getExtras().get("data");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.PNG,100,stream);
                contato.setFoto(stream.toByteArray());
                foto.setImageBitmap(img);
            }
        }
    }
}

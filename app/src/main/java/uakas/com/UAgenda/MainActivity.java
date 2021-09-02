package uakas.com.UAgenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import Entidades.Contato;
import crud.Dados;

public class MainActivity extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id._dynamic); //Oque faz aki? e porque esse lista vermelho?
        atualiza(); //atualizar a lista

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(),Segunda.class);
                it.putExtra("Contato", (Contato) Dados.getLista().get(i));//oque está acontecendo que ele nao está pegando numero inteiro?

                Contato c=(Contato)Dados.getLista().get(i);
                startActivityForResult(it,201);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Dados.remove(i);
                atualiza();
                return true;
            }
        });

    }

    private void atualiza(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,Dados.getLista());
        lista.setAdapter(adapter);
    }

    public void addContato(View view) {
        Intent it = new Intent(this, Segunda.class);
        startActivityForResult(it,0,null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==201){
            if (requestCode==RESULT_OK){
                Toast.makeText(this,"Salvo",Toast.LENGTH_SHORT).show();//mostra uma pequena mensagem de que foi salvo
            }
            else{
                Toast.makeText(this,"Saiu",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
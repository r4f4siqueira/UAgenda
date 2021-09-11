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
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = findViewById(R.id.lista); //Oque faz aki? e porque esse lista vermelho?
        //estava vermelho pq ele ia na main(tela princiipal) buscar algo chamado lista mas nao existia essa tal de lista
        atualiza(); //atualizar a lista

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(),Segunda.class);
                it.putExtra("Contato",(Contato)Dados.getLista().get(i));//oque está acontecendo que ele nao está pegando numero inteiro?

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
        if(adapter==null){
            adapter= new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,Dados.getLista());
            lista.setAdapter(adapter);
        }
        else {
            adapter.notifyDataSetChanged();
        }
    }

    public void addContato(View view) {
        Intent it = new Intent(this, Segunda.class);
        startActivityForResult(it,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if (requestCode==RESULT_OK){
                atualiza();
                Toast.makeText(this,"Salvo",Toast.LENGTH_SHORT).show();//mostra uma pequena mensagem de que foi salvo
            }
            else{
                Toast.makeText(this,"Saiu",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
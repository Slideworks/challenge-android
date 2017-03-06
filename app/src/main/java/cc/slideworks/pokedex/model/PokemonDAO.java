package cc.slideworks.pokedex.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

import cc.slideworks.pokedex.Pokemon;

public class PokemonDAO extends SQLiteOpenHelper {

    public PokemonDAO(Context context) {
        super(context, "Pokedex", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE pokemon (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, caminho TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS pokemon;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(String nome, String caminho) {

        if(existe(nome) != 0){

            altera(nome, caminho, existe(nome));

        }else{
            SQLiteDatabase db = getWritableDatabase();

            ContentValues dados = new ContentValues();

            dados.put("nome", nome);
            dados.put("caminho", caminho);

            db.insert("pokemon", null, dados);
        }

    }
    public void altera(String nome, String caminho, int id){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome", nome);
        dados.put("caminho", caminho);

        String idstring = Integer.toString(id);

        String[] parms = {idstring};

        db.update("pokemon", dados, "id = ?", parms);
    }

    private int existe(String nome){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT nome FROM pokemon WHERE nome=? LIMIT 1;";
        Cursor c = db.rawQuery(sql, new String[]{nome});
        //int quantidade = c.getCount();

        int id = 0;

        while(c.moveToNext()){
            id = c.getColumnIndex("id");
        }
        c.close();

        return id;
    }

    public List<Pokemon> buscaPokemons() {

        String sql = "SELECT * FROM pokemon ORDER BY nome ASC;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Pokemon> pokemons = new ArrayList<Pokemon>();

        while(c.moveToNext()){
            Pokemon pokemon = new Pokemon();
            pokemon.setId(c.getInt(c.getColumnIndex("id")));
            pokemon.setName(c.getString(c.getColumnIndex("nome")));
            pokemon.setResource_uri(c.getString(c.getColumnIndex("caminho")));
            pokemons.add(pokemon);
        }
        c.close();

        return pokemons;
    }

    public List<Pokemon> buscaPokemonsQuery(String busca) {

        String sql = "SELECT * FROM pokemon WHERE nome LIKE ? ORDER BY nome ASC;";
        SQLiteDatabase db = getReadableDatabase();
        String like = "%"+busca+"%";
        Cursor c = db.rawQuery(sql, new String[]{like});

        List<Pokemon> pokemons = new ArrayList<Pokemon>();

        while(c.moveToNext()){
            Pokemon pokemon = new Pokemon();
            pokemon.setId(c.getInt(c.getColumnIndex("id")));
            pokemon.setName(c.getString(c.getColumnIndex("nome")));
            pokemon.setResource_uri(c.getString(c.getColumnIndex("caminho")));
            pokemons.add(pokemon);
        }
        c.close();

        return pokemons;
    }

}

package cc.slideworks.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;

import java.util.List;

import cc.slideworks.pokedex.Pokemon;
import cc.slideworks.pokedex.R;

public class PokemonsAdapter extends BaseAdapter{

    private final List<Pokemon> pokes;
    private final Context context;

    public PokemonsAdapter(Context context, List<Pokemon> pokes) {
        this.pokes = pokes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pokes.size();
    }

    @Override
    public Object getItem(int position) {
        return pokes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pokes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lista_pokemons, null);

        TextView nome = (TextView) view.findViewById(R.id.nome);
        nome.setText(pokes.get(position).toString());

        return view;
    }
}

package br.unisanta.firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class ClienteAdapter(
    private val lista: List<Cliente>
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    class ClienteViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val nome: TextView = view.findViewById(R.id.txt_Nome)
        val cpf: TextView = view.findViewById(R.id.txt_Cpf)
        val preco: TextView = view.findViewById(R.id.txt_Preco)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClienteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)

        return ClienteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ClienteViewHolder,
        position: Int
    ) {

        val cliente = lista[position]

        holder.nome.text = cliente.nome
        holder.cpf.text = cliente.cpf
        holder.preco.text = cliente.preco

    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
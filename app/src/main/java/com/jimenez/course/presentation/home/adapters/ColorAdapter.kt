package com.jimenez.course.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimenez.course.R
import com.jimenez.course.presentation.components.buttons.ActionButtonRectangle
import com.jimenez.course.presentation.core.callbacks.OnItemClickListener
import com.jimenez.course.presentation.home.model.Color

class ColorAdapter(
    private val colorList: List<Color>,
    private val onItemClickListener: OnItemClickListener<Color>
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    /** crear la vista del item que mostraremos en el RecyclerView */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    /** Union entre los datos de la lista y los componentes a mostrar */
    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colorList[position]

        /** implementacion sin custom view */

        /*holder.colorTextView.text = color.name
        color.description?.let {
            holder.descriptionTextView.text = color.description
            holder.descriptionTextView.visibility = View.VISIBLE
        }
        holder.container.setOnClickListener {
            onItemClickListener.onItemClick(color)
        }
        holder.trashImageView.setOnClickListener {
            onItemClickListener.onItemClick(color, "delete")
        }
        */

        /** implementacion sin custom view */
        color.name?.let { title ->
            holder.cardColor.setTextTitle(title)
        }
        color.hex?.let { colorCard ->
            holder.cardColor.setImageBackgroundTintColor(colorCard)
        }

        holder.cardColor.setOnClickListener {
            onItemClickListener.onItemClick(color)
        }

    }

    /** tamaño de nuestro arreglo o lista */
    override fun getItemCount(): Int = colorList.size

    /** crea instancias de cada componente del item a mostrar */
    class ColorViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        /** implementacion sin custom view */
        /*val container: ConstraintLayout = view.findViewById(R.id.container)
        val colorTextView: TextView = view.findViewById(R.id.colorTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val trashImageView: ImageView = view.findViewById(R.id.trashImageView)*/

        /** implementacion con custom view */
        val cardColor: ActionButtonRectangle = view.findViewById(R.id.cardColor)
    }

}
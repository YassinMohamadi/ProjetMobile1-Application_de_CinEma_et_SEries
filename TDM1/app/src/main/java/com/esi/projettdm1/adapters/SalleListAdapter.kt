package com.esi.projettdm1.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.esi.projettdm1.MovieDetailsActivity
import com.esi.projettdm1.R
import com.esi.projettdm1.SalleDetailsActivity
import com.esi.projettdm1.data.Movie
import com.esi.projettdm1.data.Salle
import com.esi.projettdm1.utils.FontChanger
import com.squareup.picasso.Picasso

class SalleListAdapter(private val salleList: List<Salle>, internal var context: Context) : RecyclerView.Adapter<SalleListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var salleNameTV: TextView
        var salleMovie: TextView

        var salleDetailsCV: CardView
        var sallePosterCardCV: CardView
        var posterIV: ImageView

        init {

            salleNameTV = view.findViewById(R.id.salleName)
            salleMovie = view.findViewById(R.id.salleMovie)

            posterIV = view.findViewById(R.id.sallePosterIV)


            salleDetailsCV = view.findViewById(R.id.salleDetailCard)
            sallePosterCardCV = view.findViewById(R.id.sallePosterCard)

            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.salle_column_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val salle = salleList[position]

        holder.salleNameTV.setText(salle.name)

        holder.salleMovie.text = salle.movie

        Picasso.with(context).load(salle.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, SalleDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)


            transitionPairs[0] = Pair.create(holder.sallePosterCardCV as View, holder.sallePosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.salleDetailsCV as View, holder.salleDetailsCV.transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }

        holder.salleDetailsCV.setOnClickListener {
            val intent = Intent(context, SalleDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            transitionPairs[0] = Pair.create(holder.sallePosterCardCV as View, holder.sallePosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.salleDetailsCV as View, holder.salleDetailsCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return salleList.size
    }


}


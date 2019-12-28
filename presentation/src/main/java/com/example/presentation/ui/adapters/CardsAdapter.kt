package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.PokemonCard
import com.example.domain.gateways.CacheGateway
import com.example.presentation.App
import com.example.presentation.R
import com.example.presentation.ui.scenes.all_cards.AllCardsFragmentDirections
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_card.view.*
import javax.inject.Inject

class CardsAdapter(private val makeToast: (message: String) -> Unit) :
    RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    var pokemonCards = listOf<PokemonCard>()

    @Inject
    lateinit var cacheGateway: CacheGateway

    init {
        App.appComponent.inject(this)
    }

    fun addCards(cards: List<PokemonCard>) {
        renderNewList(cards)
    }

    fun clear() {
        renderNewList(emptyList())
    }

    private fun renderNewList(newList: List<PokemonCard>) {
//todo эта хуета не работает, извините я и так потратил ан это часа 2
// val diffResult = DiffUtil.calculateDiff(CardsDiffUtil(pokemonCards, newList))
        if (newList.isEmpty()) {
            pokemonCards = emptyList()
        }
        pokemonCards = pokemonCards + newList
        notifyDataSetChanged()
//todo вместе с этой )))))))
// diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return pokemonCards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_card, parent, false)
        return CardsViewHolder(view, makeToast)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(pokemonCards[position])
    }


    inner class CardsViewHolder(
        view: View,
        private val makeToast: (message: String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(card: PokemonCard) {
            val imageView = itemView.cardImageView
            Glide.with(itemView)
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_pokeball)
                .into(imageView)

            val checkBox = itemView.favouriteCheckBox
            checkBox.isChecked = card.isFavorite == 1

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cacheGateway.updateCard(card.id, 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete {
                            makeToast.invoke("Добавлено в избранное")
                        }
                        .doOnError {
                            makeToast.invoke("Ошибка при добавлении в избранное")
                        }
                        .subscribe()
                } else {
                    cacheGateway.updateCard(card.id, 0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete {
                            makeToast.invoke("Удалено из избранного")
                        }
                        .doOnError {
                            makeToast.invoke("Ошибка при удалении из избарнного")
                        }
                        .subscribe()
                }
            }
            imageView.setOnClickListener {
                val action = AllCardsFragmentDirections.actionAllCardsToFragmentDetailedCard(card.id)
                it.findNavController().navigate(action)
            }
        }
    }
}
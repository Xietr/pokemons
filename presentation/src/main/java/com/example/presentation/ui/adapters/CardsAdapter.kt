package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.PokemonCard
import com.example.domain.gateways.CacheGateway
import com.example.presentation.App
import com.example.presentation.R
import com.example.presentation.ui.utils.CardsDiffUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.item_card.view.*
import javax.inject.Inject

class CardsAdapter(
    private val makeToast: (message: String) -> Unit,
    private val navigateToDetailedCard: (id: String) -> Unit
) : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    var pokemonCards = listOf<PokemonCard>()

    @Inject
    lateinit var cacheGateway: CacheGateway

    init {
        App.appComponent.inject(this)
    }

    fun clear() {
        renderNewList(emptyList())
    }

    fun setCards(cards: List<PokemonCard>) {
        renderNewList(cards)
    }

    private fun renderNewList(newList: List<PokemonCard>) {
        val diffCallback = CardsDiffUtil(pokemonCards, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        if (newList.isEmpty()) {
            pokemonCards = emptyList()
        }
        pokemonCards = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return pokemonCards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_card, parent, false)
        return CardsViewHolder(view, makeToast, navigateToDetailedCard)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.bind(pokemonCards[position])
    }


    inner class CardsViewHolder(
        view: View,
        private val makeToast: (message: String) -> Unit,
        private val navigateToDetailedCard: (id: String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(card: PokemonCard) {
            val imageView = itemView.cardImageView
            Glide.with(itemView)
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_pokeball)
                .into(imageView)

            val checkBox = itemView.favouriteCheckBox

            checkBox.setOnCheckedChangeListener(null)

            val compositeDisposable = CompositeDisposable()
            cacheGateway.getCardById(card.id)
                .subscribeOn(Schedulers.io())
                .doFinally { compositeDisposable.dispose() }
                .subscribe({
                    checkBox.isChecked = (card.isFavorite == 1) or (it.isFavorite == 1)
                    setupOnCheckListener(checkBox, card.id)
                }, {}).addTo(compositeDisposable)

            imageView.setOnClickListener {
                navigateToDetailedCard.invoke(card.id)
            }
        }

        private fun setupOnCheckListener(checkBox: CheckBox, id: String) {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    cacheGateway.updateCard(id, 1)
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
                    cacheGateway.updateCard(id, 0)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete {
                            makeToast.invoke("Удалено из избранного")
                        }
                        .doOnError {
                            makeToast.invoke("Ошибка при удалении из избранного")
                        }
                        .subscribe()
                }
            }
        }
    }
}
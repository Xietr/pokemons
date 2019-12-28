package com.example.presentation.ui.scenes.detailed_card

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.domain.entities.PokemonCard
import com.example.presentation.App
import com.example.presentation.R
import com.example.presentation.ui.scenes.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detailed_card.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter


class DetailedCardFragment : MvpAppCompatFragment(R.layout.fragment_detailed_card),
    DetailedCardView {

    @InjectPresenter
    lateinit var presenter: DetailedCardPresenter

    @ProvidePresenter
    fun provideDetailedCard() = App.appComponent.provideDetailedCardPresenter()

    val args: DetailedCardFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id
        presenter.getCard(id)
        (activity as MainActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).supportActionBar?.show()
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun showAllInfo(pokemonCard: PokemonCard) {
        Picasso.get()
            .load(pokemonCard.imageUrlHiRes)
            .placeholder(R.drawable.ic_pokeball)
            .into(detailedImageView)

        pokemonCard.apply {
            setText(name, nameTextView, "Имя : ")
            setText(rarity, rarityTextView, "Редкость : ")
            setText(types?.joinToString(), typeTextView, "Типы : ")
            setText(subtype, subtypeTextView, "Подтип : ")
            setText(hp, hpAmountTextView, "Количество здоровья : ")

            val strWithoutComma = attacks?.joinToString(separator = "\n")?.replace(",", ", \n")
            val strWithoutBracket = strWithoutComma?.replace("(", ": \n")?.replace(")", "\n")
            setText(strWithoutBracket?.replace("text=", ""), attackTypeTextView, "Типы атак : ")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setText(string: String?, view: TextView, preString: String) {
        if (string.isNullOrEmpty()) {
            view.visibility = View.GONE
        } else {
            view.text = preString + string
        }
    }
}
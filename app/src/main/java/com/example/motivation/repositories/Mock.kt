package com.example.motivation.repositories

import com.example.motivation.infra.MotivationConstants
import java.util.*

fun Int.random() : Int {
    return Random().nextInt(this)
}

data class Phrase(val text: String, val category: Int)

class Mock {
    private val all = MotivationConstants.PHRASES_FILTER.all
    private val morning = MotivationConstants.PHRASES_FILTER.morning
    private val happy = MotivationConstants.PHRASES_FILTER.happy

    private val mListPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", happy),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", happy),
        Phrase("Quando está mais escuro, vemos mais estrelas!", happy),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", happy),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", happy),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", happy),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", morning),
        Phrase("Você perde todas as chances que você não aproveita.", morning),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", morning),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", morning),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", morning),
        Phrase("Se você acredita, faz toda a diferença.", morning),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", morning)
    )

    fun getPhrase(filter: Int) : String {
        val phrases: List<Phrase>

        phrases = if (filter == all) {
            mListPhrases;
        } else {
            mListPhrases.filter { p -> p.category == filter}
        }

        val rand = (phrases.size).random()
        return phrases[rand].text
    }

}
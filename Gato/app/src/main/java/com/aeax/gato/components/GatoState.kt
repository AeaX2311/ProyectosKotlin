package com.aeax.gato.components

data class GatoState(
    val gatoArray: Array<Array<String>> = arrayOf( arrayOf("","",""), arrayOf("","",""), arrayOf("","","")),
    val player: String = "X",
    val winner: Char? = null,
    val isDraw: Boolean = false,
    val isGameOver: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GatoState

        if (!gatoArray.contentDeepEquals(other.gatoArray)) return false
        if (player != other.player) return false
        if (winner != other.winner) return false
        if (isDraw != other.isDraw) return false
        if (isGameOver != other.isGameOver) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gatoArray.contentDeepHashCode()
        result = 31 * result + player.hashCode()
        result = 31 * result + (winner?.hashCode() ?: 0)
        result = 31 * result + isDraw.hashCode()
        result = 31 * result + isGameOver.hashCode()
        return result
    }
}
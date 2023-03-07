package com.aeax.gato.models

data class Board(var player: Char = 'X', var gatoArray: Array<Array<String>> = arrayOf( arrayOf("","",""), arrayOf("","",""), arrayOf("","","") ))
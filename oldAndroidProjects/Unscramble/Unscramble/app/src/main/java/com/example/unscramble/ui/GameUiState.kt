package com.example.unscramble.ui

//In Android, StateFlow works well with classes that must maintain an observable immutable state.
data class GameUiState(
    val currentScrambledWord: String = "",
    val isGuessedWordWrong: Boolean = false,
    val score: Int = 0,
    val currentWordCount: Int = 1,
    val isGameOver: Boolean = false
)

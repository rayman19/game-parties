package com.example.game_parties.exceptions

import jakarta.persistence.EntityNotFoundException

object PartyNotFound  : EntityNotFoundException("ОШИБКА. Партия не найдена!")
object GameNotFound   : EntityNotFoundException("ОШИБКА. Игра не найдена!")
object PlayerNotFound : EntityNotFoundException("ОШИБКА. Игрок не найден!")
package models

import jakarta.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    @OneToMany(mappedBy = "user")
    val games: List<Game> = mutableListOf()
)

@Entity
data class Game(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val maxPlayers: Int,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)

@Entity
data class Party(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    @JoinColumn(name = "game_id")
    val game: Game,
    @ManyToMany
    @JoinTable(
        name = "party_users",
        joinColumns = [JoinColumn(name = "party_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    val users: List<User> = mutableListOf()
)
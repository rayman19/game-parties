Задача: "Поиск партии для кооперативной онлйан игры"

Функциональные требования: 
Есть три модели User/Game/Party.
У пользователя может быть несколько игр.
У каждой игры есть ограничение на количество игроков в одной партии. Оно всегда больше 2-х
Количество одновременных партий одной игры/разных игр не ограниченно
Пользоваель может быть только в одной партии в каждый момент времени
Над User можно делать следующие операции: добавить/удалить/изменить
Над Game можно делать следуюшие операции: добавить/удалить/изменить
Над Party можно делать следуюшие операции: добавить/удалить/добавить пользователя в партию/удалить пользователя
из партии
Должна быть возможность искать все доступные партии для пользователя
Должна быть возможность искать все доступные партии для пользователя с фильтром по конкретной игре.

Нефункциональные требования:
Фреймворк Spring Boot
Язык Kotlin
Система сборки Gradle + Kotin-Dsl
база данных in-mem h2
Интерфейс swagger (не обязательно, можн опросто рест-ручки)
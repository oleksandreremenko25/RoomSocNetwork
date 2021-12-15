package com.example.socnetwork.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Перед оголошенням класу анотуйте клас даних за допомогою @Entity.
// Ця анотація має кілька можливих аргументів. За замовчуванням (без аргументів до @Entity),
// ім'я таблиці буде таким же, як і класу. Але давайте використаємо корисну назву таблиці daily_sleep_quality_table.
// Цей аргумент на користь tableNameнеобов’язковий, але настійно рекомендований.
// Є кілька інших аргументів на користь @EntityВи можете дослідити в документації.
@Entity(tableName = "user_table")
data class User(
    // Щоб ідентифікувати nightId як первинний ключ, анотуйте nightId майно с @PrimaryKey.
    // Встановіть параметр autoGenerateдо true так що Room створює ідентифікатор для кожної сутності.
    // Це гарантує, що ідентифікатор кожної ночі є унікальним.

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "userId")
    var userId: Long? = 0L,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "name")
    var name: String,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "email")
    var email: String,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "hobby")
    var hobby: String,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "last_online")
    var lastOnline: String,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "photo")
    var photo: String,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "about")
    var about: String,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "following")
    var following: Int,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "followers")
    var followers: Int,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "posts")
    var posts: Int,

    // Налаштуйте імена властивостей за допомогою параметрів
    @ColumnInfo(name = "likes")
    var likes: Int) {

    init {
        createPhoto()
    }

    fun createPhoto() {
        if (photo.length == 0) {
            photo = "no"
        }
    }
}
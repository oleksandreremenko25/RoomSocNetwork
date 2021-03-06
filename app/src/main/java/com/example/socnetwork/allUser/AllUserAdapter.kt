package com.example.socnetwork.allUser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.socnetwork.database.User
import androidx.recyclerview.widget.ListAdapter
import com.example.socnetwork.databinding.UserShortBinding

class AllUserAdapter : ListAdapter<User, AllUserAdapter.MyViewHolder>(AllUserDiffCallback()) {

    // onBindViewHolder() - ця функція викликається RecyclerView щоб відобразити дані
    // для одного елемента списку у вказаному місці. Отже, onBindViewHolder() метод
    // приймає два аргументи: власник подання та позицію даних, які потрібно зв’язувати.
    // Для цього додатка тримач є TextItemViewHolder, а позиція — це позиція у списку
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    // myViewHolder - це один елемент який буде розмножений при створенні списку
    // parent: ViewGroup - це такби мовити контейнер куди буде записуватись оиночні елементи
    // короткого виводу User
    // position: Int - використовується коли більше одного типу елементів в списку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    class MyViewHolder private constructor(val binding: UserShortBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(oneUser: User) {
            // передаєм в xml файл одного юзера який тоді буде записаний по полям
            binding.user = oneUser
            itemView.id = oneUser.userId!!.toInt()
            binding.executePendingBindings()
        }

        companion object {
            // from()  - це функція яка має бути в супровідному об’єкті, щоб її можна
            // було викликати на ViewHolder клас, не викликаний на ViewHolder екземпляр.
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = UserShortBinding.inflate(layoutInflater, parent, false)

                return MyViewHolder(binding)
            }
        }

    }

}

class AllUserDiffCallback : DiffUtil.ItemCallback<User>() {
    // Перевіряє, чи передано обидва User предмети, oldItem та newItem, однакові.
    // Якщо  userId однакові, то поверніться true. В іншому випадку поверніться false.
    // DiffUtil використовує цей тест, щоб дізнатися,
    // чи був доданий, видалений чи переміщений елемент.
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    // Всередині areContentsTheSame(), перевіряється чи oldItem та newItem містять однакові дані,
    // тобто чи  вони рівні. Ця перевірка рівності перевірить усі поля, тому що User це клас даних.
    // Data класи автоматично визначають equals і кілька інших методів для вас. Якщо є відмінності
    // між oldItem та newItem, розповідає цей код DiffUtil що елемент оновлено.
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

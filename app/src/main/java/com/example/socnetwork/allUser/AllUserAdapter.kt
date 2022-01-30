package com.example.socnetwork.allUser

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.socnetwork.database.User
import com.example.socnetwork.R
import com.example.socnetwork.databinding.UserAllBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_short.view.*

class AllUserAdapter : RecyclerView.Adapter<AllUserAdapter.MyViewHolder>()  {
    // змінна для зберігання даних. Щоб розповісти RecyclerView коли дані, які він відображає,
    // змінилися, додайте спеціальний сетер до data змінної, яка знаходиться у верхній частині
    // AllUserAdapter класу. У сетері дайте data нове значення, а потім зателефонуйте notifyDataSetChanged()
    // щоб запустити перемалювання списку з новими даними.
    var data =  listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Файл RecyclerView потрібно знати, скільки елементів має адаптер для відображення,
    // і він робить це за допомогою виклику getItemCount()
    override fun getItemCount() = data.size

    // onBindViewHolder() - ця функція викликається RecyclerView щоб відобразити дані
    // для одного елемента списку у вказаному місці. Отже, onBindViewHolder() метод
    // приймає два аргументи: власник подання та позицію даних, які потрібно зв’язувати.
    // Для цього додатка тримач є TextItemViewHolder, а позиція — це позиція у списку
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]

        holder.bind(item)
    }

    // myViewHolder - це один елемент який буде розмножений при створенні списку
    // parent: ViewGroup - це такби мовити контейнер куди буде записуватись оиночні елементи
    // короткого виводу User
    // position: Int - використовується коли більше одного типу елементів в списку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    // View - це елемент який буде потім розмножений
    // для заповнення RecyclerView в файлі xml
    class MyViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(oneUser: User) {
            itemView.id = oneUser.userId!!.toInt()
            itemView.idUser.text = oneUser.userId.toString()
            Picasso.get().load(oneUser.photo).placeholder(R.drawable.no).into(itemView.photoUser);
            itemView.nameUser.text = oneUser.name
            itemView.lastOnlinelUser.text = oneUser.lastOnline
        }

        companion object {
            // from()  - це функція яка має бути в супровідному об’єкті, щоб її можна
            // було викликати на ViewHolder клас, не викликаний на ViewHolder екземпляр.
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                // вказуєм який layout (шаблон) використовувати для одного виводу User
                val view = layoutInflater.inflate(R.layout.user_short, parent, false)

                return MyViewHolder(view)
            }
        }

    }

}
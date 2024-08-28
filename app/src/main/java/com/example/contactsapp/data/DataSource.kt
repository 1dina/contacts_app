package com.example.contactsapp.data

import com.example.contactsapp.R
import com.example.contactsapp.model.Contact

class DataSource {
    fun getContactsList(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        contacts.add(Contact(R.drawable.auntie, R.string.auntie, R.string.auntie_num))
        contacts.add(Contact(R.drawable.brother, R.string.brother, R.string.brother_num))
        contacts.add(Contact(R.drawable.daughter, R.string.daughter, R.string.daughter_num))
        contacts.add(Contact(R.drawable.friend_1, R.string.friend_1, R.string.friend_1_num))
        contacts.add(Contact(R.drawable.friend_2, R.string.friend_2, R.string.friend_2_num))
        contacts.add(
            Contact(
                R.drawable.grandfather, R.string.grand_father,
                R.string.grand_father_num
            )
        )
        contacts.add(Contact(R.drawable.granny, R.string.granny, R.string.granny_num))
        contacts.add(Contact(R.drawable.neigbour, R.string.neighbour, R.string.neighbour_num))
        contacts.add(Contact(R.drawable.sister, R.string.sister, R.string.sister_num))
        contacts.add(Contact(R.drawable.son, R.string.son, R.string.son_num))
        contacts.add(Contact(R.drawable.uncle, R.string.uncle, R.string.uncle_num))

        return contacts
    }
}
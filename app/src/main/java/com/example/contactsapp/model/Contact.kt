package com.example.contactsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Contact(
    @DrawableRes val contactImage: Int,
    @StringRes val contactName: Int,
    @StringRes val contactNumber: Int
)
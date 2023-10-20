package com.example.clickbait.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Bait(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
)

package com.example.clickbait.data


import com.example.clickbait.R
import com.example.clickbait.model.Bait

object DataSource {
   val baits = listOf(
       Bait(
           imageRes = R.drawable.travel,
           nameRes = R.string.name1,
           descriptionRes = R.string.description1,
       ),
       Bait(
           imageRes = R.drawable.pet,
           nameRes = R.string.name2,
           descriptionRes = R.string.description2,
       ),
       Bait(
           imageRes = R.drawable.fantasy,
           nameRes = R.string.name3,
           descriptionRes = R.string.description3,
       ),
       Bait(
           imageRes = R.drawable.diy,
           nameRes = R.string.name4,
           descriptionRes = R.string.description4,
       ),
       Bait(
           imageRes = R.drawable.cartoons,
           nameRes = R.string.name5,
           descriptionRes = R.string.description5,
       ),
       Bait(
           imageRes = R.drawable.pizza,
           nameRes = R.string.name6,
           descriptionRes = R.string.description6,
       ),
       Bait(
           imageRes = R.drawable.celebrity,
           nameRes = R.string.name7,
           descriptionRes = R.string.description7,
       ),
       Bait(
           imageRes = R.drawable.junkfood,
           nameRes = R.string.name8,
           descriptionRes = R.string.description8,
       ),
       Bait(
           imageRes = R.drawable.soulmate,
           nameRes = R.string.name9,
           descriptionRes = R.string.description9,
    ),
       Bait(
           imageRes = R.drawable.lifehacks,
           nameRes = R.string.name10,
           descriptionRes = R.string.description10,
        ),
   )
}
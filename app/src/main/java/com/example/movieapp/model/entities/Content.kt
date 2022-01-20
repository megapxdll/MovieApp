package com.example.movieapp.model.entities
import android.media.Image
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    val title: String,
    val description: String
    //val icon: ImageView,
) : Parcelable

fun getWishContent() = listOf(
    Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past."),
            Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past."),
            Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past."),
            Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past."),
            Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past."),
            Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past.")
)
package com.example.movieapp.model.entities
import android.media.Image
import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content(
    val id: Int,
    val name: String
    //val icon: ImageView,
) : Parcelable

/*
fun getFilmContent() = listOf(
    Content("Scream", "Twenty-five years after a streak of brutal murders shocked the quiet town of Woodsboro, Calif., " +
            "a new killer dons the Ghostface mask and begins targeting a group of teenagers to resurrect secrets from the town's deadly past."),
            Content("THE HUNTING", "In a small town, " +
                    "Detective Connor Ryan investigates the brutal murder of a local man who was torn apart in the forest outside the quaint town. " +
                    "The crime scene reveals strange evidence of an unknown animal attack which leads Detective Ryan to consult with Maggie Talbot, " +
                    "a wolf expert and conservationist. While Connor and Maggie dive into the case they uncover disturbing information about werewolf lore. " +
                    "Maggie's past experience with wolves and her desire to find closure from her missing father moves her to believe the werewolf lore is real. " +
                    "Connor is not easily convinced. While the two continue to search for answers a group of college students plan a poorly timed social gathering in the forest. " +
                    "By the time Connor and Maggie close in on the truth it is too late for the students. " +
                    "But not everything is as it seems when a surprising twist leaves Connor and Maggie questioning everything they think they know."),
            Content("TAMING THE GARDEN", "A powerful man, who is also the former prime minister of Georgia, has developed an exquisite hobby. " +
                    "He collects century old trees along Georgia's coastline. He commissions his men to uproot them and bring them to his private garden. " +
                    "Some of these trees are as tall as 15-floor-buildings. And in order to transplant a tree of such dimensions some other trees are chopped down, " +
                    "electric cables are shifted and new roads are paved through mandarin plantations."),
            Content("STOP-ZEMLIA", "Introvert Masha sees herself as an outsider unless she's hanging out with her two best friends, Yana and Senia. " +
                    "While trying to navigate through her last year of school, Masha falls in love in a way that forces her out of her comfort zone."),
            Content("THE JACK IN THE BOX: AWAKENING", "Terminally ill heiress Olga Marsdale acquires a mysterious gothic box containing a captured demon -- Jack. " +
                    "The powerful entity within makes a deadly deal with Olga and her devoted son Edgar -- deliver six victims to Jack and Olga will live. " +
                    "They trap several unsuspecting victims for him within the vast crumbling mansion -- but can they deliver all six before it's too late? " +
                    "Or will Amy, the young and innocent woman recently hired to look after the estate turn out to be more than a match for both the family and the Jack?"),
            Content("SAUL AT NIGHT", "As a result of a bizarre experiment, Saul Capgras is forced to become acclimated to a life of isolation at night, " +
                    "while the rest of the city is restricted to sleep and a mandated curfew. Saul is the only person left awake at night, and, while still living with his wife and daughter, " +
                    "he must discover inventive ways to experience their lives.")
)

fun getSeriesContent() = listOf(
    Content("LOSING ALICE: SEASON 1", "Drama, Mystery thriller"),
    Content("SCENES FROM A MARRIAGE: LIMITED SERIES", "Other, Drama"),
    Content("THE IRREGULARS: SEASON 1", "Drama, Fantasy, Crime"),
    Content("THE CHAIR: SEASON 1", "Comedy, Drama"),
    Content("SERVANT: SEASON 2", "Drama, Mystery thriller"),
    Content("SHADOW AND BONE: SEASON 1", "Adventure, Action, Drama, Fantasy")
)

fun getWishListContent() = listOf(
    Content("Freaks", "Drama, Mystery thriller"),
    Content("Peacemaker", "Other, Drama"),
    Content("Teen Titans", "Drama, Fantasy, Crime"),
    Content("Dune", "Comedy, Drama")
)
*/
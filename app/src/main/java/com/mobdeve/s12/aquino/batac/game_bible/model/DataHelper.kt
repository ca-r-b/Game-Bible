package com.mobdeve.s12.aquino.batac.game_bible.model

import com.mobdeve.s12.aquino.batac.game_bible.R

class DataHelper {
    companion object {
        fun loadUserAccounts(): ArrayList<User>{
            val data = ArrayList<User>()

            data.add(
                User(
                    "testing",
                    "testing",
                    "Wow I look cool dang",
                    R.drawable.sample_1,
                    ArrayList<Game>()
                )
            )

            return data
        }

        fun loadData(): ArrayList<Game> {
            val data = ArrayList<Game>()

            data.add(
                Game(
                "Kingdom Hearts 3",
                    R.drawable.sample_1,
                    "KINGDOM HEARTS III tells the story of the power of friendship as Sora and his friends embark on a perilous adventure. Set in a vast array of Disney and Pixar worlds, KINGDOM HEARTS follows the journey of Sora, a young boy and unknowing heir to a spectacular power. Sora is joined by Donald Duck and Goofy to stop an evil force known as the Heartless from invading and overtaking the universe.",
                    "Action-Adventure",
                    "January 25, 2019",
                    "Square Enix Business Division 3",
                    "Square Enix",
                    "2VawSnaxtSI"
                )
            )

            data.add(
                Game(
                    "Hogwarts Legacy",
                    R.drawable.sample_2,
                    "Hogwarts Legacy is an immersive, open-world action RPG set in the world first introduced in the Harry Potter books. For the first time, experience Hogwarts in the 1800s. Your character is a student who holds the key to an ancient secret that threatens to tear the wizarding world apart. Now you can take control of the action and be at the center of your own adventure in the wizarding world. Your legacy is what you make of it.",
                    "Action-Adventure",
                    "February 10, 2023",
                    "Avalanche Software",
                    "Warner Bros. Games",
                    "BtyBjOW8sGY"
                )
            )

            data.add(
                Game(
                    "Forza Horizon 4",
                    R.drawable.sample_3,
                    "Forza Horizon 4 is a racing video game set in an open world environment based in a fictionalised Great Britain, with regions that include condensed representations of Edinburgh, the Scottish Highlands, the Lake District (including Derwentwater), Ambleside and the Cotswolds (including Broadway), Bamburgh among others.",
                    "Racing",
                    "September 28, 2018",
                    "Playground Games",
                    "Microsoft Studios",
                    "5xy4n73WOMM"
                )
            )

            return data
        }

//        fun isBookmarked(game: Game, user: User): Boolean {
//            for (i in user.saved){
//                if(i.title.contains(game.title)){
//                    return true
//                }
//            }
//
//            return false
//        }
    }
}
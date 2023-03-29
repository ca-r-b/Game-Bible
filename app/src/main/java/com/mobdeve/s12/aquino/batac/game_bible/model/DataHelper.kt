package com.mobdeve.s12.aquino.batac.game_bible.model

import com.mobdeve.s12.aquino.batac.game_bible.R

/* NOTE:
*     - This file (or the codes in this file) is subjective to change and is
*       being used MAINLY (for the meantime) for the sake of demonstration for functions.
* */

class DataHelper {
    companion object {
        fun loadUserAccounts(): ArrayList<User>{
            val data = ArrayList<User>()

            data.add(
                User(
                    "testing",
                    "testing",
                    "Wow I look cool",
                    R.drawable.sample_1,
//                    ArrayList<Game>()
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
                    "2VawSnaxtSI",
                    "Featured"
                )
            )

            data.add(
                Game(
                    "Tekken 7",
                    R.drawable.sample_2,
                    "TEKKEN 7 represents the final chapter of the 20-year-long Mishima feud. Unveil the epic ending to the emotionally charged family warfare between the members of the Mishima Clan as they struggle to settle old scores and wrestle for control of a global empire, putting the balance of the world in peril.",
                    "Fighting",
                    "February 18, 2015",
                    "Bandai Namco Studios Inc.",
                    "Bandai Namco Entertainment",
                    "kKLCwDg2JLA",
                    "Featured"
                )
            )

            data.add(
                Game(
                    "Alien Isolation",
                    R.drawable.sample_3,
                    "Alien: Isolation is a single-player game with an emphasis on stealth and survival horror. The player controls Amanda Ripley from a first-person perspective, and must explore a space station and complete objectives while avoiding, outsmarting, and defeating enemies.",
                    "Horror",
                    "October 6, 2014",
                    "Creative Assembly",
                    "Sega",
                    "7h0cgmvIrZw",
                    "Featured"
                )
            )

            data.add(
                Game(
                    "Forza Horizon 4",
                    R.drawable.sample_4,
                    "Forza Horizon 4 is a racing video game set in an open world environment based in a fictionalised Great Britain, with regions that include condensed representations of Edinburgh, the Scottish Highlands, the Lake District (including Derwentwater), Ambleside and the Cotswolds (including Broadway), Bamburgh among others.",
                    "Sports",
                    "September 28, 2018",
                    "Playground Games",
                    "Microsoft Studios",
                    "5xy4n73WOMM",
                    "Featured"
                )
            )

            data.add(
                Game(
                    "The Sims 4",
                    R.drawable.sample_5,
                    "The Sims 4 is a social simulation game, similar to preceding titles in the series. There is no primary objective or goal to achieve, and instead of fulfilling objectives, the player is encouraged to make choices and engage fully in an interactive environment.",
                    "Simulation",
                    "September 2, 2014",
                    "Maxis",
                    "Electronic Arts",
                    "GJENRAB4ykA",
                    "Featured"
                )
            )

            data.add(
                Game(
                    "Elden Ring",
                    R.drawable.sample_6,
                    "ELDEN RING features vast fantastical landscapes and shadowy, complex dungeons that are connected seamlessly. Traverse the breathtaking world on foot or on horseback, alone or online with other players, and fully immerse yourself in the grassy plains, suffocating swamps, spiraling mountains, foreboding castles and other sites of grandeur on a scale never seen before in a FromSoftware title.",
                    "Action-Adventure",
                    "February 25, 2022",
                    "FromSoftware",
                    "Bandai Namco Entertainment",
                    "AKXiKBnzpBQ",
                    "Discovery Queue"
                )
            )

            data.add(
                Game(
                    "The Quarry",
                    R.drawable.sample_7,
                    "The Quarry is a 2022 interactive drama horror video game developed by Supermassive Games and published by 2K. Players assume control of nine teenage counsellors who must survive their last night at Hackett's Quarry summer camp amongst supernatural creatures and violent locals.",
                    "Horror",
                    "June 10, 2022",
                    "Supermassive Games",
                    "2k",
                    "2Cst4YHdHXI",
                    "Discovery Queue"
                )
            )

            return data
        }

        fun searchSection(section: String, gameList: ArrayList<Game>): ArrayList<Game>{
            var newData = ArrayList<Game>()

            for(game in gameList){
                if(game.section == section){
                    newData.add(game)
                }
            }

            return newData
        }

//        TODO: Bookmark Functionality
//        fun isBookmarked(game: Game, user: User): Boolean {
//            for (i in user.saved){
//                if(i.title.contains(game.title)){
//                    return true
//                }
//            }
//
//            return false
//        }

//       TODO: Search Functionality - Current code is subject to change after DB application
        fun searchGame(input: String, currentData: ArrayList<Game>): ArrayList<Game>{
            val data = ArrayList<Game>()

//          Ignore case sensitivity
            var lowercasedInput = input.lowercase()
            var pattern = Regex(lowercasedInput)

            for(game in currentData){
                var lowercasedTitle = game.title.lowercase()
                
                if(pattern.containsMatchIn(lowercasedTitle)){
                    data.add(game)
                }
            }

            return data
        }

//       TODO: Spinner Functionality - Current code is subject to change after DB application
        fun searchGenre(input: String, currentData: ArrayList<Game>): ArrayList<Game>{
            val data = ArrayList<Game>()

            for(game in currentData){
                if(game.genre == input){
                    data.add(game)
                }
            }

            return data
        }
    }
}
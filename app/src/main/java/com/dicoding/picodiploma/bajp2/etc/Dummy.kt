package com.dicoding.picodiploma.bajp2.etc

import com.dicoding.picodiploma.bajp2.data.source.local.entity.MovieEntity
import com.dicoding.picodiploma.bajp2.data.source.local.entity.TvShowEntity
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.GenresItem
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.Movie
import com.dicoding.picodiploma.bajp2.data.source.remote.response.movie.MovieDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.Genre
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvDR
import com.dicoding.picodiploma.bajp2.data.source.remote.response.tv.TvShow

object Dummy {
    fun generateDummyMovies(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                "567189",
                "Tom Clancy's Without Remorse",
                "2021-04-29",
                109,
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "Action, Adventure, Thriller, War",
                "Released",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                false,
                7.3
            ),
            MovieEntity(
                "460465",
                "Mortal Kombat",
                "2021-04-07",
                110,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Action, Fantasy, Adventure",
                "Released",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                false,
                7.8
            ),
            MovieEntity(
                "399566",
                "Godzilla Collection",
                "2021-03-24",
                113,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "Science Fiction, Action, Drama",
                "Released",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                false,
                8.1
            )
        )
    }

    fun generateDummyDetailMovie(): MovieEntity {
        return  MovieEntity(
            "567189",
            "Tom Clancy's Without Remorse",
            "2021-04-29",
            109,
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "Action, Adventure, Thriller, War",
            "Released",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            false,
            7.3
        )
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                "60735",
                "The Flash",
                "2014-10-07",
                44,
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Action, Fantasy, Adventure, Science Fiction",
                "Returning Series",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                false,
                7.7
            ),
            TvShowEntity(
                "71712",
                "The Good Doctor",
                "2017-09-25",
                45,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "Drama",
                "Returning Series",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                false,
                8.6
            ),
            TvShowEntity(
                "88396",
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                50,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "Action, Fantasy, Adventure, Science Fiction",
                "Ended",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                false,
                7.9
            ),
        )
    }

    fun generateDummyDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            "60735",
            "The Flash",
            "2014-10-07",
            44,
            "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            "Action, Fantasy, Adventure, Science Fiction",
            "Returning Series",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            false,
            7.7
        )
    }

    fun generateRemoteDummyMovies(): List<Movie> {
        return listOf(
            Movie(
                567189,
                "Tom Clancy's Without Remorse",
                "2021-04-29",
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                7.3
            ),
            Movie(
                460465,
                "Mortal Kombat",
                "2021-04-07",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                7.8
            ),
            Movie(
                399566,
                "Godzilla Collection",
                "2021-03-24",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                8.1
            )
        )
    }

    fun generateRemoteDummyDetailMovie(): MovieDR {
        return MovieDR(
            567189,
            "Tom Clancy's Without Remorse",
            "2021-04-29",
            109,
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            listOf(
                GenresItem(name = "Action"),
                GenresItem(name = "Adventure"),
                GenresItem(name = "Thriller"),
                GenresItem(name = "War")
            ),
            "Released",
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            7.3
        )
    }

    fun generateRemoteDummyTvShows(): List<TvShow> {
        return listOf(
            TvShow(
                60735,
                "The Flash",
                "2014-10-07",
                "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                .7,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
            ),
            TvShow(
                71712,
                "The Good Doctor",
                "2017-09-25",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                8.6,
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives"
            ),
            TvShow(
                88396,
                "The Falcon and the Winter Soldier",
                "2021-03-19",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience."
            )

        )
    }

    fun generateRemoteDummyDetailTvShow(): TvDR {
        return TvDR(
            60735,
            "The Flash",
            "2014-10-07",
            listOf(44),
            "/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
            listOf(
                Genre(name = "Action"),
                Genre(name = "Fantasy"),
                Genre(name = "Adventure"),
                Genre(name = "Science Fiction"),
            ),
            "Returning Series",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            7.7
        )
    }
}
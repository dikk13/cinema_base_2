package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.mappers.MovieResponseDtoMapper;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.CharacterType;
import com.kata.cinema.base.models.enums.ShowType;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
public class MovieResponseDtoDaoImpl implements MovieResponseDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.MovieResponseDto (m.id, m.name, m.originalName, m.time, m.dateRelease, m.countries) " +
                "from Movie m where m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) "
                        + buildShowTypeQueryString((ShowType) parameters.get("showType")), MovieResponseDto.class)
                .setParameter("folder_movies_id", parameters.get("folderMovieId"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }


    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (long) entityManager.createQuery("select m.id from Movie m where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) "
                        + buildShowTypeQueryString((ShowType) parameters.get("showType")), Long.class)
                .setParameter("folder_movies_id", parameters.get("folderMovieId")).getResultList().size();
    }


        private String buildShowTypeQueryString (ShowType showType) {
        StringBuilder showTypeString = new StringBuilder();
        switch (showType) {

            case VIEWED -> showTypeString.append("and m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where ")
                    .append("fm.category = " + Category.VIEWED_MOVES.ordinal() + " and fm.user.id =")
                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id)) ");

            case NOT_VIEWED -> showTypeString.append("and m.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where ")
                    .append("fm.category = " + Category.VIEWED_MOVES.ordinal() + " and fm.user.id =")
                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id)) ");

            case ALL -> {
                return "";
            }
        }
        return showTypeString.toString();
    }


    @Override
    public Map<Long, List<String>> getGenresMap(String moviesId) {
        List <Object[]> testResult1 = entityManager.createQuery("select m.id, mg.name from Movie m join m.genres mg where m.id in " + moviesId)
                .getResultList();
        Map<Long, List<String>> genresMap = new HashMap<>();
        for (Object[] row : testResult1) {
            if (!genresMap.containsKey((Long) row[0])) {
                genresMap.put((Long) row[0], new ArrayList<>());
            }
            genresMap.get((Long) row[0]).add((String) row[1]);
        }
        return genresMap;
    }

    @Override
    public Map<Long, List<String>> getProducersMap(String moviesId) {
        List <Object[]> testResult2 = entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
                    + moviesId + " and mp.profession.name = 'Режиссер' and mp.typeCharacter =: characterType")
                .setParameter("characterType", String.valueOf(CharacterType.NO_CHARACTER_MOVIE.ordinal()))
                .getResultList();

            Map<Long, List<String>> producersMap = new HashMap<>();
            for (Object[] row : testResult2) {
                if (!producersMap.containsKey((Long) row[0])) {
                    producersMap.put((Long) row[0], new ArrayList<>());
                }
                producersMap.get((Long) row[0]).add(row[1] + " " + row[2]);
            }
            return producersMap;
    }

    @Override
    public Map<Long, List<String>> getActorsMap(String moviesId) {
        List <Object[]> testResult3 = entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
                        + moviesId + " and mp.profession.name = 'Актер' and mp.typeCharacter =: characterType")
                .setParameter("characterType", String.valueOf(CharacterType.MAIN_CHARACTER.ordinal()))
                .getResultList();

        Map<Long, List<String>> actorsMap = new HashMap<>();
        for (Object[] row : testResult3) {
            if (!actorsMap.containsKey((Long) row[0])) {
                actorsMap.put((Long) row[0], new ArrayList<>());
            }
            actorsMap.get((Long) row[0]).add(row[1] + " " + row[2]);
        }
        return actorsMap;
    }


//    @Override
//    public List<MovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
//        List<Long> moviesIdQueue = getMoviesIdQueue(currentPage, itemsOnPage, parameters);      //  1. получение id фильмов с учетом сортировки, фильтра и пагинации
//
//        if (moviesIdQueue.size() != 0) {
//        Map<Long, Movie> movieMap = new LinkedHashMap<>();
//
//        for (Long movieId: moviesIdQueue) {
//            movieMap.put(movieId, null);
//        }
//
//        List<Movie> movieList = getMoviesList(movieMap.keySet().toString());                        // 2. получение объектов Movie по id из п.1
//
//        for (Movie movie : movieList) {                                                             // 3. сортировка набора из п.2 в соответствии с порядком из п.1
//            movieMap.put(movie.getId(), movie);
//        }
//
//
//            /* --- Тестовая часть --- */
//
//            System.out.println("ТЕСТОВАЯ ЧАСТЬ");
//            // Получаем словарь с жанрами
//            Query testQuery1 = entityManager.createQuery("select m.id, mg.name from Movie m join m.genres mg where m.id in "
//                    + movieMap.keySet().toString().replace("[", "(").replace("]", ")"));
//
//            List <Object[]> testResult1 = testQuery1.getResultList();
//            Map<Long, List<String>> genresMap = new HashMap<>();
//            for (Object[] row : testResult1) {
//                if (!genresMap.containsKey((Long) row[0])) {
//                    genresMap.put((Long) row[0], new ArrayList<>());
//                }
//                genresMap.get((Long) row[0]).add((String) row[1]);
//            }
//            System.out.println("RESULT FOR GENRES: " + genresMap);
//
//            // Получаем словарь с режиссёрами
//            Query testQuery2 = entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
//                    + movieMap.keySet().toString().replace("[", "(").replace("]", ")") + " and mp.profession.name = 'Режиссер'");
//
//            List <Object[]> testResult2 = testQuery2.getResultList();
//            Map<Long, List<String>> producersMap = new HashMap<>();
//            for (Object[] row : testResult2) {
////                System.out.println(row[0] + " " + row[1] + " " + row[2]);
//                if (!producersMap.containsKey((Long) row[0])) {
//                    producersMap.put((Long) row[0], new ArrayList<>());
//                }
//                producersMap.get((Long) row[0]).add(row[1] + " " + row[2]);
//            }
//            System.out.println("RESULT FOR PRODUCERS: " + producersMap);
//
//
//            // Получаем словарь с актерами
//
//            //
//            Query testQuery3 = entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
//                            + movieMap.keySet().toString().replace("[", "(").replace("]", ")")
//                            + "  and mp.profession.name = 'Актер' and mp.typeCharacter =: characterType ")
//                    .setParameter("characterType", (String.valueOf(CharacterType.MAIN_CHARACTER.ordinal())));
//
//            List <Object[]> testResult3 = testQuery3.getResultList();
//            Map<Long, List<String>> actorsMap = new HashMap<>();
//            for (Object[] row : testResult3) {
////                System.out.println(row[0] + " " + row[1] + " " + row[2]);
//                if (!actorsMap.containsKey((Long) row[0])) {
//                    actorsMap.put((Long) row[0], new ArrayList<>());
//                }
//                actorsMap.get((Long) row[0]).add(row[1] + " " + row[2]);
//            }
//            System.out.println("RESULT FOR ACTORS: " + actorsMap);
//
//
//
//            /* --- Тестовая часть --- */
//
//
//        return movieResponseDtoMapper.mapListOfMoviesToDto(new ArrayList<>(movieMap.values()));     // 4. получение объектов MovieDto из п.3
//        }
//
//        return new ArrayList<>();                                                                   // если данные не получены - отдаем пустой список
//    }
//
//
//    @Override
//    public Long getResultTotal(Map<String, Object> parameters) {
//        return (long) entityManager.createQuery(buildQueryStringForQueueGeneration(parameters), Long.class)
//                .setParameter("folder_movies_id", parameters.get("folderMovieId")).getResultList().size();
//    }
//
//
//    private List <Long> getMoviesIdQueue (Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
//        TypedQuery <Long> query = entityManager.createQuery(buildQueryStringForQueueGeneration(parameters), Long.class)
//                .setParameter("folder_movies_id", parameters.get("folderMovieId"))
//                .setFirstResult((currentPage - 1) * itemsOnPage)
//                .setMaxResults(itemsOnPage);
//        return query.getResultList();
//    }
//
//    private String buildQueryStringForQueueGeneration (Map<String, Object> parameters) {
//        SortMovieFolderType sortMovieFolderType = (SortMovieFolderType) parameters.get("sortingType");
//        ShowType showType = (ShowType) parameters.get("showType");
//        StringBuilder resultedRequestString = new StringBuilder();
//        switch (sortMovieFolderType) {
//
//            case NAME -> resultedRequestString.append("select m.id from Movie m where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by m.name asc");
//
//            case ORIGINAL_NAME -> resultedRequestString.append("select m.id from Movie m where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by m.originalName asc");
//
//            case YEAR -> resultedRequestString.append("select m.id from Movie m where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by m.dateRelease asc");
//
//            case RATING -> resultedRequestString.append("select m.id from Movie m left outer join Score s on m.id = s.movie.id where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by avg (s.score) desc nulls last, m.id");
//
//            case COUNT_SCORE -> resultedRequestString.append("select m.id from Movie m left outer join Score s on m.id = s.movie.id where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by count (s.score) desc nulls last, m.id");
//
//            case MY_SCORE -> resultedRequestString.append("select m.id from Movie m left outer join m.scores mss on m.id = mss.movie.id and mss.user.id = ")
//                    .append("(select fm.user.id from FolderMovie fm where fm.id =: folder_movies_id) where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by avg (mss.score) desc nulls last, m.id");
//
//            case ORDER -> resultedRequestString.append("select m.id from Movie m where m.id in ")
//                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
//                    .append(buildShowTypeQueryString(showType))
//                    .append("group by m.id order by m.id asc");
//        }
//        return resultedRequestString.toString();
//    }
//
//    private String buildShowTypeQueryString (ShowType showType) {
//        StringBuilder showTypeString = new StringBuilder();
//        switch (showType) {
//
//            case VIEWED -> showTypeString.append("and m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = ")
//                    .append("(select fm.id from FolderMovie fm where fm.category = ")
//                    .append(Category.VIEWED_MOVES.ordinal())
//                    .append(" and fm.user.id = ")
//                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id))) ");
//
//            case NOT_VIEWED -> showTypeString.append("and m.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = ")
//                    .append("(select fm.id from FolderMovie fm where fm.category = ")
//                    .append(Category.VIEWED_MOVES.ordinal())
//                    .append(" and fm.user.id = ")
//                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id))) ");
//
//            case ALL -> {
//                return "";
//            }
//        }
//        return showTypeString.toString();
//    }
//
//    private List<Movie> getMoviesList(String moviesIdSet) {
//        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
//        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m where m.id in "
//                        + moviesIdSet.replace('[', '(').replace(']', ')'), Movie.class)
//                .setHint("javax.persistence.fetchgraph", entityGraph);
//        return query.getResultList();
//    }
}




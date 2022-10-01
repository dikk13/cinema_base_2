package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.models.*;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.ShowType;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.ToListResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
public class MovieResponseDaoImpl implements MovieResponseDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> getMovieListByFolderMovieId (
            Long folderMovieId,
            String sortMovieFolder,
            Integer pageNumber,
            Integer itemsOnPage,
            String showType) {
        System.out.println("try get movies for folderMovie " + folderMovieId);


        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m left join m.scores ms where m.id in " +
                        "(select fmm.id from FolderMovie fm left join fm.movies fmm where fm.id =: folder_movie_id " +
                        "and fmm.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = " +
                        "(select fm.id from FolderMovie fm where fm.category = 2 and fm.user.id = " +
                        "(select fm.user.id from FolderMovie fm where fm.id =: folder_movie_id)))) group by m.id order by count (ms.score) desc nulls last, m.id", Movie.class)
                .setParameter("folder_movie_id", folderMovieId);

        List<Movie> movieList = query.getResultList();

        for (Movie movie: movieList) {
            System.out.println(movie.getId() + " " + movie.getName());
        }




        System.out.println("test2");
        TypedQuery<Movie> query2 = entityManager.createQuery("select m from Movie m join m.scores mss on m.id = mss.movie.id and mss.user.id = 1L where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movie_id) order by mss.score desc nulls last, m.id", Movie.class)
                .setParameter("folder_movie_id", folderMovieId);

        List<Movie> movieList2 = query2.getResultList();

        for (Movie movie: movieList2) {
            System.out.println(movie.getId() + " " + movie.getName());
        }


        System.out.println("test3");
        try (Session session = entityManager.unwrap(Session.class)) {
            org.hibernate.query.Query query3 = entityManager.createQuery("select m.id as id, m.name as name, m_g.name as genre_name, m_p.person.id, m_p.person.firstName as person_Firstname, m_p.person.lastName as person_lastName " +
                    "from Movie m join m.genres m_g join m.moviePerson m_p where m.id in (1, 2, 3)").unwrap(org.hibernate.query.Query.class).setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map> answer = query3.list();
            for (Map map: answer
                 ) {
                System.out.println(map);
            }
        }

        System.out.println("test4");






//        System.out.println("test2");
//        TypedQuery<Movie> query2 = entityManager.createQuery("select m from Movie m left join m.scores mss on m.id = mss.movie.id and mss.user.id = " +
//                        "(select fm.user.id from FolderMovie fm where fm.id =: folder_movie_id) where m.id in " +
//                        "(select fmm.id from FolderMovie fm left join fm.movies fmm where fm.id =: folder_movie_id " +
//                        "and fmm.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = " +
//                        "(select fm.id from FolderMovie fm where fm.category = 2 and fm.user.id = " +
//                        "(select fm.user.id from FolderMovie fm where fm.id =: folder_movie_id)))) group by m.id order by mss.score desc, m.id", Movie.class)
//                .setParameter("folder_movie_id", folderMovieId);
//
//        List<Movie> movieList2 = query2.getResultList();
//
//        for (Movie movie: movieList2) {
//            System.out.println(movie.getId() + " " + movie.getName());
//        }

/*   --- ******************************************************8 ---   */


//        if (sortMovieFolder != null) {
//            switch (sortMovieFolder) {
//                case ("COUNT_SCORE"):
//                case ("RATING"):
//                    return getMovieListByFolderMovieId_postSorting(folderMovieId, sortMovieFolder, pageNumber, itemsOnPage, showType);
//            }
//        }
//
//        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
//        TypedQuery<Movie> query = entityManager
//                .createQuery("select m from Movie m " + sorted(sortMovieFolder, folderMovieId.toString())[0] +
//                        " where m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movie_id) "
//                        + showTypeSorted(showType)
//
////                        "and m.id not in " +
////                        "(select fm.id from FolderMovie fm where fm.category = 2 and fm.user.id = " +
////                        "(select fm.user.id from fm where fm.id =: folder_movie_id))) "
//
//                        + sorted(sortMovieFolder, folderMovieId.toString())[1], Movie.class)
//                .setParameter("folder_movie_id", folderMovieId)
//                .setHint("javax.persistence.fetchgraph", entityGraph)   // fetchgraph  или loadgraph
//                .setFirstResult((pageNumber - 1) * itemsOnPage)
//                .setMaxResults(itemsOnPage);
//
//        return query.getResultList();

        return null;
    }

    @Override
    public List<Movie> getMovieListByFolderMovieId_postSorting(Long folderMovieId, String sortMovieFolder, Integer pageNumber, Integer itemsOnPage, String showType) {

        /* -- 1. Получили и сохранили список очередности (!!!) id фильмов -- */

        System.out.println("вызвали метод");
        System.out.println("строка подзапроса");
        System.out.println(showTypeSorted(showType));

        Query queueOrderQuery = entityManager.createQuery("select m.id, m.name, " + chooseAgrFuncParams(sortMovieFolder) + " (s.score) as agregate_func from Movie m " +
                        "left outer join Score s on m.id = s.movie.id " +
                        "where m.id in " +
                        "(select fm_movies.id from FolderMovie fm join fm.movies fm_movies on m.id = fm_movies.id where fm.id =: folder_movie_id) "
                        + showTypeSorted(showType) +

//                        "and m.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = " +
//                        "(select fm.id from FolderMovie fm where fm.category = 2 and fm.user.id = " +
//                        "(select fm.user.id from fm where fm.id =: folder_movie_id))) " +

                        "group by m.id " +
                        "order by agregate_func desc nulls last, m.id")
                .setParameter("folder_movie_id", folderMovieId)
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);

        List<Object[]> queueOrder = queueOrderQuery.getResultList();

        /* -- 2. Получили объекты фильмов -- */

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
        TypedQuery<Movie> extractMoviesQuery = entityManager
                .createQuery("select m from Movie m where m.id in " + getIdQueue(queueOrder), Movie.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);   // fetchgraph  или loadgraph

        List<Movie> movieList = extractMoviesQuery.getResultList();

        /* -- 3. Отсортировали объекты фильмов в соответствии с порядком из п.1 -- */

        Map <Long, Movie> sortedMovieList = new LinkedHashMap<>();
        for (Object[] row: queueOrder) {
            sortedMovieList.put((Long) row[0], null);
        }

        for (Movie movie: movieList) {
            sortedMovieList.put(movie.getId(), movie);
        }

        return new ArrayList<>(sortedMovieList.values());
    }

    @Override
    public String[] sorted(String sortingParameters, String folderMovieId) {
        String[] requestParams = new String[2];
        if (sortingParameters != null) {
            switch (sortingParameters) {
                case ("NAME") -> {
                    requestParams[0] = "";
                    requestParams[1] = "order by m.name";
                    return requestParams;
                }
                case ("ORIGINAL_NAME") -> {
                    requestParams[0] = "";
                    requestParams[1] = "order by m.originalName";
                    return requestParams;
                }
                case ("YEAR") -> {
                    requestParams[0] = "";
                    requestParams[1] = "order by m.dateRelease";
                    return requestParams;
                }
                case ("MY_SCORE") -> {
                    requestParams[0] = "left join m.scores mss on m.id = mss.movie.id and mss.user.id= (select fm.user.id from FolderMovie fm where fm.id = " + folderMovieId + ")";
                    requestParams[1] = "order by mss.score desc nulls last, m.id";
                    return requestParams;
                }
            }
        }
        requestParams[0] = "";
        requestParams[1] = "order by m.id asc";
        return requestParams;
    }


    public String chooseAgrFuncParams(String sortingParameters) {
        return switch (sortingParameters) {
            case ("COUNT_SCORE") -> "count";
            case ("RATING") -> "avg";
            default -> sortingParameters;
        };
    }

    public String getIdQueue(List<Object[]> result) {
        System.out.println("размер списка: " + result.size());
        int i = 1;
        StringBuilder idString = new StringBuilder("(");
        for (Object[] row: result) {
            idString.append(row[0]);
            if (i < result.size()) {
                idString.append(", ");
                i++;
            }
        }
        idString.append(")");
        return idString.toString();
    }

    public String showTypeSorted (String showType) {
        return switch (showType) {
            case ("VIEWED") ->
                    "and m.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = " +
                            "(select fm.id from FolderMovie fm where fm.category = " + Category.VIEWED_MOVES.ordinal() + " and fm.user.id = " +
                            "(select fm.user.id from fm where fm.id =: folder_movie_id))) ";

            case ("NOT_VIEWED") ->
                    "and m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = " +
                            "(select fm.id from FolderMovie fm where fm.category = " + Category.VIEWED_MOVES.ordinal() + " and fm.user.id = " +
                            "(select fm.user.id from fm where fm.id =: folder_movie_id))) ";
            default -> "";
        };
    }
}




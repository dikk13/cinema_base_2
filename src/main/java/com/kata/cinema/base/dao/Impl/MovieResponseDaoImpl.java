package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.models.*;
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
            Integer itemsOnPage) {
        System.out.println("try get movies for folderMovie " + folderMovieId);


        /* --- РАБОЧИЙ КОД С НЕ РЕАЛИЗОВАННЫМИ СОРТИРОВКАМИ  COUNT_SCORE("Число оценок"), RATING ("Средняя оценка пользователями) ---*/

//        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
//        TypedQuery<Movie> query = entityManager
//                .createQuery("select m from Movie m left join m.scores mss on m.id = mss.movie.id and " +
//                        "mss.user.id= (select fm.user.id from FolderMovie fm where fm.id =: id) " +
//                        "where m.id in " +
//                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id) " + sorted(sortMovieFolder), Movie.class)
//                .setParameter("id", folderMovieId)
//                .setHint("javax.persistence.fetchgraph", entityGraph)   // fetchgraph  или loadgraph
//                .setFirstResult((pageNumber - 1) * itemsOnPage)
//                .setMaxResults(itemsOnPage);
//
//        return query.getResultList();


        /* --  -- */

        Query query = entityManager.createQuery("select m.id, m.name, count (s.score) as cnt from Movie m " +
                "left outer join Score s on m.id = s.movie.id " +
                "where m.id in " +
                "(select fm_movies.id from FolderMovie fm join fm.movies fm_movies on m.id = fm_movies.id where fm.id =: folder_movie_id)" +
                        "group by m.id " +
                        "order by cnt desc nulls last, m.id")
                .setParameter("folder_movie_id", folderMovieId)
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);

        List<Object[]> result = query.getResultList();
        for (Object[] row: result) {
            System.out.println(row[0] + " " + row[1]);
        }

        int i = 1;
        // Пробуем добавить очередность
        for (Object[] row: result) {
            row[2] = i;
            i++;
        }



        System.out.println("получили очередность");
        for (Object[] row: result) {
            System.out.println(row[2] + " " + row[0] + " " + row[1]);
        }

        System.out.println("Строка для id: " + getIdrow(result));

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
                TypedQuery<Movie> query2 = entityManager
                .createQuery("select m from Movie m where m.id in " + getIdrow(result), Movie.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);   // fetchgraph  или loadgraph


        List<Movie> movieList = query2.getResultList();
        System.out.println("Получаем результат");
        for (Movie movie: movieList) {
            System.out.println(movie.getId() + " " + movie.getName());
        }

        /*-- А теперь надо отсортировать по моему массиву --*/

        // Сделаем словарь, где ключи - это номер в очереди
        Map <Long, Movie> queue = new LinkedHashMap<>();
        for (Object[] row: result) {
            queue.put((Long) row[0], null);
        }

        System.out.println("ключи очереди: " + queue.keySet());

        for (Movie movie: movieList) {
            queue.put(movie.getId(), movie);
        }

        System.out.println("---------------------");
        for (Long item: queue.keySet()) {
            System.out.println(queue.get(item));
        }

        List<Movie> newList = new ArrayList<>(queue.values());
        System.out.println(newList);




        return newList;
    }

    @Override
    public String sorted(String sortingParameters) {
        if (sortingParameters != null) {
            switch (sortingParameters) {
                case ("NAME"):
                    return "order by m.name";
                case ("ORIGINAL_NAME"):
                    return "order by m.originalName";
                case ("YEAR"):
                    return "order by m.dateRelease";
                case ("RATING"):
                    return "order by m.mpaa";
                case ("MY_SCORE"):
                    return "order by mss.score desc nulls last";
                // Еще надо добавить :
                // COUNT_SCORE("Число оценок"),
                // RATING ("Средняя оценка пользователями)
            }
        }
        return "order by m.id asc";
    }

    @Override
    public String getIdrow(List<Object[]> result) {
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
}

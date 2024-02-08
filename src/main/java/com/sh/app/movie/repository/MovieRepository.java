package com.sh.app.movie.repository;

import com.sh.app.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
@Query(value = """
    select 
        *
    from(select 
            *
        from 
            movie
        order by 
            advance_reservation desc)
    where 
        rownum between 1 and 5
    """, nativeQuery = true)
    List<Movie> findFirst5ByOrderByAdvanceReservation();

    @Query("select m, g from Movie m join fetch m.genres g where g.genreList = :genreList")
    List<Movie> findByGenreList(String genreList);
}

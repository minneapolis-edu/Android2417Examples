package com.clara.roommovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.clara.roommovies.db.Movie;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListFragment.MovieListFragmentListener, AddMovieFragment.OnMovieAddedListener{

    private static final String TAG = "MAIN_ACTIVITY";

    private static final String TAG_MOVIE_LIST = "MovieListFragment";
    private static final String TAG_ADD_MOVIE = "AddMovieFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieListFragment movieListFragment = MovieListFragment.newInstance();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(android.R.id.content, movieListFragment, TAG_MOVIE_LIST);
        ft.commit();


        MovieViewModel mvm = new MovieViewModel(getApplication());

        // Uncomment to add some example movies to the DB
        /*

        Movie m1 = new Movie("Star Wars", 3.5f);
        Movie m2 = new Movie("Black Panther", 5);
        Movie m3 = new Movie("Back to the Future", 4);

        mvm.insert( m1, m2, m3 );
        */

        LiveData<List<Movie>> movieList = mvm.getAllMovies();

        // This doesn't do anything for the app, but can be helpful for debugging.
        movieList.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                Log.d(TAG, "movies: " + movies);
            }
        });
    }

    @Override
    public void onMovieAdded(Movie movie) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MovieListFragment movieListFragment = (MovieListFragment) fm.findFragmentByTag(TAG_MOVIE_LIST);
        if (movieListFragment != null) {
            ft.replace(android.R.id.content, movieListFragment);
            ft.commit();
        } else {
            Log.w(TAG, "Movie list fragment not found");
        }
    }

    @Override
    public void requestMakeNewMovie() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddMovieFragment addMovieFragment = AddMovieFragment.newInstance();
        ft.replace(android.R.id.content, addMovieFragment, TAG_ADD_MOVIE);
        ft.addToBackStack(null);
        ft.commit();
    }

}



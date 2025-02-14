/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jokeserver.Jokes.Service;

import java.util.Date;
import java.util.List;
import jokeserver.Jokes.Model.Jokes;

/**
 *
 * @author edoua
 */
public interface JokeService {
    boolean createJoke(String text, int authorId, Date date,List<Integer> categoryIDs);
    Jokes getJoke(int jokeId);
    List<Jokes> getAllJokes();
     boolean updateJoke(int jokeId, String newText, int authorId, Date newDate);
     boolean deleteJoke(int jokeId);
     List<Jokes> getJokesByCategory(String searchCategory);
}

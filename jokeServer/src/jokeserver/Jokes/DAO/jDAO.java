/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package jokeserver.Jokes.DAO;

import java.util.List;
import jokeserver.Jokes.Model.Jokes;

/**
 *
 * @author edoua
 */
public interface jDAO {
     boolean createJokes(Jokes joke, List<Integer> categoryIDs);
    Jokes getJoke(int JokeID);
    List<Jokes> getJokes();
    List<Jokes> getJokesCategory(String searchCategory);
    boolean updateJoke(Jokes joke);
    boolean deleteJoke(int jokeID);
}

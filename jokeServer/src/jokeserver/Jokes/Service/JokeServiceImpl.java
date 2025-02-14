/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.Jokes.Service;

import java.util.Date;
import java.util.List;
import jokeserver.Jokes.DAO.JokeDAO;
import jokeserver.Jokes.Model.Jokes;

/**
 *
 * @author edoua
 */
public class JokeServiceImpl implements JokeService {
    private JokeDAO jokeDAO;

    public JokeServiceImpl() {
        this.jokeDAO = new JokeDAO();
    }
    
    @Override
    public boolean createJoke(String text, int authorId,Date date,List<Integer> categoryIDs) {
        Jokes joke=new Jokes(text,authorId,date);
        return jokeDAO.createJokes(joke, categoryIDs);
    }

    @Override
    public Jokes getJoke(int jokeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Jokes> getAllJokes() {
        return jokeDAO.getJokes();
    }

    @Override
    public boolean updateJoke(int jokeId, String newText, int authorId, Date newDate) {
        Jokes updatedJoke = new Jokes(newText, authorId, newDate);
        updatedJoke.setId(jokeId);
        return jokeDAO.updateJoke(updatedJoke);
    }

    @Override
    public boolean deleteJoke(int jokeId ) {
        return jokeDAO.deleteJoke(jokeId);
    }

    @Override
    public List<Jokes> getJokesByCategory(String searchCategory) {
        return jokeDAO.getJokesCategory(searchCategory);
    }
   public boolean verifyJoke(int jokeId) {
    return jokeDAO.verifyJoke(jokeId);
}

public List<Jokes> getAllJokesIncludingUnverified() {
    return jokeDAO.getAllJokesIncludingUnverified();
} 
}

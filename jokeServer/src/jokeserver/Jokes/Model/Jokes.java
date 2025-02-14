/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jokeserver.Jokes.Model;

import java.util.Date;

/**
 *
 * @author edoua
 */
public class Jokes {
    private int id;
    private String jokeText;
    private int authorId;
    private int verified;
    private Date dateSelected;

    public Jokes( String jokeText, int authorId, int verified, Date dateSelected) {
        this.jokeText = jokeText;
        this.authorId = authorId;
        this.verified = verified;
        this.dateSelected = dateSelected;
    }

    public Jokes(String jokeText, int authorId, Date dateSelected) {
        this.jokeText = jokeText;
        this.authorId = authorId;
        this.dateSelected = dateSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public Date getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(Date dateSelected) {
        this.dateSelected = dateSelected;
    }
    
}

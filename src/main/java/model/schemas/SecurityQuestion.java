
package model.schemas;

import javax.persistence.*;

@Entity
@Table(name = "security_question")
public class SecurityQuestion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_question")
    private int id;
    @Column(name = "question")
    private String question;
    

    public SecurityQuestion(String question) {
        this.question = question;
    }

    public SecurityQuestion() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    
}

package pl.potoczak.cshoes.model.parameters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Size {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    int size;

    public Size() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

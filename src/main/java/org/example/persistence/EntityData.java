package org.example.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EntityData {
    @Id
    public int id;

    public int counter;
    public String text;

    public EntityData() { }
}

package Model;

import java.util.ArrayList;


public class List {
    private int id;
    private String name;
    private String description;
    //List<Item> items = new ArrayList<Item>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   /* public <any> getItems() {
        return items;
    }

    public void setItems(<any> items) {
        this.items = items;
    }*/
    
}

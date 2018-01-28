package sample.model;

/**
 * Created by mariabenetgarcia on 20.01.2018.
 */
public class Task {

    private String datecreated;
    private String description;
    private String task;

    public Task() {
    }

    public Task(String datecreated, String description, String task) {

        this.datecreated = datecreated;
        this.description = description;
        this.task = task;


    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }




}

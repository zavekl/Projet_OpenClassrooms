package com.cleanupBN.todoc.bdd;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanupBN.todoc.model.Task;

import java.util.List;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
@Dao
public interface MyDao {

    @Insert
    long addTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM task")
    List<Task> getTasks();
}

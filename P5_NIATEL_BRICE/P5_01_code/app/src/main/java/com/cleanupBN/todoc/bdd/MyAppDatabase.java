package com.cleanupBN.todoc.bdd;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cleanupBN.todoc.model.Task;

/**
 * Created by <Brice NIATEL> on <04/12/2019>.
 */
@Database(entities = {Task.class}, version = 1, exportSchema = false)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDao myDao();

}

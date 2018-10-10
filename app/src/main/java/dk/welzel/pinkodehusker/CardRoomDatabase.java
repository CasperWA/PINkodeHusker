package dk.welzel.pinkodehusker;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {PIN_Card.class}, version = 1)
public abstract class CardRoomDatabase extends RoomDatabase {

    //DAO's associated with Room
    public abstract CardDao cardDao();

    private static CardRoomDatabase INSTANCE;

    public static CardRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CardRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardRoomDatabase.class, "card_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CardDao mDao;

        PopulateDbAsync(CardRoomDatabase db) { mDao = db.cardDao(); }

        @Override
        protected Void doInBackground(Void... voids) {

            // Add test data
            mDao.deleteAll();

            PIN_Card visa = new PIN_Card();
            visa.id = 1;
            visa.cardName = "VISA";

            PIN_Card mc = new PIN_Card();
            mc.id = 2;
            mc.cardName = "MasterCard";

            mDao.insert(visa);
            mDao.insert(mc);

            return null;
        }
    }
}
package dk.welzel.pinkodehusker;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CardRepository {

    private CardDao mCardDao;
    private LiveData<List<PIN_Card>> mAllCards;

    public CardRepository(Application app) {
        CardRoomDatabase db = CardRoomDatabase.getDatabase(app);
        mCardDao = db.cardDao();
        mAllCards = mCardDao.getAllCards();
    }

    LiveData<List<PIN_Card>> getAllCards() { return mAllCards; }

    public void insert(PIN_Card card) { new insertAsyncTask(mCardDao).execute(card); }

    private static class insertAsyncTask extends AsyncTask<PIN_Card, Void, Void> {

        private CardDao mAsyncTaskDao;

        insertAsyncTask(CardDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PIN_Card... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

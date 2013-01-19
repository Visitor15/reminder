

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flabs.reminder.util.EnvironmentVariables;

public class DBManager extends SQLiteOpenHelper {

	private static final DBManager instance;
	private static Context mContext;
	
	public static final DBManager getInstance(Context c) {
		if(instance == null) {
			return new DBManager(c);
		}
		else {
			return instance;
		}
	}

	private DBManager(final Context context) {
		super(context, EnvironmentVariables.DATABASE.MASTER_DATABASE_NAME, null, EnvironmentVariables.DATABASE.MASTER_DATABASE_VERSION);
		mContext = context;
		instance = this;
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {

		db.execSQL(EnvironmentVariables.DATABASE.CREATE_MASTER_DATABASE_TABLE);


		if(db.isReadOnly()) {
			final SQLiteDatabase wdb = this.getWritableDatabase();
			//Whoever may need a writeable database gets it here.
			wdb.close();
		} else {
			//Initialize with the read only database.
		}

	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
	}

	@Override
	protected void finalize() throws Throwable {
		close();
	}
}

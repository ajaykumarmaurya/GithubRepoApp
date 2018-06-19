package com.example.android.githubrepolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android.githubrepolist.RepoData;

import java.util.ArrayList;
import java.util.HashSet;


public class DatabaseTableHelper {
	private static final String TAG = "DatabaseTableHelper";
	
	private static final String TABLE_NAME = "repo";


	private static final int MAX_ROW_NUM = 100;
	
	private static final String COLUMN_KEY_ROW_ID           = "id";
	private String name,link,description,size,watcher,issue,avatar,owner;

	//private static final String COLUMN_KEY_BUSINESS_URI     		 = "url";
	private static final String COLUMN_KEY_OWNER     			 = "owner";
	private static final String COLUMN_KEY_NAME   		 		 = "name";

	private static final String COLUMN_KEY_URL    				 = "url";
	private static final String COLUMN_KEY_AVATAR    			 = "avatar";
	private static final String COLUMN_KEY_DES    				 = "des";
	private static final String COLUMN_KEY_SIZE    		 		 = "size";
	private static final String COLUMN_KEY_WATCHER    			 = "watcher";
	private static final String COLUMN_KEY_ISSUE    		 	 = "issue";



	private SQLiteDatabase database;

	public DatabaseTableHelper(SQLiteDatabase database) {
		this.database = database;
	}

	public boolean insertRecord(RepoData dataRecord) {

		long status = 0;
		
		ContentValues values = new ContentValues();

		values.put(COLUMN_KEY_OWNER,      dataRecord.getOwner());
		values.put(COLUMN_KEY_NAME,      dataRecord.getName());
		values.put(COLUMN_KEY_URL,      dataRecord.getLink());



		values.put(COLUMN_KEY_AVATAR,      dataRecord.getAvatar());
		values.put(COLUMN_KEY_DES,      dataRecord.getDescription());
		values.put(COLUMN_KEY_SIZE,      dataRecord.getSize());

		values.put(COLUMN_KEY_WATCHER,      dataRecord.getWatcher());
		values.put(COLUMN_KEY_ISSUE,      dataRecord.getOpenIssue());









		if ((String.valueOf(dataRecord.getOwner()) != null) &&(String.valueOf(dataRecord.getName()) != null) &&
			(isRecordExists(dataRecord.getOwner(),dataRecord.getName()) == true)) {

			/*status = database.update(TABLE_NAME,
					values,
					COLUMN_KEY_OWNER + "=? AND "+ COLUMN_KEY_NAME + "=?",
					new String[] {dataRecord.getOwner(),dataRecord.getName()});
*/
		} else {

			status = database.insert(TABLE_NAME, null, values);
		}
		return (status > 0);
	}

	/*public boolean updateCampaignStatus(DataCampaignRecord dataCampaignRecord) {

		long status = 0;
		
		ContentValues values = new ContentValues();

		*//*
		COLUMN_KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COLUMN_KEY_APP_ID                   + " TEXT,"   +
				COLUMN_KEY_DOWNLOAD_ID              + " TEXT,"   +
				COLUMN_KEY_JSON_CONTENT             + " TEXT,"   +
				COLUMN_KEY_EXPIRY_COUNTER			+ " TEXT,"	 +
				COLUMN_KEY_RETRY_COUNTER			+ " TEXT,"	 +
				COLUMN_KEY_RECEIVE_STATUS           + " TEXT,"   +
				COLUMN_KEY_RECEIVE_VALUE            + " TEXT,"   +
				COLUMN_KEY_RECEIVE_TIME             + " TEXT,"   +
				COLUMN_KEY_CLICK_STATUS             + " TEXT,"   +
				COLUMN_KEY_CLICK_VALUE              + " TEXT,"   +
				COLUMN_KEY_CLICK_TIME               + " TEXT,"   +
				COLUMN_KEY_INSTALL_CLICK_STATUS     + " TEXT,"   +
				COLUMN_KEY_INSTALL_CLICK_VALUE      + " TEXT,"   +
				COLUMN_KEY_INSTALL_CLICK_TIME       + " TEXT,"   +
				COLUMN_KEY_OPEN_CLICK_STATUS        + " TEXT,"   +
				COLUMN_KEY_OPEN_CLICK_VALUE         + " TEXT,"   +
				COLUMN_KEY_OPEN_CLICK_TIME          + " TEXT,"   +
				COLUMN_KEY_CONVERT_STATUS           + " TEXT,"   +
				COLUMN_KEY_CONVERT_VALUE            + " TEXT,"   +
				COLUMN_KEY_CONVERT_TIME             + " TEXT,"   +
				COLUMN_KEY_PACKAGE_NAME             + " TEXT,"   +
				COLUMN_KEY_CUSTOM_PARAM_2           + " TEXT,"   +
				COLUMN_KEY_CUSTOM_PARAM_3           + " TEXT,"   +
				COLUMN_KEY_CREATION_DATE            + " INTEGER,"+
				COLUMN_KEY_DOWNLAOD_STATUS          + " TEXT," 	 +
				COLUMN_KEY_DOWNLAOD_VALUE           + " TEXT,"   +
				COLUMN_KEY_DOWNLOAD_TIME        	+ " TEXT"  	 +*//*

		
		values.put(COLUMN_KEY_APP_ID,      dataCampaignRecord.getAppId());
		values.put(COLUMN_KEY_DOWNLOAD_ID,      dataCampaignRecord.getDownloadId());



		values.put(COLUMN_KEY_EXPIRY_TIME,   dataCampaignRecord.getExpiryTime());
		values.put(COLUMN_KEY_RETRY_COUNTER,     dataCampaignRecord.getRetryCounter());

		values.put(COLUMN_KEY_RECEIVE_STATUS,   dataCampaignRecord.getRecvStatus());
		values.put(COLUMN_KEY_RECEIVE_VALUE,    dataCampaignRecord.getRecvValue());
		values.put(COLUMN_KEY_RECEIVE_TIME,     dataCampaignRecord.getRecvTime());
		values.put(COLUMN_KEY_CLICK_STATUS,     dataCampaignRecord.getNotificationClickedStatus());
		values.put(COLUMN_KEY_CLICK_VALUE,      dataCampaignRecord.getNotificationClickedValue());
		values.put(COLUMN_KEY_CLICK_TIME,       dataCampaignRecord.getNotificationClickedTime());

		values.put(COLUMN_KEY_INSTALL_CLICK_STATUS,     dataCampaignRecord.getInstallClickedStatus());
		values.put(COLUMN_KEY_INSTALL_CLICK_VALUE,      dataCampaignRecord.getInstallClickedValue());
		values.put(COLUMN_KEY_INSTALL_CLICK_TIME,       dataCampaignRecord.getInstallClickedTime());

		values.put(COLUMN_KEY_OPEN_CLICK_STATUS,     dataCampaignRecord.getOpenClickedStatus());
		values.put(COLUMN_KEY_OPEN_CLICK_VALUE,      dataCampaignRecord.getOpenClickedValue());
		values.put(COLUMN_KEY_OPEN_CLICK_TIME,       dataCampaignRecord.getOpenClickedTime());

		values.put(COLUMN_KEY_CONVERT_STATUS,   dataCampaignRecord.getConvertedStatus());
		values.put(COLUMN_KEY_CONVERT_VALUE,    dataCampaignRecord.getConvertedValue());
		values.put(COLUMN_KEY_CONVERT_TIME,     dataCampaignRecord.getConvertedTime());

		values.put(COLUMN_KEY_DOWNLAOD_STATUS, dataCampaignRecord.getDownloadStatus());
		values.put(COLUMN_KEY_DOWNLAOD_VALUE, dataCampaignRecord.getDownloadValue());
		values.put(COLUMN_KEY_DOWNLOAD_TIME,dataCampaignRecord.getDownloadedTime());
		values.put(COLUMN_KEY_PACKAGE_NAME,dataCampaignRecord.getPackageName());
		values.put(COLUMN_KEY_CUSTOM_PARAM_2,dataCampaignRecord.getCustomParam2());
		values.put(COLUMN_KEY_CUSTOM_PARAM_3,dataCampaignRecord.getCustomParam3());

		
		if ((String.valueOf(dataCampaignRecord.getAppId()) != null) &&
			(isCampaignExists(dataCampaignRecord.getAppId()) == true)) {
			dataCampaignRecord.Print();
			status = database.update(TABLE_NAME, 
						values,
					COLUMN_KEY_APP_ID  +"=?",
						new String[] {dataCampaignRecord.getAppId()});



		}
		
		return (status > 0);
	}*/
	
/*
	public boolean updateCustomParams(DataCampaignRecord dataCampaignRecord) {
		long status = 0;
		
		ContentValues values = new ContentValues();
		
		//values.put(COLUMN_KEY_APP_ID,    dataCampaignRecord.getAppId());

		values.put(COLUMN_KEY_PACKAGE_NAME, dataCampaignRecord.getPackageName());
		values.put(COLUMN_KEY_CUSTOM_PARAM_2, dataCampaignRecord.getCustomParam2());		
		values.put(COLUMN_KEY_CUSTOM_PARAM_3, dataCampaignRecord.getCustomParam3());
	
		
		if ((String.valueOf(dataCampaignRecord.getAppId()) != null) &&
			(isCampaignExists(dataCampaignRecord.getAppId()) == true)) {
			dataCampaignRecord.Print();
			status = database.update(TABLE_NAME, 
						values,
					      COLUMN_KEY_APP_ID + "=?",
						new String[] {dataCampaignRecord.getAppId()});
		}
		
		return (status > 0);		
	}
	
	public boolean updateCampaignDownloadId(DataCampaignRecord dataCampaignRecord) {

		long status = 0;
		
		ContentValues values = new ContentValues();
		


		values.put(COLUMN_KEY_DOWNLOAD_ID,      dataCampaignRecord.getDownloadId());
	    values.put(COLUMN_KEY_JSON_CONTENT,     dataCampaignRecord.getJsonContent());

		values.put(COLUMN_KEY_EXPIRY_TIME,   dataCampaignRecord.getExpiryTime());
		values.put(COLUMN_KEY_RETRY_COUNTER,     dataCampaignRecord.getRetryCounter());
		values.put(COLUMN_KEY_RECEIVE_STATUS,   dataCampaignRecord.getRecvStatus());
		values.put(COLUMN_KEY_RECEIVE_VALUE,    dataCampaignRecord.getRecvValue());
		values.put(COLUMN_KEY_RECEIVE_TIME,     dataCampaignRecord.getRecvTime());
		values.put(COLUMN_KEY_CLICK_STATUS,     dataCampaignRecord.getNotificationClickedStatus());
		values.put(COLUMN_KEY_CLICK_VALUE,      dataCampaignRecord.getNotificationClickedValue());
		values.put(COLUMN_KEY_CLICK_TIME,       dataCampaignRecord.getNotificationClickedTime());

		values.put(COLUMN_KEY_INSTALL_CLICK_STATUS,     dataCampaignRecord.getInstallClickedStatus());
		values.put(COLUMN_KEY_INSTALL_CLICK_VALUE,      dataCampaignRecord.getInstallClickedValue());
		values.put(COLUMN_KEY_INSTALL_CLICK_TIME,       dataCampaignRecord.getInstallClickedTime());

		values.put(COLUMN_KEY_OPEN_CLICK_STATUS,     dataCampaignRecord.getOpenClickedStatus());
		values.put(COLUMN_KEY_OPEN_CLICK_VALUE,      dataCampaignRecord.getOpenClickedValue());
		values.put(COLUMN_KEY_OPEN_CLICK_TIME,       dataCampaignRecord.getOpenClickedTime());

		values.put(COLUMN_KEY_CONVERT_STATUS,   dataCampaignRecord.getConvertedStatus());
		values.put(COLUMN_KEY_CONVERT_VALUE,    dataCampaignRecord.getConvertedValue());
		values.put(COLUMN_KEY_CONVERT_TIME,     dataCampaignRecord.getConvertedTime());

		values.put(COLUMN_KEY_DOWNLAOD_STATUS, dataCampaignRecord.getDownloadStatus());
		values.put(COLUMN_KEY_DOWNLAOD_VALUE, dataCampaignRecord.getDownloadValue());
		values.put(COLUMN_KEY_DOWNLOAD_TIME,dataCampaignRecord.getDownloadedTime());

		values.put(COLUMN_KEY_PACKAGE_NAME,dataCampaignRecord.getPackageName());
		values.put(COLUMN_KEY_CUSTOM_PARAM_2,dataCampaignRecord.getCustomParam2());
		values.put(COLUMN_KEY_CUSTOM_PARAM_3,dataCampaignRecord.getCustomParam3());
		
		if ((String.valueOf(dataCampaignRecord.getAppId()) != null) &&
			(isCampaignExists(dataCampaignRecord.getAppId()) == true)) {
			dataCampaignRecord.Print();
			status = database.update(TABLE_NAME,
					values,
					COLUMN_KEY_APP_ID + "=?" ,
					new String[] {dataCampaignRecord.getAppId()});
		} else {
//			if (getRecordCount() >= MAX_ROW_NUM) {
//				deleteLastRecord();
//			}	
//			values.put(COLUMN_KEY_CREATION_DATE, CommonUtils.getCurrentTime());			
//
//			dataCampaignRecord.Print();			
//			status = database.insert(TABLE_NAME, null, values);
		}
		
		return (status > 0);		
	}
*/

	public boolean deleteAllRecord() {
		int count = database.delete(TABLE_NAME, null, null);
		
		return (count > 0);
	}

	public int getRecordCount() {
		int recordCount = 0;
		
		Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		if (c != null) {
			recordCount = c.getCount();
			c.close();
		}
		return (recordCount);
	}



	
	public ArrayList<RepoData> getAllDataRecord(Context context) {
		ArrayList<RepoData> dataList = new ArrayList<RepoData>();
		
		String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_KEY_ROW_ID;

		Cursor c = database.rawQuery(query, null);
		
		if (c != null && c.moveToFirst()) {
			do {
				RepoData dataCampaignRecord = getDataRecord(c, context);
				dataList.add(dataCampaignRecord);
			} while (c.moveToNext());
			
			c.close();
		}

		return (dataList);
	}

	public HashSet<String> getOwnerDataRecord(Context context) {
		HashSet<String>  dataList = new HashSet<>();

		String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_KEY_ROW_ID;

		Cursor c = database.rawQuery(query, null);

		if (c != null && c.moveToFirst()) {
			do {
				RepoData dataCampaignRecord = getDataRecord(c, context);
				dataList.add(dataCampaignRecord.getOwner());
			} while (c.moveToNext());

			c.close();
		}

		return (dataList);
	}

	public ArrayList<RepoData> getAllRepoDataRecord(Context context,String owner) {
		ArrayList<RepoData> dataList = new ArrayList<RepoData>();
		
		Cursor c = database.query(TABLE_NAME,
				null,
				COLUMN_KEY_OWNER + "=?" ,
						new String[] {String.valueOf(owner)},
				null,
				null,
				null);
		
		if (c != null && c.moveToFirst()) {
			do {
				RepoData data = getDataRecord(c, context);
				dataList.add(data);
			} while (c.moveToNext());
			
			c.close();
		}

		return (dataList);
	}
	
	public RepoData getDataRecordByOwner(String owner,
			Context context) {

		RepoData data = null;
		
		try {
			Cursor c = database.query(TABLE_NAME,
							null,
					          COLUMN_KEY_OWNER + "=?" ,
							new String[] {owner.toLowerCase()},
							null,
							null,
							null);
			
			if (c != null && c.moveToFirst()) {
				data = getDataRecord(c, context);
				c.close();
			}
		}catch (Exception e) {
		}
		
	    return data;
	}


	/*public DataCampaignRecord getDataRecordByDownloadId(long downloadId, Context context) {

		DataCampaignRecord data = null;
        
		try {
			Cursor c = database.query(TABLE_NAME, 
							null,
							COLUMN_KEY_DOWNLOAD_ID + "=?",
							new String[] {String.valueOf(downloadId)},
							null,
							null,
							null);
			
			if (c != null && c.moveToFirst()) {
				data = getDataRecord(c, context);
				c.close();
			}
		}catch (Exception e) {
		}

		return data;
	}*/
	
	
	/*public DataCampaignRecord getDataRecordByCustomParams(String param1,
			String param2,
			String param3,
			Context context) {

		DataCampaignRecord data = null;
		
		try {
			String   selection     = null;		
			String[] selectionArgs = null;
			ArrayList<String> selectionArgsList = new ArrayList<String>(); 
			
			if (param1 != null) {
				selection = COLUMN_KEY_PACKAGE_NAME + "=?";
				selectionArgsList.add(param1);
			}
			
			if (param2 != null) {
				selection = selection + " AND " + COLUMN_KEY_CUSTOM_PARAM_2 + "=?";
				selectionArgsList.add(param2);				
			}
			
			if (param3 != null) {
				selection = selection + " AND " + COLUMN_KEY_CUSTOM_PARAM_3 + "=?";
				selectionArgsList.add(param3);				
			}
			
			if (selectionArgsList.size() > 0) {
				selectionArgs = new String[selectionArgsList.size()];
				for (int i = 0; i < selectionArgsList.size(); i++) {
					selectionArgs[i] = selectionArgsList.get(i);
				}
			}
			
		//	LogUtils.writeLogs(context, TAG, "getDataRecordByCustomParams: selection: " + selection + " selection args: " + selectionArgs, LogUtils.LOG_LEVEL_DEBUG);
			
			if (selection != null) {
				Cursor c = database.query(TABLE_NAME, 
						null,
						selection,
						selectionArgs,
						null,
						null,
						COLUMN_KEY_CREATION_DATE + " DESC");
		
				if (c != null && c.moveToFirst()) {
					data = getDataRecord(c, context);
					
					c.close();
				}				
			}
		}catch (Exception e) {
			//LogUtils.writeLogs(context, TAG, "getDataRecordByCustomParams: exception: " + e.getMessage(), LogUtils.LOG_LEVEL_DEBUG);
		}

		return data;
	}*/




	/*public DataCampaignRecord getDataRecordByPendingConversion(String param1,

														  Context context) {
		//+" AND " + COLUMN_KEY_RETRY_COUNTER + " < "+ COLUMN_KEY_EXPIRY_COUNTER

		DataCampaignRecord data = null;

		try {
			String   selection     = null;
			String[] selectionArgs = null;
			ArrayList<String> selectionArgsList = new ArrayList<String>();

			if (param1 != null) {
				selection = COLUMN_KEY_CONVERT_STATUS + "=?";
				selectionArgsList.add(param1);
			}



			if (selectionArgsList.size() > 0) {
				selectionArgs = new String[selectionArgsList.size()];
				for (int i = 0; i < selectionArgsList.size(); i++) {
					selectionArgs[i] = selectionArgsList.get(i);
				}
			}

			//	LogUtils.writeLogs(context, TAG, "getDataRecordByCustomParams: selection: " + selection + " selection args: " + selectionArgs, LogUtils.LOG_LEVEL_DEBUG);

			if (selection != null) {
				Cursor c = database.query(TABLE_NAME,
						null,
						selection,
						selectionArgs,
						null,
						null,
						COLUMN_KEY_CREATION_DATE + " DESC");

				if (c != null && c.moveToFirst()) {
					data = getDataRecord(c, context);

					c.close();
				}
			}
		}catch (Exception e) {
			//LogUtils.writeLogs(context, TAG, "getDataRecordByCustomParams: exception: " + e.getMessage(), LogUtils.LOG_LEVEL_DEBUG);
		}

		return data;
	}*/
	
	/*public DataCampaignRecord getLatestDataRecord(Context context) {

		DataCampaignRecord data = null;
		
		try {
			
				Cursor c = database.query(TABLE_NAME, 
						null,
						null,
						null,
						null,
						null,
						COLUMN_KEY_CREATION_DATE + " DESC",
						"1");
		
				if (c != null && c.moveToFirst()) {
					data = getDataRecord(c, context);
					
					c.close();
				}				
		}catch (Exception e) {
		//	LogUtils.writeLogs(context, TAG, "getLatestDataRecord: exception: " + e.getMessage(), LogUtils.LOG_LEVEL_DEBUG);
		}

		return data;
	}*/
	
	private RepoData getDataRecord(Cursor c, Context context) {
		RepoData dataRecord = new RepoData();

		//dataRecord.setRowId(c.getString(c.getColumnIndex(COLUMN_KEY_ROW_ID)));

		dataRecord.setOwner(c.getString(c.getColumnIndex(COLUMN_KEY_OWNER)));
		dataRecord.setName(c.getString(c.getColumnIndex(COLUMN_KEY_NAME)));
		dataRecord.setLink(c.getString(c.getColumnIndex(COLUMN_KEY_URL)));
		dataRecord.setAvatar(c.getString(c.getColumnIndex(COLUMN_KEY_AVATAR)));
		dataRecord.setDescription(c.getString(c.getColumnIndex(COLUMN_KEY_DES)));
		dataRecord.setSize(c.getString(c.getColumnIndex(COLUMN_KEY_SIZE)));
		dataRecord.setWatcher(c.getString(c.getColumnIndex(COLUMN_KEY_WATCHER)));
		dataRecord.setOpenIssue(c.getString(c.getColumnIndex(COLUMN_KEY_ISSUE)));



		return (dataRecord);
	}
	
	/*public boolean deleteRecord(String campaignId, String campaignTime, String pushId) {
		int status =
				database.delete(TABLE_NAME,
						COLUMn_BUSS + "=?",
						new String[] {campaignId, campaignTime, pushId});
		
		return (status > 0);
	}*/
	
	private void  deleteLastRecord(){
		database.delete(TABLE_NAME,
				COLUMN_KEY_ROW_ID + "=" + "(SELECT MIN(id) FROM " + TABLE_NAME + ")",
				null);
	}

	private boolean isRecordExists(String owner,String name) {
		boolean isCampaignExists = false;
		try {
			Cursor c = database.query(TABLE_NAME,
					null,
					COLUMN_KEY_OWNER + "= ? AND "+COLUMN_KEY_NAME + "= ?",
					new String[] {owner,name},
					null,
					null,
					null);

			if (c != null) {
				isCampaignExists = c.getCount() > 0;
				c.close();
			}
		}catch (Exception e){

		}


		
		return (isCampaignExists);
	}

	// ------------- STATIC FUNCTIONS ---------------
	
	public static void createTable(SQLiteDatabase db) {
		db.execSQL(getCreateTableQuery());
	}

	private static String getCreateTableQuery() {
		final String CREATE_TABLE_QUERY =
				"CREATE TABLE " + TABLE_NAME + 
				"(" +
					COLUMN_KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
						COLUMN_KEY_OWNER                  	 + " TEXT,"   +
						COLUMN_KEY_NAME                  	 + " TEXT,"   +
						COLUMN_KEY_URL                   	+ " TEXT,"   +
						COLUMN_KEY_AVATAR                   + " TEXT,"   +
						COLUMN_KEY_DES                   	+ " TEXT,"   +
						COLUMN_KEY_SIZE                  	 + " TEXT,"   +
						COLUMN_KEY_WATCHER                   + " TEXT,"   +
						COLUMN_KEY_ISSUE                  	 + " TEXT"   +

						")";

		return (CREATE_TABLE_QUERY);
	}


}

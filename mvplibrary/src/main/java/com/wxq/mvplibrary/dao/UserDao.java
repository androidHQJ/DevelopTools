package com.wxq.mvplibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wxq.mvplibrary.model.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ClassBlocked = new Property(1, String.class, "classBlocked", false, "CLASS_BLOCKED");
        public final static Property UserId = new Property(2, String.class, "userId", false, "USER_ID");
        public final static Property AccessToken = new Property(3, String.class, "accessToken", false, "ACCESS_TOKEN");
        public final static Property Time = new Property(4, String.class, "time", false, "TIME");
        public final static Property UserName = new Property(5, String.class, "userName", false, "USER_NAME");
        public final static Property PhoneNumber = new Property(6, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property UserImageUrl = new Property(7, String.class, "userImageUrl", false, "USER_IMAGE_URL");
        public final static Property ProvinceName = new Property(8, String.class, "provinceName", false, "PROVINCE_NAME");
        public final static Property ProvinceId = new Property(9, String.class, "provinceId", false, "PROVINCE_ID");
        public final static Property CityName = new Property(10, String.class, "cityName", false, "CITY_NAME");
        public final static Property CityId = new Property(11, String.class, "cityId", false, "CITY_ID");
        public final static Property DistrictName = new Property(12, String.class, "districtName", false, "DISTRICT_NAME");
        public final static Property DistrictId = new Property(13, String.class, "districtId", false, "DISTRICT_ID");
        public final static Property Registered = new Property(14, String.class, "registered", false, "REGISTERED");
        public final static Property XxCode = new Property(15, String.class, "xxCode", false, "XX_CODE");
        public final static Property Role = new Property(16, String.class, "role", false, "ROLE");
        public final static Property StudentName = new Property(17, String.class, "studentName", false, "STUDENT_NAME");
        public final static Property Type = new Property(18, Integer.class, "type", false, "TYPE");
        public final static Property AddGlag = new Property(19, String.class, "addGlag", false, "ADD_GLAG");
        public final static Property Pinyin = new Property(20, String.class, "pinyin", false, "PINYIN");
        public final static Property IsChat = new Property(21, Integer.class, "isChat", false, "IS_CHAT");
        public final static Property Ischecked = new Property(22, Boolean.class, "ischecked", false, "ISCHECKED");
        public final static Property Flag = new Property(23, Integer.class, "flag", false, "FLAG");
        public final static Property StoreUid = new Property(24, String.class, "storeUid", false, "STORE_UID");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CLASS_BLOCKED\" TEXT," + // 1: classBlocked
                "\"USER_ID\" TEXT," + // 2: userId
                "\"ACCESS_TOKEN\" TEXT," + // 3: accessToken
                "\"TIME\" TEXT," + // 4: time
                "\"USER_NAME\" TEXT," + // 5: userName
                "\"PHONE_NUMBER\" TEXT," + // 6: phoneNumber
                "\"USER_IMAGE_URL\" TEXT," + // 7: userImageUrl
                "\"PROVINCE_NAME\" TEXT," + // 8: provinceName
                "\"PROVINCE_ID\" TEXT," + // 9: provinceId
                "\"CITY_NAME\" TEXT," + // 10: cityName
                "\"CITY_ID\" TEXT," + // 11: cityId
                "\"DISTRICT_NAME\" TEXT," + // 12: districtName
                "\"DISTRICT_ID\" TEXT," + // 13: districtId
                "\"REGISTERED\" TEXT," + // 14: registered
                "\"XX_CODE\" TEXT," + // 15: xxCode
                "\"ROLE\" TEXT," + // 16: role
                "\"STUDENT_NAME\" TEXT," + // 17: studentName
                "\"TYPE\" INTEGER," + // 18: type
                "\"ADD_GLAG\" TEXT," + // 19: addGlag
                "\"PINYIN\" TEXT," + // 20: pinyin
                "\"IS_CHAT\" INTEGER," + // 21: isChat
                "\"ISCHECKED\" INTEGER," + // 22: ischecked
                "\"FLAG\" INTEGER," + // 23: flag
                "\"STORE_UID\" TEXT);"); // 24: storeUid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classBlocked = entity.getClassBlocked();
        if (classBlocked != null) {
            stmt.bindString(2, classBlocked);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(3, userId);
        }
 
        String accessToken = entity.getAccessToken();
        if (accessToken != null) {
            stmt.bindString(4, accessToken);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(6, userName);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(7, phoneNumber);
        }
 
        String userImageUrl = entity.getUserImageUrl();
        if (userImageUrl != null) {
            stmt.bindString(8, userImageUrl);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(9, provinceName);
        }
 
        String provinceId = entity.getProvinceId();
        if (provinceId != null) {
            stmt.bindString(10, provinceId);
        }
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(11, cityName);
        }
 
        String cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindString(12, cityId);
        }
 
        String districtName = entity.getDistrictName();
        if (districtName != null) {
            stmt.bindString(13, districtName);
        }
 
        String districtId = entity.getDistrictId();
        if (districtId != null) {
            stmt.bindString(14, districtId);
        }
 
        String registered = entity.getRegistered();
        if (registered != null) {
            stmt.bindString(15, registered);
        }
 
        String xxCode = entity.getXxCode();
        if (xxCode != null) {
            stmt.bindString(16, xxCode);
        }
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(17, role);
        }
 
        String studentName = entity.getStudentName();
        if (studentName != null) {
            stmt.bindString(18, studentName);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(19, type);
        }
 
        String addGlag = entity.getAddGlag();
        if (addGlag != null) {
            stmt.bindString(20, addGlag);
        }
 
        String pinyin = entity.getPinyin();
        if (pinyin != null) {
            stmt.bindString(21, pinyin);
        }
 
        Integer isChat = entity.getIsChat();
        if (isChat != null) {
            stmt.bindLong(22, isChat);
        }
 
        Boolean ischecked = entity.getIschecked();
        if (ischecked != null) {
            stmt.bindLong(23, ischecked ? 1L: 0L);
        }
 
        Integer flag = entity.getFlag();
        if (flag != null) {
            stmt.bindLong(24, flag);
        }
 
        String storeUid = entity.getStoreUid();
        if (storeUid != null) {
            stmt.bindString(25, storeUid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classBlocked = entity.getClassBlocked();
        if (classBlocked != null) {
            stmt.bindString(2, classBlocked);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(3, userId);
        }
 
        String accessToken = entity.getAccessToken();
        if (accessToken != null) {
            stmt.bindString(4, accessToken);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(6, userName);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(7, phoneNumber);
        }
 
        String userImageUrl = entity.getUserImageUrl();
        if (userImageUrl != null) {
            stmt.bindString(8, userImageUrl);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(9, provinceName);
        }
 
        String provinceId = entity.getProvinceId();
        if (provinceId != null) {
            stmt.bindString(10, provinceId);
        }
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(11, cityName);
        }
 
        String cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindString(12, cityId);
        }
 
        String districtName = entity.getDistrictName();
        if (districtName != null) {
            stmt.bindString(13, districtName);
        }
 
        String districtId = entity.getDistrictId();
        if (districtId != null) {
            stmt.bindString(14, districtId);
        }
 
        String registered = entity.getRegistered();
        if (registered != null) {
            stmt.bindString(15, registered);
        }
 
        String xxCode = entity.getXxCode();
        if (xxCode != null) {
            stmt.bindString(16, xxCode);
        }
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(17, role);
        }
 
        String studentName = entity.getStudentName();
        if (studentName != null) {
            stmt.bindString(18, studentName);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(19, type);
        }
 
        String addGlag = entity.getAddGlag();
        if (addGlag != null) {
            stmt.bindString(20, addGlag);
        }
 
        String pinyin = entity.getPinyin();
        if (pinyin != null) {
            stmt.bindString(21, pinyin);
        }
 
        Integer isChat = entity.getIsChat();
        if (isChat != null) {
            stmt.bindLong(22, isChat);
        }
 
        Boolean ischecked = entity.getIschecked();
        if (ischecked != null) {
            stmt.bindLong(23, ischecked ? 1L: 0L);
        }
 
        Integer flag = entity.getFlag();
        if (flag != null) {
            stmt.bindLong(24, flag);
        }
 
        String storeUid = entity.getStoreUid();
        if (storeUid != null) {
            stmt.bindString(25, storeUid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // classBlocked
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // accessToken
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // phoneNumber
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // userImageUrl
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // provinceName
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // provinceId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // cityName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // cityId
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // districtName
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // districtId
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // registered
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // xxCode
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // role
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // studentName
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // type
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // addGlag
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // pinyin
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // isChat
            cursor.isNull(offset + 22) ? null : cursor.getShort(offset + 22) != 0, // ischecked
            cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23), // flag
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24) // storeUid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setClassBlocked(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAccessToken(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPhoneNumber(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setUserImageUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setProvinceName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProvinceId(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCityName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCityId(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDistrictName(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setDistrictId(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setRegistered(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setXxCode(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setRole(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setStudentName(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setType(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setAddGlag(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setPinyin(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setIsChat(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setIschecked(cursor.isNull(offset + 22) ? null : cursor.getShort(offset + 22) != 0);
        entity.setFlag(cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23));
        entity.setStoreUid(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
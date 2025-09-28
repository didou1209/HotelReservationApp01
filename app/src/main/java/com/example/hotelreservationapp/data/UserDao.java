package com.example.hotelreservationapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private final DBHelper helper;

    public UserDao(Context ctx) {
        this.helper = new DBHelper(ctx);
    }

    public boolean emailExists(String email) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT 1 FROM users WHERE email=? LIMIT 1",
                new String[]{email.toLowerCase().trim()});
        boolean exists = c.moveToFirst();
        c.close();
        return exists;
    }

    public boolean insertUser(String name, String email, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email.toLowerCase().trim());
        cv.put("password", password);       // (démo) pas hashé
        cv.put("role", "client");           // on force client
        return db.insert("users", null, cv) != -1;
    }

    public String getRoleIfLoginOK(String email, String password) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT role FROM users WHERE email=? AND password=? LIMIT 1",
                new String[]{email.toLowerCase().trim(), password});
        String role = null;
        if (c.moveToFirst()) role = c.getString(0);
        c.close();
        return role; // null si mauvais identifiants
    }

    // Option: créer un admin de test une fois
    public void ensureAdminTestUser() {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT 1 FROM users WHERE email='admin@demo.com' LIMIT 1", null);
        boolean exists = c.moveToFirst();
        c.close();
        if (!exists) {
            ContentValues cv = new ContentValues();
            cv.put("name", "Admin Demo");
            cv.put("email", "admin@demo.com");
            cv.put("password", "admin1234");
            cv.put("role", "admin");
            db.insert("users", null, cv);
        }
    }
}
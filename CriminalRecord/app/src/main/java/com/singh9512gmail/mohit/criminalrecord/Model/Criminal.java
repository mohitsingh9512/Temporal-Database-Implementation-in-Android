package com.singh9512gmail.mohit.criminalrecord.Model;

import java.io.Serializable;

/**
 * Created by tusharaggarwal on 01/11/17.
 */

public class Criminal implements Serializable {

    //Private variables
    private int _id;
    private String _ref_id;
    private String _name;
    private String _address;
    private String _valid_from;
    private String _valid_to;


    //Empty Constructor
    public Criminal(){

    }

    public Criminal(int _id, String _ref_id, String _name, String _address, String _valid_from, String _valid_to) {
        this._id = _id;
        this._ref_id = _ref_id;
        this._name = _name;
        this._address = _address;
        this._valid_from = _valid_from;
        this._valid_to = _valid_to;
    }

    public Criminal(String _ref_id, String _name, String _address, String _valid_from, String _valid_to) {
        this._ref_id = _ref_id;
        this._name = _name;
        this._address = _address;
        this._valid_from = _valid_from;
        this._valid_to = _valid_to;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_valid_from() {
        return _valid_from;
    }

    public void set_valid_from(String _valid_from) {
        this._valid_from = _valid_from;
    }

    public String get_valid_to() {
        return _valid_to;
    }

    public void set_valid_to(String _valid_to) {
        this._valid_to = _valid_to;
    }

    public String get_ref_id() {
        return _ref_id;
    }

    public void set_ref_id(String _ref_id) {
        this._ref_id = _ref_id;
    }
}

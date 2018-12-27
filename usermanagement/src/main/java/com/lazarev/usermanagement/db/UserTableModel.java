package com.lazarev.usermanagement.db;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.lazarev.usermanagement.User;

import jade.util.leap.ArrayList;

public class UserTableModel extends AbstractTableModel {

	 private List users;
	    private static final String[] COLUMN_NAMES = {Message.getString("id"), Message.getString("firstname"), Message.getString("lastname")};
	    private static final  Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};

	    public UserTableModel(Collection users) {
	        super();
	        this.users = (List) new ArrayList();
	    }

	    @Override
	    public int getRowCount() {
	        return users.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return 3;
	    }

	    @Override
	    public Object getValueAt(int row, int column) {
	        User user = (User) users.get(row);
	        switch(column){
	            case 0:
	                return user.getiD();
	            case 1:
	                return user.getFirstName();
	            case 2:
	                return user.getLastName();
	        }
	        return null;
	    }

	    @Override
	    public Class<?> getColumnClass(int i) {
	        return COLUMN_CLASSES[i];
	    }

	    @Override
	    public String getColumnName(int i) {
	        return COLUMN_NAMES[i];
	    }

	    public void addUsers(Collection users) {
	        this.users.addAll(users);
	    }

	    public void clearUsers() {
	        this.users = (List) new ArrayList();
	    }

}

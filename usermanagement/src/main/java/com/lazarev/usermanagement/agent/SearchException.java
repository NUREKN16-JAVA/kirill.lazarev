package com.lazarev.usermanagement.agent;

public class SearchException extends Exception {
	 public SearchException(String s) {
	        super(s);
	    }

	    public SearchException(Exception e) {
	        super(e);
	    }
}

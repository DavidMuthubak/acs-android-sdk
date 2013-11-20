package com.appcelerator.cloud.sdk;

import org.json.JSONException;
import org.json.JSONObject;

public class CCMeta {
	private JSONObject json;
	private String status;
	private int code;
	private String message;
	private String method;

	// Pagination info (optional)
	private CCPagination pagination;

	public String getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getMethod() {
		return method;
	}

	public CCPagination getPagination() {
		return pagination;
	}

	public String toString() {
		if (json != null)
			return json.toString();
		else
			return "";
	}

	public CCMeta(JSONObject jObject) throws ACSClientError {
		json = jObject;
		try {
			status = jObject.getString("status").trim();
		} catch (JSONException e1) {
			throw new ACSClientError("Invalid Server Response: CCMeta: Missing status");
		}
		try {
			code = Integer.parseInt(jObject.getString("code").trim());
		} catch (NumberFormatException e1) {
			throw new ACSClientError("Invalid Server Response: CCMeta: code should be a number");
		} catch (JSONException e1) {
			throw new ACSClientError("Invalid Server Response: CCMeta: Missing code");
		}

		try {
			// optional
			message = jObject.getString("message").trim();
		} catch (Exception e) {
		}

		try {
			method = jObject.getString("method_name").trim();
		} catch (Exception e) {
		}

		try {
			pagination = new CCPagination(jObject);
		} catch (ACSClientError e) {

		}
	}
}
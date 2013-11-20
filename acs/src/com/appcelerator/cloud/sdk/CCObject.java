package com.appcelerator.cloud.sdk;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

public class CCObject implements Externalizable {
	protected String objectId;
	protected java.util.Date createdDate;
	protected java.util.Date updatedDate;

	public String getObjectId() {
		return objectId;
	}

	public java.util.Date getCreatedDate() {
		return createdDate;
	}

	public java.util.Date getUpdatedDate() {
		return updatedDate;
	}

	public CCObject(JSONObject jObject) throws ACSClientError {
		try {
			objectId = jObject.getString(CCConstants.ID);
		} catch (JSONException e) {
			throw new ACSClientError("Invalid server response: " + this.getClass().getName() + ": Missing id");
		}

		SimpleDateFormat ccDateFormatter = new SimpleDateFormat(CCConstants.DATE_FORMAT);
		try {
			String dateString = jObject.getString(CCConstants.UPDATED_AT);
			updatedDate = ccDateFormatter.parse(dateString);
		} catch (JSONException e) {
			//        throw new ACSClientError("Invalid Server Response: " + this.getClass().getName() + ": Missing updatedDate");
		} catch (ParseException e) {
			throw new ACSClientError("Invalid Server Response: " + this.getClass().getName() + ": invalid date format for updatedDate");
		}  

		try {
			String dateString = jObject.getString(CCConstants.CREATED_AT);
			createdDate = ccDateFormatter.parse(dateString);
		} catch (JSONException e) {
			//        throw new ACSClientError("Invalid Server Response: " + this.getClass().getName() + ": Missing createdDate");
		} catch (ParseException e) {
			throw new ACSClientError("Invalid Server Response: " + this.getClass().getName() + ": invalid date format for createdDate");
		}

	}

	protected CCObject() {

	}

	public void readExternal(ObjectInput input) throws IOException,
	ClassNotFoundException {
		boolean hasObjectId = input.readBoolean();
		if (hasObjectId) {
			objectId = input.readUTF();
		}

		SimpleDateFormat ccDateFormatter = new SimpleDateFormat(CCConstants.DATE_FORMAT);
		boolean hasCreatedDate = input.readBoolean();
		if (hasCreatedDate) {
			String dateString = input.readUTF();
			try {
				createdDate = ccDateFormatter.parse(dateString);
			} catch (ParseException e) {
			}
		}

		boolean hasUpdatedDate = input.readBoolean();
		if (hasUpdatedDate) {
			String dateString = input.readUTF();
			try {
				updatedDate = ccDateFormatter.parse(dateString);
			} catch (ParseException e) {
			}
		}
	}

	public void writeExternal(ObjectOutput output) throws IOException {
		boolean hasObjectId = true;
		if (objectId == null || objectId.trim().length() == 0) {
			hasObjectId = false;
		} 
		output.writeBoolean(hasObjectId);

		if (hasObjectId) {
			output.writeUTF(objectId);
		}

		SimpleDateFormat ccDateFormatter = new SimpleDateFormat(CCConstants.DATE_FORMAT);

		if (createdDate != null) {
			output.writeBoolean(true);
			output.writeUTF(ccDateFormatter.format(createdDate));
		} else {
			output.writeBoolean(false);
		}

		if (updatedDate != null) {
			output.writeBoolean(true);
			output.writeUTF(ccDateFormatter.format(updatedDate));
		} else {
			output.writeBoolean(false);
		}

	}
}
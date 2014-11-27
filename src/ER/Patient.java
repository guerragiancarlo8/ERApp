package ER;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
// import java.util.ArrayList;
//import java.util.Date;
//import java.util.Calendar;
// import csc207b.group_0867.erapp.Record;
// import csc207b.group_0867.erapp.map;
import java.util.Map.Entry;

/**
 * The patient class contains all information that should be associated with
 * patient objects. Variables are name, the date of birth, the health card
 * number and all records associated with the patient.
 * 
 * @author Z.LU, L.CHEN, T.TRUONG, G.GUERRA.
 */

public class Patient implements Serializable, Comparable<Patient> {
	/**
	 * This UID was generated by Eclipse.
	 */
	private static final long serialVersionUID = -9037197185357157406L;
	/**
	 * Patient's name.
	 */
	private String name;
	/**
	 * Patient's date of birth.
	 */
	private String birthDate;
	/**
	 * Patient's health card number.
	 */
	private String healthCardNum;
	/**
	 * Patient's prescription history.
	 */
	private Map<String, String> prescription;
	/**
	 * Patient's age.
	 */
	private int age;

	/**
	 * Patient's urgency points based on hospital policy.
	 */
	private int urgency;

	/**
	 * Patient's record history.
	 */
	protected TreeMap<String, Record> listOfRecords;

	// private ArrayList<Record> arrayOfRecords;

	// The following are the variables that are not required for design2 feature
	// list

	// protected String seenByDoc;
	// protected int systolic;
	// protected int diastolic;
	// protected String textDescription;

	// The following are all transferred into class Record
	// protected String arrivalTime;
	// protected map<String, Float> temperature;
	// protected map<String, Integer> heartRate;
	// protected map<String, Integer> bloodPressure;

	/**
	 * An empty constructor of patient.
	 */
	public Patient() {
	}

	/**
	 * Patient Constructor taking patient's name, birth of date, and health card
	 * number.
	 * 
	 * @param name
	 *            patient's name.
	 * @param dob
	 *            patient's date of birth.
	 * @param healthCardNum
	 *            patient's health card number.
	 */
	public Patient(String name, String dob, String healthCardNum) {
		// Date dob = new Date(year, month - 1, day);
		// Calendar dob = Calendar.set(year, month - 1, day))
		this.setName(name);
		this.setHealthCardNum(healthCardNum);
		this.setBirthDate(dob);
		this.listOfRecords = new TreeMap<String, Record>();
		this.setPrescription(new TreeMap<String, String>());

		String[] birthInfo = dob.split("-");
		Integer yearOfBirth = Integer.valueOf(birthInfo[0]);
		this.age = 2014 - yearOfBirth;
	}

	/*
	 * public int getUrgency() { // TO Do calculate the urgency points return
	 * urgency; }
	 * 
	 * public void setUrgency(int urgency) { this.urgency = urgency; }
	 * 
	 * public String getArrivalTime() { return arrivalTime; }
	 * 
	 * public void setArrivalTime(int year, int month, int day, int hrs, int
	 * min) { this.arrivalTime = new Date(year, month, day, hrs, min); }
	 */

	/**
	 * Returns a string representation of the patient.
	 * 
	 * @returns a string representing the patient.
	 */
	@Override
	public String toString() {
		String records = "";
		if (!listOfRecords.isEmpty()) {
			for (Record r : listOfRecords.values()) {
				records = records + r.toString() + ";";
			}
		}
		return getHealthCardNum() + "," + getName() + "," + getBirthDate()
				+ "," + prescriptionToString() + "," + records;
	}

	/**
	 * Returns a string representation of a prescription.
	 * 
	 * @return a string representing the prescription.
	 */
	public String prescriptionToString() {
		String result = "";
		Iterator<Entry<String, String>> iter = getPrescription().entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			result = result + entry.getKey() + ">"
					+ entry.getValue().replace(",", "/") + ";";
		}
		return result;
	}

	/**
	 * Retrieves existing records stored in the patient's records history.
	 * 
	 * @return the list of records the patient has.
	 */
	public Map<String, Record> getRecords() {
		return this.listOfRecords;
	}

	/**
	 * Gets the name of the patient.
	 * 
	 * @return the name of the patient
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the patient.
	 * 
	 * @param name
	 *            A name in string format.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Updates urgency point for the Patient based on hospital policy.
	 */
	public void updateUrgency() {
		listOfRecords.lastEntry().getValue().updateUrgency();
		Integer point = listOfRecords.lastEntry().getValue().getUrgency();
		if (age < 2) {
			point++;
		}
		this.urgency = point;
	}

	/**
	 * Compare urgency point with Patient p1 to determine order.
	 * 
	 * @param p1
	 *            A pattient that being compared.
	 */
	public int compareTo(Patient p1) {
		return p1.getUrgency() - this.getUrgency();
	}

	/**
	 * Returns the prescription map for the particular patient object.
	 * 
	 * @return the prescription map.
	 */
	public Map<String, String> getPrescription() {
		return prescription;
	}

	/**
	 * Builds the prescription map based on the prescription parameter.
	 * 
	 * @param prescription
	 *            The prescriptions to be added.
	 */
	public void setPrescription(Map<String, String> prescription) {
		this.prescription = prescription;
	}

	/**
	 * Returns the health card number for a particular patient.
	 * 
	 * @return the health card number.
	 */
	public String getHealthCardNum() {
		return healthCardNum;
	}

	/**
	 * Sets the healthcard number variable for a patient object.
	 * 
	 * @param healthCardNum
	 *            The healthcard number that will be associated to a particular
	 *            patient object.
	 */
	public void setHealthCardNum(String healthCardNum) {
		this.healthCardNum = healthCardNum;
	}

	/**
	 * Returns the birthDate variable for a particular patient.
	 * 
	 * @return the birth date.
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * sets the birthDate variable for a particular patient object.
	 * 
	 * @param birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Returns the urgency number for a particular patient object.
	 * 
	 * @return the urgency number.
	 */
	public int getUrgency() {
		return urgency;
	}

	/**
	 * Sets the urgency value for the urgency variable of a particular patient
	 * object.
	 * 
	 * @param urgency
	 *            The urgency value.
	 */
	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

}
package com.uis.taskmanagerapp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.uis.taskmanagerapp.constants.Constants;

//Utitlity class for all the static methods used in the AppUI
public class TaskUtility {
	public static boolean validateName(String categoryname) {
		if (categoryname == null || categoryname.trim().equals("")) {
			return false;
		}

		if (categoryname.split(" ").length > 1) {
			return false;
		}

		if (!Character.isLetter(categoryname.charAt(0))) {
			return false;
		}

		for (int i = 1; i < categoryname.length(); i++) {
			char c = categoryname.charAt(i);
			if (!(Character.isDigit(c) || Character.isLetter(c))) {
				return false;
			}
		}
		return true;
	}

	public static Date validateDate(Date date) {
		try {
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String nowDate = sdf.format(calender.getTime());
			if (date.compareTo(sdf.parse(nowDate)) >= 0) {
				return date;
			} else {
				System.err.println("Date has to be either " + sdf.format(nowDate) + " or has to be after this date");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static boolean isValidTaskName(String taskName) {
		if (taskName != null && !taskName.trim().isBlank()) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isValidDescription(String description) {
		if (description != null && !description.trim().isBlank()) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isPriorityValid(int priority) {
		if (priority > 0 && priority <= 10) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTaskUpdated(Object categoryname, Object oldname, Object newname) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		File file = null;
		if (categoryname instanceof String && oldname instanceof String && newname instanceof String) {
			String caString = (String) categoryname;
			String olString = (String) oldname;
			String newString = (String) newname;
			try {
				file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + caString);
				if (file.exists() && file.isFile() && caString.endsWith(".txt")) {
					reader = new BufferedReader(new FileReader(file));
					writer = new BufferedWriter(new FileWriter(file, true));
					String line;
					writer.write("======================================Updated Part==============================");
					writer.newLine();
					while ((line = reader.readLine()) != null) {
						StringBuilder sb = new StringBuilder();
						String[] files = line.split(":");
						for (int i = 0; i < files.length; i++) {
							if (files[i].equalsIgnoreCase(olString)) {
								sb.append(files[i].replaceAll(olString, newString) + ":");
							} else {
								sb.append(files[i] + ":");
							}
						}
						writer.write(line.replaceAll(line, sb.toString()));
						writer.newLine();

					}
					writer.write(
							"=====================================================================================");
					writer.newLine();
				} else {
					throw new IllegalArgumentException("File should be given as parameter, ends with .txt");
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else if (categoryname instanceof String && oldname instanceof Date && newname instanceof Date) {
			String caString = (String) categoryname;
			Date oldDate = (Date) oldname;
			Date newDate = (Date) newname;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + caString);
				if (file.exists() && file.isFile() && caString.endsWith(".txt")) {
					reader = new BufferedReader(new FileReader(file));
					writer = new BufferedWriter(new FileWriter(file, true));
					String line;
					writer.write("======================================Updated Part==============================");
					writer.newLine();
					while ((line = reader.readLine()) != null) {
						StringBuilder sb = new StringBuilder();
						String[] files = line.split(":");
						for (int i = 0; i < files.length; i++) {
							if (files[i].equals(sdf.format(oldDate))) {
								String newdt = sdf.format(oldDate);
								String olddt = sdf.format(newDate);
								sb.append(files[i].replaceAll(olddt, newdt) + ":");
							} else {
								sb.append(files[i]);
							}
						}
						writer.write(line.replaceAll(line, sb.toString()));
						writer.newLine();
					}
					writer.write(
							"=====================================================================================");
					writer.newLine();
				} else {
					throw new IllegalArgumentException("File should be given as parameter, ends with .txt");
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else if (categoryname instanceof String && oldname instanceof Integer && newname instanceof Integer) {
			String caString = (String) categoryname;
			Integer oldInteger = (Integer) oldname;
			Integer neInteger = (Integer) newname;
			try {

				file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + caString);
				if (file.exists() && file.isFile() && caString.endsWith(".txt") && isPriorityValid(oldInteger)
						&& isPriorityValid(neInteger)) {
					reader = new BufferedReader(new FileReader(file));
					writer = new BufferedWriter(new FileWriter(file, true));
					String line;
					writer.write("======================================Updated Part==============================");
					writer.newLine();
					while ((line = reader.readLine()) != null) {
						StringBuilder sb = new StringBuilder();
						String[] files = line.split(":");
						for (int i = 0; i < files.length; i++) {
							if (files[i].equalsIgnoreCase(String.valueOf(oldInteger))) {
								sb.append(files[i].replaceAll(String.valueOf(oldInteger), String.valueOf(neInteger))
										+ ":");
							} else {
								sb.append(files[i] + ":");
							}
						}
						writer.write(line.replaceAll(line, sb.toString()));
						writer.newLine();
					}
					writer.write(
							"=====================================================================================");
					writer.newLine();
				} else {
					throw new IllegalArgumentException("File should be given as parameter, ends with .txt");
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;

			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		} else {
			throw new IllegalArgumentException("Invalid inputs are given, try again!!!!");
		}
		

	}

}

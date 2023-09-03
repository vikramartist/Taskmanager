package com.uis.taskmanagerapp.controlmodel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aspose.words.Document;
import com.uis.taskmanagerapp.constants.Constants;
import com.uis.taskmanagerapp.util.TaskUtility;

public class TaskModel {

	public List<TaskBean> getTasks(String categoryname) {
		BufferedReader reader = null;
		String line;

		try {
			reader = new BufferedReader(
					new FileReader(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + categoryname));
			TaskBean bean = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			List<TaskBean> tasks = new ArrayList<TaskBean>();
			while ((line = reader.readLine()) != null) {
				String[] lines = line.split(":");
				bean = new TaskBean(lines[0], lines[1], sdf.parse(lines[2]), Integer.parseInt(lines[3]), lines[4]);
				tasks.add(bean);
			}
			return tasks;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean isCategoryNameValid(String categoryname) {
		return new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + categoryname + ".txt").exists();
	}

	public void loadCategory(String categoryname) {
		BufferedReader reader = null;
		try {
			File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + categoryname);
			if (file.exists() && file.isFile()) {
				reader = new BufferedReader(new FileReader(file));
				String file1;
				System.out.println("Files in the category " + categoryname);
				while ((file1 = reader.readLine()) != null) {
					System.out.println(file1);
				}

			} else {
				throw new IllegalArgumentException(
						"File doesn't exist or is not of the extension .txt, check again!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteCategory(String categoryname) {
		File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files" + "\\" + categoryname);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("Category " + categoryname + " is deleted Successfully");
			} else {
				System.err.println("Category Deletion Failed");
			}
		} else {
			throw new IllegalArgumentException("File doesn't exist or is not of the extension .txt, check again!!!");
		}

	}

	public void listCategory() {
		File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files");
		File[] files = file.listFiles();
		for (File fi : files) {
			System.out.println(fi);
		}
	}

	public void searchCategory(String categoryname) {
		File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files");
		String[] str = file.list();
		String searched="";
		for (int i = 0; i < str.length; i++) {
			if (str[i].equalsIgnoreCase(categoryname)) {
				System.out.println("\t" + str[i]);
				searched += "Found";
				break;
			}

		}
		if (searched.equals("Found")) {
			System.out.println("Search of " + categoryname + " is successfull!!!!");
		} else {
			System.out.println("Search failed!!!");
		}
	}

	public String addtask(TaskBean bean, String categoryName) {
		BufferedWriter writer = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			writer = new BufferedWriter(new FileWriter(
					Constants.PATH + "\\src\\main\\resources\\Category_files\\" + categoryName + ".txt", true));
			writer.write(bean.getTaskName() + ":" + bean.getDescription() + ":" + sdf.format(bean.getPlannedDate())
					+ ":" + bean.getPriority() + ":" + bean.getTags());
			writer.newLine();
			return Constants.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.FAILURE;
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean updateTaskName(String categoryname, String oldtask, String newtask) {
		return TaskUtility.isTaskUpdated(categoryname, oldtask, newtask);
	}

	public boolean updateDescription(String categoryName, String olddescription, String newdescription) {
		return TaskUtility.isTaskUpdated(categoryName, olddescription, newdescription);
	}

	public boolean updateDate(String categoryname, Date olddate, Date newdate) {
		return TaskUtility.isTaskUpdated(categoryname, olddate, newdate);
	}

	public boolean updatePriority(String categoryname, int oldpriority, int newpriority) {
		return TaskUtility.isTaskUpdated(categoryname, categoryname, categoryname);
	}

	public boolean updateTag(String categoryname, String oldtag, String newtag) {
		return TaskUtility.isTaskUpdated(categoryname, oldtag, newtag);
	}

	public boolean isTaskDeleted(String categoryname, String taskname) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + categoryname);
			if (file.exists() && file.isFile() && categoryname.endsWith(".txt")) {
				reader = new BufferedReader(new FileReader(file));
				writer = new BufferedWriter(new FileWriter(file, true));
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.contains(taskname)) {
						writer.write(line.replace(line, "Deleted taskname-" + taskname));
						break;
					}
					writer.newLine();
				}

				return true;
			} else {
				throw new IllegalArgumentException("File should be given as parameter, ends with .txt");
			}
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
	}

	public boolean isTaskFound(String categoryname, String taskName) {
		BufferedReader read = null;
		try {
			File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files\\" + categoryname);
			if (file.exists() && file.isFile()) {
				read = new BufferedReader(new FileReader(file));
				String readl;
				StringBuilder sb = new StringBuilder();
				System.out.println("taskname-" + taskName + " found");
				while ((readl = read.readLine()) != null) {
					if (readl.contains(taskName)) {
						sb.append(readl + " ");
					}
				}
				System.out.println(sb.toString());
				return true;
			} else {
				throw new IllegalArgumentException("File should be given as parameter, ends with .txt");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void covertTxtToPdf() {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File(Constants.PATH + "\\src\\main\\resources\\Category_files");
			File destfile = new File(Constants.PATH + "\\src\\main\\resources\\Final.txt");
			writer = new BufferedWriter(new FileWriter(destfile));
			if (file.exists()) {
				String[] files = file.list();
				for (int i = 0; i < files.length; i++) {
					writer.write(files[i]);
					writer.newLine();
				}

			} else {
				System.err.println("File does not exist");
			}
			Document doc = new Document(Constants.PATH + "\\src\\main\\resources\\Final.txt");
			doc.save(Constants.PATH + "\\src\\main\\resources\\Output.pdf");
			System.out.println("Document converted to PDF Successfully");
		} catch (Exception e) {
			e.printStackTrace();

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

	}
}

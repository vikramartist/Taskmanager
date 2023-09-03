package com.uis.taskmanagerapp.ui;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import com.uis.taskmanagerapp.constants.Constants;
import com.uis.taskmanagerapp.controlmodel.TaskBean;
import com.uis.taskmanagerapp.controlmodel.TaskModel;
import com.uis.taskmanagerapp.util.Logger;
import com.uis.taskmanagerapp.util.TaskUtility;

public class AppUI {

	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);

		try {
			int choice1 = 0;
			Logger logger = Logger.getInstance();
			TaskModel model = new TaskModel();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			logger.log("================================================================================");
			logger.log("-------Starting the Task Manager App-------");
			while (choice1 != 7) {
				String categoryName = null, taskName = null, descriptionName = null, createDate = null, tags = null,
						result = null, ucategoryName = null, utaskName = null;
				int priority = 0, choice2 = 0;
				System.out.println("===================Welcome to Task Manager App=================== ");
				System.out.println("Press 1 to Create Category");
				System.out.println("Press 2 to Load Category");
				System.out.println("Press 3 to Delete Category");
				System.out.println("Press 4 to List Category");
				System.out.println("Press 5 to Search Category");
				System.out.println("Press 6 to Export Category");
				System.out.println("Press 7 to Exit the App");
				choice1 = sc1.nextInt();
				switch (choice1) {
				case 1: {
					logger.log("-------Creating the Category-------");
					System.out.println("Enter the Category Name");
					categoryName = sc2.nextLine();
					if (!TaskUtility.validateName(categoryName)) {
						System.err.println(
								"The Category Name is Invalid, it should start with letter with no spaces, no special characters and should have one word");
						categoryName = sc2.nextLine();
					} else {
						while (choice2 != 6) {
							System.out.println("Press 1 to add task");
							System.out.println("Press 2 to Update task");
							System.out.println("Press 3 to Delete task");
							System.out.println("Press 4 to List task");
							System.out.println("Press 5 to Search task");
							System.out.println("Press 6 to Go back");
							choice2 = sc1.nextInt();
							switch (choice2) {
							case 1: {
								logger.log("------Creating the task-----");
								System.out.println("Enter the Task Name");
								taskName = sc2.nextLine();
								while (!TaskUtility.isValidTaskName(taskName)) {
									taskName = sc2.nextLine();
								}

								System.out.println("Enter the Task Description");
								descriptionName = sc2.nextLine();
								while (!TaskUtility.isValidDescription(descriptionName)) {
									descriptionName = sc2.nextLine();
								}

								System.out.println(
										"Enter Date Completion Planned (dd/mm/yyyy)(Date has to begin from today's date)");
								createDate = sc2.nextLine();
								while (TaskUtility.validateDate(sdf.parse(createDate)) == null) {
									System.err.println(
											"The date given is before current dats(today's date), give either today's date or dates after today's date, try again, give a valid date!!!");
									createDate = sc2.nextLine();
								}

								System.out.println("Enter Priority (1-VERY-LOW,(2-9)-MEDIUM, 10-VERY-HIGH)");
								priority = sc1.nextInt();
								while (!TaskUtility.isPriorityValid(priority)) {
									System.err
											.println("Priority has to be within the range(1-10), please try again!!!");
									priority = sc1.nextInt();
								}
								System.out.println("Enter Tags (comma separated)");
								tags = sc2.nextLine();
								TaskBean bean = new TaskBean(taskName, descriptionName, sdf.parse(createDate), priority,
										tags);
								result = model.addtask(bean, categoryName);
								if (result.equals(Constants.SUCCESS)) {
									logger.log("----Task Added Successfully------");
									System.out.println("Task is Added Successfully");
								} else if (result.equals(Constants.FAILURE)) {
									logger.log("-----Task Addition Failed------");
									System.err.println("Task Addition Failed");
								}
								System.out.println("========Going back to main menu===========");
								break;
							}

							case 2: {
								System.out.println("Enter the Category Name");
								ucategoryName = sc2.nextLine();
								System.out.println("You are in " + ucategoryName + " category file");
								if (!model.isCategoryNameValid(ucategoryName)) {
									System.err.println("Invalid category name entered!!!, try again");
									ucategoryName = sc2.nextLine();
								} else {

									int choice3 = 0;
									while (choice3 != 4) {
										logger.log("---------Updating the task---------");
										System.out.println(
												"Press 1 to Update the Taskname/Description/Tags\nPress 2 to Update the Planned-Date\nPress 3 to Update Priority");
										System.out.println("Press 4 to go back to select Category");
										choice3 = sc1.nextInt();
										switch (choice3) {
										case 1: {
											System.out.println("Enter the task name which you want to update");
											utaskName = sc2.nextLine();
											System.out.println("Enter the new task name");
											String ntaskname = sc2.nextLine();
											if (TaskUtility.isTaskUpdated(ucategoryName + ".txt", utaskName,
													ntaskname)) {
												logger.log("-------Task Updated-----------");
												System.out.println("Task updation Successfull");
											} else {
												System.err.println("Task Updation Failed");
											}
											break;
										}

										case 2: {
											System.out.println("Enter the planned date to update (dd/mm/yyyy)");
											createDate = sc2.nextLine();
											System.out.println("Enter the new planned date");
											String newdate = sc2.nextLine();
											if (TaskUtility.isTaskUpdated(ucategoryName + ".txt", sdf.parse(createDate),
													sdf.parse(newdate))) {
												logger.log("----Date Updated----");
												System.out.println("Date Updated Successfully");
											} else {
												System.err.println("Date updation failed!!!");
											}
											break;
										}

										case 3: {
											System.out.println("Enter the priority to update");
											priority = sc1.nextInt();
											System.out.println("Enter the new priority");
											int newPriority = sc1.nextInt();
											if (TaskUtility.isTaskUpdated(ucategoryName + ".txt", priority,
													newPriority)) {
												logger.log("----priority Updated----");
												System.out.println("Priority Updated Successfully");
											} else {
												System.err.println("Priority updation failed!!!");
											}
											break;
										}

										case 4: {
											System.out.println("====Going back to menu====");
											break;
										}
										}
									}
								}

								break;
							}

							case 4: {
								logger.log("------Listing the task files----");
								System.out.println("Enter the category name");
								ucategoryName = sc2.nextLine();
								System.out.println("========Getting the tasks======");
								List<TaskBean> list = model.getTasks(ucategoryName + ".txt");
								System.out.println("These are the files in the category " + ucategoryName);
								for (TaskBean be : list) {
									System.out
											.println("Name:" + be.getTaskName() + " Description:" + be.getDescription()
													+ " Planned_Date:" + be.getPlannedDate() + " Tag:" + be.getTags());
								}
								break;
							}

							case 3: {
								logger.log("-------Deleting the task------");
								System.out.println("Enter the category name");
								ucategoryName = sc2.nextLine();
								System.out.println("Enter the taskname to be deleted");
								taskName = sc2.nextLine();
								if (model.isTaskDeleted(ucategoryName + ".txt", utaskName)) {
									System.out.println("Task Deleted Successfully");
									logger.log("----Task deleted successfully----------");
								} else {
									System.err.println("Task Deletion Failed");
								}
								break;
							}

							case 5: {
								logger.log("---------searching the task-------");
								System.out.println("Enter the categoryname");
								ucategoryName = sc2.nextLine();
								System.out.println("Enter the taskname to search");
								taskName = sc2.nextLine();
								if (model.isTaskFound(ucategoryName + ".txt", taskName)) {
									System.out.println("Searching done successfully");
									logger.log("-------searching done successfully-----");
								} else {
									System.err.println("Searching failed!!!!!!!!!");
								}
								break;
							}

							}
						}

					}
					break;
				}

				case 2: {
					logger.log("----Loading the Category---------");
					System.out.println("Enter the Category Name");
					categoryName = sc2.nextLine();
					model.loadCategory(categoryName + ".txt");
					break;
				}

				case 3: {
					logger.log("-------Deleting the Category-------");
					System.out.println("Enter the Category name");
					String categoryName2 = sc2.nextLine();
					model.deleteCategory(categoryName2 + ".txt");
					break; 
				}

				case 4: {
					logger.log("-----Listing the Categories--------");
					model.listCategory();
					break;
				}

				case 5: {
					logger.log("-------Searching the Categories---------");
					System.out.println("Enter the category name to search");
					categoryName = sc2.nextLine();
					model.searchCategory(categoryName + ".txt");
					break;
				}

				case 6: {
					logger.log("--------------Exporting the files-----------------");
					model.covertTxtToPdf();

					break;
				}

				case 7: {
					logger.log("-----------------Exiting the App-----------------");
					logger.log("=========================================================================");
					System.out.println(
							"=======================Exiting, Have a nice day------ Bye!!!!=======================");
					break;
				}

				default: {
					System.err.println("The Number is not in the option(1-7), Out Of Range!!!");
					break;
				}

				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

		sc1.close();
		sc2.close();
	}

}

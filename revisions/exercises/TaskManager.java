package revisions.exercises;

import java.util.ArrayList;
// import java.util.List;

public class TaskManager {
  private ArrayList<String> tasks;

  public TaskManager(){
    this.tasks = new ArrayList<>();
  }

  public void addTask(String task){
    tasks.add(task);
  }

  public void removeTask(int index){
    tasks.remove(index);
  }

  public void printTasks(){
    System.out.println("--- Current Tasks ---");
    int count = 0;
    for(String task: tasks){
      System.out.println(count+": "+task);
      count++;
    }
  }

  public static void main(String[] args) {
    TaskManager manager = new TaskManager();
    
    manager.addTask("Review Java Generics");
    manager.addTask("Master Collections Framework");
    manager.addTask("Write a compiler");
    
    manager.printTasks();
    
    System.out.println("\nRemoving the second task...");
    manager.removeTask(1); // Removes "Master Collections Framework"
    
    manager.printTasks();
    }
}

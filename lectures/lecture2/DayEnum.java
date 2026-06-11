package lectures.lecture2;

public enum DayEnum {
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY;

  private String action;

  public StringBuilder getAction(DayEnum today){
    StringBuilder sb = new StringBuilder();
    sb.append("I will ").append(action);
    return sb;
  }

}

package at.fhjoanneum.ippr.processengine.akka.tasks;

public enum TaskAllocation {

  // @formatter:off
  STATE_OBJECT_CHANGE_TASK("User.StateObjectChangeTask"),
  STATE_OBJECT_RETRIEVE_TASK("User.StateObjectRetrieveTask"),
  USER_ACTOR_INITIALIZE_TASK("User.UserActorInitializeTask"),
  PROCESS_STATE_TASK("Process.ProcessStateTask"),
  PROCESS_STOP_TASK("Process.ProcessStopTask"),
  PROCESS_STATE_CHANGE_TASK("Process.ProcessStateChangeTask"),
  PROCESS_START_TASK("ProcessSupervisor.ProcessStartTask"),
  PROCESS_INFO_TASK("ProcessSupervisor.ProcessInfoTask"),
  PROCESS_INITIALIZE_TASK("UserSupervisor.ProcessInitializeTask");
  // @formatter:on

  private final String actorName;

  private TaskAllocation(final String actorName) {
    this.actorName = actorName;
  }

  String getActorName() {
    return actorName;
  }
}
